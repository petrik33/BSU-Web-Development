package Jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static Logger.LogManager.logException;

public class JdbcConnector {
    private static BlockingQueue<Connection> connectionPool;
    private static int INITIAL_POOL_SIZE = 10;

    static {
        connectionPool = new ArrayBlockingQueue<>(INITIAL_POOL_SIZE);
        try {
            initializePool();
        } catch (JdbcConnectionException e) {
            throw new RuntimeException(e);
        }
    }

    private static void initializePool() throws JdbcConnectionException {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        String driver = resource.getString("driver");
        String user = resource.getString("user");
        String url = resource.getString("url");
        String password = resource.getString("password");

        try {
            Class.forName(driver).newInstance();
        } catch (ClassNotFoundException e) {
            logException(e);
            throw new JdbcConnectionException ("Драйвер не загружен!");
        } catch (InstantiationException | IllegalAccessException e) {
            logException(e);
            throw new JdbcConnectionException(e.getMessage());
        }

        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            try {
                Connection connection = DriverManager.getConnection(url, user, password);
                if (connectionPool.add(connection)) {
                    continue;
                }
                JdbcConnectionException e = new JdbcConnectionException("Can't add connection to pool");
                logException(e);
                throw e;
            } catch (SQLException e) {
                logException(e);
                throw new JdbcConnectionException("Error creating database connections.");
            }
        }
    }

    public static Connection GetConnection() throws JdbcConnectionException {
        try {
            return connectionPool.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logException(e);
            throw new JdbcConnectionException("Interrupted while waiting for a connection.");
        }
    }

    public static boolean ReleaseConnection(Connection connection) {
        return connectionPool.add(connection);
    }
}
