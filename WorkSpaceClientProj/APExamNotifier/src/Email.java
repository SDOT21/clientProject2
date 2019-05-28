//This one works, but you have to allow less secure app access from your google account
import javax.mail.internet.*;

import com.sun.net.httpserver.Authenticator;

import java.util.*;
import javax.mail.*;
public class Email {
	/*
	 * username: apexamnotifier@gmail.com
	 * password: APexam135
	 */

    String  d_email = "tennis.rule25@gmail.com",
            d_password = "fiktpihfystl",
            d_host = "smtp.gmail.com",
            d_port  = "465",
            m_to = "christinetang075@gmail.com",
            m_subject = "Testing",
            m_text = "testing email.";

    public Email()
    {
        Properties props = new Properties();
        props.put("mail.smtp.user", d_email);
        props.put("mail.smtp.host", d_host);
        props.put("mail.smtp.port", d_port);
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.socketFactory.port", d_port);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        SecurityManager security = System.getSecurityManager();

        try
        {
            SMTPAuthenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);
            session.setDebug(true);
            MimeMessage msg = new MimeMessage(session);
            msg.setText(m_text);
            msg.setSubject(m_subject);
            msg.setFrom(new InternetAddress(d_email));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(m_to));
            Transport.send(msg);
        }
        catch (Exception mex)
        {
            mex.printStackTrace();
        } 
    }

    public static void main(String[] args)
    {
    	Email email = new Email();
    }

    private class SMTPAuthenticator extends javax.mail.Authenticator
    {
        public PasswordAuthentication getPasswordAuthentication()
        {
            return new PasswordAuthentication(d_email, d_password);
        }
    }
}