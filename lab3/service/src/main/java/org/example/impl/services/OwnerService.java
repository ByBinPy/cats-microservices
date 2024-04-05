package org.example.impl.services;

import org.example.declarations.OwnerDao;
import org.example.exceptions.UnknownOwner;
import org.example.impl.dto.OwnerDto;
import org.example.implementations.entities.Cat;
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

    public ResponseEntity<?> getById(Integer id) throws UnknownOwner {
        Optional<Owner> ownerOptional = ownerDao.findById(id);
        if (ownerOptional.isEmpty())

            return new ResponseEntity<>(new UnknownOwner(), HttpStatus.NOT_FOUND)

        Owner owner = ownerOptional.get();
        OwnerDto ownerDto = new OwnerDto();
        ownerDto.setId(owner.getId());
        ownerDto.setName(owner.getName());
        ownerDto.setDateOfBirth(owner.getDateOfBirth());
        ownerDto.setCats(owner.getCats());

        return new ResponseEntity<>(ownerDto, HttpStatus.OK);
    }
}
