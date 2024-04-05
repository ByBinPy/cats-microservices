package org.example.impl.dto;

import lombok.Data;
import org.example.implementations.entities.Owner;
import org.example.implementations.Colors;
import java.time.LocalDate;

@Data
public class CatDto {
    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private String breed;
    private Colors color;
    private Owner owner;
}
