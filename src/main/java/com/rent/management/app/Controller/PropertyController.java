package com.rent.management.app.Controller;

import java.util.ArrayList;
import java.util.UUID;

import com.rent.management.app.Model.*;
import com.rent.management.app.Model.Property.*;
import com.rent.management.app.Model.Util.*;
import com.rent.management.app.View.Pages.AdminPage.LandlordView;
import com.rent.management.app.View.Pages.CreateEditPage.CreateListing;
import com.rent.management.app.View.Pages.Listing.RenterMenuView;
import com.rent.management.app.View.Pages.Listing.RenterPropView;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;

public class PropertyController implements ActionListener {
    ArrayList<Property> properties;
    private DBCore db;
    private RenterMenuView renterMenuView;
    private RenterPropView renterPropView;
    
    
    public PropertyController(DBCore db){
        this.db = db;
        properties = new ArrayList<Property>();
        setAllProperties(db.getAllProperties());
        renterMenuView=new RenterMenuView();
        renterMenuView.setVisible(true);
        addListernersToClass();
    }

    void setSearchMetrics(){

        //Call Getters of Search
        String type = "NULL";
        int num_bed = 0;
        int num_bath = 0;
        String furnished= "No";
        String quadrant= "NULL";

        //Based on Search Metrics
        setAllProperties(db.search(type, num_bed, num_bath, furnished, quadrant));
    }

