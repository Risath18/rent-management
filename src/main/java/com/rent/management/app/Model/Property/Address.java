package com.rent.management.app.Model.Property;

public class Address {
    private String street; //street address
    private CityQuadrant cityQuadrant; //quadrant of the city, corresponds to an enum
    private int houseNumber; //house number

    /**
     * Default constructor for address
     */
    public Address(){
    }

    /**
     * Constructor for address
     * @param street String parameter corresponding to the street of Address
     * @param cityQuadrant cityQuadrant enum value corresponding to the quadrant of the city (NW, NE, SW, SE)
     * @param houseNumber int parameter corresponding to the house number of Address
     */
	public Address(String street, CityQuadrant cityQuadrant, int houseNumber) {
        this.setStreet(street);
        this.setCityQuadrant(cityQuadrant);
        this.setHouseNumber(houseNumber);
    }

    /**
     * getter for house number
     * @return returns int of house number
     */
    public int getHouseNumber() {
        return houseNumber;
    }

    /**
     * setter for house number
     * @param houseNumber int parameter corresponding to the house number of Address
     */
    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
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
     * getter for street
     * @return returns a String corresponding to the street of the address
     */
    public String getStreet() {
        return street;
    }

    /**
     * setter for street
     * @param street String argument for the street of the address
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * getter for formatted address
     * @return returns a String with the address properly formatted to read
     */
    public String getFormattedAddress() {
		return houseNumber + " " + street;
    }

    //INCOMPLETE, PARSE DATA
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
