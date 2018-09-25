package com.shchuryk.dao;

import com.shchuryk.model.*;
import com.shchuryk.exception.ConnectorException;
import com.shchuryk.exception.DaoException;
import com.shchuryk.connection.ProxyConnection;

import java.sql.*;

public class DaoOrder extends Dao<Order> {
    private static final String QUERY_ALL_ORDERS_BY_ID = "SELECT * FROM voucher WHERE idclient = ? and idtour = ?";

    private static final String INSERT_ORDER = "INSERT INTO voucher (idclient, idtour) " +
            "VALUES (?, ?)";
    private static final String UPDATE_ORDER = "UPDATE voucher SET idtour=? " +
            "WHERE idclient = ?";
    private static final String DELETE_ORDER = "DELETE FROM voucher WHERE idclient = ?";

    public DaoOrder() throws DaoException  {
        super();
    }

    public boolean create(Order entity) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connector.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, entity.getClientId());
            preparedStatement.setInt(2, entity.getTourId());
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

    public boolean update(Order entity) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connector.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_ORDER);
            preparedStatement.setInt(1, entity.getTourId());
            preparedStatement.setInt(2, 1);
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

    public boolean update(int id) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connector.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_ORDER);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, 1);
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
            preparedStatement = connection.prepareStatement(DELETE_ORDER);
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

    public Order read(int id) throws DaoException {
        Order order = null;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connector.getConnection();
            preparedStatement = connection.prepareStatement(QUERY_ALL_ORDERS_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                order = new Order();
                order.setClientId(resultSet.getInt("idclient"));
                order.setTourId(resultSet.getInt("idtour"));
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
        return order;
    }
}
