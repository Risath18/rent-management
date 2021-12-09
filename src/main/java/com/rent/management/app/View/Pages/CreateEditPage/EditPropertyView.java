package com.rent.management.app.View.Pages.CreateEditPage;

import javax.swing.*;

import com.rent.management.app.Model.Property.PropertyStatus;
import com.rent.management.app.Model.Property.Property;

import java.awt.*;
import java.awt.event.ActionListener;

public class EditPropertyView extends JFrame {
    private String []status ={"Active","Rented","Cancelled","Suspended"};
    private JButton saveButton=new JButton("Save");
    private JTextArea id=new JTextArea();
    private JComboBox comb=new JComboBox(status);
    private Property property=null;

    /**
     * Constructor for editing a property
     * @param ptr Property type of property to be edited.
     * */
    public EditPropertyView(Property ptr){
        property=ptr;

        setSize(278,222);
        getContentPane().setBackground(new Color(230, 230, 250));
        setTitle("Edit Property");
        getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Property ID");
        lblNewLabel.setBounds(15, 16, 94, 20);
        getContentPane().add(lblNewLabel);

        JLabel lblCurrentStatus = new JLabel("Status");
        lblCurrentStatus.setBounds(25, 57, 112, 20);
        getContentPane().add(lblCurrentStatus);

        comb.setBounds(124, 54, 117, 26);
        getContentPane().add(comb);

        saveButton.setBounds(56, 108, 115, 29);
        getContentPane().add(saveButton);

        id.setEnabled(false);
        id.setEditable(false);
        id.setBounds(124, 16, 117, 24);
        getContentPane().add(id);
    }

    /**
     * Setter for save action listener
     * @param al action listener to trigger saving property
     * */
    public void saveListener(ActionListener al){
        System.out.println("Here");
        saveButton.addActionListener(al);
        saveButton.setActionCommand("saveEditedProperty");

    }

    /**
     * Setter for property ID using getPropertyID
     * */
    public void setID(){
        System.out.println("CHECK: " + property.getPropertyId());
        id.setText(property.getPropertyId());
    }

    public String getID(){
        return id.getText();
    }

    /**
     * Setter of property status using getPropertyStatus
     * */
    public void setStatus(){
        comb.setSelectedItem(property.getPropertyStatus().toString());
    }

    /**
     * Getter of property status
     * @return PropertyStatus type of property status
     * */
    public PropertyStatus getStatus(){
        return PropertyStatus.fromString((String) comb.getSelectedItem());
    }

    /**
     * Getter of property for editing
     * @return selected property as type Property
     * */
    public Property getSelectedProperty(){
        return property;
    }
}
