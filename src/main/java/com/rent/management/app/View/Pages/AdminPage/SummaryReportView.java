package com.rent.management.app.View.Pages.AdminPage;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class SummaryReportView {
    private String[] col = {"Landlord name", "Property ID", "Address"};
    private JFrame frame = new JFrame("Summary Report");
    private JTextArea houseList = new JTextArea();
    private JTextArea houseRent = new JTextArea();
    private JTextArea activeListing = new JTextArea();
    private JButton btnClose = new JButton("Close");

    /**
     * summary report view constructor
     * @param data 2D array of string containing report data
     */
    public SummaryReportView(String [][]data){
        frame.setSize(551, 483);
        frame.getContentPane().setBackground(new Color(230, 230, 250));
        frame.getContentPane().setLayout(null);

        JLabel listedLbl = new JLabel("Number of Houses Listed: ");
        listedLbl.setBounds(15, 16, 189, 20);
        frame.getContentPane().add(listedLbl);

        JLabel rentedLbl = new JLabel("Number of Houses Rented:");
        rentedLbl.setBounds(15, 51, 197, 20);
        frame.getContentPane().add(rentedLbl);

        JLabel activeLbl = new JLabel("Number of Active Listings:");
        activeLbl.setBounds(15, 87, 189, 20);
        frame.getContentPane().add(activeLbl);
        houseList.setBounds(208, 16, 66, 24);

        houseList.setEditable(false);
        houseList.setEnabled(false);
        houseList.setForeground(new Color(0, 0, 0));
        frame.getContentPane().add(houseList);
        houseRent.setBounds(208, 52, 66, 24);

        houseRent.setForeground(Color.BLACK);
        houseRent.setEnabled(false);
        houseRent.setEditable(false);
        frame.getContentPane().add(houseRent);
        activeListing.setBounds(208, 87, 66, 24);

        activeListing.setForeground(Color.BLACK);
        activeListing.setEnabled(false);
        activeListing.setEditable(false);
        frame.getContentPane().add(activeListing);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 123, 530, 2);
        frame.getContentPane().add(separator);

        JLabel listLbl = new JLabel("List of Houses Rented");
        listLbl.setBounds(10, 132, 194, 20);
        listLbl.setFont(new Font("Tahoma", Font.BOLD, 16));
        frame.getContentPane().add(listLbl);
        btnClose.setBounds(208, 398, 115, 29);
        frame.getContentPane().add(btnClose);

        JTable table = new JTable(data, col) {
            @Override
            public boolean isCellEditable(int row,int column) {
                return false;
            }
        };

        JScrollPane scrollpane = new JScrollPane(table);
        scrollpane.setBounds(15, 156, 509, 218);
        scrollpane.setVerticalScrollBarPolicy(scrollpane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.getContentPane().add(scrollpane, BorderLayout.CENTER);

        table.setBounds(15, 156, 481, 218);
        //getContentPane().add(table);
        TableColumnModel tcm = table.getColumnModel();
        scrollpane.setViewportView(table);

        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);

        for(int i = 0; i < tcm.getColumnCount(); i++) {
            tcm.getColumn(i).setCellRenderer(dtcr);
        }

        frame.setResizable(false);
        frame.setVisible(true);
    }

    /**
     * setter for close functionality action listener
     * @param al action listener
     */
    public void addCloseListener(ActionListener al)  {
        btnClose.addActionListener(al);
        btnClose.setActionCommand("closeReport");
    }

    /**
     * setter for number house list
     * @param n number of houses listed
     */
    public void setNumHouseList(int n) {
        houseList.setText(Integer.toString(n));
    }

    /**
     * setter for number of houses rented
     * @param n number of houses rented
     */
    public void setNumHouseRent(int n) {
        houseRent.setText(Integer.toString(n));
    }

    /**
     * setter for number of active listings
     * @param n number of active listing
     */
    public void setNumActiveList(int n) {
        activeListing.setText(Integer.toString(n));
    }

    /**
     * setter of visibility
     * @param v view status
     */
    public void setVisible(boolean v) {
        if (!v) {
            frame.setVisible(false);
        }
    }
}
