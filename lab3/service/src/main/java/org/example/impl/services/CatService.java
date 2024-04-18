package org.example.impl.services;

import org.example.declarations.CatDao;
import org.example.declarations.OwnerDao;
import org.example.exceptions.SaveExistCat;
import org.example.exceptions.SaveExistOwner;
import org.example.exceptions.UnknownCat;
import org.example.exceptions.UnknownOwner;
import org.example.impl.dto.CatDto;
import org.example.impl.dto.OwnerDto;
import org.example.implementations.entities.Cat;
import org.example.implementations.entities.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CatService {

    CatDao catDao;
    OwnerDao ownerDao;

    @Autowired
    public CatService(CatDao catDao, OwnerDao ownerDao) {
        this.catDao = catDao;
        this.ownerDao = ownerDao;
    }

    public CatDto getById(Integer id) throws UnknownCat {
        Optional<Cat> catOpt = catDao.findById(id);
        if (catOpt.isEmpty()) {
            throw new UnknownCat();
        }
        Cat cat = catOpt.get();
        return convertCatToDto(cat);
    }
    public ResponseEntity<?> save(CatDto catDto) {
        if (catDao.findById(catDto.getId()).isPresent())
            return new ResponseEntity<>(new SaveExistCat(), HttpStatus.NOT_ACCEPTABLE);

        return new ResponseEntity<>(convertCatToDto(catDao.save(convertDtoToCat(catDto))), HttpStatus.CREATED);
    }
    public void delete(Integer id){
        catDao.deleteById(id);
    }

    public ResponseEntity<?> update(Integer id, CatDto newCat){
        if (catDao.findById(id).isEmpty())
            return new ResponseEntity<>(new UnknownCat(), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>( catDao.save(convertDtoToCat(newCat)), HttpStatus.ACCEPTED);
    }
    private Cat convertDtoToCat(CatDto catDto){
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
