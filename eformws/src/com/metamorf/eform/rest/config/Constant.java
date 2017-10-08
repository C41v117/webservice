package com.metamorf.eform.rest.config;


public class Constant {

	public static final String SUCCESS 						= "success";
	
	public static final String SUCCESS_JOIN_FILE 			= "success_join";
	
	public static final String ERROR 						= "error";
	
	public static final String ERROR_VERIFY					= "error_verify";
	
	public static final String ERROR_TOKEN					= "error_token";
	
	public static final String CHANGE 						= "change";
	
	public static final String MESSAGE_PROPERTY 			= "message.properties";
		
	/*BELOW RESULT FROM BTPN HOST CODE (WOW) */
	public static class mdwResult{
		public static final String SUCCESS								= "00";
		public static final String USER_INVALID							= "04";
		public static final String TRANSACTION_REPETITION 				= "201";
		public static final String PAYEE_UNKNOWN 						= "39";
		public static final String PAYER_DAILY_LIMIT	 				= "61";
		public static final String PAYER_PAYMENTINSTRUMENT_NO_FUNDS 	= "51";
		public static final String CUSTOMER_INACTIVE	 				= "12";
		public static final String CUSTOMER_NOT_FOUND	 				= "39";
		public static final String TRACING_FLAG_MISSING 				= "6";
		public static final String TIME_OUT				 				= "68";
		public static final String GENERAL_ERROR		 				= "06";
	}
	
	public static class PushNotificationResult{
		public static final String SUCCESS								= "201";
	}
	
	/*BELOW RESULT FROM BTPN HOST CODE*/
	public static class DWHResult{
		public static final String EXISTS			= "1";
		public static final String NOT_EXISTS		= "0";
		public static final String INVALID_DATA 	= "06";
	}
	
	public static class CheckCIFResult{
		public static final String EXISTS			= "1";
		public static final String NOT_EXISTS		= "0";
	}
	
	/*BELOW RESULT FOR BTPN MOBILISER INTERGRATION*/
	public static class LoginResult{
		public static final Integer NO_RESPONSE		= new Integer(-1);
		public static final Integer SUCCESS			= new Integer(0);
		public static final Integer USER_INVALID	= new Integer(1);
		public static final Integer FAILED			= new Integer(2);
	}
	
	public static class ldapResult{
		public static final Integer SUCCESS			= new Integer(0);
		public static final Integer FAILED_CONNECT	= new Integer(1);
		public static final Integer CREDENTIAL		= new Integer(2);
		public static final Integer LDAP_EXCEPTION	= new Integer(3);
	}

}

