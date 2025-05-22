package org.example.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.PasswordAuthentication;
import java.util.Properties;

public class MailUtil {
    public static void send(String to, String subject, String html) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.example.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(props, new Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication("toxiccorporationservice@gmail.com", "pcflrpyowssowqcz");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("noreply@example.com"));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        msg.setSubject(subject);
        msg.setContent(html, "text/html; charset=utf-8");
        Transport.send(msg);
    }
}
