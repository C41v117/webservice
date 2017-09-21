package com.metamort.service.call.rest.model;

public class CardPersoResponse {
	private DefaultResponse defaultResponse;
	private Integer taskId;
	
	
	public CardPersoResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CardPersoResponse(DefaultResponse defaultResponse, Integer taskId) {
		super();
		this.defaultResponse = defaultResponse;
		this.taskId = taskId;
	}

	public DefaultResponse getDefaultResponse() {
		return defaultResponse;
	}

	public void setDefaultResponse(DefaultResponse defaultResponse) {
		this.defaultResponse = defaultResponse;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	
}
