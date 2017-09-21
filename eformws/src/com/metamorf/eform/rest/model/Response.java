package com.metamorf.eform.rest.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlAccessorType(XmlAccessType.FIELD)
public class Response {
	
	protected String result;
	
	protected String message;
	
	protected String json;
		
	public Response() {
		super();
	}
	
	public Response(String result) {
		super();
		this.result = result;
	}

	public Response(String result, String message) {
		super();
		this.result = result;
		this.message = message;
	}
	
	public Response(String result, String message, String json) {
		super();
		this.result = result;		
		this.message = message;		
		this.json = json;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}
}