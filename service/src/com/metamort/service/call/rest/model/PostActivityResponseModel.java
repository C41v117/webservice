package com.metamort.service.call.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostActivityResponseModel {

	@JsonProperty("TaskActivityId")
	private String taskActivityId; // response
	@JsonProperty("DefaultResponse")
	private DefaultResponse defaultResponse;
	@JsonProperty("ResponseCode")
	private String responseCode;
	@JsonProperty("ResponseMessage")
	private String responseMessage;
	
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
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
	
	
}
