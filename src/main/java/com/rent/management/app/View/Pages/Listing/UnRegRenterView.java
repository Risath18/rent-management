package com.rent.management.app.View.Pages.Listing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;

public class UnRegRenterView extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField fName;
    private JTextField lName;
    private JTextField uName;
    private JTextField email;
    private JPasswordField password;
    private JButton submit = new JButton("submit");

    /**
     * Default constructor for unregistered renter view
     * */

    public UnRegRenterView(){
        setSize(303, 295);
        getContentPane().setBackground(new Color(230, 230, 250));
        setTitle("Register");
        getContentPane().setLayout(null);

        JLabel fNameLbl = new JLabel("First Name");
        fNameLbl.setBounds(15, 16, 86, 20);
        getContentPane().add(fNameLbl);

        JLabel lNameLbl = new JLabel("Last Name");
        lNameLbl.setBounds(15, 52, 86, 20);
        getContentPane().add(lNameLbl);

        JLabel uNameLbl = new JLabel("User Name");
        uNameLbl.setBounds(15, 88, 86, 20);
        getContentPane().add(uNameLbl);

        JLabel pwLbl = new JLabel("Password");
        pwLbl.setBounds(15, 124, 69, 20);
        getContentPane().add(pwLbl);

        JLabel emailLbl = new JLabel("email");
        emailLbl.setBounds(15, 160, 69, 20);
        getContentPane().add(emailLbl);

        submit.setBounds(72, 196, 115, 29);
        getContentPane().add(submit);

        fName = new JTextField();
        fName.setBounds(116, 13, 146, 26);
        getContentPane().add(fName);
        fName.setColumns(10);

        lName = new JTextField();
        lName.setBounds(116, 52, 146, 26);
        getContentPane().add(lName);
        lName.setColumns(10);

        uName = new JTextField();
        uName.setBounds(116, 85, 146, 26);
        getContentPane().add(uName);
        uName.setColumns(10);

        email = new JTextField();
        email.setBounds(116, 157, 146, 26);
        getContentPane().add(email);
        email.setColumns(10);

        password = new JPasswordField();
        password.setBounds(116, 121, 146, 26);
        getContentPane().add(password);
    }
    /**
     * Setter for action listener for submission
     * @param al action listener for submition action
     * */
    public void submitListener(ActionListener al){
        submit.addActionListener(al);
        submit.setActionCommand("submit");
    }

    /**
     * Getter for first name
     * @return first name
     * */
    public String getFName(){
        return fName.getText();
    }

    /**
     * Getter for last name
     * @return last name
     * */
    public String getLName(){
        return lName.getText();
    }

    /**
     * Getter for password
     * @return password
     * */
    public String getPassword(){
        return String.copyValueOf(password.getPassword());
    }

    /**
     * Getter for username
     * @return username
     * */
    public String getUName(){
        return uName.getText();
    }

    /**
     * Getter for email address
     * @return email address
     * */
    public String getEmail(){
        return email.getText();
    }

//    public static void main(String[] args) {
//        UnRegRenterView unReg = new UnRegRenterView();
//        unReg.createView();
//        unReg.setVisible(true);
//
//    }

}
