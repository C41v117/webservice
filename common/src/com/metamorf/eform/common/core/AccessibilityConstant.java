package com.metamorf.eform.common.core;

import java.util.HashMap;
import java.util.Map;

import com.metamorf.eform.common.util.ReflectionFunction;

public class AccessibilityConstant {
	
	public static final long FUNC_LOGIN = 1;
	public static final long FUNC_LOGOUT = 2;
	public static final long FUNC_CHANGE_PASSWORD = 3;
	
	public static final long FUNC_ROOT = 10;
	
	public static final long FUNC_SETTING = 200;
	
	public static final long FUNC_SETTING_SYSPARAM = 210;
	public static final long FUNC_SETTING_SYSPARAM_LIST = 211;
	public static final long FUNC_SETTING_SYSPARAM_MODIFY = 212;
	public static final long FUNC_SETTING_SYSPARAM_APPROVAL = 213;
	
	public static final long FUNC_SETTING_HOLIDAY = 220;
	public static final long FUNC_SETTING_HOLIDAY_LIST = 221;
//	@Deprecated
	public static final long FUNC_SETTING_HOLIDAY_CREATE = 222;
//	@Deprecated
	public static final long FUNC_SETTING_HOLIDAY_MODIFY = 223;
//	@Deprecated
	public static final long FUNC_SETTING_HOLIDAY_APPROVAL = 224;
	
	public static final long FUNC_SETTING_CONTENT_MANAGER = 230;
	public static final long FUNC_SETTING_CONTENT_MANAGER_LIST = 231;
	public static final long FUNC_SETTING_CONTENT_MANAGER_CREATE = 232;
	public static final long FUNC_SETTING_CONTENT_MANAGER_MODIFY = 233;
	public static final long FUNC_SETTING_CONTENT_MANAGER_APPROVAL = 234;
	public static final long FUNC_SETTING_CONTENT_MANAGER_SUSPENSION = 235;
	
	public static final long FUNC_SETTING_WORKFLOW_PARAMETER			= 240;
	public static final long FUNC_SETTING_WORKFLOW_PARAMETER_LIST		= 241;
	public static final long FUNC_SETTING_WORKFLOW_PARAMETER_MODIFY		= 242;
	public static final long FUNC_SETTING_WORKFLOW_PARAMETER_APPROVAL 	= 243;
	
	public static final long FUNC_SETTING_RISK_PROFILER = 250;
	public static final long FUNC_SETTING_RISK_PROFILER_LIST = 251;
	public static final long FUNC_SETTING_RISK_PROFILER_MODIFY = 252;
	
	public static final long FUNC_SETTING_LOOKUP = 260;
	public static final long FUNC_SETTING_LOOKUP_LIST = 261;
	public static final long FUNC_SETTING_LOOKUP_CREATE = 262;
	public static final long FUNC_SETTING_LOOKUP_MODIFY = 263;
	public static final long FUNC_SETTING_LOOKUP_APPROVAL = 264;
	public static final long FUNC_SETTING_LOOKUP_SUSPENSION = 265;
	
	public static final long FUNC_SETTING_REGION			= 266;
	public static final long FUNC_SETTING_REGION_LIST		= 267;
	public static final long FUNC_SETTING_REGION_CREATE		= 268;
	public static final long FUNC_SETTING_REGION_MODIFY		= 269;
	public static final long FUNC_SETTING_REGION_APPROVAL 	= 270;
	public static final long FUNC_SETTING_REGION_SUSPENSION	= 271;
	
	public static final long FUNC_SETTING_AREA				= 272;
	public static final long FUNC_SETTING_AREA_LIST			= 273;
	public static final long FUNC_SETTING_AREA_CREATE		= 274;
	public static final long FUNC_SETTING_AREA_MODIFY		= 275;
	public static final long FUNC_SETTING_AREA_APPROVAL 	= 276;
	public static final long FUNC_SETTING_AREA_SUSPENSION 	= 277;
	
	public static final long FUNC_SETTING_BRANCH_LOCATION				= 278;
	public static final long FUNC_SETTING_BRANCH_LOCATION_LIST			= 279;
	public static final long FUNC_SETTING_BRANCH_LOCATION_CREATE		= 280;
	public static final long FUNC_SETTING_BRANCH_LOCATION_MODIFY		= 281;
	public static final long FUNC_SETTING_BRANCH_LOCATION_APPROVAL 		= 282;
	public static final long FUNC_SETTING_BRANCH_LOCATION_SUSPENSION 	= 283;
	
	
	public static final long FUNC_SETTING_SUB_BRANCH				= 7000;
	public static final long FUNC_SETTING_SUB_BRANCH_LIST			= 7001;
	public static final long FUNC_SETTING_SUB_BRANCH_CREATE			= 7002;
	public static final long FUNC_SETTING_SUB_BRANCH_MODIFY			= 7003;
	public static final long FUNC_SETTING_SUB_BRANCH_APPROVAL 		= 7004;
	public static final long FUNC_SETTING_SUB_BRANCH_SUSPENSION 	= 7005;
	
	// Exception Handling
	public static final long FUNC_SETTING_EXCEPTION_HANDLING			= 284;
	public static final long FUNC_SETTING_EXCEPTION_HANDLING_LIST		= 285;
	public static final long FUNC_SETTING_EXCEPTION_HANDLING_MODIFY		= 286;
	public static final long FUNC_SETTING_EXCEPTION_HANDLING_APPROVAL 	= 287;
	
	public static final long FUNC_SETTING_ARCHIVE = 288;
	public static final long FUNC_SETTING_ARCHIVE_LIST = 289;
	public static final long FUNC_SETTING_ARCHIVE_CREATE = 290;
	public static final long FUNC_SETTING_ARCHIVE_CANCEL = 291;
	
	public static final long FUNC_SECURITY = 300;
	
	public static final long FUNC_SECURITY_INTERNAL_USER = 310;
	public static final long FUNC_SECURITY_INTERNAL_USER_LIST = 311;
	public static final long FUNC_SECURITY_INTERNAL_USER_CREATE = 312;
	public static final long FUNC_SECURITY_INTERNAL_USER_MODIFY = 313;
	public static final long FUNC_SECURITY_INTERNAL_USER_SUSPENSION = 314;
	public static final long FUNC_SECURITY_INTERNAL_USER_APPROVAL = 315;
	public static final long FUNC_SECURITY_INTERNAL_USER_LOCK_UNLOCK = 316;
	public static final long FUNC_SECURITY_INTERNAL_USER_LOCK_UNLOCK_APPROVAL = 3320;
	public static final long FUNC_SECURITY_INTERNAL_USER_RESET_PASSWORD = 317;
	public static final long FUNC_SECURITY_INTERNAL_USER_RESET_PASSWORD_APPROVAL = 3321;
	public static final long FUNC_SECURITY_INTERNAL_USER_FORCE_LOGOUT = 318;
	public static final long FUNC_SECURITY_INTERNAL_USER_VIEW_DORMAN = 319;
	public static final long FUNC_SECURITY_INTERNAL_USER_DELETE = 3322;
	public static final long FUNC_SECURITY_INTERNAL_USER_DELETE_APPROVAL = 3323;
	public static final long FUNC_SECURITY_INTERNAL_USER_RESET_IMSI = 3324;
	
