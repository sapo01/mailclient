package main;

import java.io.InputStreamReader;
import java.util.Locale;
import java.util.ResourceBundle;

import control.EmailService;
import view.MainView;
import view.LoginView;

public class Main {
	static MainView view;
	static EmailService mailservice = new EmailService();
	//private static EmailListModel msg;
	
	public static void main(String[] args) {
		Locale localeDE = new Locale("de", "CH");
		Locale localeEN = new Locale("en", "US");
		
		ResourceBundle rb = ResourceBundle.getBundle("languages.ressources", localeDE);
		initialize();
	}

	private static void initialize(){
		LoginView settings = new LoginView();
		settings.setVisible(true);
	}
	
	public static EmailService getMailService(){
		return mailservice;
	}
		
}
