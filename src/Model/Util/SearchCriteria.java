package Model.Util;

import Model.Property.*;

public class SearchCriteria {
    private PropertyType propertyType;
    private int numOfBed;
    private int numOfBath;
    private boolean isFurnished;
    private CityQuadrant cityQuadrant;

    public SearchCriteria(){}
    
    public SearchCriteria(PropertyType propertyType, int numOfBed, int numOfBath, boolean isFurnished,
            CityQuadrant cityQuadrant) {
        this.setPropertyType(propertyType);
        this.setNumOfBed(numOfBed);
        this.setNumOfBath(numOfBath);
        this.setFurnished(isFurnished);
        this.setCityQuadrant(cityQuadrant);
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public int getNumOfBed() {
        return numOfBed;
    }

    public void setNumOfBed(int numOfBed) {
        this.numOfBed = numOfBed;
    }

    public int getNumOfBath() {
        return numOfBath;
    }

    public void setNumOfBath(int numOfBath) {
        this.numOfBath = numOfBath;
    }

    public boolean isFurnished() {
        return isFurnished;
    }

    public void setFurnished(boolean isFurnished) {
        this.isFurnished = isFurnished;
    }

    public CityQuadrant getCityQuadrant() {
        return cityQuadrant;
    }

    public void setCityQuadrant(CityQuadrant cityQuadrant) {
        this.cityQuadrant = cityQuadrant;
    }

    public String getFormattedSearch(){
        return propertyType + ":" + numOfBed + ":" + numOfBath + ":" + isFurnished + ":" + cityQuadrant;
    }

    public void setSearchCriteria(String data){


    }

}
