package com.metamorf.eform.entity.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.ForeignKey;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.annotations.Expose;
import com.metamorf.eform.common.core.SystemConstant;
import com.metamorf.eform.common.enumer.UploadAction;
import com.metamorf.eform.common.enumer.UploadStatus;
import com.metamorf.eform.entity.core.SimpleApprovalObject;
import com.metamorf.eform.entity.deserializer.CustomJsonDateDeserializer;
import com.metamorf.eform.entity.deserializer.CustomJsonInverseBooleanDeserializer;
import com.metamorf.eform.entity.deserializer.DefaultJsonDateDeserializer;
import com.metamorf.eform.entity.settings.Lookup;
import com.softtech.kismiss.csv.annotations.CsvColumn;
import com.softtech.kismiss.enumer.HorizontalAlignment;
import com.softtech.kismiss.enumer.Orientation;
import com.softtech.kismiss.enumer.PaperType;
import com.softtech.kismiss.property.Detail;
import com.softtech.kismiss.property.Header;
import com.softtech.kismiss.property.Kismiss;
import com.softtech.kismiss.property.Property;
import com.softtech.kismiss.property.RecordNumber;

@Entity(name="APP_USER")
@Kismiss(name = "User Report", columnAutoSize = true, paperType= PaperType.A4, orientation=Orientation.Landscape, 
	isTitleEveryPage=false, pageNumberAlignment=HorizontalAlignment.Center, noDataFound="Data tidak ditemukan")
@Header(columnHeaderHeight=25, isColumnHeaderBold=true, columnHeaderColor="#BDBBBC", lineWidth=0.5)
@Detail(lineWidth=0.5)
public class User extends SimpleApprovalObject {
	private static final long serialVersionUID = -3070917904978317383L;
	
	public User(){
//		this.isFromUwmp=Boolean.FALSE;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	@Expose
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="TITLE", nullable=true)
	@ForeignKey(name="FK_AU_APP_USER_TITLE_LOOKUP")
	@Expose
	private Lookup title;
	
	@Column(name="RO_CODE", nullable=true)
	@Expose
	private String roCode;
	
	@Column(name="RA_CODE", nullable=true)
	@Expose
	private String raCode; // NIK_SUPERVISOR --> same/duplicate
	
	@Transient
	@CsvColumn(position = 2)
	private String titleCsv;
	
	@Column(name="USER_NAME", length=30, nullable=false)
	@Expose
	@CsvColumn(position = 3)
	private String userName;
	
	@Column(name="PASSWORD", length=256, nullable=false)
	@Expose
	private String password;
	
	@ManyToOne
	@JoinColumn(name="ROLE_ID", nullable=true)
	@ForeignKey(name="FK_AU_APP_ROLE_APP_ROLE")
	@Expose	
	private Role role;
	
	@Transient
	@CsvColumn(position = 8)
	private String roleCsv;
	
	@Column(name="NIK_SUPERVISOR", length=256)
	@Expose
	private String nikSupervisor;
	
	@Transient
	private UploadStatus uploadStatus;
	
	@Transient
	private UploadAction uploadAction;
	
	@Transient
	private String remark;
	
	@Transient
	@CsvColumn(position = 9)
	private String activeCsv;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_LOGIN_DATE",nullable=true)
	@Expose
	@JsonDeserialize(using=CustomJsonDateDeserializer.class)
	private Date lastLogInDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_LOGOUT_DATE",nullable=true)
	@Expose
	private Date lastLogOutDate;
	
	@Column(name="LAST_LOGIN_FROM",nullable=true)
	@Expose
	private String lastLogInFrom;
	
	@Column(name="FAILED_LOGIN_COUNTER")
	@Expose
	private Integer failedLoginCounter;
	
	@Column(name = "IS_LOCK", precision = 1, scale = 0, nullable = false)
	@Expose
	private Boolean lock;
	
	@Column(name="MUST_CHANGE_PASSWORD", columnDefinition="int default 0", nullable=false)
	@Expose
	private Integer mustChangePassword;
	
