package com.rent.management.app.Controller;

import com.rent.management.app.View.Pages.Listing.ViewMain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController implements ActionListener {

    private ViewMain viewMain;
    private LoginController login;
    public MainController(){
        viewMain = new ViewMain();
        this.addListeners();
        viewMain.setVisible(true);
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

        }
        else if (e.getActionCommand().equals("Landlord") || e.getActionCommand().equals("Renter") || e.getActionCommand().equals("Manager")){
            viewMain.setVisible(false);
            login= new LoginController();
        }
    }
}
