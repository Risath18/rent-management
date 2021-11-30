package Exceptions;

public class IllegalPropertyStatusException extends IllegalArgumentException{
    public IllegalPropertyStatusException(){
        super("Incorrect Property Status Selected");
    }
}