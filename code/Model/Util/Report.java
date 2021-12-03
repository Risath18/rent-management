package Model.Util;

import Model.Property.Period;

public class Report {
    private Period period; //period of report
    private int totalHousesListed; //total number of houses listed
    private int totalHousesRented; //total numebr of houses rented
    private int totalActiveListing; //total number of active listings

    /**
     * Constructor for Report
     * @param period Period object to be stored
     * @param totalHousesListed 
     * @param totalHousesRented
     * @param totalActiveListing
     */
    public Report(Period period, int totalHousesListed, int totalHousesRented, int totalActiveListing) {
        this.setPeriod(period);
        this.setTotalHousesListed(totalHousesListed);
        this.setTotalHousesRented(totalHousesRented);
        this.setTotalActiveListing(totalActiveListing);
    }
    
    /**
     * getter for total active listings
     * @return returns int of how many listings were active in the period
     */
    public int getTotalActiveListing() {
        return totalActiveListing;
    }

    /**
     * setter for total active listings
     * @param totalActiveListing int for how many listings are active in the period
     */
    public void setTotalActiveListing(int totalActiveListing) {
        this.totalActiveListing = totalActiveListing;
    }

    /**
     * getter for total active rented
     * @return returns int of how many houses were rented in the period
     */
    public int getTotalHousesRented() {
        return totalHousesRented;
    }

    /**
     * setter for total houses rented
     * @param totalActiveListing int for how many houses are rented in the period
     */
    public void setTotalHousesRented(int totalHousesRented) {
        this.totalHousesRented = totalHousesRented;
    }

    /**
     * getter for total houses listed
     * @return returns int of how many houses were listed in the period
     */
    public int getTotalHousesListed() {
        return totalHousesListed;
    }

    /**
     * setter for total houses listed
     * @param totalActiveListing int for how many houses are listed in the period
     */
    public void setTotalHousesListed(int totalHousesListed) {
        this.totalHousesListed = totalHousesListed;
    }

    /**
     * getter for period
     * @return returns Period object
     */
    public Period getPeriod() {
        return period;
    }

    /**
     * setter for period
     * @param period Period object to be stored
     */
    public void setPeriod(Period period) {
        this.period = period;
    }

    /**
     * getter for formatted report
     * @return returns a String with the report properly formatted to read
     */
    public String getFormattedReport(){
        return period + ":" + totalHousesListed + ":" + totalHousesRented  + ":" + totalActiveListing;
    }

    //INCOMPLETE, PARSE DATA
    /**
     * setReport creates a report
     * @param data String argument being read in that will need to be parsed into a report
     */
    public void setReport(String data){

    }
}
