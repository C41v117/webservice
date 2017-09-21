package com.metamorf.eform.common.enumer;

public enum UserType {
	BANK_USER("Bank User"),
	AGENT("Agent");
	
	
	String value;
	
	UserType(String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}
