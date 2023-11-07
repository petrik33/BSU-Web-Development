package EntitiesDao;

import DataPackage.DAOException;
import DataPackage.DaoMySql;
import Entities.DevTeam;
import Entities.Developer;
import Entities.Project;
import Entities.Qualification;
import Jdbc.JdbcConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoDeveloper extends DaoMySql<Developer> {
    private DaoTeam daoTeam;
    private DaoProject daoProject;
    private String byTeamQuery = "SELECT * FROM Developer WHERE teamID = ?";
    private String byProjectQuery = "SELECT  * FROM  developer WHERE projectID = ?";
    private String assignProjectQuery = "UPDATE Developer SET projectID = ? WHERE id = ?";

    public DaoDeveloper() throws DAOException {
        super();
        this.daoTeam = new DaoTeam();
        this.daoProject = new DaoProject();
    }

    @Override public String getStatement() {
        return "SELECT * FROM Developer WHERE id = ?";
    }

    @Override public String getAllStatement() {
        return "SELECT * FROM Developer";
    }

    @Override
    public Optional<Developer> convertFullSet(ResultSet resultSet) throws SQLException, DAOException {
        int developerId = resultSet.getInt("id");
        String developerName = resultSet.getString("name");
        String qualification = resultSet.getString("qualification");
        int pay = resultSet.getInt("pay");
        int projectId = resultSet.getInt("projectID");
        int teamID = resultSet.getInt("teamID");

        Optional<DevTeam> team = daoTeam.get(teamID);
        Optional<Project> project = daoProject.get(projectId);

        Developer developer = new Developer(developerName, Qualification.valueOf(qualification), pay, team.orElse(null));
        developer.setCurrentProject(project.orElse(null));
        developer.setId(developerId);

        return Optional.of(developer);
    }

    public List<Developer> getDevelopersByTeam(DevTeam team) throws SQLException, DAOException {
        List<Developer> developers = new ArrayList<>();
        Connection connection = JdbcConnector.GetConnection();
        PreparedStatement byTeamStatement = connection.prepareStatement(byTeamQuery);
        byTeamStatement.setInt(1, team.getId());
        ResultSet resultSet = byTeamStatement.executeQuery();
        JdbcConnector.ReleaseConnection(connection);
        while (resultSet.next()) {
            convertFullSet(resultSet).ifPresent(developers::add);
        }
        return developers;
    }

    public List<Developer> getDevelopersByProject(Project project) throws SQLException, DAOException {
        List<Developer> developers = new ArrayList<>();
        Connection connection = JdbcConnector.GetConnection();
        PreparedStatement byProjectStatement = connection.prepareStatement(byProjectQuery);
        byProjectStatement.setInt(1, project.getId());
        ResultSet resultSet = byProjectStatement.executeQuery();
        JdbcConnector.ReleaseConnection(connection);
        while (resultSet.next()) {
            convertFullSet(resultSet).ifPresent(developers::add);
        }
        return developers;
    }

    public void assignProjectToDeveloper(Developer developer, Project project) throws SQLException {
        Connection connection = JdbcConnector.GetConnection();
        PreparedStatement assignProjectStatement = connection.prepareStatement(assignProjectQuery);
        assignProjectStatement.setInt(1, project.getId());
        assignProjectStatement.setInt(2, developer.getId());
        assignProjectStatement.executeUpdate();
        JdbcConnector.ReleaseConnection(connection);
        developer.setCurrentProject(project);
    }
}
