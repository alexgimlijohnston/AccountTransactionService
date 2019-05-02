package com.service.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class DatabaseUtil {

    private static EntityManager entityManager;

    public static EntityManager getEntityManager() {
        if(entityManager == null) {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
            entityManager = entityManagerFactory.createEntityManager();
        }
        return entityManager;
    }

    public static <T> Optional<T> selectObject(Class<T> typeOfClass, Integer id) {
        //List obg = entityManager.createQuery("from Account where id = "+id).getResultList();
        T obj = getEntityManager().find(typeOfClass, id);
        return obj == null ? Optional.empty(): Optional.of(obj);
    }

    public static <T> void insertObject(T objectToBeSaved) throws Exception {
        try {
            getEntityManager().getTransaction().begin();
            System.out.println("Saving object to Database");
            getEntityManager().persist(objectToBeSaved);
            getEntityManager().flush();
            getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            if (getEntityManager().getTransaction() != null) {
                getEntityManager().getTransaction().rollback();
            }
            throw new Exception(e);
        }
    }

}
