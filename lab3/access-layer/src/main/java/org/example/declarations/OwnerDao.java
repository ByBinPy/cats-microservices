package org.example.declarations;

import org.example.implementations.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OwnerDao extends JpaRepository<Owner, Integer> {
}
