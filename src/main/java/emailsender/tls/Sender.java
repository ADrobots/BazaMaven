package emailsender.tls;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Sender {
    private String username;
    private String password;
    private Properties properties;

    public Sender(String username, String password) {
        this.username = username;
        this.password = password;

        properties= new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
    }

    public void send(String subjectm, String text, String from, String to) throws MessagingException {
        Session session=Session.getInstance(properties, new Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);//от кого
            message.setFrom(new InternetAddress(username));//кому
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subjectm);//заголовок письма
            message.setText(text);//Содержимое

            Transport.send(message);
        }catch (MessagingException e){
            throw  new RuntimeException(e);
        }
    }
}
