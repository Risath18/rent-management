package View.Pages.Listing;

import Model.Property.Property;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PropertyInfoView extends JFrame {
    private static final long serialVersionUID = 1L;
    private JButton emailButton = new JButton("Click to send email to landlord");
    private JTextArea ID = new JTextArea();
    private JTextArea address = new JTextArea();
    private JTextArea numOfBeds = new JTextArea();
    private JTextArea numOfBaths = new JTextArea();
    private JTextArea furnished = new JTextArea();
    private JTextArea status = new JTextArea();
    private Property property;

    /**
     * Constructor for property information view
     * @param ptr property pointer to view
     * */
    public PropertyInfoView(Property ptr){
        property = ptr;
        setSize(456, 335);
        getContentPane().setBackground(new Color(230, 230, 250));
        setTitle("Property View");
        getContentPane().setLayout(null);

        JLabel addressLbl = new JLabel("Address");
        addressLbl.setBounds(15, 50, 69, 20);
        getContentPane().add(addressLbl);

        JLabel idLbl = new JLabel("ID");
        idLbl.setBounds(15, 16, 69, 20);
        getContentPane().add(idLbl);

        JLabel bedLbl = new JLabel("Number of bedrooms");
        bedLbl.setBounds(15, 86, 152, 20);
        getContentPane().add(bedLbl);

        JLabel bathLbl = new JLabel("Number of bathrooms");
        bathLbl.setBounds(15, 122, 162, 20);
        getContentPane().add(bathLbl);

        JLabel furnishedLbl = new JLabel("Furnished");
        furnishedLbl.setBounds(15, 158, 69, 20);
        getContentPane().add(furnishedLbl);

        JLabel quadLbl = new JLabel("City Quadrant");
        quadLbl.setBounds(15, 191, 98, 20);
        getContentPane().add(quadLbl);

        emailButton.setBounds(81, 239, 247, 29);
        getContentPane().add(emailButton);

        ID.setEditable(false);
        ID.setBounds(99, 16, 118, 24);
        getContentPane().add(ID);

        address.setEditable(false);
        address.setBounds(99, 50, 314, 24);
        getContentPane().add(address);

        numOfBeds.setEditable(false);
        numOfBeds.setBounds(182, 86, 35, 24);
        getContentPane().add(numOfBeds);

        numOfBaths.setEditable(false);
        numOfBaths.setBounds(182, 122, 35, 24);
        getContentPane().add(numOfBaths);

        furnished.setEditable(false);
        furnished.setBounds(115, 158, 52, 24);
        getContentPane().add(furnished);

        status.setEditable(false);
        status.setBounds(125, 191, 52, 24);
        getContentPane().add(status);

        setIDField();
        setAddressField();
        setNumOfBedField();
        setNumOfBathField();
        setFurnished();
        setStatus();
    }

    /**
     * Setter for sending an email listener
     * @param a Action listener to listen for emails
     * */
    public void addingSendEmailListener(ActionListener a)
    {
        emailButton.addActionListener(a);
        emailButton.setActionCommand("email");
    }

    /**
     * Setter for ID from property id
     * */
    public void setIDField(){
        ID.setText(Integer.toString(property.getPropertyId()));
   }

    /**
     * Setter for address field from property address
     * */
    public void setAddressField(){
        address.setText(property.getAddress().getFormattedAddress());
   }

    /**
     * Setter of beds from property beds
     * */
    public void setNumOfBedField(){
        numOfBeds.setText(Integer.toString(property.getNumOfBed()));
   }

    /**
     * Setter of property status from property status
     * */
    public void setStatus(){
        status.setText(property.getPropertyStatus().toString());
   }

    /**
     * Setter for number of baths from property bath count
     * */
    public void setNumOfBathField(){
        numOfBaths.setText(Integer.toString(property.getNumOfBath()));
   }

    /**
     * Setter of furnished status from property furnished status
     * */
    public void setFurnished(){
        furnished.setText(Boolean.toString(property.isFurnished()));
   }
}
