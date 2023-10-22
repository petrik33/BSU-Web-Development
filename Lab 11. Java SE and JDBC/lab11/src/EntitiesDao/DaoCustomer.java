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

    @Override public String getStatement() {
        return "SELECT * FROM Customer WHERE id = ?";
    }

    @Override public String getAllStatement() {
        return "SELECT * FROM Customer";
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
