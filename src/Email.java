import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

public class Email {
    public static void main(String[] args) {
        System.out.println("preparing to send message");
        String message = "<h1>Hello </h1> ";
        String subject = "CodersArea : confirmation";
        String to = "nawazquazi356@gmail.com";
        String from = "akibquazi345@gmail.com";

        //sendEmail(message,subject,to,from);
        sendAttach(message, subject, to, from);
    }

    /**
     * @param message send a message
     * @param subject set subject
     * @param to      send to
     * @param from    send from
     *                this is responsible to send the message with attachment
     */
    private static void sendAttach(String message, String subject, String to, String from) {
        String host = "smtp.gmail.com";

        //get the system properties
        Properties properties = new Properties();

        // setting important information to properties object
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");

        // step:1 to get the session object
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("akibquazi345@gmail.com", "**************");
            }
        });

        // step :2 compose the message [text , multi media]
        MimeMessage mimeMessage = new MimeMessage(session);
        try {

            //from email
            mimeMessage.setFrom(from);

            // adding recipient to message
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // adding subject to message
            mimeMessage.setSubject(subject);

            // file path
            String path = "C:\\Users\\Public\\Documents\\Send-an-Email\\static\\nawaz.jpeg";

            // attachment
            MimeMultipart multipart = new MimeMultipart();
            //text
            MimeBodyPart textMime = new MimeBodyPart();
            try {
                textMime.setText(message);
                multipart.addBodyPart(textMime);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //file
            MimeBodyPart fileMine = new MimeBodyPart();
            try {
                File file = new File(path);
                fileMine.attachFile(file);
                multipart.addBodyPart(fileMine);
            } catch (Exception e) {
                e.printStackTrace();
            }

            mimeMessage.setContent(multipart);

            // step :3 send the message using transport class
            Transport.send(mimeMessage);

            System.out.println("send success full ");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        /**
         * @param message send a message
         * @param subject set subject
         * @param to send to
         * @param from send from
         *    this is responsible to send email
         */

        private static void sendEmail (String message, String subject, String to, String from){
            // variable for emil
            String host = "smtp.gmail.com";

            //get the system properties
            Properties properties = System.getProperties();
            System.out.println("properties".toUpperCase() + properties);

            // setting important information to properties object
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.ssl.enable", "true");

            // step:1 to get the session object
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("akibquazi345@gmail.com", "nawaz01@01");
                }
            });

            session.setDebug(true);

            // step :2 compose the message [text , multi media]
            MimeMessage mimeMessage = new MimeMessage(session);
            try {
                //from email
                mimeMessage.setFrom(from);

                // adding recipient to message
                mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

                // adding subject to message
                mimeMessage.setSubject(subject);

                // adding text to message
                mimeMessage.setText(message);

                // step :3 send the message using transport class
                Transport.send(mimeMessage);

                System.out.println("send success full ");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
//