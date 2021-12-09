package com.rent.management.app.View.Pages.AdminPage;

import java.awt.*;
import javax.swing.*;

import com.rent.management.app.Controller.GuestController;
import com.rent.management.app.Controller.PropertyController;

import java.awt.event.*;

/*
 An emailView for a registered/unregistered renter to email the landlord
*/
public class EmailView extends JFrame {
    private JTextField subject;
    private JTextField from;
    private JTextField to;
    private JTextArea email = new JTextArea();
    private JButton btnSend = new JButton("send");
    private GuestController gc;
    private PropertyController propController;
    //This is a default constructor for the email view 
    //Takes in a string to send that email to as a parameter
    public EmailView(String to){
        setSize(452, 450);
        getContentPane().setBackground(new Color(230, 230, 250));
        setTitle("Send Email");
        getContentPane().setLayout(null);

        JLabel fromLbl = new JLabel("From:");
        fromLbl.setBounds(15, 16, 69, 20);
        getContentPane().add(fromLbl);

        JLabel subjectLbl = new JLabel("Subject:");
        subjectLbl.setBounds(15, 52, 69, 20);
        getContentPane().add(subjectLbl);

        email.setLocation(15, 0);
        email.setLineWrap(true);

        getContentPane().add(email);
        JScrollPane scroll = new JScrollPane (email);
        scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        scroll.setBounds(10, 88, 403, 262);
        getContentPane().add(scroll);

        btnSend.setBounds(150, 358, 115, 29);
        getContentPane().add(btnSend);

        subject = new JTextField();
        subject.setBounds(77, 52, 336, 26);
        getContentPane().add(subject);
        subject.setColumns(10);

        from = new JTextField();
        from.setBounds(74, 10, 146, 26);
        getContentPane().add(from);
        from.setColumns(10);
        setLocationRelativeTo ( null );
    }
    // A Listener to send that email
    public void addSendListener(ActionListener al)  {
        btnSend.addActionListener(al);
        btnSend.setActionCommand("send email");
    }
    //Get the sender address
    public String getFrom() {
        return from.getText();
    }
    //Get the email body
    public String getEmail() {
        return email.getText();
    }
    //Get the email subject
    public String getSubject() {
        return subject.getText();
    }
    // The receiving address
    public void setTo(String ll) {
        to.setText(ll);
    }

    public void setPropertyController(PropertyController arg){
        this.propController = arg;
    }

    public void setGuestController(GuestController arg){
        this.gc = arg;
    }

//    public static void main(String[] args) {
//        EmailView emailView=new EmailView("test@gmail.com");
//        emailView.setVisible(true);
//    }
}
