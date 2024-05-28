package org.example.impl.services;

import org.example.declarations.CatDao;
import org.example.declarations.OwnerDao;
import org.example.exceptions.SaveExistCat;
import org.example.exceptions.UnknownCat;
import org.example.exceptions.UnknownColor;
import org.example.impl.dto.CatDto;
import org.example.implementations.Colors;
import org.example.implementations.entities.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CatService {

    private final CatDao catDao;
    private final OwnerDao ownerDao;

    @Autowired
    public CatService(CatDao catDao, OwnerDao ownerDao) {
        this.catDao = catDao;
        this.ownerDao = ownerDao;
    }

    public ResponseEntity<?> getById(Integer id, Integer ownerId) throws UnknownCat {
        Optional<Cat> catOpt = catDao.findById(id).filter(cat -> cat.getOwner().getId().equals(ownerId));
        if (catOpt.isEmpty()) {
            throw new UnknownCat("try find unknown cat");
        }
        return new ResponseEntity<>(convertCatToDto(catOpt.get()), HttpStatus.OK);
    }

    public ResponseEntity<?> save(CatDto catDto) throws SaveExistCat {
        if (catDao.findById(catDto.getId()).isPresent())
            throw new SaveExistCat();

        return new ResponseEntity<>(convertCatToDto(catDao.save(convertDtoToCat(catDto))), HttpStatus.CREATED);
    }

    public void delete(Integer id) {
        catDao.deleteById(id);
    }

    public ResponseEntity<?> getByColor(String color, Integer ownerId) throws UnknownColor {
        Colors colorFind = Arrays.stream(Colors.values()).filter(col -> col.name().equals(color)).collect(Collectors.toList()).getLast();

        if (colorFind == null)

            throw new UnknownColor("try find cat with unknown color");

        List<Cat> cats = catDao.findCatsByColor(colorFind)
                .stream()
                .filter(cat -> Objects.equals(cat.getOwner().getId(), ownerId))
                .collect(Collectors.toList());

        if (cats.isEmpty())

            throw new UnknownColor("try find cat with unknown color");

        return new ResponseEntity<>(cats.stream().map(this::convertCatToDto).collect(Collectors.toList()), HttpStatus.OK);
    }
    public ResponseEntity<?> update(Integer id, CatDto newCat) throws UnknownCat {
        if (catDao.findById(id).isEmpty())
            throw new UnknownCat("try update unknown cat");

        return new ResponseEntity<>(catDao.save(convertDtoToCat(newCat)), HttpStatus.ACCEPTED);
    }

    private Cat convertDtoToCat(CatDto catDto) {
        Cat cat = new Cat();
        cat.setName(catDto.getName());
        cat.setId(catDto.getId());
        cat.setDateOfBirth(catDto.getDateOfBirth());
        cat.setColor(catDto.getColor());
        cat.setOwner(ownerDao.findById(catDto.getOwnerId()).get());
        cat.setBreed(catDto.getBreed());
        cat.setFriends(catDto.getFriends());
        return cat;
    }

    private CatDto convertCatToDto(Cat cat) {
        CatDto catDto = new CatDto();
        catDto.setId(cat.getId());
        catDto.setName(cat.getName());
        catDto.setDateOfBirth(cat.getDateOfBirth());
        catDto.setColor(cat.getColor());
        catDto.setOwnerId(cat.getOwner().getId());
        catDto.setBreed(cat.getBreed());
        catDto.setFriends(cat.getFriends());
        return catDto;
    }
}
