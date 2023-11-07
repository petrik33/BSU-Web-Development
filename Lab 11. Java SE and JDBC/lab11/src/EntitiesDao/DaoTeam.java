package EntitiesDao;

import DataPackage.DaoMySql;
import Entities.DevTeam;

public class DaoTeam extends DaoMySql<DevTeam> {

    @Override
    protected String getByIdNamedQuery() {
        return "Team.selectById";
    }

    @Override
    protected String getAllNamedQuery() {
        return "Team.selectAll";
    }

}