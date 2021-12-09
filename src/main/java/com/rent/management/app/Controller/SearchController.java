package com.rent.management.app.Controller;

import com.rent.management.app.Model.Property.Property;
import com.rent.management.app.View.Pages.Listing.SearchView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchController implements ActionListener {
    private SearchView searchView;
    private GeneralPropController genConroller;

    /**
     * search controller constructor
     * @param controller General property controller object
     */
    public SearchController(GeneralPropController controller){
        searchView = new SearchView();
        searchView.setVisible(true);
        genConroller=controller;
        addListeners();
    }

    /**
     * add listeners to view
     */
    public void addListeners(){
        searchView.submitListener(this);
    }

    /**
     * functionality in response to an action event
     * @param e action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("searchSubmit")){
            //Construct a Property object from stuff passed in
            Property p = new Property();
            genConroller.getSearchData(p);
        }
    }

    //turn on/off notifications

    //save a search
    public void saveSearch(){
        //call registerRenter
        //call saveSearch
    }
    //add to saved search table
    //make UI ask user to save their search criteria

    //saved search
    //public void checkSearch()
    //call database to see if person's notifications are on
    //if they are on, check the properties table and return any properties that have been added since the date of last login

    //also need function to store the date the person logged in
}
