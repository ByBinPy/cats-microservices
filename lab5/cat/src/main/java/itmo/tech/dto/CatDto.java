package itmo.tech.dto;

import itmo.tech.entities.Cat;
import itmo.tech.enums.Colors;
import lombok.Data;

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
