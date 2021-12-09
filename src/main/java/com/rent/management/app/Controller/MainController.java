package com.rent.management.app.Controller;

import com.rent.management.app.View.Pages.Listing.ViewMain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.text.Utilities;

public class MainController implements ActionListener {
    private DBCore db;
    private ViewMain viewMain;
    private LoginController login;
    private PropertyController propc;
    private UtilController uc;

    /**
     * Default constructor for the main constructor
     */
    public MainController(){
        this.db = new DBCore();
        viewMain = new ViewMain();
        this.uc = new UtilController(db);
        this.addListeners();
        viewMain.setVisible(true);
        //this.propc = new PropertyController(db);

    }

    /**
     * Adds listeners to user types
     */
    public void addListeners(){
        viewMain.addLandLord(this);
        viewMain.addGuest(this);
        viewMain.addManager(this);
        viewMain.addRenter(this);
    }

    /**
     * reacts to an action performed
     * @param e action performed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Guest")){
            viewMain.setVisible(false);
            PersonController pc = new PersonController(db);
             this.propc = new PropertyController(db, pc, uc);
        }else if (e.getActionCommand().equals("Landlord")){
            viewMain.setVisible(false);
            login= new LoginController(db, uc, 2, propc);
            } else if(e.getActionCommand().equals("Renter")){
            viewMain.setVisible(false);
            login= new LoginController(db, uc, 3, propc);
        } else if(e.getActionCommand().equals("Manager")){
            viewMain.setVisible(false);
            login= new LoginController(db, uc, 1, propc);
        }
    }
}
