package org.example.exceptions;

public class SaveExistOwner extends Exception{
    public SaveExistOwner(){
    }
    public  SaveExistOwner(String message) {
        super(message);
    }
}
