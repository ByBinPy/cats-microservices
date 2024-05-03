package org.example.implementations.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.implementations.Colors;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Component
@Table(schema = "cats_db", name = "cats")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column
    private String breed;
    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "color")
    private Colors color;
    @Setter
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;

    @Setter
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(schema = "cats_db",
            name="friends",
            joinColumns=  @JoinColumn(name="cat_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="friend_id", referencedColumnName="id"))
    private Set<Cat> friends;
    public Cat() {

    }
}
