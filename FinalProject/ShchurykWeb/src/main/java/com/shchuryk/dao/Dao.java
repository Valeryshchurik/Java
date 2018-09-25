package com.shchuryk.dao;

import com.shchuryk.connection.ConnectionPool;
import com.shchuryk.exception.DaoException;

public abstract class Dao<T> {
    protected ConnectionPool connector;

    public Dao() throws DaoException {
            connector = ConnectionPool.getInstance();
    }

    public ConnectionPool getJdbcConnector() {
        return connector;
    }
    public abstract boolean create(T entity) throws DaoException;
    public abstract boolean update(T entity) throws DaoException;
    public abstract boolean delete(int id) throws DaoException;
}
