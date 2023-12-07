package Jdbc;

import java.sql.SQLException;

public class JdbcConnectionException extends SQLException {

    public JdbcConnectionException(String s) {
        super(s);
    }
}
