package com.wenchanter.thales.mail;

import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;

import com.wenchanter.thales.mail.utils.MailUtils;

@Repository
public class SendMail {

    @Resource(name = "mailsender")
    private JavaMailSender mailsender;

    @Resource(name = "mailMessage")
    private MailMessage mailMessage;

    public void sendMail(String content, String email) {
        synchronized (mailMessage) {

            mailMessage.setTo(email);

            // MailUtils.sendTemplateHtmlMail(content, mailsender, mailMessage, "/mail.vm",
            // mailModel);
            MailUtils.sendTemplateTextMail(content, mailsender, mailMessage, "", null);
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml");
        SendMail mail = (SendMail)act.getBean("sendMail");
        mail.sendMail("这个是降价信息邮件测试", "cker_03@163.com");
    }

}
