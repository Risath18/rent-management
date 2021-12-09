package com.rent.management.app.View.Pages.Listing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class SearchView  extends JFrame {
    private String [] propertyType={"Any", "APARTMENT", "ATTACHED", "TOWNHOUSE ", "DETACHED", "OTHER"};
    private String []cityQuadrant={"Any", "SW","NW","SE","NE"};
    private String[] numbers={"Any","1","2","3","4","5"};
    private String[] furnish={"Any", "Furnished","Not Furnished"};

    private JComboBox typeBox=new JComboBox(propertyType);
    private JComboBox bedBox=new JComboBox(numbers);
    private JComboBox bathBox = new JComboBox(numbers); 
    private JComboBox quadrantBox= new JComboBox(cityQuadrant);
    private JComboBox furnished=new JComboBox(furnish);


    private JButton submit=new JButton("Submit");

    /**
     * default constructor for search view
     */
    public SearchView() {
        setSize(450, 543);
        getContentPane().setBackground(new Color(230, 230, 250));
        setTitle("Search View");
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

        bedBox.setBounds(157, 52, 100, 26);
        getContentPane().add(bedBox);

        bathBox.setBounds(157, 88, 100, 26);
        getContentPane().add(bathBox);

        furnished.setBounds(157, 120, 100, 26);
        getContentPane().add(furnished);

        quadrantBox.setBounds(157, 160, 100, 26);
        getContentPane().add(quadrantBox);

        submit.setBounds(144, 441, 115, 29);
        getContentPane().add(submit);

    }

    /**
     * getter for property type
     * @return string value of property type
     */
    public String getPropertyType(){
        if(typeBox.getSelectedItem().toString().equals("Any")){
            return "NULL";
        }else
        return (typeBox.getSelectedItem().toString());
    }

    /**
     * getter for number of beds
     * @return number of beds as an int
     */
    public int getBeds(){
        if( Objects.requireNonNull(bedBox.getSelectedItem()).toString().equals("Any")){
            return 0;
        }
        return Integer.parseInt((String) Objects.requireNonNull(bedBox.getSelectedItem()));
    }

    /**
     * Getter funciton for number of baths
     * @return number of baths as an int
     * */
    public int getBaths(){
        if( Objects.requireNonNull(bathBox.getSelectedItem()).toString().equals("Any")){
            return 0;
        }else
        return Integer.parseInt((String) Objects.requireNonNull(bathBox.getSelectedItem()));
    }

    /**
     * Getter function for furnished status
     * @return boolean value of furnished status
     * */
    public String isFurnished(){
        if( furnished.getSelectedItem().toString().equals("Any")){
            return "NULL";
        }else if(furnished.getSelectedItem().toString().equals("Furnished")){
            return "Yes";
        }else{
            return "No";
        }    
    }

    /**
     * getter for city quadrant
     * @return string value of city quadrant
     */
     public String getCityQuadrant(){
         if(quadrantBox.getSelectedItem().toString().equals("Any"))
            return "NULL";
        else
            return quadrantBox.getSelectedItem().toString();
    }

    /**
     * action listenner for submit functionality
     * @param al action listener
     */
    public void submitListener(ActionListener al){
        submit.addActionListener(al);
        submit.setActionCommand("searchSubmit");
    }
}
