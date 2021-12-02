package View.Pages.Listing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RenterMenuView extends JFrame {
    private static final long serialVersionUID = 1L;
    private JButton searchBtn = new JButton("Search");
    private JButton exitBtn = new JButton("Exit Program");

    /**
     * Default constructor for renter menu view
     * */
    public RenterMenuView() {
        setSize(418, 281);
        getContentPane().setBackground(new Color(230, 230, 250));
        setTitle("Renter Menu");
        getContentPane().setLayout(null);

        searchBtn.setBounds(124, 46, 129, 29);
        getContentPane().add(searchBtn);

        exitBtn.setBounds(124, 160, 132, 29);
        getContentPane().add(exitBtn);
    }

    /**
     * Action listener for search funcitonality
     * @param al action listener to trigger search
     * */
    public void searchListener(ActionListener al)  {
        searchBtn.addActionListener(al);
        searchBtn.setActionCommand("search");
    }

    /**
     * Setter for action listener to exit
     * @param al action listener to trigger exit
     * */
    public void exitListener(ActionListener al)  {
        exitBtn.addActionListener(al);
        exitBtn.setActionCommand("exit");
    }

//    public static void main(String[] args) {
//        RenterMenuView ren=new RenterMenuView();
//        ren.setVisible(true);
//    }
}
