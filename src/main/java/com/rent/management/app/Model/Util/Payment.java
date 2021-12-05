package com.rent.management.app.Model.Util;


public class Payment {
    private Date listingExpiryDate; //date that listing expires
    private Date datePaid;

    /**
     * Constructor for Payment
     * @param datePaid Date object for date paid
     * @param listingExpiryDate Date object to be stored as the expiry date of the listing
     */
    public Payment(Date datePaid, Date listingExpiryDate) {
        this.setDatePaid(datePaid);
        this.setListingExpiryDate(listingExpiryDate);
    }

    /**
     * getter for listing expiry date
     * @return returns a Date object
     */
    public Date getListingExpiryDate() {
        return listingExpiryDate;
    }

    /**
     * setter for listing expiry date
     * @param listingExpiryDate Date object to be stored
     */
    public void setListingExpiryDate(Date listingExpiryDate) {
        this.listingExpiryDate = listingExpiryDate;
    }

    /**
     * setter for date of payment
     * @param datePaid Date object for date of payment
     */
    public void setDatePaid(Date datePaid){
        this.datePaid = datePaid;
    }

    /**
     * getter for date paid
     * @return returns a Date object
     */
    public Date getDatePaid() {
        return datePaid;
    }

}
