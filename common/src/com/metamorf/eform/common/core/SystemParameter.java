package com.metamorf.eform.common.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.metamorf.eform.common.util.ReflectionFunction;

public class SystemParameter {	
	public static final String HEADER_STD = "BBJMFCME95FGC99824";

	public static String TITLE_BM_FOR_EMAIL_WHEN_REPAIR = "BM";
	
	public static int USERNAME_LENGTH = 50;
	
	public static int DATA_RETENTION_IN_MONTH = 12;
	
	//HIGH RISK CODE
	public static String HIGH_RISK_CODE								= "H";
	public static String NOT_HIGH_RISK_CODE							= "L";
	
	public static String HIGH_RISK_TERRORIST 						= "TERRORIST";
	public static String LOW_RISK_TERRORIST 						= "-";
	
	//Template Email & SMS - SINAYA
	public static String TEMPLATE_EMAIL_RETURN_TO_PB_CS				= "";
	public static String TEMPLATE_EMAIL_VERIFIER					= "";
	public static String TEMPLATE_EMAIL_CALLBACK 					= "";
	public static String TEMPLATE_EMAIL_EFORM_APPROVAL				= "";
	public static String TEMPLATE_EMAIL_EDD_APPROVAL				= "";
	public static String TEMPLATE_EMAIL_APPROVED_TO_PB_CS			= "";
	public static String TEMPLATE_EMAIL_REJECTED_TO_PB_CS			= "";
	public static String TEMPLATE_EMAIL_SEMI_ACTIVE_TO_CUSTOMER		= "";
	public static String TEMPLATE_EMAIL_FULLY_ACTIVE_TO_CUSTOMER	= "";
	public static String TEMPLATE_SMS_SEMI_ACTIVE_TO_CUSTOMER		= "";
	public static String TEMPLATE_SMS_FULLY_ACTIVE_TO_CUSTOMER		= "";
	public static String TEMPLATE_BODY								= "";
	public static String TEMPLATE_EMAIL_CARD_EMAIL_CARD_PERSO	    = "";
	
	/*system param title for purna*/
	public static String TEMPLATE_EMAIL_REMIND_TBO_OFFICER_AND_1_LEVEL_UP   = "14";
	public static String TEMPLATE_EMAIL_TO_REMIND_TBO_FOR_2_LEVEL_UP	    = "30";
	public static String USER_TITLE_SKIP_VERIFIER= "CA"; //separate by comma e.g. CA,CAA
	public static String USER_TITLE_PRE_EDD = "BSM, BM"; //separate by comma e.g. BSM, BM
	public static String USER_TITLE_EDD	    = "ASL"; //separate by comma e.g. ASL, ABL
	
	//Subject Email (generate by system)
	public static String SUBJECT_EMAIL = "Eform Sinaya";

	//LDAP
	/** OpenDJ LDAP related Parameters */
    public static String LDAP_HOST = "10.1.72.28";
    public static int LDAP_PORT = 389;
    public static String LDAP_BASE_DN = "DC=dev,DC=corp,DC=btpn,DC=co,DC=id";
    public static String LDAP_ADMIN_DN = "cn=Directory Manager";
    public static String LDAP_ADMIN_AAPL = "Bii12345";
    public static String LDAP_DOMAIN_DN = "dc=bankbii,dc=com";
    public static String LDAP_USER_DIR_RDN = "ou=user";
    
	public static String LDAP_PRINCIPAL_USERNAME = "opics1.dev";
	public static String LDAP_PRINCIPAL_PASSWORD = "P@ssw0rd";
	public static String LDAP_USERNAME_SUFFIX = "@dev.corp.btpn.co.id";
	public static String LDAP_QUERY_USERNAME = "userPrincipalName";
	public static String LDAP_QUERY_NAME = "name";
	public static String LDAP_QUERY_EMAIL = "mail";
	public static String LDAP_QUERY_CN = "cn";
	public static String LDAP_QUERY_SUFFIX = "@BTPN";
	public static String LDAP_OBJECT_CLASS = "objectClass";
	public static String LDAP_VALUE_OBJECT_CLASS_USER = "user";
	
	//Mobilizer
	public static Boolean SKIP_VERIFY_OTP 	= false;
	public static boolean BYPASS_LDAP = false;
	public static boolean BYPASS_REST_AUTHENTICATION = false;
	public static boolean BYPASS_ELOAN_OUTSYSTEMS = false;
	public static boolean BYPASS_DECRYPT = false; // DEV
	public static boolean API_GATEWAY_TLS_1_2 = true; // PROD
	
	//CALLBACK
	public static boolean NEED_CALLBACK = true;
	public static int CALLBACK_COUNT = 3;
	public static int CALLBACK_DURATION = 2;
	
	//CALLBACK BATCH
	public static String CALLBACK_BATCH_1_START = "10:30";
	public static String CALLBACK_BATCH_1_END = "12:00";
	public static String CALLBACK_BATCH_2_START = "13:00";
	public static String CALLBACK_BATCH_2_END = "14:30";
	public static String CALLBACK_BATCH_3_START = "15:00";
	public static String CALLBACK_BATCH_3_END = "16:30";
	
	//WORKFLOW
	public static int AUTO_REJECT_REPAIR_DURATION = 1;
	public static int AUTO_REJECT_AKTIFASI_DURATION = 1;	
	public static boolean NEED_EDD_APPROVAL = true;
	public static String CUT_OFF_TIME_VERIFIER = "20:00";
	public static String START_WORK_TIME_VERIFIER = "08:00";
	public static String CUT_OFF_TIME_CALLBACK = "20:00";
	public static String START_WORK_TIME_CALLBACK = "08:00";
	public static String CUT_OFF_TIME_APPROVAL = "20:00";
	public static String START_WORK_TIME_APPROVAL = "08:00";
	public static String CUT_OFF_TIME_EDD = "20:00";
	public static String START_WORK_TIME_EDD = "08:00";

