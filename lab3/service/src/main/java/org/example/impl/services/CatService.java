package org.example.impl.services;

import org.example.declarations.CatDao;
import org.example.exceptions.UnknownCat;
import org.example.impl.dto.CatDto;
import org.example.implementations.entities.Cat;
import org.springframework.beans.factory.annotation.Autowired;
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
}
