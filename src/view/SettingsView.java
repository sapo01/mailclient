package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.JTextField;

public class SettingsView extends JFrame {
	
	//set the inputs for the userinformations
    JTextField mailAddress= new JTextField();
    JPasswordField password = new JPasswordField(20);
    JTextField host = new JTextField();
    JTextField  port = new JTextField();

    public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
        	SettingsView settings = new SettingsView();
        	settings.setVisible(true);
        }
    });    
    }

    public SettingsView() {
        Initialize();
    }
   
private void Initialize() {
        setTitle("Settings");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(600,280));

        getContentPane().setLayout(new BorderLayout());

        // Body Panel
        JPanel body = new JPanel();
        body.setLayout(new GridLayout(6, 2));

        body.add(new JLabel("E-Mail:"));
        body.add(mailAddress);
        
        body.add(new JLabel("Password:"));
        body.add(password);

        body.add(new JLabel("Host:"));
        body.add(host);
        
        body.add(new JLabel("Port:"));
        body.add(port);

 //     headerPanel.add(new JLabel("STMP Server:"));
 //     headerPanel.add(mailServer);
 //     mailServer.addItem("smtp.gmail.com");
 //
 //     headerPanel.add(usernameField);
 //     headerPanel.add(new JLabel("Password:"));
 //     headerPanel.add(passwordField);


        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BorderLayout());
        JButton sendMailButton = new JButton("Save and Exit");
        footerPanel.add(sendMailButton, BorderLayout.SOUTH);

        getContentPane().add(body, BorderLayout.CENTER);
        getContentPane().add(footerPanel, BorderLayout.SOUTH);
    }
}
