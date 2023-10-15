package DataPackage;

import Jdbc.JdbcConnectionException;
import Jdbc.JdbcConnector;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public abstract class DaoMySql<T> {
    protected PreparedStatement getStatement;
    protected PreparedStatement getAllStatement;
    protected PreparedStatement saveStatement;
    protected PreparedStatement updateStatement;
    protected PreparedStatement deleteStatement;

    public DaoMySql() throws DAOException {
        try {
            connector = new JdbcConnector();
        } catch (JdbcConnectionException e) {
            throw new DAOException("Can't create DataPackage.Jdbc.JdbcConnector " + e.getMessage());
        }
    }

    public abstract Optional<T> get(long id);

    public abstract List<T> getAll();

    public abstract void save(T t);

    public abstract void update(T t);

    public abstract void delete(T t);

    protected JdbcConnector connector;
}
