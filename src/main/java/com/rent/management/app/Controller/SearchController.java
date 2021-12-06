package com.rent.management.app.Controller;

import com.rent.management.app.Model.Property.Property;
import com.rent.management.app.View.Pages.Listing.SearchView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchController implements ActionListener {

    private SearchView searchView;
    private GeneralPropController genConroller;
    public SearchController(GeneralPropController controller){
        searchView = new SearchView();
        searchView.setVisible(true);
        genConroller=controller;
        addListeners();
    }
    public void addListeners(){
        searchView.submitListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("searchSubmit")){
            //Construct a Property object from stuff passed in
            Property p = new Property();
            genConroller.getSearchData(p);
        }
    }
}
