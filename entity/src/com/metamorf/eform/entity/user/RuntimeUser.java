package com.metamorf.eform.entity.user;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "RUNTIME_USER")
public class RuntimeUser implements Serializable{

	private static final long serialVersionUID = 1045849832899437510L;
	
	public static final String USERNAME = "username";
	public static final String TOKEN = "token";
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="USERNAME")
	private String username;
	
	@Column(name="TOKEN")
	private String token;
	
	@Column(name="CREATED_DATE")
	private Date createdDate;
	
	@Column(name="DEVICE_UID")
	private String deviceUid;

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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDeviceUid() {
		return deviceUid;
	}

	public void setDeviceUid(String deviceUid) {
		this.deviceUid = deviceUid;
	}

}