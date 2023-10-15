package EntitiesDao;

import DataPackage.DAOException;
import DataPackage.DaoMySql;
import Entities.DevTeam;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoTeam extends DaoMySql<DevTeam> {
    public DaoTeam() throws DAOException {
        super();
        try {
            getStatement = connector.getConnection().prepareStatement("SELECT * FROM Team WHERE id = ?");
            getAllStatement = connector.getConnection().prepareStatement("SELECT * FROM Team");
            saveStatement = connector.getConnection().prepareStatement("INSERT INTO Team (name) VALUES (?)");
            updateStatement = connector.getConnection().prepareStatement("UPDATE Team SET name = ? WHERE id = ?");
            deleteStatement = connector.getConnection().prepareStatement("DELETE FROM Team WHERE id = ?");
        } catch (SQLException e) {
            throw new DAOException("Error initializing prepared statements: " + e.getMessage());
        }
    }

    @Override
    public Optional<DevTeam> get(long id) {
        try {
            getStatement.setLong(1, id);
            ResultSet resultSet = getStatement.executeQuery();
            if (resultSet.next()) {
                int teamId = resultSet.getInt("id");
                String teamName = resultSet.getString("name");
                DevTeam team = new DevTeam(teamName);
                team.setId(teamId);
                return Optional.of(team);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }
        return Optional.empty();
    }

    @Override
    public List<DevTeam> getAll() {
        List<DevTeam> teams = new ArrayList<>();
        try {
            ResultSet resultSet = getAllStatement.executeQuery();
            while (resultSet.next()) {
                int teamId = resultSet.getInt("id");
                String teamName = resultSet.getString("name");
                DevTeam team = new DevTeam(teamName);
                team.setId(teamId);
                teams.add(team);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }
        return teams;
    }

    @Override
    public void save(DevTeam team) {
        try {
            saveStatement.setString(1, team.getName());
            saveStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }
    }

    @Override
    public void update(DevTeam team) {
        try {
            updateStatement.setString(1, team.getName());
            updateStatement.setInt(2, team.getId());
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }
    }

    @Override
    public void delete(DevTeam team) {
        try {
            deleteStatement.setInt(1, team.getId());
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }
    }
}
