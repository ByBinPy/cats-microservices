package org.example.controllers;

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
    public ResponseEntity<?> getCatById(@RequestParam Integer ownerId) {
        return ownerService.getById(ownerId);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveOwner(@RequestBody OwnerDto ownerDto) {
        return ownerService.save(ownerDto);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteOwner(@PathVariable("id") Integer ownerId) {
        ownerService.delete(ownerId);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOwner(@PathVariable("id") Integer id, @RequestBody OwnerDto ownerDto){
        return ownerService.update(id, ownerDto);
    }
}