	//TITLE DESCRIPTION or GEOGRAPHY CODE
	public static String SPAM = "SPAM";
	public static String HQ = "HQ";
	public static String REGION = "Region";
	public static String AREA = "Area";
	public static String BRANCH = "Branch";
	public static String SUB_BRANCH = "Sub Branch";
	
	//SINAYA AGENT
	public static String PB = "PB";
	public static String CS = "CS";
	
	//USER
	public static long AGENT_ROLE = 37L;	
	public static long BANK_USER_AREA = 10L;
	
	public static String AGENT_ROLE_NAME = "Sinaya Agent";	
	public static String BANK_USER_AREA_NAME = "Headquarters";
	
	public static String RSCF_WSDL_URL = "https://172.29.74.47:9445/rcasws/services/CASServices";
	//ID: http://172.18.25.16:9080/rcasws/services/CASServices
	//MY: https://172.29.74.47:9445/rcasws/services/CASServices

	public static boolean RCAS_ENABLE = true;
    public static String RCAS_AUTO_SEND_INDI_USER_CREATE ="P";
    public static String RCAS_AUTO_SEND_INDI_USER_RESET ="R";
   
    //WSDL End Point
    @Deprecated
    public static String WSDL_BTPN_WOW_CUSTOMER 	= "http://10.1.72.165:5888/ws/com.btpn.meapwow.ws.provider:BtpnMeapWowCustomerService"; 
    
    @Deprecated
    public static String WSDL_BTPN_WOW_DWH 			= "http://appmdwdev02.dev.corp.btpn.co.id:5888/ws/com.btpn.eform.ws.provider:BtpnRevampEformDwh"; 
    
    @Deprecated
    public static String WSDL_BTPN_WOW_NOTIFICATION = "http://APPMDWDEV02.dev.corp.btpn.co.id:5887/ws/com.btpn.common.ws.in:NotifiactionService"; 
    
    @Deprecated
    public static String WSDL_BTPN_WOW_REVAMP 		= "http://10.1.72.165:5888/ws/com.btpn.eform.ws.provider:BtpnRevampEformWow"; 
    
    @Deprecated
    public static String WSDL_BTPN_WOW_SECURITY 	= "http://10.1.72.165:5888/ws/com.btpn.meapwow.ws.provider:BtpnMeapWowSecurityService"; 
    
    @Deprecated
    public static String WSDL_BTPN_WOW_SMP 			= "http://APPMDWDEV02.dev.corp.btpn.co.id:5888/ws/com.btpn.eform.ws.provider:BtpnRevampEformSmp";
        
    public static String WSDL_BTPN_SINAYA_NOTIFICATION 	= "https://appmdwdev03.dev.corp.btpn.co.id:7216/ws/com.btpn.sinaya.ws.provider:BtpnEformNotificationSinaya";
    public static String WSDL_BTPN_SINAYA	= "https://appmdwdev04.dev.corp.btpn.co.id:7216/ws/com.btpn.sinaya.ws.provider:sinayaService";
    public static String WSDL_BTPN_SINAYA_SMP 	= "https://appmdwdev04.dev.corp.btpn.co.id:7216/ws/com.btpn.sinaya.ws.provider:BtpnSmpSinaya";
    
    //SDB - WSDL
    public static String WSDL_BTPN_SDB_INQUIRY 	= "https://appmdwdev03.dev.corp.btpn.co.id:5774/ws/com.btpn.ecm.ws.provider.BtpnECMInquiry";
    public static String WSDL_BTPN_SDB_UPDATE 	= "https://appmdwdev03.dev.corp.btpn.co.id:5774/ws/com.btpn.ecm.ws.provider.BtpnECMUpdate";
    public static String WSDL_BTPN_SDB_NOTIFICATION = "https://appmdwdev04.dev.corp.btpn.co.id:5774/ws/com.btpn.common.ws.in.BtpnNotificationService/com_btpn_common_ws_in_BtpnNotificationService_Port";
    public static String WSDL_BTPN_SDB_CLOSE 		= "https://appmdwdev03.dev.corp.btpn.co.id:7103/ws/com.btpn.eformeq.ws.provider:BtpnEformCloseAccountEQ";

	// HOST REST URL
 
	public static String BTPN_REST_URL_API_GATEWAY = /*"https://apidev-int.dev.corp.btpn.co.id:19501/"*/"https://api-int.btpn.com:19502/"/*"https://APPAPISIT01.dev.corp.btpn.co.id:9501"*/;
	public static String BTPN_API_GATEWAY_KEY = "ffb120d9-37f3-40a9-8a6a-e1fe7e3ecf63";
	
//	public static String BTPNCHECKING_REST_SUFFIX_URL = "/rest/btpnchecking";
//	public static String ECM_REST_SUFFIX_URL = "/rest/ecm";
//	public static String SINAYA_REST_SUFFIX_URL = "/rest/sinaya";
	public static String HOST_REST_BTPN_OAUTH_TOKEN_URL = "https://APPAPISIT01.dev.corp.btpn.co.id:8089/api/oauth/token";
//	public static String HOST_REST_URL_ELOAN = "http://10.1.77.23/PURELOANWS/rest/";
	@Deprecated
	public static String HOST_REST_URL_UWMP = "https://appradpts01.dev.corp.btpn.co.id/"; // 10.1.77.23
	@Deprecated
	public static String HOST_REST_URL_UPLOAD = "/rest/ecm2/com.btpn.ecm.wrapper.restservice.maintainFile.uploadDocument";
	@Deprecated
	public static String OUTSYSTEM_NOTIFY_IMAGE = "http://10.1.77.23/PURELOANImageWS/rest/mainImageStatus/oneImage";
	
	public static String OUTSYSTEM_SUBMIT_DATA_ENTRY ="http://10.1.77.23/PURELOANMainWS/rest/mainLoan/GSubmitDataEntry";
	
	
	//HOST STUB URL
	@Deprecated
	
	public static String PUR_STUB_SUFFIX_URL = "/stub/pur";

//	public static String BTPN_REST_URL_API_GATEWAY_TEST = "https://APPAPISIT01.dev.corp.btpn.co.id:9501";

//	/com.btpn.ecm.wrapper.restservice.maintainFile.downloadDocument
    
