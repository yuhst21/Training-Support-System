/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author win
 */
public class EmailAutoSend {

    public void sendEmail(String to) {
        final String user = "huyvqhe163567@fpt.edu.vn";//change accordingly
        final String password = "huyprodk1";//change accordingly
        //Get the session object
        String host = "smtp.gmail.com";
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", 587);
        prop.put("mail.smtp.ssl.trust", host);
        prop.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        Session session = Session.getDefaultInstance(prop,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
        //Compose the message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Your Support Request");
            message.setText("Hi there! We just wanted to let you know we received your "
                    + "message and will be in touch as soon as possible.");
            //send the message
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendEmailBySupporter(String to, String text, String supporter) {
        final String user = "huyvqhe163567@fpt.edu.vn";//change accordingly
        final String password = "huyprodk1";//change accordingly
        //Get the session object
        String host = "smtp.gmail.com";
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", 587);
        prop.put("mail.smtp.ssl.trust", host);
        prop.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        Session session = Session.getDefaultInstance(prop,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
        //Compose the message
        try {
            if (text != null) {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(user));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                message.setSubject("Your Support Request");
                message.setText(text + "\nBest Regards," + "\n" + supporter);
                //send the message
                Transport.send(message);
            }

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
