package main;
import control.EmailService;
import model.EmailListModel;
import view.MainView;

public class Main {
	static MainView view;
	static EmailService mailservice = new EmailService();
	private static EmailListModel msg = new EmailListModel(mailservice.getEmails());
	
	public static void main(String[] args) {
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