    public static String SORT_ORDER			 		= "ASC";
    public static String OPERATOR			 		= "LIKE";
    public static String PAGE_INDEX			 		= "1";
    public static String PAGE_SIZE			 		= "250";
    public static String AUTH_USER_NAME		 		= "development\\optimus1";
    public static String AUTH_PASSWORD		 		= "P@ssw0rd";
    public static int SDB_NOTIFICATION_TYPE			= 1;
    public static String PROCODE_CLOSE_ACCOUNT_SDB 	= "92000";
    public static String PROCODE_CLOSE_ACCOUNT_PURNA_BAKTI 	= "92000";
     
	//Template Email & SMS - SDB
	public static String TEMPLATE_EMAIL_SDB_FULLY_ACTIVE_CUSTOMER	= "";
	public static String TEMPLATE_EMAIL_SDB_REJECTED_CUSTOMER		= "";
	public static String TEMPLATE_SMS_SDB_FULLY_ACTIVE_CUSTOMER		= "";
	public static String TEMPLATE_SMS_SDB_REJECTED_CUSTOMER			= "";
	public static String TEMPLATE_EMAIL_SDB_BUSINESS_APPROVAL		= "";
	
	// Subject email - SDB
	public static String SUBJECT_SDB_FULLY_ACTIVE_CUSTOMER			= "";
	public static String SUBJECT_SDB_REJECTED_CUSTOMER				= "";
	public static String SUBJECT_SDB_BUSINESS_APPROVAL				= "";
	
	// username, Password, & Sender Id notification
	public static String SDB_NOTIFICATION_USERNAME			= "E-Form.SDB@btpn.com";
	public static String SDB_NOTIFICATION_PASSWORD			= " ";
	public static String SDB_EMAIL_FROM						= "BTPN";
	public static String SDB_SENDER_ID						= "BTPN OTP";
    
    public static String WSDL_SUFFIX = "?WSDL";
    public static String WSDL_BASIC_AUTH_USERNAME = "2FiQGztZHjvfECWpAzyOWxKnZWNFRACmMGsAv6jkhZguA5jOaONuP8S/lj6NQnnz"; //eformService
    public static String WSDL_BASIC_AUTH_PASSWORD = "qyB7wqo0/9hvNYDK0h+JJGIEOAvyEv2If+1iE9yVSlHbUqp9lUktNMb6wHBlR/aB"; //wowbtpneform123
    public static String WSDL_BASIC_AUTH_SALT = "qyB7w";   
    
    public static int WEB_SERVICE_CONNECT_TIMEOUT = 70000; // 70s waiting to connect
	public static int WEB_SERVICE_REQUEST_TIMEOUT = 70000; // 70s waiting for response after successful connection

    
    //Rest Page eForm
    public static String REST_URL = "http://localhost:8080/eform/rest/RestWebservice.action";   
    
    public static boolean NPWP_IMAGE_REQUIRED = false;
    
    public static String CERTIFICATE_NAME 	= "appcondev03_keystore";
    public static String CERTIFICATE_PWD 	= "P@ssw0rd123";
    public static String CERTIFICATE_INSTANCE = "TLS";
    public static String KEYSTORE_INSTANCE 	= "JKS";
    
    public static int WOW_CUSTOMER_TYPE_SEMI_ACTIVE = 50000;
    public static int WOW_CUSTOMER_TYPE_FULL_ACTIVE = 60;
    public static String CALLER_ID				= "221";
    public static String TERITORY_CODE			= "0700";
    public static int NOTIFICATION_MODE			= 3;
    
    public static String REPORT_TEMPLATE_FOLDER = "/apps/eform/report/jrxml/";
    
	//BTPN Mobiliser : Push Notification
    public static String BTPN_SMP_APPLICATION_ID 	= "wow.app.eform";    
    
    //List Customer Min Days
    public static int MIN_DAYS_FINISHED = 10;
	
	/** Digester Algorithm */
    public static String DIGESTER_ALGORITHM="MD5";

    /** std indo utk format uang */
    public static String LOCALE="in_ID";

    /** MAIL_HOST */
    public static String MAIL_HOST="10.225.16.153";

    /** MAIL_PORT */
    public static int MAIL_PORT=25;

    /** MAIL_USERNAME */
    public static String MAIL_USERNAME="fscmbpm@biilab.net";
    
    /** MAIL_FROM_DISPLAY_NAME */
    public static String MAIL_FROM_DISPLAY_NAME="RSCF";
    
    public static String EFORM_URL="http://localhost:8280/eform";
    
    public static String PROCESS_APP_RSCF_ID = "2066.3fe42188-1403-4c25-b92a-2bebad47f4c3";
    public static String PROCESS_APP_NAME = "RSCF 1B1";
    public static String BPD_DOCUMENT_MANAGEMENT_ID = "25.9bb25b93-8939-4c1a-b82b-9cddb4bfd0fb";
	public static String BPD_DISBURSEMENT_ID = "25.4257559a-4fb6-4f88-bc50-ea44812cc8ab";
	public static String BPD_SETTLEMENT_ID = "25.57e1b025-94f4-440d-a3e7-9b53e4061ee4";
	public static String BPM_HOST_NAME = "10.225.7.44"; //use ip instead of hostname to resolve 401 error response
	public static String BPM_FIND_DOCUMENT_MANAGEMENT_PROCESS = "findDocumentManagementProcess";
	public static int BPM_HOST_PORT = 9080;

    /** Jumlah baris per halaman */
    public static int MAX_RECORD_PER_SCREEN=10;
    public static int PAGE_GROUP_SIZE = 10;

    /** Max size for file upload */
    public static long MAX_FILE_UPLOAD_SIZE = 1000000; // per byte
    public static long MAX_FILE_DOWNLOAD_SIZE = 1000000; // per byte
    public static long MAX_FILE_UPLOAD_MULTIPLE_SIZE = 102400; // per byte

