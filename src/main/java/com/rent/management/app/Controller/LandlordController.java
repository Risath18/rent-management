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
        //landlo
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void updateStatus(Property temp) {
    
    }

}
