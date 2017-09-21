package com.metamorf.eform.entity.rest;

public class RequestModelLorina extends RequestModelApi{
	
	private String taskId;
	
	private String actionCode;
	
	
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}
	
	public String getActionCode() {
		return actionCode;
	}
	

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
}