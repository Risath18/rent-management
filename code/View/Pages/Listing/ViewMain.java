package View.Pages.Listing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ViewMain  extends JFrame {
    private JButton landlord = new JButton("Landlord");
    private JButton renter = new JButton("Renter");
    private JButton manager = new JButton("Manager");

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

    public void addLandLord(ActionListener al){
        landlord.addActionListener(al);
    }

    public void addRenter(ActionListener al){
        renter.addActionListener(al);
    }

    public void addManager(ActionListener al){
        manager.addActionListener(al);
    }

}
