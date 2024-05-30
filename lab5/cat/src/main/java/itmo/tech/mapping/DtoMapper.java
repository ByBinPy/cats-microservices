package itmo.tech.mapping;

import itmo.tech.entities.Cat;
import itmo.tech.entities.Owner;
import itmo.tech.owner.Rpc;

import java.util.List;

public class DtoMapper {
    public static Rpc.OwnerDto toRpcOwnerDto(Owner owner) {
        var responseDto = Rpc.OwnerDto
                .newBuilder()
                .setName(owner.getName())
                .setDateOfBirth(owner.getDateOfBirth().toString());
        List<Cat> cats = owner.getCats();
        for (int i = 0; i < cats.size(); i++) {
            responseDto.setCats(i, cats.get(i).getId());
        }

        return responseDto.build();
    }
    public static Rpc.OwnerId toOwnerId(Integer id) {

        return Rpc.OwnerId.newBuilder().setId(id).build();
    }

}
