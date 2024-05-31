package itmo.tech.messaging.mapping;

import itmo.tech.dto.CatDto;
import itmo.tech.dto.CatShow;
import itmo.tech.dto.OwnerDto;
import itmo.tech.dto.OwnerShow;
import itmo.tech.enums.Colors;
import itmo.tech.enums.Roles;
import itmo.tech.owner.Rpc;

import java.time.LocalDate;

public class DtoMapper {
    public static OwnerDto toOwnerDto(Rpc.OwnerDto rpcDto) {
        return OwnerDto.builder()
                .id(rpcDto.getId())
                .name(rpcDto.getName())
                .cats(rpcDto.getCatsList())
                .dateOfBirth(rpcDto.getDateOfBirth())
                .role(Roles.valueOf(rpcDto.getRole().name()))
                .password(rpcDto.getPassword())
                .build();
    }

    public static OwnerShow toOwnerShow(Rpc.OwnerDto rpcDto) {
        return OwnerShow.builder()
                .name(rpcDto.getName())
                .cats(rpcDto.getCatsList())
                .dateOfBirth(rpcDto.getDateOfBirth())
                .role(Roles.valueOf(rpcDto.getRole().name()))
                .build();
    }

    public static CatShow toCatShow(Rpc.CatDto catDto) {
        return CatShow.builder()
                .name(catDto.getName())
                .color(Colors.valueOf(catDto.getColor()))
                .breed(catDto.getBreed())
                .dateOfBirth(catDto.getDateOfBirth())
                .friendsId(catDto.getFriendsIdList())
                .ownerId(catDto.getOwnerId())
                .build();
    }

    public static Rpc.SecurityColor toSecurityColor(String color, Integer ownerId) {
        return Rpc.SecurityColor.newBuilder().setColor(color).setOwnerId(ownerId).build();
    }

    public static Rpc.SecurityId toSecurityId(Integer catId, Integer ownerId) {
        return Rpc.SecurityId.newBuilder().setCatId(catId).setOwnerId(ownerId).build();
    }
    public static Rpc.Id toRpcId(Integer id) {
        return Rpc.Id.newBuilder().setId(id).build();
    }

    public static Rpc.OwnerName toRpcOwnerName(String name) {
        return Rpc.OwnerName.newBuilder().setName(name).build();
    }
}
