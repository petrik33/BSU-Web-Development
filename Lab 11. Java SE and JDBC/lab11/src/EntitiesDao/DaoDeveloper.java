package EntitiesDao;

import DataPackage.DAOException;
import DataPackage.DaoMySql;
import Entities.DevTeam;
import Entities.Developer;
import Entities.Project;
import Entities.Qualification;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoDeveloper extends DaoMySql<Developer> {
    private DaoTeam daoTeam;
    private DaoProject daoProject;
    private PreparedStatement byTeamStatement;
    private PreparedStatement byProjectStatement;
    private PreparedStatement assignProjectStatement;

    public DaoDeveloper() throws DAOException {
        super();
        this.daoTeam = new DaoTeam();
        this.daoProject = new DaoProject();
        try {
            byTeamStatement = connector.getConnection().prepareStatement("SELECT * FROM Developer WHERE teamID = ?");
            byProjectStatement = connector.getConnection().prepareStatement("SELECT  * FROM  developer WHERE projectID = ?");
            assignProjectStatement = connector.getConnection().prepareStatement("UPDATE Developer SET projectID = ? WHERE id = ?");
            getStatement = connector.getConnection().prepareStatement("SELECT * FROM Developer WHERE id = ?");
            getAllStatement = connector.getConnection().prepareStatement("SELECT * FROM Developer");
        } catch (SQLException e) {
            throw new DAOException("Error initializing prepared statements: " + e.getMessage());
        }
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

    public List<Developer> getDevelopersByTeam(DevTeam team) {
        List<Developer> developers = new ArrayList<>();
        try {
            byTeamStatement.setInt(1, team.getId());
            ResultSet resultSet = byTeamStatement.executeQuery();
            while (resultSet.next()) {
                convertFullSet(resultSet).ifPresent(developers::add);
            }
        } catch (SQLException | DAOException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }
        return developers;
    }

    public List<Developer> getDevelopersByProject(Project project) {
        List<Developer> developers = new ArrayList<>();
        try {
            byProjectStatement.setInt(1, project.getId());
            ResultSet resultSet = byProjectStatement.executeQuery();
            while (resultSet.next()) {
                convertFullSet(resultSet).ifPresent(developers::add);
            }
        } catch (SQLException | DAOException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }
        return developers;
    }

    public void assignProjectToDeveloper(Developer developer, Project project) {
        try {
            assignProjectStatement.setInt(1, project.getId());
            assignProjectStatement.setInt(2, developer.getId());
            assignProjectStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