	public static final long FUNC_SECURITY_ROLE = 320;
	public static final long FUNC_SECURITY_ROLE_LIST = 321;
	public static final long FUNC_SECURITY_ROLE_CREATE = 322;
	public static final long FUNC_SECURITY_ROLE_MODIFY = 323;
	public static final long FUNC_SECURITY_ROLE_SUSPENSION = 324;
	public static final long FUNC_SECURITY_ROLE_APPROVAL = 325;
	
	public static final long FUNC_SECURITY_PARAMETER = 330;
	public static final long FUNC_SECURITY_PARAMETER_LIST = 331;
	public static final long FUNC_SECURITY_PARAMETER_MODIFY = 332;
	public static final long FUNC_SECURITY_PARAMETER_APPROVAL = 333;
	
	/* Force Fully Active */
	public static final long FUNC_FORCE_FULLY_ACTIVE			= 400; 
	public static final long FUNC_FORCE_FULLY_ACTIVE_LIST		= 401; 
	public static final long FUNC_FORCE_FULLY_ACTIVE_SUBMIT		= 402; 

	public static final long FUNC_FORCE_APPROVE_CMS					= 440; 
	public static final long FUNC_FORCE_APPROVE_CMS_LIST			= 441; 
	public static final long FUNC_FORCE_APPROVE_CMS_SUBMIT			= 442;
	
	public static final long FUNC_MY_TASK = 600;
	
	public static final long FUNC_MY_TASK_MASTER_DATA_APPROVAL = 610;
	
	public static final long FUNC_VERIFIER = 701;
	public static final long FUNC_VERIFIER_LIST = 702;
	public static final long FUNC_VERIFIER_CREATE = 703;//tidak kepake
	public static final long FUNC_VERIFIER_MODIFY = 704;
	public static final long FUNC_VERIFIER_APPROVAL = 705;
	public static final long FUNC_VERIFIER_SUSPENSION = 706; // sebagai reject
	
	public static final long FUNC_EDD_APPROVAL = 707;
	public static final long FUNC_EDD_APPROVAL_LIST = 708;
	public static final long FUNC_EDD_APPROVAL_APPROVAL = 709;
	
	public static final long FUNC_PRE_EDD_APPROVAL = 760;
	public static final long FUNC_PRE_EDD_APPROVAL_LIST = 761;
	public static final long FUNC_PRE_EDD_APPROVAL_APPROVAL = 762;
	
	public static final long FUNC_EFORM_APPROVAL = 720;
	public static final long FUNC_EFORM_APPROVAL_LIST = 721;
	public static final long FUNC_EFORM_APPROVAL_APPROVAL = 722;
	
	public static final long FUNC_CALLBACK = 740;
	public static final long FUNC_CALLBACK_LIST = 741;
	public static final long FUNC_CALLBACK_CALLBACK = 742;
	
	public static final long FUNC_TBO_MAINTENANCE = 750;
	public static final long FUNC_TBO_MAINTENANCE_LIST = 751;
	public static final long FUNC_TBO_MAINTENANCE_RELEASE = 752;
	
	public static final long FUNC_DEVIASI = 780;
	public static final long FUNC_DEVIASI_LIST = 781;
	public static final long FUNC_DEVIASI_APPROVAL = 782;
	
	public static final long FUNC_WORKFLOW_MONITORING = 699;
	
	public static final long FUNC_WORK_IN_PROGRESS = 710;
	public static final long FUNC_WORK_IN_PROGRESS_LIST = 711;
	public static final long FUNC_WORK_IN_PROGRESS_MODIFY = 712;
	
	public static final long FUNC_LEADS = 730;
	public static final long FUNC_LEADS_LIST = 731;
	
	public static final long FUNC_REPORT = 801;
	public static final long FUNC_REPORT_ACQUISITION = 802;
	public static final long FUNC_REPORT_ACQUISITION_PDF = 803;
	public static final long FUNC_REPORT_ACQUISITION_XLS = 804;
	
	public static final long FUNC_REPORT_SUBMIT_FORM = 805;
	public static final long FUNC_REPORT_SUBMIT_FORM_PDF = 806;
	public static final long FUNC_REPORT_SUBMIT_FORM_XLS = 807;
	
	public static final long FUNC_REPORT_FINISHED_FORM = 808;
	public static final long FUNC_REPORT_FINISHED_FORM_PDF = 809;
	public static final long FUNC_REPORT_FINISHED_FORM_XLS = 810;
	
	public static final long FUNC_REPORT_OUTSTANDING_FORM = 811;
	public static final long FUNC_REPORT_OUTSTANDING_FORM_PDF = 812;
	public static final long FUNC_REPORT_OUTSTANDING_FORM_XLS = 813;
	
	public static final long FUNC_REPORT_REJECTED_FORM = 814;
	public static final long FUNC_REPORT_REJECTED_FORM_PDF = 815;
	public static final long FUNC_REPORT_REJECTED_FORM_XLS = 816;
	
	public static final long FUNC_REPORT_RETURN_REPAIR_FORM = 817;
	public static final long FUNC_REPORT_RETURN_REPAIR_FORM_PDF = 818;
	public static final long FUNC_REPORT_RETURN_REPAIR_FORM_XLS = 819;
	
	public static final long FUNC_REPORT_PRODUCTIVITY = 820;
	public static final long FUNC_REPORT_PRODUCTIVITY_PDF = 821;
	public static final long FUNC_REPORT_PRODUCTIVITY_XLS = 822;
	
	public static final long FUNC_REPORT_SLA = 823;
	public static final long FUNC_REPORT_SLA_PDF = 824;
	public static final long FUNC_REPORT_SLA_XLS = 825;
	
	public static final long FUNC_REPORT_USER = 826;
	public static final long FUNC_REPORT_USER_PDF = 827;
	public static final long FUNC_REPORT_USER_XLS = 828;
	
