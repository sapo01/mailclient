package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.EmailListModel;
import model.MailPreferences;
import view.MainView;
import view.LoginView;

public class LoginActionListener implements ActionListener {

	private LoginView settings;
	private static EmailListModel msg;
	private static MainView menu;
	public LoginActionListener(LoginView settings){
		this.settings = settings;
	}
	
	@Override
	public void actionPerformed(ActionEvent input) {
		        final MailPreferences prefs = MailPreferences.getMailPreferences();
				final String command = input.getActionCommand();
				
				switch(command) {
				case "saveSettings":
					prefs.setUser(settings.getUser(), settings.getUser(),settings.getPass());
					prefs.setProvider(settings.getPOP3Host(),settings.getSMTPHost(),settings.getIMAPHost());
					settings.setVisible(false);
					JOptionPane.showMessageDialog(settings, "Successfull !");
					
					//After Setup Settings -> Create MainView
					msg = new EmailListModel(main.Main.getMailService().getEmails());
					menu = new MainView(msg);
					menu.setUp();
					break;
				case "discardSettings":
					settings.setVisible(false);
			     	break;
				case "openSettings":
			     	settings.setVisible(true);
			     	break;
				default: 
					System.out.println("Command not exists");
					break;
				}
	}
	
	public static MainView getMainView(){
		return menu;
	}
		
	public static EmailListModel getMsgList(){
		return msg;
	}

}
