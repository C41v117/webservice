package com.metamorf.eform.common.enumer;

import com.metamorf.eform.common.core.SystemConstant.RestApiDefinition.PurEloanMainSavingOperation;

public enum ActionCodeQuequeType {
	SUBMIT_VERIFIER("SVRF", PurEloanMainSavingOperation.taskActivity),
	SUBMIT_PRE_EDD("SPRE", PurEloanMainSavingOperation.taskActivity),
	SUBMIT_EDD_APPROVED("EDD",PurEloanMainSavingOperation.taskActivity),
	SUBMIT_EDD_REJECTED("SRJCT", PurEloanMainSavingOperation.savingRejection),
	SUBMIT_APPROVAL_APPROVED("SARPV", PurEloanMainSavingOperation.taskActivity),
	SUBMIT_APPROVAL_REJECTED("SRJCT", PurEloanMainSavingOperation.savingRejection),
	SUBMIT_DEVIASI_APPROVED("DAPPRV", PurEloanMainSavingOperation.deviationApprove),
	SUBMIT_DEVIASI_REJECTED("DRJCT", PurEloanMainSavingOperation.deviationRejection),
	PRE_EDD("PRE", PurEloanMainSavingOperation.taskActivity), 
	PRE_APPROVAL("APRVL", PurEloanMainSavingOperation.taskActivity), 
	PRE_VERIFIER("VRF", PurEloanMainSavingOperation.taskActivity),
	PRE_CARD_PERSO("ACT", PurEloanMainSavingOperation.cardPerso),
	CARD_PERSO("CASO",PurEloanMainSavingOperation.taskActivity),
	CARD_PERSO_ACTIVATION("AAPPRV", PurEloanMainSavingOperation.cardActivation), 
	CARD_PERSO_CANCELLATION("ARJCT", PurEloanMainSavingOperation.cardCancellation),
	PRE_DEVIASI("DEV", PurEloanMainSavingOperation.deviation),
	REPAIR_VERIFIER("RVRF", PurEloanMainSavingOperation.taskActivity),
	REPAIR_APPROVAL("RAPRVL", PurEloanMainSavingOperation.taskActivity),
	REQUEST_REPAIR_APPROVAL("RRAPRVL", PurEloanMainSavingOperation.taskActivity),
	REQUEST_REPAIR_VERIFIER("RRVRF", PurEloanMainSavingOperation.taskActivity), 
	SYSTEM_AUTO_REJECT("SARJCT", PurEloanMainSavingOperation.savingRejection),
	UPDATE_TASK_CIF("UPDTS", PurEloanMainSavingOperation.updateTaskCIF),
	
	SAME_DAY_REJECT("SDREJECT", PurEloanMainSavingOperation.sdReject);

	private String code;
	private String restURI;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	private ActionCodeQuequeType(String code, String restURI) {
		this.code = code;
		this.setRestURI(restURI);
	}

	/**
	 * Convert from Action Code (String)
	 * 
	 * @param code
	 * @return
	 */
	public static ActionCodeQuequeType convertFromCode(String code) {
		for (ActionCodeQuequeType actionCode : ActionCodeQuequeType.values()) {
			if (actionCode.getCode().equals(code)) {
				return actionCode;
			}
		}
		return null;
	}

	public String getRestURI() {
		return restURI;
	}

	public void setRestURI(String restURI) {
		this.restURI = restURI;
	}
}
