package com.adhound.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


/**
 * This class provides a SessionFactory for DAOs using Hibernate
 *
 * @author kkelm
 * @version 1.0 2/13/2020.
 */
public class SessionFactoryProvider {

    private static SessionFactory sessionFactory;

    /**
     * Private constructor prevents instantiating this class anywhere else
     */
    private SessionFactoryProvider() {

    }

    /**
     * Reads in the Hibernate config file to setup a connection to the database. Assigns the connection meta
     * to the SessionFactory object.
     */
    public static void createSessionFactory() {
        // If the Hibernate config file had a different name then the file name would be passed to the configure() method.
        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure().build();
        Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
        sessionFactory = metaData.getSessionFactoryBuilder().build();
    }

    /**
     * Gets a SessionFactory.
     *
     * @return A SessionFactory object
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            createSessionFactory();
        }
        return sessionFactory;

    }
}