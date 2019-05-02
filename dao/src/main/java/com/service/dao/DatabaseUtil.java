package com.service.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;

public class DatabaseUtil {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseUtil.class);

    private static EntityManager entityManager;

    public static EntityManager getEntityManager() {
        if(entityManager == null) {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
            entityManager = entityManagerFactory.createEntityManager();
        }
        return entityManager;
    }

    public static <T> Optional<T> selectObject(Class<T> typeOfClass, Integer id) {
        LOG.info(String.format("selecting object of type %s with id %d", typeOfClass, id));
        T obj = getEntityManager().find(typeOfClass, id);
        return obj == null ? Optional.empty(): Optional.of(obj);
    }

    public static <T> void insertObject(T objectToBeSaved) throws Exception {
        try {
            getEntityManager().getTransaction().begin();
            LOG.info(String.format("Saving object %s to Database", objectToBeSaved));
            getEntityManager().persist(objectToBeSaved);
            getEntityManager().flush();
            getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            if (getEntityManager().getTransaction() != null) {
                LOG.warn("Transaction could not be saved. Rollback.");
                getEntityManager().getTransaction().rollback();
            }
            throw new Exception(e);
        }
    }

}
