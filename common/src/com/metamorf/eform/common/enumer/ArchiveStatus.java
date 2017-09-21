package com.metamorf.eform.common.enumer;

public enum ArchiveStatus {
	PENDING("PENDING"), FAILED("FAILED"), SUCCESS("SUCCESS"), CANCELLED("CANCELLED");
	
	String value;
	
	ArchiveStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}