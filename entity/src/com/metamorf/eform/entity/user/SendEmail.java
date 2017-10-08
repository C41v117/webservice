package com.metamorf.eform.entity.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.metamorf.eform.common.enumer.EmailStatus;
import com.metamorf.eform.common.enumer.EmailType;

@Entity(name="SEND_EMAIL")
public class SendEmail {

	public static final String[] MAINTENANCE_LIST_FIELDS = {
		"id", "userId", "username", "email", "emailStatus", "subjectMessage",  
		"bodyMessage", "retry", "createdDate", "lastSendDate", "maxCountRetry", "type"
	};
	public static final String EMAIL_STATUS = "emailStatus";
	public static final String CREATED_DATE = "createdDate";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="USER_ID")
	private Long userId;
	
	@Column(name="USERNAME")
	private String username;
	
	@Column(name="email")
	private String email;

	@Column(name="EMAIL_STATUS")
	@Enumerated(EnumType.ORDINAL)
	private EmailStatus emailStatus;

	@Column(name="TYPE")
	@Enumerated(EnumType.ORDINAL)
	private EmailType type;
	
	@Column(name="SUBJECT_MESSAGE")
	private String subjectMessage;
	
	@Column(name="BODY_MESSAGE")
	private String bodyMessage;
	
	@Column(name="RETRY")
	private Integer retry;
	
	@Column(name="CREATED_DATE")
	private Date createdDate;
	
	@Column(name="LAST_SEND_DATE")
	private Date lastSendDate;
	
	@Column(name="MAX_COUNT_RETRY ")
	private Integer maxCountRetry;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EmailStatus getEmailStatus() {
		return emailStatus;
	}

	public void setEmailStatus(EmailStatus emailStatus) {
		this.emailStatus = emailStatus;
	}

	public String getSubjectMessage() {
		return subjectMessage;
	}

	public void setSubjectMessage(String subjectMessage) {
		this.subjectMessage = subjectMessage;
	}

	public String getBodyMessage() {
		return bodyMessage;
	}

	public void setBodyMessage(String bodyMessage) {
		this.bodyMessage = bodyMessage;
	}

	public Integer getRetry() {
		return retry;
	}

	public void setRetry(Integer retry) {
		this.retry = retry;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastSendDate() {
		return lastSendDate;
	}

	public void setLastSendDate(Date lastSendDate) {
		this.lastSendDate = lastSendDate;
	}

	public Integer getMaxCountRetry() {
		return maxCountRetry;
	}

	public void setMaxCountRetry(Integer maxCountRetry) {
		this.maxCountRetry = maxCountRetry;
	}

	public EmailType getType() {
		return type;
	}

	public void setType(EmailType type) {
		this.type = type;
	}
	
}
