package org.example.implementations.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.example.implementations.Colors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(schema = "cats_db", name = "cats")
@AllArgsConstructor
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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
