package common;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Admin
 */
import dal.UserDAO;
import static java.lang.ProcessBuilder.Redirect.to;
import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.User;

public class SendEmail {

    //generate vrification code
    public String getRandom() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }
    Captcha c = new Captcha();

    //send email to the user email
//    public boolean sendEmail(User user) {
//        boolean test = false;
//
//        String toEmail = user.getEmail();
//        String fromEmail ="thanhndthe163697@fpt.edu.vn";
//        String password = "tho3152003";
//
//        try {
//
//            // your host email smtp server details
//            Properties pr = new Properties();
//            pr.setProperty("mail.smtp.host", "smtp.mail.com");
//            pr.setProperty("mail.smtp.port", "587");
//            pr.setProperty("mail.smtp.auth", "true");
//            pr.setProperty("mail.smtp.starttls.enable", "true");
//            pr.put("mail.smtp.socketFactory.port", "587");
//            pr.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
// 
//            //get session to authenticate the host email address and password
//            Session session = Session.getInstance(pr, new Authenticator() {
//                @Override
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(fromEmail, password);
//                }
//            });
//
//            //set email message details
//            Message mess = new MimeMessage(session);
//
//    		//set from email address
//            mess.setFrom(new InternetAddress(fromEmail));
//    		//set to email address or destination email address
//            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
//    		
//    		//set email subject
//            mess.setSubject("User Email Verification");
//            
//    		//set message text
//            mess.setText("Registered successfully.Please verify your account using this code: " + user.getCode());
//            //send the message
//            Transport.send(mess);
//            
//            test=true;
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return test;
//    }
    String rd;

    public String sendEmail(String to) {
        final String user = "thanhndthe163697@fpt.edu.vn";//change accordingly
        final String password = "thanh112002";//change accordingly
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
//        rd = getRandom();
        rd = c.generateCaptcha();
        //Compose the message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Your Support Request");

//            message.setText("Your password is "+rd);
            message.setText("Dear learner!\n"
                    + "\n"
                    + "The verification password you need to use to access your Emaill is:\n"
                    + "\n"
                    + rd
                    + "\n"
                    + "If you don't request this code, it's possible that someone is trying to access your Google Account . Do not forward or give this code to anyone.\n"
                    + "\n"
                    + "You are got this message because this email address is being list as email restore for Google Accounts . If this information is incorrect, please go here to remove your email address from that Google Accounts.\n"
                    + "\n"
                    + "Best regards!\n"
                    + "\n"
                    + "Group Training Support System");
            //send the message
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rd;
    }

    public void sendEmail2(int id, String to) {

        final String user = "thanhndthe163697@fpt.edu.vn";//change accordingly
        final String password = "thanh112002";//change accordingly
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

            message.setText("Hi learner,\n"
                    + "\n"
                    + "Forgot your password?\n"
                    + "We received a request to reset the password for your account\n"
                    + "\n"
                    + "To reset your password copy and paste the URL into your browser:\n"
                    + "http://localhost:9999/TrainingSupportSystem/change?" + getRandom());
            //send the message
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String sendEmailToGuest(String to) {
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
//        rd = getRandom();
        rd = c.generateCaptcha();
        //Compose the message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Password to new member !");

//            message.setText("Your password is "+rd);
            message.setText("Dear learner!\n"
                    + "\n"
                    + "The verification password you need to use to access your Emaill is:\n"
                    + "\n"
                    + rd
                    + "\n"
                    + ". Do not forward or give this code to anyone.\n"
                    + ".\n"
                    + "\n"
                    + "Best regards!\n"
                    + "\n"
                    + "Group Training Support System");
            //send the message
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rd;
    }

}
