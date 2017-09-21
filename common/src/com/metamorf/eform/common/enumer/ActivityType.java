package com.metamorf.eform.common.enumer;

public enum ActivityType {
	//ACTIVITY("Activity"),
	AREA("Area"),
	BRANCH_LOCATION("Branch Location"),
	CONTENT_MANAGER("Content Manager"),
	EXCEPTION_HANDLING("Exception Handling"),
	HOLIDAY("Holiday"),
	LOGIN("Login"),
	LOGOUT("Logout"),
	REFERENCE("Reference"),
	REGION("Region"),
	ROLE("Role"),
	SECURITY_PARAM("Security Parameters"),	
	SYS_PARAM("System Prameter"),	
	USERS("Users"),	
	WORK_FLOW_PARAM("Workflow Parameter"),
	TBO("TBO Maintenance"),
	VERIFIER("Verifier"),
	CALLBACK("Callback"),
	EFORM_APPROVAL("Eform Approval"),
	MASTER_DATA_APPROVAL("Master Data Approval"),
	EDD_APPROVAL("EDD Approval"),
	DEVIASI("Deviasi"),
	ARCHIVE("Archive"),
	FORCE_FULLY_ACTIVE("Force Fully Active"),
	FORCE_APPROVE_CMS("Force Approve CMS"),
	PRE_EDD_APPROVAL("Pre EDD Approval"),
	SUB_BRANCH("Sub Branch Detail");
	
	
	String value;
	
	ActivityType(String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}
