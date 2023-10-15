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

    public DaoDeveloper() throws DAOException {
        super();
        this.daoTeam = new DaoTeam();
        this.daoProject = new DaoProject();
        try {
            byTeamStatement = connector.getConnection().prepareStatement("SELECT * FROM Developer WHERE teamID = ?");
            byProjectStatement = connector.getConnection().prepareStatement("SELECT  * FROM  developer WHERE projectID = ?");
            getStatement = connector.getConnection().prepareStatement("SELECT * FROM Developer WHERE id = ?");
            getAllStatement = connector.getConnection().prepareStatement("SELECT * FROM Developer");
            saveStatement = connector.getConnection().prepareStatement("INSERT INTO Developer (name, qualification, pay, teamID) VALUES (?, ?, ?, ?)");
            updateStatement = connector.getConnection().prepareStatement("UPDATE Developer SET name = ?, qualification = ?, pay = ?, teamID = ? WHERE id = ?");
            deleteStatement = connector.getConnection().prepareStatement("DELETE FROM Developer WHERE id = ?");
        } catch (SQLException e) {
            throw new DAOException("Error initializing prepared statements: " + e.getMessage());
        }
    }

    public List<Developer> getDevelopersByTeam(DevTeam team) {
        List<Developer> developers = new ArrayList<>();
        try {
            byTeamStatement.setInt(1, team.getId());
            ResultSet resultSet = byTeamStatement.executeQuery();

            while (resultSet.next()) {
                int developerId = resultSet.getInt("id");
                String developerName = resultSet.getString("name");
                String developerQualification = resultSet.getString("qualification");
                int developerPay = resultSet.getInt("pay");

                Developer developer = new Developer(developerName, Qualification.valueOf(developerQualification), developerPay, team);
                developer.setId(developerId);
                developers.add(developer);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }
        return developers;
    }

    public List<Developer> getDevelopersByProject(Project project) {
        List<Developer> developers = new ArrayList<>();
        try {
            byProjectStatement.setInt(1, project == null ? 0 : project.getId());
            ResultSet resultSet = byTeamStatement.executeQuery();

            while (resultSet.next()) {
                int developerId = resultSet.getInt("id");
                String developerName = resultSet.getString("name");
                String developerQualification = resultSet.getString("qualification");
                int developerPay = resultSet.getInt("pay");
                int teamId = resultSet.getInt("teamID");
                Optional<DevTeam> team = daoTeam.get(teamId);
                if (!team.isPresent()) {
                    continue;
                }
                Developer developer = new Developer(developerName, Qualification.valueOf(developerQualification), developerPay, team.get());
                developer.setId(developerId);
                developer.setCurrentProject(project);
                developers.add(developer);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }
        return developers;
    }


    @Override
    public Optional<Developer> get(long id) {
        try {
            getStatement.setLong(1, id);
            ResultSet resultSet = getStatement.executeQuery();
            if (resultSet.next()) {
                int developerId = resultSet.getInt("id");
                String developerName = resultSet.getString("name");
                String qualification = resultSet.getString("qualification");
                int pay = resultSet.getInt("pay");
                int projectId = resultSet.getInt("projectID");
                int teamID = resultSet.getInt("teamID");
                Optional<DevTeam> team = daoTeam.get(teamID);
                if (!team.isPresent()) {
                    return Optional.empty();
                }
                Optional<Project> project = daoProject.get(projectId);
                if (!project.isPresent()) {
                    return Optional.empty();
                }
                Developer developer = new Developer(developerName, Qualification.valueOf(qualification), pay, team.get());
                developer.setCurrentProject(project.get());
                developer.setId(developerId);
                return Optional.of(developer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Developer> getAll() {
        List<Developer> developers = new ArrayList<>();
        try {
            ResultSet resultSet = getAllStatement.executeQuery();
            while (resultSet.next()) {
                int developerId = resultSet.getInt("id");
                String developerName = resultSet.getString("name");
                String qualification = resultSet.getString("qualification");
                int pay = resultSet.getInt("pay");
                int teamID = resultSet.getInt("teamID");
                Optional<DevTeam> team = daoTeam.get(teamID);
                if (!team.isPresent()) {
                    continue;
                }
                Developer developer = new Developer(developerName, Qualification.valueOf(qualification), pay, team.get());
                developer.setId(developerId);
                developers.add(developer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developers;
    }

    @Override
    public void save(Developer developer) {
        try {
            saveStatement.setString(1, developer.getName());
            saveStatement.setString(2, developer.getQualification().toString());
            saveStatement.setInt(3, developer.getPayRate());
            saveStatement.setInt(4, developer.getTeam().getId());
            saveStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Developer developer) {
        try {
            updateStatement.setString(1, developer.getName());
            updateStatement.setString(2, developer.getQualification().toString());
            updateStatement.setInt(3, developer.getPayRate());
            updateStatement.setInt(4, developer.getTeam().getId());
            updateStatement.setInt(5, developer.getId());
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Developer developer) {
        try {
            deleteStatement.setInt(1, developer.getId());
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
