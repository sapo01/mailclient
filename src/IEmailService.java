
import java.util.ArrayList;

/**
 * @author Sandro Portner
 * @author Janick Rüegger
 */
public interface IEmailService
{
    ArrayList<Email> getEmails();

    void sendMail(Email email);
}
