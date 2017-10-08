package com.metamorf.eform.service.user;

import java.util.List;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.metamorf.eform.common.core.SystemParameter;
import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.data.access.user.ISendEmailDAO;
import com.metamorf.eform.entity.user.SendEmail;
import com.metamorf.eform.interfaces.core.IBaseService;
import com.metamorf.eform.interfaces.user.ISendEmailService;

@Service
public class SendEmailService implements IBaseService<SendEmail>, ISendEmailService {

	private static Logger logger = LoggerFactory.getLogger("com.metamorf.eform.web.quartz");
	
	private ISendEmailDAO sendEmailDAO;
	
	public void setSendEmailDAO(ISendEmailDAO sendEmailDAO) {
		this.sendEmailDAO = sendEmailDAO;
	}

	@Override
	public List<SendEmail> findAll(int startNo, int offset, List<SearchFilter> searchFilters, List<SearchOrder> searchOrders) throws SystemException {
		return sendEmailDAO.findAll(startNo, offset, searchFilters, searchOrders);
	}

	@Override
	public SendEmail findById(Long id) throws SystemException {
		return sendEmailDAO.findById(id);
	}

	@Override
	public void saveOrUpdate(SendEmail anObject) {
		sendEmailDAO.saveOrUpdate(anObject);
	}

	@Override
	public void delete(SendEmail user) throws SystemException {
		sendEmailDAO.deleteObject(user);
	}

	@Override
	public PagingWrapper<SendEmail> findAllWithPagingWrapper(int startNo,
			int offset, List<SearchFilter> searchFilters,
			List<SearchOrder> searchOrders) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SendEmail> findReportCollection(List<SearchFilter> searchFilters,
			List<SearchOrder> searchOrders) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SendEmail> findReportCollection(int startNo, int offset,
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void sendEmail (SendEmail email) throws SystemException{
		try {
			logger.info("prepare send email...");
			Properties props = new Properties();
			props.put("mail.smtp.host", SystemParameter.EMAIL_HOST);
			props.put("mail.smtp.port", SystemParameter.EMAIL_PORT);
			props.put("mail.smtp.localhost", "brezyn.com");
			props.put("mail.smtp.auth", "true");
			
			logger.info("prepare properties send email..."); 
			Session session = Session.getInstance(props,
			     new javax.mail.Authenticator() {
			        protected PasswordAuthentication getPasswordAuthentication() {
			           return new PasswordAuthentication(SystemParameter.EMAIL_USERNAME, SystemParameter.EMAIL_PASSWORD);
			        }
			});

			logger.info("Success authenticate email sender...");
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(SystemParameter.EMAIL_SENDER));
			logger.info("Sender : "+SystemParameter.EMAIL_SENDER);
			
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(email.getEmail()));
			logger.info("Recipient : "+email.getEmail());
			
			message.setSubject(email.getSubjectMessage());
			logger.info("Subject Message : "+email.getSubjectMessage());
			
			message.setText(email.getBodyMessage());
			logger.info("Body Message : \n"+email.getBodyMessage());
			
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			
			helper.setTo(email.getEmail());
			helper.setText(email.getBodyMessage(), true);
			
		    BodyPart messageBodyPart = new MimeBodyPart();
	
		    logger.info("Prepare create body email...");
		    
		    messageBodyPart.setHeader("Content-Type", "text/plain; charset=\"us-ascii\";");
	        messageBodyPart.setContent(email.getBodyMessage(), "text/html; charset=utf-8");
	        Multipart multipart = new MimeMultipart();
	        multipart.addBodyPart(messageBodyPart);
	        
	        // Send the complete message parts
	        logger.info("Send complete message parts...");
	        message.setContent(multipart);
        
	        Transport.send(message);
			logger.info("Sent message successfully....");
		} catch (Exception e) {
			logger.error("Error when Send Message : ",e);
		}
	}
}
