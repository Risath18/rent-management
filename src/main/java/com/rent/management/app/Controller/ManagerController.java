package com.rent.management.app.Controller;

import com.rent.management.app.Controller.PersonController;
import com.rent.management.app.Controller.PropertyController;

public class ManagerController {
    PersonController personController;
    PropertyController propertyController;

    public void retrieveSummary(String startDate, String endDate){
        String [][] properties = propertyController.getAllPropertiesDated();
        //count the number of houses that are rented or active in the period (ones that have dates)
        //count the number of rented houses in the period
        //count the number of active houses in period
        //retrieve all houses rented in period (l name, pid, address)
        //output all numbers and list


        //includes total number of houses in the period
        //total number of houses rented in period
        //total number of active listings 
        //list of houses rented in period (landlord's name, house ID, address)
    }
}
