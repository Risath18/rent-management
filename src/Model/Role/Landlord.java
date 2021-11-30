package Model.Role;

import java.util.ArrayList;

import Model.Property.*;
import Model.Util.*;

public class Landlord implements Person{
    private Name name;
    private String email;

    public Landlord(){}

    public Landlord(Name name, String email){
        setName(name);
        setEmail(email);
    }
    
    public void changePropertyStatus(Property p, PropertyStatus ps){
        p.setPropertyStatus(ps);
    }

    public Person getPerson(){
        return this;
    }

    public Name getName(){
        return this.name;
    }
    
    public String getEmail(){
        return this.email;
    }

    public void setName(Name name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }
    
    // public ArrayList<Property> getLandlordProperties(){

    // }

}
