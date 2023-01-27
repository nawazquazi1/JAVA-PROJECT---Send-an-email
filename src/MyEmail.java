import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class MyEmail {
    public static void main(String[] args) throws MessagingException, IOException {
        Properties properties=new Properties();
        properties.put("mail.smtp.auth",true);
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port",587);
        properties.put("mail.smtp.starttls.enable",true);
        properties.put("mail.transport.protocol","smtp");
        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("akibquazi345@gmail.com","*************");
            }
        });
        MimeMessage message=new MimeMessage(session);
        message.setSubject("Email from my java program");
        message.setFrom("akibquazi345@gmail.com");

        Address address=new InternetAddress("ateeqquazi1975@gmail.com");
        message.setRecipient(Message.RecipientType.TO,address);
        MimeMultipart mimeMultipart=new MimeMultipart();
        MimeBodyPart attachment=new MimeBodyPart();
        attachment.attachFile(new File("static/akib.pdf"));
        MimeBodyPart messageBodypart=new MimeBodyPart();
        messageBodypart.setContent("<h1> Email from my cool program ! </h1>","text/html");

        MimeBodyPart attachment2=new MimeBodyPart();
        attachment2.attachFile("static/nawaz.jpeg");
        mimeMultipart.addBodyPart(messageBodypart);
        mimeMultipart.addBodyPart(attachment);
        mimeMultipart.addBodyPart(attachment2);

        message.setContent(mimeMultipart);
        Transport.send(message);

    }
}
