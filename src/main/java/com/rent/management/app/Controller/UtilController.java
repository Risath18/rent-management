package com.rent.management.app.Controller;

import java.text.SimpleDateFormat;

import com.rent.management.app.Model.Util.Payment;

import org.json.simple.JSONObject;

public class UtilController {
    DBCore db;
    Payment payment;

    public UtilController(DBCore db){
        this.db = db;
        this.payment = new Payment();
    }
    
    // public void makePayment(int days){
    //     SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
    //     Date date = new Date(System.currentTimeMillis());
    //     date.setDay(date.getDay(days));
    //     System.out.println(formatter.format(date));
    // }

    public void getRate(){
        JSONObject obj = db.getFormattedFees();
        int days = obj.get("Days");
        int fees = obj.get("Fees");
        
    }
}
