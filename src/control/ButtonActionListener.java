package control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import model.MessageList;
import view.SettingsView;

public class ButtonActionListener implements ActionListener {
	
	private final JFrame frame;
	
	public ButtonActionListener(JFrame inputFrame){
		this.frame = inputFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent input) {
				String command = input.getActionCommand();
				switch(command) {
				case "saveSettings":break;
				case "discardSettings":
					frame.setVisible(false);
			     	break;
				case "deleteMail":
					MessageList msg = view.MainView.getMsgList();
					msg.remove();
					break;
				case "openSettings":
					SettingsView settings = new SettingsView();
			     	settings.setVisible(true);
			     	break;
				case "updateMails":break;
				default: 
					System.out.println("Command not exists");
					break;
				}
	}
	
	

}
