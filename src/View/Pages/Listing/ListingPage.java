package View.Pages.Listing;

import Model.Property.Property;

import java.util.ArrayList;

public interface ListingPage {
    public void generateListing(ArrayList<Property> properties);
    boolean addListings(Property e,ArrayList<Property> properties);
}
