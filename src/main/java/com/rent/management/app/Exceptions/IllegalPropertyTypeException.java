package com.rent.management.app.Exceptions;

public class IllegalPropertyTypeException extends IllegalArgumentException{
    /**
     * Exception constructor
     */
    public IllegalPropertyTypeException(){
        super("Incorrect Property Type Selected");
    }
}