	@Temporal(TemporalType.DATE)
	@Column(name="PWD_EXPIRED_DATE", nullable=false)
	@Expose
	private Date pwdExpiredDate;
	
	/*@Column(name="STATE")
	private Integer state;*/
	
	@Column(name="FIRST_NAME", length=50)
	@Expose
	private String firstName;
	
	@Column(name="LAST_NAME", length=50)
	@Expose
	private String lastName;
	
	@Column(name="FULL_NAME", length=100)
	@Expose
	@CsvColumn(position = 4)
	private String fullName;
	
	@Column(name="LOCALE", length=7)
	@Expose
	private String locale;
	
	@CsvColumn(position = 7)
	@Column(name="EMAIL", length=180)
	@Expose
	private String email;
	
	@CsvColumn(position = 10)
	@Column(name="MOBILE_NO", length=20)
	@Expose
	private String mobileNo;
	
	@Column(name="PHONE_NO", length=20)
	@Expose
	private String phoneNo;
	
	@Column(name="ext", length=10)
	@Expose
	private String ext;
	
	@Column(name = "IS_NOTIF_SEND", precision = 1, scale = 0)
	@Expose
	private Boolean notifSend;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE")
	@Expose
	@JsonDeserialize(using=CustomJsonDateDeserializer.class)
	private Date createdDate;
	
	@Column(name="CREATED_BY")
	@Expose
	private String createdBy;
	
	@Column(name="AUTH_MODE", length=1)
	@Expose
	private String authMode; //T or S
	
	@Column(name = "TOKEN", length = 250)
	@Expose
	private String token;
	
	@Column(name="DEVICE_UUID", length=50)
	@Expose
	private String deviceUuid;
	
	@Column(name="NAME_ON_PIN_MAILER")
	@Expose
	private String nameOnPinMailer;
	
	@Column(name="ADDR1")
	@Expose
	private String addr1;
	
	@Column(name="ADDR2")
	@Expose
	private String addr2;
	
	@Column(name="ADDR3")
	@Expose
	private String addr3;
	
	@Column(name="ADDR4")
	@Expose
	private String addr4;
	
	@Column(name="ADDR5")
	@Expose
	private String addr5;
	
	@Column(name="POSTCODE")
	@Expose
	private String postcode;
	
	@Column(name="STATE")
	@Expose
	private String state;
	
	@Column(name="CITY")
	@Expose
	private String city;
	
	@Column(name="PREFECTURE")
	@Expose
	private String prefecture;
	
	@Column(name="CORR_ADDR1")
	@Expose
	private String corrAddr1;
	
	@Column(name="CORR_ADDR2")
	@Expose
	private String corrAddr2;
	
	@Column(name="CORR_ADDR3")
	@Expose
	private String corrAddr3;
	
	@Column(name="CORR_ADDR4")
	@Expose
	private String corrAddr4;
	
	@Column(name="CORR_ADDR5")
	@Expose
	private String corrAddr5;
	
	@Column(name="CORR_POSTCODE")
	@Expose
	private String corrPostcode;
	
	@Column(name="CORR_STATE")
	@Expose
	private String corrState;
	
	@Column(name="CORR_CITY")
	@Expose
	private String corrCity;
	
	@Column(name="CORR_PREFECTURE")
	@Expose
	private String corrPrefecture;
	
	@Column(name="IS_SUPER")
	@Expose
	private Integer isSuper=0;
	
	@Column(name="COUNTRY_CODE")
	@Expose
	private String countryCode;
	
	@Column(name="CORR_COUNTRY_CODE")
	@Expose
	private String corrCountryCode;
	
	@Column(name="AUTO_SEND_INDI")
	@Expose
	private String autoSendIndicator;
	
	@Column(name="RESET_PASS_NOTIF_MODE")
	@Expose
	private String resetPassNotifMode;
	
	@Column(name = "IS_DELETED", precision = 1, scale = 0)
	@Expose
	@JsonDeserialize(using=CustomJsonInverseBooleanDeserializer.class)
	private Boolean deleted=false;
	
