package org.example.implementations.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class HibernateUtil {
    private static EntityManagerFactory entityManagerFactory;
    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            try {
                entityManagerFactory = Persistence.createEntityManagerFactory("myUnit");
            } catch (Throwable exception) {
                throw new RuntimeException(exception.getMessage());
            }
        }
        return entityManagerFactory;
    }
}