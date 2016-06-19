package main;

import java.util.Locale;
import java.util.ResourceBundle;

public class Language {

	Locale localeDE = new Locale("de", "CH");
	Locale localeEN = new Locale("en", "US");
	
	ResourceBundle rb = ResourceBundle.getBundle("ressources", localeDE);
}
