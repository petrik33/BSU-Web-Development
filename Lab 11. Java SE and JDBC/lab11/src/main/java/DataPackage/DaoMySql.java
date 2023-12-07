package DataPackage;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static Jdbc.JdbcConnector.getEntityManager;
import static Jdbc.JdbcConnector.releaseEntityManager;

public abstract class DaoMySql<T> {

    private Class<T> entityClass;

    public DaoMySql(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public Optional<T> get(long id) throws DAOException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(entityClass);
            Root<T> root = cq.from(entityClass);

            EntityType<T> entityType = em.getMetamodel().entity(entityClass);
            SingularAttribute<? super T, Integer> idAttribute = entityType.getSingularAttribute("id", Integer.class);

            cq.select(root).where(cb.equal(root.get(idAttribute), id));
            TypedQuery<T> query = em.createQuery(cq);
            return Optional.of(query.getSingleResult());
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        } finally {
            releaseEntityManager(em);
        }
    }

    public List<T> getAll() throws DAOException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(entityClass);
            Root<T> root = cq.from(entityClass);
            cq.select(root);
            TypedQuery<T> query = em.createQuery(cq);
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        } finally {
            releaseEntityManager(em);
        }
    }
}
