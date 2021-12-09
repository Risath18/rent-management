package com.rent.management.app.View.Pages.AdminPage;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.rent.management.app.Controller.ManagerController;
import com.rent.management.app.Model.Property.Property;
import com.rent.management.app.View.Pages.CreateEditPage.EditPropertyView;
import com.rent.management.app.View.Pages.Listing.PropertyView;

public class ManagerPropertyView extends PropertyView {
    private ManagerController managerContoller;
    /**
     * ManagerPropertyView constructor
     * @param addToTable double string array of property listing information
     * @param prop array list of type property with properties to be edited
     * */
    public ManagerPropertyView(String[][] addToTable, ArrayList<Property>prop) {
        super(addToTable);
        this.setDisplay(prop);
    }

    /**
     * Override of mouse listener to set the EditProperty view
     * @param e MouseEvent occurred
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

    public EditPropertyView getEditView(){
        return editView;
    }

    /**
     * Override of action performed to set the property status
     * @param e Action event triggering the listener.
     * */
  @Override
    public void actionPerformed(ActionEvent e) {
        Property temp=editView.getSelectedProperty();
        temp.setPropertyStatus(editView.getStatus());
        editView.saveListener(managerContoller);
        //llc.updateStatus(temp);
    }
    
    public void setManagerController(ManagerController arg){
        this.managerContoller = arg;
    }
}
