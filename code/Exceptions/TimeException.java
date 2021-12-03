package Exceptions;


public class TimeException extends IllegalArgumentException{
    public TimeException(){
        super("Please enter a valid time.");
    }
}