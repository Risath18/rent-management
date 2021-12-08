package com.rent.management.app.Controller;

import com.rent.management.app.Controller.PersonController;
import com.rent.management.app.Controller.PropertyController;
import com.rent.management.app.View.Pages.AdminPage.ChangeFeesView;
import com.rent.management.app.View.Pages.AdminPage.ManagerView;
import com.rent.management.app.View.Pages.AdminPage.RequestReport;
import com.rent.management.app.View.Pages.AdminPage.SummaryReportView;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.rent.management.app.Model.Property.*;

import java.util.ArrayList;
import java.util.Calendar;
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
    private SummaryReportView reportView;
    private RequestReport requestReport;
    private DBCore db;
    private ArrayList<Property> allProps= new ArrayList<>();

    /**
     * constructor for ManagerController class
     * @param db DBCore object
     * @param pc PersonController object
     */
    public ManagerController(DBCore db, PersonController pc){
        this.db = new DBCore();
        managerView = new ManagerView();
        managerView.setVisible(true);
        addListeners();
    }

    /**
     * setter for PersonController class
     * @param person PersonController object
     */
    public void setPersonController(PersonController person) {
        this.personController = person;
    }

    /**
     * adds a listener
     */
    public void addListeners(){
        managerView.changeFeesListner(this);
        managerView.changeStatusListner(this);
        managerView.exitListener(this);
        managerView.createReportListener(this);
    }

    /**
     * shows the window for fees
     */
    public void showFeesWindow(){
        changeFeesView = new ChangeFeesView();
        changeFeesView.setFee(changeFeesView.getChangedFees());
        changeFeesView.setVisible(true);
        changeFeesView.addSubmitListener(this);
    }

    /**
     * Change fees charges to landlords for posting properties.
     * @param days period to change to, 0 for no change.
     * @param price cost for period duration, 0 for no change.
     */
    public void changeFees(int days, int price){
        // temporary values until connected to GUI
        days = 60;
        price = 100;

        if (price != 0) db.changeFeeAmount(price);
        
        if (days != 0) db.changeFeePeriod(days);
    }

    /**
     * performs an action
     * @param e ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "changeFee":
                showFeesWindow();
                break;
            case "changeStatus":
                changeStatus();
                break;
            case "reportRequested":
                System.out.println("Report Requested!");
                openReportForm();
                break;
            case "generateReport":
                System.out.println("Generating Report");
              //  retrieveSummary();
                break;
            case "closeReport":
                //Close it
                break;
            case "submitFee":
               // changeFees();
               break;
            case "exit":
                System.exit(1);
        }
        
    }

    /**
     * changes a property's status
     */
    public void changeStatus(){
        // setPropertyData();
        // managerView = new ManagerView();
        // //Continue code ((JAYYYY))
    }

    public void openReportForm(){
        requestReport = new RequestReport();
        requestReport.setVisible(true);
        requestReport.addGenerateReportListener(this);
    }

    /**
     * retrieves a manager's periodical summary
     */
    public void retrieveSummary(){
        try{
        
        JSONArray arr = db.getAllProperties();
        String [][] data = new String[arr.size()][8];
        for(int i = 0; i < arr.size(); i++){
            JSONObject obj = (JSONObject)arr.get(i);
            String ID = obj.get("pid").toString();
            String email = obj.get("l_email").toString();
            String status = obj.get("status").toString();
            String address = obj.get("address").toString();
            String quadrant = obj.get("quadrant").toString();
            String start = obj.get("active_date").toString();
            String end = obj.get("end_date").toString();

            data[i][0] = ID;
            data[i][1] = email;
            data[i][2] = status;
            data[i][3] = address;
            data[i][4] = quadrant;
            data[i][5] = start;
            data[i][6] = end;
        }
         
        String startDate = requestReport.getStartYear()+"-"+requestReport.getStartMonth()+"-"+requestReport.getStartDate();
        String endDate = requestReport.getEndYear() + "-" +requestReport.getEndMonth() + "-" + requestReport.getEndDate();

        int year = Integer.parseInt(startDate.split("[-]")[0]);
        int month = Integer.parseInt(startDate.split("[-]")[1]);
        int day = Integer.parseInt(startDate.split("[-]")[2]);
                
        Calendar start = Calendar.getInstance();
        start.set(Calendar.DAY_OF_MONTH, day);
        start.set(Calendar.MONTH, month);
        start.set(Calendar.YEAR, year);

        Calendar end = Calendar.getInstance();
        year = Integer.parseInt(endDate.split("[-]")[0]);
        month = Integer.parseInt(endDate.split("[-]")[1]);
        day = Integer.parseInt(endDate.split("[-]")[2]);
        end.set(Calendar.DAY_OF_MONTH, day);
        end.set(Calendar.MONTH, month);
        end.set(Calendar.YEAR, year);

        int housesRentedActive = 0;
        for(int i = 0; i < data.length; i++){
            if(data[i][5]!= "NULL"){
                year = Integer.parseInt(data[i][6].split("[-]")[0]);
                month = Integer.parseInt(data[i][6].split("[-]")[1]);
                day = Integer.parseInt(data[i][6].split("[-]")[2]);
                Calendar getData = Calendar.getInstance();
                getData.set(Calendar.DAY_OF_MONTH, day);
                getData.set(Calendar.MONTH, month);
                getData.set(Calendar.YEAR, year);
                int compareStart = getData.compareTo(start);
                int compareEnd = getData.compareTo(end);
                if(compareStart >= 0 && compareEnd <= 0)
                    housesRentedActive++;
            }
        }//count the number of houses that are rented or active in the period

        int rented = 0;
        int active = 0;
        for(int i = 0; i < data.length; i++){
            if(data[i][5]!= "NULL" && data[i][2] == "RENTED"){
                year = Integer.parseInt(data[i][6].split("[-]")[0]);
                month = Integer.parseInt(data[i][6].split("[-]")[1]);
                day = Integer.parseInt(data[i][6].split("[-]")[2]);
                Calendar getData = Calendar.getInstance();
                getData.set(Calendar.DAY_OF_MONTH, day);
                getData.set(Calendar.MONTH, month);
                getData.set(Calendar.YEAR, year);
                int compareStart = getData.compareTo(start);
                int compareEnd = getData.compareTo(end);
                if(compareStart >= 0 && compareEnd <= 0)
                    rented++;
            }
            else if(data[i][5]!= "NULL" && data[i][2] == "ACTIVE"){
                year = Integer.parseInt(data[i][6].split("[-]")[0]);
                month = Integer.parseInt(data[i][6].split("[-]")[1]);
                day = Integer.parseInt(data[i][6].split("[-]")[2]);
                Calendar getData =  Calendar.getInstance();
                getData.set(Calendar.DAY_OF_MONTH, day);
                getData.set(Calendar.MONTH, month);
                getData.set(Calendar.YEAR, year);
                int compareStart = getData.compareTo(start);
                int compareEnd = getData.compareTo(end);
                if(compareStart >= 0 && compareEnd <= 0)
                    active++;
            }
        }
        //count the number of active houses in period
        //count the number of rented houses in the period

        String[][] houses = new String[rented][4];
        for(int i = 0; i < data.length; i++){
            if(data[i][5]!= "NULL" && data[i][2] == "RENTED"){
                houses[i][0] = data[i][0]; //pid
                houses[i][1] = db.getLandlordName(data[i][1]); //landlord name
                houses[i][2] = data[i][3];
                houses[i][3] = data[i][4];
            }
        }//list of houses rented in period (landlord's name, house ID, address)
        
        reportView = new SummaryReportView(houses);
        reportView.setNumActiveList(active);
        reportView.setNumHouseList(housesRentedActive);
        reportView.setNumHouseRent(rented);
        reportView.setVisible(true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
        // includes total number of houses in the period
        // total number of houses rented in period
        // total number of active listings 
}
