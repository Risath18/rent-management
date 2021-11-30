package View.Login;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

public class UnRegLoginPage {
    private JFrame frame; // content frame
    private JPanel textPane; // pane for text fields
    private JPanel buttonPane; // pane fore buttons
    private JPanel mainPane; // the main pane

    private JTextField username; // username text field
    private JPasswordField password; // password text field
    private JButton loginButton; // log-in button
    private JButton registerButton; // register button
    private JTextArea title; // title text component

    public static void main (String [] args) {
        UnRegLoginPage page = new UnRegLoginPage();
    }

    public UnRegLoginPage () {
        // Initialize frame and panels
        frame = new JFrame("Log In");
        frame.setSize(400,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        textPane = new JPanel();
        textPane.setLayout(new BoxLayout(textPane, BoxLayout.Y_AXIS));
        buttonPane = new JPanel();
        mainPane = new JPanel();
        // Initialize components
        username = new JTextField(15);
        password = new JPasswordField(15);
        loginButton = new JButton("Log In");
        registerButton = new JButton("Register");
        title = new JTextArea("Please enter your username and password to login.");
        title.setEditable(false);
        // Add all components to frame
        textPane.add(title);
        textPane.add(username);
        textPane.add(password);
        buttonPane.add(loginButton);
        buttonPane.add(registerButton);
        // set content
        mainPane.add(textPane);
        mainPane.add(buttonPane);
        frame.add(mainPane);
        // set the frame to visible
        frame.setVisible(true);
    }

//    @Override
    public void login() {
        // populate once skeleton functions
        System.out.println("Logging in...");
    }

//    @Override
    public void register() {
        // populate once skeleton works
        System.out.println("Registering...");
    }
}
