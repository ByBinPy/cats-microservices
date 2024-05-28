package org.example.exceptions;

public class SaveExistCat extends Exception {
    public SaveExistCat() {
    }

    public SaveExistCat(String message) {
        super(message);
    }
}
