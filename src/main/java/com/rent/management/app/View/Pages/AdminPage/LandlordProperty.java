package com.rent.management.app.View.Pages.AdminPage;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.rent.management.app.Controller.LandlordController;
import com.rent.management.app.Model.Property.Property;
import com.rent.management.app.View.Pages.Listing.PropertyView;
import com.rent.management.app.View.Pages.CreateEditPage.EditPropertyView;

public class LandlordProperty extends PropertyView {
    //Implement Controller methods later
    private LandlordController llc;

    /**
     * Constructor for LandLordProperty View
     * @param addToTable A double array of strings containing the data to render to the GUI
     * @param prop Array list of current properties that might be edited.
     * */
    public LandlordProperty(String[][] addToTable, ArrayList<Property>prop) {
        super(addToTable);
        this.setDisplay(prop);
    }

    /**
     * Mouse action listener override
     * @param e MouseEvent to be passed by controller
     * */
    @Override
    public void actionMouse(MouseEvent e) {
        int index=getTable().getSelectedRow();
        if(properties.size() > 0){
            Property sendData=properties.get(index);
            editView=new EditPropertyView(sendData);
            editView.saveListener(this);
            editView.setID();
            editView.setStatus();
        }else{
            editView=new EditPropertyView(null);
        }
        editView.setVisible(true);
    }

    /**
     * Action performed override
     * @param e ActionEvent.
     * */
    @Override
    public void actionPerformed(ActionEvent e) {
        Property temp=editView.getSelectedProperty();
        temp.setPropertyStatus(editView.getStatus());
        llc.updateStatus(temp);
    }

    public void setLandlordController ( LandlordController lc){
        this.llc = lc;
    }

    
}