    /** format harus sesuai */
    public static boolean STRICT_EMAIL_ADDRESS=true;

    /** Lokasi File Template Export */
    public static String TEMPLATE_DIRECTORY="/com/bankbii/rscf/excel/template/";

    /** Lokasi template direktori untuk download report */
    public static String TEMPLATE_REPORT_DOWNLOAD_DIR="/opt/cis/template";    
    
    /** No. of idle days before account become inactive */
    public static int DAYS_NOT_LOGIN=30;

    /** maximum days not login before locked */
    public static int MAX_DAYS_NOT_LOGIN=30;
    

    /** No. of second before session become expired */
    public static int SESSION_TIME_OUT=90000;

    /** No. of login trial before locked */
    public static int MAX_PASSWORD_ERROR=5;

    /** max stored password in history  */
    public static int MAX_PASSWORD_HISTORY_CHECK=6;

    /** Max password length */
    public static int MAX_PASSWORD_LENGTH=15;

    /** Min password length */
    public static int MIN_PASSWORD_LENGTH=6;

    /** No. of days to notify before password become expired */
    public static int ALERT_BEFORE_PASSWORD_EXPIRED=4;

    /** No. of days of default password before expired */
    public static int PASSWORD_LIFETIME=60;

    /** No. of second before session become expired */
    public static int SESSION_TIMEOUT_BO=90;
    public static int SESSION_TIMEOUT_FO=90000;

    /** Default expired login interval in days for user account */
    public static int VALID_LOGIN_INTERVAL=15;
    
    /** Default first login day interval in days for user account */
    public static int FIRST_LOGIN_DAY_INTERVAL=5;

    /** should show ? */
    public static boolean RECAPTCHA_DISPLAY=false;
    
    public static int WORKFLOW_DAYS_PERFORMANCE = 1;
    
    //change the value of report_template_location based on your report folder location, please merge before commiting
    public static String ROOT_LOCATION = "/opts/eform/";
    public static String ROOT_IMAGE_LOCATION = "/images/";
    
    //Applicant Image Location
    public static String SVS_IMAGE_LOCATION = "/apps/eform_app/svs_images/";
    public static String PDF_LOCATION = "/apps/eform_app/svs_pdf/";
    public static String EMAIL_LOCATION = "/apps/eform/email/";
    
    public static String CUSTOMER_IMAGE_LOCATION = "/apps/eform_app/images/customer/";
    public static String CUSTOMER_IMAGE_SHOW_LOCATION = "/images/customer/";
    public static String FILE_CHUNK_TEMP_LOCATION = "/apps/eform_app/images/chunk/";
    public static String FILE_RESULT_EXTENSION = ".jpeg";
    
    //ECM
    public static String ECM_FILE_EXTENSION = ".jpg";
    public static String ECM_IMAGE_TYPE = "Mobile Apps Data Entry";
    public static String PASSWORD_ECM = "P@ssw0rd";
	public static String USER_NAME_ECM = "development\\spfessvc";
    
	//LORINA
	public static String LORINA_FILE_EXTENSION = "jpg";
	
    //public static String ROOT_LOCATION = "/Users/kisman/Documents/workspace/EForm";
    
    public static String REPORT_TEMPLATE_LOCATION = "/report/";
    public static String REPORT_RESULT_LOCATION = "/result/";
    public static String DISBURSEMENT_ADVICE_FILE_LOCATION = "result/disbursement";
    public static String SETTLEMENT_ADVICE_FILE_LOCATION = "result/settlement";
    public static String SETTLEMENT_TRX_ADVICE_FILE_LOCATION = "result/settlementTrx";
    	
    public static String MAIL_SENDER_HOST = "rscf.bankbii.com";
        
    /**Corporate Action Cron Trigger Resolver*/
    public static String QUARTZ_TASK_MONITORING_CRON_EXPRESSION = "0 0 0 * * ?";
    public static String CRON_LOAN_STATUS_EXPRESSION = "0 0 0 * * ?";
    public static String CRON_INVOICE_STATUS_EXPRESSION = "0 0 0 * * ?";
    public static String CRON_ONE_TIME_NOTIFICATION_EXPRESSION = "0 0/3 * * * ?";
    public static String CRON_DUE_NOTIFICATION_EXPRESSION = "0 0 1 * * ?";

    public static String LDAP_USER_DIR_DN = LDAP_USER_DIR_RDN + "," + LDAP_DOMAIN_DN;
    /*not using this ldap param */
    
    public static int SERVER_TIME_DIFFERENCE = 0; //in hour(s)
    public static boolean IS_FIRST_NAME_FIRST = true;
    public static int DEFAULT_DOCUMENT_DETAIL_ITEM_LIST_ROW = 5;
    public static int MAXIMUM_DOCUMENT_DETAIL_ITEM_LIST_ROW = 15;
    public static String DISB_SEQUENCE_NO 		= "000000";
    public static String LOAN_SEQUENCE_NO 		= "000000";
    public static String INV_SEQUENCE_NO 		= "0000000";
    
    /* MAIL PARAM */
    public static String MAIL_SUBJECT_GENERIC = "Please take action in RSCF";
    public static String MAIL_SUBJECT_INVOICE = "Please take action in RSCF";
    public static String MAIL_SUBJECT_DISB = "Please take disbursement action in RSCF";
    public static String MAIL_SUBJECT_SETTLE = "Please take settlement action in RSCF";
    public static String MAIL_SUBJECT_EXTEND_TENOR = "Please take extend tenor action in RSCF";
    
    /*MAIL TEMPLATE*/
    public static String MAIL_TEMPLATE_SETTLEMENT_FROM_BANK_RELEASE_CHECKER = "settlementFromBankReleaseChecker";
    public static String MAIL_TEMPLATE_SETTLEMENT_FROM_CREATE = "settlementFromCreate";
    public static String MAIL_TEMPLATE_SETTLEMENT_TO_REPAIR = "settlementToRepair";
    
