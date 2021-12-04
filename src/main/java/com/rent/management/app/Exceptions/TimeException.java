package com.rent.management.app.Exceptions;

public class TimeException extends IllegalArgumentException{
    public TimeException(){
        super("Please enter a valid time.");
    }
}