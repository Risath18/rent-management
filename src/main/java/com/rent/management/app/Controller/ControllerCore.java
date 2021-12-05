package com.rent.management.app.Controller;

import com.rent.management.app.Model.Role.Person;
import com.rent.management.app.Model.Util.Name;
import com.rent.management.app.Exceptions.*;
public class ControllerCore {
    //private ViewCore view;
    private DBCore db;
    private PersonController pc;
    

    public ControllerCore(){
        System.out.println("Controller> Created!");
        this.db = new DBCore();

       // register("bernie@gmail.com", "iheartamerica", 3, "Bernie", "Democrat", "Sanders");
       login("deadpool@gmail.com", "wolverine");
    }

    public void endProgram(){
        db.close();
    }


    /**
     * registers a user by checking if they already exist in the database. If not, the person gets stored
     * @param username String for username
     * @param password String for password
     * @param accessLevel int for level of access in system
     * @param name object of name for the user's name
     * @return boolean whether successful 
     */
    public boolean register(String username, String password, int accessLevel, String fName, String mName, String lName){

        //Send to DB
        try{
            db.registerPerson(username, password, accessLevel, (fName + " " + mName + " " + lName));
        } catch(IllegalQueryException e){
            return false; //registration Failed
        }
        //If successfully, Add to Model
        this.pc = new PersonController();
        
        if(accessLevel == 1){ //Manager
            pc.setManager(fName, mName, lName, username);
        } else if(accessLevel == 2){ //Landlord
            pc.setLandlord(fName, mName, lName, username);
        } else{ //Renter
            pc.setRenter(fName, mName, lName, username, false);
            try{
                db.registerRenter(username, 0, "NULL");
            } catch(IllegalQueryException e){
                return false;
            }
        }
        return true;
    }

    public void login(String username, String password){
        //See if user exists
        String formattedQuery;
        int accessLevel;

        try{
           accessLevel = db.validateLogin(username, password);
           System.out.println(accessLevel);

            if(accessLevel == 1){ //Manager
                formattedQuery = db.findPerson(username);
                pc.setManager(formattedQuery);
            } else if(accessLevel == 2){ //Landlord
                formattedQuery = db.findPerson(username);
            } else{ //person
                formattedQuery = db.findRenter(username);
            }
        } catch(IllegalQueryException e){
           // return false; //registration Failed
        }

        //If successfully logged in, add to model

    }
}
