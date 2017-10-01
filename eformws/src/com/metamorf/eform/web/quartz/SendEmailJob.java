package com.metamorf.eform.web.quartz;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.metamorf.eform.common.core.SystemParameter;
import com.metamorf.eform.common.data.util.Operator;
import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.data.util.SearchOrder.Sort;
import com.metamorf.eform.common.enumer.EmailStatus;
import com.metamorf.eform.common.util.AES;
import com.metamorf.eform.entity.user.SendEmail;
import com.metamorf.eform.entity.user.User;
import com.metamorf.eform.interfaces.user.ISendEmailService;
import com.metamorf.eform.interfaces.user.IUserService;

public class SendEmailJob extends QuartzJobBean implements StatefulJob{
	Logger logger = LoggerFactory.getLogger("com.metamorf.eform.web.quartz");
	
	private ISendEmailService sendEmailService;
	private IUserService userService;
	private static Thread runningThread;
	private static Date heartBeatRate;
	
	public void setSendEmailService(ISendEmailService sendEmailService) {
		this.sendEmailService = sendEmailService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	private void startThread(){
		runningThread = new Thread("Thread send email "+System.currentTimeMillis()) {
			@Override
			public void run() {
				try {
					sendEmail();
				} catch (Exception e) {
					logger.error("ERROR Thread send email : "+e);
				}
			}
		};
		
		try {
			runningThread.start();
		} catch (Exception e) {
			logger.error("Error while starting Thread : ",e);
		}
		logger.info("Thread started");
	}

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			if (heartBeatRate == null) {
				heartBeatRate = new Date();
				logger.info("Initialize new heartbeat : "+heartBeatRate);
				startThread();	
			}else{
				Date currentDate = new Date();
				long gap = currentDate.getTime() - heartBeatRate.getTime();
				long diffMinutes = gap / (60 * 1000);

				if (diffMinutes >= Integer.parseInt(SystemParameter.EMAIL_TIME_INTERVAL_THREAD)) {
					if(runningThread != null){
						try {
							runningThread.interrupt();
						} catch (Exception e) {
							logger.error("Error while interrupting current thread: "+e);
						}	
					}
					heartBeatRate = new Date();
					logger.info("Reinitialize heartbeat : "+heartBeatRate);
					startThread();
				}else{
					
				}
			}
		}catch(Exception ex){
			logger.error("Error general exception when exectuting send email job : ",ex);
		}
		
	}

	private void sendEmail(){
		try{
			logger.info("Starting send email");
			heartBeatRate = new Date();
			List<SearchFilter> searchFilters = new ArrayList<SearchFilter>();
			searchFilters.add(new SearchFilter(SendEmail.EMAIL_STATUS, Operator.IN_ARRAY, new Object[] 
					{EmailStatus.QUEUE, EmailStatus.SENT}));
			
			List<SearchOrder> searchOrders = new ArrayList<SearchOrder>();
			searchOrders.add(new SearchOrder(SendEmail.CREATED_DATE, Sort.ASC));
			
			List<SendEmail> sendEmails = sendEmailService.findAll(0, 0, searchFilters, searchOrders);
			logger.debug("Got {} emails to send", sendEmails.size());
			for (SendEmail sendEmail : sendEmails) {
				logger.debug("Checking email data for username=[{}], email=[{}], retry=[{}], maxcount=[{}], userId=[{}], Id=[{}]", 
						sendEmail.getUsername(), sendEmail.getEmail(), sendEmail.getRetry(), sendEmail.getMaxCountRetry(), 
						sendEmail.getUserId(), sendEmail.getId());
				if(sendEmail.getRetry() <= sendEmail.getMaxCountRetry()){
					logger.debug("Sending email for username=[{}], email=[{}], retry=[{}], maxcount=[{}], userId=[{}], Id=[{}]", 
							sendEmail.getUsername(), sendEmail.getEmail(), sendEmail.getRetry(), sendEmail.getMaxCountRetry(), 
							sendEmail.getUserId(), sendEmail.getId());
						
					User user = userService.findByUsername(sendEmail.getUsername());

					Calendar cal = Calendar.getInstance();
					cal.setTime(sendEmail.getLastSendDate()!=null?sendEmail.getLastSendDate():new Date());
					cal.add(Calendar.MINUTE, Integer.valueOf(SystemParameter.DURATION_RETRY_SEND_EMAIL_VERIFICATION));

					Calendar newCal = Calendar.getInstance();
					newCal.setTime(new Date());

					sendEmail.setSubjectMessage(SystemParameter.EMAIL_VERIFICATION_SUBJECT);
					sendEmail.setBodyMessage(generateBodyEmail(user));
					
					if(sendEmail.getEmailStatus() == EmailStatus.QUEUE){
						sendEmail.setEmailStatus(EmailStatus.SENT);
						sendEmail.setLastSendDate(new Date());
						sendEmailService.sendEmail(sendEmail);
						sendEmailService.saveOrUpdate(sendEmail);
						heartBeatRate = new Date();
					}else if(cal.before(newCal) && (sendEmail.getEmailStatus() != EmailStatus.COMPLETE)){
						sendEmail.setLastSendDate(new Date());
						sendEmail.setRetry(sendEmail.getRetry()+1);
						sendEmailService.sendEmail(sendEmail);
						sendEmailService.saveOrUpdate(sendEmail);
						heartBeatRate = new Date();
					}

					logger.info("success sent to email : " + sendEmail.getEmail());
				}
			}
		}catch(Exception e){
			logger.info("error send email : ", e);
		}
		logger.info("Finished send email");
	}
	
	private String generateBodyEmail(User user) throws Exception{
		try{
			VelocityEngine ve = new VelocityEngine();
			ve.setProperty("resource.loader", "class");
			ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

	        ve.init();
	        
	        Template t = ve.getTemplate("TemplateEmailVerification.vm");
	        VelocityContext context = new VelocityContext();
	        context.put("username", user.getUsername());
	        context.put("link", generateLink(user));
	        
	        StringWriter writer = new StringWriter();
	        t.merge(context, writer);
        
	        return writer.toString();
		}catch (Exception e){
			logger.info("error generate body email : ", e);
			throw e;
		}
	}
	
	private String generateLink(User user){
		String token = "";
		try {
			AES.generateKey();
			token = AES.encryptString(user.getVerificationToken());
		} catch (Exception e) {
			logger.error("error encrypt verification token : ", e);
		}
		
		return SystemParameter.APP_URL + SystemParameter.VERIFICATION_URL + token;
	}
	
}
