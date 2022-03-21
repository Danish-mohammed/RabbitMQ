package com.bridgelabz.userregistration.jwtoperations;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class Jms {
	@Autowired
	static JavaMailSender javaMailSender;

	public static void sendEmail(String toEmail, String subject, String body) {

//		System.out.println(toEmail);
//		System.out.println(subject);
//		System.out.println(body);
		String fromEmail = "mohammedatif90@gmail.com";
//		System.out.println(fromEmail);
		String password = "Mohammed@22";
//		System.out.println(password);
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
//		Authenticator auth = new Authenticator() {
//			@Override
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(toEmail, password);
//			}
//
//		};
//		Session session = Session.getInstance(prop, auth);
//		System.out.println(session);
		send(fromEmail, toEmail, subject, body);
	}


	private static void send(String fromEmail, String toEmail, String subject, String body) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
//			MimeMessage message = new MimeMessage();
			message.setFrom(fromEmail);
//			message.setFrom(new InternetAddress(fromEmail, body));
//			message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			message.setSubject(subject);
			message.setTo(toEmail);
			message.setText(body);
//			Transport.send(message);
			javaMailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("exception occurred while sending mail");
		}
	}

}

