package control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.text.View;

import model.Email;
import model.MailPreferences;
import model.MessageList;
import view.SendView;
import view.SettingsView;

public class MainActionListener implements ActionListener {
	
	private  JFrame view;
		
	public MainActionListener(JFrame view){
		this.view = view;
	}
		
	@Override
	public void actionPerformed(ActionEvent input) {
		        final MailPreferences prefs = MailPreferences.getMailPreferences();
				final String command = input.getActionCommand();
				
				final SendView newMail = new SendView();
				
				switch(command) {
				
				case "deleteMail":
					MessageList msg = main.Main.getMsgList();
					msg.remove();
					break;
				case "openSettings":
					SettingsView settings = new SettingsView();
			     	settings.setVisible(true);
			     	break;
				case "updateMails":
					main.Main.getMailService().getEmails();
					break;
				case "newMail":
			     	newMail.setVisible(true);
			     	break;
				default: 
					System.out.println("Command not exists");
					break;
				}
	}
}
