package EntitiesDao;

import DataPackage.DAOException;
import DataPackage.DaoMySql;
import Entities.Customer;

import java.sql.PreparedStatement;
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
            saveStatement = connector.getConnection().prepareStatement("INSERT INTO Customer (name) VALUES (?)");
            updateStatement = connector.getConnection().prepareStatement("UPDATE Customer SET name = ? WHERE id = ?");
            deleteStatement = connector.getConnection().prepareStatement("DELETE FROM Customer WHERE id = ?");
        } catch (SQLException e) {
            throw new DAOException("Error initializing prepared statements: " + e.getMessage());
        }
    }

    @Override
    public Optional<Customer> get(long id) {
        try {
            getStatement.setLong(1, id);
            ResultSet resultSet = getStatement.executeQuery();
            if (resultSet.next()) {
                int customerId = resultSet.getInt("id");
                String customerName = resultSet.getString("name");
                Customer customer = new Customer(customerName);
                customer.setId(customerId);
                return Optional.of(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }
        return Optional.empty();
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        try {
            ResultSet resultSet = getAllStatement.executeQuery();
            while (resultSet.next()) {
                int customerId = resultSet.getInt("id");
                String customerName = resultSet.getString("name");
                Customer customer = new Customer(customerName);
                customer.setId(customerId);
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }
        return customers;
    }

    @Override
    public void save(Customer customer) {
        try {
            saveStatement.setString(1, customer.getName());
            saveStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }
    }

    @Override
    public void update(Customer customer) {
        try {
            updateStatement.setString(1, customer.getName());
            updateStatement.setInt(2, customer.getId());
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }
    }

    @Override
    public void delete(Customer customer) {
        try {
            deleteStatement.setInt(1, customer.getId());
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }
    }
}
