package View.Pages.AdminPage;

import Model.Property.Property;
import View.Pages.CreateEditPage.EditPropertyView;
import View.Pages.Listing.PropertyView;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ManagerPropertyView extends PropertyView {
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
            Property toSend=properties.get(index);
            editView=new EditPropertyView(toSend);
            editView.saveListener(this);
            editView.setID();
            editView.setStatus();
        }else
            editView=new EditPropertyView(null);
        editView.setVisible(true);
    }

    /**
     * Override of action performed to set the property status
     * @param e Action event triggering the listener.
     * */
    @Override
    public void actionPerformed(ActionEvent e) {
        Property temp=editView.getSelectedProperty();
        temp.setPropertyStatus(editView.getStatus());
        //Manager controller code
    }
    //Set this once we have a working controller
    //public void setManagerController()
}
