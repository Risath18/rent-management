package View.Pages.Listing;

import java.util.ArrayList;

public class LandListingPage implements ListingPage{
    @Override
    public void generateListing(ArrayList<Property> landlordProp) {
        for(int i=0;i< landlordProp.size();i++){
            System.out.println(landlordProp.get(i));
        }
    }

    @Override
    public boolean addListings(Property e, ArrayList<Property> landlordProp) {
        landlordProp.add(e);
        return true;
    }


}
