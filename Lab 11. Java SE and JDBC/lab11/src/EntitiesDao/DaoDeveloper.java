package EntitiesDao;

import DataPackage.DAOException;
import DataPackage.DaoMySql;
import Entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Objects;

import static Jdbc.JdbcConnector.getEntityManager;
import static Jdbc.JdbcConnector.releaseEntityManager;

public class DaoDeveloper extends DaoMySql<Developer> {

    public DaoDeveloper() {
        super(Developer.class);
    }

    public List<Developer> getDevelopersByTeam(DevTeam team) throws DAOException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Developer> cq = cb.createQuery(Developer.class);
            Root<Developer> root = cq.from(Developer.class);

            cq.where(cb.equal(root.get(Developer_.team).get(DevTeam_.id), team.getId()));

            TypedQuery<Developer> query = em.createQuery(cq);
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        } finally {
            releaseEntityManager(em);
        }
    }

    public List<Developer> getDevelopersByProject(Project project) throws DAOException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Developer> cq = cb.createQuery(Developer.class);
            Root<Developer> root = cq.from(Developer.class);

            cq.where(cb.equal(root.get(Developer_.currentProject).get(Project_.id), project.getId()));

            TypedQuery<Developer> query = em.createQuery(cq);
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        } finally {
            releaseEntityManager(em);
        }
    }

    public void assignProjectToDeveloper(Developer developer, Project project) throws DAOException {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = getEntityManager();
            tx = em.getTransaction();

            tx.begin();

            Developer managedDeveloper = em.find(Developer.class, developer.getId());
            Project managedProject = em.find(Project.class, project.getId());

            if (managedDeveloper != null && managedProject != null) {
                managedDeveloper.setCurrentProject(managedProject);
                em.merge(managedDeveloper);
            }

            tx.commit();
        } catch (Exception e) {
            if (Objects.nonNull(tx)) tx.rollback();
            throw new DAOException(e.getMessage());
        } finally {
            releaseEntityManager(em);
        }
    }
}
