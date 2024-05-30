package itmo.tech.messaging.mapping;

import itmo.tech.enums.Roles;
import itmo.tech.owner.Rpc;
import itmo.tech.dto.OwnerDto;

public class DtoMapper {
    public static OwnerDto toOwnerDto(Rpc.OwnerDto rpcDto) {
         return OwnerDto.builder()
                 .name(rpcDto.getName())
                 .cats(rpcDto.getCatsList())
                 .dateOfBirth(rpcDto.getDateOfBirth())
                 .role(Roles.valueOf(rpcDto.getRole().name()))
                 .build();
    }
    public static Rpc.OwnerId toRpcOwnerId(Integer id) {
        return Rpc.OwnerId.newBuilder().setId(id).build();
    }
    public static Rpc.OwnerName toRpcOwnerName(String name) {
        return Rpc.OwnerName.newBuilder().setName(name).build();
    }
}
