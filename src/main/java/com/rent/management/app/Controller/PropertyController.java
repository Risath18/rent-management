package com.rent.management.app.Controller;

import java.util.ArrayList;

import com.rent.management.app.Model.*;
import com.rent.management.app.Model.Property.*;
import com.rent.management.app.Model.Util.*;
import com.rent.management.app.View.Pages.Listing.RenterMenuView;
import com.rent.management.app.View.Pages.Listing.RenterPropView;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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

    
    public void setProperty(JSONObject obj){
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
        Property property = new Property(propertyId, pt, num_bed, num_bath, isFurnished, address, paid, ps);
        System.out.println(pt);
        System.out.println(num_bed);
        System.out.println(num_bath);
        System.out.println(isFurnished);
        System.out.println(propertyId);
        System.out.println(ps);
        System.out.println(address);
        System.out.println(paid);

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

        if(paid){
            Date startDate = payment.getDatePaid();
            Date endDate = payment.getListingExpiryDate();
            db.updateProperty(pid, type, numBed, numBath, furnishedStatus, quadrant, address.getFormattedAddress(), 1, status, startDate.getFormattedDate(), endDate.getFormattedDate());
        }
        else{
            db.updateProperty(pid, type, numBed, numBath, furnishedStatus, quadrant, address.getFormattedAddress(), 0, status, "NULL", "NULL");
        }
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
            result[i][5] = properties.get(i).getAddress().getCityQuadrant().toString();
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
}
