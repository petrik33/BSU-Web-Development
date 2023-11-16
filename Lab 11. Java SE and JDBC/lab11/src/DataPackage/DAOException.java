package DataPackage;

import Jdbc.JdbcConnectionException;

public class DAOException extends Exception {
    public DAOException(String s) {
        super(s);
    }
}
