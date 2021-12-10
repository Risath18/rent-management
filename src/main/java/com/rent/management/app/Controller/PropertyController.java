package com.rent.management.app.Controller;

import java.util.ArrayList;

import javax.security.auth.Subject;

import com.rent.management.app.Model.Property.*;
import com.rent.management.app.Model.Util.*;

import com.rent.management.app.View.Pages.Listing.RenterMenuView;
import com.rent.management.app.View.Pages.Listing.RenterPropView;
import com.rent.management.app.View.Pages.Listing.SearchView;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import java.awt.event.*;
import java.time.LocalDateTime;

public class PropertyController implements ActionListener {
    ArrayList<Property> properties;
    private DBCore db;
    private RenterMenuView renterMenuView;
    private RenterPropView renterPropView;
    private SearchView searchView;
    private PersonController pc;
    private UtilController uc;
    ArrayList<Property>propsToPass = new ArrayList<>();
    /**
     * Constructor for property controller
     * @param db database connection
     * @param pc person controller
     * @param uc utility controller
     */
    public PropertyController(DBCore db,PersonController pc, UtilController uc){
        this.db = db;
        this.pc = pc;
        this.uc = uc;
        properties = new ArrayList<Property>();
        renterMenuView=new RenterMenuView();
        renterMenuView.setVisible(true);
        addListernersToClass();
    }
    
    /**
     * sets search metrics
     */
    void setSearchMetrics(){

        //Call Getters of Search
        String type = searchView.getPropertyType();
        int num_bed = searchView.getBeds();
        int num_bath = searchView.getBaths();
        String furnishedString = searchView.isFurnished();
        String cityQuadrant = searchView.getCityQuadrant();


        //Based on Search Metrics
        try{
             setAllProperties(db.search(type, num_bed, num_bath, furnishedString, cityQuadrant));
        } catch(Exception e){
            return;
        }

        if(pc.getPerson() != null){
            saveSearch(type, num_bed, num_bath, furnishedString, cityQuadrant);
        }
    }

    /**
     * saves the search
     * @param type String for property type
     * @param num_bed int for number of bedrooms
     * @param num_bath int for number of bathrooms
     * @param furnishedString String for furnished status
     * @param cityQuadrant String for city quadrant
     */
    private void saveSearch(String type, int num_bed, int num_bath, String furnishedString, String cityQuadrant){
        if(cityQuadrant.equals("NULL")){
            cityQuadrant = "AL";
        }
        String searchID = db.saveSearchCriteria(type, num_bed, num_bath, furnishedString, cityQuadrant);
        String email = pc.getPerson().getEmail();
        db.updateRenterSearch(email, searchID);
    }

    /**
     * add's listeners to classes
     */
    private void addListernersToClass(){
        renterMenuView.searchListener(this);
        renterMenuView.exitListener(this);
    }

