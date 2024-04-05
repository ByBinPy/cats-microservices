package org.example.controllers;

import org.example.exceptions.UnknownCat;
import org.example.impl.dto.CatDto;
import org.example.impl.dto.OwnerDto;
import org.example.impl.services.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cat")
public class CatController {
    private final CatService catService;

    @Autowired
    public CatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("/find")
    public CatDto getCatById(@RequestParam Integer catId) throws UnknownCat {
        return catService.getById(catId);
    }
    @PostMapping("/save")
    public ResponseEntity<?> saveCat(@RequestBody CatDto catDto) {
        return catService.save(catDto);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteCat(@PathVariable("id") Integer catId) {
        catService.delete(catId);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCat(@PathVariable("id") Integer id, @RequestBody CatDto catDto){
        return catService.update(id, catDto);
    }
}
