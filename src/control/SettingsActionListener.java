package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import model.MailPreferences;
import view.SettingsView;

public class SettingsActionListener implements ActionListener {

	private SettingsView settings;
	
	public SettingsActionListener(SettingsView settings){
		this.settings = settings;
	}
	
	@Override
	public void actionPerformed(ActionEvent input) {
		        final MailPreferences prefs = MailPreferences.getMailPreferences();
				final String command = input.getActionCommand();
				
				ResourceBundle rb = ResourceBundle.getBundle("languages.ressources",  main.Main.getLanguage());
				
				switch(command) {
				case "saveSettings":
					prefs.setUser(settings.getUser(), settings.getUser(),settings.getPass());
					prefs.setProvider(settings.getPOP3Host(),settings.getSMTPHost(),settings.getIMAPHost());
					settings.setVisible(false);
					JOptionPane.showMessageDialog(settings, rb.getString("success"));
					break;
				case "discardSettings":
					settings.setVisible(false);
			     	break;
				case "openSettings":
			     	settings.setVisible(true);
			     	break;
				case "language":
					if(settings.getLanguage()== "Deutsch"){
			     	    prefs.setLanguage("de", "CH");
					}else{
						prefs.setLanguage("en", "US");
					}
					main.Main.setLanguage(new Locale(prefs.getLanguage(), prefs.getCountry()));
			     	break;
				default: 
					System.out.println("Command not exists");
					break;
				}
	}

}
