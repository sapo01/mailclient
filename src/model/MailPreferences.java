package model;
import java.util.prefs.Preferences;

public class MailPreferences {

	private static final String userID = "UserID";
	private static final String userAddress = "UserAddress";
	private static final String passwd = "Passwd";

	private Preferences prefs;
	
	public MailPreferences() {
		// This will define a node in which the preferences can be stored
		prefs = Preferences.userRoot().node(this.getClass().getName());
	}

	public String getUserName() {
		return prefs.get(userID, "UserID");
	}
	
	public String getUserAdress() {
		return prefs.get(userAddress, "EMailAddress");
	}
	
	public String getPassword() {
		return prefs.get(passwd, "Passwort");
	}

	public void setUser(String userAdress, String password) {
		setUser(userAdress, userAdress, password);
	}

	public void setUser(String userName, String userAddr, String password) {
		prefs.put(userID, userName);
		prefs.put(userAddress, userAddr);
		prefs.put(passwd, password);
	}

	public static void main(String[] args) {
		MailPreferences test = new MailPreferences();
		System.out.println(test.getUserName());
		System.out.println(test.getUserAdress());
		System.out.println(test.getPassword());
		test.setUser(
			  	"jsmailclient@gmail.com", 
				"jsmailclient@gmail.com", 
				"********");
	}
}
