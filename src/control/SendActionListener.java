package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
				final DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd HH:mm:ss");
		        final Date sendTime = new Date();
		        final MailPreferences prefs = MailPreferences.getMailPreferences();
				Email mail = new Email("ID",dateFormat.format(sendTime).toString(),prefs.getUserAdress(),SendMail.getTo(),SendMail.getCc(), SendMail.getSubject(),SendMail.getMessage());
				main.Main.getMailService().sendMail(mail);
				SendMail.setVisible(false);
	}

}