package org.example.declarations;

import java.util.List;

public interface Dao<T> {
    T getById(final Long id);
    List<T> getItems(final Integer from, final Integer count);
    List<T> getAll();
    Long getCount();
    void saveOrUpdate(final T entity);
    T update(final T entity);
    void delete(final T entity);
    void deleteById(final Long entityId);
}
