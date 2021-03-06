package com.rent.management.app.Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.rent.management.app.Model.Util.Payment;

import org.json.simple.JSONObject;

import io.github.cdimascio.dotenv.Dotenv;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.*;

public class UtilController {
    DBCore db;
    Payment payment;
    Dotenv env;

    /**
     * constructor for UtilController class
     * @param db DBCore object
     */
    public UtilController(DBCore db){
        this.db = db;
        this.payment = new Payment();
        this.env = Dotenv.load();
        setFees();
    
    }

    /**
     * setter for fees
     */
    protected void setFees(){
        JSONObject obj = db.getFormattedFees();
        this.payment.setDays(Integer.parseInt(obj.get("Days").toString()));
        this.payment.setPrice(Integer.parseInt(obj.get("price").toString()));
    }

     /**
     * Change fees charges to landlords for posting properties.
     * @param days period to change to, 0 for no change.
     * @param price cost for period duration, 0 for no change.
     */
    public void changeFees(int days, int price){

        if (price != payment.getPrice()) db.changeFeeAmount(price);
        if (days != payment.getDays()) db.changeFeePeriod(days);
    }
    
    /**
     * makes a payment
     * @return JSONObject with payement information
     */
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

        payment.getPeriod().setStartDate(dateFormat.format(today_date));
        payment.getPeriod().setEndDate(dateFormat.format(next_date));
        
        return obj;
    }

    /**
     * gets rate of a fee
     * @return JSONObject with rate information
     */
    public JSONObject getRate(){
        JSONObject obj = db.getFormattedFees();
        payment.setDays(Integer.parseInt(obj.get("Days").toString()));
        payment.setPrice(Integer.parseInt(obj.get("price").toString()));
        return obj;
    }

    /**
     * Sends email to someone from a specific email with subject and content
     * like a true email client.
     * @param to receiver email address.
     * @param from sender email address
     * @param subject message subject.
     * @param message message content.
     */
    public void sendEmail(String sender, String to_email, String subject, String message) {
        String from_email = "radarrisat@gmail.com";
        Email to = new Email (to_email);
        Email from = new Email (from_email);
        message = "Email Sent By: <" + sender + ">\n" + message;
        subject = "Rent Management System - ENSF 480: " + subject;
        Content content = new Content("text/plain", message);
        Mail mail = new Mail(from, subject, to, content);
        SendGrid sg = new SendGrid(env.get("SENDGRID_API_KEY")); // retrieve API key from environment variables

        try{
            Request request = new Request ();
            request.setMethod (Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request); // can use response functions to print information
            
        }catch(Exception e){
            System.err.println("Error sending email.");
            e.printStackTrace();
        }
    }
}
