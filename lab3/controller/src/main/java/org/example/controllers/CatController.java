package org.example.controllers;

import org.example.exceptions.UnknownCat;
import org.example.impl.dto.CatDto;
import org.example.impl.services.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
