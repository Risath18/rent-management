package com.rent.management.app.Controller;

import com.rent.management.app.Model.*;
import com.rent.management.app.Model.Role.*;

public class PersonController {
    Person person;

    public PersonController(){ }

    public void setLandlord(String fName, String mName, String lName, String email){
        Name name = new Name(fName, mName, lName);
        this.person = new Landlord(name, email);
    }

    public void setManager(String fName, String mName, String lName, String email){
        Name name = new Name(fName, mName, lName);
        this.person = new Manager(name, email);
    }

    public void setRenter(String fName, String mName, String lName, String email, boolean notificationsOn){
        Name name = new Name(fName, mName, lName);
        this.person = new Renter(name, email, notificationsOn, null);
    }
    
    public Person getPerson(){
        return Person;
    }

}
