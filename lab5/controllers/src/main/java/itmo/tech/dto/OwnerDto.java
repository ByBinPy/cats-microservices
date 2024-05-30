package itmo.tech.dto;

import itmo.tech.enums.Roles;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class OwnerDto {
    private Integer id;
    private String password;
    private String name;
    private String dateOfBirth;
    private Roles role;
    private List<Integer> cats;
}
