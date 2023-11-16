package EntitiesDao;

import DataPackage.DaoMySql;
import Entities.DevTeam;

public class DaoTeam extends DaoMySql<DevTeam> {

    public DaoTeam() {
        super(DevTeam.class);
    }

}