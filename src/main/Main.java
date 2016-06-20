package main;
import control.EmailService;
import model.EmailListModel;
import model.MailPreferences;
import view.MainView;
import view.SettingsView;

public class Main {
	static MainView view;
	static EmailService mailservice = new EmailService();
	private static EmailListModel msg;
	
	public static void main(String[] args) {
		MailPreferences prefs = model.MailPreferences.getMailPreferences();
		if(!prefs.getImapAddress().isEmpty()){
		initialize();
		}else{
			SettingsView settings = new SettingsView();
			settings.setVisible(true);
		}
	}

	private static void initialize(){
		msg = new EmailListModel(mailservice.getEmails());
		MainView menu = new MainView(msg);
		menu.setUp();
	}
	
	public static EmailService getMailService(){
		return mailservice;
	}
	
	public static EmailListModel getMsgList(){
		return msg;
	}
	
	public static MainView getMainView(){
		return view;
	}
	
}
