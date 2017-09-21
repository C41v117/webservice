package com.metamorf.eform.common.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import com.metamorf.eform.common.enumer.ImageType;
import com.metamorf.eform.common.enumer.ModuleType;


public class SystemConstant {	
	
	public static class SubmitInterview {
		public static final String YA = "1";
		public static final String TIDAK = "2";
		public static final String NA = "3";
	}
	
	//SDB
	/*public static class HighRiskTypeSDB {
		public static final String OCCUPATION 		= "Pekerjaan, ";
		public static final String INDUSTRY 		= "Bidang Usaha, ";
		public static final String COUNTRY	 		= "Title, ";
		public static final String DHIB	 			= "Teroris, ";
	}

	public static class HighRiskTypeSinaya {
		public static final String OCCUPATION 		= "Pekerjaan, ";
		public static final String INDUSTRY 		= "Bidang Usaha, ";
		public static final String COUNTRY	 		= "Negara, ";
		public static final String DHIB	 			= "DHIB, ";
		
		public static final String OCCUPATION2 		= "Pekerjaan pemohon 2, ";
		public static final String INDUSTRY2 		= "Bidang Usaha pemohon 2, ";
		public static final String COUNTRY2	 		= "Negara pemohon 2, ";
		public static final String DHIB2 			= "DHIB pemohon 2, ";
	}
	
	public static class HighRiskStatus {
		public static final String YA 		= "YA";
		public static final String TIDAK 	= "TIDAK";
	}*/
	
	public static final String IS_PROCEED_CARD_PERSO_ALLOWED = "IS_PROCEED_CARD_PERSO_ALLOWED";

	public static final String XLS_REPORT_EXT = ".xls";
	public static final String PDF_REPORT_EXT = ".pdf";
	
	public static final String SDB_BRANCH_NAME = "SDB BRANCH";
	public static final String SDB_BRANCH_CODE = "9999";
	
	public static final String SDB_TITLE_CODE = "SDB";
	
	public static final String SDB_USER_FIRST_NAME = "SDB";
	public static final String SDB_ID_TYPE = "1";
	
	//Project di Customer
    public static class ProjectType {
		public static final String SINAYA_STR = "SINAYA";
		public static final String SDB_STR = "SDB";
		public static final String PURNABAKTI_STR = "PURNABAKTI";
		public static final String BOTH_STR = "ALL";

		public static final Integer SINAYA = 0;
		public static final Integer SDB = 1;
		public static final Integer BOTH = 2;
		public static final Integer PUR = 3;
		public static final Integer NULL_QUERY_RESULT = 9999;
		
		public static final String[] ProjectTypeStr = {SINAYA_STR,SDB_STR,BOTH_STR, PURNABAKTI_STR};
		
		// List containing LOB which region/area/branch are maintained in eform
		public static final List<Integer> ENABLE_ADD_REGION_AREA_BRANCH = Arrays.asList(SINAYA, SDB, BOTH);
		
		public static final Map<Integer, String> projectList = new HashMap<Integer, String>();
		static{
			projectList.put(SINAYA, SINAYA_STR);
			projectList.put(PUR, PURNABAKTI_STR);
		}
		
		public static final String[] userLobStr = {SINAYA_STR, PURNABAKTI_STR, BOTH_STR};
		
		public static final Map<Integer, String> userLobList = new HashMap<Integer, String>();
		static{
			userLobList.put(BOTH, BOTH_STR);
			userLobList.put(SINAYA, SINAYA_STR);
			userLobList.put(PUR, PURNABAKTI_STR);
		}
		static{
			userLobList.put(BOTH, BOTH_STR);
			userLobList.put(SINAYA, SINAYA_STR);
			userLobList.put(PUR, PURNABAKTI_STR);
		}
		public static final Map<String, Integer> enumLobList = new HashMap<String, Integer>();
		static{
			enumLobList.put(BOTH_STR, BOTH);
			enumLobList.put(SINAYA_STR, SINAYA);
			enumLobList.put(PURNABAKTI_STR, PUR);
		}
    }
    
    public static class DivisionType {
    	public static String ALL = "";   	
        public static String AE = "AE";
        public static String AESales = "AESales";
        public static String AEService = "AEService";  
        public static String PUR = "PUR";
        public static String PURSales = "PURSales";
        public static String PURService = "PURService";

        public static String ALL_STR = "All";
        public static String UWMP_PB_PURNA_SALES_STR = "Purna Sales";
        public static String UWMP_PB_PURNA_SERVICE_STR = "Purna Service";
        public static String UWMP_PB_AE_SALES_STR = "AE Sales";
        public static String UWMP_PB_AE_SERVICE_STR = "AE Service";
        
        public static String REGION = "REGION";
        public static String AREA = "AREA";
        public static String BRANCH_LOCATION = "BRANCH_LOCATION";
        public static String SUB_BRANCH = "SUB_BRANCH";
        
        public static Map<String, String> divisionListForFilter = new LinkedHashMap<String, String>();
        static{
        	divisionListForFilter.put(DivisionType.ALL, DivisionType.ALL_STR);
        	divisionListForFilter.put(SystemParameter.UWMP_PB_PURNA_SALES, UWMP_PB_PURNA_SALES_STR);
        	divisionListForFilter.put(SystemParameter.UWMP_PB_PURNA_SERVICE, UWMP_PB_PURNA_SERVICE_STR);
        	divisionListForFilter.put(SystemParameter.UWMP_PB_AE_SALES, UWMP_PB_AE_SALES_STR);
        	divisionListForFilter.put(SystemParameter.UWMP_PB_AE_SERVICE, UWMP_PB_AE_SERVICE_STR);
        }
        
        public static Map<String, String> divisionList = new HashMap<String, String>();
        static{
        	divisionList.put(SystemParameter.UWMP_PB_PURNA_SALES, UWMP_PB_PURNA_SALES_STR);
        	divisionList.put(SystemParameter.UWMP_PB_PURNA_SERVICE, UWMP_PB_PURNA_SERVICE_STR);
        	divisionList.put(SystemParameter.UWMP_PB_AE_SALES, UWMP_PB_AE_SALES_STR);
        	divisionList.put(SystemParameter.UWMP_PB_AE_SERVICE, UWMP_PB_AE_SERVICE_STR);
        }
        
        public static  Map<String, String> mappingDivision = new HashMap<String, String>();
        static{
        	mappingDivision.put(SystemParameter.UWMP_PB_PURNA_SALES_DIVISION,SystemParameter.UWMP_PB_PURNA_SALES);
        	mappingDivision.put(SystemParameter.UWMP_PB_PURNA_SERVICE_DIVISION,SystemParameter.UWMP_PB_PURNA_SERVICE);
        	mappingDivision.put(SystemParameter.UWMP_PB_AE_SALES_DIVISION,SystemParameter.UWMP_PB_AE_SALES);
        	mappingDivision.put(SystemParameter.UWMP_PB_AE_SERVICE_DIVISION,SystemParameter.UWMP_PB_AE_SERVICE);
        }
        
        public static Map<String, String> mappingStructureName = new HashMap<String, String>();
        static{
        	mappingStructureName.put(SystemParameter.UWMP_PB_REGION_STRUCTURE, REGION);
        	mappingStructureName.put(SystemParameter.UWMP_PB_AREA_STRUCTURE, AREA);
        	mappingStructureName.put(SystemParameter.UWMP_PB_BRANCH_LOCATION_STRUCTURE, BRANCH_LOCATION);
        	mappingStructureName.put(SystemParameter.UWMP_PB_SUB_BRANCH_STRUCTURE, SUB_BRANCH);
        }
    }

	//EMAIL / SMS
	public static final String LINE_BREAK_CODE_AFTER = "\r\n";
	public static final String LINE_BREAK_CODE_BEFORE = "\\n";
	
	//Karyawan BTPN
	public static final String KARYAWAN_BTPN_CODE = "06";
	
	//RA CODE
	public static final String PREFIX_RA_CODE = "B";
	
	//Report Sinaya
	public static final String KETERANGAN_FINAL_DAY = "Pending Task di ";
	
	//NOTIFICATION TYPE
	public static final String SMS = "1";
	public static final String EMAIL = "2";
	public static final String SMS_EMAIL = "3";
	
	//JOB
	public static final String AUTO_REJECT_NOTES = "Auto Reject";
	public static final String SYSTEM = "SYSTEM";
	
	//CALLBACK
	public static final String CALLBACK_PROCESS_NAME = "callback_flow_process";
	public static final String PICKUP_TASK_KEY = "callback.pickuptask";
	public static final String DO_CALL_TASK_KEY = "callback.docall";	
	
	public static final String CALLBACK_ROLE = "callbackGroup";
	
	public static final String PICKED_UP 		= "callPickedUp";	
	public static final String WRONG_NUMBER 	= "wrongNumber";
	public static final String NEED_RESCHEDULE 	= "needReschedule";
	public static final String RESCHEDULE_TIME 	= "rescheduleTime";
	public static final String CALL_SUCCESS	 	= "callSuccess";
	public static final String CALLBACK_COUNT_DOWN 	= "callbackCountDown";
	public static final String NEED_REPAIR	 	= "needRepair";
	
	//WORKFLOW
	//public static final String PROCESS_NAME = "wow_approval_flow_process";
	public static final String VERIFICATION_TASK_KEY = "wow.customer-data-verification";
	public static final String APPROVAL_TASK_KEY = "wow.approval";
	public static final String PRE_EDD_APPROVAL_TASK_KEY = "wow.pre-edd-approval";
	public static final String EDD_APPROVAL_TASK_KEY = "wow.edd-approval";
	public static final String DEVIASI_TASK_KEY = "purna.deviasi";
		
	public static final String VERIFY_ROLE = "verifier";
	public static final String APPROVAL_ROLE = "approver";
	public static final String PRE_EDD_APPROVAL_ROLE = "pre_edd_approver";
	public static final String EDD_APPROVAL_ROLE = "edd_approver";
	public static final String DEVIASI_ROLE = "deviasi";
	
	public static final String PROJECT = "project";
	public static final String NEED_VERIFIER = "needVerifier";
	public static final String IS_RELEASED = "isReleased";
	public static final String IS_ACCEPTED = "isAccepted";
	public static final String IS_AUTO_REJECTED = "isAutoRejected";
	public static final String SUBMIT_DATE = "submitDate";
	public static final String BRANCH_ID = "branchId";
	public static final String IS_DEVIASI = "isDeviasi";

	public static final String EDD_APPROVAL_RESULT = "edd_approval_result";
	public static final String APPROVAL_RESULT = "approval_result";
	public static final String VERIFIER_RESULT = "verifier_result";
	public static final String NEED_CALLBACK = "needCallback";
	public static final String NEED_EFORM_APPROVAL = "needEformApproval";
	public static final String NEED_EDD_APPROVAL = "needEddApproval";
	public static final String BUSINESS_KEY = "businessKey";
	public static final String IMAGE_READY_SIGNAL = "imageReadySignal";
	public static final String REPAIR_SIGNAL = "repairSignal";
	public static final String SEMI_ACTIVE_ACCOUNT_READY_SIGNAL = "semiActiveAccountReadySignal";
	
	public static final String RECAPTCHA_PUBLIC_KEY ="6LdFYsESAAAAADc5B5e9brgINvhCEuYwt5QceKPK";
	public static final String RECAPTCHA_PRIVATE_KEY="6LdFYsESAAAAAHSS-lJDGrgcGrvzdSfaLs-iUSZZ";
	public static final String RECAPTCHA_CHALLENGE_FIELD="recaptcha_challenge_field";
	public static final String RECAPTCHA_RESPONSE_FIELD="recaptcha_response_field";
	
	public static final int FIELD_TYPE_INT = 0;
    public static final int FIELD_TYPE_LONG = 1;
    public static final int FIELD_TYPE_DOUBLE = 2;
    public static final int FIELD_TYPE_STRING = 3;
    public static final int FIELD_TYPE_DATE = 4;
    public static final int FIELD_TYPE_BOOLEAN = 5;
    public static final int FIELD_TYPE_TIME = 6;
    public static final int FIELD_TYPE_DAY = 7;
    
    public static final Boolean yes = Boolean.valueOf(true);
    public static final Boolean no = Boolean.valueOf(false);
    
    public static final Map<Boolean, String> yesNo = new HashMap<Boolean, String>();
	static{
		yesNo.put(Boolean.TRUE, "Yes");
		yesNo.put(Boolean.FALSE, "No");
	}
    
    public static final String USER_TYPE = "userType";
    
    public static final String URL_SESSION = "URL_SESSION";
    
    public static final String CACHE_PARAM = "CACHE_PARAM";
    
