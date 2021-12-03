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

    /**
     * Constructor for LandLordProperty View
     * @param addToTable A double array of strings containing the data to render to the GUI
     * @param prop Array list of current properties that might be edited.
     * */
    public LandLordProperty(String[][] addToTable, ArrayList<Property>prop) {
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
        //Contoller code to follow
    }

    //Complete the following later. :)
    //public void setLandLordController
}
