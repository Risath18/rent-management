package com.rent.management.app.View.Pages.Listing;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.rent.management.app.Controller.PropertyController;

public class RenterPropView extends PropertyView{
    private PropertyController propController;
    
    public RenterPropView(String[][] addToTable) {
        super(addToTable);
    }

    @Override
    public void actionMouse(MouseEvent e) {
        int index = getTable().getSelectedRow();
		if (propertyList.size() > 0) {
			selectedProperty = propertyList.get(index);
			propertyInfo = new PropertyInfoView(selectedProperty);
		} else {
			selectedProperty = null;
			propertyInfo = new PropertyInfoView(selectedProperty);
		}
		propertyInfo.setVisible(true);
		propertyInfo.addSendEmailListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
