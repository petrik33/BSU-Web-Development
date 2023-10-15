package EntitiesDao;

import DataPackage.DAOException;
import DataPackage.DaoMySql;
import Entities.DevTeam;
import Entities.Manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class DaoManager extends DaoMySql<Manager> {

    protected DaoTeam daoTeam;

    public DaoManager() throws DAOException {
        super();
        this.daoTeam = new DaoTeam();
        try {
            getStatement = connector.getConnection().prepareStatement("SELECT * FROM Manager WHERE id = ?");
            getAllStatement = connector.getConnection().prepareStatement("SELECT * FROM Manager");
            saveStatement = connector.getConnection().prepareStatement("INSERT INTO Manager (name, pay, teamID) VALUES (?, ?, ?)");
            updateStatement = connector.getConnection().prepareStatement("UPDATE Manager SET name = ?, pay = ?, teamID = ? WHERE id = ?");
            deleteStatement = connector.getConnection().prepareStatement("DELETE FROM Manager WHERE id = ?");
        } catch (SQLException e) {
            throw new DAOException("Error initializing prepared statements: " + e.getMessage());
        }
    }

    @Override
    public Optional<Manager> get(long id) {
        try {
            getStatement.setLong(1, id);
            ResultSet resultSet = getStatement.executeQuery();
            if (resultSet.next()) {
                int managerId = resultSet.getInt("id");
                String managerName = resultSet.getString("name");
                int managerPay = resultSet.getInt("pay");
                int teamID = resultSet.getInt("teamID");
                Manager manager = null;
                Optional<DevTeam> team = daoTeam.get(teamID);
                if (!team.isPresent()) {
                    return Optional.empty();
                }
                manager = new Manager(managerName, managerPay, team.get());
                manager.setId(managerId);
                return Optional.of(manager);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Manager> getAll() {
        List<Manager> managers = new ArrayList<>();
        try {
            ResultSet resultSet = getAllStatement.executeQuery();
            while (resultSet.next()) {
                int managerId = resultSet.getInt("id");
                String managerName = resultSet.getString("name");
                int managerPay = resultSet.getInt("pay");
                int teamID = resultSet.getInt("teamID");
                Manager manager = null;
                Optional<DevTeam> team = daoTeam.get(teamID);
                if (!team.isPresent()) {
                    continue;
                }
                manager = new Manager(managerName, managerPay, team.get());
                manager.setId(managerId);
                managers.add(manager);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }
        return managers;
    }

    @Override
    public void save(Manager manager) {
        try {
            saveStatement.setString(1, manager.getName());
            saveStatement.setInt(2, manager.getPayRate());
            saveStatement.setInt(3, manager.getTeam().getId());
            saveStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }
    }

    @Override
    public void update(Manager manager) {
        try {
            updateStatement.setString(1, manager.getName());
            updateStatement.setInt(2, manager.getPayRate());
            updateStatement.setInt(3, manager.getTeam().getId());
            updateStatement.setInt(4, manager.getId());
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }
    }

    @Override
    public void delete(Manager manager) {
        try {
            deleteStatement.setInt(1, manager.getId());
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }
    }
}
