package EntitiesDao;

import DataPackage.DAOException;
import DataPackage.DaoMySql;
import Entities.*;

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
        } catch (SQLException e) {
            throw new DAOException("Error initializing prepared statements: " + e.getMessage());
        }
    }

    @Override
    public Optional<Manager> convertFullSet(ResultSet resultSet) throws SQLException, DAOException {
        int managerId = resultSet.getInt("id");
        String managerName = resultSet.getString("name");
        int managerPay = resultSet.getInt("pay");
        int teamID = resultSet.getInt("teamID");
        Optional<DevTeam> team = daoTeam.get(teamID);
        Manager manager = new Manager(managerName, managerPay, team.orElse(null));
        manager.setId(managerId);
        return Optional.of(manager);
    }
}
