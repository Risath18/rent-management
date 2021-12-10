package com.rent.management.app.View.Pages.Listing;
import java.util.ArrayList;

import com.rent.management.app.Model.Property.Property;

public interface ListingPage {
    public void generateListing(ArrayList<Property> properties); //generates a listing
    boolean addListings(Property e,ArrayList<Property> properties); //adds a listing
}
