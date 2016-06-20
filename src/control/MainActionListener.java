package control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.Email;
import model.EmailListModel;
import model.MailPreferences;
import view.MainView;
import view.SendView;
import view.SettingsView;

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
					EmailListModel list = main.Main.getMsgList();
					int msg = view.getList().getSelectedIndex(); 
					String UID = view.getList().getSelectedValue().getId();
					list.remove(msg);
					main.Main.getMsgList().setNewContent(removeFromJSON(UID));
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
	
	public static MainView getView(){
		return view;
	}
	//WRONG -> CHANGE :)
    private ArrayList<Email> removeFromJSON(String UID){
    	final String JSON_SENDER   = "From";
        final String JSON_SUBJECT  = "Subject";
        final String JSON_MESSAGE  = "Message";
        
    	@SuppressWarnings("static-access")
    	ArrayList<JSONObject> jsonObjects  = new ArrayList<>();
        ArrayList<Email>      emailObjects = new ArrayList<>();
        JSONObject obj = null;
        try {
        	@SuppressWarnings("resource")
			Scanner jsonScanner = new Scanner(new File(MailPreferences.getMailPreferences().getInboxPath()), "UTF-8");
            // Reading each line of file and parse content to JSON-Object
            while (jsonScanner.hasNext()) {
                obj = (JSONObject) new JSONParser().parse(jsonScanner.nextLine());
                String uid = (String) obj.get("Id");
                if(!UID.equals(uid)){
                    jsonObjects.add(obj);
                }else{
                	//dont add to newList -> do nothing
                }
            }
         // Get receivers from message object
            final String receivers       = (String) obj.get("to");
            final String cc              = (String) obj.get("cc");

            // Generate Email Object and add it to ArrayList of Email objects
            emailObjects.add(
            		new Email(
            				UUID.randomUUID().toString(),
            				(String) obj.get(JSON_SENDER),
            				receivers,
            				cc,
            				(String) obj.get(JSON_SUBJECT),
            				(String) obj.get(JSON_MESSAGE))
            		);
        
        }
        catch (ParseException parseEx) {
        	System.out.println("JSON Object from file could not be parsed " + parseEx);
        }
        catch (IOException ioEx) {
        	System.out.println("JSON File could not be opened for readout " + ioEx);
        }
        
        return emailObjects;
 	}
}

class MySwingWorker extends SwingWorker<Object, Integer> {
	@Override
	protected Object doInBackground() throws Exception {
		while (!isCancelled()) {
			MainView.setList(main.Main.getMailService().getEmails());
			this.cancel(true);
		}
		return null;
	}
	
	protected void done(){
		Date date = new Date();
		JOptionPane.showMessageDialog(main.Main.getMainView(), "Updated at " + new Timestamp(date.getTime()));
	}
	
}


