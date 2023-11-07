package EntitiesDao;

import DataPackage.DaoMySql;
import Entities.Customer;

public class DaoCustomer extends DaoMySql<Customer> {

    @Override
    protected String getByIdNamedQuery() {
        return "Customer.selectById";
    }

    @Override
    protected String getAllNamedQuery() {
        return "Customer.selectAll";
    }

}
