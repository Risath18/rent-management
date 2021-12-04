package  com.rent.management.app.Exceptions;

public class IllegalPropertyTypeException extends IllegalArgumentException{
    public IllegalPropertyTypeException(){
        super("Incorrect Property Type Selected");
    }
}