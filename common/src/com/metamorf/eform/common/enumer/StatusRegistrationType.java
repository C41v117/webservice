package com.metamorf.eform.common.enumer;

public enum StatusRegistrationType {
	REGISTERED("eform.status.registered"),
	RETURNED_TO_AGENT("eform.status.returned.agent"),
	RETURNED_TO_VERIFIER("eform.status.returned.verifier"),
	RETURNED_TO_APPROVER("eform.status.returned.approver"),
	RETURNED_TO_CALLBACK("eform.status.returned.callback"),
	VERIFIED("eform.status.verified"),
	REJECTED_BY_APPROVER("eform.status.rejected.approver"),
	REJECTED_BY_EDD("eform.status.rejected.edd"),
	APPROVED_BY_APPROVER("eform.status.approved.appprover"),
	APPROVED_BY_EDD("eform.status.approved.edd"),
	CALLBACK_WRONG_NUMBER("eform.status.callback.wrong.number"),
	CALLBACK_NO_ANSWER("eform.status.callback.no.answer"),
	CALLBACK_ANSWER("eform.status.callback.answer"),
	CALLBACK_FINISH("eform.status.callback.finish"),
	CALLBACK_CALLBACK("eform.status.callback.callback"),
	REJECTED_BY_VERIFIER("eform.status.rejected.verifier"),
	RETURNED_TO_PB("eform.status.returned.pb"),
	REJECTED_BY_PRE_EDD("eform.status.rejected.edd"),	
	APPROVED_BY_PRE_EDD("eform.status.approved.edd"),
	
	//purna
	APPROVED_BY_INTERVIEW("eform.status.approved.interview"), //ini state kalo di approve dari card activation / interview
	REJECTED_BY_INTERVIEW("eform.status.rejected.interview"), //ini state kalo di reject dari card activation / interview
	APPROVED_BY_DEVIASI_APPROVAL("eform.status.approved.deviasi.approval"), //ini state kalo di approve dari deviasi
	REJECTED_BY_DEVIASI_APPROVAL("eform.status.rejected.deviasi.approval"), //ini state kalo di reject dari deviasi
	
	START_REGISTRATION("eform.status.start.registration") // state sebelum eform mulai panggil customerService.registerCustomer untuk menghindari 2x pemanggilan di saat bersamaan
	;
	
	String value;
	
	StatusRegistrationType(String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}
