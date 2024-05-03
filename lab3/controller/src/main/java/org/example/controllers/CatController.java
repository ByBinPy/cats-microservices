package org.example.controllers;

import org.example.exceptions.UnknownCat;
import org.example.impl.dto.CatDto;
import org.example.impl.services.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public CatDto getCatById(@RequestParam Integer catId) throws UnknownCat {
        return catService.getById(catId);
    }

    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/filter/color")
    public ResponseEntity<?> getCatsByColor(@RequestParam String color) {
        return catService.getByColor(color);
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