    public static final String DEFAULT_MONEY_FORMAT = "###,###,###.##";
    public static final String DEFAULT_EXCHANGE_FORMAT = "#,###,###.####";
    
    public static final Boolean SHOW_AGREEMENT = true;
    
    public static final String DOCUMENT_DETAIL_BULK = "BULK";
    
    public static final String DOCUMENT_DETAIL_ITEM = "ITEM";
   
    public static class Button {
    	public static final String SUBMIT = "Submit";
    	public static final String CANCEL = "Cancel";
    }
 	
    public static class WorkflowColor {
    	public static final int GREEN = 0;
    	public static final int YELLOW = 1;
    	public static final int RED = 2;
    }
    
    public static class WorkflowStateName {
    	public static final String AKTIFASI = "Aktifasi";
    	public static final String VERIFIKASI = "Verifikasi";
    	public static final String VERIFIKASI_SDB = "Verifikasi SDB";
    	public static final String CALLBACK = "Callback";
    	public static final String REPAIR = "Repair";
    	public static final String APPROVE = "Approval";
    	public static final String APPROVE_SDB = "Approval SDB";
    	public static final String EDD = "EDD";
    	public static final String PRE_EDD = "Pre EDD";
    	public static final String APPROVED = "Approved";
    	public static final String REJECTED = "Rejected";
    	public static final String CALLBACK_PURNA = "Callback Purna";
    	public static final String APPROVAL_PURNA = "Approval Purna";
    	public static final String EDD_PURNA = "EDD Purna";
    	public static final String PRE_EDD_PURNA = "Pre EDD Purna";
    	public static final String VERIFIKASI_PURNA = "Verifikasi Purna";
    	public static final String AKTIFASI_PURNA = "Aktifasi Purna";
    	public static final String DEVIASI = "Deviasi";
    	public static final String CARD_ACTIVATION = "Card Activation";
    	public static final String READY_TO_PERSO = "Ready_to_Perso";
    	public static final String READY_TO_VISIT = "Ready_to_Visit";
    	public static final String ACTIVATED = "Activated";
    	public static final String CANCELED = "Canceled";
    	
    	public static final String[] CARD_ACTIVATIONS = {READY_TO_PERSO, READY_TO_VISIT,ACTIVATED, CANCELED};
    }
    
    public static final int FINISHED = 1;
    public static final int NOT_FINISHED = 0;
    
    public static class UserType {
    	public static final int INTERNAL = 1;
    	public static final int EXTERNAL = 2;
    	
    	public static final String INTERNAL_STR = "Internal";
    	public static final String EXTERNAL_STR = "External";
    }
    
    public static class AccountStatus {
    	public static final int FREEZE = 1;
    	public static final int UNFREEZE = 2;
    	
    	public static final String FREEZE_STR = "Freeze";
    	public static final String UNFREEZE_STR = "Unfreeze";
    }
    
    public static class RoleType {
    	public static final int INTERNAL = 1;
    	public static final int EXTERNAL = 2;
    	
    	public static final String INTERNAL_STR = "Internal";
    	public static final String EXTERNAL_STR = "External";
    }
    
    public static class FinancingType {
    	public static final String FINANCING_SUPPLIER		= "0";
    	public static final String FINANCING_DISTRIBUTOR	= "1";
    }
	
	public static final String SYSTEM_DATE_DAY = "dd";
    public static final String SYSTEM_DATE_MONTH = "MM";
    public static final String SYSTEM_DATE_YEAR = "yyyy";
    public static final String SYSTEM_DATE_YEAR_SHORT = "yy";
    public static final String SYSTEM_DATE_MASK = "dd-MM-yyyy";
    public static final String SYSTEM_DATE_MASK_SLASH = "dd/MM/yyyy";
    public static final String SYSTEM_DATE_MONTH_DETAIL = "dd-MMM-yyyy";
    public static final String SYSTEM_DATE_MONTH_YEAR = "MMM-yyyy";
    public static final String SYSTEM_DATE_TIME_MASK="dd-MMM-yyyy hh:mm";
    public static final String SYSTEM_MONTH_YEAR_MASK = "MM-yyyy";
    public static final String SYSTEM_YEAR_MONTH_MASK = "yyyy-MM";
    public static final String SYSTEM_FULL_MONTH_YEAR_MASK = "MMMMM yyyy";
    public static final String SYSTEM_FULL_MONTH_FULL_DATE_MASK = "dd MMMMM yyyy";
    public static final String SYSTEM_TIME_MASK = "dd-MM-yyyy HH:mm:ss";
    public static final String SYSTEM_TIME_MASK2 = "yyyy-MM-dd HH:mm:ss";
    public static final String SYSTEM_TIME_MASK_SECONDHAND = "dd-MM-yyyy HH:mm";
    public static final String HOUR_MINUTE_MASK = "HH:mm";
    public static final String HOUR_MINUTE_SECOND_MASK = "HH:mm:ss";
    public static final String HOUR_MINUTE_SECOND_MASK_NO_DELIMITER = "HHmmss";
    public static final String HOUR_MINUTE_MASK_NO_DELIMITER = "HHmm";
    public static final String DATABASE_DATE_FORMAT_STD = "MM/dd/yyyy";
    public static final String HOUR_MINUTE_SECOND_AMPM= "hh:mm:ss a";
    public static final String SYSTEM_FULL_DATE = "dd MM yyyy";
    public static final String SYSTEM_REPORT_DATE = "yyyyMMdd";
    public static final String WEB_SERVICE_DATETIME = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String WEB_SERVICE_DATE = "yyyy-MM-dd";
    public static final String WEB_SERVICE_TIME = "HH:mm:ss.SSS";
    public static final String SYSTEM_DATE_MASK_ADO = "dd/MM/yy";
    public static final String MESSAGE_DATE_MASK = "yyyyMMddHHmmssSSS";
    public static final String LETTER_DATE = "ddMMyyyy_HHmm";
    public static final String SYSTEM_REPORT_FILENAME_DATE = "ddMMyyyy_kkmm";
    public static final String EXPORT_DATETIME = "yyyy-MM-dd.HH-mm-ss-SSS";
    public static final String SERIALIZED_DATETIME = "M/d/yy K:mm:ss aa.SSS";
    public static final String SYSTEM_COM_TIME_MASK = "dd/MM/yyyy HH:mm:ss";
    public static final String BPM_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String TO_OUTSYSTEM = "yyyyMMddHHmmss";
    public static final String API_GATEWAY_DATETIME = "yyyy-mm-dd'T'HH:mm:ss.SSSXXX";
    public static final String UWMP_SYNC_USER_DATE_FORMAT = "ddMMyyyyHHmmss";
    
    public static final String SYSTEM_CURRENCY_MASK = "#,##0.00";
    public static final String DECIMAL_MASK = "###0.00";
    public static final String SYSTEM_NUMBER_MASK = "#,##0";
    public static final String SYSTEM_DOUBLE_MASK = "#############.##";
    
    public static final int SUGGESTION_STATUS_PENDING=0;
    public static final int SUGGESTION_STATUS_ACTIVE=1;
    
    //WSDL Location
    public static final String WSDL_SOURCE_LOCATION = "/wsdl/";
    
    public static class UserLock {
    	public static final boolean IS_LOCK = true;
        public static final boolean IS_NOT_LOCK = false;
    }
    
    public static class MasterDataStatus {
    	public static final int NEW=0;
    	public static final int ACTIVE=1;
    	public static final int INACTIVE=2;
    	
    	public static final String NEW_STR = "New";
    	public static final String ACTIVE_STR = "Active";
    	public static final String INACTIVE_STR = "Inactive";
    	
    	public static final String[] MasterDataStatusStr= {NEW_STR, ACTIVE_STR, INACTIVE_STR};
    	
    	public static final Map<Integer, String> masterDataStatusListExNew = new HashMap<Integer, String>();
		static{
			masterDataStatusListExNew.put(ACTIVE, ACTIVE_STR);
			masterDataStatusListExNew.put(INACTIVE, INACTIVE_STR);
		}
		
		public static final Map<Boolean, Integer> masterDataStatusUwmpSync = new HashMap<Boolean, Integer>();
		static{
			masterDataStatusUwmpSync.put(Boolean.TRUE, ACTIVE);
			masterDataStatusUwmpSync.put(Boolean.FALSE, INACTIVE);
		}
    }
    
    public static class MasterDataBeneficiaryType {
    	public static final int BLANK = 0;
    	public static final int IN_HOUSE = 1;
    	public static final int DOMESTIC = 2;
    	public static final int REMITTANCE = 3;
    	
    	public static final String BLANK_STR = "";
    	public static final String IN_HOUSE_STR = "In House";
    	public static final String DOMESTIC_STR = "Domestic";
    	public static final String REMITTANCE_STR = "Remittance";
    	
        public static final Map<Integer, String> masterDataBeneficiaryTypeList = new HashMap<Integer, String>();
		static{
			masterDataBeneficiaryTypeList.put(BLANK, BLANK_STR);
			masterDataBeneficiaryTypeList.put(IN_HOUSE, IN_HOUSE_STR);
			masterDataBeneficiaryTypeList.put(DOMESTIC, DOMESTIC_STR);
			masterDataBeneficiaryTypeList.put(REMITTANCE, REMITTANCE_STR);
		}
    }
    
    public static class BankAccountBeneficiaryType {
    	public static final int DOMESTIC = 1;
    	public static final int REMITTANCE = 2;
    	
    	public static final String DOMESTIC_STR = "Domestic";
    	public static final String REMITTANCE_STR = "Remittance";
    	
        public static final Map<Integer, String> masterDataBeneficiaryTypeList = new HashMap<Integer, String>();
		static{
			masterDataBeneficiaryTypeList.put(DOMESTIC, DOMESTIC_STR);
			masterDataBeneficiaryTypeList.put(REMITTANCE, REMITTANCE_STR);
		}
    }
    
    //list dedscription on loan report
    public static class schemaTypeDescription {
    	public static final int SUPPLIER=0;
    	public static final int BUYER=1;
    	
    	public static final String SUPPLIER_STR = "Supplier Financing";
    	public static final String BUYER_STR = "Distributor Financing";
    	
    	public static final String[] schemaTypeList= {SUPPLIER_STR, BUYER_STR};
    	
    	public static final Map<Integer, String> schemaTypeMap = new HashMap<Integer, String>();
		static{
			schemaTypeMap.put(SUPPLIER, SUPPLIER_STR);
			schemaTypeMap.put(BUYER, BUYER_STR);
		}    	
    }
    
    //Exception Handling ProcessStatus
    public static class exceptionHandlingProcessStatus{
    	public static final int FAIL 		= 0;
    	public static final int SUCCESS 	= 1;
    	
    	public static final String FAIL_STR 	 = "Fail";
    	public static final String SUCCESS_STR 	 = "Success";
    	
    	public static final Map<Integer, String> processStatusMap = new HashMap<Integer, String>();
		static{
			processStatusMap.put(FAIL, FAIL_STR);
			processStatusMap.put(SUCCESS, SUCCESS_STR);
		}
    }
    
    public static class exceptionHandlingType{
    	public static final String CREATE_CUSTOMER 		= "Create Customer";
    	public static final String CREATE_CUSTOMER_PURNA_BAKTI	= "Create Customer Purna Bakti";
    	public static final String UPDATE_CUSTOMER 		= "Update Customer";
    	public static final String UPDATE_CUSTOMER_PURNA_BAKTI	= "Update Customer Purna Bakti";
    	public static final String CLOSE_ACCOUNT_SDB 			= "Close Account SDB";
    	public static final String CLOSE_ACCOUNT_PURNA_BAKTI 	= "Close Account Purna Bakti";
    	public static final String APPROVE_CMS		 	= "Approve CMS";
    }
    
    public static class documentTypeDescription {
    	public static final String ALLOCATION = "L";
    	public static final String PURCHASE_ORDER = "P";
    	public static final String DELIVERY_ORDER = "D";
    	public static final String GOOD_REEIPT = "G";
    	public static final String INVOICE = "I";
    	public static final String DEBIT_NOTE = "DN";
    	public static final String CREDIT_NOTE = "CN";
    	
    	public static final String ALLOCATION_STR = "Lot Allocation";
    	public static final String PURCHASE_ORDER_STR = "Purchase Order";
    	public static final String DELIVERY_ORDER_STR = "Delivery Order";
    	public static final String GOOD_RECEIPT_STR = "Good Receipt";
    	public static final String INVOICE_STR = "Invoice";
    	public static final String DEBIT_NOTE_STR = "Debit Note";
    	public static final String CREDIT_NOTE_STR = "Credit Note";
    	
