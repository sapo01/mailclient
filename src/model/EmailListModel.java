package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

@SuppressWarnings("serial")
public class EmailListModel extends AbstractListModel<Email>
{
    private ArrayList<Email> emails;
    private int size;
        
    public EmailListModel(final ArrayList<Email> inputData)
    {
        this.emails = new ArrayList<>(inputData);
        this.size = this.emails.size();
    }
    
	@Override
    public int getSize()
    {
        return this.emails.size();
    }

    @Override
    public Email getElementAt(final int index)
    {
        return this.emails.get(index);
    }
    
    public void addEntry(final Email newMail)
    {
        this.emails.add(newMail);
        fireIntervalAdded(this, getSize() - 1, getSize() - 1);
    }

    public void setNewContent(final List<Email> newEntries)
    {
        this.emails.clear();
        this.emails.addAll(newEntries);
        fireContentsChanged(this, 0, getSize() - 1);
    }

    public void rowContentsChanged(final int startIndex, final int endIndex)
    {
        fireContentsChanged(this, startIndex, endIndex);
    }
    
    public void remove(final int index){
		this.emails.remove(index);
		fireIntervalRemoved(this,size,size);
	}
    
    @SuppressWarnings("rawtypes")
	public ArrayList open(final int index){
    	Email mail = this.emails.get(index);
    	ArrayList<String> components = new ArrayList<>();
    	components.add(mail.getSubject());
    	components.add(mail.getMessage());
    	return components;
    }
}
