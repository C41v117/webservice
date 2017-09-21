package com.metamorf.eform.web.entity;

import java.io.Serializable;

public class TokenContent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4920048442803690142L;
	
	private String token;
	private Long userId;
	private String clientSecret;
	private String userName;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
