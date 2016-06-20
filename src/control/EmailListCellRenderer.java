package control;
import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.*;
import javax.swing.ListCellRenderer;

import model.Email;

@SuppressWarnings("rawtypes")
public class EmailListCellRenderer implements ListCellRenderer
{
    private DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
    {
    	//Test the Object ..

        JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        Font   font = new Font("Helvetica", Font.PLAIN, 13);
        Email mail = (Email) value;
        String boldText;
        String attachmentPadding = "10px";

        String messageSubstr = mail.getMessage().length() > 100 ? mail.getMessage().substring(0, 100) + "..." : mail.getMessage();

        if(!mail.isRead()){
             boldText = "700";
        } else {
            boldText = "300";
        }

        String labelText = "<html><body style='width: 180px'<div style='margin: 2px 0 3px "+attachmentPadding+";'><p style='font-size: 10px; font-weight: "+boldText+";'>" + mail.getSender() + "</p>"+
                                   "<p style='font-size: 9px; margin-top: 2px;'>"+ mail.getSubject()+"</p>"+
                                   "<p style='font-size: 8px; font-weight: 300; color: gray; margin-top: 2px;'>"+messageSubstr+"</p></div></body></html>";

        renderer.setText(labelText);
        renderer.setFont(font);
        return renderer;
    }

}
