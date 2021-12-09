package com.rent.management.app.Model.Property;

import com.rent.management.app.Model.Util.Date;

public class Period {
    private Date startDate; //start date of period
    private Date endDate; //end date of period

    /**
     * Default constructor for Period
     */
    public Period(){}
    
    /**
     * Constructor for Period
     * @param startDate Date object argument for the start date
     * @param endDate Date object argument for the end date
     */
    public Period(Date startDate, Date endDate) {
        this.setStartDate(startDate);
        this.setEndDate(endDate);
    }

    /**
     * getter for end date
     * @return returns a Date object of the end date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * setter for end date
     * @param Date object argument for the end date to be set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

     /**
     * getter for start date
     * @return returns a Date object of the start date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * setter for start date
     * @param Date object argument for the start date to be set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Setter for the start date
     * @param date date to be set. 
     */
    public void setStartDate(String date) {
        Date dt = new Date(Integer.parseInt(date.split("[-]")[0]), Integer.parseInt(date.split("[-]")[2]), Integer.parseInt(date.split("[-]")[1]));
        setStartDate(dt);
    }
    
    /**
     * setter for end date
     * @param date date to be set
     */
    public void setEndDate(String date){
        Date dt = new Date(Integer.parseInt(date.split("[-]")[0]), Integer.parseInt(date.split("[-]")[2]), Integer.parseInt(date.split("[-]")[1]));
        this.endDate = dt;
    }
    
    /**
     * function to send the period into a readable format
     * @return returns a String with the period in a readable format
     */
    public String getFormattedPeriod(){
        return startDate + ":" + endDate;
    }
}

