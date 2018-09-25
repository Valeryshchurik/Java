package com.shchuryk.dao;

import com.shchuryk.model.Tour;
import com.shchuryk.model.Type;
import com.shchuryk.model.User;
import com.shchuryk.exception.ConnectorException;
import com.shchuryk.exception.DaoException;
import com.shchuryk.model.UserType;
import com.shchuryk.connection.ProxyConnection;

import java.sql.*;
import java.util.ArrayList;

public class DaoUser extends Dao<User>{
    private static final String QUERY_ORDER_OF_CLIENTS = "SELECT tour.id, tour.name, tour.country, tour.start_date, tour.end_date," +
            " tour.type, tour.price, tour.burning, tour.discounts " +
            "FROM voucher INNER JOIN tour " +
            "ON tour.id = voucher.idtour " +
            "INNER JOIN client " +
            "ON client.id = voucher.idclient " +
            "WHERE client.name = ?";
    private static final String QUERY_CLIENT_BY_LOGIN = "SELECT * FROM client WHERE login = ?";
    private static final String INSERT_CLIENT = "INSERT INTO client (name, login, password, email, user_type) VALUES (?,?,?,?,  'client')";
    private static final String UPDATE_CLIENT = "UPDATE client SET name = ? WHERE login = ?";
    private static final String DELETE_CLIENT = "DELETE FROM client WHERE login = ?";
    private static final String QUERY_ALL_USERS ="";

    public DaoUser() throws DaoException {
        super();
    }

    public boolean create(User entity) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connector.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_CLIENT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getLogin());
            preparedStatement.setString(3, entity.getPassword());
            preparedStatement.setString(4, entity.getEmail());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            entity.setId(resultSet.getInt(1));
        } catch (ConnectorException e) {
            throw new DaoException("Cannot get connection to database.", e);
        } catch (SQLException e) {
            throw new DaoException("Cannot execute SQL query.", e);
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new DaoException("Cannot close ResultSet.", e);
                }
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new DaoException("Cannot close PreparedStatement.", e);
                }
            if (connection != null) {
                connector.returnConnection(connection);
            }
        }
        return true;
    }

    public boolean update(User entity) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connector.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_CLIENT);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getLogin());
            preparedStatement.executeUpdate();
        } catch (ConnectorException e) {
            throw new DaoException("Cannot get connection to database.", e);
        } catch (SQLException e) {
            throw new DaoException("Cannot execute SQL query.", e);
        } finally {
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new DaoException("Cannot close PreparedStatement.", e);
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
            preparedStatement = connection.prepareStatement(DELETE_CLIENT);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (ConnectorException e) {
            throw new DaoException("Cannot get connection to database.", e);
        } catch (SQLException e) {
            throw new DaoException("Cannot execute SQL query.", e);
        } finally {
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new DaoException("Cannot close PreparedStatement.", e);
                }
            if (connection != null) {
                connector.returnConnection(connection);
            }
        }
        return true;
    }

    public User read(String login) throws DaoException {
        User user = null;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connector.getConnection();
            preparedStatement = connection.prepareStatement(QUERY_CLIENT_BY_LOGIN);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                user = new User();
                user.setLogin(resultSet.getString("logIn"));
                user.setPassword(resultSet.getString("password"));
                user.setUserType(UserType.Role.valueOf(resultSet.getString("user_type").toUpperCase()).ordinal()+1);
            }
        } catch (ConnectorException e) {
            throw new DaoException("Cannot get connection to database.", e);
        } catch (SQLException e) {
            throw new DaoException("Cannot execute SQL query.", e);
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new DaoException("Cannot close ResultSet.", e);
                }
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new DaoException("Cannot close PreparedStatement.", e);
                }
            if (connection != null) {
                connector.returnConnection(connection);
            }
        }
        return user;
    }

    public ArrayList<Tour> readClientOrders(String name) throws DaoException {
        ArrayList<Tour> tours = new ArrayList<>();
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connector.getConnection();
            preparedStatement = connection.prepareStatement(QUERY_ORDER_OF_CLIENTS);
            preparedStatement.setString(1, name);
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

    public ArrayList<Tour> readAllUsers() throws DaoException {
        ArrayList<Tour> tours = new ArrayList<>();
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connector.getConnection();
            preparedStatement = connection.prepareStatement(QUERY_ALL_USERS);
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
