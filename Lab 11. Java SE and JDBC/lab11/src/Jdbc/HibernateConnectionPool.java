package Jdbc;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static Logger.JLogManager.logException;

public class HibernateConnectionPool {
    private static BlockingQueue<Session> connectionPool;
    private static int INITIAL_POOL_SIZE = 20;
    private static SessionFactory sessionFactory;

    static {
        connectionPool = new ArrayBlockingQueue<>(INITIAL_POOL_SIZE);
        try {
            initializePool();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    private static void initializePool() {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException e) {
            logException(e);
            throw e;
        }

        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            try {
                Session session = sessionFactory.openSession();
                if (connectionPool.add(session)) {
                    continue;
                }
                HibernateException e = new HibernateException("Can't add session to pool");
                logException(e);
                throw e;
            } catch (HibernateException e) {
                logException(e);
                throw e;
            }
        }
    }

    public static Session getSession() {
        try {
            return connectionPool.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new HibernateException("Interrupted while waiting for a session.");
        }
    }

    public static boolean releaseSession(Session session) {
        session.clear();
        return connectionPool.offer(session);
    }
}