	public static final long FUNC_REPORT_TBO = 829;
	public static final long FUNC_REPORT_TBO_PDF = 830;
	public static final long FUNC_REPORT_TBO_XLS = 831;
	
	public static final long FUNC_REPORT_FINAL_DAY = 832;
	public static final long FUNC_REPORT_FINAL_DAY_PDF = 833;
	public static final long FUNC_REPORT_FINAL_DAY_XLS = 834;
	
	public static final long FUNC_REPORT_FULLY_ACTIVE = 835;
	public static final long FUNC_REPORT_FULLY_ACTIVE_PDF = 836;
	public static final long FUNC_REPORT_FULLY_ACTIVE_XLS = 837;
	
	public static final long FUNC_REPORT_SEMI_ACTIVE = 838;
	public static final long FUNC_REPORT_SEMI_ACTIVE_PDF = 839;
	public static final long FUNC_REPORT_SEMI_ACTIVE_XLS = 840;
	
	public static final long FUNC_REPORT_DOUBLE_CIF = 841;
	public static final long FUNC_REPORT_DOUBLE_CIF_PDF = 842;
	public static final long FUNC_REPORT_DOUBLE_CIF_XLS = 843;
	
	public static final long FUNC_REPORT_FORCE_FULLY_ACTIVE = 847;
	public static final long FUNC_REPORT_FORCE_FULLY_ACTIVE_PDF = 848;
	public static final long FUNC_REPORT_FORCE_FULLY_ACTIVE_XLS = 849;
	
	public static final long FUNC_HOME_FO = 3000;
	
	public static final long FUNC_VIEW_OWN_ACTIVITY = 3300;
	public static final long FUNC_VIEW_OWN_ACTIVITY_LIST = 3301;
	public static final long FUNC_VIEW_OWN_ACTIVITY_PDF = 3302;
	public static final long FUNC_VIEW_OWN_ACTIVITY_XLS = 3303;
	
	public static final long FUNC_FO_VIEW_OWN_ACTIVITY = 3310;
	public static final long FUNC_FO_VIEW_OWN_ACTIVITY_LIST = 3311;
	
	public static final long FUNC_MOBILE								= 500;
	public static final long FUNC_MOBILE_PURNA_SAVING					= 510;
	public static final long FUNC_MOBILE_CARD_ACTIVATION				= 520;
	
	public static final long FUNC_MASTER_DATA_LORINA					= 4000;
	public static final long FUNC_MASTER_DATA_LORINA_PARTNER_CODE_LIST	= 4040;
	/*
	public static final long FUNC_SETTING_PRODUCT_TYPE					= 4020;
	public static final long FUNC_SETTING_PRODUCT_TYPE_LIST				= 4021;
	public static final long FUNC_SETTING_PRODUCT_TYPE_CREATE			= 4022;
	public static final long FUNC_SETTING_PRODUCT_TYPE_MODIFY			= 4023;
	public static final long FUNC_SETTING_PRODUCT_TYPE_APPROVAL 		= 4024;
	public static final long FUNC_SETTING_PRODUCT_TYPE_SUSPENSION		= 4025;
//	public static final long FUNC_MASTER_DATA_LORINA_PARTNER_CODE		= 4040;
	*/
	
	/* Report Purna */
//	public static final long FUNC_REPORT_PURNA = 8000;
//	public static final long FUNC_REPORT_HARIAN_PEMBUKAAN_REKENING_PURNA = 8010;
//	public static final long FUNC_REPORT_HARIAN_PEMBUKAAN_REKENING_PURNA_PDF = 8011;
//	public static final long FUNC_REPORT_HARIAN_PEMBUKAAN_REKENING_PURNA_XLS = 8012;
//
//	public static final long FUNC_REPORT_PEMBUKAAN_REKENING_PER_CABANG_PURNA = 8020;
//	public static final long FUNC_REPORT_PEMBUKAAN_REKENING_PER_CABANG_PURNA_PDF = 8021;
//	public static final long FUNC_REPORT_PEMBUKAAN_REKENING_PER_CABANG_PURNA_XLS = 8022;
//	
//	public static final long FUNC_REPORT_REKENING_SUDAH_FULLY_ACTIVE_REJECTED_PURNA = 8030;
//	public static final long FUNC_REPORT_REKENING_SUDAH_FULLY_ACTIVE_REJECTED_PURNA_PDF = 8031;
//	public static final long FUNC_REPORT_REKENING_SUDAH_FULLY_ACTIVE_REJECTED_PURNA_XLS = 8032;
//
//	public static final long FUNC_REPORT_PRODUKTIVITAS_PURNA = 8040;
//	public static final long FUNC_REPORT_PRODUKTIVITAS_PURNA_PDF = 8041;
//	public static final long FUNC_REPORT_PRODUKTIVITAS_PURNA_XLS = 8042;
//	
//	public static final long FUNC_REPORT_DOUBLE_CIF_PURNA = 8050;
//	public static final long FUNC_REPORT_DOUBLE_CIF_PURNA_PDF = 8051;
//	public static final long FUNC_REPORT_DOUBLE_CIF_PURNA_XLS = 8052;
//
//	public static final long FUNC_REPORT_TBO_PURNA = 8060;
//	public static final long FUNC_REPORT_TBO_PURNA_PDF = 8061;
//	public static final long FUNC_REPORT_TBO_PURNA_XLS = 8062;
//
//	public static final long FUNC_REPORT_AKHIR_HARI_UNTUK_REKONSILIASI_PURNA = 8070;
//	public static final long FUNC_REPORT_AKHIR_HARI_UNTUK_REKONSILIASI_PURNA_PDF = 8071;
//	public static final long FUNC_REPORT_AKHIR_HARI_UNTUK_REKONSILIASI_PURNA_XLS = 8072;
//
//	public static final long FUNC_REPORT_SLA_PURNA = 8080;
//	public static final long FUNC_REPORT_SLA_PURNA_PDF = 8081;
//	public static final long FUNC_REPORT_SLA_PURNA_XLS = 8082;
//
//	public static final long FUNC_REPORT_RETURN_REPAIR_SMO_CA_PURNA = 8090;
//	public static final long FUNC_REPORT_RETURN_REPAIR_SMO_CA_PURNA_PDF = 8091;
//	public static final long FUNC_REPORT_RETURN_REPAIR_SMO_CA_PURNA_XLS = 8092;
//	
//	public static final long FUNC_REPORT_FORCE_FULLY_ACTIVE_PURNA = 8100;
//	public static final long FUNC_REPORT_FORCE_FULLY_ACTIVE_PURNA_PDF = 8101;
//	public static final long FUNC_REPORT_FORCE_FULLY_ACTIVE_PURNA_XLS = 8102;
	
	
	// Report for Purna
	public static final long FUNC_REPORT_PURNA = 8000;
	
