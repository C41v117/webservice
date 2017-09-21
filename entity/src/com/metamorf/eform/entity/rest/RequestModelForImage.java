package com.metamorf.eform.entity.rest;

import com.fasterxml.jackson.annotation.JsonProperty;



public class RequestModelForImage {
	
	@JsonProperty("FileId")
	private String fileId;
	
	@JsonProperty("FileName")
	private String fileName;
	
	@JsonProperty("ImageType")
	private String imageType;
	
	@JsonProperty("ReferenceNo")
	private String referenceNo;
	
	@JsonProperty("TaskId")
	private String taskId;
	
	
	public RequestModelForImage() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	@JsonProperty("FileId")
	public String getFileId() {
		return fileId;
	}


	public void setFileId(String fileId) {
		this.fileId = fileId;
	}


	@JsonProperty("FileName")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	@JsonProperty("ImageType")
	public String getImageType() {
		return imageType;
	}


	public void setImageType(String imageType) {
		this.imageType = imageType;
	}


	@JsonProperty("ReferenceNo")
	public String getReferenceNo() {
		return referenceNo;
	}


	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}


	@JsonProperty("taskId")
	public String getTaskId() {
		return taskId;
	}


	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	

}
