package com.rent.management.app.Exceptions;

public class TimeException extends IllegalArgumentException{
    /**
     * Exception constructor
     */
    public TimeException(){
        super("Please enter a valid time.");
    }
}