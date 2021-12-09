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
    private JButton btnSend = new JButton("Send");
    private GuestController gc;
    private PropertyController propController;
    private String landLord;
    //This is a default constructor for the email view 
    //Takes in a string to send that email to as a parameter
    /**
     * constructor for email view
     * @param to to email address
     */
    public EmailView(String to){
        landLord = to;
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
    
    /**
     * action listener to send an email
     * @param al action listener
     */
    public void addSendListener(ActionListener al)  {
        btnSend.addActionListener(al);
        btnSend.setActionCommand("sendEmail");
    }

    /**
     * getter for the sender address
     * @return sender address
     */
    public String getFrom() {
        return from.getText();
    }

    /**
     * getter for email message
     * @return message
     */
    public String getMessage() {
        return email.getText();
    }

    /**
     * getter for the email subject
     * @return email subject
     */
    public String getSubject() {
        return subject.getText();
    }

    /**
     * setter for reveiving address (debugging function only)
     * @param ll print out
     */
    public void setTo(String ll) {
        from.setText(ll);
        from.setEditable(false);
    }

    /**
     * setter for property controller
     * @param arg property controller
     */
    public void setPropertyController(PropertyController arg){
        this.propController = arg;
    }

    /**
     * setter for guest controller
     * @param arg guest controller
     */
    public void setGuestController(GuestController arg){
        this.gc = arg;
    }

    /**
     * getter for landlord email
     * @return landlord's email
     */
    public String getLandlordEmail(){
        return landLord;
    }

//    public static void main(String[] args) {
//        EmailView emailView=new EmailView("test@gmail.com");
//        emailView.setVisible(true);
//    }
}
