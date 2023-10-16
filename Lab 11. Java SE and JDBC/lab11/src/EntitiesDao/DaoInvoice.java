package EntitiesDao;

import DataPackage.DAOException;
import DataPackage.DaoMySql;
import Entities.Customer;
import Entities.Invoice;
import Entities.Project;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoInvoice extends DaoMySql<Invoice> {
    private DaoProject daoProject;
    private DaoCustomer daoCustomer;
    private PreparedStatement payStatement;

    public DaoInvoice() throws DAOException {
        super();
        this.daoProject = new DaoProject();
        this.daoCustomer = new DaoCustomer();
        try {
            getStatement = connector.getConnection().prepareStatement("SELECT * FROM invoices WHERE id = ?");
            getAllStatement = connector.getConnection().prepareStatement("SELECT * FROM invoices");
            payStatement = connector.getConnection().prepareStatement("UPDATE invoices SET IsPaid = ? WHERE id = ?");
        } catch (SQLException e) {
            throw new DAOException("Error initializing prepared statements: " + e.getMessage());
        }
    }

    @Override
    public Optional<Invoice> convertFullSet(ResultSet resultSet) throws SQLException, DAOException {
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

    public void pay(Invoice invoice) {
        try {
            payStatement.setBoolean(1, true);
            payStatement.setInt(2, invoice.getId());
            payStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
