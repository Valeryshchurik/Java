package com.shchuryk.dao;

import com.shchuryk.exception.ConnectorException;
import com.shchuryk.exception.DaoException;
import com.shchuryk.model.FlowerLot;
import com.shchuryk.connection.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoFlowerLot extends Dao<FlowerLot> {
    private static final String QUERY_CLIENT_BY_LOGIN = "SELECT * FROM flower_lot WHERE login = ?";
    private static final String INSERT_FLOWER_LOT = "INSERT INTO flower_lot " +
            "(`ID`,`FLOWER_PROVIDER_ID`,`FLOWER_NAME`,`NUMBER_OF_FLOWERS`,`START_PRICE`,`ADDITIONAL_INFO`,`REGISTRATION_ID`) VALUES (?,?,?,?,?,?)";
    private static final String REGISTER_LOT = "UPDATE flowerLot SET REGISTRATION_ID = ? WHERE ID = ?";
    private static final String DELETE_CLIENT = "DELETE FROM flowerLot WHERE login = ?";

    public DaoFlowerLot() throws DaoException {
        super();
    }

    public boolean create(FlowerLot entity) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connector.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_FLOWER_LOT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, entity.getFlowerProviderId());
            preparedStatement.setString(2, entity.getFlowerName());
            preparedStatement.setInt(3, entity.getNumberOfFlowers());
            preparedStatement.setInt(4, entity.getStartPrice());
            preparedStatement.setString(5, entity.getAdditionalInfo());
            preparedStatement.setInt(6, entity.getRegistrationId());
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

    public boolean update(FlowerLot entity) throws DaoException {
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

    public FlowerLot read(String login) throws DaoException {
        FlowerLot flowerLot = null;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connector.getConnection();
            preparedStatement = connection.prepareStatement(QUERY_CLIENT_BY_LOGIN);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                flowerLot = new FlowerLot();
                flowerLot.setFlowerName(resultSet.getString("FLOWER_NAME"));
                flowerLot.setFlowerProviderId(resultSet.getInt("FLOWER_PROVIDER_ID"));
                flowerLot.setAdditionalInfo(resultSet.getString("ADDITIONAL_INFO"));
                flowerLot.setNumberOfFlowers(resultSet.getInt("NUMBER_OF_FLOWERS"));
                flowerLot.setStartPrice(resultSet.getInt("START_PRICE"));
                flowerLot.setRegistrationId(resultSet.getInt("REGISTRATION_ID"));
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
        return flowerLot;
    }
}
