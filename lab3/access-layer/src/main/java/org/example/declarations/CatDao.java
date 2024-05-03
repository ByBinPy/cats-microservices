package org.example.declarations;

import org.example.implementations.Colors;
import org.example.implementations.entities.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("CatDao")
public interface CatDao extends JpaRepository<Cat, Integer> {
    List<Cat> findCatsByColor(Colors color);
}