package com.rent.management.app.Model.Property;

import com.rent.management.app.Model.Util.Payment;

public class Property {
    private String propertyId; //unique ID for property
    private PropertyType propertyType; //enum value for the type of property
    private int numOfBed; //number of bedrooms in property
    private int numOfBath; //number of bathrooms in property
    private boolean isFurnished; //whether property is furnished (true) or not (false)
    private PropertyStatus propertyStatus; //enum value for the status of the property
    private Address address; //address of property
    private Payment payment; //payment for propert
    private String email;
    
    /**
     * default constructor for property
     */
    public Property(){
        
    }

    /**
     * Constructor for property
     * @param propertyType PropertyType object to be stored
     * @param numOfBed int argument for number of bedrooms to be stored
     * @param numOfBath int argument for number of bathrooms to be stored
     * @param isFurnished boolean argument if property furnished
     * @param propertyID int ID of property
     * @param propertyStatus PropertyStatus object to be stored
     * @param address Address object to be stored
     */
    public Property(String propertyId, PropertyType propertyType, int numOfBed, int numOfBath, boolean isFurnished, Address address, PropertyStatus propertyStatus) {
        this.setPropertyType(propertyType);
        this.setNumOfBed(numOfBed);
        this.setNumOfBath(numOfBath);
        this.setFurnished(isFurnished);
        this.setPropertyId(propertyId);
        this.setPropertyStatus(propertyStatus);
        this.setAddress(address);
    }

    /**
     * getter for formatted property
     * @return returns a String with the property properly formatted to read
     */
    public String getFormattedProperty(){
        return propertyId + ":" + numOfBed + ":" + numOfBath + ":" + isFurnished + ":" + propertyType + 
            ":" + propertyStatus + ":" + address.getFormattedAddress();
    }

    /**
     * setter for property based on an incoming string
     * @param data String argument for data to be parsed
     */
    public void setProperty(String data){
        this.propertyId = data.split("[:]")[0];
        this.propertyType = PropertyType.fromString(data.split("[:]")[1]);
        this.numOfBed = Integer.parseInt(data.split("[:]")[2]);
        this.numOfBath = Integer.parseInt(data.split("[:]")[3]);
        this.isFurnished = Boolean.parseBoolean(data.split("[:]")[4]);
        this.propertyStatus = PropertyStatus.fromString(data.split("[:]")[5]);
        this.address = Address.makeAddress(data.split("[:]")[6]);
    }

    /**
     * getter for Address
     * @return returns Address object
     */
    public Address getAddress() {
        return address;
    }

    /**
     * setter for Address
     * @param address Address object to be stored
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * getter for Property status
     * @return returns PropertyStatus object
     */
    public PropertyStatus getPropertyStatus() {
        return propertyStatus;
    }

    /**
     * setter for Property status
     * @param propertyStatus PropertyStatus object to be stored
     */
    public void setPropertyStatus(PropertyStatus propertyStatus) {
        this.propertyStatus = propertyStatus;
    }

    /**
     * getter for property ID
     * @return returns int corresponding to property ID
     */
    public String getPropertyId() {
        return propertyId;
    }

    /**
     * setter for property ID
     * @param propertyID int argument for property ID to be stored
     */
    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    /**
     * getter for furnished status
     * @return returns boolean of whether the property is furnished
     */
    public boolean isFurnished() {
        return isFurnished;
    }

    /**
     * setter for furnished status
     * @param isFurnished boolean argument for furnished status
     */
    public void setFurnished(boolean isFurnished) {
        this.isFurnished = isFurnished;
    }

    /**
     * getter for number of bathrooms
     * @return returns int of number of bathrooms
     */
    public int getNumOfBath() {
        return numOfBath;
    }

    /**
     * setter for number of bathrooms
     * @param numOfBath int of number of bathrooms to be stored
     */
    public void setNumOfBath(int numOfBath) {
        this.numOfBath = numOfBath;
    }

    /**
     * getter for number of bedrooms
     * @return returns int of number of bedrooms
     */
    public int getNumOfBed() {
        return numOfBed;
    }

    /**
     * setter for number of bedrooms
     * @param numOfBed int of number of bedrooms to be stored
     */
    public void setNumOfBed(int numOfBed) {
        this.numOfBed = numOfBed;
    }

    /**
     * getter for property type
     * @return returns PropertyType object
     */
    public PropertyType getPropertyType() {
        return propertyType;
    }

    /**
     * setter for property type
     * @param propertyType object of PropertyType to be stored
     */
    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    /**
     * function to set payment
     * @param payment Payment object
     */
    public void setPayment(Payment payment){
        this.payment = payment;
    }

    /**
     * getter for Payment
     * @return Payment object 
     */
    public Payment getPayment(){
        return this.payment;
    }

    /**
     * getter for email
     */
    public String getEmail(){
        return this.email;
    }

    /**
     * setter for email
     * @param email String argument for email
     */
    public void setEmail(String email){
        this.email = email;
    }
}


