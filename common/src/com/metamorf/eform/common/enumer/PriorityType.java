package com.metamorf.eform.common.enumer;

public enum PriorityType {
	VVIP("VVIP"),
	VIP("VIP"),
	Standard("Standard");
	
	String value;
	
	PriorityType(String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}
