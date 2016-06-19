package model;

import java.awt.*;
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
    private boolean           isUnread;
    private ArrayList<Byte[]> attachments;

    public Email (int id, String sender, String recipients, String subject, String message) {
        this.id = id;
        this.sender = sender;
        this.recipents = recipients;
        this.subject = subject;
        this.message = message;
        this.attachments = new ArrayList<>();
    }

    public Email (int id, String sender, String recipients, String subject, String message, ArrayList<Byte[]> attachments) {
        this.id = id;
        this.sender = sender;
        this.recipents = recipients;
        this.subject = subject;
        this.message = message;
        this.attachments = new ArrayList<>(attachments);
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
    public boolean isUnread()
    {
        //TODO ^^
        return Math.random() > 0.5;
    }
}