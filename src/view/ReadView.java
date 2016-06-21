package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ResourceBundle;

import javax.swing.*;

import model.Email;

@SuppressWarnings("serial")
public class ReadView extends JFrame {
	
	ResourceBundle rb = ResourceBundle.getBundle("languages.ressources",  main.Main.getLanguage());
	
    //set font for JLabel
	JLabel fromField = new JLabel("");
	JLabel subjectField = new JLabel("");
	JLabel MailMessage = new JLabel("");
	
	//create different fonts
	Font infoFont = new Font("Courier", Font.ITALIC,13);
	Font msgFont = new Font("Courier",Font.PLAIN,14);
  
    public ReadView(Email mail) {
    	fromField.setText(mail.getSender());
    	subjectField.setText(mail.getSubject());
    	String formatted = mail.getMessage().replace("\n", "<br>");
    	formatted = "<html>" + formatted + "</html>";
    	MailMessage.setText(formatted);
    	mail.setRead();
        initialize();
    }
   
    private void initialize() {
        setTitle("Reader");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(new Dimension(600, 480));

        getContentPane().setLayout(new BorderLayout());

        fromField.setFont(infoFont);
        subjectField.setFont(infoFont);
        
        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(255, 255, 255));
        headerPanel.setLayout(new GridLayout(6, 2));
        headerPanel.add(new JLabel(rb.getString("from")));
        headerPanel.add(fromField);

        headerPanel.add(new JLabel(rb.getString("subject")));
        headerPanel.add(subjectField);
       
       
        // Body Panel
        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(new BorderLayout());
        bodyPanel.add(new JLabel(rb.getString("message")), BorderLayout.NORTH);
        MailMessage.setFont(msgFont);
        bodyPanel.add(MailMessage, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BorderLayout());
        
        JButton closeMailButton = new JButton(rb.getString("close"));
        closeMailButton.addActionListener(event -> close());
        footerPanel.add(closeMailButton, BorderLayout.SOUTH);

        //add the panels to the frame
        getContentPane().add(headerPanel, BorderLayout.NORTH);
        getContentPane().add(bodyPanel, BorderLayout.CENTER);
        getContentPane().add(footerPanel, BorderLayout.SOUTH);
    }

	private void close(){
		this.dispose();
	}
}
