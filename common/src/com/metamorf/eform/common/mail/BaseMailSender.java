package com.metamorf.eform.common.mail;

import java.io.File;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.metamorf.eform.common.core.SystemParameter;


public  abstract class BaseMailSender<T> {
	
	protected abstract void populateMimeMessageHelper(MimeMessageHelper messageHelper,Map<String,Object> generic);
	protected abstract void populateMimeMessageHelper(MimeMessageHelper messageHelper,T generic);
	
//	protected Session session;
	protected String username;
	protected String password;
	protected Properties properties ;
	protected int port;
	protected String host;
	protected VelocityEngine velocityEngine;
	protected Map<String,Object> templateLocation;
	
	public void setUsername(String username) {
		this.username = username;
		if(!"".equalsIgnoreCase(SystemParameter.MAIL_USERNAME)){
			this.username = SystemParameter.MAIL_USERNAME;
		}
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public void setPort(int port) {
		this.port = port;
		if(0!=(SystemParameter.MAIL_PORT)){
			this.port = SystemParameter.MAIL_PORT;
		}
	}

	public void setHost(String host) {
		this.host = host;
		if(!"".equalsIgnoreCase(SystemParameter.MAIL_HOST)){
			this.host = SystemParameter.MAIL_HOST;
		}
	}
	
	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}
	
	public void setTemplateLocation(Map<String,Object> templateLocation) {
		this.templateLocation = templateLocation;
	}
	
	/*public void setSession(Session session) {
		this.session = session;
	}*/
	public void sendMail(Map<String,Object> generic, String attachmentId, File attachmentFile) throws MailException, MessagingException {
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		//sender.setSession(session);
		sender.setHost(host);
		sender.setPort(port);
		sender.setUsername(username);
		sender.setPassword(password);
		sender.setJavaMailProperties(properties);
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		populateMimeMessageHelper(helper, generic);//helper.setTo("test@host.com"); helper.setText("Check out this image!");
		if(attachmentFile!=null&&attachmentId!=null){
			FileSystemResource file = new FileSystemResource(attachmentFile);
			helper.addAttachment(attachmentId, file);
		}
		sender.send(message);
	}

	public void sendMail(T generic, String attachmentId, File attachmentFile) throws MailException, MessagingException {
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
//		sender.setSession(session);
		sender.setHost(host);
		sender.setPort(port);
		sender.setUsername(username);
		sender.setPassword(password);
		sender.setJavaMailProperties(properties);
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		populateMimeMessageHelper(helper, generic);//helper.setTo("test@host.com"); helper.setText("Check out this image!");
		FileSystemResource file = new FileSystemResource(attachmentFile);
		helper.addAttachment(attachmentId, file);
		sender.send(message);
	}

}
