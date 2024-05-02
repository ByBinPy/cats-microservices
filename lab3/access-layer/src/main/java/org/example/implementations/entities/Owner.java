package org.example.implementations.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.implementations.Roles;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@Table(schema = "cats_db", name = "owners")
@Component
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String password;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    Roles role;

    @Column(name = "date_of_birth")
    LocalDate dateOfBirth;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "owner_id")
    List<Cat> cats;

    public Owner() {

    }
}