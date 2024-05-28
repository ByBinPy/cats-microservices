package org.example.exceptions;

public class UnknownColor extends Exception {
    public UnknownColor() {
    }

    public UnknownColor(String message) {
        super(message);
    }
}
