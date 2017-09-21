package com.metamorf.eform.common.enumer;

public enum RiskType {
	NOT_HIGH_RISK("verifier.not.high.risk", "L"), HIGH_RISK("verifier.high.risk", "H");
	
	String value;
	String code;
	
	RiskType(String value, String code) {
		this.value = value;
		this.code = code;
	}
	
	public String getValue(){
		return value;
	}

	public String getCode() {
		return code;
	}

	/**
	 * If not found, default to NOT HIGH RISK to cater NPE (not correct way
	 * actually)
	 * 
	 * @param code
	 * @return
	 */
	public static RiskType convertFromCode(String code) {
		for (RiskType riskType : RiskType.values()) {
			if (riskType.getCode().equals(code)) {
				return riskType;
			}
		}
		return RiskType.NOT_HIGH_RISK;
	}

}
