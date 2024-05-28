package org.example.exceptions;

public class UnknownCat extends Exception {
    public UnknownCat() {

    }
    public UnknownCat(String message) {
        super(message);
    }

}
