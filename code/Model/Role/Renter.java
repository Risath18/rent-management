package Model.Role;

import Model.Util.*;

public class Renter implements Person{
    private Date loginDate; //Date object for date of login
    private boolean notificationsOn; //boolean for whether notifications are on (true) or not
    private Name name; //Name object for renter's name
    private String email; //renter's email

    /**
     * getter for login date
     * @return returns Date object
     */
    public Date getLoginDate() {
        return loginDate;
    }

    /**
     * getter for whether notifications are on
     * @return returns boolean: (true) if notifications on, (false) if off
     */
    public boolean isNotificationsOn() {
        return notificationsOn;
    }

    /**
     * setter for whether notifications should be turned on
     * @param notificationsOn status to be set
     */
    public void setNotificationsOn(boolean notificationsOn) {
        this.notificationsOn = notificationsOn;
    }

    /**
     * setter for login date
     * @param loginDate Date object to be stored
     */
    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    /**
     * getter for Person object
     * @return returns this renter
     */
    public Person getPerson(){
        return this;
    }

    /**
     * getter for renter name
     * @return returns Name object
     */
    public Name getName(){
        return this.name;
    }
    
    /**
     * getter for renter email
     * @return returns renter's email as a String
     */
    public String getEmail(){
        return this.email;
    }

    /**
     * setter for renter name
     * @param name Name object to be stored
     */
    public void setName(Name name){
        this.name = name;
    }

    /**
     * setter for renter email
     * @param email String email to be stored 
     */
    public void setEmail(String email){
        this.email = email;
    }
}
