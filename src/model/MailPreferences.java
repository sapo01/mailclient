package model;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

/**
 * @author Sandro Portner
 * @author Janick Rueegger
 */

public class MailPreferences {

    //At program startup, generate a single Instance of this class
    private static MailPreferences MAIL_PREFERENCES = new MailPreferences();

    //User variables
    private static final String userID      = "userID";
    private static final String userAddress = "userAddress";
    private static final String passwd      = "password";
    private static final String inboxPath   = "inboxPath";

    //Provider variables
    private static final String pop3Address = "pop3Address";
    private static final String pop3Port    = "pop3Port";
    private static final String smtpAddress = "smtpAddress";
    private static final String smtpPort    = "smtpPort";
    private static final String tlsEnabled  = "tlsEnabled";


    private Preferences prefs;

    public MailPreferences () {
        // This will define a node in which the preferences can be stored
        prefs = Preferences.userRoot().node(this.getClass().getName());
    }

    //add access to a single Instance of this class
    public static MailPreferences getMailPreferences () {
        return MAIL_PREFERENCES;
    }

    //Getter and setter for User variables
    public String getUserName () {
        return prefs.get(userID, "UserID");
    }

    public String getUserAdress () {
        return prefs.get(userAddress, "EMailAddress");
    }

    public String getPassword () {
        return prefs.get(passwd, "Passwort");
    }

    public String getInboxPath () {
        return prefs.get(inboxPath, "C:\\");
    }

    public void setUser (String userAdress, String password) {
        setUser(userAdress, userAdress, password);
    }

    public void setUser (String userName, String userAddr, String password) {
        prefs.put(userID, userName);
        prefs.put(userAddress, userAddr);
        prefs.put(passwd, password);
        try {
            prefs.put(inboxPath, new File(".").getCanonicalPath() + "\\" + userAddr + "_inbox.json");
        }
        catch (IOException e) {
            Logger.getLogger("Unable to get user inbox file path");
        }
    }

    //Getter and setter for Provider variables
    public String getPop3Address () {
        return prefs.get(pop3Address, "Pop3Address");
    }

    public String getSmtpAddress () {
        return prefs.get(smtpAddress, "SmtpAddress");
    }

    public String getPop3Port () {
        return prefs.get(pop3Port, "Pop3Port");
    }

    public String getSmtpPort () {
        return prefs.get(smtpPort, "SmtpPort");
    }

    public String getTlsEnabled () {
        return prefs.get(tlsEnabled, "TlsEnabled");
    }


    /**
     * Sets Provider with standard POP3 and SMTP Ports (995 and 587) and TLS enabled.
     *
     * @param pop3Addr POP3 Address of Mail Server
     * @param smtpAddr SMTP Address of Mail Server
     */
    public void setProvider (String pop3Addr, String smtpAddr) {
        setProvider(pop3Addr, "995", smtpAddr, "587", "true");
    }

    public void setProvider (String pop3Addr, String pop3PortNumber, String smtpAddr, String smtpPortNumber, String tls) {
        prefs.put(pop3Address, pop3Addr);
        prefs.put(pop3Port, pop3PortNumber);
        prefs.put(smtpAddress, smtpAddr);
        prefs.put(smtpPort, smtpPortNumber);
        prefs.put(tlsEnabled, tls);
    }
}
