package EntitiesDao;

import DataPackage.DAOException;
import DataPackage.DaoMySql;
import Entities.Customer;
import Entities.DevTeam;
import Entities.Project;
import Entities.Specification;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoSpecification extends DaoMySql<Specification> {
    private DaoCustomer daoCustomer;
    public DaoSpecification() throws DAOException {
        super();
        this.daoCustomer = new DaoCustomer();
    }

    @Override public String getStatement() {
        return "SELECT * FROM Specification WHERE id = ?";
    }

    @Override public String getAllStatement() {
        return "SELECT * FROM Specification";
    }

    @Override
    public Optional<Specification> convertFullSet(ResultSet resultSet) throws SQLException, DAOException {
        int specificationId = resultSet.getInt("id");
        int customerId = resultSet.getInt("customerID");
        Optional<Customer> customer = daoCustomer.get(customerId);
        if (!customer.isPresent()) {
            return Optional.empty();
        }
        Specification specification = new Specification(customer.get());
        specification.setId(specificationId);
        return Optional.of(specification);
    }
}
