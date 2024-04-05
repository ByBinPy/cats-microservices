package org.example.impl.services;

import org.example.declarations.CatDao;
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

    @Autowired
    public CatService(CatDao catDao) {
        this.catDao = catDao;
    }

    public CatDto getById(Integer id) throws UnknownCat {
        Optional<Cat> catOpt = catDao.findById(id);
        if (catOpt.isEmpty()) {
            throw new UnknownCat();
        }
        Cat cat = catOpt.get();
        CatDto catDto = new CatDto();
        catDto.setId(cat.getId());
        catDto.setName(cat.getName());
        catDto.setDateOfBirth(cat.getDateOfBirth());
        catDto.setOwner(cat.getOwner());
        catDto.setBreed(cat.getBreed());
        catDto.setColor(cat.getColor());
        return catDto;
    }
    public ResponseEntity<?> save(CatDto catDto) {
        if (catDao.findById(catDto.getId()).isPresent())
            return new ResponseEntity<>(new SaveExistCat(), HttpStatus.NOT_ACCEPTABLE);

        Cat cat = new Cat();
        cat.setName(catDto.getName());
        cat.setId(catDto.getId());
        cat.setDateOfBirth(catDto.getDateOfBirth());
        cat.setColor(catDto.getColor());
        cat.setOwner(catDto.getOwner());
        cat.setBreed(catDto.getBreed());
        cat.setFriends(catDto.getFriends());


        Cat catSaved = catDao.save(cat);

        catDto.setId(catSaved.getId());
        catDto.setName(catSaved.getName());
        catDto.setDateOfBirth(catSaved.getDateOfBirth());
        catDto.setColor(catSaved.getColor());
        catDto.setOwner(catSaved.getOwner());
        catDto.setBreed(catSaved.getBreed());
        catDto.setFriends(catSaved.getFriends());

        return new ResponseEntity<>(catDto, HttpStatus.CREATED);
    }
    public void delete(Integer id){
        catDao.deleteById(id);
    }

    public ResponseEntity<?> update(Integer id, CatDto newCat){
        if (catDao.findById(id).isEmpty())
            return new ResponseEntity<>(new UnknownCat(), HttpStatus.NOT_FOUND);

        Cat cat = new Cat();
        cat.setName(newCat.getName());
        cat.setId(newCat.getId());
        cat.setDateOfBirth(newCat.getDateOfBirth());
        cat.setColor(newCat.getColor());
        cat.setOwner(newCat.getOwner());
        cat.setBreed(newCat.getBreed());
        cat.setFriends(newCat.getFriends());
        catDao.save(cat);

        return new ResponseEntity<>(newCat, HttpStatus.ACCEPTED);
    }
}
