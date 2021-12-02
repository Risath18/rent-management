package View.Pages.AdminPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LandLordView extends JFrame {
    private static final long serialVersionUID = 1L;
    private JButton editBtn = new JButton("Edit Listing");
    private JButton createBtn = new JButton("Create Listing");
    private JButton exitBtn = new JButton("Exit Program");

    public LandLordView(){
        setSize(448, 318);
        getContentPane().setBackground(new Color(230, 230, 250));
        setTitle("Landlord Menu");
        getContentPane().setLayout(null);

        editBtn.setBounds(149, 36, 129, 29);
        getContentPane().add(editBtn);

        createBtn.setBounds(149, 112, 129, 29);
        getContentPane().add(createBtn);

        exitBtn.setBounds(149, 186, 129, 29);
        getContentPane().add(exitBtn);
    }

    public void editListener(ActionListener al){
        editBtn.addActionListener(al);
        editBtn.setActionCommand("editProperty");
    }

    public void createListener(ActionListener al){
        createBtn.addActionListener(al);
        createBtn.setActionCommand("createProperty");
    }

    public void exitListner(ActionListener al){
        exitBtn.addActionListener(al);
        exitBtn.setActionCommand("exit");
    }
}
