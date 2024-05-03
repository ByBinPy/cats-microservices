package org.example.declarations;

import org.example.implementations.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository("OwnerDao")
public interface OwnerDao extends JpaRepository<Owner, Integer> {
    Optional<Owner> findOwnerByName(String name);
}
