package org.example.impl.dto;

import lombok.Data;
import org.example.implementations.Roles;

import java.time.LocalDate;
import java.util.List;

@Data
public class OwnerDto {
    Integer id;
    String name;
    String password;
    Roles role;
    LocalDate dateOfBirth;
    List<Integer> catIds;
}
