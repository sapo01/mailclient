package control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.text.View;

import model.Email;
import model.MessageList;
import view.SendView;
import view.SettingsView;

public class ButtonActionListener implements ActionListener {
	
	private  JFrame view;
	private  Email email;
	
	public ButtonActionListener(JFrame view){
		this.view = view;
	}
	
	public void setMail(final Email email){
		this.email = email;
	}
	
	@Override
	public void actionPerformed(ActionEvent input) {
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
					main.Main.getMailService().sendMail(email);
					break;
				default: 
					System.out.println("Command not exists");
					break;
				}
	}
}
