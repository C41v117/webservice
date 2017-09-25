package com.metamorf.eform.entity.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="USER")
public class User implements Serializable{
	
	private static final long serialVersionUID = -819480506707870364L;

	public static final String USERNAME = "username";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="USERNAME")
	private String username;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="CREATED_DATE")
	private Date createdDate;
	
	@Column(name="LAST_LOGIN_DATE")
	private Date lastLogInDate;

	@Column(name = "IS_VERIFY")
	private Boolean verify;
	
	@Column(name="VERIFY_DATE")
	private Date verifyDate;
	
	@Column(name = "IS_LOCK")
	private Boolean lock;
	
	@Column(name="LOCKED_DATE")
	private Date lockedDate;
	
	@Column(name = "IS_REQ_CHANGE_PWD")
	private Boolean reqChangePwd;
	
	@Column(name="APK_VERSION")
	private String apkVersion;
	
	@Column(name="MODEL_NUMBER")
	private String modelNumber;
	
	@Column(name="DEVICE_UID")
	private String deviceUid;
	
	@Column(name="ANDROID_VERSION")
	private String androidVersion;
	
	@Column(name="TOKEN")
	private String token;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastLogInDate() {
		return lastLogInDate;
	}

	public void setLastLogInDate(Date lastLogInDate) {
		this.lastLogInDate = lastLogInDate;
	}

	public Boolean getVerify() {
		return verify;
	}

	public void setVerify(Boolean verify) {
		this.verify = verify;
	}

	public Date getVerifyDate() {
		return verifyDate;
	}

	public void setVerifyDate(Date verifyDate) {
		this.verifyDate = verifyDate;
	}

	public Date getLockedDate() {
		return lockedDate;
	}

	public void setLockedDate(Date lockedDate) {
		this.lockedDate = lockedDate;
	}

	public Boolean getReqChangePwd() {
		return reqChangePwd;
	}

	public void setReqChangePwd(Boolean reqChangePwd) {
		this.reqChangePwd = reqChangePwd;
	}

	public Boolean getLock() {
		return lock;
	}

	public void setLock(Boolean lock) {
		this.lock = lock;
	}

	public String getApkVersion() {
		return apkVersion;
	}

	public void setApkVersion(String apkVersion) {
		this.apkVersion = apkVersion;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public String getDeviceUid() {
		return deviceUid;
	}

	public void setDeviceUid(String deviceUid) {
		this.deviceUid = deviceUid;
	}

	public String getAndroidVersion() {
		return androidVersion;
	}

	public void setAndroidVersion(String androidVersion) {
		this.androidVersion = androidVersion;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}