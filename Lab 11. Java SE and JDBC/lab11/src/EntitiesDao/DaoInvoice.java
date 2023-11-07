package EntitiesDao;

import DataPackage.DAOException;
import DataPackage.DaoMySql;
import Entities.Customer;
import Entities.Invoice;
import Entities.Project;
import Jdbc.JdbcConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DaoInvoice extends DaoMySql<Invoice> {
    private DaoProject daoProject;
    private DaoCustomer daoCustomer;
    private String payQuery = "UPDATE invoices SET IsPaid = ? WHERE id = ?";

    public DaoInvoice() throws DAOException {
        super();
        this.daoProject = new DaoProject();
        this.daoCustomer = new DaoCustomer();
    }

    @Override public String getStatement() {
        return "SELECT * FROM invoices WHERE id = ?";
    }

    @Override public String getAllStatement() {
        return "SELECT * FROM invoices";
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

    public void cancelPayment(Invoice invoice) throws SQLException {
        Connection connection = JdbcConnector.GetConnection();
        PreparedStatement payStatement = connection.prepareStatement(payQuery);
        payStatement.setBoolean(1, false);
        payStatement.setInt(2, invoice.getId());
        payStatement.executeUpdate();
        JdbcConnector.ReleaseConnection(connection);
        invoice.setPaid(false);
    }

    public void pay(Invoice invoice) throws SQLException {
        Connection connection = JdbcConnector.GetConnection();
        PreparedStatement payStatement = connection.prepareStatement(payQuery);
        payStatement.setBoolean(1, true);
        payStatement.setInt(2, invoice.getId());
        payStatement.executeUpdate();
        JdbcConnector.ReleaseConnection(connection);
        invoice.setPaid(true);
    }
}
