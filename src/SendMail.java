import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
//import javax.activation.*;

/**
 * A simple program for sending a mail message to gmail using SMTP.
 *
 * @version 02-APR-2015
 */
public class SendMail  {
	/**
	 * Constructs and sends a simple email.
	 *
	 * @param args
	 *            Uses the first arg as receiver address.
	 */
	public static void main(String[] args) {
		// Receiver mail address
		final String to = args[0]; // "------@------.ch" ;
		System.out.println("to: " + to);
		//user name and password
		final MailPreferences prefs = new MailPreferences();
		final String username = prefs.getUserName();
		final String password = prefs.getPassword();

		// Sender mail address
		final String from = prefs.getUserAdress();
		System.out.println("from: " +from);

		// Get system properties
		final Properties properties = System.getProperties();

		// Setup properties for the mail server with TLS
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.setProperty("mail.smtp.port", "587");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		
		// Setup properties for the mail server with SSL
//		properties.put("mail.smtp.auth", "true");
//		properties.put("mail.smtp.host", "smtp.gmail.com");
//		properties.put("mail.smtp.port", "465");
//		properties.put("mail.smtp.socketFactory.port", "465");
//		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		// Get the default Session object
		final Session session = Session.getDefaultInstance(properties);
		
		try {
			// Create a default MimeMessage object
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject("Testmail");

			// Now set the actual message
			message.setText("Na Bravo !");

			Transport transport = session.getTransport("smtps");

			try {
				transport.connect("smtp.gmail.com", username, password);
				transport.sendMessage(message, message.getAllRecipients());
			} finally {
				transport.close();
			}

			System.out.println("Message sent");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}