	public static final long FUNC_REPORT_SUBMIT_FORM_PURNA = 8010;
	public static final long FUNC_REPORT_SUBMIT_FORM_PURNA_PDF = 8011;
	public static final long FUNC_REPORT_SUBMIT_FORM_PURNA_XLS = 8012;
	
	public static final long FUNC_REPORT_SEMI_ACTIVE_PURNA = 8020;
	public static final long FUNC_REPORT_SEMI_ACTIVE_PURNA_PDF = 8021;
	public static final long FUNC_REPORT_SEMI_ACTIVE_PURNA_XLS = 8022;

	public static final long FUNC_REPORT_FULLY_ACTIVE_PURNA = 8030;
	public static final long FUNC_REPORT_FULLY_ACTIVE_PURNA_PDF = 8031;
	public static final long FUNC_REPORT_FULLY_ACTIVE_PURNA_XLS = 8032;
	
	public static final long FUNC_REPORT_PRODUCTIVITY_PURNA = 8040;
	public static final long FUNC_REPORT_PRODUCTIVITY_PURNA_PDF = 8041;
	public static final long FUNC_REPORT_PRODUCTIVITY_PURNA_XLS = 8042;

	public static final long FUNC_REPORT_DOUBLE_CIF_PURNA = 8050;
	public static final long FUNC_REPORT_DOUBLE_CIF_PURNA_PDF = 8051;
	public static final long FUNC_REPORT_DOUBLE_CIF_PURNA_XLS = 8052;
	
	public static final long FUNC_REPORT_TBO_PURNA = 8060;
	public static final long FUNC_REPORT_TBO_PURNA_PDF = 8061;
	public static final long FUNC_REPORT_TBO_PURNA_XLS = 8062;
	
	public static final long FUNC_REPORT_FINAL_DAY_PURNA = 8070;
	public static final long FUNC_REPORT_FINAL_DAY_PURNA_PDF = 8071;
	public static final long FUNC_REPORT_FINAL_DAY_PURNA_XLS = 8072;
	
	public static final long FUNC_REPORT_SLA_PURNA = 8080;
	public static final long FUNC_REPORT_SLA_PURNA_PDF = 8081;
	public static final long FUNC_REPORT_SLA_PURNA_XLS = 8082;
	
	public static final long FUNC_REPORT_RETURN_REPAIR_SMO_CA_PURNA = 8090;
	public static final long FUNC_REPORT_RETURN_REPAIR_SMO_CA_PURNA_PDF = 8091;
	public static final long FUNC_REPORT_RETURN_REPAIR_SMO_CA_PURNA_XLS = 8092;
	
	public static final long FUNC_REPORT_FORCE_FULLY_ACTIVE_PURNA = 8100;
	public static final long FUNC_REPORT_FORCE_FULLY_ACTIVE_PURNA_PDF = 8101;
	public static final long FUNC_REPORT_FORCE_FULLY_ACTIVE_PURNA_XLS = 8102;
	
	
	//use for master data approval
	//please add into this array with sorted value
	public static final long FUNC_MAKER_ARR[] = {
		FUNC_SETTING_SYSPARAM_MODIFY, // 212
		FUNC_SETTING_HOLIDAY_CREATE, //222
		FUNC_SETTING_HOLIDAY_MODIFY, //223
		FUNC_SETTING_CONTENT_MANAGER_CREATE, //232
		FUNC_SETTING_CONTENT_MANAGER_MODIFY, //233
		FUNC_SETTING_CONTENT_MANAGER_SUSPENSION, //235
		FUNC_SETTING_WORKFLOW_PARAMETER_MODIFY, //242
		FUNC_SETTING_RISK_PROFILER_MODIFY, // 252
		FUNC_SETTING_LOOKUP_CREATE,  // 262
		FUNC_SETTING_LOOKUP_MODIFY, // 263
		FUNC_SETTING_LOOKUP_SUSPENSION, // 265
		FUNC_SETTING_REGION_CREATE, // 268
		FUNC_SETTING_REGION_MODIFY, // 269
		FUNC_SETTING_REGION_SUSPENSION, // 271
		FUNC_SETTING_AREA_CREATE, // 274
		FUNC_SETTING_AREA_MODIFY, // 275
		FUNC_SETTING_AREA_SUSPENSION, // 277
		FUNC_SETTING_BRANCH_LOCATION_CREATE, // 280
		FUNC_SETTING_BRANCH_LOCATION_MODIFY, // 281
		FUNC_SETTING_BRANCH_LOCATION_SUSPENSION, // 283
		FUNC_SETTING_EXCEPTION_HANDLING_MODIFY, // 286
		FUNC_SETTING_ARCHIVE_CREATE, //290
		FUNC_SETTING_ARCHIVE_CANCEL, // 291
		FUNC_SECURITY_INTERNAL_USER_CREATE, //  312
		FUNC_SECURITY_INTERNAL_USER_MODIFY, // 313
		FUNC_SECURITY_INTERNAL_USER_SUSPENSION, // 314
		FUNC_SECURITY_INTERNAL_USER_LOCK_UNLOCK, // 316
		FUNC_SECURITY_INTERNAL_USER_RESET_PASSWORD, // 317
		FUNC_SECURITY_ROLE_CREATE, // 322
		FUNC_SECURITY_ROLE_MODIFY, // 323
		FUNC_SECURITY_ROLE_SUSPENSION, // 324
		FUNC_SECURITY_PARAMETER_MODIFY, // 332
		FUNC_VERIFIER_CREATE, // 703
		FUNC_VERIFIER_MODIFY, // 704
		FUNC_VERIFIER_SUSPENSION, // 706
		FUNC_SECURITY_INTERNAL_USER_DELETE, // 3322
		FUNC_SETTING_SUB_BRANCH_CREATE,
		FUNC_SETTING_SUB_BRANCH_MODIFY,
		FUNC_SETTING_SUB_BRANCH_APPROVAL,
		FUNC_SETTING_SUB_BRANCH_SUSPENSION,
//		FUNC_SETTING_PRODUCT_TYPE_CREATE, // 268
//		FUNC_SETTING_PRODUCT_TYPE_MODIFY, // 269
//		FUNC_SETTING_PRODUCT_TYPE_APPROVAL,
//		FUNC_SETTING_PRODUCT_TYPE_SUSPENSION // 271
	};
	
