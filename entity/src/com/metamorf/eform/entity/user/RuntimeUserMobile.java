package com.metamorf.eform.entity.user;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.annotations.Expose;

@Entity(name = "RUNTIME_USER_MOBILE")
public class RuntimeUserMobile implements Serializable{

	private static final long serialVersionUID = 1045849832899437510L;
	public static final String USERNAME = "username";
	public static final String USER_ID = "userId";
	public static final String TOKEN = "token";
	public static final String APP_ID = "appId";
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Expose
	private Long id;
	
	@Column(name="USERNAME")
	@Expose
	private String username;
	
	@Column(name="APP_ID")
	@Expose
	private Integer appId;
	
	@Column(name="TOKEN")
	@Expose
	private String token;
	
	@Column(name="DEVICE_UID")
	@Expose
	private String deviceUid;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE")
	@Expose
	private Date createdDate;
	
	@Column(name="USER_ID")
	@Expose
	private Long userId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getAppId() {
		return appId;
	}
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getDeviceUid() {
		return deviceUid;
	}
	public void setDeviceUid(String deviceUid) {
		this.deviceUid = deviceUid;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

}