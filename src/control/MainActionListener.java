package control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import model.EmailListModel;
import model.MailPreferences;
import view.MainView;
import view.SendView;
import view.LoginView;

public class MainActionListener implements ActionListener {
	private static MainView view;
	
	@SuppressWarnings("static-access")
	public MainActionListener(MainView view){
		this.view = view;
	}
		
	@SuppressWarnings("rawtypes")
	@Override
	public void actionPerformed(ActionEvent input) {
		        MailPreferences.getMailPreferences();
		        
				final String command = input.getActionCommand();
				final SendView newMail = new SendView();
				
				switch(command) {
	
				case "deleteMail":
					EmailListModel list = control.LoginActionListener.getMsgList();
					int msg = view.getList().getSelectedIndex(); 
					list.remove(msg);
					break;
				case "openSettings":
					LoginView settings = new LoginView();
			     	settings.setVisible(true);
			     	view.setVisible(false);
			     	view.dispose();
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
	
	public static MainView getView(){
		return view;
	}

}

class MySwingWorker extends SwingWorker<Object, Integer> {
	@Override
	protected Object doInBackground() throws Exception {
		while (!isCancelled()) {
			main.Main.getMailService().getEmails();
			this.cancel(true);
		}
		return null;
	}
	
	protected void done(){
		Date date = new Date();
		JOptionPane.showMessageDialog(control.LoginActionListener.getMainView(), "Updated at " + new Timestamp(date.getTime()));
	}

}
