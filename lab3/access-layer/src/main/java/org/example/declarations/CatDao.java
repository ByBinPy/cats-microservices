package org.example.declarations;

import org.example.implementations.entities.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatDao extends JpaRepository<Cat, Integer> {

}
