package com.rent.management.app.View.Pages.Listing;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.rent.management.app.Controller.PropertyController;
import com.rent.management.app.Model.Property.Property;

public class RenterPropView extends PropertyView{
    private PropertyController propController;
    private Property selectedProperty;

    public RenterPropView(String[][] addToTable) {
        super(addToTable);
    }

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
		//propInfo.addSendEmailListener(this);
    }

    public void setPropertyController(PropertyController pc){
        this.propController=pc;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
