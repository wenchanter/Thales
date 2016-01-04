package com.wenchanter.thales.mail.utils;

import java.io.File;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * 邮件工具
 *
 * @author wang_hui
 *
 */
public class MailUtils {

    // private static Logger logger = Logger.getLogger(VelocityUtils.class);

    /**
     * 发送文本格式的邮件
     *
     * @param sender
     * @param message
     * @param templatePath
     * @param tenplateModel
     */
    public static void sendTemplateTextMail(String content, JavaMailSender sender,
            MailMessage message, String templatePath, Map<String, Object> tenplateModel) {
        // String content = VelocityUtils.generateEmailContent(templatePath, tenplateModel);
        if (message instanceof SimpleMailMessage) {
            SimpleMailMessage simpleMessage = (SimpleMailMessage) message;
            simpleMessage.setText(content);
            sender.send(simpleMessage);
        }
    }

    /**
     * 发送html格式邮件
     * 
     * @param sender
     * @param message
     * @param templatePath
     * @param tenplateModel
     * @throws MessagingException
     */
    public static void sendTemplateHtmlMail(String content, JavaMailSender sender,
            MailMessage message, String templatePath, Map<String, Object> tenplateModel)
            throws MessagingException {

        sendAttachmentMail(content, sender, message, templatePath, tenplateModel, null, null);

    }

    /**
     * 发送带附件的邮件
     *
     * @param sender
     * @param message
     * @param templatePath
     * @param tenplateModel
     * @param attachmentName 附件名
     * @param File 附件
     * @throws MessagingException
     */
    public static void sendAttachmentMail(String content, JavaMailSender sender,
            MailMessage message, String templatePath, Map<String, Object> tenplateModel,
            String attachmentName, File File) throws MessagingException {
        // String content = VelocityUtils.generateEmailContent(templatePath, tenplateModel);

        if (message instanceof SimpleMailMessage) {
            SimpleMailMessage simpleMessage = (SimpleMailMessage) message;
            MimeMessage mailMessage = sender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, false, "utf-8");

            messageHelper.setTo(simpleMessage.getTo());
            messageHelper.setFrom(simpleMessage.getFrom());
            if (!ArrayUtils.isEmpty(simpleMessage.getBcc())) {
                messageHelper.setBcc(simpleMessage.getBcc());
            }
            if (!ArrayUtils.isEmpty(simpleMessage.getCc())) {
                messageHelper.setCc(simpleMessage.getCc());
            }
            if (StringUtils.isNotBlank(simpleMessage.getSubject())) {
                messageHelper.setSubject(simpleMessage.getSubject());
            }
            if (StringUtils.isNotBlank(simpleMessage.getReplyTo())) {
                messageHelper.setReplyTo(simpleMessage.getReplyTo());
            }
            if (simpleMessage.getSentDate() != null) {
                messageHelper.setSentDate(simpleMessage.getSentDate());
            }

            messageHelper.setText(content, true);

            if (StringUtils.isNotBlank(attachmentName) && File != null) {
                messageHelper.addAttachment(attachmentName, File);
            }

            // 发送邮件
            sender.send(mailMessage);

        } else {
            // 暂不支持其他MailMessage
            String err = "Not support! Please use SimpleMailMessage";
            // logger.info(err);
            throw new IllegalArgumentException(err);
        }

    }

    /**
     * 校验email格式
     *
     * @param email
     * @return
     */
    public static boolean chkEmailFormat(String email) {
        String checkemail = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        Pattern regexemail = Pattern.compile(checkemail);
        Matcher matcheremail = regexemail.matcher(email);
        return matcheremail.matches();
    }
}
