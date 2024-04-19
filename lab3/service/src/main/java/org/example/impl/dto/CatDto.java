package org.example.impl.dto;

import lombok.Data;
import org.example.implementations.Colors;
import org.example.implementations.entities.Cat;

import java.time.LocalDate;
import java.util.Set;

@Data
public class CatDto {
    private Integer id;
    private String name;
    private LocalDate dateOfBirth;
    private String breed;
    private Colors color;
    private Integer ownerId;
    private Set<Cat> friends;
}