    	public static final String[] schemaTypeList= {ALLOCATION_STR, PURCHASE_ORDER_STR, DELIVERY_ORDER_STR, GOOD_RECEIPT_STR, INVOICE_STR, DEBIT_NOTE_STR, CREDIT_NOTE_STR};
    	
    	public static final Map<String, String> documentTypeMap = new HashMap<String, String>();
		static{
			documentTypeMap.put(ALLOCATION, ALLOCATION_STR);
			documentTypeMap.put(PURCHASE_ORDER, PURCHASE_ORDER_STR);
			documentTypeMap.put(DELIVERY_ORDER, DELIVERY_ORDER_STR);
			documentTypeMap.put(GOOD_REEIPT, GOOD_RECEIPT_STR);
			documentTypeMap.put(INVOICE, INVOICE_STR);
			documentTypeMap.put(DEBIT_NOTE, DEBIT_NOTE_STR);
			documentTypeMap.put(CREDIT_NOTE, CREDIT_NOTE_STR);
		}    	
    }
    
    public static class statusDescription {
    	public static final int NEW = 0;
    	public static final int CURRENT = 1;
    	public static final int DUE = 2;
    	public static final int DUE_GRACE = 3;
    	public static final int DUE_PENALTY = 4;
    	public static final int SETTLED=5;
    	public static final int PARTIAL_SETTLED=6;
    	
    	public static final String NEW_STR = "New";
    	public static final String CURRENT_STR = "Current";
    	public static final String DUE_STR = "Due";
    	public static final String DUE_GRACE_STR = "Over Due - Grace Period";
    	public static final String DUE_PENALTY_STR = "Over Due - Penalty Period";
    	public static final String SETTLED_STR = "Settled";
    	public static final String PARTIAL_STR = "Partial Settled";
    	
    	public static final String[] statusList= {NEW_STR, CURRENT_STR, DUE_STR, DUE_GRACE_STR, DUE_PENALTY_STR, SETTLED_STR, PARTIAL_STR};
    	
    	public static final Map<Integer, String> statusMap = new HashMap<Integer, String>();
		static{
			statusMap.put(NEW, NEW_STR);
			statusMap.put(CURRENT, CURRENT_STR);
			statusMap.put(DUE, DUE_STR);
			statusMap.put(DUE_GRACE, DUE_GRACE_STR);
			statusMap.put(DUE_GRACE, DUE_PENALTY_STR);
			statusMap.put(SETTLED, SETTLED_STR);
			statusMap.put(PARTIAL_SETTLED, PARTIAL_STR);
		}    	
    }
    
    
    //list description dropdown pada menu Predefined Beneficiary
    public static final class organizationDirectory {
    	public static final Map<Integer, String> LIST = new HashMap<Integer, String>();
		static{
			LIST.put(0, "");
			LIST.put(1, "BIC");
			LIST.put(2, "SSB");
			LIST.put(3, "CHIPS");
			LIST.put(4, "FEDWIRE");
			LIST.put(5, "OTHER");
			LIST.put(6, "SORTCODE");
		}   	
    }
    
    public static final class identicalStatus {
    	public static final Map<Integer, String> LIST = new HashMap<Integer, String>();
		static{
			LIST.put(0, "Remitter is identical with Beneficiary");
			LIST.put(1, "Remitter is identical without Beneficiary");
			LIST.put(2, "Not Applicable");
		}   	
    }
    
    public static final class beneficiaryStatus {
    	public static final Map<Integer, String> LIST = new HashMap<Integer, String>();
		static{
			LIST.put(0, "Resident");
			LIST.put(1, "Non-Resident");
			LIST.put(2, "Not Applicable");
		}   	
    }
    
    public static final class beneficiaryCitizenship {
    	public static final Map<Integer, String> LIST = new HashMap<Integer, String>();
		static{
			LIST.put(0, "Citizenship");
			LIST.put(1, "Non-Citizenship");
			LIST.put(2, "Not Applicable");
		}   	
    }
    
    public static final class beneficiaryCategory {
    	public static final Map<Integer, String> LIST = new HashMap<Integer, String>();
		static{
			LIST.put(0, "Bank Central");
			LIST.put(1, "Government");
			LIST.put(2, "Individual");
			LIST.put(3, "Company");
			LIST.put(4, "Not Applicable");
		}   
    }
    
    public static final class transactionRelationship {
    	public static final Map<Integer, String> LIST = new HashMap<Integer, String>();
		static{
			LIST.put(0, "Affiliated");
			LIST.put(1, "Non-Affiliated");
			LIST.put(2, "Not Applicable");
		}   	
    }
    
    public static class MasterDataApprovalStatus {
    	public static final int PENDING=0;
    	public static final int APPROVED=1;
    	
    	public static final String NONE_STR = "None";
    	public static final String PENDING_STR = "Pending Approval";
    	public static final String APPROVED_STR = ""; //this is intentionally left blank, please do not add any string in this variable 
    	
    	public static final String[] MasterDataApprovalStatusStr= {PENDING_STR, APPROVED_STR};
    	
    	public static final Map<Integer, String> MasterDataApprovalStatusList = new HashMap<Integer, String>();
		static{
			MasterDataApprovalStatusList.put(APPROVED, NONE_STR);
			MasterDataApprovalStatusList.put(PENDING, PENDING_STR);
		}    	
    }
    
    public static class MyTaskApprovalStatus {
    	public static final int PENDING=0;
        public static final int APPROVED=1;
        public static final int REJECTED=2;
        public static final int REQUEST_REPAIR=3;
        public static final int NEED_OVERRIDE=4;
        public static final int DELETE=5;
        
        public static final String PENDING_STR = "Pending Approval";
        public static final String APPROVED_STR = "Approved";
        public static final String REJECTED_STR = "Rejected";
        public static final String REQUEST_REPAIR_STR = "Request Repair";
        public static final String NEED_OVERRIDE_STR = "Need Override";
        public static final String DELETE_STR = "Deleted";
        
        public static final String[] MyTaskApprovalStatusStr = {PENDING_STR, APPROVED_STR, REJECTED_STR, REQUEST_REPAIR_STR, NEED_OVERRIDE_STR, DELETE_STR};
        
        public static final Map<String, String> MyTaskApprovalStatusList = new HashMap<String, String>();
		static{
			MyTaskApprovalStatusList.put(String.valueOf(PENDING), PENDING_STR);
			MyTaskApprovalStatusList.put(String.valueOf(APPROVED), APPROVED_STR);
			MyTaskApprovalStatusList.put(String.valueOf(REJECTED), REJECTED_STR);
			MyTaskApprovalStatusList.put(String.valueOf(REQUEST_REPAIR), REQUEST_REPAIR_STR);
			MyTaskApprovalStatusList.put(String.valueOf(NEED_OVERRIDE), NEED_OVERRIDE_STR);
			MyTaskApprovalStatusList.put(String.valueOf(DELETE), DELETE_STR);
		}
		
		public static final Map<String, String> MyTaskMakerStatusList = new HashMap<String, String>();
		static{
			MyTaskMakerStatusList.put(String.valueOf(PENDING), PENDING_STR);
			MyTaskMakerStatusList.put(String.valueOf(REQUEST_REPAIR), REQUEST_REPAIR_STR);
		}
    }
    
    public static class LoanApprovalStatus {
    	public static final int NEW=0;
      	public static final int PENDING=1;
        public static final int REQUEST_REPAIR=2;
        public static final int APPROVED=3;
        public static final int REJECTED=4;
        public static final int PARTIAL=5;
        
        public static final String NEW_STR = "New";
        public static final String PENDING_STR = "Pending Approval";
        public static final String APPROVED_STR = "Approved";
        public static final String REJECTED_STR = "Rejected";
        public static final String REQUEST_REPAIR_STR = "Request Repair";
        public static final String PARTIAL_STR = "Partial";
        
        public static final String[] LoanApprovalStatusStr = {NEW_STR, PENDING_STR, REQUEST_REPAIR_STR, APPROVED_STR, REJECTED_STR, PARTIAL_STR};
        
        public static final Map<String, String> LoanApprovalStatusList = new HashMap<String, String>();
		static{
			LoanApprovalStatusList.put(String.valueOf(NEW), NEW_STR);
			LoanApprovalStatusList.put(String.valueOf(PENDING), PENDING_STR);
			LoanApprovalStatusList.put(String.valueOf(APPROVED), APPROVED_STR);
			LoanApprovalStatusList.put(String.valueOf(REJECTED), REJECTED_STR);
			LoanApprovalStatusList.put(String.valueOf(REQUEST_REPAIR), REQUEST_REPAIR_STR);
			LoanApprovalStatusList.put(String.valueOf(PARTIAL), PARTIAL_STR);
		}	
    }
    
    public static class InvoiceApprovalStatus {
      	public static final int PENDING=0;
        public static final int REQUEST_REPAIR=1;
        public static final int APPROVED=2;
        public static final int REJECTED=3;
        public static final int DELETED=4;
        
        public static final String PENDING_STR = "Pending Approval";
        public static final String APPROVED_STR = "Approved";
        public static final String REJECTED_STR = "Rejected";
        public static final String REQUEST_REPAIR_STR = "Request Repair";
        public static final String DELETED_STR = "Deleted";
        
        public static final String[] InvoiceApprovalStatusStr = {PENDING_STR, REQUEST_REPAIR_STR, APPROVED_STR, REJECTED_STR, DELETED_STR};
        
        public static final Map<String, String> InvoiceApprovalStatusList = new HashMap<String, String>();
		static{
			InvoiceApprovalStatusList.put(String.valueOf(PENDING), PENDING_STR);
			InvoiceApprovalStatusList.put(String.valueOf(APPROVED), APPROVED_STR);
			InvoiceApprovalStatusList.put(String.valueOf(REJECTED), REJECTED_STR);
			InvoiceApprovalStatusList.put(String.valueOf(REQUEST_REPAIR), REQUEST_REPAIR_STR);
			InvoiceApprovalStatusList.put(String.valueOf(DELETED), DELETED_STR);
		}	
    }
    
    public static final class LoanStatus {
    	public static final int NEW = 0;
    	public static final int CURRENT = 1;
    	public static final int DUE = 2;
    	public static final int OVER_DUE_GRACE_PERIOD = 3;
    	public static final int OVER_DUE_PENALTY_PERIOD = 4;
    	public static final int SETTLED = 5;
    	public static final int PENDING_APPROVAL = 6; //used for changing financing date
    	
    	public static final String NEW_STR = "New";
    	public static final String CURRENT_STR = "Current";
    	public static final String DUE_STR = "Due";
    	public static final String OVER_DUE_GRACE_PERIOD_STR = "Over Due - Grace Period";
    	public static final String OVER_DUE_PENALTY_PERIOD_STR = "Over Due - Penalty Period";
    	public static final String SETTLED_STR = "Settled";
    	public static final String PENDING_STR = "Pending Date Approval";
    	
    	public static final String[] LoanStatusStr = {NEW_STR, CURRENT_STR, DUE_STR, OVER_DUE_GRACE_PERIOD_STR, OVER_DUE_PENALTY_PERIOD_STR, SETTLED_STR, PENDING_STR};
    	
    	public static final Map<Integer, String> LoanStatusList = new HashMap<Integer, String>();
		static{
			LoanStatusList.put(NEW, String.valueOf(NEW_STR));
			LoanStatusList.put(CURRENT, String.valueOf(CURRENT_STR));
			LoanStatusList.put(DUE, String.valueOf(DUE_STR));
			LoanStatusList.put(OVER_DUE_GRACE_PERIOD, String.valueOf(OVER_DUE_GRACE_PERIOD_STR));
			LoanStatusList.put(OVER_DUE_PENALTY_PERIOD, String.valueOf(OVER_DUE_PENALTY_PERIOD_STR));
			//LoanStatusList.put(SETTLED, String.valueOf(SETTLED_STR));
			LoanStatusList.put(PENDING_APPROVAL, String.valueOf(PENDING_STR));
		}
		
		public static final String MASALAH = "MASALAH";
		public static final String GAGAL_MASALAH = "GAGAL_MASALAH";
		public static final String MASALAH_GAMBAR = "MASALAH_GAMBAR";
		public static final String GAGAL_MASALAH_GAMBAR = "GAGAL_MASALAH_GAMBAR";
		
		public static final Map<String, String> masalahMap = new HashMap<>();
		static{
			masalahMap.put(MASALAH, MASALAH);
			masalahMap.put(GAGAL_MASALAH, GAGAL_MASALAH);
			masalahMap.put(MASALAH_GAMBAR, MASALAH_GAMBAR);
			masalahMap.put(GAGAL_MASALAH_GAMBAR, GAGAL_MASALAH_GAMBAR);
		}
		
    }
    
