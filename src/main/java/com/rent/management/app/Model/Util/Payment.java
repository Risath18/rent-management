package com.rent.management.app.Model.Util;


public class Payment {
    private Date listingExpiryDate; //date that listing expires

    /**
     * Constructor for Payment
     * @param listingExpiryDate Date object to be stored as the expiry date of the listing
     */
    public Payment(Date listingExpiryDate) {
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
}
