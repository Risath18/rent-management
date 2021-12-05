package com.rent.management.app.View.Pages.CreateEditPage;

import javax.swing.*;
import java.awt.*;
import com.rent.management.app.Model.*;
import com.rent.management.app.Model.Property.Address;
import com.rent.management.app.Model.Property.CityQuadrant;
import com.rent.management.app.Model.Property.PropertyStatus;

import java.lang.instrument.IllegalClassFormatException;
import java.text.*;
import java.util.Objects;

public class CreateListing extends JFrame{
    private static final long serialVersionUID = 1L;
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
    private final JLabel cardNumLbl = new JLabel("Card Number");
    private final JLabel expiryDateLbl = new JLabel("Expiry Date");
    private final JLabel cvvLbl = new JLabel("CVV");
    private final JLabel streetLbl = new JLabel("Street");
    private final JLabel houseNumberLal = new JLabel("House Number");
    private JTextField holderName = new JTextField();
    private JTextField cardNum = new JTextField();
    private JTextField cvv = new JTextField();
    private JTextArea currentFee = new JTextArea();
    private JTextField street;
    private JTextField houseNumber;

    private String[] year = {"2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"};
    private String[] month = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
    private final JComboBox yearEx = new JComboBox(year);
    private final JComboBox monthEx = new JComboBox(month);

    /**
     * Default constructor to initialize the create listing view including initializing all panes and buttons.
     * */
    public CreateListing(){
        setSize(450, 543);
        cvv.setBounds(176, 404, 61, 26);
        cvv.setColumns(10);
        cardNum.setBounds(176, 331, 177, 26);
        cardNum.setColumns(10);
        holderName.setBounds(176, 295, 177, 26);
        holderName.setColumns(10);
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

        JLabel payInfoLbl = new JLabel("Payment Information");
        payInfoLbl.setFont(new Font("Tahoma", Font.BOLD, 16));
        payInfoLbl.setBounds(41, 270, 182, 20);
        getContentPane().add(payInfoLbl);

        JLabel nameLbl = new JLabel("Card Holder Name");
        nameLbl.setBounds(41, 298, 131, 20);
        getContentPane().add(nameLbl);
        cardNumLbl.setBounds(41, 334, 107, 20);

        getContentPane().add(cardNumLbl);
        expiryDateLbl.setBounds(41, 370, 96, 20);

        getContentPane().add(expiryDateLbl);
        cvvLbl.setBounds(41, 407, 42, 20);

        getContentPane().add(cvvLbl);

        street = new JTextField();
        street.setBounds(157, 196, 256, 26);
        getContentPane().add(street);
        street.setColumns(10);

        houseNumber = new JTextField();
        houseNumber.setBounds(157, 196, 256, 26);
        getContentPane().add(houseNumber);
        houseNumber.setColumns(10);

        getContentPane().add(holderName);

        getContentPane().add(cardNum);

        getContentPane().add(cvv);

        JLabel currentFeeLbl = new JLabel("Current Fee: $");
        currentFeeLbl.setBounds(41, 246, 107, 20);
        getContentPane().add(currentFeeLbl);

        currentFee.setEditable(false);
        currentFee.setBounds(144, 246, 69, 24);
        getContentPane().add(currentFee);
        streetLbl.setBounds(41, 199, 69, 20);

        getContentPane().add(streetLbl);

        houseNumberLal.setBounds(41, 199, 69, 20);
        getContentPane().add(houseNumberLal);


        yearEx.setBounds(176, 367, 61, 26);

        getContentPane().add(yearEx);
        monthEx.setBounds(247, 367, 48, 26);

        getContentPane().add(monthEx);

    }

    /**
     * Getter method for Address
     * @throws IllegalClassFormatException
     * @return address value as Address class
     * */
    public Address getAddress() throws IllegalClassFormatException {
        CityQuadrant quad = null;
        quad.fromString((String) Objects.requireNonNull(quadrantBox.getSelectedItem()));
        Address toReturn= new Address(street.getText(),quad,Integer.parseInt(houseNumberLal.getText()));
        return toReturn;
    }

    /**
     * Getter function for bed type
     * @return int bed number
     * */
    public int getBeds(){
        return Integer.parseInt((String)bedBox.getSelectedItem());
    }

    /**
     * Getter funciton for number of baths
     * @return number of baths as an int
     * */
    public int getBaths(){
        return Integer.parseInt((String) bathBox.getSelectedItem());
    }

    /**
     * Getter function for furnished status
     * @return boolean value of furnished status
     * */
    public boolean isFurnished(){
        return Boolean.parseBoolean((String) furnished.getSelectedItem());
   }

    /**
     * Getter for property status
     * @return PropertyStatus type of property status
     * */
    public PropertyStatus getStatus(){
        PropertyStatus toReturn=null;
        toReturn=PropertyStatus.fromString("ACTIVE");
        return toReturn;
    }
}
