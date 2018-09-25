package com.shchuryk.dao;

import com.shchuryk.exception.ConnectorException;
import com.shchuryk.exception.DaoException;
import com.shchuryk.model.Auction;
import com.shchuryk.model.UserType;
import com.shchuryk.proxy.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoAuction extends Dao<Auction>{
    private static final String QUERY_CLIENT_BY_LOGIN = "SELECT * FROM user WHERE login = ?";
    private static final String INSERT_CLIENT = "INSERT INTO user (name, password) VALUES (?)";
    private static final String UPDATE_CLIENT = "UPDATE user SET name = ? WHERE login = ?";
    private static final String DELETE_CLIENT = "DELETE FROM user WHERE login = ?";

    public DaoAuction() throws DaoException {
        super();
    }

    public boolean create(Auction entity) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connector.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_CLIENT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getLogin());
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

    public boolean update(Auction entity) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connector.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_CLIENT);
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setInt(2, entity.getId());
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

    public Auction read(String login) throws DaoException {
        Auction auction = null;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connector.getConnection();
            preparedStatement = connection.prepareStatement(QUERY_CLIENT_BY_LOGIN);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                auction = new Auction();
                auction.setLogin(resultSet.getString("logIn"));
                auction.setPassword(resultSet.getString("password"));
                auction.setUserType(UserType.Role.valueOf(resultSet.getString("user_type").toUpperCase()).ordinal()+1);
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
        return auction;
    }
}
