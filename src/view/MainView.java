package view;

import model.Email;
import model.EmailListModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.ResourceBundle;

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
		
	ResourceBundle rb = ResourceBundle.getBundle("languages.ressources",  main.Main.getLanguage());
	
	//Get the Mails and create the list
    private static JList<Email> inboxList;
	private static JScrollPane scrollPane;
	private static EmailListModel model;
    //----------------
	
	@SuppressWarnings("unchecked")
	public MainView(EmailListModel inboxListModel){
        model = inboxListModel;
		inboxList = new JList<>(model);
		inboxList.setCellRenderer(new EmailListCellRenderer());
		
		//Scrollbar implementation
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(inboxList);
		inboxList.addMouseListener(initializeMouseListener(inboxList));
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
		mainPanel.add(scrollPane, BorderLayout.CENTER);		
		
		
		JPanel buttonPanel = new JPanel(new FlowLayout());
		
		//Add the buttons to this page
		JButton newMailButton = new JButton(rb.getString("newmail"));
		JButton updateButton = new JButton(rb.getString("refresh"));
		JButton removeButton = new JButton(rb.getString("delete"));
		JButton settingsButton = new JButton(rb.getString("settings"));
		
		buttonPanel.add(newMailButton);
		buttonPanel.add(updateButton);
		buttonPanel.add(removeButton);
		buttonPanel.add(settingsButton);
		
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		newMailButton.setActionCommand("newMail");
		newMailButton.addActionListener(listener);
		
		updateButton.setActionCommand("updateMails");
		updateButton.addActionListener(listener);
				
		removeButton.setActionCommand("deleteMail");
		removeButton.addActionListener(listener);
		
		settingsButton.setActionCommand("openSettings");
		settingsButton.addActionListener(listener);
		
		JFrame frame = new JFrame("Mailclient");
        
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        
        frame.add(mainPanel, BorderLayout.CENTER);
        
        frame.setSize(500, 500);
        frame.setVisible(true);
        
 	}
	
	public JList<Email> getList(){
		return inboxList;
	}
	
	public static void setList(List<Email> updated){
		model.setNewContent(updated);
		scrollPane.setViewportView(inboxList);
	}
	
	public MouseListener initializeMouseListener(final JList<Email> inboxList){
	MouseListener mouselistener = new MouseAdapter(){
		public void mouseClicked(MouseEvent mouseEvent){
			JList<?> inboxList = (JList<?>) mouseEvent.getSource();
			if(mouseEvent.getClickCount() == 2){
				int index = inboxList.locationToIndex(mouseEvent.getPoint());
				if(index >=0){
					Email mail = (Email) inboxList.getModel().getElementAt(index);
					ReadView view = new ReadView(mail);
					view.setVisible(true);
				}
			}
		}
	};
	return mouselistener;
	}	

}