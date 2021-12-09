package com.rent.management.app.Controller;

import com.rent.management.app.Model.Property.Property;
import com.rent.management.app.View.Pages.Listing.RenterMenuView;
import com.rent.management.app.View.Pages.Listing.RenterPropView;
import com.rent.management.app.Controller.GeneralPropController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GuestController implements GeneralPropController, ActionListener{
    private RenterMenuView renterMenuView;
    private PropertyController propContoller;
    private SearchController searchController;
    private ArrayList<Property> searchResults = null;
    private String [][]dataa;
    private RenterPropView renterPropView;
    
    /**
     * Guest Controller default constructor
     */
    public GuestController(){
        renterMenuView = new RenterMenuView();
        renterMenuView.setVisible(true);
        addListeners();
    }

    /**
     * Adds a listener to the view 
     */
    public void addListeners(){
        renterMenuView.exitListener(this);
        renterMenuView.searchListener(this);
    }

    /**
     * action performed function definitions.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("exit")){
            System.exit(1);
        }
        else if(e.getActionCommand().equals("search")){
            searchController = new SearchController(this);
        }
        else if(e.getActionCommand().equals("sendEmail")){ }
    }

    /**
     * Searching properties based on property
     * @param p property to be used in search
     */
    public void getSearchData(Property p) {
        //Search Properties based onp
        renterPropView = new RenterPropView(dataa);
        renterPropView.setGuestController(this);
        renterPropView.setDisplay(searchResults);
    }
}
