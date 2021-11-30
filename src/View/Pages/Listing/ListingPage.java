package View.Pages.Listing;

import Controller.*;

import java.util.ArrayList;

public interface ListingPage {
    ArrayList<String> propertyList = new ArrayList<String>();
    ControllerCore controller=new ControllerCore();

    public void generateListing();
    boolean addListings();
}