	public static final Map<Long,Long[]> FUNC_BANK_MAKER_MAP = new HashMap<Long,Long[]>();
	static{
		FUNC_BANK_MAKER_MAP.put(FUNC_SETTING, new Long[]{
				FUNC_SETTING_SYSPARAM_LIST, // 211
				FUNC_SETTING_SYSPARAM_MODIFY, // 212
				FUNC_SETTING_HOLIDAY_LIST, //221
				FUNC_SETTING_HOLIDAY_MODIFY, //223
				FUNC_SETTING_CONTENT_MANAGER_LIST, //231
				FUNC_SETTING_CONTENT_MANAGER_CREATE, //232
				FUNC_SETTING_CONTENT_MANAGER_MODIFY, //233
				FUNC_SETTING_CONTENT_MANAGER_SUSPENSION, //235
				FUNC_SETTING_WORKFLOW_PARAMETER_LIST, //241
				FUNC_SETTING_WORKFLOW_PARAMETER_MODIFY, //242
				FUNC_SETTING_RISK_PROFILER_LIST, //251
				FUNC_SETTING_RISK_PROFILER_MODIFY, //252
				FUNC_SETTING_LOOKUP_LIST, // 261 
				FUNC_SETTING_LOOKUP_CREATE, // 262
				FUNC_SETTING_LOOKUP_MODIFY, // 263
				FUNC_SETTING_LOOKUP_SUSPENSION, // 265
				FUNC_SETTING_REGION_LIST, // 267
				FUNC_SETTING_REGION_CREATE, // 268
				FUNC_SETTING_REGION_MODIFY, // 269
				FUNC_SETTING_REGION_SUSPENSION, 
				FUNC_SETTING_AREA_LIST, // 273
				FUNC_SETTING_AREA_CREATE, // 274
				FUNC_SETTING_AREA_MODIFY, // 275
				FUNC_SETTING_AREA_SUSPENSION, // 277
				FUNC_SETTING_BRANCH_LOCATION_CREATE, // 280
				FUNC_SETTING_BRANCH_LOCATION_MODIFY, // 281
				FUNC_SETTING_BRANCH_LOCATION_SUSPENSION, // 283
				FUNC_SETTING_EXCEPTION_HANDLING_MODIFY, //286
				FUNC_SETTING_ARCHIVE_LIST, // 289
				FUNC_SETTING_ARCHIVE_CREATE, // 290
				FUNC_SETTING_ARCHIVE_CANCEL, // 291
				FUNC_SETTING_SUB_BRANCH_CREATE,
				FUNC_SETTING_SUB_BRANCH_MODIFY,
				FUNC_SETTING_SUB_BRANCH_APPROVAL,
				FUNC_SETTING_SUB_BRANCH_SUSPENSION,
//				FUNC_SETTING_PRODUCT_TYPE_LIST, // 267
//				FUNC_SETTING_PRODUCT_TYPE_CREATE, // 268
//				FUNC_SETTING_PRODUCT_TYPE_MODIFY, // 269
//				FUNC_SETTING_PRODUCT_TYPE_SUSPENSION 
				});
		FUNC_BANK_MAKER_MAP.put(FUNC_SECURITY, new Long[]{
				FUNC_SECURITY_INTERNAL_USER_LIST, // 311
				FUNC_SECURITY_INTERNAL_USER_CREATE, // 312
				FUNC_SECURITY_INTERNAL_USER_MODIFY, // 313
				FUNC_SECURITY_INTERNAL_USER_SUSPENSION, // 314 
				FUNC_SECURITY_INTERNAL_USER_LOCK_UNLOCK, // 316
				FUNC_SECURITY_INTERNAL_USER_RESET_PASSWORD, // 317
		
				FUNC_SECURITY_ROLE_LIST, // 321
				FUNC_SECURITY_ROLE_CREATE, // 322
				FUNC_SECURITY_ROLE_MODIFY, // 323
				FUNC_SECURITY_PARAMETER_LIST, // 331
				FUNC_SECURITY_PARAMETER_MODIFY, // 332
				FUNC_SECURITY_INTERNAL_USER_DELETE // 3322
				});
		FUNC_BANK_MAKER_MAP.put(FUNC_WORKFLOW_MONITORING, new Long[]{
				FUNC_WORK_IN_PROGRESS, // 710
				FUNC_LEADS // 720
				});
		FUNC_BANK_MAKER_MAP.put(FUNC_REPORT, new Long[]{
				FUNC_REPORT_ACQUISITION, 
				FUNC_REPORT_FINISHED_FORM,
				FUNC_REPORT_OUTSTANDING_FORM,
				FUNC_REPORT_PRODUCTIVITY,
				FUNC_REPORT_REJECTED_FORM,
				FUNC_REPORT_RETURN_REPAIR_FORM,
				FUNC_REPORT_SLA,
				FUNC_REPORT_TBO,
				FUNC_REPORT_FINAL_DAY,
				FUNC_REPORT_FULLY_ACTIVE,
				FUNC_REPORT_SEMI_ACTIVE,
				FUNC_REPORT_SUBMIT_FORM,
				FUNC_REPORT_USER,
				FUNC_REPORT_DOUBLE_CIF,
				FUNC_REPORT_FORCE_FULLY_ACTIVE
				});
		FUNC_BANK_MAKER_MAP.put(FUNC_REPORT_PURNA, new Long[]{
				FUNC_REPORT_SUBMIT_FORM_PURNA,
				FUNC_REPORT_SEMI_ACTIVE_PURNA,
				FUNC_REPORT_FULLY_ACTIVE_PURNA,
				FUNC_REPORT_PRODUCTIVITY_PURNA,
				FUNC_REPORT_DOUBLE_CIF_PURNA,
				FUNC_REPORT_TBO_PURNA,
				FUNC_REPORT_FINAL_DAY_PURNA,
				FUNC_REPORT_SLA_PURNA,
				FUNC_REPORT_RETURN_REPAIR_SMO_CA_PURNA,
				FUNC_REPORT_FORCE_FULLY_ACTIVE_PURNA
				});
		FUNC_BANK_MAKER_MAP.put(FUNC_MASTER_DATA_LORINA, new Long[]{
//				FUNC_SETTING_PRODUCT_TYPE_LIST,
				FUNC_MASTER_DATA_LORINA_PARTNER_CODE_LIST //4041
				});
	}
	
