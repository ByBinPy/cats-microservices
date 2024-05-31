package itmo.tech.dto;

import itmo.tech.enums.Roles;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class OwnerShow {
    private String name;
    private String dateOfBirth;
    private Roles role;
    private List<Integer> cats;
}
