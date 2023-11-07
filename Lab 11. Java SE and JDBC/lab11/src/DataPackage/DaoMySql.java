package DataPackage;

import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static Jdbc.HibernateConnectionPool.getSession;
import static Jdbc.HibernateConnectionPool.releaseSession;

public abstract class DaoMySql<T> {

    public DaoMySql() {
    }

    protected abstract String getByIdNamedQuery();

    protected abstract String getAllNamedQuery();

    public Optional<T> get(long id) throws DAOException {
        org.hibernate.Session session = getSession();
        try {
            Query query = session.getNamedQuery(getByIdNamedQuery());
            query.setParameter("id", id);
            return Optional.of((T) query.getSingleResult());
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        } finally {
            releaseSession(session);
        }
    }

    public List<T> getAll() throws DAOException {
        List<T> entries = new ArrayList<>();
        org.hibernate.Session session = getSession();
        try {
            Query query = session.getNamedQuery(getAllNamedQuery());
            return query.list();
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        } finally {
            releaseSession(session);
        }
    }
}
