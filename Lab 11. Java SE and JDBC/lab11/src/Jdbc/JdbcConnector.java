package Jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class JdbcConnector {

    public JdbcConnector() throws JdbcConnectionException {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        String url = resource.getString("url");
        String driver = resource.getString("driver");
        String user = resource.getString("user");
        String pass = resource.getString("password");
        try {
            Class.forName(driver).newInstance();
        } catch (ClassNotFoundException e) {
            throw new JdbcConnectionException ("Драйвер не загружен!");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            this.connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() throws JdbcConnectionException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new JdbcConnectionException("Can't close connection: " + e.getMessage());
            }
        }
    }

    private Connection connection;
}
