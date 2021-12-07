package com.rent.management.app.Controller;

import com.rent.management.app.Controller.PersonController;
import com.rent.management.app.Controller.PropertyController;
import com.rent.management.app.View.Pages.AdminPage.ChangeFeesView;
import com.rent.management.app.View.Pages.AdminPage.ManagerView;

import org.json.simple.JSONArray;

import com.rent.management.app.Model.Property.*;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//when listing status changed to rented, change end date to today's date PLEASE DO THIS!!!!!!!!!!!!!!!!!!
public class ManagerController implements ActionListener {
    private PersonController personController;
    private PropertyController propertyController;
    private ManagerView managerView;
    private ChangeFeesView changeFeesView;
    private DBCore db;
    private ArrayList<Property> allProps= new ArrayList<>();

    // public void retrieveSummary(String startDate, String endDate){
    //     String [][] properties = propertyController.getAllPropertiesDated();
    //     //JSONArray arr = db.getAllProperties();
    //     //
    //     Date start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
    //     Date end = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
        
    //     int housesRentedActive = 0;
    //     for(int i = 0; i < properties.length; i++){
    //         if(properties[i][6]!= "NULL"){
    //             Date fromPropertiesEnd = new SimpleDateFormat("yyyy-MM-dd").parse(properties[i][7]);
    //             if(fromPropertiesEnd.after(start) && fromPropertiesEnd.before(end))
    //                 housesRentedActive++;
    //         }
    //     }//count the number of houses that are rented or active in the period

    //     int rented = 0;
    //     int active = 0;
    //     for(int i = 0; i < properties.length; i++){
    //         if(properties[i][6]!= "NULL" && properties[i][2] == "RENTED"){
    //             Date fromPropertiesEnd = new SimpleDateFormat("yyyy-MM-dd").parse(properties[i][7]);
    //             if(fromPropertiesEnd.after(start) && fromPropertiesEnd.before(end))
    //                 rented++;
    //         }
    //         else if(properties[i][6]!= "NULL" && properties[i][2] == "ACTIVE"){
    //             Date fromPropertiesEnd = new SimpleDateFormat("yyyy-MM-dd").parse(properties[i][7]);
    //             if(fromPropertiesEnd.after(start) && fromPropertiesEnd.before(end))
    //                 active++;
    //         }
    //     }//count the number of active houses in period
    //     //count the number of rented houses in the period

    //     String[][] houses = new String[rented][3];
    //     // for(int i = 0; i < properties.length; i++){
    //     //     if(properties[i][7]!= "NULL" && properties[i][4] == "RENTED"){
    //     //         houses[i][0] = properties[i][1];
                
    //     //     }
    //     // }
        
        
    // //     //retrieve all houses rented in period (l name, pid, address)
    // //     //output all numbers and list


    // //     //includes total number of houses in the period
    // //     //total number of houses rented in period
    // //     //total number of active listings 
       
    // //      //list of houses rented in period (landlord's name, house ID, address)
    // }
    public void setPersonController(PersonController person) {
        this.personController = person;
    }

    public ManagerController(DBCore db){
        this.db = new DBCore();
        managerView = new ManagerView();
        managerView.setVisible(true);
        addListeners();
    }

    public void addListeners(){
        managerView.changeFeesListner(this);
        managerView.changeStatusListner(this);
        managerView.exitListener(this);
    }

    public void showFeesWindow(){
        changeFeesView = new ChangeFeesView();
        changeFeesView.setFee(changeFeesView.getChangedFees());
        changeFeesView.setVisible(true);
        changeFeesView.addSubmitListener(this);
    }

    public void changeFees(){
        //Logic to change it in DB
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "changeFee":
                showFeesWindow();
            case "changeStatus":
                changeStatus();
            case "report":
                openReportForm();
            case "generateReport":
                generateReport();
            case "closeReport":
                //Close it
            case "submitFee":
                changeFees();
            case "exit":
                System.exit(1);
        }
        
    }

    public void changeStatus(){
        // setPropertyData();
        // managerView = new ManagerView();
        // //Continue code ((JAYYYY))
    }
    
    public void openReportForm(){

    }

    public void generateReport(){
        
    }
    
    

}
