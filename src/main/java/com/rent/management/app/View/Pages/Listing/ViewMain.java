package com.rent.management.app.View.Pages.Listing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ViewMain  extends JFrame {
    private JButton landlord = new JButton("Landlord");
    private JButton renter = new JButton("Renter");
    private JButton manager = new JButton("Manager");

    /**
     * Default constructor for main view
     * */
    public ViewMain(){
        getContentPane().setBackground(new Color(230, 230, 250));
        setTitle("Main Menu");
        getContentPane().setLayout(null);
        setSize(450, 300);


        landlord.setBounds(66, 57, 115, 56);
        getContentPane().add(landlord);
        landlord.setActionCommand("Landlord");
        renter.setBounds(242, 57, 115, 56);
        getContentPane().add(renter);
        renter.setActionCommand("Registered");
        manager.setBounds(66, 141, 115, 56);
        getContentPane().add(manager);
        manager.setActionCommand("Manager");

        JLabel optionsLbl = new JLabel("Please select one of the options");
        optionsLbl.setBounds(96, 16, 222, 20);
        getContentPane().add(optionsLbl);

        JLabel exitLbl = new JLabel("Or click X to exit");
        exitLbl.setBounds(154, 213, 126, 20);
        getContentPane().add(exitLbl);
    }

    /**
     * Setter for action listener for adding a landlord event
     * @param al action listener to trigger adding a landlord
     * */
    public void addLandLord(ActionListener al){
        landlord.addActionListener(al);
    }

    /**
     * Setter for action listener for adding a renter event
     * @param al action listener to trigger adding a renter
     * */
    public void addRenter(ActionListener al){
        renter.addActionListener(al);
    }

    /**
     * Setter for action listener for adding a manager event
     * @param al action listener to trigger adding a manager
     * */
    public void addManager(ActionListener al){
        manager.addActionListener(al);
    }

}
