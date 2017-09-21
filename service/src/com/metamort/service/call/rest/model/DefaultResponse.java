package com.metamort.service.call.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DefaultResponse {
	@JsonProperty("ResponseCode")
	private String responseCode;
	@JsonProperty("ReferenceNo")
	private String referenceNo;
	@JsonProperty("ResponseMessage")
	private String responseMessage;

	public DefaultResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DefaultResponse(String responseCode, String referenceNo) {
		super();
		this.responseCode = responseCode;
		this.referenceNo = referenceNo;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	
	
	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
}
