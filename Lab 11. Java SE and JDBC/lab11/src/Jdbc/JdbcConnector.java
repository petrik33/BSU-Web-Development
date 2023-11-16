package Jdbc;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static Logger.JLogManager.logException;

public class JdbcConnector {
    private static BlockingQueue<EntityManager> entityManagerPool;
    private static final int INITIAL_POOL_SIZE = 20;
    private static EntityManagerFactory entityManagerFactory;

    public static void initJdbcConnector() throws RuntimeException, JdbcConnectionException, ClassNotFoundException {
        entityManagerPool = new ArrayBlockingQueue<>(INITIAL_POOL_SIZE);
        initializePool();
    }

    private static void initializePool() throws JdbcConnectionException, ClassNotFoundException {
        entityManagerFactory = Persistence.createEntityManagerFactory("development");
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            try {
                EntityManager entityManager = entityManagerFactory.createEntityManager();
                entityManagerPool.add(entityManager);
            } catch (Exception e) {
                logException(e);
                throw new RuntimeException("Ошибка при создании экземпляров EntityManager.", e);
            }
        }
    }

    public static EntityManager getEntityManager() throws InterruptedException {
        return entityManagerPool.take();
    }

    public static boolean releaseEntityManager(EntityManager entityManager) {
        if (entityManager != null) {
            if (entityManager.isOpen()) entityManager.close();
            return entityManagerPool.offer(entityManager);
        }
        return false;
    }

    public static void closeFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
