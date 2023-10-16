package EntitiesDao;

import DataPackage.DAOException;
import DataPackage.DaoMySql;
import Entities.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoProject extends DaoMySql<Project> {
    private DaoTeam daoTeam;
    private DaoSpecification daoSpecification;
    private PreparedStatement projectsByCustomerStatement;

    public DaoProject() throws DAOException {
        super();
        this.daoSpecification = new DaoSpecification();
        this.daoTeam = new DaoTeam();
        try {
            getStatement = connector.getConnection().prepareStatement("SELECT * FROM Project WHERE id = ?");
            getAllStatement = connector.getConnection().prepareStatement("SELECT * FROM Project");
            projectsByCustomerStatement = connector.getConnection().prepareStatement("SELECT p.* FROM Project p " +
                    "JOIN Invoices i ON p.id = i.projectID " +
                            "WHERE i.customerID = ?");
        } catch (SQLException e) {
            throw new DAOException("Error initializing prepared statements: " + e.getMessage());
        }
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

    public List<Project> getProjectsForCustomer(Customer customer) {
        List<Project> projects = new ArrayList<>();
        try {
            projectsByCustomerStatement.setInt(1, customer.getId());
            ResultSet resultSet = projectsByCustomerStatement.executeQuery();
            while (resultSet.next()) {
                convertFullSet(resultSet).ifPresent(projects::add);
            }
        } catch (SQLException | DAOException e) {
            e.printStackTrace();
        }
        return projects;
    }
}
