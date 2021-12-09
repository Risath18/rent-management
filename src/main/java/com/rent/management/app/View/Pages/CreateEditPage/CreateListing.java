package com.rent.management.app.View.Pages.CreateEditPage;

import javax.swing.*;
import java.awt.*;
import com.rent.management.app.Model.*;
import com.rent.management.app.Model.Property.Address;
import com.rent.management.app.Model.Property.CityQuadrant;
import com.rent.management.app.Model.Property.PropertyStatus;

import org.json.simple.JSONObject;

import java.awt.event.ActionListener;
import java.lang.instrument.IllegalClassFormatException;
import java.text.*;
import java.util.Objects;

public class CreateListing extends JFrame{
    private static final long serialVersionUID = 1L;
    private String [] propertyType={"Apartment", "Attached", "Detached", "Townhouse ", "Other"};
    private String []cityQuadrant={"SW","NW","SE","NE"};
    private String[] numbers={"1","2","3","4","5", "6"};
    private String[] furnish={"Yes","No"};

    private JComboBox typeBox=new JComboBox(propertyType);
    private JComboBox bedBox=new JComboBox(numbers);
    private JComboBox bathBox = new JComboBox(numbers);
    private JComboBox quadrantBox= new JComboBox(cityQuadrant);
    private JComboBox furnished=new JComboBox(furnish);
    private JButton submit=new JButton("Submit");
    private final JLabel cardNumLbl = new JLabel("Card Number");
    private final JLabel expiryDateLbl = new JLabel("Expiry Date");
    private final JLabel cvvLbl = new JLabel("CVV");
    private final JLabel addressLbl = new JLabel("Address");
    private JTextField holderName = new JTextField();
    private JTextField cardNum = new JTextField();
    private JTextField cvv = new JTextField();
    private JTextArea currentFee = new JTextArea();
    private JTextField address;

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

        bedBox.setBounds(157, 52, 150, 26);
        getContentPane().add(bedBox);

        bathBox.setBounds(157, 88, 150, 26);
        getContentPane().add(bathBox);

        furnished.setBounds(157, 120, 150, 26);
        getContentPane().add(furnished);

        quadrantBox.setBounds(157, 160, 150, 26);
        getContentPane().add(quadrantBox);

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
        cvvLbl.setBounds(41, 407, 100, 20);

        getContentPane().add(cvvLbl);

        address = new JTextField();
        address.setBounds(157, 200, 256, 26);
        getContentPane().add(address);
        address.setColumns(10);
        addressLbl.setBounds(41, 200, 69, 20);

        getContentPane().add(holderName);

        getContentPane().add(cardNum);

        getContentPane().add(cvv);

        JLabel currentFeeLbl = new JLabel("Current Fee: $");
        currentFeeLbl.setBounds(41, 240, 107, 20);
        getContentPane().add(currentFeeLbl);

        currentFee.setEditable(false);
        currentFee.setBounds(157, 240, 69, 24);
        getContentPane().add(currentFee);

        getContentPane().add(addressLbl);

        // houseNumberLal.setBounds(41, 210, 69, 20);
        // getContentPane().add(houseNumberLal);


        yearEx.setBounds(176, 367, 80, 26);

        getContentPane().add(yearEx);
        monthEx.setBounds(260, 367, 70, 26);

        getContentPane().add(monthEx);

    }

    /**
     * Getter method for Address
     * @throws IllegalClassFormatException
     * @return JSON object to be parsed
     * */
    public JSONObject getAddress() throws IllegalClassFormatException {
        JSONObject obj = new JSONObject();
        obj.put("address", address.getText());
        return obj;
    }

    /**
     * getter method for type
     * @return returns string for type of property
     */
    public String getPropertyType(){
        return (typeBox.getSelectedItem().toString());
    }

    /**
     * getter for quadrant
     * @return returns string for quadrant of property
     */
    public String getQuadrant(){
        return quadrantBox.getSelectedItem().toString();
    }

    /**
     * Getter function for bed type
     * @return int bed number
     * */
    public int getBeds(){
        return Integer.parseInt(bedBox.getSelectedItem().toString());
    }

    /**
     * Getter funciton for number of baths
     * @return number of baths as an int
     * */
    public int getBaths(){
        return Integer.parseInt( bathBox.getSelectedItem().toString());
    }

    /**
     * Getter function for furnished status
     * @return boolean value of furnished status
     * */
    public String isFurnished(){
        return furnished.getSelectedItem().toString();
   }


    /**
     * An actionlistener to trigger when submit is pressed
     * @return void
     */
    public void addSubmitListener(ActionListener al)  {
        submit.addActionListener(al);
        submit.setActionCommand("addSubmit");
    }


    /**
     * Setter for fees
     */
    public void setCurrentFee(String fee) {
		currentFee.setText(fee);
	}
}
