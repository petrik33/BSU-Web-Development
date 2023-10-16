package DataPackage;

import Entities.Invoice;
import Jdbc.JdbcConnectionException;
import Jdbc.JdbcConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class DaoMySql<T> {
    protected JdbcConnector connector;
    protected PreparedStatement getStatement;
    protected PreparedStatement getAllStatement;

    public DaoMySql() throws DAOException {
        try {
            connector = new JdbcConnector();
        } catch (JdbcConnectionException e) {
            throw new DAOException("Can't create DataPackage.Jdbc.JdbcConnector " + e.getMessage());
        }
    }

    public abstract Optional<T> convertFullSet(ResultSet resultSet) throws SQLException, DAOException;

    public Optional<T> get(long id) throws DAOException {
        try {
            getStatement.setLong(1, id);
            ResultSet resultSet = getStatement.executeQuery();
            if (resultSet.next()) {
                return convertFullSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException("Can't convert DAO query result to entity: " + e.getMessage());
        }
        return Optional.empty();
    }

    public List<T> getAll() throws DAOException {
        List<T> entries = new ArrayList<>();
        try {
            ResultSet resultSet = getAllStatement.executeQuery();
            while (resultSet.next()) {
                convertFullSet(resultSet).ifPresent(entries::add);
            }
        } catch (SQLException e) {
            throw new DAOException("Can't convert DAO query result to entity: " + e.getMessage());
        }
        return entries;
    }
}
