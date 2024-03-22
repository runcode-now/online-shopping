package com.team3.onlineshopping.information;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class Email {
    private static final String from = "ashionshopping@gmail.com";
    private static final String password = "rbfnnzodvgzjxods";
    
    
    public static void sendEmail(String to, int id, int time, String subject,
                String content) throws UnsupportedEncodingException {
        // Cấu hình các thuộc tính của email
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Tạo phiên làm việc với thông tin đăng nhập
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            // Create MimeMessage
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            
            // Set subject
            message.setSubject(MimeUtility.encodeText(subject, "UTF-8", "B"));
            
            // Set content
            message.setContent(content, "text/html; charset=UTF-8");

            // Send mail
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    

    public static void main(String[] args) throws UnsupportedEncodingException {
//        sendEmail("ashionshopping@gmail.com", "anhphhe172226@fpt.edu.vn", 
//                "rbfnnzodvgzjxods", 1, "Đặt lại mật khẩu", "");
    }
}
