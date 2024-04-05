package org.example.impl.services;

import org.example.declarations.OwnerDao;
import org.example.exceptions.SaveExistOwner;
import org.example.exceptions.UnknownOwner;
import org.example.impl.dto.OwnerDto;
import org.example.implementations.entities.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OwnerService {
    private final OwnerDao ownerDao;

    @Autowired
    public OwnerService(OwnerDao ownerDao) {
        this.ownerDao = ownerDao;
    }

    public ResponseEntity<?> getById(Integer id) {
        Optional<Owner> ownerOptional = ownerDao.findById(id);
        if (ownerOptional.isEmpty())
            return new ResponseEntity<>(new UnknownOwner(), HttpStatus.NOT_FOUND);

        Owner owner = ownerOptional.get();
        OwnerDto ownerDto = new OwnerDto();
        ownerDto.setId(owner.getId());
        ownerDto.setName(owner.getName());
        ownerDto.setDateOfBirth(owner.getDateOfBirth());
        ownerDto.setCats(owner.getCats());

        return new ResponseEntity<>(ownerDto, HttpStatus.OK);
    }
    public ResponseEntity<?> save(OwnerDto ownerDto) {
        if (ownerDao.findById(ownerDto.getId()).isPresent())
            return new ResponseEntity<>(new SaveExistOwner(), HttpStatus.NOT_ACCEPTABLE);

        Owner owner = new Owner();
        owner.setName(ownerDto.getName());
        owner.setId(ownerDto.getId());
        owner.setDateOfBirth(ownerDto.getDateOfBirth());
        owner.setCats(ownerDto.getCats());


        Owner ownerSaved = ownerDao.save(owner);

        ownerDto.setId(ownerSaved.getId());
        ownerDto.setName(ownerSaved.getName());
        ownerDto.setDateOfBirth(ownerSaved.getDateOfBirth());
        ownerDto.setCats(ownerSaved.getCats());

        return new ResponseEntity<>(ownerDto, HttpStatus.CREATED);
    }
    public void delete(Integer id){
        ownerDao.deleteById(id);
    }

    public ResponseEntity<?> update(Integer id, OwnerDto newOwner){
        if (ownerDao.findById(id).isEmpty())
            return new ResponseEntity<>(new UnknownOwner(), HttpStatus.NOT_FOUND);

        Owner owner = new Owner();
        owner.setId(newOwner.getId());
        owner.setName(newOwner.getName());
        owner.setDateOfBirth(newOwner.getDateOfBirth());
        owner.setCats(newOwner.getCats());
        ownerDao.save(owner);

        return new ResponseEntity<>(newOwner, HttpStatus.ACCEPTED);
    }
}