    public static final class MakerAction {
    	public static final int EDIT		= 0;
        public static final int SUSPEND		= 1;
        public static final int UNSUSPEND	= 2;
        public static final int ADD			= 3;
        public static final int REPAIR		= 4;
        public static final int RESET_AAPL 	= 5;
        public static final int LOCK 		= 6;
        public static final int UNLOCK 		= 7;
        public static final int FORCE_LOGOUT= 8;
        public static final int DELETE 		= 9;
        public static final int UPLOAD 		= 10;
        public static final int RESET_IMSI  = 11;
        
        public static final String EDIT_STR = "Edit";
    	public static final String SUSPEND_STR = "Suspend";
    	public static final String UNSUSPEND_STR = "Unsuspend";
    	public static final String ADD_STR = "Add";
    	public static final String REPAIR_STR = "Repair";
    	public static final String RESET_AAPL_STR = "Reset Password";
    	public static final String LOCK_STR = "Lock";
    	public static final String UNLOCK_STR = "Unlock";
    	public static final String FORCE_LOGOUT_STR = "Force Logout";
    	public static final String DELETE_STR = "Delete";
    	public static final String UPLOAD_STR = "Upload";
    	public static final String RESET_IMSI_STR = "Reset IMSI";
    	
    	public static final String[] ActionStr= {EDIT_STR, SUSPEND_STR, UNSUSPEND_STR, ADD_STR, REPAIR_STR, RESET_AAPL_STR, LOCK_STR, UNLOCK_STR, FORCE_LOGOUT_STR, DELETE_STR, UPLOAD_STR, RESET_IMSI_STR};
    	
    	public static final Map<String, String> ActionList = new LinkedHashMap<String, String>();
		
	
		static{
			ActionList.put(String.valueOf(ADD), ADD_STR);
			ActionList.put(String.valueOf(EDIT), EDIT_STR);
			//ActionList.put(String.valueOf(DELETE), DELETE_STR);
			ActionList.put(String.valueOf(LOCK), LOCK_STR);
			ActionList.put(String.valueOf(UNLOCK), UNLOCK_STR);
			/*ActionList.put(String.valueOf(REPAIR), REPAIR_STR);*/ //bugfix #292
			//ActionList.put(String.valueOf(RESET_AAPL), RESET_AAPL_STR);
			ActionList.put(String.valueOf(SUSPEND), SUSPEND_STR);
			ActionList.put(String.valueOf(UNSUSPEND), UNSUSPEND_STR);
			//ActionList.put(String.valueOf(FORCE_LOGOUT), FORCE_LOGOUT_STR);
			//ActionList.put(String.valueOf(RESET_IMSI), RESET_IMSI_STR);
		}
    }
    
    public static final class ApproverAction {
    	public static final int APPROVE=0;
        public static final int REJECT=1;
        public static final int REQUEST_REPAIR=2;
        public static final int RELEASE=3;
        
    	public static final String APPROVE_STR = "Approve";
    	public static final String REJECT_STR = "Reject";
    	public static final String REQUEST_REPAIR_STR = "Request Repair";
    	public static final String PENDING_APPROVAL_STR = "Pending Approval";
    	public static final String RELEASE_STR = "Release";
    	
    	public static final String[] ActionStr= {APPROVE_STR, REJECT_STR, REQUEST_REPAIR_STR, RELEASE_STR};
    }
    
    public static final class ApprovalData {
    	public static final String ACTION = "action";
    	public static final String OLD_OBJECT = "oldObject";
    	public static final String NEW_OBJECT = "newObject";
    }
    
    public static final class EformAction {
    	public static final int PREVIEW	= 0;
    	public static final int SAVE_DRAFT = 1;
    	public static final int SUBMIT_CONTINUE = 2;
    	public static final int SUBMIT_CLOSE = 3;
    	
    	public static final String PREVIEW_STR	= "Preview";
    	public static final String SAVE_DRAFT_STR = "Save Draft";
    	public static final String SUBMIT_CONTINUE_STR = "Submit Continue";
    	public static final String SUBMIT_CLOSE_STR = "Submit Close";
    	
    	public static final String[] ActionStr= {PREVIEW_STR, SAVE_DRAFT_STR, SUBMIT_CONTINUE_STR, SUBMIT_CLOSE_STR};
    }
    
    public static final class CallbackAction {
    	public static final int WRONG_NUMBER	= 0;
    	public static final int NO_ANSWER 		= 1;
    	public static final int FINISH			= 2;
    	public static final int CALLBACK		= 3;
    	
    	public static final String WRONG_NUMBER_STR	= "Nomor salah atau tidak terdaftar";
    	public static final String NO_ANSWER_STR = "Tidak diangkat";
    	public static final String FINISH_STR = "Selesai";
    	public static final String CALLBACK_STR = "Telepon kembali";
    	
    	public static final String[] ActionStr= {WRONG_NUMBER_STR, NO_ANSWER_STR, FINISH_STR, CALLBACK_STR};
    }
    
    public static final class Validation{
    	
    	public static final class Nullable{
    		public static final boolean TRUE = true;
    		public static final boolean FALSE = false;
    	}
    	
    	public static final class Type{
    		public static final int STRING = 1;
    		public static final int NUMBER = 2;
    		public static final int EMAIL = 3;
    		public static final int BOOLEAN = 4;
    		public static final int PASSWORD = 5;
    		public static final int PHONE = 6;
    		public static final int DATE = 7;
    		public static final int TIME = 8;
    		public static final int MONEY = 9;
    		public static final int PERCENTAGE = 10;
    	}
    }
    
    public static class UserLockStatus {
    	public static final int UNLOCKED=0;
    	public static final int LOCKED=1;
    	
    	public static final String UNLOCKED_STR = "No";
    	public static final String LOCKED_STR = "Yes";
    	
    	public static final boolean UNLOCKED_BOOELAN = false;
    	public static final boolean LOCKED_BOOELAN = true;
    	
    	public static final String[] LockStatusStr= {UNLOCKED_STR,LOCKED_STR};
    }
    
    public static final class RegexPattern {
    	public static final Pattern companyCodePattern = Pattern.compile("^[A-Z0-9].{4,29}");
        public static final Pattern phonePattern = Pattern.compile("^[0-9]+-[0-9]+-[0-9]+$");
        public static final Pattern filenamePattern = Pattern.compile("^[a-zA-Z0-9\\_ ]{1,238}");
    }
 
    public static final class BPMConstant {
    	
    	public static final String USERID = "bpmadmin"; //44, admin; 45, bpmadmin
    	public static final String AAPL = "bpmadmin"; //44, admin; 45, bpmadmin
    	
    	public static final class JoinType{
    		// 1-AND; 0-OR; 2-None
    		public static final int AND = 1;
    		public static final int OR = 0;
    		public static final int NONE = 2;
    	}
    	public static final class BPDType{
    		public static final int DISBURSEMENT = 1;
    		public static final int SETTLEMENT = 2;
    		public static final int INVOICE = 3;
    		public static final int PURCHASE_ORDER = 4;
    	}
    	public static final class BPMAction {
    		public static final String APPROVE = "approve";
    		public static final String REJECT = "reject";
    		public static final String REPAIR = "repair";
    		public static final String SUBMIT = "submit";
    	}
    	
    	public static final String TRX_TYPE_DEAL_INV = "DOCINV";
    	public static final String TRX_TYPE_DEAL_PO = "DOCPO";
    	public static final String TRX_TYPE_DISBURS = "DISBURS";
    	public static final String TRX_TYPE_CAN_DISB = "CANDISB";
    	public static final String TRX_TYPE_SETTLE = "SETTLE";
    	public static final String TRX_TYPE_EXTEND_TENOR = "EXTENOR";
    }
    
    public static class InvoiceDealStatus {
        public static final int UNKNOWN=-1;
        public static final int OPEN=0;
        public static final int DUE=1;
        public static final int OVERDUE=2;
        public static final int PAID=3;
        public static final int DELETED=4;
        public static final int UTILIZED=5;
        
        public static final String OPEN_STR="Open";
        public static final String DUE_STR="Due";
        public static final String OVERDUE_STR="Overdue";
        public static final String PAID_STR="Paid";
        public static final String DELETE_STR="Deleted";
        public static final String UTILIZED_STR="Utilized";
        
        public static final String[] STR = {OPEN_STR, DUE_STR, OVERDUE_STR, PAID_STR, DELETE_STR, UTILIZED_STR};
                
        public static final Map<String, String> LIST = new HashMap<String, String>();
		static{
			LIST.put(String.valueOf(OPEN), OPEN_STR);
			LIST.put(String.valueOf(DUE), DUE_STR);
			LIST.put(String.valueOf(OVERDUE), OVERDUE_STR);
			LIST.put(String.valueOf(PAID), PAID_STR);
			LIST.put(String.valueOf(UTILIZED), UTILIZED_STR);
		}	
		
		public static final Map<String, Integer> GET_KEY = new HashMap<String, Integer>();
		static{
			GET_KEY.put(OPEN_STR,OPEN);
			GET_KEY.put(DUE_STR,DUE);
			GET_KEY.put(OVERDUE_STR,OVERDUE);
			GET_KEY.put(PAID_STR,PAID);
			GET_KEY.put(UTILIZED_STR,UTILIZED);
		}	
    }
    
    public static class DisbursementProcessAction {
    	public static String CREATE = "CREATE";
    	public static String APPROVE = "APPROVE";
    	public static String RELEASE = "RELEASE";
    	public static String REJECT = "REJECT";
    	public static String REQUEST_REPAIR = "REQUEST_REPAIR";
    	public static String REPAIRING = "REPAIRING";
    }
    
    public static class InvoiceFinancingStatus {
      	public static final int NOT_AVAILABLE = 0;
        public static final int AVAILABLE = 1;
        public static final int PROCESSING = 2;
        public static final int PENDING = 3;
        public static final int FINANCED = 4;
        public static final int OVERDUE = 5;
        public static final int SETTLED = 6;
        
        public static final String NOT_AVAILABLE_STR = "Not Available";
        public static final String AVAILABLE_STR = "Available";
        public static final String PROCESSING_STR = "Processing";
        public static final String PENDING_STR = "Pending Future Date";
        public static final String FINANCED_STR = "Financed";
        public static final String OVERDUE_STR = "Overdue";
        public static final String SETTLED_STR = "Settled";
        
        public static final String[] STR = {NOT_AVAILABLE_STR, AVAILABLE_STR, PROCESSING_STR, PENDING_STR, FINANCED_STR, OVERDUE_STR, SETTLED_STR};
        
        public static final Map<String, String> LIST = new HashMap<String, String>();
		static{
			LIST.put(String.valueOf(NOT_AVAILABLE), NOT_AVAILABLE_STR);
			LIST.put(String.valueOf(AVAILABLE), AVAILABLE_STR);
			LIST.put(String.valueOf(PROCESSING), PROCESSING_STR);
			LIST.put(String.valueOf(PENDING), PENDING_STR);
			LIST.put(String.valueOf(FINANCED), FINANCED_STR);
			LIST.put(String.valueOf(OVERDUE), OVERDUE_STR);
			LIST.put(String.valueOf(SETTLED), SETTLED_STR);
		}	
		
		public static final Map<String, Integer> GET_KEY = new HashMap<String, Integer>();
		static{
			GET_KEY.put(NOT_AVAILABLE_STR,NOT_AVAILABLE);
			GET_KEY.put(AVAILABLE_STR, AVAILABLE);
			GET_KEY.put(PROCESSING_STR, PROCESSING);
			GET_KEY.put(PENDING_STR, PENDING);
			GET_KEY.put(FINANCED_STR, FINANCED);
			GET_KEY.put(OVERDUE_STR, OVERDUE);
			GET_KEY.put(SETTLED_STR, SETTLED);
		}	
    }
    
    public static final class PaymentCode {
    	public static final String MULTI = "M";
    	public static final String BULK_PAYMENT = "B";
    	public static final String DISBURSEMENT = "D";
    	public static final String SETTLEMENT = "S";
    	public static final String SUPPLY_CHAIN_BULK_DISBURSEMENT = "U";
    	
    	public static final String MULTI_DESC = "MULTI";
    	public static final String BULK_PAYMENT_DESC = "BULK PAYMENT";
    	public static final String DISBURSEMENT_DESC = "DISBURSEMENT";
    	public static final String SETTLEMENT_DESC = "SETTLEMENT";
    	public static final String SUPPLY_CHAIN_BULK_DISBURSEMENT_DESC = "BULK DISBURSEMENT";
    	
