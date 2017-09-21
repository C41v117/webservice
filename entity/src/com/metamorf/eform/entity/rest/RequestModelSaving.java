package com.metamorf.eform.entity.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestModelSaving extends RequestModelMainSaving{
	public RequestModelSaving() {
		super();
	}
	
	@JsonProperty("NextState")
	private String nextState;
	
	@JsonProperty("OfficerCode")
	private String officerCode;
	
	@JsonProperty("UserName")
	private String UserName;
	
	@JsonProperty("NextState")
	public String getNextState() {
	return nextState;
	}

	@JsonProperty("NextState")
	public void setNextState(String nextState) {
	this.nextState = nextState;
	}
	
	@JsonProperty("OfficerCode")
	public String getOfficerCode() {
	return officerCode;
	}

	@JsonProperty("OfficerCode")
	public void setOfficerCode(String officerCode) {
	this.officerCode = officerCode;
	}
	
	@JsonProperty("UserName")
	public void setUserName(String userName) {
		UserName = userName;
	}
	
	@JsonProperty("UserName")
	public String getUserName() {
		return UserName;
	}
}
