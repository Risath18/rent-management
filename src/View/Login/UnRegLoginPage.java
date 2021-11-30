package View.Login;

import javax.swing.*;

public class UnRegLoginPage {
    JFrame frame; // content frame
    JPanel panel;
    JTextField username; // username text field
    JPasswordField password; // password text field
    JButton login_button;
    JButton register_button;
    // log-in button
    // register button

    public static void main (String [] args) {
        UnRegLoginPage page = new UnRegLoginPage();
    }

    public UnRegLoginPage () {
        // Initialize frame and panel
        frame = new JFrame("Log In");
        frame.setSize(600,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        // Initialize components
        username = new JTextField(25);
        password = new JPasswordField(25);
        login_button = new JButton("Log In");
        register_button = new JButton("Register");
        // Add all components to frame
        panel.add(username);
        panel.add(password);
        panel.add(login_button);
        panel.add(register_button);
        frame.setContentPane(panel);
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
