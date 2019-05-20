package com.yky.web.service.impl;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.yky.web.service.EmailService;
@Service
public class EmailServiceImpl implements EmailService {
	@Autowired
	 private JavaMailSender javaMailSender;
@Autowired
	   private SimpleMailMessage simpleMailMessage;

	  
	   @Override
	public void sendMailSimple(String to, String subject, String content)  {

	       try {
	    	   MimeMessage message=javaMailSender.createMimeMessage();
	    	   MimeMessageHelper helper=new MimeMessageHelper(message,true);
	          // simpleMailMessage.setTo(to);
	         
	           //simpleMailMessage.setSubject(subject);
	    	   helper.setFrom(simpleMailMessage.getFrom());
	    	   helper.setTo(simpleMailMessage.getTo());
	           helper.setSubject(subject);
	    	   helper.setText(content,true);

	           
	      
	           javaMailSender.send(message);

	       } catch (Exception e) {
	          e.printStackTrace();
	       }
	   }

	   public void setJavaMailSender(JavaMailSender javaMailSender) {
	       this.javaMailSender = javaMailSender;
	   }

	   public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
	       this.simpleMailMessage = simpleMailMessage;
	   }
}
