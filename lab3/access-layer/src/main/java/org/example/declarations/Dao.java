package org.example.declarations;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Dao<T> extends JpaRepository<T, Integer> {
}
