package model;

import java.util.ArrayList;

/**
 * @author Sandro Portner
 * @author Janick R�egger
 */
public class Email {
    //TODO: Implement central storage of id, auto incrementing
    private int               id;
    private String            sender;
    
    private String            recipents;
    private String            subject;
    private String            message;
    private boolean 		  read;
    //Add eventually..(if enough time)
    private ArrayList<Byte[]> attachments;

    public Email (String sender, String recipients, String subject, String message) {
        this.sender = sender;
        this.recipents = recipients;
        this.subject = subject;
        this.message = message;
    }
    
    public int getId () {
        return this.id;
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