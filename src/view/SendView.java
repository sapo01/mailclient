package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.JTextField;

import control.ButtonActionListener;
import model.Email;

@SuppressWarnings("serial")
public class SendView extends JFrame {
	
    JTextField toField = new JTextField();
    JTextField ccField = new JTextField();
    JTextField subjectField = new JTextField();
    JTextArea contentTextArea = new JTextArea();

    ActionListener listener = new ButtonActionListener(this);
    
    
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
        setSize(new Dimension(600, 280));

        getContentPane().setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(6, 2));
      

        headerPanel.add(new JLabel("To:"));
        headerPanel.add(toField);
        
        headerPanel.add(new JLabel("Cc:"));
        headerPanel.add(ccField);

        headerPanel.add(new JLabel("Subject:"));
        headerPanel.add(subjectField);

        
        // Body Panel
        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(new BorderLayout());
        bodyPanel.add(new JLabel("Message:"), BorderLayout.NORTH);
        bodyPanel.add(contentTextArea, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BorderLayout());
        
        JButton sendMailButton = new JButton("Send E-mail");
        
        sendMailButton.addActionListener(listener);
        sendMailButton.setActionCommand("sendMail");
        		
        footerPanel.add(sendMailButton, BorderLayout.SOUTH);

        //add the panels to the frame
        getContentPane().add(headerPanel, BorderLayout.NORTH);
        getContentPane().add(bodyPanel, BorderLayout.CENTER);
        getContentPane().add(footerPanel, BorderLayout.SOUTH);
    }

}
