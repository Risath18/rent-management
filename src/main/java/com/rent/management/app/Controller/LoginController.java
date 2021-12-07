package com.rent.management.app.Controller;

import com.rent.management.app.Model.Role.Person;
import com.rent.management.app.Model.Util.Name;
import com.rent.management.app.Model.Util.SearchCriteria;
import com.rent.management.app.View.Pages.Listing.RenterMenuView;
import com.rent.management.app.View.Pages.Listing.UnRegRenterView;
import com.rent.management.app.View.Pages.LoginPage.Login;

import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.JFrame;

import com.rent.management.app.Exceptions.*;
public class LoginController implements ActionListener{
    //private ViewCore view;
    private DBCore db;
    private PersonController pc;
    private Login loginView;
    private UnRegRenterView unRegRenterView;
    private PropertyController propertyController;
    private String userType;
    private LandlordController landlordController;
    private int accessLevel;

    public LoginController(DBCore db, int accessLevel){
        System.out.println("Login controller "+accessLevel);
        this.accessLevel = accessLevel;
        loginView=new Login();
        unRegRenterView = new UnRegRenterView();
        loginView.Login();
        loginView.setVisible(true);
        this.addListernersToView();
        System.out.println("Controller Created!");
        this.db = db;

        // this.propc = new PropertyController(db);
        // propc.createProperty();

    }

    public void addListernersToView(){
        loginView.addLoginListener(this);
        loginView.addNewListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String username=loginView.getUsername();
        String password=loginView.getPassword();
        System.out.println(username + " " + password);

        try{
            if(e.getActionCommand().equals("login")){
                if(login(username, password)){
                    System.out.println("Log in Success for: " + username);
                } else {
                    System.out.println("Fail!");
                }
            }else if(e.getActionCommand().equals("register")){
                unRegRenterView.setVisible(true);
                unRegRenterView.submitListener(this);
                //return;
            }else if(e.getActionCommand().equals("submit")){
                String fName=unRegRenterView.getFName();
                String lName = unRegRenterView.getLName();
                String email=unRegRenterView.getEmail();
                String pass= unRegRenterView.getPassword();
                if(register(email, pass, accessLevel, fName, lName)){
                    System.out.println("Registration Success!");

                } else{
                    System.out.println("Registration Failed! User exist!");
                    //TODO: FAIL, GO BACK
                }
            }
        }catch(Exception exception){
                exception.printStackTrace();
        }
    }

    


    public void openHomePage(String userType){
        //Open the page to view depending on renter, landlord or other stuff
    }

    public void endProgram(){
        db.close();
    }


    /**
     * registers a user by checking if they already exist in the database. If not, the person gets stored
     * @param username String for username
     * @param password String for password
     * @param accessLevel int for level of access in system
     * @param lName object of name for the user's name
     * @return boolean whether successful 
     */
    public boolean register(String username, String password, int accessLevel, String fName, String lName){

        //Send to DB
        try{
            db.registerPerson(username, password, accessLevel, (fName + " " + lName));
        } catch(IllegalQueryException e){
            return false; //registration Failed
        }
        //If successfully, Add to Model
        this.pc = new PersonController();
        
        if(accessLevel == 1){ //Manager
            pc.setManager(fName, lName, username);
        } else if(accessLevel == 2){ //Landlord
            pc.setLandlord(fName, lName, username);
        } else{ //Renter
            pc.setRenter(fName, lName, username, false);
            try{
                db.registerRenter(username, 0, "NULL");
            } catch(IllegalQueryException e){
                return false;
            }
        }
        return true;
    }

    public boolean login(String username, String password){
        //See if user exists
        //String formattedQuery;
        JSONObject obj;
        this.pc = new PersonController();

        int accessLevel;
        try{
           accessLevel = db.validateLogin(username, password);
           System.out.println("al: " + accessLevel);

            if(accessLevel == 1){ //Manager
                obj = db.findPerson(username);
                obj.put("Email", username);
                pc.setManager(obj);
            } else if(accessLevel == 2){ //Landlord
                obj = db.findPerson(username);
                obj.put("Email", username);
                pc.setLandlord(obj);
                landlordController = new LandlordController(db, pc);
            } else{ //Renter
                obj = db.findPerson(username);
                obj.put("Email", username);
                obj = db.findRenter(obj);                
                pc.setRenter(obj);
                propertyController = new PropertyController(db);
            }
        } catch(IllegalQueryException e){
            e.printStackTrace();
            return false; //registration Failed
        }

        //If successfully logged in, add to model
        return true;

    }
}