	//use for master data approval
	//please add into this array with sorted value
	public static final long FUNC_APPROVAL_ARR[] = {
		FUNC_SETTING_SYSPARAM_APPROVAL, // 213
		FUNC_SETTING_HOLIDAY_APPROVAL, //224
		FUNC_SETTING_CONTENT_MANAGER_APPROVAL, //234
		FUNC_SETTING_WORKFLOW_PARAMETER_APPROVAL, //243
		FUNC_SETTING_LOOKUP_APPROVAL, // 264
		FUNC_SETTING_REGION_APPROVAL,// 270
		FUNC_SETTING_AREA_APPROVAL,// 276
		FUNC_SETTING_BRANCH_LOCATION_APPROVAL,// 282
		FUNC_SETTING_EXCEPTION_HANDLING_APPROVAL, //287
		FUNC_SECURITY_INTERNAL_USER_APPROVAL, // 315
		FUNC_SECURITY_ROLE_APPROVAL, // 325
		FUNC_SECURITY_PARAMETER_APPROVAL, // 333
		FUNC_VERIFIER_APPROVAL, // 705 
		FUNC_SECURITY_INTERNAL_USER_LOCK_UNLOCK_APPROVAL, // 3320
		FUNC_SECURITY_INTERNAL_USER_RESET_PASSWORD_APPROVAL, // 3321
		FUNC_SECURITY_INTERNAL_USER_DELETE_APPROVAL, // 3323
		FUNC_SETTING_SUB_BRANCH_APPROVAL,
//		FUNC_SETTING_PRODUCT_TYPE_APPROVAL// 270
		};
	
	public static final Map<Long,Long> FUNC_BANK_MY_TASK_MAP = new HashMap<Long,Long>();
	static{
		for (Long makerFunction : FUNC_MAKER_ARR) {
			FUNC_BANK_MY_TASK_MAP.put(makerFunction, FUNC_MY_TASK_MASTER_DATA_APPROVAL);
		}
		for (Long approverFunction : FUNC_APPROVAL_ARR) {
			FUNC_BANK_MY_TASK_MAP.put(approverFunction, FUNC_MY_TASK_MASTER_DATA_APPROVAL);
		}
	}
	
	public static boolean checkingFunction(String functionName, int functionNumber){
		if(functionName!=null){
			return (Long)ReflectionFunction.getPropertyValue(AccessibilityConstant.class, functionName)==functionNumber?true:false;
		}
		return false;
	}
	
