package com.rent.management.app.Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.rent.management.app.Model.Util.Payment;

import org.json.simple.JSONObject;

public class UtilController {
    DBCore db;
    Payment payment;

    /**
     * constructor for UtilController class
     * @param db DBCore object
     */
    public UtilController(DBCore db){
        this.db = db;
        this.payment = new Payment();
    }
    
    public JSONObject makePayment(){
        JSONObject obj = new JSONObject();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date currentDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        Date today_date = c.getTime();
        c.add(Calendar.DAY_OF_YEAR, payment.getDays());
        Date next_date = c.getTime();
        obj.put("current-date", dateFormat.format(today_date));
        obj.put("end-date", dateFormat.format(next_date));

        //BROKE HERE YIKES!
        payment.getPeriod().setStartDate(dateFormat.format(today_date).toString());
        payment.getPeriod().setEndDate(dateFormat.format(today_date).toString());

        // obj.put("current-date", dtf.format(now));

        // c.setTime(dtf.parse(now));
        // Date d = sdf.format(c.getTime());
      //  c.add(Calendar.DATE, payment.getDays());
        
        return obj;
    }

    /**
     * gets rate of a fee
     */
    public JSONObject getRate(){
        JSONObject obj = db.getFormattedFees();
        payment.setDays(Integer.parseInt(obj.get("Days").toString()));
        payment.setPrice(Integer.parseInt(obj.get("price").toString()));
        return obj;
    }
}