    	public static final Map<String, String> LIST = new HashMap<String, String>();
		static{
//			LIST.put(MULTI, MULTI_DESC);
//			LIST.put(BULK_PAYMENT, BULK_PAYMENT_DESC);
			LIST.put(DISBURSEMENT, DISBURSEMENT_DESC);
			LIST.put(SETTLEMENT, SETTLEMENT_DESC);
//			LIST.put(SUPPLY_CHAIN_BULK_DISBURSEMENT, SUPPLY_CHAIN_BULK_DISBURSEMENT_DESC);
		}
    }
    
    public static final class provisionCode {
    	public static final Map<Integer, String> LIST = new HashMap<Integer, String>();
		static{
			LIST.put(0, "");
			LIST.put(1, "1/8 Min 5");
			LIST.put(2, "1/16 Min 5");
			LIST.put(3, "1/16 Min 10");
		}   	
    }
    
    public static final class correspondenceType {
    	public static final Map<Integer, String> LIST = new HashMap<Integer, String>();
		static{
			LIST.put(0, "Fixed Amount");
			LIST.put(1, "Percentage");
		}   	
    }
    
    public static final String CRON_EXP_FLAG = "CRON";
    public static final String DATE_FORMAT_FLAG = "DATE_FORMAT";
    public static final String STRUTS2_DATE_FORMAT_FLAG = "STRUTS2_DATE_FORMAT";
    public static final String JQUERY_DATE_FORMAT_FLAG = "JQUERY_DATE_FORMAT";
    public static final String JQUERY_DATE_NO_TIME_FORMAT_FLAG = "JQUERY_DATE_FORMAT_NO_TIME";
    public static final String JQGRID_DATE_FORMAT_FLAG = "JQGRID_DATE_FORMAT";
    public static final String JQGRID_DATE_NO_TIME_FORMAT_FLAG = "JQGRID_DATE_FORMAT_NO_TIME";
    public static final String DATE_NO_TIME_FORMAT_FLAG = "DATE_FORMAT_NO_TIME";
    public static final String STRUTS2_DATE_FORMAT_NO_TIME_FLAG = "STRUTS2_DATE_FORMAT_NO_TIME";
    
    public static final class RCAS {
    	public static final String ADMIN_ID 				= "RSCF";
    	public static final String ADMIN_PWD 				= "RSCFONL";
    	public static final String COMPANY_ID_BANK 			= "MAYBANK"; //IDBANKBII, MAYBANK
    	public static final String PROVIDER 				= "MY"; //ID, MY
    	public static final String COUNTRY_CODE 			= "MY"; //ID, MY 
    	public static final String PIN_LENGTH_DEFAULT 		= "8";
    	public static final String APPLICATION_ID 			= "104";
    	public static final String NONCE		 			= "1234567890";
    	
    	// UAT
    	public static final String PUBLIC_MOD				= "EA0E946A73B9398C5582D15DD47B3E46706A1C750771285349732AC597CB87C1ADE3C16B2EC2D92DB51F377FF424ACF331D755B817F5D22D06D1AC461DCE6CDDB7E6D2D7CAC315228DE08A8EA5EA873A5502BFC3DD5C8215D9E878239F0E3707E542D4966EA70E8C6C79C01B79554E291A90091371FBAC5CE90B82846230E8F1"; //MY
		public static final String PUBLIC_EXPONENT			= "010001"; //MY
		
		// PROD
    	//public static final String PUBLIC_MOD				= "A52DE265449D4338A3598707EC81B6914FB56FD3C271C167680153E1AD6644549DA07DF9190238EDE43AE52BAA10702978BE9A037CEE8CA6DC16E25AAAF6F1CCED8F201F2E059AFBB7ED178F1AEB63B348F598169632C1276051368460C944CF26050F6941617D1DC35294F74EEED07F80DCCAF6F8DD2FB363CBF839C251EFE9"; //MY
//		public static final String PUBLIC_EXPONENT			= "010001"; //MY
		
		public static final String OTP_TYPE		 			= "OTP1";
		
		public static final int KEY_SET 					= 2; //ID = 1, MY=2
		public static final int LMK_ID	 					= 5; //ID = 0, MY=5
		//public static final String PUBLIC_MOD				= "90C0860CF97C460F024793C97733CD57AF913251FBE8B73FF270A1D84537C7869C65F41685C76772111BB374D8297AD052E46934E62BD6071938D609D63C98BED5B508EEA61DCE6036CAA27288D619219012B92EE00DF9F96B6CB1327BBF3C8F3C7AA241F59445F9991EED30EB4DFC03F81CB70B97AE6614398DD75E11165291"; //ID
		//public static final String PUBLIC_EXPONENT			= "010001"; //ID
		
		public static final String AUTH_MODE_TOKEN			= "T"; //S dan T
		public static final String AUTH_MODE_STATIC			= "S";
    	public static final String AUTO_SEND_INDI 			= "R"; // REAL-TIME
    	public static final String AUTO_SEND_INDI_PIN_MAILER= "P"; // PIN MAILER
		public static final String INTERNAL_USER_INDI 		= "I";
		public static final String EXTERNAL_USER_INDI 		= "E";
		
    }
    
    public static class SettlementProcessAction {
    	public static String CREATE = "CREATE";
    	public static String APPROVE = "APPROVE";
    	public static String RELEASE = "RELEASE";
    	public static String REJECT = "REJECT";
    	public static String VIEW_SETTLEMENT = "VIEW SETTLEMENT";
    	// tanggal 21 january 2014, yuna nyuruh underscorenya diilangin 
    	public static String REQUEST_REPAIR = "REQUEST REPAIR";
    	public static String REPAIRING = "REPAIRING";
    	public static String EXTEND_TENOR = "EXTEND TENOR";
    }
    
    public static class MailConstant {
    	public static String TEMPLATE_KEY = "templatekey";
    	public static String MODEL = "model";
    	public static String SUBJECT = "subject";
    	public static String USER_IDS = "userIds";
    	public static String TO_ADDRESS = "toAddress";
    	public static String NEXT_USER = "nextUser";
    }
        
    public static String PARAMETER_NAME_QUARTZ_TASK_MONITORING_CRON_EXPRESSION	= "QUARTZ_TASK_MONITORING_CRON_EXPRESSION";
    public static String PARAMETER_NAME_CRON_LOAN_STATUS_EXPRESSION 			= "CRON_LOAN_STATUS_EXPRESSION";
    public static String PARAMETER_NAME_CRON_INVOICE_STATUS_EXPRESSION 			= "CRON_INVOICE_STATUS_EXPRESSION";

    public static final String BANK_USER = "BANK USER";
    
    public static final int SUPERPOWER = 1;
    public static final int NOPOWER = 0;

	public static class AutoSendInticator {
    	public static String INSTANT_EMAIL = "R";
    	public static String INSTANT_SMS = "S";
    	public static String BATCH_EMAIL = "B";
    	public static String BATCH_SMS = "T";
    	public static String BATCH_PIN_MAILER = "P";
    	
    	public static String INSTANT_EMAIL_DESC = "Instant Email";
    	public static String INSTANT_SMS_DESC = "Instant SMS";
    	public static String BATCH_EMAIL_DESC = "Batch Email";
    	public static String BATCH_SMS_DESC = "Batch SMS";
    	public static String BATCH_PIN_MAILER_DESC = "Batch PIN Mailer";
    	
    	public static final Map<String, String> LIST = new HashMap<String, String>();
		static{
			LIST.put(String.valueOf(INSTANT_EMAIL), INSTANT_EMAIL_DESC);
			LIST.put(String.valueOf(INSTANT_SMS), INSTANT_SMS_DESC);
			LIST.put(String.valueOf(BATCH_EMAIL), BATCH_EMAIL_DESC);
			LIST.put(String.valueOf(BATCH_SMS), BATCH_SMS_DESC);
			LIST.put(String.valueOf(BATCH_PIN_MAILER), BATCH_PIN_MAILER_DESC);
		}
    }
    
    public static class ResetPassNotifMode {
    	public static String INSTANT_EMAIL = "R";
    	public static String INSTANT_SMS = "S";
    	public static String BATCH_EMAIL = "B";
    	public static String BATCH_SMS = "T";
    	public static String BATCH_PIN_MAILER = "P";
    	
    	public static String INSTANT_EMAIL_DESC = "Instant Email";
    	public static String INSTANT_SMS_DESC = "Instant SMS";
    	public static String BATCH_EMAIL_DESC = "Batch Email";
    	public static String BATCH_SMS_DESC = "Batch SMS";
    	public static String BATCH_PIN_MAILER_DESC = "Batch PIN Mailer";
    	
    	public static final Map<String, String> LIST = new HashMap<String, String>();
		static{
			LIST.put(String.valueOf(INSTANT_EMAIL), INSTANT_EMAIL_DESC);
			LIST.put(String.valueOf(INSTANT_SMS), INSTANT_SMS_DESC);
			LIST.put(String.valueOf(BATCH_EMAIL), BATCH_EMAIL_DESC);
			LIST.put(String.valueOf(BATCH_SMS), BATCH_SMS_DESC);
			LIST.put(String.valueOf(BATCH_PIN_MAILER), BATCH_PIN_MAILER_DESC);
		}
    }

	public static final class TaskCode {
    	public static final String SETTLEMENT = "S";
    	public static final String EXTEND_TENOR = "E";
    	public static final String DISBURSEMENT = "D";
    }
    
    public static String COMMA_DELIMITER = ",";
    public static String SPACE_DELIMITER = " ";
    public static String PIPE_DELIMITER = "|";
	public static final String OLD_STR = "(OLD)";
    public static final String NEW_STR = "(NEW)";
    public static final String DELIMITER_DASH = "-";
    public static final String DELIMITER_COLON = ":";
    public static final String DELIMITER_DOT = ".";
    
    public static class NotificationConstant {
    	public static String DATA = "data";
    	public static String SUBJECT = "subject";
    	public static String REF_NO = "refNo";
    	public static String EMAIL_CONTENT = "emailContent";
    	public static String DISBURSEMENT_DATE = "disbursementDate";
    	public static String BANK_MATURITY_DATE = "bankMaturityDate";
    	public static String USE_TEMPLATE_CODE = "useTemplateCode";
    	public static String DOC_RECIPIENTS = "docRecipients";
    	public static String DIS_RECIPIENTS = "disRecipients";
    	public static String SET_RECIPIENTS = "setRecipients";
    	public static String ETC_RECIPIENTS = "etcRecipients";
    	public static Integer TYPE_ONE_TIME = 1;
    	public static Integer TYPE_DUE = 2;
    	public static Integer TRX_TYPE_DOC = 1;
    	public static Integer TRX_TYPE_DIS = 2;
    	public static Integer TRX_TYPE_SET = 3;
    }
    
    public static class NotificationTemplateConstant {
    	public static String DOC01 = "DOC01";
    	public static String DOC02 = "DOC02";
    	public static String DOC03 = "DOC03";
    	public static String DOC04 = "DOC04";
    	public static String DOC05 = "DOC05";
    	public static String DOC06 = "DOC06";
    	
    	public static String DISB01 = "DISB01";
    	public static String DISB02 = "DISB02";
    	public static String DISB03 = "DISB03";
    	public static String DISB04 = "DISB04";
    	public static String DISB05 = "DISB05";
    	public static String DISB06 = "DISB06";
    	public static String DISB07 = "DISB07";
    	public static String DISB08 = "DISB08";
    	public static String DISB09 = "DISB09";
    	
    	public static String SET01 = "SET01";
    	public static String SET02 = "SET02";
    	public static String SET03 = "SET03";
    	public static String SET04 = "SET04";
    	public static String SET05 = "SET05";
    	public static String SET06 = "SET06";
    	public static String SET07 = "SET07";

    }
    
    public static class WS_CONSTANSTS {
    	public static final String PK_FINANCING_SCHEME = "pkFinancingScheme";
    	public static final String PK_COMMUNITY = "pkCommunity";
    	public static final String PK_COMPANY = "pkCompany";
    	public static final String PK_DISBURSEMENT = "pkDisbursement";
    	public static final String PK_LOAN = "pkLoan";
    	
    	public static final String JOB_ID = "jobId";
    	public static final String STATUS = "status";
    	public static final String CODE = "code";
    	public static final String NAME = "name";
    	public static final String LIMIT = "limit";
    	public static final String DOC_NO = "docNo";
    	public static final String STATUS2 = "status2";
    	public static final String TYPE = "type";
    	
    	public static final String RESULT = "result";
    	
    	// return message
    	public static final Integer SUCCESS = 1;
    	public static final Integer DATA_NOT_FOUND = 0;
    	public static final Integer FAILED = -1;
    	
   }
    
    public static class PredefinedType{
    	public static final int IN_HOUSE = 1;
    	public static final int DOMESTIC = 2;
    	public static final int REMITTANCE = 3;
    }
    
