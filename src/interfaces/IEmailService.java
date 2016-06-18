package interfaces;

import java.util.ArrayList;

import model.Email;

/**
 * @author Sandro Portner
 * @author Janick R�egger
 */
public interface IEmailService
{
    ArrayList<Email> getEmails();

    void sendMail(Email email);
}
