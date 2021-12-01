package Controller;

import java.util.*;

import Model.Role.Landlord;
import Model.Role.Manager;
import Model.Role.Person;

public class ControllerCore {

    Person person;
    ArrayList<Property> properties;

    public ControllerCore(){
        System.out.println("Controller Made!");
        this.properties = new ArrayList<Property>();
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

    public String getPropertyAddress() {
       return properties.get(properties.size()-1).getAddress();
    }
    
}
