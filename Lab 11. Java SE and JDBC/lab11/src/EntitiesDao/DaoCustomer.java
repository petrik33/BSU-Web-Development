package EntitiesDao;

import DataPackage.DaoMySql;
import Entities.Customer;

public class DaoCustomer extends DaoMySql<Customer> {

    public DaoCustomer() {
        super(Customer.class);
    }

}
