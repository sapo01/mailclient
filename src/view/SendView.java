package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.*;
import javax.swing.JTextField;

import control.SendActionListener;
import model.MailPreferences;

@SuppressWarnings("serial")
public class SendView extends JFrame {
	
	ResourceBundle rb = ResourceBundle.getBundle("languages.ressources", MailPreferences.getMailPreferences().getLanguage());
	
    JTextField toField = new JTextField();
    JTextField ccField = new JTextField();
    JTextField subjectField = new JTextField();
    JTextArea contentTextArea = new JTextArea();

    ActionListener listener = new SendActionListener(this);
    
    public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
        	SendView newMail = new SendView();
            newMail.setVisible(true);
        }
    });    
    }

    public SendView() {
        Initialize();
    }
   
private void Initialize() {
        setTitle("New Mail");
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setSize(new Dimension(600, 480));

        getContentPane().setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(6, 2));
      

        headerPanel.add(new JLabel(rb.getString("to")));
        headerPanel.add(toField);
        
        headerPanel.add(new JLabel(rb.getString("cc")));
        headerPanel.add(ccField);

        headerPanel.add(new JLabel(rb.getString("subject")));
        headerPanel.add(subjectField);

        
        // Body Panel
        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(new BorderLayout());
        bodyPanel.add(new JLabel(rb.getString("message")), BorderLayout.NORTH);
        bodyPanel.add(contentTextArea, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BorderLayout());
        
        JButton sendMailButton = new JButton(rb.getString("sendmail"));
        
        sendMailButton.setActionCommand("sendMail");
        sendMailButton.addActionListener(listener);
        		
        footerPanel.add(sendMailButton, BorderLayout.SOUTH);

        //add the panels to the frame
        getContentPane().add(headerPanel, BorderLayout.NORTH);
        getContentPane().add(bodyPanel, BorderLayout.CENTER);
        getContentPane().add(footerPanel, BorderLayout.SOUTH);
    }

	public String getTo(){
		return toField.getText();
	}
	
	public String getCc(){
		return ccField.getText();
	}
	
	public String getSubject(){
		return subjectField.getText();
	}
	
	public String getMessage(){
		return contentTextArea.getText();
	}

}