    /**
     * functionality in response to action event
     * @param e action event
     */
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("exit")){
            System.exit(1);
        }
        else if(e.getActionCommand().equals("search")){
            searchView = new SearchView();
            searchView.setVisible(true);
            searchView.submitListener(this);

        }
        else if(e.getActionCommand().equals("sendEmail")){
            String sender = renterPropView.getEmailView().getFrom();
            String landlord = renterPropView.getEmailView().getLandlordEmail();
            String subject =  renterPropView.getEmailView().getSubject();
            String message = renterPropView.getEmailView().getMessage();
            uc.sendEmail(sender, landlord, subject, message);
        }
        else if(e.getActionCommand().equals("searchSubmit")){
            setSearchMetrics();
            renterPropView = new RenterPropView(getAllPropertiesFiltered());
            renterPropView.setDisplay(propsToPass);
            renterPropView.setPropertyController(this);

            //If not a unregistered user
            if(pc.getPerson()!=null){
                renterPropView.setRenterEmail(pc.getPerson().getEmail());
            }
            
        }
    }

    /**
     * generates a property
     * @param obj JSONObject containing all required information to generate a property
     */
    public static Property generateProperty(JSONObject obj){
        String propertyId = obj.get("pid").toString();
        PropertyType pt = PropertyType.fromString(obj.get("type").toString());
        int num_bed = Integer.parseInt(obj.get("num_bed").toString());
        int num_bath = Integer.parseInt(obj.get("num_bath").toString());
        String furnished = obj.get("furnished").toString();
        boolean isFurnished;
        if(furnished.equals("Yes")){
            isFurnished = true;
        } else{
            isFurnished = false;
        }
        CityQuadrant qt = CityQuadrant.fromString(obj.get("quadrant").toString());
        String adr = obj.get("address").toString();
        String email = obj.get("l_email").toString();
        Address address = new Address(adr, qt);
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
        property.setEmail(email);
        return property;
    }
    
    /**
     * sets property
     * @param obj JSONObject with all property information
     */
    public void setProperty(JSONObject obj){
        properties.add(generateProperty(obj));
    }

    /**
     * setter for propert
     * @param property Property object
     */
    public void setProperty(Property property){
        properties.add(property);
    }

    /**
     * getter for properties
     * @return ArrayList of Properties
     */
    public ArrayList<Property> getProperty(){
        return properties;
    }
    
    /**
     * edits a property
     * @param property property containing the edits
     */
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
    }
    
    /**
     * getter for properties
     * @return 2D String array with all property information
     */
    public String [][] getAllProperties() {
        // turn array list into 2D array for view
        // "Property Type", "Beds","Baths","Furnished","Status","Address", "Quadrant"
        String [][] result = new String [properties.size()][7];
        for (int i = 0 ; i< properties.size(); i++) {
            result[i][0] = properties.get(i).getPropertyType().toString();
            result[i][1] = Integer.toString(properties.get(i).getNumOfBed());
            result[i][2] = Integer.toString(properties.get(i).getNumOfBath());
            boolean furnished = properties.get(i).isFurnished();
            if (furnished) {
                result[i][3] = "Yes";
            } else{
                result[i][3] = "No";
            }
            result[i][4] = properties.get(i).getPropertyStatus().toString();
            result[i][5] = properties.get(i).getAddress().getFormattedAddress();
            result[i][6] = properties.get(i).getAddress().getCityQuadrant().toString();
        }
        return result;
    }

    /**
     * getter for properties
     * @return 2D String array with all property information
     */
    public String [][] getAllPropertiesFiltered() {
        // turn array list into 2D array for view
        // "Property Type", "Beds","Baths","Furnished","Status","Address", "Quadrant"
        int count = 0;
        for (int i = 0; i < properties.size(); i++) {
            if(properties.get(i).getPropertyStatus().toString().equals("ACTIVE")) {
                count++;
            }
        }
        String [][] result = new String [count][7];
        int j = 0;
        for (int i = 0 ; i< properties.size(); i++) {
            if(properties.get(i).getPropertyStatus().toString().equals("ACTIVE")) { 
                propsToPass.add(properties.get(i));
                result[j][0] = properties.get(i).getPropertyType().toString();
                result[j][1] = Integer.toString(properties.get(i).getNumOfBed());
                result[j][2] = Integer.toString(properties.get(i).getNumOfBath());
                boolean furnished = properties.get(i).isFurnished();
                if (furnished) {
                    result[j][3] = "Yes";
                } else{
                    result[j][3] = "No";
                }
                result[j][4] = properties.get(i).getPropertyStatus().toString();
                result[j][5] = properties.get(i).getAddress().getFormattedAddress();
                result[j][6] = properties.get(i).getAddress().getCityQuadrant().toString();
                j++;
            }
        }
        
        return result;
    }

    /**
     * setter for all properties
     * @param arr JSONArray with property JSONObjects
     */
    public void setAllProperties (JSONArray arr) {
        // use the setProperty method and loops to populate properties array list
        properties.clear();
        for (int i = 0; i < arr.size(); i++) {
             JSONObject obj = (JSONObject)arr.get(i);
            setProperty(obj);
        }
    }

    /**
     * modifying string to a date time object
     * @param str_date string to be converted
     * @return LocalDateTime object
     */
    private LocalDateTime stringToDateTime (String str_date) { // input must be month in words, days in numbers, 
        String [] split_date = str_date.split(" ");
        
        int month = 0;
        String mon = split_date[0].substring(0, 3); // get first 3 letters of the month
        mon.toLowerCase();
        switch (mon) {
            case "jan" :
                month = 1;
                break;
            case "feb" :
                month = 2;
                break;
            case "mar" :
                month = 3;
                break;
            case "apr" :
                month = 4;
                break;
            case "may" :
                month = 5;
                break;
            case "jun":
                month = 6;
                break;
            case "jul":
                month = 7;
                break;
            case "aug" :
                month = 8;
                break;
            case "sep":
                month = 9;
                break;
            case "oct" :
                month = 10;
                break;
            case "nov":
                month = 11;
                break;
            case "dec":
                month = 12;
                break;
        }

        String day_ = split_date[1].substring(0, split_date[1].length());
        int day = Integer.parseInt(day_);

        int year = Integer.parseInt(split_date[2]);

        LocalDateTime date = LocalDateTime.of(year, month, day, 0, 0);

        return date;
    }

    /**
     * updates listing
     * @param status String for status
     * @param pid String for property ID
     * @param properties ArrayList of all properties
     */
    public void changeStatus(String status, String pid, ArrayList<Property> properties){
        for(int i = 0; i < properties.size(); i++){
            if(properties.get(i).getPropertyId().equals(pid)){
                properties.get(i).setPropertyStatus(PropertyStatus.fromString(status));
                db.changeListingStatus(pid, status);

                if(status.equals("RENTED") || status.equals("CANCELLED") || status.equals("SUSPENDED")){
                    db.updateDateRented(pid);
                }
                break;
            }
        }
    }
}
