package model;

import java.util.ArrayList;

/**
 * @author Sandro Portner
 * @author Janick R�egger
 */
public class Email {
    //TODO: Implement central storage of id, auto incrementing
    private String            id;
    private String            sender;
    private String 			  sendDate;
    
    private String            recipents;
    private String            cc;
    private String            subject;
    private String            message;
    private boolean 		  read = false;
    //Add eventually..(if enough time)
    private ArrayList<Byte[]> attachments;

    public Email (String id,String sendDate, String sender, String recipients,String cc, String subject, String message) {
        this.id = id;
        this.sendDate = sendDate;
    	this.sender = sender;
        this.recipents = recipients;
        this.cc = cc;
        this.subject = subject;
        this.message = message;
    }
    
    public String getId () {
        return this.id;
    }
    
    public String getSendDate(){
    	return this.sendDate;
    }

    public String getSubject () {
        return this.subject;
    }

    public String getMessage () {
        return this.message;
    }

    public String getRecipents () {
        return this.recipents;
    }
    
    public String getCc() {
        return this.cc;
    }

    public String getSender () {
        return this.sender;
    }

    @Override
    public String toString () {
        return this.message;
    }

    public boolean hasAttachment()
    {
        return attachments.size() > 0;
    }
    
    public boolean isRead(){
    	return this.read;
    }
    
    public void setRead(){
    	this.read = true;
    }
}