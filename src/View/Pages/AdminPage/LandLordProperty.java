package View.Pages.AdminPage;

import Model.Property.Property;
import View.Pages.CreateEditPage.EditPropertyView;
import View.Pages.Listing.PropertyView;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class LandLordProperty extends PropertyView {
    //Implement Controller methods later
    //private LandLordController landController;

    public LandLordProperty(String[][] addToTable, ArrayList<Property>prop) {
        super(addToTable);
        this.setDisplay(prop);
    }

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

    @Override
    public void actionPerformed(ActionEvent e) {
        Property temp=editView.getSelectedProperty();
        temp.setPropertyStatus(editView.getStatus());
        //Contoller code to follow
    }

    //Complete the following later. :)
    //public void setLandLordController
}
