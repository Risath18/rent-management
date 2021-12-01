package View.Pages.Listing;

import java.util.ArrayList;

public class ManListingPage implements ListingPage {

    @Override
    public void generateListing(ArrayList<Property> properties) {
        for(int i=0;i< properties.size();i++){
            System.out.println(properties.get(i));
        }
    }

    @Override
    public boolean addListings(Property e, ArrayList<Property> properties) {
        properties.add(e);
        return true;
    }
}
