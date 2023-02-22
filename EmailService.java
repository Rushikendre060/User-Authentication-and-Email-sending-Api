package com.email.service;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;

import com.email.entity.EmailRequest;

@Service
public class EmailService {

	public void Sendemail(EmailRequest request) {
		
		
	}
	
	
	public boolean Sendemail(String message,String subject,String to)
	{
		
		boolean f=false;
		
		String from ="rushikeshkendre060@gmail.com";
		
		//variable for email
		String host="smtp.gmail.com";
		
		//Get the system properties
		Properties properties=System.getProperties();
		System.out.println("Properties"+properties);
		
		//Setting important information to properties object
		//host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", 465);
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		//Step1: To get the session object
		Session session=Session.getInstance(properties, new Authenticator(){

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("rushikeshkendre555@gmail.com", "********");
			}		
		});
		
		session.setDebug(true);
		
		//Step 2:Compose the message[text,Multi-media]
		MimeMessage msg=new MimeMessage(session);
		
		try {
			
		//from EmailId
		msg.setFrom(from);
		//Adding Recipient to message
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		//Adding Subject to text message
		msg.setSubject(subject);
		//Adding text to message
		msg.setText(message);
		
		//Attachments 
		
		//FilePath
		String path="C:\\Users\\Lenovo\\Desktop\\IMG_6955.JPG";
		
		//we can put multiple things here like text/file
		MimeMultipart mimeMultipart=new  MimeMultipart();
		
		MimeBodyPart textmime=new MimeBodyPart();
		MimeBodyPart filemime=new MimeBodyPart();
		
		try {
			textmime.setText(message);
			
			File file=new File(path);
			filemime.attachFile(file);
			
			mimeMultipart.addBodyPart(textmime);
			mimeMultipart.addBodyPart(filemime);
		}catch (Exception e){
			e.printStackTrace();
		}
		
		msg.setContent(mimeMultipart);
		Transport.send(msg);
		System.out.println("Msg Send Successfully.....");
		f=true;
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return f;
	}

}
