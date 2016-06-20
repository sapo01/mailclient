package main;

import java.io.File;
import java.io.IOException;

import control.EmailService;
import model.EmailListModel;
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
		//Create new mailStorageFile if not already exists
		File jsonMails = new File(model.MailPreferences.getMailPreferences().getInboxPath());
		
		if(jsonMails.exists()){
		view = new MainView(msg);
		view.setUp();
		}else{
			try {
				jsonMails.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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