    public static class ApproverCode{
    	public static final String SELLER 			= "S";
    	public static final String BUYER 			= "B";
    	public static final String SELLER_OR_BUYER 	= "O";
    	public static final String SELLER_AND_BUYER = "N";
    	public static final String AUTO_APPROVE 	= "A";
    }
    
    public static final class PrintReport {
    	public static final int PDF=0;
        public static final int CSV=1;
        public static final int PRINT=2;
        
    	public static final String PDF_STR = "Approve";
    	public static final String CSV_STR = "Reject";
    	public static final String PRINT_STR = "Request Repair";
    }
    
    /*public static final class LdapAttribute {
    	public static final String USER_PRINCIPAL_NAME = "userPrincipalName";
    	public static final String CN = "cn";
    	public static final String DISPLAY_NAME = "displayName";
    	public static final String GIVEN_NAME = "givenName";
    	public static final String NAME = "name";
    	public static final String EMAIL = "userPrincipalName";
    	public static final String OBJECT_CLASS = "objectClass";
    	public static final String VALUE_OBJECT_CLASS_USER = "user";
    }*/
    
    public static class PhoneNumberPrefix{
		public static final Character[] PREFIX		= {'0'};
		public static final String[] BTPN_PREFIX	= {"+62"};
		
		public static Map<Character, String> mobileNumberMap = new HashMap<Character, String>();
		static{
			for(int i = 0; i < PREFIX.length; i++){
				mobileNumberMap.put(PREFIX[i], BTPN_PREFIX[i]);
			}
		}
	}    
    
    public static class RestAction{
    	public static final Integer REGISTER_CUSTOMER 		= 1;
    	public static final Integer UPDATE_CUSTOMER 		= 2;
    	public static final Integer SEND_CHUNK 				= 3;
    	public static final Integer REGISTER_CUSTOMER_SDB 	= 4;
    }
    
    public static class RestResponseCode{
    	public static final String SUCCESS 			= "00";
    	public static final String EXCEPTION 		= "01";
    	public static final String FAILED 			= "02";
    	public static final String CUSTOMER_EXIST 	= "03";
    }
    
    public static class RandomStuff{
    	public static final int START_RANDOM = 100000;
    	public static final long END_RANDOM = 999999;
    	public static final int RANDOM_LENGTH = 4;
    }
    
    public static class btpnYesNo{
    	public static final Integer[] MOBILE_VALUE		= {1,2};
		public static final String[] BTPN_YES_NO	= {"Y","N"};
		
		public static Map<Integer, String> convertMap = new HashMap<Integer, String>();
		static{
			for(int i = 0; i < MOBILE_VALUE.length; i++){
				convertMap.put(MOBILE_VALUE[i], BTPN_YES_NO[i]);
			}
		}
		
		public static Map<String, Integer> reverseConvertMap = new HashMap<String, Integer>();
		static{
			for(int i = 0; i < MOBILE_VALUE.length; i++){
				reverseConvertMap.put(BTPN_YES_NO[i], MOBILE_VALUE[i]);
			}
		}
    }
    
    public static String TAHUN = "TAHUN";
    public static String BULAN = "BULAN";
    public static int MONTH_COUNTER = 12;
    public static int BTPN_MONTH_ROUNDER = 5; // Bu Mey, 27 May 2015
    
    public static String NO_PIC_LOCATION = "/img/nopicture.png";
    public static String CUSTOMER_JRXML_LOCATION 	= "/apps/eform/previewPdf/preview.jrxml";
    public static String CUSTOMER_PUR_JRXML_LOCATION = "/apps/eform/previewPdf/preview-pur.jrxml";
    public static String CUSTOMER2_JRXML_LOCATION = "/apps/eform/previewPdf/preview2.jrxml";
    public static String CUSTOMER_SDB_JRXML_LOCATION = "/apps/eform/previewPdf/previewSDB.jrxml";
    public static String EMAIL_JRXML_LOCATION = "/apps/eform/email/pdf/";
    public static String EMAIL_SINAYA 		= "emailSinaya.jrxml";
    public static String EMAIL_PUR 			= "emailPur.jrxml";
    
    // keyword used by reademailjob in detecting the mail to consume
    // has to be different per lob due to limited development environment
    // where job in sinaya' sit env consume the mail belongs to purna' sit
    public static String EMAIL_REPLY_KEYWORD_SINAYA = "[RE]";
    public static String EMAIL_REPLY_KEYWORD_PURNA = "[RE ]"; 
   
    public static String EQ_TAX_CODE_01 = "T2";
    public static String EQ_TAX_CODE_02 = "T0";
    public static String EQ_GOLONGAN_NASABAH = "9000";
    
    public static String  ACCOUNT_TYPE_INDIVIDUAL				= "INDIVIDUAL";
    public static String  ACCOUNT_TYPE_JOIN_AND					= "JOIN AND";
    public static String  ACCOUNT_TYPE_JOIN_OR					= "JOIN OR";
    public static String  ACCOUNT_TYPE_JOIN_QQ					= "JOIN QQ";
    public static String  ACCOUNT_TYPE_JOIN_BENEFICIARY_OWNER	= "JOIN BENEFICIARY OWNER";
    
    public static Integer DHIB_FLAG_FIRST_APPLICANT 		= 1;
    public static Integer DHIB_FLAG_SECOND_APPLICANT 		= 2;
    
    /**
	 * [13 January 2016]
	 * Constant Below use to configuration time out connection and request client jax - ws.
	 */
	public static final String BINDING_PROPERTY_CONNECTION_TIMEOUT 	= "com.sun.xml.ws.connect.timeout";
	public static final String BINDING_PROPERTY_REQUEST_TIMEOUT 	= "com.sun.xml.ws.request.timeout";
	
	public static final String STAND_ALONE_BINDING_PROPERTY_CONNECTION_TIMEOUT 	= "com.sun.xml.internal.ws.connect.timeout";
	public static final String STAND_ALONE_BINDING_PROPERTY_REQUEST_TIMEOUT 	= "com.sun.xml.internal.ws.request.timeout";
    
    /*BELOW CONSTANTS FOR BTPN SINAYA*/	
	public static class BTPN{
		
		public static String CURRENCY 						= "IDR";
		public static String PERKIRAAN_DEBIT_TRX_PER_TAHUN 	= "0";
		public static String PERKIRAAN_KREDIT_TRX_PER_TAHUN = "0";
		public static String ZERO_CODE						= "0";
		public static int INDEX_PEMOHON						= 0;
		
		public static class common{			
			public static final String BTPN_CHANNEL_ID 		= "EFORM_SINAYA";			
			public static final String BTPN_CHANNEL_TYPE 	= "EFORM_SINAYA";			
			public static final String BTPN_NODE 			= "EFORM_SINAYA";
			public static final String BTPN_EQ_CHANNEL_TYPE	= "EFO";
			public static final String BTPN_SMS_TERMINAL_NAME = "BTPN OTP";
			
			//SDB
			public static final String BTPN_SDB_CHANNEL_ID 		= "EFORM_SDB";	
			public static final String BTPN_SDB_CHANNEL_TYPE 	= "EFORM_SDB";	
			public static final String BTPN_SDB_NODE 			= "EFORM_SDB";
			public static final String BTPN_NOTIFICATION_AC_ID	= "213";
			
			//PURNABAKTI
			public static final String BTPN_PURNA_BAKTI_CHANNEL_ID 		= "EFORM_PURNA_BAKTI";	
			public static final String BTPN_PURNA_BAKTI_CHANNEL_TYPE 	= "EFORM_PURNA_BAKTI";	
			public static final String BTPN_PURNA_BAKTI_NODE 			= "EFORM_PURNA_BAKTI";
		}
		
		public static class createSemiActive{
			public static final String PROCESSING_CODE_SINGLE						= "100000";
			public static final String PROCESSING_CODE_SINGLE_CIF					= "110000";
			public static final String PROCESSING_CODE_JOIN							= "120000";
			public static final String PROCESSING_CODE_JOIN_CIF1_CIFJOIN			= "130000"; //Pemohon New ditaruh di row 1, pemohon Ex di row 2 hanya field kode relasi
			public static final String PROCESSING_CODE_JOIN_CIF1_CIF2_CIFJOIN 		= "140000";
			public static final String PROCESSING_CODE_JOIN_CIFJOIN					= "150000";
			public static final String PROCESSING_CODE_JOIN_QQ_CIFPARENT_CIFJOIN	= "200000";
			public static final String PROCESSING_CODE_JOIN_QQ_CIFJOIN 				= "210000";
			public static final String PROCESSING_CODE_CIF_JOIN_BO					= "220000";
		}
		
		public static class getDataByAccountNo{
			public static final String PROCESSING_CODE_SINGLE	= "100000";
		}
		
		public static class createFullyActive{
			public static final String PROCESSING_CODE 					= "160000"; // Untuk update Data: 930000
			public static final String PROCESSING_CODE_CIF1 			= "170000";
			public static final String PROCESSING_CODE_CIF1_CIF2		= "180000";
		}
		
		public static class pushNotification{
			public static final String APPLICATION_ID 		= "wow.app.eform";
		}
		
		public static class notification{
			public static final String PCODE_SMS_EMAIL_SEMI_ACTIVE		=	"2001";
			public static final String PCODE_SMS_EMAIL_FULLY_ACTIVE		=	"2002";
			public static final String PCODE_SMS_EMAIL_OTHERS			=	"2000";
		}
		
		public static class holiday{
			public static final String DATE_FOMAT		= "yyyy-MM-dd";
			public static final String HOLIDAY_STATUS	= "H";
		}
		
		public static class contentType{
			public static final String KTP 			= "KTP";
			public static final String NPWP 		= "TAX ID";
			public static final String PHOTO 		= "SELF-PHOTO";
			public static final String SIGNATURE 	= "SPECIMEN SIGNATURE";
			
			public static Map<ImageType, String> contentTypeMap = new HashMap<ImageType, String>();
			static{
				//KTP
				contentTypeMap.put(ImageType.KTP, KTP);
				contentTypeMap.put(ImageType.KTP2, KTP);
				
				//NPWP
				contentTypeMap.put(ImageType.NPWP, NPWP);
				contentTypeMap.put(ImageType.NPWP2, NPWP);
				
				//Photo
				contentTypeMap.put(ImageType.APPLICANT, PHOTO);
				contentTypeMap.put(ImageType.APPLICANT2 , PHOTO);
				
				//Signature
				contentTypeMap.put(ImageType.SIGNATURE, SIGNATURE);
				contentTypeMap.put(ImageType.SIGNATURE2 , SIGNATURE);
			}
		}
		
		public static class updateMetaData{
			public static final String columnName 	 = "IDCardNumber";
			public static final String columnDataType = "string";
		}
		
		public static String orderByDataType = "string";
		

		public static String CUSTOMER_TYPE_SDB = "04";
		
		public static String FLAG_PEMBUKAAN_SDB = "CS";
		
		public static String REPLACE_STRING_COMMENT_IF_EMPTY = "Empty";
		
		public static class sdbNotificationType{
			public static final int EMAIL 		= 1;
			public static final int SMS 		= 2;
			public static final int EMAIL_SMS 	= 3;
		}
		
		/**
		 * WO - 012 (CR 12 Sinaya)Life Time
		 */
		public static String FORMAT_LIFE_TIME_PARAMETER = "yyyyMMdd";
		
		public static int LIFE_TIME_DATE 	= 31;
		public static int LIFE_TIME_MONTH 	= Calendar.DECEMBER;
		public static int LIFE_TIME_YEAR 	= 2899;
	}
    
	public static class selectOptionOrText{
		public static final boolean IS_SELECT_OPTION	= true;
		public static final boolean IS_FREE_TEXT		= false;
		
		public static final String IDENTITY_PROVINCE_FIELD 			= "IdentityProvince";
		public static final String IDENTITY_CITY_FIELD 	 			= "IdentityCity";
		public static final String HOUSE_PROVINCE_FIELD 	 		= "HouseProvince";
		public static final String HOUSE_CITY_FIELD 	 			= "HouseCity";
		public static final String COMPANY_PROVINCE_FIELD 	 		= "CompanyProvince";
		public static final String COMPANY_CITY_FIELD 	 			= "CompanyCity";
		public static final String CORRESPONDENT_PROVINCE_FIELD 	= "CorrespondentProvince";
		public static final String CORRESPONDENT_CITY_FIELD 	 	= "CorrespondentCity";
	}
	
	
	public static class SemiActiveRemarkStatus{
		public static final Integer SUCCEED = 1;
		public static final Integer FAILED = 0;
		
		public static final String SUCCEED_STR = "Succeed";
		public static final String FAILED_STR = "Failed - Exception Handling";
		
