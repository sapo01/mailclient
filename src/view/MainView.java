package view;

import model.Email;
import model.EmailListModel;
import model.MessageList;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import control.ButtonActionListener;
import control.EmailListCellRenderer;

@SuppressWarnings("serial")
public class MainView extends JFrame {
	//TESTING
	private ArrayList<Email> testList = new ArrayList<Email>();
		
	Email test = new Email("Sender", "To ? ","Hallo","Text");
	Email test1 = new Email("Sender2", "Recipients","Test","Test");
	
		
	EmailListModel inboxListModel = new EmailListModel(testList); //here get new Mails
    JList<Email> inboxList = new JList<>(inboxListModel);
	JScrollPane inboxPane = new JScrollPane(inboxList);

	@SuppressWarnings("unchecked")
	public MainView(MessageList msg){
		inboxList.setCellRenderer(new EmailListCellRenderer());
		inboxListModel.addEntry(test);
	}
		
	ActionListener listener = new ButtonActionListener(this);
	
	public  void setUp() {
	
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		JLabel name = new JLabel("MailClient 1.0");
		name.setFont(new Font(Font.DIALOG, Font.ITALIC, 20));
		
		//not ready
		JLabel preview = new JLabel(" ");
		preview.setFont(new Font(Font.DIALOG, Font.ITALIC, 10));
		
		
		mainPanel.add(name,BorderLayout.PAGE_START);
		mainPanel.add(inboxPane,BorderLayout.AFTER_LINE_ENDS);
		mainPanel.add(inboxList,BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel(new FlowLayout());
		
		//Add the buttons to this page
		JButton newMailButton = new JButton("New Mail");
		JButton updateButton = new JButton("Get Mails");
		JButton removeButton = new JButton("Delete");
		JButton settingsButton = new JButton("Settings");
		
		buttonPanel.add(newMailButton);
		buttonPanel.add(updateButton);
		buttonPanel.add(removeButton);
		buttonPanel.add(settingsButton);
		
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		newMailButton.setActionCommand("newMail");
		newMailButton.addActionListener(listener);
		
		updateButton.setActionCommand("updateMails");
		updateButton.addActionListener(listener);
		
		settingsButton.setActionCommand("openSettings");
		settingsButton.addActionListener(listener);
		
		removeButton.setActionCommand("deleteMail");
		removeButton.addActionListener(listener);
		
		JFrame frame = new JFrame("Mailclient");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        
        frame.add(mainPanel, BorderLayout.CENTER);
        
        frame.setSize(500, 500);
        frame.setVisible(true);
        
	}
    
}