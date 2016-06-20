package view;

import model.Email;
import model.EmailListModel;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import control.MainActionListener;
import control.EmailListCellRenderer;

@SuppressWarnings("serial")
public class MainView extends JFrame {
	
	//Get the Mails and create the list
    private JList<Email> inboxList;
	private JScrollPane inboxPane;
    //----------------
	
	@SuppressWarnings("unchecked")
	public MainView(EmailListModel inboxListModel){
		inboxList = new JList<>(inboxListModel);
		inboxList.setCellRenderer(new EmailListCellRenderer());
		inboxPane = new JScrollPane(inboxList);
	}
		
	ActionListener listener = new MainActionListener(this);
	
	public  void setUp() {
	
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		JLabel name = new JLabel("MailClient 1.0");
		name.setFont(new Font(Font.DIALOG, Font.ITALIC, 20));
		
		//not ready
		JLabel preview = new JLabel(" ");
		preview.setFont(new Font(Font.DIALOG, Font.ITALIC, 10));
		
		
		mainPanel.add(name,BorderLayout.PAGE_START);
		mainPanel.add(inboxList);
		inboxList.add(inboxPane);		
		
		
		JPanel buttonPanel = new JPanel(new FlowLayout());
		
		//Add the buttons to this page
		JButton newMailButton = new JButton("New Mail");
		JButton updateButton = new JButton("Refresh");
		JButton removeButton = new JButton("Delete");
		
		buttonPanel.add(newMailButton);
		buttonPanel.add(updateButton);
		buttonPanel.add(removeButton);
		
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		newMailButton.setActionCommand("newMail");
		newMailButton.addActionListener(listener);
		
		updateButton.setActionCommand("updateMails");
		updateButton.addActionListener(listener);
				
		removeButton.setActionCommand("deleteMail");
		removeButton.addActionListener(listener);
		
		JFrame frame = new JFrame("Mailclient");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        
        frame.add(mainPanel, BorderLayout.CENTER);
        
        frame.setSize(500, 500);
        frame.setVisible(true);
        
	}
	
	@SuppressWarnings("rawtypes")
	public JList getList(){
		return inboxList;
	}
    
}