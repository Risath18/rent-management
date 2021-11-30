package View.Pages.Listing;

import Model.Property;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PropertyInfoView extends JFrame {
    private static final long serialUID=1L;
    private JButton emailButton=new JButton("Click to send an email");
    private JTextArea name=new JTextArea();
    private JTextArea address=new JTextArea();
    private JTextArea listing=new JTextArea();
    private Property property;

    public PropertyInfoView(Property ptr){
        property=ptr;
        setSize(500,400);
        getContentPane().setBackground(new Color(230, 230, 250));
        setTitle("Property View");
        getContentPane().setLayout(null);
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(15, 50, 69, 20);
        getContentPane().add(nameLabel);
        JLabel addressLbl = new JLabel("Address");
        addressLbl.setBounds(15, 50, 69, 20);
        getContentPane().add(addressLbl);
        JLabel listingLabel = new JLabel("Listing");
        listingLabel.setBounds(15, 50, 69, 20);
        getContentPane().add(listingLabel);

        emailButton.setBounds(81,239,247,29);
        getContentPane().add(emailButton);

        name.setEditable(false);
        name.setBounds(99, 50, 314, 24);
        getContentPane().add(name);

        address.setEditable(false);
        address.setBounds(99, 50, 314, 24);
        getContentPane().add(address);

        listing.setEditable(false);
        listing.setBounds(99, 50, 314, 24);
        getContentPane().add(listing);

        setName();
        setAddress();
        setListingStatus();
    }
    public void addingSendEmailListener(ActionListener a)
    {
        emailButton.addActionListener(a);
        emailButton.setActionCommand("email");
    }

    public void setName(){
        name.setText(property.getName());
    }

    public void setAddress(){
        address.setText(property.getAddress());
    }

    public void setListingStatus(){
        listing.setText(property.getListing());
    }
}
