package com.metamorf.eform.common.enumer;

public enum ModuleType {
	//mobile
	LOOKUP("Lookup"),//0
	REGION("Region"),
	AREA("Area"),
	SYSTEM_PARAMETER("System Parameter"),
	FAQ("Faq"),
	BRANCH_LOCATION("Branch Location"),//5
	SUB_BRANCH("Sub Branch"),
	LOR_MITRA("Kode Mitra"),

	//non mobile
	LOR_KANTOR_BAYAR("Kantor Bayar"), //LOOKUP
	POP_LOB("Point of Presence LOB"),
	LOR_KODE_PENSIUN("Kode Pensiun"), //10
	POP("Point of Presence"),
    BRANCH_CLASS(""),
    CONTENT_MANAGER(""),
    LOR_DOCUMENT(""),
    LOR_DOCUMENT_MAPPING(""),
    LOR_DOCUMENT_LIST(""),
    LOR_GOL_PNS(""),
    LOR_HUBUNGAN_KELUARGA(""),
    LOR_INSURANCE_RATE(""),
    LOR_ASURANSI(""),
    LOR_GRACE_PERIOD(""),
    LOR_JENIS_KELAMIN(""),
    LOR_JENIS_NASABAH(""),
    LOR_PENGAJUAN(""),
    LOR_PRODUCT(""),
    LOR_PROGRAM(""),
    LOR_JOINT_INCOME_JNS_USAHA(""),
    LOR_CITY(""),
    LOR_ZIP(""),
    LOR_PROVINCE(""),
    LOR_DOCUMENT_LOCATION(""),
    LOR_PROGRAM_AREA(""),
    LOR_PROGRAM_INTEREST_RATE(""),
    LOR_PROGRAM_MITRA(""),
    LOR_PROGRAM_PRODUCT_FEE(""),
    LOR_REJECT_REASON(""),
    LOR_DOCUMENT_STATUS(""),
    LOR_STATUS_KEPEMILIKAN(""),
    LOR_STATUS_NASABAH(""),
    LOR_CHANNEL(""); //R/A/B/SB	
	
	String value;
	
	ModuleType(String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}
