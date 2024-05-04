package org.example.controllers;

import org.example.impl.dto.CatDto;
import org.example.impl.dto.SecurityOwner;
import org.example.impl.services.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cat")
public class CatController {
    private final CatService catService;
    @Autowired
    public CatController(CatService catService) {
        this.catService = catService;
    }

    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/find")
    public ResponseEntity<?> getCatById(@RequestParam Integer catId, @AuthenticationPrincipal SecurityOwner securityOwner) {
        return catService.getById(catId, securityOwner.getId());
    }

    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/filter/color")
    public ResponseEntity<?> getCatsByColor(@RequestParam String color, @AuthenticationPrincipal SecurityOwner securityOwner) {

        return catService.getByColor(color, securityOwner.getId());
    }
    @PreAuthorize("hasAuthority('WRITE')")
    @PostMapping("/save")
    public ResponseEntity<?> saveCat(@RequestBody CatDto catDto) {
        return catService.save(catDto);
    }
    @PreAuthorize("hasAuthority('WRITE')")
    @DeleteMapping("/delete/{id}")
    public void deleteCat(@PathVariable("id") Integer catId) {
        catService.delete(catId);
    }

    @PreAuthorize("hasAuthority('WRITE')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCat(@PathVariable("id") Integer id, @RequestBody CatDto catDto){
        return catService.update(id, catDto);
    }
}
