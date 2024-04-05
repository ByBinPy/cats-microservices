package org.example.implementations.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(schema = "cats_db", name = "owners")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column
    String name;

    @Column(name = "date_of_birth")
    LocalDate dateOfBirth;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "owner_id")
    List<Cat> cats;

    public Owner(String name, LocalDate dateOfBirth, List<Cat> cats) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.cats = cats;
    }

    public Owner() {

    }
}