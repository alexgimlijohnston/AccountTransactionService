package com.service.dao;

import com.service.domain.Account;
import com.service.domain.AccountCustomerMapping;
import com.service.domain.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import java.util.Properties;

public class DatabaseUtil {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:~/test";
    private static final String DB_USERNAME = "";
    private static final String DB_PASSWORD = "";
    private static final String DB_DIALECT = "org.hibernate.dialect.MySQL5Dialect";
    private static final String DB_SHOW_SQL = "true";
    private static final String CURRENT_SESSION_CONTEXT_CLASS = "thread";
    private static final String HBM2DDL_AUTO = "create-drop";

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            Properties settings = new Properties();
            settings.put(Environment.DRIVER, DB_DRIVER);
            settings.put(Environment.URL, DB_CONNECTION);
            settings.put(Environment.USER, DB_USERNAME);
            settings.put(Environment.PASS, DB_PASSWORD);
            settings.put(Environment.DIALECT, DB_DIALECT);
            settings.put(Environment.SHOW_SQL, DB_SHOW_SQL);
            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, CURRENT_SESSION_CONTEXT_CLASS);
            settings.put(Environment.HBM2DDL_AUTO, HBM2DDL_AUTO);

            configuration.setProperties(settings);
            configuration.addAnnotatedClass(Account.class);
            configuration.addAnnotatedClass(Customer.class);
            configuration.addAnnotatedClass(AccountCustomerMapping.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }

    public static Session getNewSession() {
        return DatabaseUtil.getSessionFactory().openSession();
    }

    public static <T> void withTransaction(T objectToBeSaved) {
        Transaction transaction = null;
        try {
            Session session = DatabaseUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(objectToBeSaved);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public static <T> void insertWithTransaction(T objectToBeSaved) {
        Transaction transaction = null;
        try {
            Session session = DatabaseUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(objectToBeSaved);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }



}
