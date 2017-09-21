package com.metamorf.eform.entity.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseModelForImage {
	
	@JsonProperty("ResponseCode")
	private String responseCode;
	
	@JsonProperty("ReferenceNo")
	private String referenceNo;
	
	@JsonProperty("ResponseMessage")
	private String responseMessage;
	
	public ResponseModelForImage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResponseModelForImage(String responseCode, String referenceNo,
			String responseMessage) {
		super();
		this.responseCode = responseCode;
		this.referenceNo = referenceNo;
		this.responseMessage = responseMessage;
	}
	
	@JsonProperty("ResponseCode")
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	
	@JsonProperty("ReferenceNo")
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	
	@JsonProperty("ResponseMessage")
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
	
	
	

}
