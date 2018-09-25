package com.shchuryk.dao;

import com.shchuryk.model.Tour;
import com.shchuryk.model.Type;
import com.shchuryk.exception.ConnectorException;
import com.shchuryk.exception.DaoException;
import com.shchuryk.connection.ProxyConnection;

import java.sql.*;
import java.util.ArrayList;

public class DaoTour extends Dao<Tour> {
    private static final String QUERY_BURNING_TOURS = "SELECT tour.id, tour.name, tour.country,tour.start_date," +
            "tour.end_date,tour.type,tour.price,tour.burning,tour.discounts FROM tour " +
            "WHERE tour.burning = true";
    private static final String QUERY_TOUR_BY_ID = "SELECT tour.id, tour.name, tour.country,tour.start_date," +
            "tour.end_date,tour.type,tour.price,tour.burning,tour.discounts FROM tour WHERE id = ?";
    private static final String QUERY_ALL_TOURS = "SELECT * FROM tour";

    private static final String INSERT_TOUR = "INSERT INTO tour (name, country, start_date, end_date, type, price, burning, discounts) VALUES (?,?,?,?,?,?,?,?)";

    private static final String UPDATE_TOUR = "UPDATE tour SET name = ? WHERE id = ?";

    private static final String DELETE_TOUR = "DELETE FROM tour WHERE id = ?";

    public DaoTour() throws DaoException {
        super();
    }

    public boolean create(Tour entity) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connector.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_TOUR, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getCountry());
            preparedStatement.setDate(3, entity.getStartDate());
            preparedStatement.setDate(4, entity.getEndDate());
            preparedStatement.setString(5, entity.getType().toString().toLowerCase());
            preparedStatement.setInt(6, entity.getPrice());
            preparedStatement.setBoolean(7, entity.isBurning());
            preparedStatement.setDouble(8, entity.getDiscounts());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            entity.setTourId(resultSet.getInt(1));
        } catch (ConnectorException e) {
            throw new DaoException("Cannot get connection to database.");
        } catch (SQLException e) {
            throw new DaoException("Cannot execute SQL query.");
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new DaoException("Cannot close ResultSet.");
                }
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new DaoException("Cannot close PreparedStatement.");
                }
            if (connection != null) {
                connector.returnConnection(connection);
            }
        }
        return true;
    }

    public boolean update(Tour entity) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connector.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_TOUR);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getCountry());
            preparedStatement.executeUpdate();
        } catch (ConnectorException e) {
            throw new DaoException("Cannot get connection to database.");
        } catch (SQLException e) {
            throw new DaoException("Cannot execute SQL query.");
        } finally {
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new DaoException("Cannot close PreparedStatement.");
                }
            if (connection != null) {
                connector.returnConnection(connection);
            }
        }
        return true;
    }

    @SuppressWarnings("Duplicates")
    public boolean delete(int id) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connector.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_TOUR);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (ConnectorException e) {
            throw new DaoException("Cannot get connection to database.");
        } catch (SQLException e) {
            throw new DaoException("Cannot execute SQL query.");
        } finally {
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new DaoException("Cannot close PreparedStatement.");
                }
            if (connection != null) {
                connector.returnConnection(connection);
            }
        }
        return true;
    }

    public Tour read(int id) throws DaoException {
        Tour tour = null;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connector.getConnection();
            preparedStatement = connection.prepareStatement(QUERY_TOUR_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                tour = new Tour();
                tour.setTourId(id);
                tour.setName(resultSet.getString("name"));
                tour.setCountry(resultSet.getString("country"));
                tour.setStartDate(resultSet.getDate("start_date"));
                tour.setEndDate(resultSet.getDate("end_date"));
                tour.setType(Type.valueOf((resultSet.getObject("type")).toString().toUpperCase()));
                tour.setPrice(resultSet.getInt("price"));
                tour.setBurning(resultSet.getBoolean("burning"));
                tour.setDiscounts(resultSet.getDouble("discounts"));
            }
        } catch (ConnectorException e) {
            throw new DaoException("Cannot get connection to database.");
        } catch (SQLException e) {
            throw new DaoException("Cannot execute SQL query.");
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new DaoException("Cannot close ResultSet.");
                }
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new DaoException("Cannot close PreparedStatement.");
                }
            if (connection != null) {
                connector.returnConnection(connection);
            }
        }
        return tour;
    }

    public ArrayList<Tour> readAllTours() throws DaoException {
        ArrayList<Tour> tours = new ArrayList<>();
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connector.getConnection();
            preparedStatement = connection.prepareStatement(QUERY_ALL_TOURS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Tour tour = new Tour();
                tour.setTourId(resultSet.getInt("id"));
                tour.setName(resultSet.getString("name"));
                tour.setCountry(resultSet.getString("country"));
                tour.setStartDate(resultSet.getDate("start_date"));
                tour.setEndDate(resultSet.getDate("end_date"));
                tour.setType(Type.valueOf((resultSet.getObject("type")).toString().toUpperCase()));
                tour.setPrice(resultSet.getInt("price"));
                tour.setBurning(resultSet.getBoolean("burning"));
                tour.setDiscounts(resultSet.getDouble("discounts"));
                tours.add(tour);
            }
        } catch (ConnectorException e) {
            throw new DaoException("Cannot get connection to database.");
        } catch (SQLException e) {
            throw new DaoException("Cannot execute SQL query.");
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new DaoException("Cannot close ResultSet.");
                }
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new DaoException("Cannot close PreparedStatement.");
                }
            if (connection != null) {
                connector.returnConnection(connection);
            }
        }
        return tours;
    }

    public ArrayList<Tour> readBurningTours() throws DaoException {
        ArrayList<Tour> tours = new ArrayList<>();
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connector.getConnection();
            preparedStatement = connection.prepareStatement(QUERY_BURNING_TOURS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Tour tour = new Tour();
                tour.setTourId(resultSet.getInt("id"));
                tour.setName(resultSet.getString("name"));
                tour.setCountry(resultSet.getString("country"));
                tour.setStartDate(resultSet.getDate("start_date"));
                tour.setEndDate(resultSet.getDate("end_date"));
                tour.setType(Type.valueOf((resultSet.getObject("type")).toString().toUpperCase()));
                tour.setPrice(resultSet.getInt("price"));
                tour.setBurning(resultSet.getBoolean("burning"));
                tour.setDiscounts(resultSet.getDouble("discounts"));
                tours.add(tour);
            }
        } catch (ConnectorException e) {
            throw new DaoException("Cannot get connection to database.");
        } catch (SQLException e) {
            throw new DaoException("Cannot execute SQL query.");
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new DaoException("Cannot close ResultSet.");
                }
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new DaoException("Cannot close PreparedStatement.");
                }
            if (connection != null) {
                connector.returnConnection(connection);
            }
        }
        return tours;
    }

}
