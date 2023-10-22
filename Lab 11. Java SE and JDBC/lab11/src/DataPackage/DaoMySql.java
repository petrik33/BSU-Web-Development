package DataPackage;

import Entities.Invoice;
import Jdbc.JdbcConnectionException;
import Jdbc.JdbcConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class DaoMySql<T> {

    public DaoMySql() {
    }

    protected abstract String getStatement();

    protected abstract String getAllStatement();

    public abstract Optional<T> convertFullSet(ResultSet resultSet) throws SQLException, DAOException;

    public Optional<T> get(long id) throws DAOException {
        try {
            Connection connection = JdbcConnector.GetConnection();
            PreparedStatement statement = connection.prepareStatement(getStatement());
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            JdbcConnector.ReleaseConnection(connection);
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
            Connection connection = JdbcConnector.GetConnection();
            PreparedStatement statement = connection.prepareStatement(getAllStatement());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                convertFullSet(resultSet).ifPresent(entries::add);
            }
            JdbcConnector.ReleaseConnection(connection);
        } catch (SQLException e) {
            throw new DAOException("Can't convert DAO query result to entity: " + e.getMessage());
        }
        return entries;
    }
}
