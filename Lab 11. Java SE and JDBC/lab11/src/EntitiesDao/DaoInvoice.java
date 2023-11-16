package EntitiesDao;
import DataPackage.DAOException;
import DataPackage.DaoMySql;
import Entities.Invoice;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.Objects;

import static Jdbc.JdbcConnector.getEntityManager;
import static Jdbc.JdbcConnector.releaseEntityManager;

public class DaoInvoice extends DaoMySql<Invoice> {
    public DaoInvoice() {
        super(Invoice.class);
    }

    public void cancelPayment(Invoice invoice) throws DAOException {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = getEntityManager();
            tx = em.getTransaction();
            tx.begin();

            Invoice managedInvoice = em.find(Invoice.class, invoice.getId());
            if (managedInvoice != null) {
                managedInvoice.setPaid(false);
                em.merge(managedInvoice);
            }

            tx.commit();
        } catch (Exception e) {
            if (Objects.nonNull(tx)) tx.rollback();
            throw new DAOException(e.getMessage());
        } finally {
            releaseEntityManager(em);
        }
    }

    public void pay(Invoice invoice) throws DAOException {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = getEntityManager();
            tx = em.getTransaction();
            tx.begin();

            Invoice managedInvoice = em.find(Invoice.class, invoice.getId());
            if (managedInvoice != null) {
                managedInvoice.setPaid(true);
                em.merge(managedInvoice);
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
