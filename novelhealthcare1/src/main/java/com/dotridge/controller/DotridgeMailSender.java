package com.dotridge.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dotridge.bean.EmailBean;

@RestController
@RequestMapping("/mailSender")
public class DotridgeMailSender {

	@Autowired
	private JavaMailSender sender;
	
	@RequestMapping("/sendEmail")
	public @ResponseBody EmailBean sendEmail(EmailBean mailBean) {
		MimeMessage message = sender.createMimeMessage();
		// Enable the multipart flag!
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message,true);
			helper.setTo(mailBean.getTo());
			helper.setText(mailBean.getBody());
			helper.setSubject(mailBean.getSubject());
			helper.setFrom(mailBean.getFrom());
			ClassPathResource file = new ClassPathResource("Desert.jpg");
			helper.addAttachment("Desert.jpg", file);


		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				   

		        sender.send(message);

		return mailBean;
	}
}
