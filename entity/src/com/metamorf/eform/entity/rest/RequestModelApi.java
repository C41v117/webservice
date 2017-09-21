package com.metamorf.eform.entity.rest;

public class RequestModelApi extends RequestModelMobile{
	
	private String json;
	private String token;
	
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}