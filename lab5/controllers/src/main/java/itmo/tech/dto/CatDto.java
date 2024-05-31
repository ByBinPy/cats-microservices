package itmo.tech.dto;

import itmo.tech.enums.Colors;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CatDto {
    private Integer id;
    private String name;
    private String dateOfBirth;
    private String breed;
    private Colors color;
    private Integer ownerId;
    private List<Integer> friendsId;
}
