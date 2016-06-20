package control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.SwingWorker;
import model.MailPreferences;
import model.MessageList;
import view.SendView;
import view.SettingsView;

public class MainActionListener implements ActionListener {
	
	public MainActionListener(JFrame view){
	}
		
	@SuppressWarnings("rawtypes")
	@Override
	public void actionPerformed(ActionEvent input) {
		        MailPreferences.getMailPreferences();
				final String command = input.getActionCommand();
				
				final SendView newMail = new SendView();
				
				switch(command) {
	
				case "deleteMail":
					MessageList msg = main.Main.getMsgList();
					msg.remove();
					break;
				case "openSettings":
					SettingsView settings = new SettingsView();
			     	settings.setVisible(true);
			     	break;
				case "updateMails":
					SwingWorker mySwingWorker = new MySwingWorker();
					mySwingWorker.execute();
					break;
				case "newMail":
			     	newMail.setVisible(true);
			     	break;
				default: 
					System.out.println("Command not exists");
					break;
				}
	}
}

class MySwingWorker extends SwingWorker<Object, Integer> {
	private static final int DELAY = 1000;
	@Override
	protected Object doInBackground() throws Exception {
		while (!isCancelled()) {
			main.Main.getMailService().getEmails();
			this.cancel(true);
		}
		return null;
	}
}
