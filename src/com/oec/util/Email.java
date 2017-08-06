package com.oec.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.oec.vo.EmailVO;

public class Email {
	
	private static String from;
	private static String username;
	private static String password;
	
	static{
		Properties pp = new Properties();
		try {
			Class c = Email.class;
			ClassLoader cl = c.getClassLoader();
			pp.load(cl.getResourceAsStream("Admin.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		from=pp.getProperty("email");
		username=pp.getProperty("username");
		password=pp.getProperty("password");		
	}
	
   public static boolean sendEmail(EmailVO emailvo) {
     
      String to = emailvo.getReceiverMailId();
      String host = "smtp.gmail.com";

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "587");


      Session session = Session.getInstance(props,
      new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
         }
      });

      try {
    	  
         Message message = new MimeMessage(session);
         
         message.setFrom(new InternetAddress(from));

         message.setRecipients(Message.RecipientType.TO,
         InternetAddress.parse(to));

         message.setSubject(emailvo.getSubject());

         message.setText(emailvo.getMainBody());

         Transport.send(message);
         return true;

      } catch (MessagingException e) {
    	  return false;
      }
   }
}