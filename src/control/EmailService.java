package control;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;
import java.util.UUID;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import interfaces.IEmailService;
import model.Email;
import model.MailPreferences;
/**
 * @author Sandro Portner
 * @author Janick Rueegger
 */
public class EmailService implements IEmailService {

	private static final String JSON_ID       = "Id";
    private static final String JSON_SENDER   = "From";
    private static final String JSON_RECEIVER = "To";
    private static final String JSON_CC		  = "Cc";
    private static final String JSON_SUBJECT  = "Subject";
    private static final String JSON_SENTDATE = "SentDate";
    private static final String JSON_MESSAGE  = "Message";

    @Override
    public ArrayList<Email> getEmails() {

        // Username and password
        final MailPreferences prefs    = model.MailPreferences.getMailPreferences();
        final String          user     = prefs.getUserName();
        final String          password = prefs.getPassword();

        // The IP address of the IMAP server
        String host = prefs.getImapAddress();
        // Get system properties
        Properties properties = System.getProperties();

        // Request IMAPS
        properties.setProperty("mail.store.protocol", "imaps");
        
        // Get the default Session object
        Session session = Session.getDefaultInstance(properties, null);

        try {
            // Get a store for the protocol
            Store store = session.getStore();

            // Connect to the current host using the specified username and
            // password
            store.connect(host, user, password);

            // Create a Folder object corresponding to the given name
            Folder folder = store.getFolder("INBOX");

            // Open the Folder
            folder.open(Folder.READ_WRITE);

            // Get the new messages from the server and store locally
            Message[] messages = folder.getMessages();
            storeNewMessages(messages);

            folder.close(true);
            store.close();

        }
        catch (NoSuchProviderException e) {
            System.out.println("Connection to Inbox failed" + e);
        }
        catch (MessagingException e) {
        	System.out.println("Messages in POP3 Inbox could not be accessed" + e);
        }
        // After appending new Emails to inbox file, get all Emails from inbox file
        return getEmailsFromInboxFile();
    }

    // Suppress unchecked warning since JSONObject inherits from HashMap, but does not allow ParameterTypes
    @SuppressWarnings("unchecked")
    private static void storeNewMessages (Message[] messages) {
    	
        // Reformat each message into JSON Object and append to file
        for (Message msg : messages) {
        	
            FileWriter file = null;
            try {
                JSONObject jsonEmail = new JSONObject();
                
                jsonEmail.put(JSON_ID, UUID.randomUUID().toString());
                
                // Get and put From
                Address[] in = msg.getFrom();
                for (Address address : in) {
                	jsonEmail.put(JSON_SENDER, address.toString());
                }
                
                Multipart mp = (Multipart) msg.getContent();
    			
                // Get and put To
                String to = InternetAddress.toString(msg.getRecipients(Message.RecipientType.TO));
                if (to != null) {
                    jsonEmail.put(JSON_RECEIVER, to);
                }
                
                // Get and put Cc
                String cc = InternetAddress.toString(msg.getRecipients(Message.RecipientType.CC));
                if (cc != null) {
                    jsonEmail.put(JSON_CC, cc);
                }

                // Get and put Subject
                String subject = msg.getSubject();
                if (subject != null) {
                    jsonEmail.put(JSON_SUBJECT, subject);
                }

                // Get and put SentDate
                Date sent = msg.getSentDate();
                if (sent != null) {
                    jsonEmail.put(JSON_SENTDATE, sent.toString());
                }
                
                BodyPart bp = mp.getBodyPart(0);
                // Get and put Content
                String content = bp.getContent().toString();
                if (content != null) {
                    jsonEmail.put(JSON_MESSAGE, content);
                }
                
                
                // Write Email to JSON File
                file = new FileWriter(model.MailPreferences.getMailPreferences().getInboxPath(), true);
                file.append(jsonEmail.toJSONString());
                file.append(System.getProperty("line.separator"));
                file.flush();
                file.close();

                // Mark this message as deleted when the session is closed
                msg.setFlag(Flags.Flag.DELETED, true);
            }
            catch (MessagingException msgEx) {
                System.out.println("A message could not be stored to the JSON File " + msgEx);
            }
            catch (IOException ioEX) {
            	 System.out.println("JSON File could not be written" + ioEX);
            }
        }
        System.out.println("New messages stored to JSON");
    }

    public static ArrayList<Email> getEmailsFromInboxFile() {

        ArrayList<JSONObject> jsonObjects  = new ArrayList<>();
        ArrayList<Email>      emailObjects = new ArrayList<>();

        try {
           
			@SuppressWarnings("resource")
			Scanner jsonScanner = new Scanner(new File(MailPreferences.getMailPreferences().getInboxPath()), "UTF-8");

            // Reading each line of file and parse content to JSON-Object
            while (jsonScanner.hasNext()) {
                JSONObject obj = (JSONObject) new JSONParser().parse(jsonScanner.nextLine());
                jsonObjects.add(obj);
            }

            // Convert JSON-Objects to Email-Objects
            for (JSONObject obj : jsonObjects) {

                // Get receivers from message object
                final String receivers       = (String) obj.get("to");
                final String cc              = (String) obj.get("cc");
 
                // Generate Email Object and add it to ArrayList of Email objects
                emailObjects.add(
                		new Email(
                				UUID.randomUUID().toString(),
                				(String) obj.get(JSON_SENDER),
                				receivers,
                				cc,
                				(String) obj.get(JSON_SUBJECT),
                				(String) obj.get(JSON_MESSAGE)));
            }
        }
        catch (ParseException parseEx) {
        	System.out.println("JSON Object from file could not be parsed " + parseEx);
        }
        catch (IOException ioEx) {
        	System.out.println("JSON File could not be opened for readout " + ioEx);
        }

        return emailObjects;
    }

    @Override
    public void sendMail (Email email) {

        // Username and password
        final MailPreferences prefs    =  MailPreferences.getMailPreferences();
        final String          username =  prefs.getUserName();
        final String          password =  prefs.getPassword();

        // Sender mail address
        final String from = prefs.getUserAdress();

        // Receiver mail addresses
        final String to = email.getRecipents();

        // Receiver mail addresses
        final String cc = email.getCc();
        
        // Subject
        final String subject = email.getSubject();

        // Message
        final String mailText = email.getMessage();

        // Get system properties
        final Properties properties = System.getProperties();

        // Setup properties for the mail server with TLS
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.host", prefs.getSmtpAddress());
        properties.setProperty("mail.smtp.port", prefs.getSmtpPort());
        properties.setProperty("mail.smtp.starttls.enable", prefs.getTlsEnabled());
        properties.setProperty("mail.smtp.ssl.enable", prefs.getTlsEnabled().equals("true") ? "false" : "true" );

        // Get the default Session object
        final Session session = Session.getDefaultInstance(properties, null);

        try {
            // Create a default MimeMessage object
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            
            // Set Cc: header field of the header
            message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
            
            // Set Subject: header field
            message.setSubject(subject);

            // Now set the actual message
            message.setText(mailText);
            
            Transport transport = session.getTransport("smtps");
            
            try {
                transport.connect(prefs.getSmtpAddress(), username, password);
                transport.sendMessage(message, message.getAllRecipients());
            }
            finally {
                transport.close();
            }

            System.out.println("Message with Subject: \"" + subject + "\" sent successfully");
        }
        catch (MessagingException e) {
        	System.out.println("Message not sent!" + e);
        }
    }
}