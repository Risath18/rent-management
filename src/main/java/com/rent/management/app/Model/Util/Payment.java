package com.rent.management.app.Model.Util;

import com.rent.management.app.Model.Property.Period;

public class Payment {
    private int price, days;
    private boolean isPaid;
    private Period period;

    /**
     * Constructor for payment
     */
    public Payment(){
        this.period = new Period();
    }

    /**
     * Chackes whether or not a payement has been made.
     * @return bool value of paid status
     */
    public boolean isPaid() {
        return isPaid;
    }

    /**
     * setter for isPaid.
     * @param isPaid boolean value with paid status
     */
    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    /**
     * Constructor for Payment
     * @param days int for days of payment validity
     * @param price int for fee amount
     */
    public Payment(int days, int price, Period period, boolean isPaid) {
        this.setDays(days);
        this.setPrice(price);
        this.setPaid(isPaid);
        this.setPeriod(period);
    }

    /**
     * setter for days
     * @param days number of days in fee period
     */
    public void setDays(int days){
        this.days = days;
    }

    /**
     * setter for fee
     * @param price cost of fees
     */
    public void setPrice(int price){
        this.price = price;
    }
    
    /**
     * getter for days
     * @return number of days in fee period
     */
    public int getDays(){
        return this.days;
    }

    /**
     * getter for fees
     * @return cost of fee
     */
    public int getPrice(){
        return this.price;
    }

    /**
     * Getter for period
     * @return period of payement
     */
    public Period getPeriod(){
        return this.period;
    }

    /**
     * Setter for period.
     * @param period period to be set
     */
    public void setPeriod(Period period){
        this.period = period;
    }
}
