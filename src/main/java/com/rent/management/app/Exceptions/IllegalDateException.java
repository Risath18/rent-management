package com.rent.management.app.Exceptions;

public class IllegalDateException extends IllegalArgumentException{
    /**
     * Exception constructor
     */
    public IllegalDateException(){
        super("Incorrect Date Selected");
    }
}