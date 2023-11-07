package EntitiesDao;
import DataPackage.DAOException;
import DataPackage.DaoMySql;
import Entities.Invoice;
import org.hibernate.query.Query;

import java.util.Objects;

import static Jdbc.HibernateConnectionPool.getSession;
import static Jdbc.HibernateConnectionPool.releaseSession;

public class DaoInvoice extends DaoMySql<Invoice> {
    private String payQuery = "Invoice.payQuery";

    @Override
    protected String getByIdNamedQuery() {
        return "Invoice.selectById";
    }

    @Override
    protected String getAllNamedQuery() {
        return "Invoice.selectAll";
    }

    public void cancelPayment(Invoice invoice) throws DAOException {
        var session = getSession();
        var tx = session.beginTransaction();
        try {
            Query query = session.getNamedQuery(payQuery);
            query.setParameter("isPaid", false);
            query.setParameter("id", invoice.getId());
            query.executeUpdate();

            tx.commit();
            invoice.setPaid(false);
        } catch (Exception e) {
            if (Objects.nonNull(tx)) tx.rollback();
            throw new DAOException(e.getMessage());
        } finally {
            releaseSession(session);
        }
    }

    public void pay(Invoice invoice) throws DAOException {
        var session = getSession();
        var tx = session.beginTransaction();
        try {
            Query query = session.getNamedQuery(payQuery);
            query.setParameter("isPaid", true);
            query.setParameter("id", invoice.getId());
            query.executeUpdate();

            tx.commit();
            invoice.setPaid(true);
        } catch (Exception e) {
            if (Objects.nonNull(tx)) tx.rollback();
            throw new DAOException(e.getMessage());
        } finally {
            releaseSession(session);
        }
    }
}
