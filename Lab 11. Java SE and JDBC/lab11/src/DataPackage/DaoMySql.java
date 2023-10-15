package DataPackage;

import Jdbc.JdbcConnectionException;
import Jdbc.JdbcConnector;

public abstract class DaoMySql<T> implements Dao<T> {
    public DaoMySql() throws DAOException {
        try {
            connector = new JdbcConnector();
        } catch (JdbcConnectionException e) {
            throw new DAOException("Can't create DataPackage.Jdbc.JdbcConnector ", e);
        }
    }

    protected JdbcConnector connector;
}
