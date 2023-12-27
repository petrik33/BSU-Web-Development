package EntitiesDao;

import DataPackage.DaoMySql;
import Entities.*;

public class DaoUser extends DaoMySql<User> {
    public DaoUser() {
        super(User.class);
    }
}