	public static final Map<Long, String> auditTrailAccessMap = new HashMap<Long, String>();
	static{
		auditTrailAccessMap.put(FUNC_SETTING_SYSPARAM_LIST, IAuditTrailConstant.VSYP);
		auditTrailAccessMap.put(FUNC_SETTING_SYSPARAM_MODIFY, IAuditTrailConstant.ESYP);
		auditTrailAccessMap.put(FUNC_SETTING_SYSPARAM_APPROVAL, IAuditTrailConstant.ASYP);	
		
		auditTrailAccessMap.put(FUNC_SETTING_HOLIDAY_LIST, IAuditTrailConstant.VHOL);
		auditTrailAccessMap.put(FUNC_SETTING_HOLIDAY_MODIFY, IAuditTrailConstant.EHOL);
		auditTrailAccessMap.put(FUNC_SETTING_HOLIDAY_APPROVAL, IAuditTrailConstant.AHOL);	

		auditTrailAccessMap.put(FUNC_SETTING_RISK_PROFILER_LIST, IAuditTrailConstant.VRISK);
		auditTrailAccessMap.put(FUNC_SETTING_RISK_PROFILER_MODIFY, IAuditTrailConstant.ERISK);
		
		auditTrailAccessMap.put(FUNC_SETTING_LOOKUP_LIST, IAuditTrailConstant.VREF);
		auditTrailAccessMap.put(FUNC_SETTING_LOOKUP_CREATE, IAuditTrailConstant.CREF);
		auditTrailAccessMap.put(FUNC_SETTING_LOOKUP_MODIFY, IAuditTrailConstant.EREF);
		auditTrailAccessMap.put(FUNC_SETTING_LOOKUP_APPROVAL, IAuditTrailConstant.AREF);
		auditTrailAccessMap.put(FUNC_SETTING_LOOKUP_SUSPENSION, IAuditTrailConstant.SUREF);
		
		auditTrailAccessMap.put(FUNC_SETTING_ARCHIVE_LIST, IAuditTrailConstant.VARC);
		auditTrailAccessMap.put(FUNC_SETTING_ARCHIVE_CREATE, IAuditTrailConstant.CARC);
		auditTrailAccessMap.put(FUNC_SETTING_ARCHIVE_CANCEL, IAuditTrailConstant.CCARC);
		
		auditTrailAccessMap.put(FUNC_VERIFIER_LIST, IAuditTrailConstant.VVER);
		auditTrailAccessMap.put(FUNC_VERIFIER_CREATE, IAuditTrailConstant.CVER);
		auditTrailAccessMap.put(FUNC_VERIFIER_MODIFY, IAuditTrailConstant.EVER);
		auditTrailAccessMap.put(FUNC_VERIFIER_APPROVAL, IAuditTrailConstant.AVER);
		auditTrailAccessMap.put(FUNC_VERIFIER_SUSPENSION, IAuditTrailConstant.SUVER);
		
		auditTrailAccessMap.put(FUNC_EDD_APPROVAL_LIST, IAuditTrailConstant.VEDD);
		auditTrailAccessMap.put(FUNC_EDD_APPROVAL_APPROVAL, IAuditTrailConstant.AEDD);

		auditTrailAccessMap.put(FUNC_PRE_EDD_APPROVAL_LIST, IAuditTrailConstant.PVEDD);
		auditTrailAccessMap.put(FUNC_PRE_EDD_APPROVAL_APPROVAL, IAuditTrailConstant.PAEDD);		
		
		auditTrailAccessMap.put(FUNC_EFORM_APPROVAL_LIST, IAuditTrailConstant.VEFA);
		auditTrailAccessMap.put(FUNC_EFORM_APPROVAL_APPROVAL, IAuditTrailConstant.AEFA);
		
		auditTrailAccessMap.put(FUNC_CALLBACK, IAuditTrailConstant.VCB);
		auditTrailAccessMap.put(FUNC_CALLBACK_CALLBACK, IAuditTrailConstant.CCB);
		
		auditTrailAccessMap.put(FUNC_TBO_MAINTENANCE_LIST, IAuditTrailConstant.VTM);
		auditTrailAccessMap.put(FUNC_TBO_MAINTENANCE_RELEASE, IAuditTrailConstant.RTM);
		
		auditTrailAccessMap.put(FUNC_SETTING_CONTENT_MANAGER_LIST, IAuditTrailConstant.VCM);
		auditTrailAccessMap.put(FUNC_SETTING_CONTENT_MANAGER_CREATE, IAuditTrailConstant.CCM);
		auditTrailAccessMap.put(FUNC_SETTING_CONTENT_MANAGER_MODIFY, IAuditTrailConstant.ECM);
		auditTrailAccessMap.put(FUNC_SETTING_CONTENT_MANAGER_APPROVAL, IAuditTrailConstant.ACM);
		auditTrailAccessMap.put(FUNC_SETTING_CONTENT_MANAGER_SUSPENSION, IAuditTrailConstant.SUCM);
		
		auditTrailAccessMap.put(FUNC_SECURITY_INTERNAL_USER_LIST, IAuditTrailConstant.VBU);
		auditTrailAccessMap.put(FUNC_SECURITY_INTERNAL_USER_CREATE, IAuditTrailConstant.CBU);
		auditTrailAccessMap.put(FUNC_SECURITY_INTERNAL_USER_MODIFY, IAuditTrailConstant.EINU);
		auditTrailAccessMap.put(FUNC_SECURITY_INTERNAL_USER_SUSPENSION, IAuditTrailConstant.SUBU);
		auditTrailAccessMap.put(FUNC_SECURITY_INTERNAL_USER_APPROVAL, IAuditTrailConstant.ABU);
		auditTrailAccessMap.put(FUNC_SECURITY_INTERNAL_USER_LOCK_UNLOCK, IAuditTrailConstant.LOBU);
		auditTrailAccessMap.put(FUNC_SECURITY_INTERNAL_USER_LOCK_UNLOCK_APPROVAL, IAuditTrailConstant.BULA);
		auditTrailAccessMap.put(FUNC_SECURITY_INTERNAL_USER_RESET_PASSWORD, IAuditTrailConstant.RPBU);
		auditTrailAccessMap.put(FUNC_SECURITY_INTERNAL_USER_RESET_PASSWORD_APPROVAL, IAuditTrailConstant.BURPA);
		auditTrailAccessMap.put(FUNC_SECURITY_INTERNAL_USER_FORCE_LOGOUT, IAuditTrailConstant.FLBU);
		auditTrailAccessMap.put(FUNC_SECURITY_INTERNAL_USER_VIEW_DORMAN, IAuditTrailConstant.SUBU);
		auditTrailAccessMap.put(FUNC_SECURITY_INTERNAL_USER_DELETE, IAuditTrailConstant.DBU);
		auditTrailAccessMap.put(FUNC_SECURITY_INTERNAL_USER_DELETE_APPROVAL, IAuditTrailConstant.DBUA);
		
		auditTrailAccessMap.put(FUNC_SECURITY_ROLE_LIST, IAuditTrailConstant.VROL);
		auditTrailAccessMap.put(FUNC_SECURITY_ROLE_CREATE, IAuditTrailConstant.CROL);
		auditTrailAccessMap.put(FUNC_SECURITY_ROLE_MODIFY, IAuditTrailConstant.EROL);
		auditTrailAccessMap.put(FUNC_SECURITY_ROLE_SUSPENSION, IAuditTrailConstant.SUROL);
		auditTrailAccessMap.put(FUNC_SECURITY_ROLE_APPROVAL, IAuditTrailConstant.AROL);
		
		auditTrailAccessMap.put(FUNC_SECURITY_PARAMETER_LIST, IAuditTrailConstant.VSCP);
		auditTrailAccessMap.put(FUNC_SECURITY_PARAMETER_MODIFY, IAuditTrailConstant.ESCP);
		auditTrailAccessMap.put(FUNC_SECURITY_PARAMETER_APPROVAL, IAuditTrailConstant.ASCP);
		
		auditTrailAccessMap.put(FUNC_WORK_IN_PROGRESS_LIST, IAuditTrailConstant.VTL);
		auditTrailAccessMap.put(FUNC_WORK_IN_PROGRESS_MODIFY, IAuditTrailConstant.ETL);
		
		auditTrailAccessMap.put(FUNC_FORCE_FULLY_ACTIVE_LIST, IAuditTrailConstant.SCFFA);
		auditTrailAccessMap.put(FUNC_FORCE_FULLY_ACTIVE_SUBMIT, IAuditTrailConstant.SBFFA);
	
		auditTrailAccessMap.put(FUNC_FORCE_APPROVE_CMS_LIST, IAuditTrailConstant.SCACMS);
		auditTrailAccessMap.put(FUNC_FORCE_APPROVE_CMS_SUBMIT, IAuditTrailConstant.SBACMS);
	}
	
