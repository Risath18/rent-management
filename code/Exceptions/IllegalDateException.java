package com.rent.management.app.Exceptions;
package Exceptions;

public class IllegalDateException extends IllegalArgumentException{
    public IllegalDateException(){
        super("Incorrect Date Selected");
    }
}