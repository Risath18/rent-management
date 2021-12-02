package Controller;

import java.util.*;
import View.ViewCore;

import Model.Property.Address;
import Model.Property.Property;
import Model.Role.Landlord;
import Model.Role.Manager;
import Model.Role.Person;
import Controller.DBCore;

public class ControllerCore {
    private ViewCore view;
    Person person;
    ArrayList<Property> properties;
    DBCore database;

    public ControllerCore(){
        System.out.println("Controller Made!");
        this.properties = new ArrayList<Property>();
    }

    /**
     * user sends in an email and password. The function checks if this exists in the database and returns a boolean
     * whether successful or unsuccessful
     * @param email email to login with
     * @param password password to login with
     * @return status of the login
     */
    public boolean login(String email, String password){

    }

    public void setLandlord(){
        this.person = new Landlord("Apple", 5);
        person.printString();
    }

    public void setManager(){
        this.person = new Manager("Tree", 5);
        person.printString();
    }

    public void createProperty() {
        Property newProperty = new Property("ICT", "University Drive", "ACTIVE");
        properties.add(newProperty);
    }

    public Address getPropertyAddress() {
       return properties.get(properties.size()-1).getAddress();
    }
    
}