	@Column(name="LOCATION_ID")
	@Expose
	private Long locationId;
	
	@Column(name="LOCATION_NAME")
	@Expose
	private String locationName;
	
	@Column(name="GEOGRAPHY_ASSIGNMENT")
	@Expose
	private String geographyAssignment;
	
	@CsvColumn(position = 11)
	@Column(name="DOB")
	@Expose
	private Date dateOfBirth;
	
	@Column(name="DIVISION")
	@Expose
	private String division;
	
	@Column(name="STRUCTURE")
	@Expose
	private String structure;
	
	@Column(name="APK_VERSION")
	@Expose
	private String apkVersion;
	
	@Column(name="IS_FROM_UWMP")
	@Expose
	private Boolean isFromUwmp;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UWMP_SYNC_DATE")
	@Expose
	@JsonDeserialize(using=DefaultJsonDateDeserializer.class)
	private Date uwmpSyncDate;

	@Transient
	@CsvColumn(position = 5)
	private String locationCsv;
	
	@Transient
	private String raCodeCsv;

	@Transient
	@CsvColumn(position = 6)
	private String roCodeCsv;
	
	@Transient
	private String localeName;
	@Transient
	private String roleGrid;
	@Transient
	private String lockDescription;
	@Transient
	private String localeDescription;
	@Transient
	private String companyName;
	@Transient
	private String companyCode;
	@Transient
	private Long communityMemberId;
	@Transient
	private Long communityId;
	@Transient
	private String approvalStatusDescription;
	@Transient
	private String rcasUserStatus;
	@Transient
	private String clientHost; //for now use only for including where the user start disbursement or settlement 
	
	/**
	 * Used for validation, exclude itself
	 */
	@Transient
	private Long masterDataApprovalId;
	
	@Transient
	private String clientId  = null;
	
	@Transient
	private String clientSecret = null;
	
	@Transient
	private String referenceNo = null;
	
	// will keep the popLobId assigned to the user (HQ might contains all)
	@Transient
	private List<Long> popLobIdList = new ArrayList<Long>();
	
	@Property(position = 0 , width = 8)
	@RecordNumber
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Lookup getTitle() {
		return title;
	}
	public void setTitle(Lookup title) {
		this.title = title;
	}
	@Property(position = 1 , width = 8)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Property(name = "Position", innerProperty = "Position:name",  position = 3 , width = 8)
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@Property(name = "Last Login", position = 10 , width = 8, pattern = "dd-MM-yyyy")
	public Date getLastLogInDate() {
		return lastLogInDate;
	}
	public void setLastLogInDate(Date lastLogInDate) {
		this.lastLogInDate = lastLogInDate;
	}
	public Date getLastLogOutDate() {
		return lastLogOutDate;
	}
	public void setLastLogOutDate(Date lastLogOutDate) {
		this.lastLogOutDate = lastLogOutDate;
	}
	public String getLastLogInFrom() {
		return lastLogInFrom;
	}
	public void setLastLogInFrom(String lastLogInFrom) {
		this.lastLogInFrom = lastLogInFrom;
	}
	/*public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}*/
	@Property(name="Name", position = 2 , width = 8)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Property(name = "Directorate", position = 4 , width = 8)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Integer getFailedLoginCounter() {
		return failedLoginCounter;
	}
	public void setFailedLoginCounter(Integer failedLoginCounter) {
		this.failedLoginCounter = failedLoginCounter;
	}
	public Boolean getLock() {
		return lock == null ? Boolean.FALSE : lock;
	}
	public void setLock(Boolean lock) {
		this.lock = lock;
	}
	
