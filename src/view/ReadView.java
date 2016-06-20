package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.*;

@SuppressWarnings("serial")
public class ReadView extends JFrame {
	
	Locale localeDE = new Locale("de", "CH");
	Locale localeEN = new Locale("en", "US");
	
	ResourceBundle rb = ResourceBundle.getBundle("languages.ressources", localeDE);
	   
    //set font for JLabel
	JLabel fromField = new JLabel("");
	JLabel subjectField = new JLabel("");
	JLabel contentTextArea = new JLabel("");
	
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
        initialize();
    }
   
private void initialize() {
        setTitle("Reader");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(600, 480));

        getContentPane().setLayout(new BorderLayout());

        fromField.setFont(infoFont);
        subjectField.setFont(infoFont);
        
        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(6, 2));
        headerPanel.add(new JLabel(rb.getString("from")));
        headerPanel.add(fromField);

        headerPanel.add(new JLabel(rb.getString("subject")));
        headerPanel.add(subjectField);
       
       
        // Body Panel
        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(new BorderLayout());
        bodyPanel.add(new JLabel(rb.getString("message")), BorderLayout.NORTH);
        contentTextArea.setFont(msgFont);
        bodyPanel.add(contentTextArea, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BorderLayout());
        JButton sendMailButton = new JButton(rb.getString("close"));
        footerPanel.add(sendMailButton, BorderLayout.SOUTH);

        //add the panels to the frame
        getContentPane().add(headerPanel, BorderLayout.NORTH);
        getContentPane().add(bodyPanel, BorderLayout.CENTER);
        getContentPane().add(footerPanel, BorderLayout.SOUTH);
    }
}
