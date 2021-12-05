package com.rent.management.app.Model.Util;

import java.util.UUID;

import com.rent.management.app.Model.Property.CityQuadrant;
import com.rent.management.app.Model.Property.PropertyType;

import org.json.simple.JSONObject;

public class SearchCriteria {
    private String id;
    private PropertyType propertyType; //type of property from enum
    private int numOfBed; //number of bedrooms
    private int numOfBath; //number of bathrooms
    private boolean isFurnished; //whether property is furnished (true) or not (false)
    private CityQuadrant cityQuadrant; //quadrant of the city being searched

    /**
     * Default constructor
     */
    public SearchCriteria(){}
    
    /**
     * Constructor for search critera
     * @param propertyType PropertyType object to be stored
     * @param numOfBed int argument for number of bedrooms to be stored
     * @param numOfBath int argument for number of bathrooms to be stored
     * @param isFurnished boolean argument if property furnished
     * @param cityQuadrant CityQuadrant object to be stored
     */
    public SearchCriteria(PropertyType propertyType, int numOfBed, int numOfBath, boolean isFurnished,
            CityQuadrant cityQuadrant) {
        this.id = UUID.randomUUID().toString();
        this.setPropertyType(propertyType);
        this.setNumOfBed(numOfBed);
        this.setNumOfBath(numOfBath);
        this.setFurnished(isFurnished);
        this.setCityQuadrant(cityQuadrant);
    }

    public SearchCriteria(JSONObject obj){
        try{
        this.id=obj.get("search").toString();
        this.setPropertyType(PropertyType.fromString(obj.get("type").toString()));
        this.setNumOfBed(Integer.parseInt(obj.get("num_bed").toString()));
        this.setNumOfBath(Integer.parseInt(obj.get("num_bath").toString()));
        this.setFurnished(Boolean.parseBoolean(obj.get("furnished").toString()));
        this.setCityQuadrant(CityQuadrant.fromString(obj.get("quadrant").toString()));

        } catch(Exception e){
            System.err.println(e);
            e.printStackTrace();
            return;
        }
    }

    /**
     * getter for Property type
     * @return returns PropertyType object
     */
    public PropertyType getPropertyType() {
        return propertyType;
    }

    /**
     * setter for Property type
     * @param propertyType PropertyType object to be stored
     */
    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
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
     * getter for city quadrant
     * @return returns CityQuadrant object
     */
    public CityQuadrant getCityQuadrant() {
        return cityQuadrant;
    }

    /**
     * setter for city quadrant
     * @param cityQuadrant CityQuadrant object to be stored
     */
    public void setCityQuadrant(CityQuadrant cityQuadrant) {
        this.cityQuadrant = cityQuadrant;
    }

    /**
     * getter for formatted search
     * @return returns a String with the search properly formatted to read
     */
    public String getFormattedSearch(){
        return propertyType + ":" + numOfBed + ":" + numOfBath + ":" + isFurnished + ":" + cityQuadrant;
    }

    //INCOMPLETE, PARSE DATA
    /**
     * setSearchCriteria creates a search
     * @param data String argument being read in that will need to be parsed into a search
     */
    public void setSearchCriteria(String data){


    }

}