		public static Map<Integer, String> remarkStatusMap = new HashMap<Integer, String>();
		static{
			remarkStatusMap.put(SUCCEED, SUCCEED_STR);
			remarkStatusMap.put(FAILED, FAILED_STR);
		}
	}
	
	/**
	 * [10 May 2016] - Flag New CIF
	 */
	
	public static final boolean IS_CIF_EXISITING = false;
	public static final boolean IS_CIF_NEW 		 = true;
	
	public static final String FORCE_FULLY_ACTION_MESSAGE = "Force Fully Active -";
	public static final String FORCE_APPROVE_CMS_ACTION_MESSAGE = "Force Approve CMS -";
	
	public static final String ADDITIONAL_INFO_SPLITTER = "|";
	
	// 8 (BACKSPACE), 9 (TAB), 35 (END), 36 (HOME), 37 (LEFT), 38 (UP), 39 (RIGHT), 40 (DOWN), 46 (DELETE) 
	public static final String KEYBOARD_NAVIGATION_KEY = "8|9|35|36|37|38|39|40|46";
	
	//workflow version
	public static final long WORKFLOW_VERSION_2 = 2;
	
	public static class EmailStatus{
		public static final Integer QUEUE 	 = 0;
		public static final Integer SENT  	 = 1;
		public static final Integer COMPLETE  = 2;
		
		
		public static final String QUEUE_STR = "In Queue";
		public static final String SENT_STR  = "Sent";
		public static final String COMPLETE_STR  = "Complete";
	}
	
	public static class MobileLoanActionCode {
		public static final String BARU = "BARU";
		public static final String TB = "TB"; //TIDAK_BERMINAT
		public static final String BB = "BB"; //Belum Berminat
		public static final String TERKIRIM = "TERKIRIM";
		public static final String MASALAH = "MASALAH";
		public static final String DISETUJUI = "DISETUJUI";
		public static final String DITOLAK = "DITOLAK";
		
		public static final String GEXL = "GEXL"; //0A Get Existing Loan 
		public static final String GEXLD = "GEXLD"; //0B Get Existing Loan Detail
		
		public static final String SIMUL = "SIMUL"; //01 Get Simulation With BIChecking
		public static final String EXIST = "EXIST"; //02 Get Existing Loan
		public static final String PCONFIRM = "PCONFIRM"; //03 PostConfirm
		public static final String CALCDEV = "CALCDEV"; //04 CalculateDeviation
		public static final String KALUL = "KALUL"; //04B KalkulasiUlang
		public static final String PICKANG = "PICKANG"; //05 PilihAngsuran
		public static final String CALCANG = "CALCANG"; //06 KalkulasiAngsuran
		public static final String GCHECK = "GCHECK"; //09A Get Checking
		public static final String PANG = "PANG"; //09 PostInterview
		
		public static final String SUBMIT = "SUBMIT"; //07 SubmitDataEntry 
		public static final String SUBMITREP = "SUBMITREP"; //12 SubmitDataRepair
		public static final String SUBMITREPIMG = "SUBMITREPIMG"; //13 SubmitImageRepair
		
		public static final String RRITV = "RRITV"; //Request Repair Interview dari Mobile
	}

	public static final String BSM = "BSM";
	public static final String GROUP = "ASL(Group)";
	public static final String MINUTE = "minutes";
	public static final String SECOND = "seconds";
	
	public static final String ASL = "ASL";
	
	public static String ERROR_RESPONSE_EDD = "'GAGAL', karena sistem sedang mengalami gangguan. Silahkan dicoba kembali.";
	public static String ERROR_RESPONSE_EDD_EXIST = "'GAGAL', karena nasabah ini telah berhasil melewati proses ini.";
	public static String SUCCESS_RESPONSE_EDD = "'BERHASIL'.";
	public static String ERROR_RESPONSE_EDD_FAILED_SEMI_ACTIVE = "'GAGAL SEMI ACTIVE'.";
	
	public static class EmailTBo{
		public static final Integer AGE_OF_DAY_TO_REMIND_TBO_FOR_OFFICER_AND_1_LEVEL_UP 	 = 14;
		public static final Integer AGE_OF_DAY_TO_REMIND_TBO_FOR_2_LEVEL_UP 	 			 = 30;
	}
	public static class AppId{
		public static final Integer E_SAVING = 1;
		public static final Integer E_LOAN = 2;
	}
	
	public static class CardPersoStatus{
		public static final int READY_TO_PERSO 	= 0;
		public static final int READY_TO_VISIT 	= 1;
		public static final int ACTIVATED 		= 2;
		public static final int CANCELED 		= 3;
		
		public static final String READY_TO_PERSO_STR = "READY_TO_PERSO";
		public static final String READY_TO_VISIT_STR = "READY_TO_VISIT";
		public static final String ACTIVATED_STR = "ACTIVATED";
		public static final String CANCELED_STR = "CANCELED";
		
		public static final Map<Integer, String> cardPersoStatusList = new HashMap<Integer, String>();
		static{
			cardPersoStatusList.put(READY_TO_PERSO, READY_TO_PERSO_STR);
			cardPersoStatusList.put(READY_TO_VISIT, READY_TO_VISIT_STR);
			cardPersoStatusList.put(ACTIVATED, ACTIVATED_STR);
			cardPersoStatusList.put(CANCELED, CANCELED_STR);
		}
		
		public static final String[] CARD_PERSO_STATUS = {READY_TO_PERSO_STR, READY_TO_VISIT_STR, ACTIVATED_STR, CANCELED_STR};
	}
	
	public static final String PUR_DIVISION = "PUR";
	public static final String AE_DIVISION = "AE";
//	public static final List<String> divisionList =  Arrays.asList(PUR_DIVISION, AE_DIVISION);
	
	public static String SUCCESS = "SUCCESS";
	
	public static List<ModuleType> UWMP_LOOKUP_GROUPS = new ArrayList<>(Arrays.asList(ModuleType.FAQ, ModuleType.LOR_KANTOR_BAYAR/*,ModuleType.LOR_MITRA*/,ModuleType.SYSTEM_PARAMETER, ModuleType.POP));

	public static class RestApiDefinition {
		public static final String BASE_PATH_BTPN_CHECKING = "/rest/btpnchecking";
		public static final String BASE_PATH_PUR_ELOAN = "/ploan/rest";
		public static final String BASE_PATH_PUR_ELOAN_MAIN_LOAN = "/ploan/rest/mainloan";
		public static final String BASE_PATH_PUR_ELOAN_MAIN_SAVING = "/ploan/rest/mainSaving";
		public static final String BASE_PATH_SINAYA = "/rest/sinaya";
		public static final String BASE_PATH_STUB_PUR = "/stub/pur";
		public static final String BASE_PATH_MDW = "/mdw";
		public static final String BASE_PATH_CMS = "/cmsekarip";
		public static final String BASE_PATH_UWMP = "/uwmp/rest";
		public static final String BASE_PATH_BTPN_LORINA = "/rest/lorina";
		public static final String BASE_PATH_BTPN_ECM = "/rest/ecm2";
		
		public static class BtpnCheckingOperation {
			public static final String btpnChecking = "/com.btpn.checking.wrapper.restservice.btpnChecking";
		}
		
		public static class SinayaOperation{
			public static final String getListOfHIB = "/com.btpn.sinaya.wrapper.restservice.getListOfHIB";
			public static final String getDataByAcctNoFromEq = "/com.btpn.sinaya.wrapper.restservice.getDataByAcctNoFromEq";
		}
		
		public static class RestPurOperation{
			public static final String cardActivation = "/com.btpn.mobile.purnabakti.wrapper.restservice.card.cardActivationPurnabakti";
			public static final String reversalCardActivation = "/com.btpn.mobile.purnabakti.wrapper.restservice.card.reversalCardActivationPurnabakti";
			public static final String cardLinking = "/com.btpn.mobile.purnabakti.wrapper.restservice.card.cardLinkingPurnabakti";
			public static final String reversalCardLinking = "/com.btpn.mobile.purnabakti.wrapper.restservice.card.reversalCardLinkingPurnabakti";
			public static final String getCifList = "/com.btpn.mobile.purnabakti.wrapper.restservice.customer.getCIFList";
			public static final String createAccount = "/com.btpn.mobile.purnabakti.wrapper.restservice.account.createAccount";
		}
		
		public static class CMSOperation{
			public static final String findEnrolmentByNopenAndNorek = "/enrollment/findAllByNopenAndNorek";
			public static final String postEnrollment = "/enrollment";
		}
		
		public static class PurEloanMainLoanOperation {
			public static final String getLoanDetail = "/GetLoanDetail";
			public static final String loan = "/loan";
			public static final String loanApprovalStatus = "/loanApprovalStatus";
			public static final String taskActivity = "/taskActivity";
			public static final String GetSimulationWithBIChecking = "/GetSimulationWithBIChecking";
			public static final String masterDataSync = null;
			public static final String CheckLoanTaskExist = "/CheckLoanTaskExist";
			public static final String createLoanTaskSameDay = "/createLoanTaskSameDay";
			public static final String GSubmitDataEntry = "/GSubmitDataEntry";
			public static final String submitDataInterview = "/SubmitDataInterview";
		}
		public static class UWMPPurOperation {
			public static final String userSycn = "/User/sync";
			public static final String userProfile = "/User/profile";
			public static final String userLogin = "/User/login";
		}
		
		public static class SchemaBTPNLorinaOperation {
			public static final String getExistingLoan = "/GetExistingLoan";
			public static final String getExistingLoanDetail = "/GetExistingLoanDetail";
			public static final String postConfirm = "/PostConfirm";
			public static final String calculationDeviation = "/CalculateDeviation";
			public static final String kalkulasiAngsuran 	= "/KalkulasiAngsuran";
			public static final String pilihAngsuran = "/PilihAngsuran";
			public static final String kalkulasiUlang = "/KalkulasiUlang";
			public static final String getChecking = "/GetChecking";
			public static final String submitDataInterview = "/SubmitDataInterview";
			public static final String getSimulationWithBIChecking="/GetSimulationWithBIChecking";
		}
		
		public static class PurEloanOperation {
			public static final String masterDataSync = "/data/sync";
		}
		
		public static class PurEloanMainSavingOperation {
			public static final String saving = "/saving";
			public static final String taskActivity = "/taskActivity";
			public static final String savingRejection = "/savingRejection";
			public static final String cardPerso ="/cardPerso";
			public static final String cardActivation ="/cardActivation";
			public static final String cardCancellation ="/cardCancellation";
			public static final String deviation ="/deviation";
			public static final String deviationApprove ="/deviationApprove";
			public static final String deviationRejection ="/deviationRejection";
			public static final String updateTaskCIF ="/updateTaskCIF";
			
			public static final String sdReject ="/sdReject";
		}
		
		public static class ECMOperation {
			public static final String uploadDocument = "/com.btpn.ecm.wrapper.restservice.maintainFile.uploadDocument";
			public static final String downloadDocument = "/com.btpn.ecm.wrapper.restservice.maintainFile.downloadDocument";
			public static final String getListDocument = "/com.btpn.ecm.wrapper.restservice.maintainFile.getListDocument";
		}
		
		public static final Map<String, String> UWMPPurRelativeUrlMap = new HashMap<String, String>();
		static {
			UWMPPurRelativeUrlMap.put(UWMPPurOperation.userSycn, BASE_PATH_UWMP + UWMPPurOperation.userSycn);
			UWMPPurRelativeUrlMap.put(UWMPPurOperation.userProfile, BASE_PATH_UWMP + UWMPPurOperation.userProfile);
			UWMPPurRelativeUrlMap.put(UWMPPurOperation.userLogin, BASE_PATH_UWMP.concat(UWMPPurOperation.userLogin));
		}
		
		public static final Map<String, String> btpnCheckingRelativeUrlMap = new HashMap<String, String>();
		static{
			btpnCheckingRelativeUrlMap.put(BtpnCheckingOperation.btpnChecking, BASE_PATH_BTPN_CHECKING+BtpnCheckingOperation.btpnChecking);
		}
		
		public static final Map<String, String> sinayaRelativeUrlMap = new HashMap<String, String>();
		static{
			sinayaRelativeUrlMap.put(SinayaOperation.getListOfHIB, BASE_PATH_SINAYA+SinayaOperation.getListOfHIB);
			sinayaRelativeUrlMap.put(SinayaOperation.getDataByAcctNoFromEq, BASE_PATH_SINAYA+SinayaOperation.getDataByAcctNoFromEq);
		}
		
