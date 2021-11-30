package Exceptions;

public class IllegalPropertyTypeException extends IllegalArgumentException{
    public IllegalPropertyTypeException(){
        super("Incorrect Property Type Selected");
    }
}