    public static String MAIL_TEMPLATE_ET_FROM_BANK_RELEASE_CHECKER = "etFromBankReleaseChecker";
    public static String MAIL_TEMPLATE_ET_FROM_CREATE = "etFromCreate";
    public static String MAIL_TEMPLATE_ET_TO_REPAIR = "etToRepair";
    
    /** Parameter For APK VERSION **/
    public static String APK_VERSION = "1.4, 2.1";
    
    public static int SLICING_COUNTER = Integer.valueOf(35);
    
   public static String generatedDisbSeqNo(){
		Integer seq = Integer.valueOf(SystemParameter.DISB_SEQUENCE_NO);
		DISB_SEQUENCE_NO = StringUtils.leftPad(String.valueOf(seq+1), 6, '0');
    	return DISB_SEQUENCE_NO;
    }
    
    public static String generateLoanSeqNo(){
		Integer seq = Integer.valueOf(SystemParameter.LOAN_SEQUENCE_NO);
		LOAN_SEQUENCE_NO = StringUtils.leftPad(String.valueOf(seq+1), 6, '0');
    	return LOAN_SEQUENCE_NO;
    }
    
    public static String generateInvSeqNo(){
		Integer seq = Integer.valueOf(SystemParameter.INV_SEQUENCE_NO);
		INV_SEQUENCE_NO = StringUtils.leftPad(String.valueOf(seq+1), 7, '0');
    	return INV_SEQUENCE_NO;
    }    
    
    public static String MONEY_FORMAT = "###,###,###.##";
    
    public static String EXCHANGE_FORMAT = "###,###,###.####";
    
    public static String EMAIL_DELIMITER = ";";
    
    public static String RSCF_FTP_ADDRESS = "10.225.7.44";
	public static int RSCF_FTP_PORT = 22;
	public static String RSCF_FTP_USER_NAME = "root";
	public static String RSCF_FTP_PASSWORD = "root";
	public static String RSCF_FTP_DEST_DIR = "/home/test_sftp";
	public static String RSCF_CSV_DEST_DIR = "/write_csv_file";
	
	public static String NOTIFICATION_TEMPLATE_DIRECTORY = "/rscf/notification_template/";
	
	public static int MAX_DOCUMENT_CHECKLIST = 5;
    
	public static int MAX_ITEM_ROW = 5;
	public static int MIN_ITEM_ROW = 5;
	
