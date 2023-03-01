package com.doctor_patinet.doctor_patient_project.util;

import com.doctor_patinet.doctor_patient_project.Main;
import lombok.SneakyThrows;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSender {

    Properties properties ;
    public MailSender(){
         properties = AppUtil.loadProperties();
//         properties.setProperty("mail.smtp.user", "java.course.basic@gmail.com");
//         properties.setProperty("mail.smtp.password", "klompzowtevergvx");
    }

    @SneakyThrows
    public void sendMessage(String to, String subject,  String msg){

        Session session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(/*properties.getProperty("mail.smtp.userName")*/"java.course.basic@gmail.com", properties.getProperty("mail.smtp.password"));
                    }
                });

        Message message = new MimeMessage(session);
//        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(msg);
        Transport.send(message);
        System.out.println("System: Email Sent Successfully.");
    }

}
