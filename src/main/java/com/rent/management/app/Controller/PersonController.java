package com.rent.management.app.Controller;

import com.rent.management.app.Model.*;
import com.rent.management.app.Model.Role.*;
import com.rent.management.app.Model.Util.Name;

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

    public void setManager(String data){
        String myName = data.split("[:]")[1];
        Name name = new Name(myName.split("[ ]")[0], myName.split("[ ]")[1], myName.split("[ ]")[2]);
        this.person = new Manager(name, data.split("[:]")[0]);
    }

    public void setRenter(String fName, String mName, String lName, String email, boolean notificationsOn){
        Name name = new Name(fName, mName, lName);
        this.person = new Renter(name, email, notificationsOn, null);
    }
    
    public Person getPerson(){
        return person;
    }

}
