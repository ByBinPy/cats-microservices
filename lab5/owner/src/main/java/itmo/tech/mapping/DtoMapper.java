package itmo.tech.mapping;

import itmo.tech.dto.OwnerDto;
import itmo.tech.entities.Owner;
import itmo.tech.owner.Rpc;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DtoMapper {
    public Rpc.OwnerDto toRpcOwnerDto(Owner owner) {
        var responseDto = Rpc.OwnerDto
                .newBuilder()
                .setName(owner.getName())
                .setDateOfBirth(owner.getDateOfBirth().toString())
                .setId(owner.getId())
                .setPassword(owner.getPassword());
        owner.getCats().forEach(cat -> responseDto.addCats(cat.getId()));

        return responseDto.build();
    }
    public Rpc.Id toId(Integer id) {

        return Rpc.Id.newBuilder().setId(id).build();
    }

    public Owner toOwner(OwnerDto ownerDto) {
        Owner owner = new Owner();
        owner.setId(ownerDto.getId());
        owner.setName(ownerDto.getName());
        owner.setPassword(new BCryptPasswordEncoder(10).encode(ownerDto.getPassword()));
        owner.setDateOfBirth(LocalDate.parse(ownerDto.getDateOfBirth()));
        owner.setRole(ownerDto.getRole());
        owner.setCats(owner.getCats());
        return owner;
    }
}
