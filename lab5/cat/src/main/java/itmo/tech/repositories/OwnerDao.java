package itmo.tech.repositories;

import itmo.tech.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerDao extends JpaRepository<Owner, Integer> {
    Optional<Owner> findOwnerByName(String name);
}