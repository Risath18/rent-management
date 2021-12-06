package com.rent.management.app.Controller;

import com.rent.management.app.Model.Property.Property;
import com.rent.management.app.View.Pages.Listing.RenterMenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GuestController implements ActionListener {
    private RenterMenuView renterMenuView;
    private PropertyController propContoller;
    private SearchController searchController;
    private ArrayList<Property> searchResults = null;
    private String [][]dataa;

    public GuestController(){
        renterMenuView = new RenterMenuView();
        renterMenuView.setVisible(true);
        addListeners();
    }
    public void addListeners(){
        renterMenuView.exitListener(this);
        renterMenuView.searchListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("exit")){
            System.exit(1);
        }
        else if(e.getActionCommand().equals("search")){
            searchController = new SearchController();
        }
        else if(e.getActionCommand().equals("send email")){
            //Email Logic
        }
    }
    public void getSearchData(Property p) {
        //Searching logic
    }
    public void setData(){
        //Set things according to search data
    }
}
