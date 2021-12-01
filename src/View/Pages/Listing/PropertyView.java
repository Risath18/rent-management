package View.Pages.Listing;

import Model.Property.Property;

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

        public void setTable(JTable table) {
                this.table = table;
        }

        public JTable getTable(){
                return this.table;
        }

        public void visibility(boolean b){
                frame.setVisible(b);
        }

        public abstract void actionMouse(MouseEvent e);

        public abstract void action(ActionEvent e);

        public void setDisplay(ArrayList<Property> prop){
                properties=prop;
        }

}
