package EntitiesDao;

import DataPackage.DaoMySql;
import Entities.Specification;

public class DaoSpecification extends DaoMySql<Specification> {

    @Override
    protected String getByIdNamedQuery() {
        return "Specification.selectById";
    }

    @Override
    protected String getAllNamedQuery() {
        return "Specification.selectAll";
    }

}
