package com.rent.management.app.View.Pages.LoginPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class Login extends JFrame implements LoginPage{
    public Login(){}
    static final long serialVersionUID = 1L;
    private JTextField nameInput;
    private JButton login = new JButton("login");
    private JButton signUp = new JButton("Sign Up");
    private JPasswordField password;

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

        password = new JPasswordField();
        password.setBounds(168, 98, 146, 26);
        getContentPane().add(password);

        JLabel instruction = new JLabel("Please enter your username and password");
        instruction.setBounds(96, 31, 305, 20);
        getContentPane().add(instruction);

        password = new JPasswordField();
        password.setBounds(168, 98, 146, 26);
        getContentPane().add(password);
    }


    public void register () {
    }


    public void addLoginListener (ActionListener al){
        login.addActionListener(al);
    }

    public void addNewListener (ActionListener al){
        signUp.addActionListener(al);
        signUp.setActionCommand("register");
    }

    public String getUsername () {
        return nameInput.getText();
    }

    public String getPassword () {
        return String.copyValueOf(password.getPassword());
    }

//    public static void main(String args[]) {
//        UnRegLoginPage instance = new UnRegLoginPage();
//        instance.Login();
//        instance.setVisible(true);
//        instance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }
    
}
