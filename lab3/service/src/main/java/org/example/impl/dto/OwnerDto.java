package org.example.impl.dto;

import lombok.Data;
import org.example.implementations.Roles;

import java.time.LocalDate;
import java.util.List;

@Data
public class OwnerDto {
    private Integer id;
    private String name;
    private String password;
    private Roles role;
    private LocalDate dateOfBirth;
    private List<Integer> catIds;
}
