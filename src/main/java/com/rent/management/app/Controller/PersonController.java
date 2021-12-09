package com.rent.management.app.Controller;

import com.rent.management.app.Model.*;
import com.rent.management.app.Model.Role.*;
import com.rent.management.app.Model.Util.Name;
import com.rent.management.app.Model.Util.SearchCriteria;

import org.json.simple.JSONObject;

public class PersonController {
    Person person;
    DBCore db;

    /**
     * Constructor for person controller
     * @param db DBCore database connection
     */
    public PersonController(DBCore db){ 
        this.db = db;
    }

    /**
     * setter for landlord
     * @param fName landlord's first name
     * @param lName landlord's last name
     * @param email landlord's email
     */
    public void setLandlord(String fName, String lName, String email){
        Name name = new Name(fName, lName);
        this.person = new Landlord(name, email);
    }

    /**
     * setter for landlord
     * @param obj JSONObject with all landlord information
     */
    public void setLandlord(JSONObject obj){
        String myName = obj.get("Name").toString();
        Name name = new Name(myName.split("[ ]")[0], myName.split("[ ]")[1]);
        this.person = new Landlord(name, obj.get("Email").toString());
    }

    /**
     * setter for manager
     * @param fName manager's first name
     * @param lName manager's last name
     * @param email manager's email
     */
    public void setManager(String fName, String lName, String email){
        Name name = new Name(fName, lName);
        this.person = new Manager(name, email);
    }

    /**
     * setter for manager 
     * @param obj JSONObject containing all manager information
     */
    public void setManager(JSONObject obj){
        String myName = obj.get("Name").toString();
        Name name = new Name(myName.split("[ ]")[0], myName.split("[ ]")[1]);
        this.person = new Manager(name, obj.get("Email").toString());
    }

    /**
     * setter for renter
     * @param fName renter's first name
     * @param lName renter's last name
     * @param email renter's email
     * @param notificationsOn renter's notification preference
     */
    public void setRenter(String fName, String lName, String email, boolean notificationsOn){
        Name name = new Name(fName, lName);
        this.person = new Renter(name, email, notificationsOn, null);
    }

    /**
     * setter for renter
     * @param obj JSONObject with all renter information
     */
    public void setRenter(JSONObject obj){
        String myName = obj.get("Name").toString();
        Name name = new Name(myName.split("[ ]")[0], myName.split("[ ]")[1]);
        boolean notificationsOn = Boolean.parseBoolean(obj.get("Notifications_on").toString());
        SearchCriteria searchCriteria = new SearchCriteria(obj);
        this.person = new Renter(name, obj.get("Email").toString(), notificationsOn, searchCriteria);
    }
    
    /**
     * Getter for person object
     * @return person
     */
    public Person getPerson(){
        return person;
    }

    /**
     * updates listing
     */
    public void updateListing(){
        String status = "RENTED";
        int pid = 0;
        db.changeListingStatus(pid, status);
    }

}
