package com.nit.mail;



import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class DoctorRegisteredMailSender {

	@Autowired
	private JavaMailSender mailSender;
	
	public boolean send(String to,String cc,String bcc,String subject,String text,Resource resource) {
		boolean sent =false;
		MimeMessage message=mailSender.createMimeMessage();
		try {
		MimeMessageHelper helper=new MimeMessageHelper(message, resource!=null);
		helper.setTo(to);
		if(cc!=null)
		helper.setCc(cc);
		if(bcc!=null)
		helper.setBcc(bcc);
		helper.setSubject(subject);
		helper.setText(text);
		if(resource!=null)
			helper.addAttachment(resource.getFilename(), resource);
		mailSender.send(message);
		sent=true;
		}catch(Exception e) {
			e.printStackTrace();
			sent=false;
		}
		return sent;
	}
}
