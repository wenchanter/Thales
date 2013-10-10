package com.wenchanter.thales.mail;

import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Repository;

import com.wenchanter.thales.mail.utils.MailUtils;

@Repository
public class SendMail {

	@Resource(name = "mailsender")
	private JavaMailSenderImpl mailsender;

	@Resource(name = "mailMessage")
	private SimpleMailMessage mailMessage;

	public void sendMail(String email,  Map<String, Object> mailModel) {
		synchronized (mailMessage) {

			mailMessage.setTo(email);

			try {
				MailUtils.sendTemplateHtmlMail(mailsender, mailMessage, "/mail.vm", mailModel);
			} catch (MessagingException e) {
				// TODO LOG
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
