package itmo.tech.repositories;

import itmo.tech.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerDao extends JpaRepository<Owner, Integer> {
    Optional<Owner> findOwnerByName(String name);
}