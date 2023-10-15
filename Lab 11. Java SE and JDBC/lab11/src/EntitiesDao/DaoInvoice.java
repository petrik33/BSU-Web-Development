package EntitiesDao;

import DataPackage.DAOException;
import DataPackage.DaoMySql;
import Entities.Customer;
import Entities.Invoice;
import Entities.Project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoInvoice extends DaoMySql<Invoice> {
    private DaoProject daoProject;
    private DaoCustomer daoCustomer;

    public DaoInvoice() throws DAOException {
        super();
        this.daoProject = new DaoProject();
        this.daoCustomer = new DaoCustomer();
        try {
            getStatement = connector.getConnection().prepareStatement("SELECT * FROM invoices WHERE id = ?");
            getAllStatement = connector.getConnection().prepareStatement("SELECT * FROM invoices");
            saveStatement = connector.getConnection().prepareStatement("INSERT INTO invoices (amount, projectID, customerID, IsPaid) VALUES (?, ?, ?, ?)");
            updateStatement = connector.getConnection().prepareStatement("UPDATE invoices SET amount = ?, projectID = ?, customerID = ?, IsPaid = ? WHERE id = ?");
            deleteStatement = connector.getConnection().prepareStatement("DELETE FROM invoices WHERE id = ?");
        } catch (SQLException e) {
            throw new DAOException("Error initializing prepared statements: " + e.getMessage());
        }
    }

    public void Pay(Invoice invoice) {
        try {
            updateStatement.setInt(1, invoice.getAmount());
            updateStatement.setInt(2, invoice.getProject().getId());
            updateStatement.setInt(3, invoice.getCustomer().getId());
            updateStatement.setBoolean(4, true);
            updateStatement.setInt(5, invoice.getId());
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected Optional<Invoice> convert(ResultSet resultSet) throws SQLException {
        int invoiceId = resultSet.getInt("id");
        int amount = resultSet.getInt("amount");
        int projectId = resultSet.getInt("projectID");
        int customerID = resultSet.getInt("customerID");
        boolean isPaid = resultSet.getBoolean("IsPaid");

        // Assuming you have a method to retrieve a Project based on its ID
        Optional<Project> project = daoProject.get(projectId);
        Optional<Customer> customer = daoCustomer.get(customerID);

        if (!project.isPresent() || !customer.isPresent()) {
            return Optional.empty();
        }

        Invoice invoice = new Invoice(project.get(), customer.get(), amount);
        invoice.setId(invoiceId);
        invoice.setPaid(isPaid);

        return Optional.of(invoice);
    }

    @Override
    public Optional<Invoice> get(long id) {
        try {
            getStatement.setLong(1, id);
            ResultSet resultSet = getStatement.executeQuery();
            if (resultSet.next()) {
                return convert(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Invoice> getAll() {
        List<Invoice> invoices = new ArrayList<>();
        try {
            ResultSet resultSet = getAllStatement.executeQuery();
            while (resultSet.next()) {
                invoices.add(convert(resultSet).orElse(null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }

    @Override
    public void save(Invoice invoice) {
        try {
            saveStatement.setInt(1, invoice.getAmount());
            saveStatement.setInt(2, invoice.getProject().getId());
            saveStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Invoice invoice) {
        try {
            updateStatement.setInt(1, invoice.getAmount());
            updateStatement.setInt(2, invoice.getProject().getId());
            updateStatement.setInt(3, invoice.getId());
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Invoice invoice) {
        try {
            deleteStatement.setInt(1, invoice.getId());
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
