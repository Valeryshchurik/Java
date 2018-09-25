package com.shchuryk.connection;

import com.shchuryk.exception.ConnectorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static int POOL_SIZE;
    private static ConnectionPool instance;
    private String driver;
    private String url;
    private String user;
    private String password;
    private Queue<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> engagedConnections;
    private ReentrantLock poolLock;
    private static final ResourceBundle bundle = ResourceBundle.getBundle("database");

    public static int getPoolSize() {
        return POOL_SIZE;
    }

    protected ConnectionPool() {
        freeConnections = new ConcurrentLinkedQueue<>();
        engagedConnections = new ConcurrentLinkedQueue<>();
        poolLock = new ReentrantLock();
        try {
            this.driver = bundle.getString("driver");
            this.url = bundle.getString("url");
            this.user = bundle.getString("user");
            this.password = bundle.getString("password");
            POOL_SIZE = (Integer.parseInt(bundle.getString("initialConnectionCount")));
            Class.forName(driver).newInstance();
        }
        catch (MissingResourceException e) {
           LOGGER.fatal("Can't load properties for database!");
           throw new RuntimeException("Can't load properties for database!");
        }
        catch (InstantiationException e) {
            throw new RuntimeException("Cannot create instance for driver class.", e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Illegal access to driver class.", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver class not found.", e);
        }
        for (int i = 0; i < POOL_SIZE; i++)
            freeConnections.add(createConnection());
    }

    /**
     * Gets the singleton instance of ConnectionPool.
     *
     * @return The singleton instance of ConnectionPool.
     * @throws ConnectorException If failed to create ConnectionPool.
     */
    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    private ProxyConnection createConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Cannot create database connection.", e);
        }
        return new ProxyConnection(connection);
    }

    /**
     * Gets connection from the pool.
     *
     * @return Database connection.
     * @throws ConnectorException If unable to retrieve connection from the pool or create a new connection.
     */
    public ProxyConnection getConnection() throws ConnectorException {
        ProxyConnection connection;
        try {
            poolLock.lock();
            connection = freeConnections.poll();
            if (connection==null)
                throw new ConnectorException("There are no connections in the pool!");
        } finally {
            poolLock.unlock();
        }
        engagedConnections.add(connection);
        return connection;
    }

    /**
     * Returns connection to the pool.
     *
     * @param conn Connection to return.
     * @return <b>true</b> if connection is returned successfully.
     */
    public synchronized boolean returnConnection(ProxyConnection conn) {
        try {
            poolLock.lock();
            if (conn == null || !engagedConnections.contains(conn))
                return false;
            engagedConnections.remove(conn);
            freeConnections.add(conn);
            return true;
        } finally {
            poolLock.unlock();
        }
    }

    /**
     * Closes connection pool.
     */
    public static void closePool() {
        if (instance == null) return;
        try {
            instance.poolLock.lock();
            for (ProxyConnection conn : instance.freeConnections) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
            for (ProxyConnection conn : instance.engagedConnections) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        } finally {
            instance.poolLock.unlock();
        }
        instance = null;
    }
}