	public Integer getMustChangePassword() {
		return mustChangePassword;
	}
	public void setMustChangePassword(Integer mustChangePassword) {
		this.mustChangePassword = mustChangePassword;
	}
	public Date getPwdExpiredDate() {
		return pwdExpiredDate;
	}
	public void setPwdExpiredDate(Date pwdExpiredDate) {
		this.pwdExpiredDate = pwdExpiredDate;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	@Property(name = "Office", position = 5 , width = 8)
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNo() {
		if (mobileNo != null && !mobileNo.equals("")){
			/*String firstChar = String.valueOf(mobileNo.charAt(0));
			mobileNo = firstChar.equals("+") ? mobileNo : "+"+mobileNo;*/
			mobileNo = mobileNo.replaceAll("[^\\d]", "");
		}
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getLocaleName() {
		return localeName;
	}
	public void setLocaleName(String localeName) {
		this.localeName = localeName;
	}
	public void lockUser() {
		setLock(true);
	}
	public String getPhoneNo() {
		/*if (phoneNo != null && !phoneNo.equals("")){
			String firstChar = String.valueOf(phoneNo.charAt(0));
			phoneNo = firstChar.equals("+") ? phoneNo : ""+phoneNo;
			phoneNo = phoneNo.replaceAll("[^\\d]", "");
		}*/
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getExt() {
		if (ext != null && !ext.equals("")){
			ext = ext.replaceAll("[^\\d]", "");
		}
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public Boolean getNotifSend() {
		return notifSend;
	}
	public void setNotifSend(Boolean notifSend) {
		this.notifSend = notifSend;
	}
	public void resetFailedLoginCounter() {
		setFailedLoginCounter(0);
	}	
	@Property(name="Date Create", position = 8 , width = 8, pattern = "dd-MM-yyyy")
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public void incrementFailedLoginCounter() {
		int counter = (getFailedLoginCounter() == null ? 0 : getFailedLoginCounter()); 
		setFailedLoginCounter(counter + 1);
	}
	public void setRoleGrid(String roleGrid) {
		this.roleGrid = roleGrid;
	}
	public String getLockDescription() {
		return SystemConstant.UserLockStatus.LockStatusStr[getLock()? 1 : 0];
	}
	public void setLockDescription(String lockDescription) {
		this.lockDescription = lockDescription;
	}
	public String getLocaleDescription() {
		return localeDescription;
	}
	public void setLocaleDescription(String localeDescription) {
		this.localeDescription = localeDescription;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Long getCommunityMemberId() {
		return communityMemberId;
	}
	public void setCommunityMemberId(Long communityMemberId) {
		this.communityMemberId = communityMemberId;
	}
	public String getApprovalStatusDescription() {
		if(null!=getApprovalStatus())
			return SystemConstant.MasterDataApprovalStatus.MasterDataApprovalStatusStr[getApprovalStatus()];
		else
			return null;
	}
	public void setApprovalStatusDescription(String approvalStatusDescription) {
		this.approvalStatusDescription = approvalStatusDescription;
	}
	
	public Long getMasterDataApprovalId() {
		return masterDataApprovalId;
	}
	public void setMasterDataApprovalId(Long masterDataApprovalId) {
		this.masterDataApprovalId = masterDataApprovalId;
	}
	public String getAuthMode() {
		return authMode;
	}
	public void setAuthMode(String authMode) {
		this.authMode = authMode;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}	
	public String getDeviceUuid() {
		return deviceUuid;
	}
	public void setDeviceUuid(String deviceUuid) {
		this.deviceUuid = deviceUuid;
	}
	public String getRcasUserStatus() {
		return rcasUserStatus;
	}
	public void setRcasUserStatus(String rcasUserStatus) {
		this.rcasUserStatus = rcasUserStatus;
	}
	public String getClientHost() {
		return clientHost;
	}
	public void setClientHost(String clientHost) {
		this.clientHost = clientHost;
	}
	
	@Transient
	public String getAuthModeLabel() {
		if (authMode != null) {
			if (authMode.equalsIgnoreCase("T")) {
				return "Yes";
			}
		}
		return "No";
	}

	public static final String ID				= "id";
	public static final String USER_NAME 		= "userName" ;
	public static final String FIRST_NAME		= "firstName";
	public static final String LAST_NAME		= "lastName";
	public static final String FULL_NAME		= "fullName";
	public static final String LOB				= "lob";
	public static final String LOB_NAME			= "lobName";
	public static final String DATE_OF_BIRT		= "dateOfBirth";
	
	public static final String ROLE 			= "role" ;	
	public static final String COMPANY 			= "company" ;
	public static final String COMPANY_ID 		= "company.id" ;
	public static final String COMPANY_CODE 	= "company.code" ;
	public static final String COMPANY_NAME 	= "company.name" ;
	public static final String ROLE_NAME 		= "role.name" ;
	public static final String ROLE_ID	 		= "role.id" ;
	public static final String CREATED_DATE		= "createdDate" ;
	public static final String CREATED_BY 		= "createdBy" ;
	public static final String ACCOUNT_LOCK 	= "lock" ;
	public static final String EMAIL 			= "email" ;
	public static final String MOBILE_NO 		= "mobileNo" ;
	public static final String PHONE_NO 		= "phoneNo" ;
	public static final String EXT 				= "ext" ;
	public static final String NOTIF_SEND 		= "notifSend" ;
	public static final String LOCALE 			= "locale" ;
	public static final String LOCALE_NAME		= "localeName" ;
	public static final String TYPE 			= "type" ;
	public static final String USER_ACCESS 		= "userAccess" ;
	public static final String LOCK 			= "lock" ;
	public static final String APPROVAL_STATUS_DESC	= "approvalStatusDescription" ;
	public static final String AUTH_MODE 		= "authMode" ;
	public static final String TOKEN 			= "token" ;
	public static final String NAME_ON_PIN_MAILER	= "nameOnPinMailer" ;
	public static final String ADDR1 				= "addr1" ;
	public static final String ADDR2 				= "addr2" ;
	public static final String ADDR3 				= "addr3" ;
	public static final String ADDR4 				= "addr4" ;
	public static final String ADDR5 				= "addr5" ;
	public static final String POSTCODE 			= "postcode" ;
	public static final String STATE 				= "state" ;
	public static final String CITY 				= "city" ;
	public static final String PREFECTURE 			= "prefecture" ;
	public static final String CORR_ADDR1 			= "corrAddr1" ;
	public static final String CORR_ADDR2 			= "corrAddr2" ;
	public static final String CORR_ADDR3 			= "corrAddr3" ;
	public static final String CORR_ADDR4 			= "corrAddr4" ;
	public static final String CORR_ADDR5 			= "corrAddr5" ;
	public static final String CORR_POSTCODE 		= "corrPostcode" ;
	public static final String CORR_STATE 			= "corrState" ;
	public static final String CORR_CITY 			= "corrCity" ;
	public static final String CORR_PREFECTURE 			= "corrPrefecture" ;
	public static final String LAST_LOGIN_DATE 			= "lastLogInDate" ;
	public static final String COUNTRY_CODE 			= "countryCode" ;
	public static final String CORR_COUNTRY_CODE 		= "corrCountryCode" ;
	public static final String AUTO_SEND_INDI 			= "autoSendIndicator" ;
	public static final String RESET_PASS_NOTIF_MODE 	= "resetPassNotifMode" ;
	public static final String DELETED		 			= "deleted" ;
	public static final String AREA_NAME				= "area.name";
	public static final String AREA						= "area";
	public static final String TITLE 					= "title" ;
	public static final String TITLE_ID					= "title.id" ;
	public static final String TITLE_CODE 				= "title.code" ;
	public static final String TITLE_NAME 				= "title.name" ;
	public static final String LOCATION_NAME 			= "locationName" ;
	public static final String LOCATION_ID 				= "locationId" ;
	public static final String GEOGRAPHY_ASSIGNMENT		= "geographyAssignment" ;
	public static final String RO_CODE					= "roCode" ;
	public static final String APK_VERSION				= "apkVersion" ;
	public static final String MUST_CHANGE_PASSWORD		= "mustChangePassword" ;
	public static final String PASSWORD 				= "password" ;
	public static final String PWD_EXPIRED_DATE 		= "pwdExpiredDate";
	public static final String NIK_SUPERVISOR 			= "nikSupervisor";
	public static final String DIVISION 				= "division";
	//Maintenance
	public static final String IS_FROM_UWMP				= "isFromUwmp" ;
	public static final String STRUCTURE				= "structure" ;
	public static final String RA_CODE					= "raCode";
	public static final String UWMP_SYNC_DATE			= "uwmpSyncDate";
	
	public static final String[] MAINTENANCE_LIST_FIELDS = {
		ID, USER_NAME, FIRST_NAME, LAST_NAME, FULL_NAME, ROLE, ROLE_NAME, STATUS, LATEST_SUGGESTION, APPROVAL_STATUS,  LAST_LOGIN_DATE,
		LATEST_SUGGESTOR, LATEST_APPROVAL, LATEST_APPROVER, CREATED_DATE, CREATED_BY, ACCOUNT_LOCK, EMAIL, MOBILE_NO, PHONE_NO,
		EXT, NOTIF_SEND, LOCALE, TITLE, TITLE_CODE, TITLE_NAME, LOCATION_ID, LOCATION_NAME, LOB,DATE_OF_BIRT, APK_VERSION, IS_FROM_UWMP,DIVISION, STRUCTURE, NIK_SUPERVISOR, UWMP_SYNC_DATE
	} ;
	
	public static final String[] SYCN_LIST_FIELDS = {
			ID, USER_NAME, FIRST_NAME, LAST_NAME, FULL_NAME, STATUS, LATEST_SUGGESTION, APPROVAL_STATUS,  LAST_LOGIN_DATE,
			LATEST_SUGGESTOR, LATEST_APPROVAL, LATEST_APPROVER, CREATED_DATE, CREATED_BY, ACCOUNT_LOCK, EMAIL, MOBILE_NO, PHONE_NO, LOCK,
			EXT, NOTIF_SEND, LOCALE,  LOCATION_ID, LOCATION_NAME, LOB,LOB_NAME, MUST_CHANGE_PASSWORD, 
			PASSWORD,PWD_EXPIRED_DATE,STRUCTURE,LOCATION_ID,NIK_SUPERVISOR,RA_CODE,DIVISION,RO_CODE
		} ;
	
	public static final String[] SYCN_LIST_FIELDS_EXCLUDE = {
			PASSWORD, PWD_EXPIRED_DATE,MUST_CHANGE_PASSWORD,STATE,DELETED,LOCK
		} ;
	
	public static final String[] MAINTENANCE_DETAIL_FIELDS = {
		ID, USER_NAME, FIRST_NAME, LAST_NAME, FULL_NAME, 
		COMPANY_ID, COMPANY_CODE, ROLE_ID, CREATED_DATE, CREATED_BY, ACCOUNT_LOCK, 
		EMAIL, MOBILE_NO, PHONE_NO, EXT, NOTIF_SEND, LOCALE, TYPE,DATE_OF_BIRT, APK_VERSION, IS_FROM_UWMP, STRUCTURE, NIK_SUPERVISOR, UWMP_SYNC_DATE
	} ;
	
	public static final String[] MAINTENANCE_JOIN_ENTITIES = {
		ROLE, COMPANY
	} ;
	
	public static final String[] COMPANY_USER_PICK_LIST_FIELDS = {
		ID, USER_NAME, FULL_NAME, COMPANY_CODE, COMPANY_NAME
	} ;
	
	public static final String[] BANK_USER_PICK_LIST_FIELDS = {
		ID, USER_NAME, FULL_NAME
	} ;

	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getNameOnPinMailer() {
		return nameOnPinMailer;
	}
	public void setNameOnPinMailer(String nameOnPinMailer) {
		this.nameOnPinMailer = nameOnPinMailer;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getAddr3() {
		return addr3;
	}
	public void setAddr3(String addr3) {
		this.addr3 = addr3;
	}
	public String getAddr4() {
		return addr4;
	}
	public void setAddr4(String addr4) {
		this.addr4 = addr4;
	}
	public String getAddr5() {
		return addr5;
	}
	public void setAddr5(String addr5) {
		this.addr5 = addr5;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPrefecture() {
		return prefecture;
	}
	public void setPrefecture(String prefecture) {
		this.prefecture = prefecture;
	}
	public String getCorrAddr1() {
		return corrAddr1;
	}
	public void setCorrAddr1(String corrAddr1) {
		this.corrAddr1 = corrAddr1;
	}
	public String getCorrAddr2() {
		return corrAddr2;
	}
	public void setCorrAddr2(String corrAddr2) {
		this.corrAddr2 = corrAddr2;
	}
	public String getCorrAddr3() {
		return corrAddr3;
	}
	public void setCorrAddr3(String corrAddr3) {
		this.corrAddr3 = corrAddr3;
	}
	public String getCorrAddr4() {
		return corrAddr4;
	}
	public void setCorrAddr4(String corrAddr4) {
		this.corrAddr4 = corrAddr4;
	}
	public String getCorrAddr5() {
		return corrAddr5;
	}
	public void setCorrAddr5(String corrAddr5) {
		this.corrAddr5 = corrAddr5;
	}
	public String getCorrPostcode() {
		return corrPostcode;
	}
	public void setCorrPostcode(String corrPostcode) {
		this.corrPostcode = corrPostcode;
	}
	public String getCorrState() {
		return corrState;
	}
	public void setCorrState(String corrState) {
		this.corrState = corrState;
	}
	public String getCorrCity() {
		return corrCity;
	}
	public void setCorrCity(String corrCity) {
		this.corrCity = corrCity;
	}
	public String getCorrPrefecture() {
		return corrPrefecture;
	}
	public void setCorrPrefecture(String corrPrefecture) {
		this.corrPrefecture = corrPrefecture;
	}
	public Integer getIsSuper() {
		return isSuper;
	}
	public void setIsSuper(Integer isSuper) {
		this.isSuper = isSuper;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getCommunityId() {
		return communityId;
	}
	public void setCommunityId(Long communityId) {
		this.communityId = communityId;
	}

	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCorrCountryCode() {
		return corrCountryCode;
	}
	public void setCorrCountryCode(String corrCountryCode) {
		this.corrCountryCode = corrCountryCode;
	}
	public String getAutoSendIndicator() {
		return autoSendIndicator;
	}
	public void setAutoSendIndicator(String autoSendIndicator) {
		this.autoSendIndicator = autoSendIndicator;
	}
	public String getResetPassNotifMode() {
		return resetPassNotifMode;
	}
	public void setResetPassNotifMode(String resetPassNotifMode) {
		this.resetPassNotifMode = resetPassNotifMode;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	@Transient
	public String getAutoSendIndicatorDescription(){
		return SystemConstant.AutoSendInticator.LIST.get(this.autoSendIndicator);
	}
	
	@Transient
	public String getResetPassNotifModeDescription(){
		return SystemConstant.AutoSendInticator.LIST.get(this.resetPassNotifMode);
	}
	public String getRoleCsv() {
		return roleCsv;
	}
	public void setRoleCsv(String roleCsv) {
		this.roleCsv = roleCsv;
	}
	public String getRoleGrid() {
		return roleGrid;
	}
	public String getActiveCsv() {
		return activeCsv;
	}
	public void setActiveCsv(String activeCsv) {
		this.activeCsv = activeCsv;
	}
	public UploadStatus getUploadStatus() {
		return uploadStatus;
	}
	public void setUploadStatus(UploadStatus uploadStatus) {
		this.uploadStatus = uploadStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public UploadAction getUploadAction() {
		return uploadAction;
	}
	public void setUploadAction(UploadAction uploadAction) {
		this.uploadAction = uploadAction;
	}
	public String getTitleCsv() {
		return titleCsv;
	}
	public void setTitleCsv(String titleCsv) {
		this.titleCsv = titleCsv;
	}
	public String getLocationCsv() {
		return locationCsv;
	}
	public void setLocationCsv(String locationCsv) {
		this.locationCsv = locationCsv;
	}

	@Transient
	private List<Long> branches;

	public List<Long> getBranches() {
		return branches;
	}
	public void setBranches(List<Long> branches) {
		this.branches = branches;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getGeographyAssignment() {
		return geographyAssignment;
	}
	public void setGeographyAssignment(String geographyAssignment) {
		this.geographyAssignment = geographyAssignment;
	}
	public String getRoCode() {
		return roCode;
	}
	public void setRoCode(String roCode) {
		this.roCode = roCode;
	}
	public String getRaCode() {
		return raCode;
	}
	public void setRaCode(String raCode) {
		this.raCode = raCode;
	}
	public String getRaCodeCsv() {
		return raCodeCsv;
	}
	public void setRaCodeCsv(String raCodeCsv) {
		this.raCodeCsv = raCodeCsv;
	}
	public String getRoCodeCsv() {
		return roCodeCsv;
	}
	public void setRoCodeCsv(String roCodeCsv) {
		this.roCodeCsv = roCodeCsv;
	}
	
	//Penambahan field pembeda SDB & Sinaya
	@Column
	@Expose
	private Integer lob;

	public Integer getLob() {
		return lob;
	}
	public void setLob(Integer lob) {
		this.lob = lob;
	}
	
	@Transient
	@Expose
	private String lobName;
	
	public void setLobName(String lobName) {
		this.lobName = lobName;
	}
	
	public String getLobName() {
		return lobName;
	}
	
	@Transient
	@CsvColumn(position = 1)
	private String lobCsv;
	
	public String getLobCsv() {
		return lobCsv;
	}
	public void setLobCsv(String lobCsv) {
		this.lobCsv = lobCsv;
	}
	
	@Transient
	public String getLobDescr(){
		return SystemConstant.ProjectType.userLobList.get(getLob());
	}
	
	@Transient
	public String getLobBaseDescr(){
		return SystemConstant.ProjectType.userLobList.get(getLobBase());
	}
	
	@Transient
	private Integer lobBase;

	public Integer getLobBase() {
		return lobBase;
	}
	public void setLobBase(Integer lobBase) {
		this.lobBase = lobBase;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	
	@Transient
	private String dob;

	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getApkVersion() {
		return apkVersion;
	}
	public void setApkVersion(String apkVersion) {
		this.apkVersion = apkVersion;
	}
	

	public void setNikSupervisor(String nikSupervisor) {
		this.nikSupervisor = nikSupervisor;
	}
	
	public String getNikSupervisor() {
		return nikSupervisor;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	
	public Boolean getIsFromUwmp() {
		return isFromUwmp;
	}
	
	public void setIsFromUwmp(Boolean isFromUwmp) {
		this.isFromUwmp = isFromUwmp;
	}
	
	public void setStructure(String structure) {
		this.structure = structure;
	}
	
	public String getStructure() {
		return structure;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	
	@Transient
	public String getDivisionDescr(){
		return SystemConstant.DivisionType.divisionList.get(getDivision());
	}
	
	public List<Long> getPopLobIdList() {
		return popLobIdList;
	}
	public void setPopLobIdList(List<Long> popLobIdList) {
		this.popLobIdList = popLobIdList;
	}
	
	@Override
	public String toString() {
		return "User [title=" + title + ", roCode=" + roCode + ", raCode=" + raCode + ", userName=" + userName
				+ ", nikSupervisor=" + nikSupervisor + ", fullName=" + fullName + ", email=" + email + ", dateOfBirth="
				+ dateOfBirth + ", division=" + division + ", structure=" + structure +", isFromUwmp=" + isFromUwmp + "]";
	}
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	public Date getUwmpSyncDate() {
		return uwmpSyncDate;
	}
	public void setUwmpSyncDate(Date uwmpSyncDate) {
		this.uwmpSyncDate = uwmpSyncDate;
	}
}