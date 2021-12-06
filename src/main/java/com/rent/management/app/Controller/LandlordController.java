package com.rent.management.app.Controller;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import com.rent.management.app.Model.Property.Property;
import com.rent.management.app.View.Pages.AdminPage.LandlordProperty;
import com.rent.management.app.View.Pages.AdminPage.LandlordView;
import com.rent.management.app.View.Pages.CreateEditPage.CreateListing;
public class LandlordController implements ActionListener{

    private LandlordView landLordView;
    private CreateListing createProperty;
    private LandlordProperty LandlordPropertyView;
    private ArrayList<Property> landLordProps = new ArrayList<>();
    String [][]data;

    public LandlordController (){
        landLordView = new LandlordView();
         landLordView.setVisible(true);
         addListeners();
    }
    public void addListeners(){
        landLordView.editListener(this);
        landLordView.exitListener(this);
        landLordView.createListener(this);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()){
            case "createProperty":
                addProperty();
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
    private void getLandLordProperties(){
        //Get the list of landlord props
    }

    public void setData(){

    }

    private void addNewProperty(){
        //Add new property
        //When submit is pressed
    }

    public void addProperty(){
        //Add a new property to the database
    }

	public void updateStatus(Property temp) {
    
    }

}
