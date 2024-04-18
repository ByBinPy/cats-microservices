package org.example.impl.dto;

import lombok.Data;
import org.example.implementations.entities.Cat;
import org.example.implementations.entities.Owner;
import org.example.implementations.Colors;
import java.time.LocalDate;
import java.util.List;
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