    /*tested format{
    	[dd-mmm-yyyy hh:mm:ss] although it is recommended to use (dd-MMM-yyyy HH:mm:ss) instead, output 30-Aug-2013 14:20:20
    	[DD-mmm-yyyy hh:mm:ss] although it is recommended to use (dd-MMM-yyyy HH:mm:ss) instead, output 30-Aug-2013 14:20:20
    	[dd-MMM-yyyy hh:mm:ss] although it is recommended to use (dd-MMM-yyyy HH:mm:ss) instead, output 30-Aug-2013 14:20:20
    	[dd-mmm-YYYY hh:mm:ss] although it is recommended to use (dd-MMM-yyyy HH:mm:ss) instead, output 30-Aug-2013 14:20:20
    	[dd-mmm-yyyy HH:mm:ss] although it is recommended to use (dd-MMM-yyyy HH:mm:ss) instead, output 30-Aug-2013 14:20:20
    	[DD-MMM-yyyy hh:mm:ss] although it is recommended to use (dd-MMM-yyyy HH:mm:ss) instead, output 30-Aug-2013 14:20:20
    	[DD-MMM-YYYY hh:mm:ss] although it is recommended to use (dd-MMM-yyyy HH:mm:ss) instead, output 30-Aug-2013 14:20:20
    	
    	[dd-mmm-yyyy] although it is recommended to use (dd-MMM-yyyy) instead, output 30-Aug-2013
    	[DD-MMM-yyyy] although it is recommended to use (dd-MMM-yyyy) instead, output 30-Aug-2013
    	[DD-MMM-YYYY] although it is recommended to use (dd-MMM-yyyy) instead, output 30-Aug-2013
    	[dd-mmm-YYYY] although it is recommended to use (dd-MMM-yyyy) instead, output 30-Aug-2013
    	[DD-mmm-YYYY] although it is recommended to use (dd-MMM-yyyy) instead, output 30-Aug-2013
    	
    	[dd-mm-yyyy hh:mm:ss] although it is recommended to use (dd-MM-yyyy HH:mm:ss) instead, output 30-08-2013 14:20:20
    	[dd-mm-yyyy HH:mm:ss] although it is recommended to use (dd-MM-yyyy HH:mm:ss) instead, output 30-08-2013 14:20:20
    	[dd-MM-yyyy hh:mm:ss] although it is recommended to use (dd-MM-yyyy HH:mm:ss) instead, output 30-08-2013 14:20:20
    	[DD-MM-yyyy hh:mm:ss] although it is recommended to use (dd-MM-yyyy HH:mm:ss) instead, output 30-08-2013 14:20:20
    	[DD-MM-YYYY hh:mm:ss] although it is recommended to use (dd-MM-yyyy HH:mm:ss) instead, output 30-08-2013 14:20:20
    	
    	[dd-mm-yyyy] although it is recommended to use (dd-MM-yyyy) instead, output 30-08-2013
    	[DD-mm-yyyy] although it is recommended to use (dd-MM-yyyy) instead, output 30-08-2013
    	[dd-mm-YYYY] although it is recommended to use (dd-MM-yyyy) instead, output 30-08-2013
    	[DD-mm-YYYY] although it is recommended to use (dd-MM-yyyy) instead, output 30-08-2013
    	
    	[dd/mm/yyyy hh:mm:ss] although it is recommended to use (dd/MM/yyyy HH:mm:ss) instead, output 30/08/2013 14:20:20
    	[DD/mm/yyyy hh:mm:ss] although it is recommended to use (dd/MM/yyyy HH:mm:ss) instead, output 30/08/2013 14:20:20
    	[dd/mm/YYYY hh:mm:ss] although it is recommended to use (dd/MM/yyyy HH:mm:ss) instead, output 30/08/2013 14:20:20
    	[dd/mm/yyyy HH:mm:ss] although it is recommended to use (dd/MM/yyyy HH:mm:ss) instead, output 30/08/2013 14:20:20
    	[DD/MM/yyyy hh:mm:ss] although it is recommended to use (dd/MM/yyyy HH:mm:ss) instead, output 30/08/2013 14:20:20
    	[DD/MM/YYYY HH:mm:ss] although it is recommended to use (dd/MM/yyyy HH:mm:ss) instead, output 30/08/2013 14:20:20
    	
    	[dd/mm/yyyy] although it is recommended to use (dd/MM/yyyy) instead, output 30/08/2013
    	[DD/mm/yyyy] although it is recommended to use (dd/MM/yyyy) instead, output 30/08/2013
    	[DD/MM/yyyy] although it is recommended to use (dd/MM/yyyy) instead, output 30/08/2013
    	[DD/MM/YYYY] although it is recommended to use (dd/MM/yyyy) instead, output 30/08/2013
    	
    	[dd mmm yyyy hh:mm:ss] although it is recommended to use (dd MMM yyyy HH:mm:ss) instead, output 30 Aug 2013 14:20:20
    	[DD mmm yyyy hh:mm:ss] although it is recommended to use (dd MMM yyyy HH:mm:ss) instead, output 30 Aug 2013 14:20:20
    	[dd MMM yyyy hh:mm:ss] although it is recommended to use (dd MMM yyyy HH:mm:ss) instead, output 30 Aug 2013 14:20:20
    	[DD MMM YYYY hh:mm:ss] although it is recommended to use (dd MMM yyyy HH:mm:ss) instead, output 30 Aug 2013 14:20:20
    	[DD MMM YYYY HH:mm:ss] although it is recommended to use (dd MMM yyyy HH:mm:ss) instead, output 30 Aug 2013 14:20:20
    	
    	[dd mmm yyyy] although it is recommended to use (dd MMM yyyy) instead, output 30 Aug 2013
    	[DD mmm yyyy] although it is recommended to use (dd MMM yyyy) instead, output 30 Aug 2013
    	[DD MMM yyyy] although it is recommended to use (dd MMM yyyy) instead, output 30 Aug 2013
    	[DD MMM YYYY] although it is recommended to use (dd MMM yyyy) instead, output 30 Aug 2013
    	
    	[yyyymmdd] although it is recommended to use (yyyyMMdd) instead, output 20130830
    	[YYYYmmdd] although it is recommended to use (yyyyMMdd) instead, output 20130830
    	[YYYYMMdd] although it is recommended to use (yyyyMMdd) instead, output 20130830
    	[YYYYMMDD] although it is recommended to use (yyyyMMdd) instead, output 20130830
    	
    	[yyyy-mm-dd] although it is recommended to use (yyyy-MM-dd) instead, output 2013-08-30
    	[YYYY-mm-dd] although it is recommended to use (yyyy-MM-dd) instead, output 2013-08-30
    	[YYYY-MM-dd] although it is recommended to use (yyyy-MM-dd) instead, output 2013-08-30
    	[YYYY-MM-DD] although it is recommended to use (yyyy-MM-dd) instead, output 2013-08-30
    	
    	[yyyy-mm-dd hh:mm:ss] although it is recommended to use (yyyy-MM-dd HH:mm:ss) instead, output 2013-08-30 14:20:20
    	*TBA*
    }
    warning{
    	it is really not recommend to use partial upperlowercase format like this (mM, mMM, Mmm)
    	for month use big M, for minute use small m
    	for hour use big H
    	milliseconds is not tackle here
    }*/
    public static String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";
    
    //non db static, date_format will not be changed dynamically
    public static String STRUTS2_DATE_FORMAT = "{0,date,"+DATE_FORMAT+"}";
    public static String JQUERY_DATE_FORMAT = "dd-mm-yy"; // kalau di datepicker dia = dd-MM-yyyy
    public static String JQUERY_DATE_FORMAT_NO_TIME = "dd-MM-yyyy";
    public static String JQGRID_DATE_FORMAT = "d-m-Y";
    public static String DATE_FORMAT_NO_TIME = "dd-MM-yyyy";
    public static String JQGRID_DATE_FORMAT_NO_TIME = "d-m-Y";
    public static String STRUTS2_DATE_FORMAT_NO_TIME = "{0,date,"+DATE_FORMAT_NO_TIME+"}";
    public static String JSON_DATE_FORMAT = "yyyy-MM-dd";
    public static String BTPN_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
//    public static String BTPN_AG_TIMESTAMP_FORMAT = "YYYY-MM-DDTHH26:MI:SS.ZZZ+07:00"
    public static String BTPN_AG_TIMESTAMP_FORMAT = "yyyy-mm-dd'T'HH:mm:ss.SSSXXX";
    public static String MDW_DATE_FORMAT = "yyyy-MM-dd";
    
    public static String SQL_FORMAT_WITH_TIME = "yyyy-MM-dd HH:mm:ss";
    
    public static String LIFETIME_IDENTITY_DATE = "28991231";
    
    //EQ Format
    public static String EQ_ID_EXPIRE_DATE_FORMAT = "yyyyMMdd";
    public static String EQ_LOI_DATE_FORMAT = "yyyyMMdd";
    public static String EQ_BIRTH_DATE_FORMAT = "yyyyMMdd";
    public static String EQ_DEFAULT_NUMERIC = "0";
    public static String EQ_DEFAULT_FREQ = "1";
    public static String EQ_DEFAULT_TAX_REFERENCES_1 = "T2";
	public static String EQ_DEFAULT_TAX_REFERENCES_2 = "T0";
	public static String EQ_DEFAULT_DAPEM = "01";
	public static String EQ_CUSTOMER_TYPE_INDIVIDUAL_PURNA = "04";
	public static String EQ_LANGUAGE_CODE = "ID";
	public static String EQ_JENIS_GOLONGAN_NASABAH = "9000";
	public static String EQ_RT = "RT.";
	public static String EQ_RW = "RW.";
	public static String EQ_HUBUNGAN_DENGAN_BANK = "02";
	public static String EQ_ID_ADDRESS_TYPE = "";
	public static String EQ_HOME_ADDRESS_TYPE = "1";
	public static String EQ_OFFICE_ADDRESS_TYPE = "2";
	public static String EQ_RELATIVE_ADDRESS_TYPE = "3";
	public static String EQ_CORRESPONDENT_ADDRESS_TYPE = "8";
	
