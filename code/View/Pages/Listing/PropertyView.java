package View.Pages.Listing;

import Model.Property.Property;
import View.Pages.CreateEditPage.EditPropertyView;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public abstract class PropertyView implements ActionListener {
    String[] columns ={"Property Type", "Beds","Baths","Furnished","Status","Address"};
    protected JFrame frame;
    // protected String [][]tableData;
    protected ArrayList<Property> properties=new ArrayList<Property>();
    protected PropertyInfoView propInfo;
    private JTable table;
    protected EditPropertyView editView;

    /**
     * Property view constructor
     * @param addToTable double array of string with property information
     * */
    public PropertyView(String [][]addToTable){
        Dimension dim=new Dimension(1000,200);
        frame = new JFrame("Property Listing");
        frame.setLocation(200,400);
        frame.setSize(1000,2000);
        setTable(new JTable(addToTable, columns));
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
        visibility(true);

        getTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //The following is mouse UI which is to be added later
        getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                actionMouse(e);
            }
        });
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

    /**
     * Setter of frame visibility
     * @param b boolean value for frame visibility
     * */
     public void visibility(boolean b){
                frame.setVisible(b);
        }

    /**
     * Abstract function for mouse listener to be implemented by children
     * */
     public abstract void actionMouse(MouseEvent e);

    /**
     * Abstract action listener to be implemented by children
     * */
     public abstract void actionPerformed(ActionEvent e);

    /**
     * Setter for display
     * @param prop array list of properties for properties
     * */
     public void setDisplay(ArrayList<Property> prop){
                properties=prop;
        }

}
