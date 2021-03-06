package com.rent.management.app.Controller;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import com.rent.management.app.Model.Property.*;
import com.rent.management.app.View.Pages.AdminPage.LandlordProperty;
import com.rent.management.app.View.Pages.AdminPage.LandlordView;
import com.rent.management.app.View.Pages.CreateEditPage.CreateListing;
import com.rent.management.app.Controller.DBCore;
import com.rent.management.app.Controller.PropertyController;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
public class LandlordController implements ActionListener{
    private LandlordView landLordView;
    private CreateListing createProperty;
    private LandlordProperty landlordPropertyView;
    private ArrayList<Property> landLordProps = new ArrayList<>();
    private DBCore db;
    private PersonController pc;
    private UtilController uc;
    private PropertyController propc;
    String [][]data;

    /**
     * constructor for landlord controller class
     * @param db DBCore object
     * @param pc PersonController object
     */
    public LandlordController (DBCore db, PersonController pc, UtilController uc, PropertyController propc){
        this.propc = propc;
        this.db = db;
        this.pc =pc;
        this.uc = uc;
        landLordView = new LandlordView();
        landLordView.setVisible(true);
        addListeners();
    }

    /**
     * adds listeners
     */
    public void addListeners(){
        landLordView.editListener(this);
        landLordView.exitListener(this);
        landLordView.createListener(this);
    }

    /**
     * Actions are performed based on the actionEvent passed
     */
	@Override   
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()){
            case "createProperty":
                propertyPage();
                break;
            case "editProperty":
                getLandLordProperties();
                break;
            case "addSubmit":
                addNewProperty();
                break;
            case  "saveEditedProperty":
                submitChanges();
                break;
            case "exit":
                System.exit(1);

        }
		
    }
    

    /**
     * getter for properties belonging to landlord 
     */ 
    private void getLandLordProperties(){
        setData();

        landlordPropertyView = new LandlordProperty(data,landLordProps);
        landlordPropertyView.setLandlordController(this);

    }

    /**
     * submits changes
     */
    public void submitChanges(){
        String status = landlordPropertyView.getEditView().getStatus().toString();
        String pid = landlordPropertyView.getEditView().getID();
        propc.changeStatus(status, pid, landLordProps);
    }


    /**
     * setter for data in properties
     */
    public void setData(){
        String email = pc.getPerson().getEmail(); // get landlord's email
        JSONArray arr = db.getLandlordProperties(email); // get all landlord properties

        data = new String [arr.size()][7];
        for(int i = 0; i < arr.size(); i++) {
            JSONObject obj = (JSONObject)arr.get(i);
            Property property = PropertyController.generateProperty(obj);
            landLordProps.add(property);
            data[i][0] = property.getPropertyType().toString();
            data[i][1] = Integer.toString(property.getNumOfBed());
            data[i][2] = Integer.toString(property.getNumOfBath());
            if (property.isFurnished()) {
                data[i][3] = "Yes";
            } else {
                data[i][3] = "No";
            }
            data[i][4] = property.getAddress().getFormattedAddress();
            data[i][5] = property.getPropertyStatus().toString();
            data[i][6] = property.getAddress().getCityQuadrant().toString();
        }
    }

    /**
     * creates a new property listing
     */
    private void propertyPage(){
        createProperty = new CreateListing();
        JSONObject obj = uc.getRate();
        createProperty.setCurrentFee(obj.get("price").toString());
        createProperty.setVisible(true);
        createProperty.addSubmitListener(this);
    }

    /**
     * adds new property
     */
    private void addNewProperty(){
        //After Submitting Button to Create Property
        try{

            PropertyType pt = PropertyType.fromString(createProperty.getPropertyType());
            int num_bed = createProperty.getBeds();
            int num_bath = createProperty.getBaths();
            String furnished = createProperty.isFurnished();
            boolean isFurnished;
            if(furnished.equals("Yes")){
                isFurnished = true;
            }
            else{
                isFurnished = false;
            }
            JSONObject obj = createProperty.getAddress();
            CityQuadrant qt = CityQuadrant.fromString(createProperty.getQuadrant());
            Address address = new Address(obj.get("address").toString(), qt);
            PropertyStatus ps = PropertyStatus.ACTIVE;
            String email = pc.getPerson().getEmail();

            //Create id
            int pid = db.generatePropertyId();
            JSONObject rateJson = uc.makePayment();

            //Add to Model and DB
            Property property = new Property(Integer.toString(pid), pt, num_bed, num_bath, isFurnished, address, ps);
            db.registerProperty(pid, email, pt.toString(), num_bed, num_bath, furnished, qt.toString(), address.getFormattedAddress(), 1, ps.toString(), rateJson.get("current-date").toString(), rateJson.get("end-date").toString());
           
            notifyRenters(pt.toString(), num_bed, num_bath, furnished, qt.toString());
           
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * notifies renters with search criteria
     * @param propertyType String for property type
     * @param num_bed int for number of bedrooms
     * @param num_bath int for number of bathrooms
     * @param furnishedString String for furnished status
     * @param cityQuadrant String for city quadrant
     */
    private void notifyRenters(String propertyType, int num_bed, int num_bath, String furnishedString, String cityQuadrant){
        //Get renters with matching properties
        JSONArray arr = db.rentersWithSearch(propertyType, num_bed, num_bath, furnishedString, cityQuadrant);
        for(int i =0; i<arr.size(); i++){
            JSONObject obj = (JSONObject)arr.get(i);
            sendNotifToRenter(obj.get("email").toString(), propertyType, num_bed, num_bath, furnishedString, cityQuadrant);
        }
    }

    /**
     * sends a formatted email to all of them
     * @param email String for email to send to
     * @param propertyType String for property type
     * @param num_bed int for number of bedrooms
     * @param num_bath int for number of bathrooms
     * @param furnishedString String for furnished status
     * @param cityQuadrant String for city quadrant
     */
    private void sendNotifToRenter(String email, String propertyType, int num_bed, int num_bath, String furnishedString, String cityQuadrant ){
        String subject = "New Property Available with Your Search Criteria!";
        String body = "The following property is available for renting: \n" + "Type: " + propertyType + "\nNumber of Bedrooms: " + num_bed + "\nNumber of Bedrooms: " + num_bath + "\nIs it Furnished: " + furnishedString + "\nCity Quadrant: " + cityQuadrant;
        body+="\nThank you for using Rent Management System!";

        uc.sendEmail("Saved Search Notifier", email, subject, body);
    }

}