		public static final Map<String, String> restPurRelativeUrlMap = new HashMap<String, String>();
		static{
			restPurRelativeUrlMap.put(RestPurOperation.cardActivation, BASE_PATH_MDW+RestPurOperation.cardActivation);
			restPurRelativeUrlMap.put(RestPurOperation.reversalCardActivation,
					BASE_PATH_MDW + RestPurOperation.reversalCardActivation);
			restPurRelativeUrlMap.put(RestPurOperation.cardLinking, BASE_PATH_MDW + RestPurOperation.cardLinking);
			restPurRelativeUrlMap.put(RestPurOperation.reversalCardLinking,
					BASE_PATH_MDW + RestPurOperation.reversalCardLinking);
			restPurRelativeUrlMap.put(RestPurOperation.createAccount, BASE_PATH_MDW+RestPurOperation.createAccount);
			restPurRelativeUrlMap.put(RestPurOperation.getCifList, BASE_PATH_MDW+RestPurOperation.getCifList);
		}
		
		public static final Map<String, String> restLorinaUrlMap = new HashMap<String, String>();
		static{
			restLorinaUrlMap.put(SchemaBTPNLorinaOperation.calculationDeviation, BASE_PATH_BTPN_LORINA+SchemaBTPNLorinaOperation.calculationDeviation);
			restLorinaUrlMap.put(SchemaBTPNLorinaOperation.getExistingLoan, BASE_PATH_BTPN_LORINA+SchemaBTPNLorinaOperation.getExistingLoan);
			restLorinaUrlMap.put(SchemaBTPNLorinaOperation.getExistingLoanDetail, BASE_PATH_BTPN_LORINA+SchemaBTPNLorinaOperation.getExistingLoanDetail);
			restLorinaUrlMap.put(SchemaBTPNLorinaOperation.getSimulationWithBIChecking, BASE_PATH_BTPN_LORINA+SchemaBTPNLorinaOperation.getSimulationWithBIChecking);
			restLorinaUrlMap.put(SchemaBTPNLorinaOperation.kalkulasiAngsuran, BASE_PATH_BTPN_LORINA+SchemaBTPNLorinaOperation.kalkulasiAngsuran);
			restLorinaUrlMap.put(SchemaBTPNLorinaOperation.pilihAngsuran, BASE_PATH_BTPN_LORINA+SchemaBTPNLorinaOperation.pilihAngsuran);
			restLorinaUrlMap.put(SchemaBTPNLorinaOperation.getChecking, BASE_PATH_BTPN_LORINA+SchemaBTPNLorinaOperation.getChecking);
			restLorinaUrlMap.put(SchemaBTPNLorinaOperation.submitDataInterview, BASE_PATH_BTPN_LORINA+SchemaBTPNLorinaOperation.submitDataInterview);
			restLorinaUrlMap.put(SchemaBTPNLorinaOperation.postConfirm, BASE_PATH_BTPN_LORINA+SchemaBTPNLorinaOperation.postConfirm);
			restLorinaUrlMap.put(SchemaBTPNLorinaOperation.kalkulasiUlang, BASE_PATH_BTPN_LORINA+SchemaBTPNLorinaOperation.kalkulasiUlang);
		}
		public static final Map<String, String> purEloanMainLoanRelativeUrlMap = new HashMap<String, String>();
		static{
			purEloanMainLoanRelativeUrlMap.put(PurEloanMainLoanOperation.getLoanDetail, BASE_PATH_PUR_ELOAN_MAIN_LOAN+PurEloanMainLoanOperation.getLoanDetail);
			purEloanMainLoanRelativeUrlMap.put(PurEloanMainLoanOperation.loan, BASE_PATH_PUR_ELOAN_MAIN_LOAN+PurEloanMainLoanOperation.loan);
			purEloanMainLoanRelativeUrlMap.put(PurEloanMainLoanOperation.taskActivity, BASE_PATH_PUR_ELOAN_MAIN_LOAN+PurEloanMainLoanOperation.taskActivity);
			purEloanMainLoanRelativeUrlMap.put(PurEloanMainLoanOperation.GetSimulationWithBIChecking, BASE_PATH_PUR_ELOAN_MAIN_LOAN+PurEloanMainLoanOperation.GetSimulationWithBIChecking);
			purEloanMainLoanRelativeUrlMap.put(PurEloanMainLoanOperation.CheckLoanTaskExist, BASE_PATH_PUR_ELOAN_MAIN_LOAN+PurEloanMainLoanOperation.CheckLoanTaskExist);
			purEloanMainLoanRelativeUrlMap.put(PurEloanMainLoanOperation.createLoanTaskSameDay, BASE_PATH_PUR_ELOAN_MAIN_LOAN+PurEloanMainLoanOperation.createLoanTaskSameDay);
			purEloanMainLoanRelativeUrlMap.put(PurEloanMainLoanOperation.GSubmitDataEntry, BASE_PATH_PUR_ELOAN_MAIN_LOAN+PurEloanMainLoanOperation.GSubmitDataEntry);
			purEloanMainLoanRelativeUrlMap.put(PurEloanMainLoanOperation.submitDataInterview, BASE_PATH_PUR_ELOAN_MAIN_LOAN+PurEloanMainLoanOperation.submitDataInterview);
		}
		
		public static final Map<String, String> purEloanRelativeUrlMap = new HashMap<String, String>();
		static {
			purEloanRelativeUrlMap.put(PurEloanOperation.masterDataSync,
					BASE_PATH_PUR_ELOAN + PurEloanOperation.masterDataSync);
		}

		public static final Map<String, String> purEloanMainSavingRelativeUrlMap = new HashMap<String, String>();
		static{
			purEloanMainSavingRelativeUrlMap.put(PurEloanMainSavingOperation.saving, BASE_PATH_PUR_ELOAN_MAIN_SAVING+PurEloanMainSavingOperation.saving);
			purEloanMainSavingRelativeUrlMap.put(PurEloanMainSavingOperation.taskActivity, BASE_PATH_PUR_ELOAN_MAIN_SAVING+PurEloanMainSavingOperation.taskActivity);
			purEloanMainSavingRelativeUrlMap.put(PurEloanMainSavingOperation.savingRejection, BASE_PATH_PUR_ELOAN_MAIN_SAVING+PurEloanMainSavingOperation.savingRejection);
			purEloanMainSavingRelativeUrlMap.put(PurEloanMainSavingOperation.cardPerso, BASE_PATH_PUR_ELOAN_MAIN_SAVING+PurEloanMainSavingOperation.cardPerso);
			purEloanMainSavingRelativeUrlMap.put(PurEloanMainSavingOperation.cardActivation, BASE_PATH_PUR_ELOAN_MAIN_SAVING+PurEloanMainSavingOperation.cardActivation);
			purEloanMainSavingRelativeUrlMap.put(PurEloanMainSavingOperation.cardCancellation, BASE_PATH_PUR_ELOAN_MAIN_SAVING+PurEloanMainSavingOperation.cardCancellation);
			purEloanMainSavingRelativeUrlMap.put(PurEloanMainSavingOperation.deviation, BASE_PATH_PUR_ELOAN_MAIN_SAVING+PurEloanMainSavingOperation.deviation);
			purEloanMainSavingRelativeUrlMap.put(PurEloanMainSavingOperation.deviationApprove, BASE_PATH_PUR_ELOAN_MAIN_SAVING+PurEloanMainSavingOperation.deviationApprove);
			purEloanMainSavingRelativeUrlMap.put(PurEloanMainSavingOperation.deviationRejection, BASE_PATH_PUR_ELOAN_MAIN_SAVING+PurEloanMainSavingOperation.deviationRejection);
			purEloanMainSavingRelativeUrlMap.put(PurEloanMainSavingOperation.updateTaskCIF, BASE_PATH_PUR_ELOAN_MAIN_SAVING+PurEloanMainSavingOperation.updateTaskCIF);
		}
		
		public static final Map<String, String> btpnStubRelativeUrlMap = new HashMap<String, String>();
		static{
			btpnStubRelativeUrlMap.put(RestPurOperation.getCifList, BASE_PATH_STUB_PUR+RestPurOperation.getCifList);
		}
		
		public static final Map<String, String> btpnCMSMap = new HashMap<String, String>();
		static{
			btpnCMSMap.put(CMSOperation.findEnrolmentByNopenAndNorek, BASE_PATH_CMS+CMSOperation.findEnrolmentByNopenAndNorek);
			btpnCMSMap.put(CMSOperation.postEnrollment, BASE_PATH_CMS+CMSOperation.postEnrollment);
		}
		
		public static final Map<String, String> BtpnEcmRelativeUrlMap = new HashMap<String, String>();
		static {
			BtpnEcmRelativeUrlMap.put(ECMOperation.uploadDocument, BASE_PATH_BTPN_ECM + ECMOperation.uploadDocument);
			BtpnEcmRelativeUrlMap.put(ECMOperation.downloadDocument, BASE_PATH_BTPN_ECM + ECMOperation.downloadDocument);
			BtpnEcmRelativeUrlMap.put(ECMOperation.getListDocument, BASE_PATH_BTPN_ECM + ECMOperation.getListDocument);
		}
		
		public static class NotifyStatusImage {
			public static final String notifyStatusImage = "/ploan/rest/mainImageStatus/oneImage";
			
		}
		
		public static class SubmitDataEntryLorina {
			public static final String submitDataEntryLorina = "/rest/lorina/SubmitDataEntry";
			
		}
	}
	
	public static String DTP_SERVER = "DTP";
	public static String ESAVING_SERVER = "ESAV";
	
	public static class RequestMethodGET{
		public static String PATH_VAR = "PATH_VAR";
		public static String REQUEST_PARAM = "REQUEST_PARAM";
	}
	
	public static class UserSyncTitle {
		public static String SMO = "SMO";
		public static String SMS = "SMS";
		public static String CA = "CA";
		public static String MSO = "MSO";
		public static String CAS= "CAS";
		public static String SDH = "SDH";
		public static String RSH = "RSH";
		public static String ABL = "ABL";
		public static String BSM = "BSM";
		public static String SPR = "SPR";
		public static String ADM = "ADM";
		public static String DH = "DH";
		public static String RH = "RH";
		public static String AH = "AH";
		public static String BH = "BH";
		public static String BM = "BM";
		
		/*
		 * NO NEED MAPPING, DYNAMIC MAPPED ROLE WITH THE SAME CODE WITH TITLE
		 * public static final Map<String, String> ROLE_MAP = new HashMap<String, String>();
		static{

			ROLE_MAP.put(SMO, "SMO_PURNA");
			ROLE_MAP.put(SMS, "SMS_PURNA");
			ROLE_MAP.put(CA, "CA_PURNA");
			ROLE_MAP.put(MSO, "MSO_PURNA");
			ROLE_MAP.put(CAS, "CAS_PURNA");
			ROLE_MAP.put(SDH, "SDH_PURNA");
			ROLE_MAP.put(RSH, "RSH_PURNA");
			ROLE_MAP.put(ABL, "ABL_PURNA");
			ROLE_MAP.put(BSM, "BSM_PURNA");
			ROLE_MAP.put(SPR, "SPR_PURNA");
			ROLE_MAP.put(ADM, "ADM_PURNA");
			ROLE_MAP.put(DH , "DH_PURNA");
			ROLE_MAP.put(RH , "RH_PURNA");
			ROLE_MAP.put(AH , "AH_PURNA");
			ROLE_MAP.put(BH , "BH_PURNA");
			ROLE_MAP.put(BM , "BM_PURNA");
		}*/
	}
	
	public static class PointOfPresenceLOBStatus{
		public static int UNPROCESSED= 0;
		public static int PROCESSED = 1;
		public static int NO_BELONG_TO = 2;
	}
	
	public static class processAllowed{
		public static Integer IS_LOAN_SAME_DAY = 0;
		//convert to bit. Bit formula = 2 power n
		public static Integer EFORM_APPROVED  = (int)Math.pow(2, 0.0);
		public static Integer DATA_ENTRY = (int)Math.pow(2, 1.0);
//		public static Integer STATUS2 = (int)Math.pow(2, 2.0);
//		public static Integer STATUSn = (int)Math.pow(2, n); //n is double value, increasing value
		
		public static Map<Integer, Integer> status = new HashMap<Integer, Integer>();
		static{
			status.put(EFORM_APPROVED, EFORM_APPROVED);
			status.put(DATA_ENTRY, DATA_ENTRY);
		}
		
		//completed is total of eform approve and data entry
		public static Integer COMPLETED = 0;
		static{
			for (Entry<Integer, Integer> entry : status.entrySet()) 
				COMPLETED += entry.getValue().intValue();
		}
	}
	
	public static class LongOfWorkCount{
		public static Integer TOTAL = 4;
	}
	
	public static final String SKIPPED_CUSTOMER_REJECTED = "SKIPPED, CUSTOMER HAS ALREADY REJECTED";
}
