package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Email;
import model.MailPreferences;
import view.SendView;

public class SendActionListener implements ActionListener {

	private SendView SendMail;
	
	public SendActionListener(SendView SendMail){
		this.SendMail = SendMail;
	}
	
	@Override
	public void actionPerformed(ActionEvent input) {
		        final MailPreferences prefs = MailPreferences.getMailPreferences();
				Email mail = new Email("ID",prefs.getUserAdress(),SendMail.getTo(),SendMail.getCc(), SendMail.getSubject(),SendMail.getMessage());
				main.Main.getMailService().sendMail(mail);
				SendMail.setVisible(false);
	}

}