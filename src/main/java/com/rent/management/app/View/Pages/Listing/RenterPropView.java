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

    public RenterPropView(String[][] addToTable) {
        super(addToTable);
    }

    @Override
    public void actionMouse(MouseEvent e) {
        int index = getTable().getSelectedRow();
		if (properties.size() > 0) {
			selectedProperty = properties.get(index);
            System.out.println(" test: " + properties.get(index).getNumOfBath());
			propInfo = new PropertyInfoView(selectedProperty);
		} else {
			selectedProperty = null;
			propInfo = new PropertyInfoView(selectedProperty);
		}
        System.out.println("Trying to create a propView");
		propInfo.setVisible(true);
		propInfo.addingSendEmailListener(this);
    }

    public void setPropertyController(PropertyController pc){
        this.propController=pc;
    }
    public void setGuestController(GuestController guest){this.gc = guest;}

    public EmailView getEmailView(){
        return ev;
    }

    public Property getSelectedProperty(){
        return selectedProperty;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        ev = new EmailView(selectedProperty.getEmail());
        ev.setPropertyController(propController);
        ev.setGuestController(gc);
        ev.addSendListener(propController);
        ev.addSendListener(gc);
        ev.setVisible(true);

    }
}
