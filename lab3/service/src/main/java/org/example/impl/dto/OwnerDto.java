package org.example.impl.dto;

import lombok.Data;
import org.example.implementations.entities.Cat;

import java.time.LocalDate;
import java.util.List;

@Data
public class OwnerDto {
    Integer id;
    String name;
    LocalDate dateOfBirth;
    List<Cat> cats;
}
