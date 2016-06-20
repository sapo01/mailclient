package main;
import control.EmailService;
import view.MainView;
import view.LoginView;

public class Main {
	static MainView view;
	static EmailService mailservice = new EmailService();
	//private static EmailListModel msg;
	
	public static void main(String[] args) {
		initialize();
	}

	private static void initialize(){
		LoginView settings = new LoginView();
		settings.setVisible(true);
//		msg = new EmailListModel(mailservice.getEmails());
//		MainView menu = new MainView(msg);
//		menu.setUp();
	}
	
	public static EmailService getMailService(){
		return mailservice;
	}
		
}
