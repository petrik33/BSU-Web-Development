package EntitiesDao;

import DataPackage.DaoMySql;
import Entities.Specification;

public class DaoSpecification extends DaoMySql<Specification> {

    public DaoSpecification() {
        super(Specification.class);
    }

}
