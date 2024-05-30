package org.example.controllers;

import org.example.exceptions.SaveExistOwner;
import org.example.exceptions.UnknownOwner;
import org.example.impl.dto.OwnerDto;
import org.example.impl.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner")
public class OwnerController {
    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }
    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/find")
    public ResponseEntity<?> getCatById(@RequestParam Integer ownerId) throws UnknownOwner {
        return ownerService.getById(ownerId);
    }
    @PreAuthorize("hasAnyAuthority('WRITE', 'READ')")
    @PostMapping("/save")
    public ResponseEntity<?> saveOwner(@RequestBody OwnerDto ownerDto) throws SaveExistOwner {
        return ownerService.save(ownerDto);
    }
    @PreAuthorize("hasAnyAuthority('WRITE', 'READ')")
    @DeleteMapping("/delete/{id}")
    public void deleteOwner(@PathVariable("id") Integer ownerId) {
        ownerService.delete(ownerId);
    }
    @PreAuthorize("hasAnyAuthority('WRITE', 'READ')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOwner(@PathVariable("id") Integer id, @RequestBody OwnerDto ownerDto) throws UnknownOwner {
        return ownerService.update(id, ownerDto);
    }
}