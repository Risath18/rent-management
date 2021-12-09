package com.rent.management.app.View.Pages.AdminPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RequestReport extends  JFrame{
    private String[] year = {"2016", "2017", "2018", "2019","2020","2021","2022"};
    private String[] month = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
    private String[] date = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};

    private JButton generateReport = new JButton("Generate Report");
    private JComboBox yearStart = new JComboBox(year);
    private JComboBox monthStart = new JComboBox(month);
    private JComboBox dateStart = new JComboBox(date);
    private final JComboBox yearEnd = new JComboBox(year);
    private final JComboBox monthEnd = new JComboBox(month);
    private final JComboBox dateEnd = new JComboBox(date);

    /**
     * default constructor to report request
     */
    public RequestReport(){
        getContentPane().setBackground(new Color(230, 230, 250));
        getContentPane().setLayout(null);
        setSize(450, 250);
        JLabel lblStartDate = new JLabel("Start Date");
        lblStartDate.setBounds(33, 16, 78, 20);
        getContentPane().add(lblStartDate);

        JLabel lblEndDate = new JLabel("End Date");
        lblEndDate.setBounds(33, 69, 69, 20);
        getContentPane().add(lblEndDate);

        generateReport.setBounds(150, 120, 175, 29);
        getContentPane().add(generateReport);

        yearStart.setBounds(108, 13, 120, 26);
        getContentPane().add(yearStart);


        monthStart.setBounds(235, 13, 80, 26);
        getContentPane().add(monthStart);

        dateStart.setBounds(325, 13, 80, 26);
        getContentPane().add(dateStart);

        getContentPane().add(yearEnd);
        yearEnd.setBounds(108, 63, 120, 26);

        getContentPane().add(monthEnd);
        monthEnd.setBounds(235, 63, 80, 26);

        getContentPane().add(dateEnd);
        dateEnd.setBounds(325, 63, 80, 26);

        setTitle("Summary Report Request Form");
    }

    /**
     * adding a listener to generate a report
     * @param al action listener
     */
    public void addGenerateReportListener(ActionListener al)  {
        generateReport.addActionListener(al);
        generateReport.setActionCommand("generateReport");
    }

    /**
     * get year of start date
     * @return String value start year
     */
    public String getStartYear()  {
        return (String) yearStart.getSelectedItem();
    }

    /**
     * getter of month of start date
     * @return string month value
     */
    public String getStartMonth() {
        return (String) monthStart.getSelectedItem();
    }

    /**
     * getter for start date
     * @param string value of start date
     */
    public String getStartDate() {
        return (String) dateStart.getSelectedItem();
    }
    
    /**
     * getter for end date year
     * @return string end year
     */
    public String getEndYear() {
        return (String) yearEnd.getSelectedItem();
    }

    /**
     * getter for end date month
     * @return string end date month
     */
    public String getEndMonth() {
        return (String) monthEnd.getSelectedItem();
    }

    /**
     * getter for end date
     * @return end date
     */
    public String getEndDate() {
        return (String) dateEnd.getSelectedItem();
    }


}
