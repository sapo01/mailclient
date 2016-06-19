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

public class ButtonActionListener implements ActionListener {
	
	private  JFrame view;
	
	Email test1 = new Email("jsmailclient@gmail.com","sandro-portner@hotmail.com","TestMail","Message:))");
	
	public ButtonActionListener(JFrame view){
		this.view = view;
	}
		
	@Override
	public void actionPerformed(ActionEvent input) {
		        final MailPreferences prefs = MailPreferences.getMailPreferences();
				final String command = input.getActionCommand();
				
				SendView newMail = new SendView();
				SettingsView settings = new SettingsView();
				
				switch(command) {
				case "saveSettings":
					
					break;
				case "discardSettings":
					settings.setVisible(false);
			     	break;
				case "deleteMail":
					MessageList msg = main.Main.getMsgList();
					msg.remove();
					break;
				case "openSettings":
			     	settings.setVisible(true);
			     	break;
				case "updateMails":
					main.Main.getMailService().getEmails();
					break;
				case "newMail":
			     	newMail.setVisible(true);
			     	break;
				case "sendMail":
					Email mail = new Email(prefs.getUserAdress(),newMail.getTo(), newMail.getSubject(),newMail.getMessage());
					System.out.println(test1.getRecipents());
					System.out.println(test1.getSubject());
					System.out.println(test1.getSender());
					System.out.println(test1.getMessage());
					main.Main.getMailService().sendMail(test1);
					break;
				default: 
					System.out.println("Command not exists");
					break;
				}
	}
}
