package View.Pages.AdminPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ManagerView extends JFrame {
    private static final long serialVersionUID = 1L;
    private JButton changeStatus = new JButton("Change Property Status");
    private JButton changeFee = new JButton("Change Fee");
    private JButton getUserInfo = new JButton("Get Information of Users");
    private JButton exit = new JButton("Exit");
    private JButton createNewReport = new JButton("Create New Summary Report");

    public ManagerView(){
        setSize(431,310);
        getContentPane().setBackground(new Color(230, 230, 250));
        setTitle("Manager Menu");
        getContentPane().setLayout(null);

        changeStatus.setBounds(106, 16, 199, 29);
        getContentPane().add(changeStatus);

        changeFee.setBounds(149, 61, 115, 29);
        getContentPane().add(changeFee);

        //Add logic to getUser information after controller
        //is done

        exit.setBounds(149, 199, 115, 29);
        getContentPane().add(exit);

        createNewReport.setBounds(91, 151, 239, 29);
        getContentPane().add(createNewReport);
    }

    public void changeStatusListner(ActionListener al){
        changeStatus.addActionListener(al);
        changeStatus.setActionCommand("changeStatus");
    }

    public void changeFeesListner(ActionListener al){
        changeFee.addActionListener(al);
        changeFee.setActionCommand("fessChanged");
    }

    public void getUserInfo(ActionListener al){
        getUserInfo.addActionListener(al);
        getUserInfo.setActionCommand("getUserInfo");
    }

    public void createReportListener(ActionListener al){
        createNewReport.addActionListener(al);
        createNewReport.setActionCommand("reportRequested");
    }

    public void exitListener(ActionListener al){
        exit.addActionListener(al);
        exit.setActionCommand("exit");
    }
}
