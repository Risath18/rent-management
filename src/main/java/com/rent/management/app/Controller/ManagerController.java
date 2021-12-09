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
    private PersonController pc;
    private PropertyController propc;
    private ManagerView managerView;
    private ChangeFeesView changeFeesView;
    private SummaryReportView reportView;
    private RequestReport requestReport;
    private DBCore db;
    private UtilController uc;
    private ArrayList<Property> allProps= new ArrayList<>();

    /**
     * constructor for ManagerController class
     * @param db DBCore object
     * @param pc PersonController object
     */
    public ManagerController(DBCore db, PersonController pc, UtilController uc, PropertyController propc){
        this.db = new DBCore();
        this.pc = pc;
        this.uc=uc;
        this.propc = propc;
        managerView = new ManagerView();
        managerView.setVisible(true);
        addListeners();
    }

    /**
     * setter for PersonController class
     * @param person PersonController object
     */
    public void setPersonController(PersonController person) {
        this.pc = person;
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
        changeFeesView.setPeriod(uc.getRate());
        changeFeesView.setVisible(true);
        changeFeesView.addSubmitListener(this);
    }


    /**
     * performs an action
     * @param e ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "fessChanged":
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
               retrieveSummary();
                break;
            case "closeReport":
                //Close it
                break;
            case "submitFee":
                changeFees();
               break;
            case "exit":
                System.exit(1);
        }
        
    }
    public void changeStatus(){

     //  propc.changeStatus(status, pid);
    }


    /**
     * changes the fees or period of a property
     */
    private void changeFees() {
        int fees = Integer.parseInt(changeFeesView.getChangedFees());
        int period = changeFeesView.getChangedPeriod();
        uc.changeFees(period, fees);
    }

    /**
     * opens report form
     */
    public void openReportForm(){
        requestReport = new RequestReport();
        requestReport.setVisible(true);
        requestReport.addGenerateReportListener(this);
    }    

    /**
     * retrieves a manager's periodical summary. Includes: 
     * includes total number of houses in the period
     * total number of active listings 
     * total number of houses rented
     * summary of which properties have been rented
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
                
            Calendar userStart = Calendar.getInstance();
            userStart.set(Calendar.DAY_OF_MONTH, day);
            userStart.set(Calendar.MONTH, month);
            userStart.set(Calendar.YEAR, year);

            Calendar userEnd = Calendar.getInstance();
            year = Integer.parseInt(endDate.split("[-]")[0]);
            month = Integer.parseInt(endDate.split("[-]")[1]);
            day = Integer.parseInt(endDate.split("[-]")[2]);
            userEnd.set(Calendar.DAY_OF_MONTH, day);
            userEnd.set(Calendar.MONTH, month);
            userEnd.set(Calendar.YEAR, year);

            ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();

            int housesRentedActive = 0;
            int rented = 0;
            int active = 0;
            
            for(int i = 0; i < data.length; i++){
                if(!data[i][5].equals("NULL") && !data[i][6].equals("NULL")){
                    year = Integer.parseInt(data[i][5].split("[-]")[0]);
                    month = Integer.parseInt(data[i][5].split("[-]")[1]);
                    day = Integer.parseInt(data[i][5].split("[-]")[2]);
                    Calendar propertyStart = Calendar.getInstance();
                    propertyStart.set(Calendar.DAY_OF_MONTH, day);
                    propertyStart.set(Calendar.MONTH, month);
                    propertyStart.set(Calendar.YEAR, year);
                    
                    year = Integer.parseInt(data[i][6].split("[-]")[0]);
                    month = Integer.parseInt(data[i][6].split("[-]")[1]);
                    day = Integer.parseInt(data[i][6].split("[-]")[2]);
                    Calendar propertyEnd = Calendar.getInstance();
                    propertyEnd.set(Calendar.DAY_OF_MONTH, day);
                    propertyEnd.set(Calendar.MONTH, month);
                    propertyEnd.set(Calendar.YEAR, year);
                    //0 == equal
                    // < 0 -> property.compareTo(report) -> property is Before report Start
                    // > 0 -> property.compareTo(report) -> property is After report Start

                    
                    //Case 1: startDate of Property is after start date of Period. End date of property is before end date of period
                    if(propertyStart.compareTo(userStart) > 0 && propertyEnd.compareTo(userEnd) < 0){
                        //if the requested dates are in the range, 
                        System.out.println("Case 1: " + data[i][2]);
                        housesRentedActive++;
                        if(data[i][2].equals("RENTED")){
                            rented++;
                            ArrayList<String> row = new ArrayList<>();
                            row.add(data[i][0]);
                            row.add(db.getLandlordName(data[i][1]));
                            row.add(data[i][3]);
                            row.add(data[i][4]);
                            table.add(row);
                        } else if(data[i][3].equals("ACTIVE")){
                            active++;
                        }

                        //Case 2: Property end date is after report start date.
                    } else if(propertyEnd.compareTo(userStart) > 0 && propertyStart.compareTo(userStart) < 0){
                        housesRentedActive++;
                        System.out.println("Case 2: " + data[i][2]);

                        if(data[i][2].equals("RENTED")){
                            rented++;
                            ArrayList<String> row = new ArrayList<>();
                            row.add(data[i][0]);
                            row.add(db.getLandlordName(data[i][1]));
                            row.add(data[i][3]);
                            row.add(data[i][4]);
                            table.add(row);
                        } else if(data[i][3].equals("ACTIVE")){
                            active++;
                        }

                        //Case 3: Property start date is before reporty end date.
                    } else if(propertyStart.compareTo(userEnd) < 0 && propertyEnd.compareTo(userEnd) > 0){
                        housesRentedActive++;
                        System.out.println("Case 3: " + data[i][2]);

                        if(data[i][2].equals("RENTED")){
                            rented++;
                            ArrayList<String> row = new ArrayList<>();
                            row.add(data[i][0]);
                            row.add(db.getLandlordName(data[i][1]));
                            row.add(data[i][3]);
                            row.add(data[i][4]);
                            table.add(row);
                        } else if(data[i][3].equals("ACTIVE")){
                            active++;
                        }

                        //Case 4: Property Start is before Period start and Property end is after Period end
                    } else if(propertyStart.compareTo(userStart) < 0 && propertyEnd.compareTo(userEnd) > 0){
                        housesRentedActive++;
                        System.out.println("Case 4: " + data[i][2]);

                        if(data[i][2].equals("RENTED")){
                            rented++;
                            ArrayList<String> row = new ArrayList<>();
                            row.add(data[i][0]);
                            row.add(db.getLandlordName(data[i][1]));
                            row.add(data[i][3]);
                            row.add(data[i][4]);
                            table.add(row);
                        } else if(data[i][3].equals("ACTIVE")){
                            active++;
                        }
                    }
                }
            }

            //count the number of active houses in period
            //count the number of rented houses in the period
            int counter =0;

            String[][] houses = new String[table.size()][];
            for (int i = 0; i < table.size(); i++) {
                System.out.println(table.get(i).get(2));
                ArrayList<String> row = table.get(i);
                houses[i] = row.toArray(new String[row.size()]);
            }

            // System.out.println("Creating a report");
            reportView = new SummaryReportView(houses);
            reportView.setVisible(true);
            reportView.setNumActiveList(active);
            reportView.setNumHouseList(housesRentedActive);
            reportView.setNumHouseRent(rented);
            }catch(Exception e){
            e.printStackTrace();
        }
    }

}