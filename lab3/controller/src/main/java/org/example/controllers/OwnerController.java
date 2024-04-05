package org.example.controllers;

import org.example.exceptions.UnknownOwner;
import org.example.impl.dto.OwnerDto;
import org.example.impl.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner")
public class OwnerController {
    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/find")
    public ResponseEntity<?> getCatById(@RequestParam Integer catId) throws UnknownOwner {
        return ownerService.getById(catId);
    }

    @PostMapping("/save")
    public ResponseEntity<OwnerDto> saveOwner(){

    }
}