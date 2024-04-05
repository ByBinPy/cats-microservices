package org.example.exceptions;

public class UnknownOwner extends Exception {
    public UnknownOwner() {

    }

    public UnknownOwner(String message) {
        super(message);
    }
}
