package org.example.implementations.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.declarations.HibernateDao;
import org.example.implementations.entities.Cat;

public class CatDaoImpl extends HibernateDao <Cat> {
    public CatDaoImpl(Class<Cat> clazz, EntityManagerFactory entityManagerFactory) {
        super(clazz, entityManagerFactory);
    }
    public void addFriend(Long cat_id, Long friend_id) {
        try(EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Cat cat = entityManager.find(Cat.class, cat_id);
            Cat friend = entityManager.find(Cat.class, friend_id);
            friend.getFriends().add(cat);
            cat.getFriends().add(friend);
            entityManager.getTransaction().commit();
        }
    }
}
