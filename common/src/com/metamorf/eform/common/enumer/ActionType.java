package com.metamorf.eform.common.enumer;

public enum ActionType {
	CREATE("Create"),
	DELETE("Delete"),
	EDIT("Edit"),
	SUSPEND("Suspend"),
	UNSUSPEND("Unsuspend");
	
	String value;
	
	ActionType(String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}
