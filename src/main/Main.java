package main;
import control.EmailService;
import model.EmailListModel;
import model.MailPreferences;
import view.MainView;

public class Main {
	static MainView view;
	static EmailService mailservice = new EmailService();
	private static EmailListModel msg;
	
	public static void main(String[] args) {
		//Set up standart user/ example user
		MailPreferences prefs = model.MailPreferences.getMailPreferences();
		prefs.setUser("username", "email@gmail.com","******");
		prefs.setProvider("pop.gmail.com", "smtp.gmail.com","imap.gmail.com");
		
		msg = new EmailListModel(mailservice.getEmails());
		initialize();
	}

	private static void initialize(){		
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