    private void addListernersToClass(){
        renterMenuView.searchListener(this);
        renterMenuView.exitListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("exit")){
            System.exit(1);
        }
        else if(e.getActionCommand().equals("search")){
            //Searching code if we wanna have this 
        }
        else if(e.getActionCommand().equals("send email")){
            //Sending email logic
        }
        renterPropView =new RenterPropView (getAllProperties());
    }

    

    public static Property generateProperty(JSONObject obj){
        String propertyId = obj.get("pid").toString();
        PropertyType pt = PropertyType.fromString(obj.get("type").toString());
        int num_bed = Integer.parseInt(obj.get("num_bed").toString());
        int num_bath = Integer.parseInt(obj.get("num_bath").toString());
        boolean isFurnished = Boolean.parseBoolean(obj.get("furnished").toString());
        CityQuadrant qt = CityQuadrant.fromString(obj.get("type").toString());
        String adr = obj.get("address").toString();
        
        Address address = new Address(adr.substring(adr.indexOf(" ", 0), adr.length()-1), qt, Integer.parseInt(adr.split("[ ]")[0]));
        PropertyStatus ps = PropertyStatus.fromString(obj.get("status").toString());
        boolean paid;
        String paidFee = obj.get("fee_paid").toString();
        if(paidFee.toLowerCase().equals("yes")){
            paid = true;
        }
        else{
            paid = false;
        }
        Property property = new Property(propertyId, pt, num_bed, num_bath, isFurnished, address, ps);
        return property;
    }
    
    public void setProperty(JSONObject obj){
        properties.add(generateProperty(obj));
    }

    public void setProperty(Property property){
        properties.add(property);
    }

    public ArrayList<Property> getProperty(){
        return properties;
    }
    

    public void editProperty(Property property){
        String pid = property.getPropertyId();
        String type = property.getPropertyType().toString();
        int numBed = property.getNumOfBed();
        int numBath = property.getNumOfBath();
        boolean isFurnished = property.isFurnished();
        String furnishedStatus;
        if(isFurnished)
            furnishedStatus = "Yes";
        else
            furnishedStatus = "No";
        String quadrant = property.getAddress().getCityQuadrant().toString();
        Address address = property.getAddress();
        Payment payment = property.getPayment();
        boolean paid = payment.isPaid();
        String status = property.getPropertyStatus().toString();
        Date startDate = payment.getPeriod().getStartDate();
        Date endDate = payment.getPeriod().getEndDate();
        db.updateProperty(pid, type, numBed, numBath, furnishedStatus, quadrant, address.getFormattedAddress(), 1, status, startDate.getFormattedDate(), endDate.getFormattedDate());

        // if(paid){
        //     Date startDate = payment.getDatePaid();
        //     Date endDate = payment.getListingExpiryDate();
        //     db.updateProperty(pid, type, numBed, numBath, furnishedStatus, quadrant, address.getFormattedAddress(), 1, status, startDate.getFormattedDate(), endDate.getFormattedDate());
        // }
        // else{
        //     db.updateProperty(pid, type, numBed, numBath, furnishedStatus, quadrant, address.getFormattedAddress(), 0, status, "NULL", "NULL");
        // }
    }

    public void updateListingStatus(){
        //also updates fee paid (possibly) and the dates
    }

    public String [][] getAllProperties() {
        // turn array list into 2D array for view
        // "Property Type", "Beds","Baths","Furnished","Status","Address", "Quadrant"
        String [][] result = new String [properties.size()][7];
        for (int i = 0 ; i< properties.size(); i++) {
            result[i][0] = properties.get(i).getPropertyType().toString();
            result[i][1] = Integer.toString(properties.get(i).getNumOfBed());
            result[i][2] = Integer.toString(properties.get(i).getNumOfBath());
            result[i][3] = Boolean.toString(properties.get(i).isFurnished());
            result[i][4] = properties.get(i).getPropertyStatus().toString();
            result[i][5] = properties.get(i).getAddress().getFormattedAddress();
            result[i][6] = properties.get(i).getAddress().getCityQuadrant().toString();
        }
        
        return result;
    }
    
    public String [][] getAllPropertiesDated() {
        // turn array list into 2D array for manager summary
        // "Property Type", "Beds","Baths","Furnished","Status","Address", "Quadrant", "StartDate", "EndDate"
        String [][] result = new String [properties.size()][8];
        for (int i = 0 ; i< properties.size(); i++) {
            result[i][0] = properties.get(i).getPropertyType().toString();
            result[i][2] = properties.get(i).getPropertyStatus().toString();
            result[i][3] = properties.get(i).getAddress().getFormattedAddress();
            result[i][4] = properties.get(i).getAddress().getCityQuadrant().toString();
            result[i][5] = properties.get(i).getPayment().getPeriod().getStartDate().getFormattedDate();
            result[i][6] = properties.get(i).getPayment().getPeriod().getEndDate().getFormattedDate();
        }
        return result;
    }


    public void setAllProperties (JSONArray arr) {
        // use the setProperty method and loops to populate properties array list
        for (int i = 0; i < arr.size(); i++) {
             JSONObject obj = (JSONObject)arr.get(i);
          //  JSONObject obj = arr.getJSONObject(i);
            setProperty(obj);
        }
        // for (JSONObject obj : arr) {
        //     setProperty(obj); // pass object to set property
        // }
    }


    private LocalDateTime stringToDateTime (String str_date) { // input must be month in words, days in numbers, 
        String [] split_date = str_date.split(" ");
        
        int month = 0;
        String mon = split_date[0].substring(0, 3); // get first 3 letters of the month
        mon.toLowerCase();
        switch (mon) {
            case "jan" :
                month = 1;
            case "feb" :
                month = 2;
            case "mar" :
                month = 3;
            case "apr" :
                month = 4;
            case "may" :
                month = 5;
            case "jun":
                month = 6;
            case "jul":
                month = 7;
            case "aug" :
                month = 8;
            case "sep":
                month = 9;
            case "oct" :
                month = 10;
            case "nov":
                month = 11;
            case "dec":
                month = 12;
        }

        String day_ = split_date[1].substring(0, split_date[1].length());
        int day = Integer.parseInt(day_);

        int year = Integer.parseInt(split_date[2]);

        LocalDateTime date = LocalDateTime.of(year, month, day, 0, 0);

        return date;
    }
}
