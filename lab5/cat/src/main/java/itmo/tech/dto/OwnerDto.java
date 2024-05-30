package itmo.tech.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class OwnerDto {
    private String name;
    private String dateOfBirth;
    private List<Integer> cats;
}
