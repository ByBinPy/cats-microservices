package itmo.tech.mapping;

import itmo.tech.dto.CatDto;
import itmo.tech.entities.Cat;
import itmo.tech.owner.Rpc;
import itmo.tech.repositories.CatDao;
import itmo.tech.repositories.OwnerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoMapper {
    private final OwnerDao ownerDao;
    private final CatDao catDao;

    @Autowired
    public DtoMapper(OwnerDao catDao, CatDao catDao1) {
        this.ownerDao = catDao;

        this.catDao = catDao1;
    }

    public Rpc.CatDto toRpcDto(Cat cat) {
        var  responseDto =  Rpc.CatDto.newBuilder()
                .setId(cat.getId())
                .setName(cat.getName())
                .setColor(cat.getColor().name())
                .setBreed(cat.getBreed())
                .setDateOfBirth(cat.getDateOfBirth().toString());
        cat.getFriends().forEach(cat1 -> responseDto.addFriendsId(cat1.getId()));

        return responseDto.build();
    }
    public Rpc.Cats toRpcCats(List<Cat> cats){
         var catsRpc =  Rpc.Cats.newBuilder();
         cats.forEach(cat -> catsRpc.addCats(toRpcDto(cat)));
         return catsRpc.build();
    }
    public Cat toCat(CatDto catDto) {
        Cat cat = new Cat();
        cat.setId(catDto.getId());
        cat.setName(catDto.getName());
        cat.setBreed(catDto.getBreed());
        cat.setColor(catDto.getColor());
        cat.setOwner(ownerDao.findById(catDto.getOwnerId()).get());
        cat.setDateOfBirth(LocalDate.parse(catDto.getDateOfBirth()));
        cat.setFriends(catDto.getFriendsId().stream().map(id -> catDao.findById(id).get()).collect(Collectors.toSet()));
        return cat;
    }
}
