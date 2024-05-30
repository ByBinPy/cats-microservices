package itmo.tech.repositories;

import itmo.tech.entities.Cat;
import itmo.tech.enums.Colors;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatDao extends JpaRepository<Cat, Integer> {
    List<Cat> findCatsByColor(Colors color);
}