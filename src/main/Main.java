package main;

import control.EmailService;
import model.EmailListModel;
import view.MainView;

public class Main {
	static MainView view;
	private static EmailListModel msg;
	private static EmailService mailservice = new EmailService();
	//private static EmailListModel msg;
	
	public static void main(String[] args) {
		//After Setup Settings -> Create MainView
		msg = new EmailListModel(mailservice.getEmails());
		initialize();
	}

	private static void initialize(){
		view = new MainView(msg);
		view.setUp();
	}
	
	public static EmailService getMailService(){
		return mailservice;
	}
	
	public static MainView getMainView(){
		return view;
	}
		
	public static EmailListModel getMsgList(){
		return msg;
	}
		
}