	//BTPNChecking constant
	public static String BTPNCHECKING_CUSTOMER_TYPE_PURNA = "I"; //I katanya adalah Individual
    
    // verifier
    public static String VERIFIER_YES = "YA";
    public static String VERIFIER_NO = "TIDAK";
    
    //Exception Handling Code
    public static String EXCEPTION_HANDLING_CODE = "68";
    
    public static String DEFAULT_AAPL = "Master123";
    
    /* used by force logout to invalidate session, value format <scheme>://<hostname>:<cluster member port>/<context root of web>
     * example value: https://localhost:8443/RSCFWeb;https://localhost:8444/RSCFWeb;https://localhost:8443/RSCFWebBO;https://localhost:8444/RSCFWebBO
     * separate each member locations of cluster with ; */
    public static String BO_MEMBER_CLUSTER_LOCATIONS = "https://127.0.0.1:443/RSCFWebBO;http://127.0.0.1:8080/RSCFWebBO";
    public static String FO_MEMBER_CLUSTER_LOCATIONS = "https://127.0.0.1:443/RSCFWeb;http://127.0.0.1:8080/RSCFWeb";
    
	//Landing Page related
    public static String LANDING_PAGE_URL = "http://localhost:8080/RSCFLandingPage/";
    public static String LANDING_PAGE_DOMAIN = "http://localhost:8080";
    
    //Base Currency
    public static String BASE_CURRENCY = "MYR";
    public static int CONVERSION_SCALE = 4;
    
    //moved from SystemConstant.RCAS
    //as per request Pak MK, in SystemParameter empty value for these three parameter, so if we there is error we know that the System Parameter is not synchronized
    public static String COMPANY_ID_BANK = "";
    public static String PROVIDER = "";
	public static String COUNTRY_CODE = "";
	public static String APPLICATION_ID = "104";
    public static String DUE_NOTIFICATION_INTERVAL = "3,1,0";
     
    //REJECTED CUSTOMER NOTES, SET BY SYSTEM
    public static String NOTE_REJECTED_PERIOD_DATE_EXPIRED 	= "Periode pengiriman data telah expired";
    public static String NOTE_REJECTED_INCOMPLETE_DATA 		= "Data lampiran tidak lengkap";
	public static String NOTE_REJECTED_DHIB_CUSTOMER = "";
    
	// [30 November 2015] - Nationality SDB Default Value to Indonesia
	public static String SDB_NATIONALITY_CODE = "ID";
	
	// [03 February 2015|| SDB - CR 02] Negara Default Value to Indonesia.
	public static String SDB_COUNTRY_CODE	 = "ID";
	public static String SDB_COUNTRY		 = "Indonesia";
	
	// [09 February 2016] Default High Risk if not found in Database
	public static boolean DEFAULT_HIGH_RISK	 = true;

    /**
     * @param interval, must be value between 1,2,3 else if less than 1 _interval = 1, bigger than 3 _interval = 3
     * @return integer value of splitted DUE_NOTIFICATION_INTERVAL by index interval-1 
     */
    public static int getDueNotificationIntervalValue(int interval){
    	int _interval = interval;
    	if(interval>3){
    		_interval = 3;
    	}else if(interval<1){
    		_interval = 1;
    	}
    	String DNISYS = DUE_NOTIFICATION_INTERVAL;
    	String[] DNI = DNISYS!=null?DNISYS.trim().split(","):"3,1,0".split(",");//failsafe if DNI null, then use 3,1,0
    	Integer[] IDNI = new Integer[3];
    	int i = 0;
    	for (String s : DNI) {
			IDNI[i++] = Integer.valueOf(s);
		}
    	Arrays.sort(IDNI,Collections.reverseOrder());
    	int intervalResult = Integer.valueOf(IDNI[_interval-1]); 
    	return intervalResult;
    }
    
    public static Map<String,Integer> appParamDataTypeMap=new HashMap<String, Integer>();

	public static synchronized void updateSystemEnvironment(String name, String value) {
		ReflectionFunction.setProperties(SystemParameter.class, name, value);
	}
	
	public static synchronized void updateParameterDataType(String name, Integer value) {
		appParamDataTypeMap.put(name, value);
	}
	
	public static synchronized Object getValue(String name) {
		return ReflectionFunction.getPropertyValue(SystemParameter.class, name);
	}
	
	/**
	 * WO-012 : 4.1.1 Special Characters
	 * [Dev: 15 April 2016]
	 */
	
	public static String SPECIAL_CHARACTERS = "<>&'\"������";
	public static String REPLACE_CHARACTERS = "";
	public static int DEFAULT_TASK_PRIORITY = 1; // 0 = Standard, 1 = VIP, 2 = VVIP
	
	/**
	 * WO 021: Parameter
	 */
	public static String FORCE_FULLY_ACTIVE_PRO_CODE = "160000";

	public static String INPUT_WHITELIST = "ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz1234567890.";
	
	public static String MAX_RETRY_SEND_EMAIL_EDD_APPROVAL_CUSTOMER = "";
	public static String DURATION_RETRY_SEND_EMAIL_EDD_APPROVAL_CUSTOMER = "";
	
	public static String EMAIL_HOST = "";
	public static String EMAIL_PORT = "";
	public static String EMAIL_USERNAME = "";
	public static String EMAIL_PASSWORD = "";
	public static String EMAIL_USERNAME_SENDER = "";
	
