package eshop.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailerService {
	@Autowired
	JavaMailSender mailSender;
	
	public void send(String to, String subject, String body) {
		try {
			// Viet thu
			MimeMessage mail = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail, true, "utf-8");
			helper.setFrom("thucctin@gmail.com", "EShop Web Master");
			helper.setTo(to);
			helper.setReplyTo("thucctin@gmail.com");
			helper.setSubject(subject);
			helper.setText(body, true);
			// Gui thu
			mailSender.send(mail);
		} 
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
