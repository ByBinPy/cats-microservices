package org.example.impl.services;

import org.example.declarations.CatDao;
import org.example.declarations.OwnerDao;
import org.example.exceptions.SaveExistOwner;
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
import java.util.stream.Collectors;

@Service
public class OwnerService {
    private final OwnerDao ownerDao;
    private final CatDao catDao;

    @Autowired
    public OwnerService(OwnerDao ownerDao, CatDao catDao) {
        this.ownerDao = ownerDao;
        this.catDao = catDao;
    }

    public ResponseEntity<?> getById(Integer id) {
        Optional<Owner> ownerOptional = ownerDao.findById(id);
        if (ownerOptional.isEmpty())
            return new ResponseEntity<>(new UnknownOwner(), HttpStatus.NOT_FOUND);

        Owner owner = ownerOptional.get();

        return new ResponseEntity<>(converOwnerToDto(owner), HttpStatus.OK);
    }
    public ResponseEntity<?> save(OwnerDto ownerDto) {
        if (ownerDao.findById(ownerDto.getId()).isPresent())
            return new ResponseEntity<>(new SaveExistOwner(), HttpStatus.NOT_ACCEPTABLE);

        return new ResponseEntity<>(converOwnerToDto(ownerDao.save(convertDtoToOwner(ownerDto))), HttpStatus.CREATED);
    }
    public void delete(Integer id){
        ownerDao.deleteById(id);
    }

    public ResponseEntity<?> update(Integer id, OwnerDto newOwner){
        if (ownerDao.findById(id).isEmpty())
            return new ResponseEntity<>(new UnknownOwner(), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(ownerDao.save(convertDtoToOwner(newOwner)), HttpStatus.ACCEPTED);
    }
    private OwnerDto converOwnerToDto(Owner owner) {
        OwnerDto ownerDto = new OwnerDto();
        ownerDto.setId(owner.getId());
        ownerDto.setName(owner.getName());
        ownerDto.setDateOfBirth(owner.getDateOfBirth());
        ownerDto.setCatIds(owner.getCats().stream().map(Cat::getId).collect(Collectors.toList()));
        return ownerDto;
    }
    private Owner convertDtoToOwner(OwnerDto ownerDto) {
        Owner owner = new Owner();
        owner.setName(ownerDto.getName());
        owner.setId(ownerDto.getId());
        owner.setDateOfBirth(ownerDto.getDateOfBirth());
        owner.setCats(ownerDto.getCatIds().stream().map((Integer id) -> catDao.findById(id).get()).collect(Collectors.toList()));
        return owner;
    }
}
