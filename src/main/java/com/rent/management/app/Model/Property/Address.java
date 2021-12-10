package com.rent.management.app.Model.Property;


public class Address {
    private CityQuadrant cityQuadrant; //quadrant of the city, corresponds to an enum
    private String address; //String for address

    /**
     * Default constructor for address
     */
    public Address(){
    }

    /**
     * constructor for address
     * @param address String for address to be stored
     * @param cityQuadrant city quadrant to be stored
     */
    public Address(String address, CityQuadrant cityQuadrant){
        this.address = address;
        this.cityQuadrant = cityQuadrant;
    }

    /**
     * getter for address 
     * @return returns a String of an address
     */
    public String getFormattedAddress(){
        return this.address;
    }

    /**
     * setter for address 
     * @param address String for address
     */
    public void setAddress(String address){
        this.address = address;
    }
    
    /**
     * getter for city quadrant
     * @return returns an enum value for the city quadrant (NW, NE, SW, SE)
     */
    public CityQuadrant getCityQuadrant() {
        return cityQuadrant;
    }

    /**
     * setter for city quadrant
     * @param cityQuadrant one of (NW, NE, SW, SE) corresponding to the quadrant of the city that the property is located
     */
    public void setCityQuadrant(CityQuadrant cityQuadrant) {
        this.cityQuadrant = cityQuadrant;
    }

    /**
     * makeAddress creates an address and stores it in the database????
     * @param data String argument being read in that will need to be parsed into an address
     * @return returns an Address object
     */
    public static Address makeAddress(String data){
        Address ad = new Address();
        return ad;
    }
    
}
