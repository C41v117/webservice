package com.metamorf.eform.common.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.metamorf.eform.common.enumer.UserType;


public class LoginSession implements Serializable  {

	private static final long serialVersionUID = 1268258705992242500L;
	private String 	sessionId;
	private Long 	userId;
	private String 	password;
	private String 	userName;
	private String 	fullName;
	private String 	firstName;
	private String 	lastName;
	private UserType userType;
	private Long 	companyId;
	private String 	companyCode;
	private String 	companyName;
	private String 	companyAddress;
	private String 	companyPhoneNo;
	private String 	companyCityAddress;
	private String 	companyZipAddress;
	private String 	companyFaxNo;
	private Date 	lastLogInDate;
	private Long 	roleId;
	private String 	roleName;
	private String 	authMode;
	private Map<Long, String> permissions;
	private List<Long> makerPermissions;
	private List<Long> approverPermissions;
	private List<Long> releaserPermissions;
	private List<Long> rootPermissions;
	private Integer mustChangePassword;
	private List<Long> branches;
	private String titleDesc;
	private Integer lob;
	private Integer lobBase;
	private String titleCode;
	private Long locationId;

	private List<Long> popLobIdList;

	private String 	division;
	
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public Integer getLobBase() {
		return lobBase;
	}
	public void setLobBase(Integer lobBase) {
		this.lobBase = lobBase;
	}
	public Integer getLob() {
		return lob;
	}
	public void setLob(Integer lob) {
		this.lob = lob;
	}
	public String getTitleDesc() {
		return titleDesc;
	}
	public void setTitleDesc(String titleDesc) {
		this.titleDesc = titleDesc;
	}
	public List<Long> getBranches() {
		return branches;
	}
	public void setBranches(List<Long> branches) {
		this.branches = branches;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getCompanyPhoneNo() {
		return companyPhoneNo;
	}
	public void setCompanyPhoneNo(String companyPhoneNo) {
		this.companyPhoneNo = companyPhoneNo;
	}
	public String getCompanyCityAddress() {
		return companyCityAddress;
	}
	public void setCompanyCityAddress(String companyCityAddress) {
		this.companyCityAddress = companyCityAddress;
	}
	public String getCompanyZipAddress() {
		return companyZipAddress;
	}
	public void setCompanyZipAddress(String companyZipAddress) {
		this.companyZipAddress = companyZipAddress;
	}
	public String getCompanyFaxNo() {
		return companyFaxNo;
	}
	public void setCompanyFaxNo(String companyFaxNo) {
		this.companyFaxNo = companyFaxNo;
	}
	public Date getLastLogInDate() {
		return lastLogInDate;
	}
	public void setLastLogInDate(Date lastLogInDate) {
		this.lastLogInDate = lastLogInDate;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public void addPermission(Long appFunctionId, String roleName) {
		if (permissions == null) {
			permissions = new HashMap<Long, String>();
		}
		
		permissions.put(appFunctionId, roleName);
	}
	public boolean hasPermission(Long menuId) {
		return permissions.containsKey(menuId);
	}
	
	public List<Long> getMakerPermissions() {
		if(makerPermissions==null){
			makerPermissions = new ArrayList<Long>();
		}
		return makerPermissions;
	}
	public List<Long> getApproverPermissions() {
		if(approverPermissions==null){
			approverPermissions = new ArrayList<Long>();
		}
		return approverPermissions;
	}
	
	public List<Long> getReleaserPermissions() {
		if(releaserPermissions==null){
			releaserPermissions = new ArrayList<Long>();
		}
		return releaserPermissions;
	}
	
	public List<Long> getRootPermissions() {
		if(rootPermissions==null){
			rootPermissions = new ArrayList<Long>();
		}
		return rootPermissions;
	}
	
	public String getAuthMode() {
		return authMode;
	}
	public void setAuthMode(String authMode) {
		this.authMode = authMode;
	}
	
	public Integer getMustChangePassword() {
		return mustChangePassword;
	}
	public void setMustChangePassword(Integer mustChangePassword) {
		this.mustChangePassword = mustChangePassword;
	}
	public String getLobDescr(){
		return SystemConstant.ProjectType.userLobList.get(getLob());
	}
	public String getTitleCode() {
		return titleCode;
	}
	public void setTitleCode(String titleCode) {
		this.titleCode = titleCode;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public List<Long> getPopLobIdList() {
		return popLobIdList;
	}
	public void setPopLobIdList(List<Long> popLobIdList) {
		this.popLobIdList = popLobIdList;
	}
	
}
