package com.rent.management.app.Exceptions;

public class IllegalPropertyStatusException extends IllegalArgumentException{
    /**
     * Exception constructor
     */
    public IllegalPropertyStatusException(){
        super("Incorrect Property Status Selected");
    }
}