package org.example.controllers;

import org.example.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler({
            UnknownCat.class,
            UnknownOwner.class,
            UnknownColor.class
            })
    public ResponseEntity<String> notFoundHandler(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({
            SaveExistCat.class,
            SaveExistOwner.class,
    })
    public ResponseEntity<String> alreadyExistHandler(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
}
