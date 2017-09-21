package com.metamort.service.call.rest.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PostActivityWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9044288463277874315L;
	
	@JsonProperty("ReferenceNo")
	private String referenceNo;
	@JsonProperty("TaskId")
	private Long taskId; // request
	@JsonProperty("ActionCode")
	private String actionCode;
	@JsonProperty("NextState")
	private String nextState;
	@JsonProperty("OfficerCode") //ini harusnya username
	private String officerCode;
	@JsonProperty("UserName") //ini harusnya username
	private String UserName;
	@JsonProperty("TaskActivityId")
	private String taskActivityId; // response
	@JsonProperty("DefaultResponse")
	private DefaultResponse defaultResponse;
	@JsonProperty("ResponseCode")
	private String responseCode;
	@JsonProperty("ResponseMessage")
	private String responseMessage;
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	
	public Long getTaskId() {
		return taskId;
	}
	
	public String getActionCode() {
		return actionCode;
	}
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}
	public String getNextState() {
		return nextState;
	}
	public void setNextState(String nextState) {
		this.nextState = nextState;
	}
	public String getTaskActivityId() {
		return taskActivityId;
	}
	public void setTaskActivityId(String taskActivityId) {
		this.taskActivityId = taskActivityId;
	}
	public DefaultResponse getDefaultResponse() {
		return defaultResponse;
	}
	public void setDefaultResponse(DefaultResponse defaultResponse) {
		this.defaultResponse = defaultResponse;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public String getOfficerCode() {
		return officerCode;
	}
	public void setOfficerCode(String officerCode) {
		this.officerCode = officerCode;
	}
	
	public void setUserName(String userName) {
		UserName = userName;
	}
	
	public String getUserName() {
		return UserName;
	}
	
	@JsonIgnore
	public PostActivityWrapper getResponse(){
		PostActivityWrapper response = new PostActivityWrapper();
		response.setTaskActivityId(taskActivityId);
		response.setDefaultResponse(defaultResponse);
		response.setTaskId(taskId);
		return response;
	}
}
