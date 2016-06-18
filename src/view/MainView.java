package view;

import model.MessageList;

import javax.mail.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainView extends JFrame {
			    

	@SuppressWarnings("serial")
	public  void setUp() {
		
		final MessageList msg = new MessageList();
		JList<String> list = new JList<String>(msg);
		
		
		list.setCellRenderer(new DefaultListCellRenderer() {
			    @SuppressWarnings("unused")
				Component getListCellRendererComponent(JList<? extends String> list, String value, int index,
			        boolean isSelected, boolean cellHasFocus) {
			          			         
			        return this;
			    }
		});
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		JLabel name = new JLabel("MailClient 1.0");
		name.setFont(new Font(Font.DIALOG, Font.ITALIC, 20));
		
		JLabel preview = new JLabel(" ");
		preview.setFont(new Font(Font.DIALOG, Font.ITALIC, 10));
		
		
		mainPanel.add(name,BorderLayout.PAGE_START);
		mainPanel.add(preview,BorderLayout.AFTER_LINE_ENDS);
		mainPanel.add(list,BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel(new FlowLayout());
		
		//Add the buttons to this page
		JButton updateButton = new JButton("Get Mails");
		JButton removeButton = new JButton("Delete");
		JButton settingsButton = new JButton("Settings");
		
		buttonPanel.add(updateButton);
		buttonPanel.add(removeButton);
		buttonPanel.add(settingsButton);
		
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		//updateButton.addActionListener(event ->);
		removeButton.addActionListener(event ->msg.remove());
		settingsButton.addActionListener(event ->settings());
		
		JFrame frame = new JFrame("ListExample");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        
        frame.add(mainPanel, BorderLayout.CENTER);
        
        frame.setSize(500, 500);
        frame.setVisible(true);
        
	}
	public static void settings() {
     	SettingsView settings = new SettingsView();
     	settings.setVisible(true);
     }

}