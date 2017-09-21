/*
 * Created on Jul 5, 2005
 *
 */
package com.metamorf.eform.common.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface ILookupConstant {
	public static final String CURRENCY_CODE_IDR = "IDR";
	public static final String CURRENCY_CODE_USD = "USD";

	public static final String OTHER = "OTHER";
	public static final String MALE = "M";
	public static final String FEMALE = "F";
	
	public static final class DocMaker {
    	public static final String BUYER = "B";
    	public static final String SELLER = "S";
    	public static final String SELLER_OR_BUYER = "O";
	}
	
	public static final class DealType {
		public static final String LOT = "L";
		public static final String PO  = "P";
		public static final String DO  = "G";
		public static final String GR  = "D";
		public static final String INV = "I";
		public static final String CN  = "CN";
		public static final String DN  = "DN";
		
		public static final String LOT_STRING = "Lot";
		public static final String PO_STRING  = "Purchase Order";
		public static final String DO_STRING  = "Delivery Order";
		public static final String GR_STRING  = "Good Reason";
		public static final String INV_STRING = "Invoice";
		public static final String CN_STRING  = "Credit Note";
		public static final String DN_STRING  = "Debit Note";
		
		public static final Map<String, String> Description = new HashMap<String, String>();
		static {
			Description.put(LOT, LOT_STRING);
			Description.put(PO, PO_STRING);
			Description.put(DO, DO_STRING);
			Description.put(GR, GR_STRING);
			Description.put(INV, INV_STRING);
			Description.put(CN, CN_STRING);
			Description.put(DN, DN_STRING);
		}
	}
	
	 public static class DealPartyType {
    	public static final int SELLER		= 0;
    	public static final int BUYER		= 1;
	 }
	 
	 public static class NotificationType {
    	public static final int INVOICE				= 0;
    	public static final int DISBURSEMENT		= 1;
    	public static final int SETTLEMENT			= 2;
    	public static final int PASSWORD			= 3;
    	
    	public static final String INVOICE_STR		= "Invoice";
    	public static final String DISBURSEMENT_STR	= "Disbursement";
    	public static final String SETTLEMENT_STR	= "Settlement";
    	public static final String PASSWORD_STR		= "Password";
    	
    	public static final Map<Integer, String> Description = new HashMap<Integer, String>();
		static{
			Description.put(INVOICE, INVOICE_STR);
			Description.put(DISBURSEMENT, DISBURSEMENT_STR);
			Description.put(SETTLEMENT, SETTLEMENT_STR);
			Description.put(PASSWORD, PASSWORD_STR);
		}
	 }
	 
	
	public static final class SettlementStatus {
		public static final String APPROVE = "Approve";
		public static final String REPAIR = "Request Repair";
		public static final String RELEASE = "Release";
		public static final String RELEASED = "Released";
		public static final String REJECTED = "Rejected";
	}
	
	public static final class InvoiceStatus {
		public static final String APPROVE = "Approve";
		public static final String REPAIR = "Request Repair";
		public static final String APPROVED = "Approved";
		public static final String REJECTED = "Rejected";
	}
	
	public static final class BPMAction {
		public static final String SUBMIT = "submit";
		public static final String APPROVE = "approve";
		public static final String REPAIR = "repair";
		public static final String REJECT = "reject";
	}
	
	public static final class DisbursementStatus {
		public static final String VERIFY_1 = "Verify before Approve";
		public static final String APPROVE = "Approve";
		public static final String APPROVED = "Approved";
		public static final String VERIFY_2 = "Verify after Approve";
		public static final String BANK_RELEASE = "Bank Release";
		public static final String RELEASE = "Release";  
		public static final String RELEASED = "Released"; 
		public static final String REPAIR = "Request Repair";
		public static final String REJECTED = "Rejected"; 
		
	}
	
	public static class ChargesType {
    	public static final String INTEREST	= "0";
    	public static final String TRANSACTION_FEE = "1";
    	public static final String INTEREST_TRANSACTION_FEE = "2";
    	
    	public static final String INTEREST_STR					= "Interest";
    	public static final String TRANSACTION_FEE_STR			= "Transaction Fee";
    	public static final String INTEREST_TRANSACTION_FEE_STR	= "Interest & Transaction Fee";
    	
    	public static final Map<String, String> Description = new HashMap<String, String>();
		static{
			Description.put(INTEREST, INTEREST_STR);
			Description.put(TRANSACTION_FEE, INTEREST_TRANSACTION_FEE);
			Description.put(INTEREST_TRANSACTION_FEE, INTEREST_TRANSACTION_FEE_STR);
		}
    }
	
	public static class ChargesTypeFee {
    	public static final String PERCENTAGE	= "0";
    	public static final String FIX_AMOUNT = "1";
    }
	
	public static class FeeBasedOnType {
		public static final String DISBURSEMENT_AMOUNT = "0";
		public static final String INVOICE_AMOUNT = "1";
	}
	
	public static class BeneficiaryCategory {
    	public static final String BANK_CENTRAL	= "0";
    	public static final String GOVERNMENT = "1";
    	public static final String INDIVIDUAL = "2";
    	public static final String COMPANY = "3";
    	public static final String NOT_APPLICABLE = "4";
    }
	
	public static class BeneficiaryCitizenship {
    	public static final String CITIZENSHIP	= "0";
    	public static final String NON_CITIZENSHIP = "1";
    	public static final String NOT_APPLICABLE = "2";
    }
	
	public static class BeneficiaryStatus {
    	public static final String RESIDENT	= "0";
    	public static final String NON_RESIDENT = "1";
    	public static final String NOT_APPLICABLE = "2";
    }
	
	public static class DealParty {
    	public static final String SELLER	= "0";
    	public static final String BUYER = "1";
    }
	
	public static class DocumentDetail {
    	public static final String ITEM	= "0";
    	public static final String BULK = "1";
    	public static final String UNIT = "2";
    }
	
	public static class DocumentOrigin {
    	public static final String NONE	= "0";
    	public static final String LOCAL = "1";
    	public static final String IMPORT = "2";
    }
	
	public static class FeeBasedOn {
    	public static final String DISBURSEMENT_AMOUNT	= "0";
    	public static final String INVOICE_AMOUNT = "1";
    }
	
	public static class FinancingSchemeType {
    	public static final String UPFRONT	= "0";
    	public static final String IN_ARREARS = "1";
    }
	
	public static class FinancingType {
    	public static final String SUPPLIER_FINANCING	= "0";
    	public static final String BUYER_FINANCING = "1";
    }
	
	public static class IdenticalStatus {
    	public static final String WITH_BENEFICARY	= "0";
    	public static final String WITHOUT_BENEFICIARY = "1";
    	public static final String NOT_APPLICABLE = "2";
    }
	
	public static class OrganizationDirectory {
		public static final String BLANK = "0";
    	public static final String BIC	= "1";
    	public static final String SSB = "2";
    	public static final String CHIPS	= "3";
    	public static final String FEDWIRE = "4";
    	public static final String OTHER	= "5";
    	public static final String SORTCODE = "6";
    }
	
	public static class TransactionRelationship {
    	public static final String AFFILIATED	= "0";
    	public static final String NON_AFFILIATED = "1";
    	public static final String NOT_APPLICABLE = "2";
    }
	
	public static class YesNo {
    	public static final String NO	= "0";
    	public static final String YES = "1";
    }
	
	public static final class Country{
		public static final String GROUP_NAME = "COUNTRY";

		public static final String INDONESIA = "112";
	}

	public static final class Province{
		public static final String GROUP_NAME = "PROVINCE";
	}
	
	public static final class City{
		public static final String GROUP_NAME = "CITY";
	}
	
	public static class BankType {
    	public static final String RENTAS_GIRO	= "1";
    	public static final String RTGS_SKN 	= "2";
    }
	
	public static class MemberRole {
    	public static final String AS_SELLER	= "0";
    	public static final String AS_BUYER 	= "1";
    }
	
	public static final class MasterDataModule {
		public static final String SYSPA = "SYSPA";		//System Parameter
		public static final String LOOKUP = "LOOKUP";	//Reference
		public static final String SECPA = "SECPA";		//Security Parameter
		public static final String ROL = "ROL";			//Role
		public static final String BUS = "BUS";			//Internal User
		public static final String BULA = "BULA";		//Internal user lock unlock
		public static final String BURPA = "BURPA";		//Internal user reset Password
		public static final String DBUA = "DBUA";		//Internal user delete
		public static final String HST = "HST";			//Holiday
		public static final String REGION = "REGION";	//Region
		public static final String AREA	= "AREA";		//Area
		public static final String BRANCH_LOCATION = "BLOC";
		public static final String FAQ = "FAQ";			//Faq
		public static final String WP = "WP";			//Workflow Parameter
		public static final String EH = "EH"; 			// Exception Handling
		
		public static final Map<String,Long> ApprovalModuleIds = new HashMap<String, Long>();
		static{
			ApprovalModuleIds.put(SYSPA, AccessibilityConstant.FUNC_SETTING_SYSPARAM_APPROVAL);
			ApprovalModuleIds.put(LOOKUP, AccessibilityConstant.FUNC_SETTING_LOOKUP_APPROVAL);
			ApprovalModuleIds.put(SECPA, AccessibilityConstant.FUNC_SECURITY_PARAMETER_APPROVAL);
			ApprovalModuleIds.put(ROL, AccessibilityConstant.FUNC_SECURITY_ROLE_APPROVAL);
			ApprovalModuleIds.put(BUS, AccessibilityConstant.FUNC_SECURITY_INTERNAL_USER_APPROVAL);
			ApprovalModuleIds.put(BULA, AccessibilityConstant.FUNC_SECURITY_INTERNAL_USER_LOCK_UNLOCK_APPROVAL);
			ApprovalModuleIds.put(BURPA, AccessibilityConstant.FUNC_SECURITY_INTERNAL_USER_RESET_PASSWORD_APPROVAL);
			ApprovalModuleIds.put(DBUA, AccessibilityConstant.FUNC_SECURITY_INTERNAL_USER_DELETE_APPROVAL);
			ApprovalModuleIds.put(HST, AccessibilityConstant.FUNC_SETTING_HOLIDAY_APPROVAL);
			ApprovalModuleIds.put(REGION, AccessibilityConstant.FUNC_SETTING_REGION_APPROVAL);
			ApprovalModuleIds.put(AREA, AccessibilityConstant.FUNC_SETTING_AREA_APPROVAL);
			ApprovalModuleIds.put(BRANCH_LOCATION, AccessibilityConstant.FUNC_SETTING_BRANCH_LOCATION_APPROVAL);
			ApprovalModuleIds.put(FAQ, AccessibilityConstant.FUNC_SETTING_CONTENT_MANAGER_APPROVAL);
			ApprovalModuleIds.put(WP, AccessibilityConstant.FUNC_SETTING_WORKFLOW_PARAMETER_APPROVAL);
			ApprovalModuleIds.put(EH, AccessibilityConstant.FUNC_SETTING_EXCEPTION_HANDLING_APPROVAL);
		}
		
		public static final Map<Long, String> ModuleIds = new HashMap<Long, String>();
		static{			
			ModuleIds.put(AccessibilityConstant.FUNC_SETTING_SYSPARAM_MODIFY, SYSPA);
			ModuleIds.put(AccessibilityConstant.FUNC_SETTING_SYSPARAM_APPROVAL, SYSPA);
						
			ModuleIds.put(AccessibilityConstant.FUNC_SETTING_LOOKUP_CREATE, LOOKUP);
			ModuleIds.put(AccessibilityConstant.FUNC_SETTING_LOOKUP_MODIFY, LOOKUP);
			ModuleIds.put(AccessibilityConstant.FUNC_SETTING_LOOKUP_APPROVAL, LOOKUP);
			ModuleIds.put(AccessibilityConstant.FUNC_SETTING_LOOKUP_SUSPENSION, LOOKUP);
			
			ModuleIds.put(AccessibilityConstant.FUNC_SECURITY_PARAMETER_MODIFY, SECPA);
			ModuleIds.put(AccessibilityConstant.FUNC_SECURITY_PARAMETER_APPROVAL, SECPA);
			ModuleIds.put(AccessibilityConstant.FUNC_SECURITY_ROLE_CREATE, ROL);
			ModuleIds.put(AccessibilityConstant.FUNC_SECURITY_ROLE_MODIFY, ROL);
			ModuleIds.put(AccessibilityConstant.FUNC_SECURITY_ROLE_SUSPENSION, ROL);
			ModuleIds.put(AccessibilityConstant.FUNC_SECURITY_ROLE_APPROVAL, ROL);
			ModuleIds.put(AccessibilityConstant.FUNC_SECURITY_INTERNAL_USER_CREATE, BUS);
			ModuleIds.put(AccessibilityConstant.FUNC_SECURITY_INTERNAL_USER_MODIFY, BUS);
			ModuleIds.put(AccessibilityConstant.FUNC_SECURITY_INTERNAL_USER_SUSPENSION, BUS);
			ModuleIds.put(AccessibilityConstant.FUNC_SECURITY_INTERNAL_USER_APPROVAL, BUS);
			ModuleIds.put(AccessibilityConstant.FUNC_SECURITY_INTERNAL_USER_LOCK_UNLOCK, BUS);
			ModuleIds.put(AccessibilityConstant.FUNC_SECURITY_INTERNAL_USER_RESET_PASSWORD, BUS);
			ModuleIds.put(AccessibilityConstant.FUNC_SECURITY_INTERNAL_USER_DELETE, BUS);
			ModuleIds.put(AccessibilityConstant.FUNC_SECURITY_INTERNAL_USER_LOCK_UNLOCK_APPROVAL, BUS);
			ModuleIds.put(AccessibilityConstant.FUNC_SECURITY_INTERNAL_USER_RESET_PASSWORD_APPROVAL, BUS);
			ModuleIds.put(AccessibilityConstant.FUNC_SECURITY_INTERNAL_USER_DELETE_APPROVAL, BUS);
			
			/*ModuleIds.put(AccessibilityConstant.FUNC_SETTING_HOLIDAY_CREATE, HST);
			ModuleIds.put(AccessibilityConstant.FUNC_SETTING_HOLIDAY_MODIFY, HST);
			ModuleIds.put(AccessibilityConstant.FUNC_SETTING_HOLIDAY_APPROVAL, HST);*/
			
			ModuleIds.put(AccessibilityConstant.FUNC_SETTING_REGION_CREATE, REGION);
			ModuleIds.put(AccessibilityConstant.FUNC_SETTING_REGION_MODIFY, REGION);
			ModuleIds.put(AccessibilityConstant.FUNC_SETTING_REGION_APPROVAL, REGION);
			ModuleIds.put(AccessibilityConstant.FUNC_SETTING_REGION_SUSPENSION, REGION);
			
			ModuleIds.put(AccessibilityConstant.FUNC_SETTING_AREA_CREATE, AREA);
			ModuleIds.put(AccessibilityConstant.FUNC_SETTING_AREA_MODIFY, AREA);
			ModuleIds.put(AccessibilityConstant.FUNC_SETTING_AREA_APPROVAL, AREA);
			ModuleIds.put(AccessibilityConstant.FUNC_SETTING_AREA_SUSPENSION, AREA);

			ModuleIds.put(AccessibilityConstant.FUNC_SETTING_BRANCH_LOCATION_CREATE, BRANCH_LOCATION);
			ModuleIds.put(AccessibilityConstant.FUNC_SETTING_BRANCH_LOCATION_MODIFY, BRANCH_LOCATION);
			ModuleIds.put(AccessibilityConstant.FUNC_SETTING_BRANCH_LOCATION_APPROVAL, BRANCH_LOCATION);
			ModuleIds.put(AccessibilityConstant.FUNC_SETTING_BRANCH_LOCATION_SUSPENSION, BRANCH_LOCATION);
			
			ModuleIds.put(AccessibilityConstant.FUNC_SETTING_CONTENT_MANAGER_CREATE, FAQ);
			ModuleIds.put(AccessibilityConstant.FUNC_SETTING_CONTENT_MANAGER_MODIFY, FAQ);
			ModuleIds.put(AccessibilityConstant.FUNC_SETTING_CONTENT_MANAGER_APPROVAL, FAQ);
			ModuleIds.put(AccessibilityConstant.FUNC_SETTING_CONTENT_MANAGER_SUSPENSION, FAQ);
			
			ModuleIds.put(AccessibilityConstant.FUNC_SETTING_WORKFLOW_PARAMETER_MODIFY, WP);
			ModuleIds.put(AccessibilityConstant.FUNC_SETTING_WORKFLOW_PARAMETER_APPROVAL, WP);
			
			ModuleIds.put(AccessibilityConstant.FUNC_SETTING_EXCEPTION_HANDLING_MODIFY, EH);
			ModuleIds.put(AccessibilityConstant.FUNC_SETTING_EXCEPTION_HANDLING_APPROVAL, EH);
		}
		
		public static final Map<String, String> ModuleNames = new LinkedHashMap<String, String>();
		static{
			ModuleNames.put(BUS, "User");
			ModuleNames.put(LOOKUP, "Reference");
			ModuleNames.put(ROL, "Role");
			ModuleNames.put(SECPA, "Security Parameters");
			ModuleNames.put(SYSPA, "System Parameter");
			//ModuleNames.put(HST, "Holiday");
			ModuleNames.put(REGION, "Region");
			ModuleNames.put(AREA, "Area");
			ModuleNames.put(BRANCH_LOCATION, "Branch Location");
			ModuleNames.put(FAQ, "Content Manager");
			ModuleNames.put(WP, "Workflow Parameter");
			ModuleNames.put(EH, "Exception Handling");
		}
		
		public static final List<String> NeedAppSynchronization = new ArrayList<String>();
		static{
			NeedAppSynchronization.add(SECPA);
			NeedAppSynchronization.add(SYSPA);
//			NeedAppSynchronization.add(HST);
		};
		
	}
	
	/**
	 * Key - value: Name - Code	
	 */
	public static Map<String, String> provinceMap = new HashMap<String, String>();
	
	/**
	 * Key - value: Code - Name
	 */
	public static Map<String, String> provinceDepKeuMap = new HashMap<String, String>();
	
	/**
	 * Key - value: Name - Code
	 */
	public static Map<String, String> cityMap = new HashMap<String, String>();
	
	/**
	 * Key - value: Code - Name
	 */
	public static Map<String, String> cityDepKeuMap = new HashMap<String, String>();
	
}