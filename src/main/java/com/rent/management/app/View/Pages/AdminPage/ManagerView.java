package com.rent.management.app.View.Pages.AdminPage;

import javax.swing.*;

import com.rent.management.app.Controller.ManagerController;

import java.awt.*;
import java.awt.event.ActionListener;

public class ManagerView extends JFrame {
    private static final long serialVersionUID = 1L;
    private JButton changeStatus = new JButton("Change Property Status");
    private JButton changeFee = new JButton("Change Fee");
    private JButton getUserInfo = new JButton("Get Information of Users");
    private JButton exit = new JButton("Exit");
    private JButton createNewReport = new JButton("Create New Summary Report");
    private ManagerController managerController;


    /**
     * ManagerView default constructor initializing view for manager to choose functionality
     * */
    public ManagerView(){
        setSize(431,310);
        getContentPane().setBackground(new Color(230, 230, 250));
        setTitle("Manager Menu");
        getContentPane().setLayout(null);

        changeStatus.setBounds(106, 16, 199, 29);
        getContentPane().add(changeStatus);

        getUserInfo.setBounds(106, 100, 199, 29);
        getContentPane().add(getUserInfo);

        changeFee.setBounds(149, 61, 115, 29);
        getContentPane().add(changeFee);

        //Add logic to getUser information after controller
        //is done

        exit.setBounds(149, 199, 115, 29);
        getContentPane().add(exit);

        createNewReport.setBounds(91, 151, 239, 29);
        getContentPane().add(createNewReport);
    }

    /**
     * Setting the status listener to a new action listener
     * @param al action listener to change the status
     * */
    public void changeStatusListner(ActionListener al){
        changeStatus.addActionListener(al);
        changeStatus.setActionCommand("changeStatus");
    }

    /**
     * Setting the fees change command action listener
     * @param al action listener to trigger fees changed event.
     * */
    public void changeFeesListner(ActionListener al){
        changeFee.addActionListener(al);
        changeFee.setActionCommand("fessChanged");
    }

    /**
     * Setting the action listener to get user info
     * @param al Action listener to trigger getting user information
     * */
    public void getUserInfo(ActionListener al){
        getUserInfo.addActionListener(al);
        getUserInfo.setActionCommand("viewPeople");
    }

    /**
     * Setting the action listener to get report
     * @param al action listener to trigger getting the report
     * */
    public void createReportListener(ActionListener al){
        createNewReport.addActionListener(al);
        createNewReport.setActionCommand("reportRequested");
    }

    /**
     * Setting the action listener to exit the GUI
     * @param al action listener to trigger exit
     * */
    public void exitListener(ActionListener al){
        exit.addActionListener(al);
        exit.setActionCommand("exit");
    }

    /**
     * setter for manager controller
     * @param cont manager controller
     */
    public void setManagerController(ManagerController cont){
        this.managerController = cont;
    }
}

