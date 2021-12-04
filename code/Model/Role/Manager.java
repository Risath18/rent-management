package com.rent.management.app.Model.Role;

import com.rent.management.app.Model.Util.*;

public class Manager implements Person{
    private Name name; //manager name from Person
    private String email; //manager email from Person
    private Report report; //Report object

    /**
     * getter for report
     * @return returns formatted report
     */
    public Report getReport() {
        return report;
    }

    //potential issue here
    /**
     * setter for report
     * @param report Report object to be stored
     * @param manager Manager who report belongs to
     */
    public void setReport(Report report, Manager manager) {
        this.report = report;
    }

    /**
     * changes the status of a property
     * @return returns a boolean whether the change was successful
     */
    // public boolean changePropertyStatus(){

    // }

    /**
     * getter for Person object
     * @return returns a Person object
     */
    public Person getPerson(){
        return this;
    }

    /**
     * getter for Name object
     * @return returns Name object
     */
    public Name getName(){
        return this.name;
    }
    
    /**
     * getter for email
     * @return returns a String of the manager's email
     */
    public String getEmail(){
        return this.email;
    }

    /**
     * setter for name
     * @param name Name object to be stored
     */
    public void setName(Name name){
        this.name = name;
    }

    /**
     * setter for email
     * @param email String email to be stored
     */
    public void setEmail(String email){
        this.email = email;
    }
}
