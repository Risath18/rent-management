package com.rent.management.app.View.Pages.AdminPage;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

public class ManagerUserInfo extends JFrame{
    String []columns = {"Email", "Name", "Role"};

    protected JFrame frame;
    private JTable table;
    public ManagerUserInfo(String [][]data){
        Dimension dim=new Dimension(1000,200);
        frame = new JFrame("User Information");
         frame.setLocation(200,400);
        frame.setSize(1000,2000);
        setTable(new JTable(data, columns){
            @Override
            public boolean isCellEditable(int row,int column) {
                return false;
            }
        });
        JScrollPane scrollPane=new JScrollPane(getTable());

         scrollPane.setPreferredSize(dim);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.getContentPane().add(scrollPane,BorderLayout.CENTER);

        DefaultTableCellRenderer cellRenderer=new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        TableColumnModel columnModel=getTable().getColumnModel();

        for(int i=0;i<columnModel.getColumnCount();i++){
            columnModel.getColumn(i).setCellRenderer(cellRenderer);
        }

        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

        getTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    /**
     * Setter for property table
     * */
    public void setTable(JTable table) {
                this.table = table;
        }

    /**
     * Getter of property table
     * @return property table
     * */
        public JTable getTable(){
                return this.table;
        }
}