package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import control.SettingsActionListener;
import model.MailPreferences;

import javax.swing.*;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class SettingsView extends JFrame {
	 // Array for the JComboBox
    String comboBoxListe[] = {"Deutsch", "English"};

    ResourceBundle rb = ResourceBundle.getBundle("languages.ressources", main.Main.getLanguage());
	
	//set the inputs for the user informations
    JTextField mailAddress= new JTextField();
    JPasswordField password = new JPasswordField(20);
    
    JTextField hostPOP = new JTextField();
    JTextField hostSMTP = new JTextField();
    JTextField hostIMAP = new JTextField();
    JComboBox<?> languageBox;
    JTextField  port = new JTextField();
    
    ActionListener listener = new SettingsActionListener(this);
    
    //for single start
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
    	setPreferences();
        initialize();
    }
    
    public void setPreferences(){
    	MailPreferences prefs = model.MailPreferences.getMailPreferences();
    	
	    mailAddress.setText(prefs.getUserAdress());
	    password.setText(prefs.getPassword());
     	hostPOP.setText(prefs.getPop3Address());
    	hostSMTP.setText(prefs.getSmtpAddress());
	    hostIMAP.setText(prefs.getImapAddress());
    }
   
private void initialize() {
	
        setTitle("Settings");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(new Dimension(600,280));

        getContentPane().setLayout(new BorderLayout());

        // Body
        JPanel body = new JPanel();
        body.setLayout(new GridLayout(6, 2));

        body.add(new JLabel(rb.getString("email")));
        body.add(mailAddress);
        
        body.add(new JLabel(rb.getString("password")));
        body.add(password);

        body.add(new JLabel(rb.getString("hostpop3")));
        body.add(hostPOP);
        
        body.add(new JLabel(rb.getString("hostsmtp")));
        body.add(hostSMTP);
        
        body.add(new JLabel(rb.getString("hostimap")));
        body.add(hostIMAP);
        
        body.add(new JLabel("Language"));
        
        //Footer
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new FlowLayout());
        
        JButton saveExit = new JButton(rb.getString("savelogin"));
        footerPanel.add(saveExit, BorderLayout.SOUTH);

        JButton discard = new JButton(rb.getString("exit"));
        footerPanel.add(discard, BorderLayout.EAST);
        
        getContentPane().add(body, BorderLayout.CENTER);
        getContentPane().add(footerPanel, BorderLayout.SOUTH);
        
        //Save function not exists until now.
        saveExit.setActionCommand("saveSettings");
        saveExit.addActionListener(listener);
        
        discard.setActionCommand("discardSettings");
        discard.addActionListener(listener);
        
        //JComboBox with the possible Languages
        languageBox = new JComboBox<Object>(comboBoxListe);
        languageBox.addActionListener(listener);
        languageBox.setActionCommand("language");
        
        //JComboBox to the Panel
        body.add(languageBox);
       
    }

	public String getUser(){
		return mailAddress.getText();
	}
	
	public String getPass(){
		return String.valueOf(password.getPassword());
	}
	
	public String getPOP3Host(){
		return hostPOP.getText();
	}
	
	public String getSMTPHost(){
		return hostSMTP.getText();
	}
	
	public String getIMAPHost(){
		return hostIMAP.getText();
	}
	
	public String getLanguage(){
		return (String) languageBox.getSelectedItem();
	}


}