package main;

import java.util.Locale;

import control.EmailService;
import model.EmailListModel;
import model.MailPreferences;
import view.MainView;

public class Main {
	static MainView view;
	private static EmailListModel msg;
	private static EmailService mailservice = new EmailService();
	
	public static void main(String[] args) {
		msg = new EmailListModel(mailservice.getEmails());
		initialize();
	}

	private static void initialize(){
		MailPreferences.getMailPreferences().setLanguage(new Locale("de","CH"));
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
