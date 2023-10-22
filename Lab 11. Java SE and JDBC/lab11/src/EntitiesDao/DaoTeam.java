package EntitiesDao;

import DataPackage.DAOException;
import DataPackage.DaoMySql;
import Entities.Customer;
import Entities.DevTeam;
import Entities.Specification;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoTeam extends DaoMySql<DevTeam> {

     @Override public String getStatement() {
        return "SELECT * FROM Team WHERE id = ?";
     }

     @Override public String getAllStatement() {
        return "SELECT * FROM Team";
     }

    @Override
    public Optional<DevTeam> convertFullSet(ResultSet resultSet) throws SQLException {
        int teamId = resultSet.getInt("id");
        String teamName = resultSet.getString("name");
        DevTeam team = new DevTeam(teamName);
        team.setId(teamId);
        return Optional.of(team);
    }
}
