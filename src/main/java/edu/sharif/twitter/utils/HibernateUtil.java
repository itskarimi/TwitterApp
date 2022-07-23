package edu.sharif.twitter.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
    private static final EntityManagerFactory entityManagerFactory;

    static {
        entityManagerFactory =
                Persistence.createEntityManagerFactory("Twitter");
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
