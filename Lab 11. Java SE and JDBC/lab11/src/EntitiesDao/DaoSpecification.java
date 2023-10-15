package EntitiesDao;

import DataPackage.DAOException;
import DataPackage.DaoMySql;
import Entities.Customer;
import Entities.Specification;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoSpecification extends DaoMySql<Specification> {
    private DaoCustomer daoCustomer;
    public DaoSpecification() throws DAOException {
        super();
        this.daoCustomer = new DaoCustomer();
        try {
            getStatement = connector.getConnection().prepareStatement("SELECT * FROM Specification WHERE id = ?");
            getAllStatement = connector.getConnection().prepareStatement("SELECT * FROM Specification");
            saveStatement = connector.getConnection().prepareStatement("INSERT INTO Specification (customerID) VALUES (?)");
            updateStatement = connector.getConnection().prepareStatement("UPDATE Specification SET customerID = ? WHERE id = ?");
            deleteStatement = connector.getConnection().prepareStatement("DELETE FROM Specification WHERE id = ?");
        } catch (SQLException e) {
            throw new DAOException("Error initializing prepared statements: " + e.getMessage());
        }
    }

    @Override
    public Optional<Specification> get(long id) {
        try {
            getStatement.setLong(1, id);
            ResultSet resultSet = getStatement.executeQuery();
            if (resultSet.next()) {
                int specificationId = resultSet.getInt("id");
                int customerId = resultSet.getInt("customerID");
                Optional<Customer> customer = daoCustomer.get(customerId);
                if (!customer.isPresent()) {
                    return Optional.empty();
                }
                Specification specification = new Specification(customer.get());
                specification.setId(specificationId);
                return Optional.of(specification);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }
        return Optional.empty();
    }

    @Override
    public List<Specification> getAll() {
        List<Specification> specifications = new ArrayList<>();
        return specifications;
    }

    @Override
    public void save(Specification specification) {
        try {
            saveStatement.setInt(1, specification.getCustomer().getId());
            saveStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }
    }

    @Override
    public void update(Specification specification) {
        try {
            updateStatement.setInt(1, specification.getCustomer().getId());
            updateStatement.setInt(2, specification.getId());
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }
    }

    @Override
    public void delete(Specification specification) {
        try {
            deleteStatement.setInt(1, specification.getId());
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }
    }
}
