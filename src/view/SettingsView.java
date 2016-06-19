package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import control.ButtonActionListener;

import javax.swing.*;
import javax.swing.JTextField;

public class SettingsView extends JFrame {
	
	//set the inputs for the user informations
    JTextField mailAddress= new JTextField();
    JPasswordField password = new JPasswordField(20);
    JTextField host = new JTextField();
    JTextField  port = new JTextField();
    
    ActionListener listener = new ButtonActionListener(this);
    
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
        initialize();
    }
   
private void initialize() {
	
        setTitle("Settings");
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setSize(new Dimension(600,280));

        getContentPane().setLayout(new BorderLayout());

        // Body
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

        //Footer
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new FlowLayout());
        
        JButton saveExit = new JButton("Save and Exit");
        footerPanel.add(saveExit, BorderLayout.SOUTH);

        JButton discard = new JButton("Discard");
        footerPanel.add(discard, BorderLayout.EAST);
        
        getContentPane().add(body, BorderLayout.CENTER);
        getContentPane().add(footerPanel, BorderLayout.SOUTH);
        
        //Save function not exists until now.
        saveExit.setActionCommand("saveSettings");
        saveExit.addActionListener(listener);
        
        discard.setActionCommand("discardSettings");
        discard.addActionListener(listener);
       
    }

	public String getSender(){
		return mailAddress.getText();
	}


}
