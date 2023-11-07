package EntitiesDao;

import DataPackage.DAOException;
import DataPackage.DaoMySql;
import Entities.Customer;
import Entities.Project;
import org.hibernate.query.Query;

import java.util.List;

import static Jdbc.HibernateConnectionPool.getSession;
import static Jdbc.HibernateConnectionPool.releaseSession;


public class DaoProject extends DaoMySql<Project> {

    @Override
    protected String getByIdNamedQuery() {
        return "Project.selectById";
    }

    @Override
    protected String getAllNamedQuery() {
        return "Project.selectAll";
    }

    public List<Project> getProjectsForCustomer(Customer customer) throws DAOException {
        List<Project> projects;
        var session = getSession();
        try {
            String byCustomerStatement = "Project.byCustomerStatement";
            Query query = session.getNamedQuery(byCustomerStatement);
            query.setParameter("customerId", customer.getId());
            projects = query.list();
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        } finally {
            releaseSession(session);
        }
        return projects;
    }
}
