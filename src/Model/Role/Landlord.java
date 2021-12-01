package Model.Role;

import Model.Util.*;

public class Landlord implements Person{
    private Name name; //landlord name from Person
    private String email; //email from Person

    /**
     * default constructor
     */
    public Landlord(){}

    /**
     * constructor for Landlord
     * @param name Name object to be stored
     * @param email String for email to be stored
     */
    public Landlord(Name name, String email){
        setName(name);
        setEmail(email);
    }
    
    /**
     * changes the property status
     * @param p Property object argument to be changed
     * @param ps PropertyStatus object to be changed to
     */
    public void changePropertyStatus(Property p, PropertyStatus ps){
        p.setPropertyStatus(ps);
    }

    /**
     * getter for person
     * @return returns a Person object
     */
    public Person getPerson(){
        return this;
    }

    /**
     * getter for landlord name
     * @return returns Name object
     */
    public Name getName(){
        return this.name;
    }
    
    /**
     * getter for landlord email
     * @return returns String for email
     */
    public String getEmail(){
        return this.email;
    }

    /**
     * setter for landlord name
     * @param name Name object to be stored
     */
    public void setName(Name name){
        this.name = name;
    }

    /**
     * setter for email
     * @param email String argument to be stored
     */
    public void setEmail(String email){
        this.email = email;
    }
    
    /**
     * getter for all properties owned by this landlord
     * @return returns an ArrayList of properties
     */
    // public ArrayList<Property> getLandlordProperties(){

    // }

}
