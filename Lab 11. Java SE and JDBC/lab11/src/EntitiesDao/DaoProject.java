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
            saveStatement = connector.getConnection().prepareStatement("INSERT INTO Project (name, teamID, specificationID) VALUES (?, ?, ?)");
            updateStatement = connector.getConnection().prepareStatement("UPDATE Project SET name = ?, teamID = ?, specificationID = ? WHERE id = ?");
            deleteStatement = connector.getConnection().prepareStatement("DELETE FROM Project WHERE id = ?");
            projectsByCustomerStatement = connector.getConnection().prepareStatement("SELECT p.* FROM Project p " +
                    "JOIN Invoices i ON p.id = i.projectID " +
                            "WHERE i.customerID = ?");
        } catch (SQLException e) {
            throw new DAOException("Error initializing prepared statements: " + e.getMessage());
        }
    }

    public List<Project> getProjectsForCustomer(Customer customer) {
        List<Project> projects = new ArrayList<>();
        try {
            projectsByCustomerStatement.setInt(1, customer.getId());
            ResultSet resultSet = projectsByCustomerStatement.executeQuery();

            while (resultSet.next()) {
                int projectId = resultSet.getInt("id");
                String projectName = resultSet.getString("name");
                int teamID = resultSet.getInt("teamID");
                int specificationID = resultSet.getInt("specificationID");

                Optional<Specification> specification = daoSpecification.get(specificationID);
                Optional<DevTeam> team = daoTeam.get(teamID);

                if (specification.isPresent() && team.isPresent()) {
                    Project project = new Project(projectName, specification.get());
                    project.setTeam(team.get());
                    project.setId(projectId);
                    projects.add(project);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }
        return projects;
    }


    protected Optional<Project> convert(ResultSet resultSet) throws SQLException {
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

    @Override
    public Optional<Project> get(long id) {
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
    public List<Project> getAll() {
        List<Project> projects = new ArrayList<>();
        try {
            ResultSet resultSet = getAllStatement.executeQuery();
            while (resultSet.next()) {
                int projectId = resultSet.getInt("id");
                String projectName = resultSet.getString("name");
                int teamID = resultSet.getInt("teamID");
                int specificationID = resultSet.getInt("specificationID");
                Optional<Specification> specification = daoSpecification.get(specificationID);
                Optional<DevTeam> team = daoTeam.get(teamID);
                if (!specification.isPresent() || !team.isPresent()) {
                   continue;
                }
                Project project = new Project(projectName, specification.get());
                project.setTeam(team.get());
                project.setId(projectId);
                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    @Override
    public void save(Project project) {
        try {
            saveStatement.setString(1, project.getName());
            saveStatement.setInt(2, project.getTeam().getId());
            saveStatement.setInt(3, project.getSpecification().getId());
            saveStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Project project) {
        try {
            updateStatement.setString(1, project.getName());
            updateStatement.setInt(2, project.getTeam().getId());
            updateStatement.setInt(3, project.getSpecification().getId());
            updateStatement.setInt(4, project.getId());
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Project project) {
        try {
            deleteStatement.setInt(1, project.getId());
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
