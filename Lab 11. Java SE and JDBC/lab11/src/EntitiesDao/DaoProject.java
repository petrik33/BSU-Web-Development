package EntitiesDao;

import DataPackage.DAOException;
import DataPackage.DaoMySql;
import Entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

import java.util.List;

import static Jdbc.JdbcConnector.getEntityManager;
import static Jdbc.JdbcConnector.releaseEntityManager;


public class DaoProject extends DaoMySql<Project> {

    public DaoProject() {
        super(Project.class);
    }

    public List<Project> getProjectsForCustomer(Customer customer) throws DAOException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Project> cq = cb.createQuery(Project.class);
            Root<Project> root = cq.from(Project.class);
            Join<Project, Specification> specificationJoin = root.join(Project_.specification);

            cq.where(cb.equal(specificationJoin.get(Specification_.customer).get(Customer_.id), customer.getId()));

            TypedQuery<Project> query = em.createQuery(cq);
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        } finally {
            releaseEntityManager(em);
        }
    }
}
