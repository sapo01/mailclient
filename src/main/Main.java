package main;

import java.util.Locale;

import control.EmailService;
import model.EmailListModel;
import model.MailPreferences;
import view.MainView;

public class Main {
	static MainView view;
	private static Locale language;
	private static EmailListModel msg;
	private static EmailService mailservice = new EmailService();
	
	public static void main(String[] args) {
		//create a new List from the updated Emails(server)
		msg = new EmailListModel(mailservice.getEmails());
		initialize();
	}

	private static void initialize(){
		if(MailPreferences.getMailPreferences().getLanguage() != "Language"){
		    final String lang    =   MailPreferences.getMailPreferences().getLanguage();
		    final String country =   MailPreferences.getMailPreferences().getCountry();
		    System.out.println(lang);
		    //Set default language
		    language = new Locale(lang,country);
		}else{
			language = new Locale("de","CH");
		}
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
	public static Locale getLanguage(){
		return language;
	}
	public static void setLanguage(Locale inLang){
		language = inLang;
	}
		
}

