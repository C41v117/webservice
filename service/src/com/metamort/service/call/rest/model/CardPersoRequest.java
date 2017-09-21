package com.metamort.service.call.rest.model;

import com.metamorf.eform.common.enumer.ActionCodeQuequeType;

public class CardPersoRequest {
	
	 private  String referenceNo;
	 private  Long taskId;
	 private  String officerCode;
	 private ActionCodeQuequeType actionCode;
	 
	public CardPersoRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CardPersoRequest(String referenceNo, Long taskId,
			String officerCode, ActionCodeQuequeType actionCode) {
		super();
		this.referenceNo = referenceNo;
		this.taskId = taskId;
		this.officerCode = officerCode;
		this.actionCode = actionCode;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getOfficerCode() {
		return officerCode;
	}

	public void setOfficerCode(String officerCode) {
		this.officerCode = officerCode;
	}

	public ActionCodeQuequeType getActionCode() {
		return actionCode;
	}

	public void setActionCode(ActionCodeQuequeType actionCode) {
		this.actionCode = actionCode;
	}

}
