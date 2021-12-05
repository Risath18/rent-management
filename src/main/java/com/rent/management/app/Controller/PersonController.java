package com.rent.management.app.Controller;

import com.rent.management.app.Model.*;
import com.rent.management.app.Model.Role.*;
import com.rent.management.app.Model.Util.Name;
import com.rent.management.app.Model.Util.SearchCriteria;

import org.json.simple.JSONObject;

public class PersonController {
    Person person;

    public PersonController(){ }

    public void setLandlord(String fName, String lName, String email){
        Name name = new Name(fName, lName);
        this.person = new Landlord(name, email);
    }

    public void setLandlord(JSONObject obj){
        String myName = obj.get("Name").toString();
        Name name = new Name(myName.split("[ ]")[0], myName.split("[ ]")[1]);
        this.person = new Landlord(name, obj.get("Email").toString());
    }

    public void setManager(String fName, String lName, String email){
        Name name = new Name(fName, lName);
        this.person = new Manager(name, email);
    }

    public void setManager(JSONObject obj){
        String myName = obj.get("Name").toString();
        Name name = new Name(myName.split("[ ]")[0], myName.split("[ ]")[1]);
        this.person = new Manager(name, obj.get("Email").toString());
    }

    public void setRenter(String fName, String lName, String email, boolean notificationsOn){
        Name name = new Name(fName, lName);
        this.person = new Renter(name, email, notificationsOn, null);
    }

    public void setRenter(JSONObject obj){
        String myName = obj.get("Name").toString();
        Name name = new Name(myName.split("[ ]")[0], myName.split("[ ]")[1]);
        boolean notificationsOn = Boolean.parseBoolean(obj.get("Notifications_on").toString());
        SearchCriteria searchCriteria = new SearchCriteria(obj);
        this.person = new Renter(name, obj.get("Email").toString(), notificationsOn, searchCriteria);
    }
    
    public Person getPerson(){
        return person;
    }

}
