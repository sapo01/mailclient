package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.JTextField;

public class ReadView extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1;
   
    //set font for JLabel
	JLabel fromField = new JLabel("sandro-portner@hotmail.com");
	JLabel subjectField = new JLabel("TestMail");
	JLabel contentTextArea = new JLabel("Bla bla bla lium suas usaun n efuhua DWNu ");
	
	//create different fonts
	Font infoFont = new Font("Courier", Font.ITALIC,12);
	Font msgFont = new Font("Courier",Font.PLAIN,12);
	
    public static void main(String[] args) {
    	SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	ReadView newMail = new ReadView();
                newMail.setVisible(true);
            }
        });
    }
  
    public ReadView() {
        Initialize();

    }
   
private void Initialize() {
        setTitle("Reader");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(400, 280));

        getContentPane().setLayout(new BorderLayout());

        fromField.setFont(infoFont);
        subjectField.setFont(infoFont);
        
        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(6, 2));
        headerPanel.add(new JLabel("From:"));
        headerPanel.add(fromField);

        headerPanel.add(new JLabel("Subject:"));
        headerPanel.add(subjectField);
       
       
        // Body Panel
        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(new BorderLayout());
        bodyPanel.add(new JLabel("Message:"), BorderLayout.NORTH);
        contentTextArea.setFont(msgFont);
        bodyPanel.add(contentTextArea, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BorderLayout());
        JButton sendMailButton = new JButton("Close");
        footerPanel.add(sendMailButton, BorderLayout.SOUTH);

        //add the panels to the frame
        getContentPane().add(headerPanel, BorderLayout.NORTH);
        getContentPane().add(bodyPanel, BorderLayout.CENTER);
        getContentPane().add(footerPanel, BorderLayout.SOUTH);
    }
}
