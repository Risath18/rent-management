package com.rent.management.app.Model.Util;

import com.rent.management.app.Exceptions.IllegalDateException;

public class Date {
    private int month;
    private int day;
    private int year;

    /**
     * Default constructor
     */
    public Date(){}

    /**
     * Constructor for Date
     * @param year int for the year
     * @param month int for the month
     * @param day int for the day
     */
    public Date(int year, int month, int day){
        if(validateYear(year))
            setYear(year);
        if(validateMonth(month))
            setMonth(month);
        if(validateDay(day))
            setDay(day);
    }

    /**
     * getter for year
     * @return returns an int of the year
     */
    public int getYear() {
        return year;
    }

    /**
     * setter for year
     * @param year int argument for year to be stored
     */
    public void setYear(int year) {
        if(validateYear(year))
            this.year = year;
    }

    /**
     * getter for day
     * @return returns int of the day
     */
    public int getDay() {
        return day;
    }

    /**
     * setter for day
     * @param day int argument for day to be stored
     */
    public void setDay(int day) {
        if(validateDay(day))
            this.day = day;
    }

    /**
     * getter for month
     * @return returns int of the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * setter for month
     * @param month int argument for year to be stored
     */
    public void setMonth(int month) {
        if(validateMonth(month))
            this.month = month;
    }

    /**
     * getter for formatted date
     * @return returns a formatted date string
     */
    public String getFormattedDate(){
        return month + " " + day + "," + year;
    }

    /**
     * function that checks if the year is valid
     * @param year year to be validated
     * @throws IllegalDateException
     * @return returns boolean as to whether year is valid
     */
    private boolean validateYear(int year) throws IllegalDateException{
        if(year < 2000 || year > 2023){
            throw new IllegalDateException();
        }
        return true;
    }

    /**
     * function that checks if the month is valid
     * @param month month to be validated
     * @throws IllegalDateException
     * @return returns boolean as to whether month is valid
     */
    private boolean validateMonth(int month) throws IllegalDateException{
        if(month < 1 || month > 12){
            throw new IllegalDateException();
        }
        return true;
    }

    /**
     * function that checks if the day is valid. Month must be checked before the day in order to maintain correctness
     * @param day day to be validated
     * @throws IllegalDateException
     * @return returns boolean as to whether day is valid
     */
    private boolean validateDay(int day)throws IllegalDateException{
        if(day < 1)
            throw new IllegalDateException();
        if((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && day > 31){
            throw new IllegalDateException();
        } else if((month == 4 || month == 6 || month == 9 || month == 11) && day > 30){
            throw new IllegalDateException();
        } else if(month == 2 && day > 28)
            throw new IllegalDateException();
        return true;
    }
}
