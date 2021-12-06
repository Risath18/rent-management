package com.rent.management.app.View.Pages.Listing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class SearchView  extends JFrame {
    private String [] propertyType={"Apartment", "Attached", "Townhouse ", "Other"};
    private String []cityQuadrant={"SW","NW","SE","NE"};
    private String[] numbers={"1","2","3","4","5"};
    private String[] furnish={"Furnished","Not Furnished"};

    private JComboBox typeBox=new JComboBox(propertyType);
    private JComboBox bedBox=new JComboBox(numbers);
    private JComboBox bathBox = new JComboBox(numbers);
    private JComboBox quadrantBox= new JComboBox(cityQuadrant);
    private JComboBox furnished=new JComboBox(furnish);

    private JRadioButton proceed=new JRadioButton("Proceed");
    private JRadioButton cancel=new JRadioButton("Cancel");
    private JButton submit=new JButton("Submit");
    private ButtonGroup group=new ButtonGroup();

    public SearchView(){
        setSize(450, 543);
        getContentPane().setBackground(new Color(230, 230, 250));
        setTitle("Create New Property");
        getContentPane().setLayout(null);


        JLabel typeLbl = new JLabel("Type");
        typeLbl.setBounds(41, 19, 69, 20);
        getContentPane().add(typeLbl);

        JLabel bedLbl = new JLabel("# of Bedrooms");
        bedLbl.setBounds(41, 55, 107, 20);
        getContentPane().add(bedLbl);

        JLabel bathLbl = new JLabel("# of bathrooms");
        bathLbl.setBounds(41, 91, 123, 20);
        getContentPane().add(bathLbl);

        JLabel FurLbl = new JLabel("Furnished");
        FurLbl.setBounds(41, 127, 69, 20);
        getContentPane().add(FurLbl);

        JLabel cityLbl = new JLabel("City quadrant");
        cityLbl.setBounds(41, 163, 107, 20);
        getContentPane().add(cityLbl);

        typeBox.setBounds(157, 16, 196, 26);
        getContentPane().add(typeBox);

        bedBox.setBounds(157, 52, 36, 26);
        getContentPane().add(bedBox);

        bathBox.setBounds(157, 88, 36, 26);
        getContentPane().add(bathBox);

        quadrantBox.setBounds(157, 115, 56, 26);
        getContentPane().add(quadrantBox);

        furnished.setBounds(157, 160, 56, 26);
        getContentPane().add(furnished);

        proceed.setBounds(152, 123, 61, 29);
        getContentPane().add(proceed);

        cancel.setBounds(217, 123, 61, 29);
        getContentPane().add(cancel);

        group.add(proceed);
        group.add(cancel);

        submit.setBounds(144, 441, 115, 29);
        getContentPane().add(submit);

    }

    public int getBeds(){
        return Integer.parseInt((String) Objects.requireNonNull(bedBox.getSelectedItem()));
    }

    /**
     * Getter funciton for number of baths
     * @return number of baths as an int
     * */
    public int getBaths(){
        return Integer.parseInt((String) Objects.requireNonNull(bathBox.getSelectedItem()));
    }

    /**
     * Getter function for furnished status
     * @return boolean value of furnished status
     * */
    public boolean isFurnished(){
        return Boolean.parseBoolean((String) furnished.getSelectedItem());
    }
    public void submitListener(ActionListener al){
        submit.addActionListener(al);
        submit.setActionCommand("searchSubmit");
    }
}
