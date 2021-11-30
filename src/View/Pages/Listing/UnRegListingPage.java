package View.Pages.Listing;

import Model.*;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class UnRegListingPage implements ListingPage{
    @Override
    public void generateListing(ArrayList<Property> properties) {
        for(int i=0;i<properties.size();i++){
            System.out.println(properties.get(i));
        }
    }

    @Override
    public boolean addListings(Property e,ArrayList<Property>properties ) {
        properties.add(e);
        return true;
    }
}
