package com.metamort.service.call.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostActivityUWMPRequestModel {

	@JsonProperty("ReferenceNo")
	private String referenceNo;
	@JsonProperty("TaskId")
	private Long taskId; // request
	@JsonProperty("ActionCode")
	private String actionCode;
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	public String getActionCode() {
		return actionCode;
	}
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}
	
	
}