	// PURNA SPECIFIC
	public static String EMAIL_USERNAME_PURNA = "";
	public static String EMAIL_PASSWORD_PURNA = "";
	public static String EMAIL_USERNAME_SENDER_PURNA = "";
	
	public static String EMAIL_HOST_POP3 = "";
	public static String EMAIL_USERNAME_POP3 = "";
	public static String EMAIL_POP3_PASSWORD = "";
	public static String EMAIL_POP3_PORT = "";
	
	public static String EMAIL_ACCOUNT_STATEMENT = "";
	
	public static String DEFAULT_ROLE_NAME = "Branch";

	public static String LAST_SYNC_USER_DATE = "01302017012322";//yyyyMMddhhmmss
	
	@Deprecated
	public static String REST_UWMP_URL = "http://10.1.77.23/BTPNUWMPWS/rest/";
	
	@Deprecated
	public static String REST_URL_MAIN_SAVING = "http://10.1.77.23/PURELOANMainWS/rest/mainSaving/saving";
	
	public static class OutsystemUserParam{
		public static String DATE_INPUT = "Date";
	}
	
	
	@Deprecated
	public static String PURELOANWS_POST_SYNC_VERSION_URL = "http://10.1.77.23/PURELOANWS/rest/version/sync";
	
//	public static String OUTSYSTEM_SYCN_MASTER_DATA_LORINA_URL = "http://10.1.77.23/BTPNUWMPWS/rest/BTPNUWMPWS/SyncMasterData";
	@Deprecated
	public static String OUTSYSTEM_SYCN_MASTER_DATA_LORINA_URL = "http://10.1.77.23/PUREALOANVersionWS/rest/version/sync";
	public static class OutsystemMasterDataLorina{
		public static String POP_LOB = "POP_LOB";
	}
	@Deprecated
	public static String OUTSYSTEM_REGISTER_ACTION_CODE_URL = "http://10.1.77.23/PUREALONMainWS/rest/mainSaving/taskActivity";
	@Deprecated
	public static String OUTSYSTEM_USER_PROFILE_CODE_URL = "http://10.1.77.23/BTPNUWMPWS/rest/User/profile?username={username}&referenceno={referenceno}";
	@Deprecated
	public static String OUTSYSTEM_MAIN_SAVING_CODE_URL = "https://10.1.77.23/PURELOANWS/rest/mainSaving/cardPerso";
	@Deprecated
	public static String OUTSYSTEM_MAIN_SAVING_URL = "http://10.1.77.23/PURELOANMainWS/rest/mainSaving";
	@Deprecated
	public static String OUTSYSTEM_LOAN_URL = "http://10.1.77.23/PURELOANMainWS/rest/mainLoan/loan";
	@Deprecated
	public static String OUTSYSTEM_LOAN_DETAIL_URL = "http://10.1.77.23/PURELOANMainWS/rest/mainLoan/GetLoanDetail";
	
	
	@Deprecated
	public static String OUTSYSTEM_REGISTER_ACTION_CODE_LOAN_URL = "http://10.1.77.23/PURELOANMainWS/rest/mainLoan/taskActivity";

	
	/* NEW EMAIL */
	public static String EMAIL_FOLDER ="";
	public static String EMAIL_MAX_DAY_TO_SEARCH ="";
	public static String EMAIL_DATE_PATTERN="";
	public static boolean EMAIL_LOOP_DIRECTION_BACKWARD=true;
	
	/* PURNA STRUCTURE DLL*/
	public static String DEFAULT_ROLE_PURNA = "DEFAULT_ROLE_PURNA";
	
	public static String UWMP_PB_PURNA_SALES = "UWMP_PB_PURNA_SALES";
	public static String UWMP_PB_PURNA_SERVICE = "UWMP_PB_PURNA_SERVICE";
	public static String UWMP_PB_AE_SALES = "UWMP_PB_AE_SALES";
	public static String UWMP_PB_AE_SERVICE = "UWMP_PB_AE_SERVICE";
	
	// UWMP_HQ, structure for HQ for every division always ends with 'KP'
	public static String UWMP_HQ_STRUCTURE ="KP";
	
	public static String UWMP_PB_PURNA_SALES_DIVISION = "UWMP_PB_PURNA_SALES_DIVISION";
	public static String UWMP_PB_PURNA_SERVICE_DIVISION = "UWMP_PB_PURNA_SERVICE_DIVISION";
	public static String UWMP_PB_AE_SALES_DIVISION = "UWMP_PB_AE_SALES_DIVISION";
	public static String UWMP_PB_AE_SERVICE_DIVISION = "UWMP_PB_AE_SERVICE_DIVISION";
	
	public static String UWMP_PB_REGION_STRUCTURE = "20RG,21RG,22RG,23RG";
	public static String UWMP_PB_AREA_STRUCTURE = "30AR,31AR,32AR,33AR";
	public static String UWMP_PB_BRANCH_LOCATION_STRUCTURE = "40KC,41KC,42KC,43KC";
	public static String UWMP_PB_SUB_BRANCH_STRUCTURE = "45KCP,46KCP,47KCP,48KCP";
	
	public static String EMAIL_TIME_INTERVAL_THREAD=""; // in minutes (ex: 5) -> 5minutes
	
	public static String PROCESSING_CODE_PURNA_CREATE_SEMI_ACTIVE 		= "250000";
	public static String PROCESSING_CODE_PURNA_CREATE_FULLY_ACTIVE 		= "270000";
	public static String PROCESSING_CODE_PURNA_FORCE_FULLY_ACTIVE 		= "270000";
	public static String PROCESSING_CODE_PURNA_CLOSE_ACCOUNT 			= "920000";
	public static String PROCESSING_CODE_PURNA_CARD_ACTIVATION			= "610000";
	
	public static boolean SIMULATE_ERROR_SAVING_REGISTRATION = false;
	
	public static String MAX_RETRY_REGISTER_SAVING = "4";
	public static String MAX_RETRY_SDE = "4";
	public static String MAX_LENGTH_SDE_RESPONSE = "999";
}
