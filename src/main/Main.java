package main;
import control.EmailService;
import model.MessageList;
import view.MainView;

public class Main {
	static EmailService mailservice = new EmailService();
	private static MessageList msg = new MessageList();
	
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
	
	public static MessageList getMsgList(){
		return msg;
	}
	
}
