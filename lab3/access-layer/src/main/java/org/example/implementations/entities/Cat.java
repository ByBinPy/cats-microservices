package org.example.implementations.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.example.implementations.Colors;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(schema = "cats_db", name = "cats")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Column
    private String name;
    @NonNull
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;
    @Column
    private String breed;
    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "colors")
    private Colors color;
    @NonNull
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

    public Cat(@NonNull String name,
               @NonNull LocalDate dateOfBirth,
               String breed,
               Colors color,
               @NonNull Owner owner,
               Set<Long> friends) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.breed = breed;
        this.color = color;
        this.owner = owner;
    }
    public Cat() {

    }
}
