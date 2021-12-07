package com.rent.management.app.Controller;

import com.rent.management.app.View.Pages.Listing.ViewMain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController implements ActionListener {
    private DBCore db;
    private ViewMain viewMain;
    private LoginController login;
    private PropertyController propc;

    public MainController(){
        this.db = new DBCore();
        viewMain = new ViewMain();
        this.addListeners();
        viewMain.setVisible(true);
        //this.propc = new PropertyController(db);

    }
    public void addListeners(){
        viewMain.addLandLord(this);
        viewMain.addGuest(this);
        viewMain.addManager(this);
        viewMain.addRenter(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Guest")){
            viewMain.setVisible(false);
        }else if (e.getActionCommand().equals("Landlord")){
            viewMain.setVisible(false);
            login= new LoginController(db, 2);
            } else if(e.getActionCommand().equals("Renter")){
            viewMain.setVisible(false);
            login= new LoginController(db, 3);
        } else if(e.getActionCommand().equals("Manager")){
            viewMain.setVisible(false);
            login= new LoginController(db, 1);
        }
    }
}
