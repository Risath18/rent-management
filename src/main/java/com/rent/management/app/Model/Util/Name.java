package com.rent.management.app.Model.Util;


public class Name {
    private String fName; //first name
    private String lName; //last name

    /**
     * Constructor for name
     * @param fName String argument for first name
     * @param mName String argument for middle name
     * @param lName String argument for last name
     */
    public Name(String fName, String lName) {
        this.setfName(fName);
        this.setlName(lName);
    }

    /**
     * getter for last name
     * @return returns string of last name
     */
    public String getlName() {
        return lName;
    }

    /**
     * setter for last name
     * @param lName String argument to be stored
     */
    public void setlName(String lName) {
        this.lName = lName;
    }

    /**
     * getter for first name
     * @return returns string of first name
     */
    public String getfName() {
        return fName;
    }

     /**
     * setter for first name
     * @param fName String argument to be stored
     */
    public void setfName(String fName) {
        this.fName = fName;
    }

    /**
     * getter for formatted name
     * @return returns a String with the name properly formatted to read
     */
    public String getFormattedName(){
        return fName  + " " + lName;
    }
}

