package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

@SuppressWarnings("serial")
public class EmailListModel extends AbstractListModel<Email>
{
    private final ArrayList<Email> emails;

    public EmailListModel(final ArrayList<Email> inputData)
    {
        this.emails = new ArrayList<>(inputData);
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
}
