
import java.util.ArrayList;

/**
 * @author Sandro Portner
 * @author Janick R�egger
 */
public interface IEmailService
{
    ArrayList<Email> getEmails();

    void sendMail(Email email);
}
