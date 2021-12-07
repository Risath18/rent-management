package com.rent.management.app.Controller;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import com.rent.management.app.Model.Property.Address;
import com.rent.management.app.Model.Property.CityQuadrant;
import com.rent.management.app.Model.Property.Property;
import com.rent.management.app.Model.Property.PropertyStatus;
import com.rent.management.app.Model.Property.PropertyType;
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
    String [][]data;

    /**
     * constructor for landlord controller class
     * @param db DBCore object
     * @param pc PersonController object
     */
    public LandlordController (DBCore db, PersonController pc){
        this.db = db;
        this.pc =pc;
        this.uc= new UtilController(db);
       // uc.sendEmail("libergood@gmail.com", "radarrisat@gmail.com", "Renters Messaging You!", "Hello World");
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
            case "exit":
                System.exit(1);

        }
		
    }
    

    /**
     * getter for properties belonging to landlord 
     * 
     */ 
    private void getLandLordProperties(){
        setData();
        landlordPropertyView = new LandlordProperty(data,landLordProps);
        landlordPropertyView.setLandlordController(this);
    }



    /**
     * setter for data in properties
     */
    public void setData(){
        String email = pc.getPerson().getEmail(); // get landlord's email
        JSONArray arr = db.getLandlordProperties (email); // get all landlord properties

        data = new String [arr.size()] [7];
        for(int i = 0; i < arr.size(); i++) {
            JSONObject obj = (JSONObject)arr.get(i);
            Property property = PropertyController.generateProperty (obj);
            data[i][0] = property.getPropertyId();
            data[i][1] = property.getPropertyType().toString();
            data[i][2] = Integer.toString(property.getNumOfBed());
            data[i][3] = Integer.toString(property.getNumOfBath());
            if (property.isFurnished()) {
                data[i][4] = "Yes";
            } else {
                data[i][4] = "No";
            }
            data[i][5] = property.getAddress().getFormattedAddress();
            data[i][6] = property.getPropertyStatus().toString();
        }

    }

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
            boolean isFurnished = createProperty.isFurnished();
            String furnishedString = isFurnished == true ? "yes" : "no";
            JSONObject obj = createProperty.getAddress();
            CityQuadrant qt = CityQuadrant.fromString(createProperty.getQuadrant());
            Address address = new Address(obj.get("street").toString(), qt, Integer.parseInt(obj.get("house_number").toString()));
            PropertyStatus ps = PropertyStatus.ACTIVE;
            String email = pc.getPerson().getEmail();

            //Create id
            int pid = db.generatePropertyId();
            JSONObject rateJson = uc.makePayment();

            //Add to Model and DB
            Property property = new Property(Integer.toString(pid), pt, num_bed, num_bath, isFurnished, address, ps);
            db.registerProperty(pid, email, pt.toString(), num_bed, num_bath, furnishedString, qt.toString(), address.getFormattedAddress(), 1, ps.toString(), rateJson.get("current-date").toString(), rateJson.get("end-date").toString());
            System.out.println("Congrats!");   
        } catch(Exception e){
                e.printStackTrace();
            }
    }

    /**
     * adds a new property to the database
     */
    public void addProperty(){
        //Add a new property to the database
    }

    /**
     * updates the status of a property
     */
	public void updateStatus(Property temp) {
    
    }

}
