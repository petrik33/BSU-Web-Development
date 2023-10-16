package EntitiesDao;

import DataPackage.DAOException;
import DataPackage.DaoMySql;
import Entities.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoCustomer extends DaoMySql<Customer> {

    public DaoCustomer() throws DAOException {
        super();
        try {
            getStatement = connector.getConnection().prepareStatement("SELECT * FROM Customer WHERE id = ?");
            getAllStatement = connector.getConnection().prepareStatement("SELECT * FROM Customer");
        } catch (SQLException e) {
            throw new DAOException("Error initializing prepared statements: " + e.getMessage());
        }
    }

    @Override
    public Optional<Customer> convertFullSet(ResultSet resultSet) throws SQLException {
        int customerId = resultSet.getInt("id");
        String customerName = resultSet.getString("name");
        Customer customer = new Customer(customerName);
        customer.setId(customerId);
        return Optional.of(customer);
    }
}
