package com.rent.management.app.Controller;

import java.util.ArrayList;

import com.rent.management.app.Model.*;
import com.rent.management.app.Model.Property.*;
import com.rent.management.app.Model.Util.*;

import org.json.simple.JSONObject;

public class PropertyController {
    ArrayList<Property> properties;
    private DBCore db;

    public PropertyController(DBCore db){
        this.db = db;
    }
    
    public void setProperty(JSONObject obj){
        String propertyId = obj.get("pid").toString();
        PropertyType pt = PropertyType.fromString(obj.get("type").toString());
        int num_bed = Integer.parseInt(obj.get("num_bed").toString());
        int num_bath = Integer.parseInt(obj.get("num_bath").toString());
        boolean isFurnished = Boolean.parseBoolean(obj.get("furnished").toString());
        CityQuadrant qt = CityQuadrant.fromString(obj.get("type").toString());
        String adr = obj.get("address").toString();
        Address address = new Address(adr.substring(adr.indexOf(" ", 0), adr.length()-1), qt, adr.split("[ ]"[0]));
        PropertyStatus ps = PropertyStatus.fromString(obj.get("status"));
        Property property = new Property(pt, num_bed, num_bath, isFurnished, propertyId, ps, address);
        properties.add(property);
    }

    public Property getProperty(){
        return property;
    }
    

    public void editProperty(Property property){
        int pid = property.getPropertyId();
        String type = property.getPropertyType().getString();
        int numBed = property.getNumBed();
        int numBath = property.getNumBath();
        boolean isFurnished = property.isFurnished();
        String furnishedStatus;
        if(isFurnished)
            furnishedStatus = "Yes";
        else
            furnishedStatus = "No";
        String quadrant = property.getAddress().getCityQuadrant().toString();
        Address address = property.getAddress();
        Payment payment = property.getPayment();
        Date startDate = payment.getDatePaid();
        Date endDate = payment.getListingExpiryDate();
        db.updateProperty(pid, type, numBed, numBath, furnishedStatus, quadrant, address.getFormattedAddress(), );
    }

    public void updateListingStatus(){
        //also updates fee paid (possibly) and the dates
    }

    public String [][] getAllProperties() {
        // turn array list into 2D array for view
        // "Property Type", "Beds","Baths","Furnished","Status","Address"
        String [][] result = new String [properties.size()][6];
        for (int i = 0 < properties.size(); i++) {
            result[i][0] = properties[i].getPropertyType();
            result[i][1] = properties[i].getNumOfBed();
            result[i][2] = properties[i].getnumBath();
            result[i][3] = properties[i].isFurnished();
            result[i][4] = properties[i].getPropertyStatus();
            result[i][5] = properties[i].getAddress();
        }
        
        return result;
    }
    
    public void setAllProperties (JSONArray arr) {
        // use the setProperty method and loops to populate properties array list
        
    }
}
