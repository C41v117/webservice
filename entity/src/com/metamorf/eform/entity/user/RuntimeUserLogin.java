package com.metamorf.eform.entity.user;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "RUNTIME_USER_LOGIN")
public class RuntimeUserLogin implements Serializable{

	private static final long serialVersionUID = 3757200249064267029L;
	
	private Long userId;
	private String username;
	private String fullname;
	private String remoteAddress;
	private Date loginTime;
	public static final String USERNAME = "username";
	public static final String USER_ID	= "userId";
	public static final String FULLNAME = "fullname";
	public static final String DO_FORCE_LOGOUT = "doForceLogout";
	public static final String ACCESS_INFO_ID = "accessInfoId";
	private String sessionId;
	private Boolean doForceLogout;
	
	/*@Column(name = "ACCESS_INFO_ID", length = 50)
	private String accessInfoId;

	public String getAccessInfoId() {
		return accessInfoId;
	}

	public void setAccessInfoId(String accessInfoId) {
		this.accessInfoId = accessInfoId;
	}*/

	// Constructors

	/** default constructor */
	public RuntimeUserLogin() {
	}

	/** full constructor */
	public RuntimeUserLogin(String username, String fullname, String remoteAddress, Date loginTime) {
		this.username = username;
		this.fullname = fullname;
		this.remoteAddress = remoteAddress;
		this.loginTime = loginTime;
	}

	// Property accessors
	@Id
	@Column(name = "USERID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "USERNAME", length = 40)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "FULLNAME", length = 400)
	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "REMOTE_ADDRESS", length = 45)
	public String getRemoteAddress() {
		return this.remoteAddress;
	}

	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LOGIN_TIME", length = 7)
	public Date getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	@Column(name = "SESSION_ID", length = 50)
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Column(name="DO_FORCE_LOGOUT", columnDefinition="smallint default 0")
	public Boolean getDoForceLogout() {
		return doForceLogout;
	}

	public void setDoForceLogout(Boolean doForceLogout) {
		this.doForceLogout = doForceLogout;
	}

}