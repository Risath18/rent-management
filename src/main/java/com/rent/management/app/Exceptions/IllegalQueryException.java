package com.rent.management.app.Exceptions;

public class IllegalQueryException extends IllegalArgumentException{
    public IllegalQueryException(){
        super("Query has Failed! Check if Paramaters are accurate!");
    }
}