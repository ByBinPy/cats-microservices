package org.example.implementations.dao;

import jakarta.persistence.EntityManagerFactory;
import org.example.declarations.HibernateDao;
import org.example.implementations.entities.Owner;

public class OwnerDaoImpl extends HibernateDao <Owner> {
    public OwnerDaoImpl(Class<Owner> clazz, EntityManagerFactory entityManagerFactory) {
        super(clazz, entityManagerFactory);
    }
}
