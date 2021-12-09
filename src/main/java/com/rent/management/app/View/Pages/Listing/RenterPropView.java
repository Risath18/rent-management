package com.rent.management.app.View.Pages.Listing;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.rent.management.app.Controller.GuestController;
import com.rent.management.app.Controller.PropertyController;
import com.rent.management.app.Model.Property.Property;
import com.rent.management.app.View.Pages.AdminPage.EmailView;

public class RenterPropView extends PropertyView{
    private PropertyController propController;
    private Property selectedProperty;
    private GuestController gc;
    private EmailView ev;
    private String emailRenter;

    /**
     * constructor for renter property view
     * @param addToTable double string array
     */
    public RenterPropView(String[][] addToTable) {
        super(addToTable);
    }

    /**
     * mouse event setter
     * @param e mouse event
     */
    @Override
    public void actionMouse(MouseEvent e) {
        int index = getTable().getSelectedRow();
		if (properties.size() > 0) {
			selectedProperty = properties.get(index);
			propInfo = new PropertyInfoView(selectedProperty);
		} else {
			selectedProperty = null;
			propInfo = new PropertyInfoView(selectedProperty);
		}

		propInfo.setVisible(true);

            propInfo.addingSendEmailListener(this);
    }

    /**
     * setter for property controller
     * @param pc property controller
     */
    public void setPropertyController(PropertyController pc){
        this.propController=pc;
    }

    /**
     * setter for guest controller
     * @param guest guest controller
     */
    public void setGuestController(GuestController guest){this.gc = guest;}

    /**
     * getter for email view
     * @return email view
     */
    public EmailView getEmailView(){
        return ev;
    }

    /**
     * getter for selected property
     * @return selected property
     */
    public Property getSelectedProperty(){
        return selectedProperty;
    }
    public void setRenterEmail(String arg){
       this.emailRenter = arg;
   } 
    
    /**
     * functionality for when action performed
     * @param e action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ev = new EmailView(selectedProperty.getEmail());
        ev.setPropertyController(propController);
        ev.setGuestController(gc);
        ev.setTo(emailRenter);
        ev.addSendListener(propController);
        ev.addSendListener(gc);
        ev.setVisible(true);
    }
}
