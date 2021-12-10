package com.rent.management.app.View.Pages.LoginPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class Login extends JFrame implements LoginPage{
    static final long serialVersionUID = 1L;
    private JTextField nameInput;
    private JButton login = new JButton("login");
    private JButton signUp = new JButton("Sign Up");
    private JTextField password;
    
    /**
     * default constructor
     */
    public Login(){}

    /**
     * default constructor for login
     */
    public void Login () {
        setSize(518, 209);
        getContentPane().setBackground(new Color(230, 230, 250));
        setTitle("Login");
        getContentPane().setLayout(null);

        nameInput = new JTextField();
        nameInput.setBounds(168, 64, 146, 26);
        getContentPane().add(nameInput);
        nameInput.setColumns(10);


        login.setBounds(329, 98, 96, 27);
        getContentPane().add(login);

        JLabel nameLbl = new JLabel("Username");
        nameLbl.setBounds(69, 67, 84, 20);
        getContentPane().add(nameLbl);

        JLabel pwLbl = new JLabel("Password");
        pwLbl.setBounds(69, 102, 69, 20);
        getContentPane().add(pwLbl);

        signUp.setBounds(329, 64, 96, 27);
        getContentPane().add(signUp);


        JLabel instruction = new JLabel("Please enter your username and password");
        instruction.setBounds(96, 31, 305, 20);
        getContentPane().add(instruction);

        password = new JTextField();
        password.setBounds(168, 98, 146, 26);
        getContentPane().add(password);
    }

    /**
     * Registering a user
     */
    public void register () {
    }

    /**
     * adding a login action listener
     * @param al action listener
     */
    public void addLoginListener (ActionListener al){
        login.addActionListener(al);
    }

    /**
     * adding a new action listener
     * @param al action listener
     */
    public void addNewListener (ActionListener al){
        signUp.addActionListener(al);
        signUp.setActionCommand("register");
    }

    /**
     * getter method for username
     * @return inputted username
     */
    public String getUsername () {
        return nameInput.getText();
    }

    /**
     * getter method for password
     * @return password
     */
    public String getPassword () {
        return password.getText();
    }
    
}
