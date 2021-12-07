package com.rent.management.app.View.Pages.Listing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class SearchView  extends JFrame {
    private String [] propertyType={"Any", "APPARTMENT", "ATTACHED", "TOWNHOUSE ", "OTHER"};
    private String []cityQuadrant={"Any", "SW","NW","SE","NE"};
    private String[] numbers={"Any","1","2","3","4","5"};
    private String[] furnish={"Any", "Furnished","Not Furnished"};

    private JComboBox typeBox=new JComboBox(propertyType);
    private JComboBox bedBox=new JComboBox(numbers);
    private JComboBox bathBox = new JComboBox(numbers); 
    private JComboBox quadrantBox= new JComboBox(cityQuadrant);
    private JComboBox furnished=new JComboBox(furnish);


    private JButton submit=new JButton("Submit");

    public SearchView(){
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

        bedBox.setBounds(157, 52, 36, 26);
        getContentPane().add(bedBox);

        bathBox.setBounds(157, 88, 36, 26);
        getContentPane().add(bathBox);

        quadrantBox.setBounds(157, 115, 56, 26);
        getContentPane().add(quadrantBox);

        furnished.setBounds(157, 160, 56, 26);
        getContentPane().add(furnished);

        submit.setBounds(144, 441, 115, 29);
        getContentPane().add(submit);

    }

    public String getPropertyType(){
        if(typeBox.getSelectedItem().toString().equals("Any")){
            return "NULL";
        }else
        return (typeBox.getSelectedItem().toString());
    }

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

     public String getCityQuadrant(){
         if(quadrantBox.getSelectedItem().toString().equals("Any"))
            return "NULL";
        else
            return quadrantBox.getSelectedItem().toString();
    }


    public void submitListener(ActionListener al){
        submit.addActionListener(al);
        submit.setActionCommand("searchSubmit");
    }
}