	public static final long FUNC_ROOT_ARR[] = {
		FUNC_SETTING_SYSPARAM, // 210
		FUNC_SETTING_HOLIDAY, // 220
		FUNC_SETTING_CONTENT_MANAGER, // 230
		FUNC_SETTING_RISK_PROFILER, // 250
		FUNC_SETTING_LOOKUP, // 260
		FUNC_SETTING_REGION, // 266
		FUNC_SETTING_AREA, // 272
		FUNC_SETTING_BRANCH_LOCATION, // 278
		FUNC_SETTING_SUB_BRANCH, // 7000
		FUNC_SETTING_EXCEPTION_HANDLING, // 284
		FUNC_SETTING_ARCHIVE, //288
		FUNC_SECURITY, // 300
		FUNC_SECURITY_INTERNAL_USER, // 310
		FUNC_SECURITY_ROLE, // 320
		FUNC_SECURITY_PARAMETER, // 330
		FUNC_FORCE_FULLY_ACTIVE, //400
		FUNC_FORCE_APPROVE_CMS, //450
		FUNC_MY_TASK, // 600
		FUNC_VERIFIER, // 701
		FUNC_EDD_APPROVAL, // 707
		FUNC_PRE_EDD_APPROVAL, // 760
		FUNC_WORKFLOW_MONITORING, // 709
		FUNC_EFORM_APPROVAL, // 720
		FUNC_REPORT, //801
		FUNC_VIEW_OWN_ACTIVITY, // 3300
		FUNC_MASTER_DATA_LORINA, //4000
		//		FUNC_SETTING_PRODUCT_TYPE,
		FUNC_REPORT_PURNA //8000
		};
	
	
	public static Map<Long,String[]> appFunctionToArrayOfAuditTrail = new HashMap<Long, String[]>();
	static{
		appFunctionToArrayOfAuditTrail.put(AccessibilityConstant.FUNC_LOGIN, new String[]{IAuditTrailConstant.BLGN, IAuditTrailConstant.FLGN});
		appFunctionToArrayOfAuditTrail.put(AccessibilityConstant.FUNC_LOGOUT, new String[]{IAuditTrailConstant.BLGT, IAuditTrailConstant.FLGT});
		appFunctionToArrayOfAuditTrail.put(AccessibilityConstant.FUNC_CHANGE_PASSWORD, new String[]{IAuditTrailConstant.CHAP});
		appFunctionToArrayOfAuditTrail.put(AccessibilityConstant.FUNC_SETTING_SYSPARAM, new String[]{IAuditTrailConstant.VSYP,IAuditTrailConstant.ESYP,IAuditTrailConstant.ASYP,IAuditTrailConstant.RSYP,IAuditTrailConstant.RRSYP,IAuditTrailConstant.RESYP});
		appFunctionToArrayOfAuditTrail.put(AccessibilityConstant.FUNC_SETTING_HOLIDAY, new String[]{IAuditTrailConstant.VHOL,IAuditTrailConstant.EHOL,IAuditTrailConstant.AHOL,IAuditTrailConstant.RHOL,IAuditTrailConstant.RRHOL,IAuditTrailConstant.REHOL, IAuditTrailConstant.CHOL});
		appFunctionToArrayOfAuditTrail.put(AccessibilityConstant.FUNC_SETTING_RISK_PROFILER, new String[]{IAuditTrailConstant.VRISK,IAuditTrailConstant.ERISK});
		appFunctionToArrayOfAuditTrail.put(AccessibilityConstant.FUNC_SETTING_LOOKUP, new String[]{IAuditTrailConstant.VREF,IAuditTrailConstant.VREF,IAuditTrailConstant.CREF,IAuditTrailConstant.EREF,IAuditTrailConstant.AREF,IAuditTrailConstant.RREF,IAuditTrailConstant.RRREF,IAuditTrailConstant.REREF,IAuditTrailConstant.SUREF});
		appFunctionToArrayOfAuditTrail.put(AccessibilityConstant.FUNC_SETTING_CONTENT_MANAGER, new String[]{IAuditTrailConstant.VCM,IAuditTrailConstant.CCM,IAuditTrailConstant.ECM,IAuditTrailConstant.ACM,IAuditTrailConstant.RCM,IAuditTrailConstant.RRCM,IAuditTrailConstant.RECM,IAuditTrailConstant.SUCM});
		appFunctionToArrayOfAuditTrail.put(AccessibilityConstant.FUNC_SECURITY_INTERNAL_USER, new String[]{IAuditTrailConstant.VBU,IAuditTrailConstant.CBU,IAuditTrailConstant.EINU,IAuditTrailConstant.SUBU,IAuditTrailConstant.ABU,IAuditTrailConstant.RBU,IAuditTrailConstant.RRBU,IAuditTrailConstant.REBU,IAuditTrailConstant.LOBU,IAuditTrailConstant.ULBU,IAuditTrailConstant.RPBU,IAuditTrailConstant.FLBU});
		appFunctionToArrayOfAuditTrail.put(AccessibilityConstant.FUNC_SECURITY_ROLE, new String[]{IAuditTrailConstant.VROL,IAuditTrailConstant.CROL,IAuditTrailConstant.EROL,IAuditTrailConstant.DROL,IAuditTrailConstant.SUROL,IAuditTrailConstant.AROL,IAuditTrailConstant.RROL,IAuditTrailConstant.RRROL,IAuditTrailConstant.REROL}); 
		appFunctionToArrayOfAuditTrail.put(AccessibilityConstant.FUNC_SECURITY_PARAMETER, new String[]{IAuditTrailConstant.VSCP,IAuditTrailConstant.ESCP,IAuditTrailConstant.ASCP,IAuditTrailConstant.RSCP,IAuditTrailConstant.RRSCP,IAuditTrailConstant.RESCP}); 	
		appFunctionToArrayOfAuditTrail.put(AccessibilityConstant.FUNC_MY_TASK, new String[]{null});
		appFunctionToArrayOfAuditTrail.put(AccessibilityConstant.FUNC_VIEW_OWN_ACTIVITY, new String[]{null});
		appFunctionToArrayOfAuditTrail.put(AccessibilityConstant.FUNC_SECURITY, new String[]{null});
		appFunctionToArrayOfAuditTrail.put(AccessibilityConstant.FUNC_VERIFIER, new String[]{null});
		appFunctionToArrayOfAuditTrail.put(AccessibilityConstant.FUNC_EDD_APPROVAL, new String[]{null});
		appFunctionToArrayOfAuditTrail.put(AccessibilityConstant.FUNC_EFORM_APPROVAL, new String[]{IAuditTrailConstant.VEFA, IAuditTrailConstant.AEFA});
		appFunctionToArrayOfAuditTrail.put(AccessibilityConstant.FUNC_CALLBACK, new String[]{IAuditTrailConstant.VCB, IAuditTrailConstant.WNCB, IAuditTrailConstant.NACB, IAuditTrailConstant.FCB, IAuditTrailConstant.CCB});
		appFunctionToArrayOfAuditTrail.put(AccessibilityConstant.FUNC_TBO_MAINTENANCE, new String[]{IAuditTrailConstant.VTM, IAuditTrailConstant.RTM});
		appFunctionToArrayOfAuditTrail.put(AccessibilityConstant.FUNC_WORK_IN_PROGRESS, new String[]{IAuditTrailConstant.VTL, IAuditTrailConstant.ETL});
		appFunctionToArrayOfAuditTrail.put(AccessibilityConstant.FUNC_REPORT, new String[]{IAuditTrailConstant.SFREP, IAuditTrailConstant.ACREP, 
													IAuditTrailConstant.FFREP, IAuditTrailConstant.PREP, IAuditTrailConstant.RFREP,
													IAuditTrailConstant.RRREP, IAuditTrailConstant.SLAREP, IAuditTrailConstant.OFREP});
		appFunctionToArrayOfAuditTrail.put(AccessibilityConstant.FUNC_FORCE_FULLY_ACTIVE, new String[]{IAuditTrailConstant.SCFFA, IAuditTrailConstant.SBFFA});
		appFunctionToArrayOfAuditTrail.put(AccessibilityConstant.FUNC_FORCE_APPROVE_CMS, new String[]{IAuditTrailConstant.SCACMS, IAuditTrailConstant.SBACMS});
	}
	
	public static Long[] allAppFunctions = {};
	static{
		allAppFunctions = new Long[appFunctionToArrayOfAuditTrail.size()];
		int i = 0;
		for(Long entry:appFunctionToArrayOfAuditTrail.keySet()){
			allAppFunctions[i++] = entry; 
		}
	}
}
