package com.rent.management.app.Exceptions;

public class IllegalPropertyStatusException extends IllegalArgumentException{
    public IllegalPropertyStatusException(){
        super("Incorrect Property Status Selected");
    }
}