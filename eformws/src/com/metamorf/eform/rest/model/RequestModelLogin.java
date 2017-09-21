package com.metamorf.eform.rest.model;

import com.metamorf.eform.entity.rest.RequestModelMobile;

public class RequestModelLogin extends RequestModelMobile{
	
	private String signature;
	private String deviceUid;
	
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getDeviceUid() {
		return deviceUid;
	}
	public void setDeviceUid(String deviceUid) {
		this.deviceUid = deviceUid;
	}
		
}