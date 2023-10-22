package EntitiesDao;

import DataPackage.DAOException;
import DataPackage.DaoMySql;
import Entities.*;
import Jdbc.JdbcConnectionException;
import Jdbc.JdbcConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoProject extends DaoMySql<Project> {
    private DaoTeam daoTeam;
    private DaoSpecification daoSpecification;
    private String byCustomerStatement = "SELECT p.* FROM Project p " +
            "JOIN Invoices i ON p.id = i.projectID " +
            "WHERE i.customerID = ?";

    public DaoProject() throws DAOException {
        super();
        this.daoSpecification = new DaoSpecification();
        this.daoTeam = new DaoTeam();
    }

    @Override public String getStatement() {
        return "SELECT * FROM Project WHERE id = ?";
    }

    @Override public String getAllStatement() {
        return "SELECT * FROM Project";
    }

    @Override
    public Optional<Project> convertFullSet(ResultSet resultSet) throws SQLException, DAOException {
        int projectId = resultSet.getInt("id");
        String projectName = resultSet.getString("name");
        int teamID = resultSet.getInt("teamID");
        int specificationID = resultSet.getInt("specificationID");
        Optional<Specification> specification = daoSpecification.get(specificationID);
        Optional<DevTeam> team = daoTeam.get(teamID);
        if (!specification.isPresent() || !team.isPresent()) {
            return Optional.empty();
        }
        Project project = new Project(projectName, specification.get());
        project.setTeam(team.get());
        project.setId(projectId);
        return Optional.of(project);
    }

    public List<Project> getProjectsForCustomer(Customer customer) throws SQLException, DAOException {
        List<Project> projects = new ArrayList<>();
        Connection connection = JdbcConnector.GetConnection();
        PreparedStatement byCustomer = connection.prepareStatement(byCustomerStatement);
        byCustomer.setInt(1, customer.getId());
        ResultSet resultSet = byCustomer.executeQuery();
        JdbcConnector.ReleaseConnection(connection);
        while (resultSet.next()) {
            convertFullSet(resultSet).ifPresent(projects::add);
        }
        return projects;
    }
}
