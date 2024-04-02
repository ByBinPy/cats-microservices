package org.example.declarations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public abstract class HibernateDao<T> implements Dao<T> {
    private final Class<T> clazz;
    protected final EntityManagerFactory entityManagerFactory;

    protected HibernateDao(Class<T> clazz, EntityManagerFactory entityManagerFactory) {
        this.clazz = clazz;
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public T getById(final Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return entityManager.find(clazz, id);
        }
    }

    @Override
    public List<T> getAll() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return entityManager.createQuery("FROM " + clazz.getName()).getResultList();
        }
    }

    @Override
    public Long getCount() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return (long) entityManager.createQuery("FROM " + clazz.getName()).getResultList().size();
        }
    }

    @Override
    public List<T> getItems(final Integer from, final Integer count) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return entityManager.createQuery("FROM " + clazz.getName())
                    .setFirstResult(from)
                    .setMaxResults(count)
                    .getResultList();
        }
    }

    public void saveOrUpdate(final T entity) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
        }
    }

    public T update(final T entity) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()){
            return entityManager.merge(entity);
        }
    }

    public void delete(final T entity) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()){
            entityManager.remove(entity);
        }
    }

    public void deleteById(final Long entityId) {
        T entity = getById(entityId);
        delete(entity);
    }
}
