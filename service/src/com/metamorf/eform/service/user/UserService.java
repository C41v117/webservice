package com.metamorf.eform.service.user;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jasypt.digest.StringDigester;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.metamorf.eform.common.core.ILookupGroupConstant;
import com.metamorf.eform.common.core.IUserConstant;
import com.metamorf.eform.common.core.SystemConstant;
import com.metamorf.eform.common.core.SystemParameter;
import com.metamorf.eform.common.data.util.Operator;
import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.exception.ErrorHolder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.common.util.DateTimeFunction;
import com.metamorf.eform.data.access.security.IAppRoleFunctionDAO;
import com.metamorf.eform.data.access.security.IRoleDAO;
import com.metamorf.eform.data.access.settings.IAppParameterDAO;
import com.metamorf.eform.data.access.settings.ILookupDAO;
import com.metamorf.eform.data.access.user.IUserDAO;
import com.metamorf.eform.entity.settings.Lookup;
import com.metamorf.eform.entity.user.Role;
import com.metamorf.eform.entity.user.User;
import com.metamorf.eform.interfaces.core.IBaseService;
import com.metamorf.eform.interfaces.user.IUserService;

@Service
public class UserService implements IBaseService<User>, IUserService {
	private static final Logger LOGGER = ESAPI.getLogger(UserService.class);
	org.slf4j.Logger infoLog = LoggerFactory.getLogger("com.metamorf.log.info");

	private IUserDAO userDAO;
	private IRoleDAO roleDAO;
	private IAppRoleFunctionDAO appRoleFunctionDAO;
	private ILookupDAO lookupDAO;
	private IAppParameterDAO appParameterDAO;
	
	public void setAppParameterDAO(IAppParameterDAO appParameterDAO) {
		this.appParameterDAO = appParameterDAO;
	}

	public void setLookupDAO(ILookupDAO lookupDAO) {
		this.lookupDAO = lookupDAO;
	}

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void setRoleDAO(IRoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}
	
	public void setAppRoleFunctionDAO(IAppRoleFunctionDAO appRoleFunctionDAO) {
		this.appRoleFunctionDAO = appRoleFunctionDAO;
	}

	@Override
	public List<User> findAll(int startNo, int offset, List<SearchFilter> searchFilters, List<SearchOrder> searchOrders) throws SystemException {
		return userDAO.findAll(startNo, offset, searchFilters, searchOrders);
	}

	@Override
	public User findById(Long id) throws SystemException {
		return userDAO.findById(id);
	}

	@Override
	public void saveOrUpdate(User anObject) {
		StringWriter stack = new StringWriter();
		if(anObject.getStatus().intValue() == SystemConstant.MasterDataStatus.INACTIVE){
			Exception inactiveE = new Exception("Info User Inactive: "+anObject.getUserName());
			inactiveE.printStackTrace(new PrintWriter(stack));
			infoLog.debug(stack.toString());
		}
		
		userDAO.saveOrUpdate(anObject);
	}

	@Override
	public void saveOrUpdateWithLDAP(User user) throws SystemException {
//		boolean isUpdate = false;
//		if(user.getId()!=null) {
//			isUpdate = true;
//		}

		userDAO.saveOrUpdate(user);
	}

	private Map createLDAPAttributes(User user) {
		Map attributes = new HashMap();
		attributes.put("cn", user.getUserName());
		attributes.put("givenName", user.getFirstName());
		attributes.put("sn", user.getLastName());
		attributes.put("objectclass", new String[]{"top", "person", "organizationalPerson", "inetOrgPerson", "bankUserClass"});

		if (user.getRole() != null) {
			Role role = roleDAO.findById(user.getRole().getId());
			attributes.put("roleCode", role.getCode());
			attributes.put("roleName", role.getName());
		}
		
		return attributes;
	}

	@Override
	public void delete(User user) throws SystemException {
		//TODO #delete User
	}

	@Override
	public User findByRoCode(String roCode) throws SystemException{
		User user = userDAO.findByRoCode(roCode);
		/*if (user == null) {
			throw new SystemException(new ErrorHolder("error.user.not.found"));
		}*/
		return user;
	}

	@Override
	public User findByUserName(String userName) throws SystemException {
		User user = userDAO.findByUserName(userName);
		/*if (user == null) {
			throw new SystemException(new ErrorHolder("error.user.not.found"));
		}*/
		return user;
	}
	
	@Override
	public User findByUserName(String userName, Boolean isDeleted) throws SystemException {
		User user = userDAO.findByUserName(userName, isDeleted);
		/*if (user == null) {
			throw new SystemException(new ErrorHolder("error.user.not.found"));
		}*/
		return user;
	}
	
	@Override
	public User findByUserNameForValidation(String userName) throws SystemException {
		User user = userDAO.findByUserName(userName, null);
		/*if (user == null) {
			throw new SystemException(new ErrorHolder("error.user.not.found"));
		}*/
		return user;
	}
	
	@Override
	public User findByUserNameClean(String userName) throws SystemException {
		User user = userDAO.findByUserNameClean(userName);
		return user;
	}
	
	@Override
	public User findForBasicAuth(String userName) throws SystemException {
		User user = userDAO.findForBasicAuth(userName);
		return user;
	}	
	
	@Override
	public User findByUserNameBO(String userName) throws SystemException {
		User user = userDAO.findByUserNameBO(userName);
		if (user == null) {
			throw new SystemException(new ErrorHolder("error.user.not.found"));
		}
		return user;
	}
	
	@Override
	public User findByUserNameBODeleted(String userName) throws SystemException {
		User user = userDAO.findByUserNameBODeleted(userName);
		return user;
	}
	
	//be careful using this, must know internal or external user is being query
	@Override
	public List<User> findAllByUserName(List<String> usernames) throws SystemException {
		List<SearchFilter> searchFilters = new ArrayList<SearchFilter>();
		searchFilters.add(new SearchFilter(User.USER_NAME, Operator.IN, usernames));
		return userDAO.findAllByUserName(searchFilters);
	}
	
	@Override
	public User findByUserNameFO(String userName, Long companyId) throws SystemException {
		User user = userDAO.findByUserNameFO(userName,companyId);
		if (user == null) {
			throw new SystemException(new ErrorHolder("error.user.company.code.not.valid"));
		}
		return user;
	}
	
	@Override
	public User findByUserNameFO(String userName, String companyCode) throws SystemException {
		User user = userDAO.findByUserNameFO(userName,companyCode);
		if (user == null) {
			throw new SystemException(new ErrorHolder("error.user.company.code.not.valid"));
		}
		return user;
	}
	
	@Override
	public User findByUserNameFODeleted(String userName, String companyCode) throws SystemException {
		User user = userDAO.findByUserNameFODeleted(userName,companyCode);
		return user;
	}

	@Override
	public List<User> findByCompanyId(Long id) throws SystemException {
		return userDAO.findByCompanyId(id);
	}
	
	@Override
	public User findByIdAndToken(Long id, String token) throws SystemException {
		return userDAO.findByIdAndToken(id, token);
	}

	@Override
	public PagingWrapper<User> findAllWithPagingWrapper(int startNo,
			int offset, List<SearchFilter> searchFilters,
			List<SearchOrder> searchOrders) throws SystemException {
		
//		for (SearchFilter filter : searchFilters) {
//			if (StringUtils.trimToNull(filter.getFieldName()) != null) {
//				if(filter.getFieldName().equals(User.TYPE) && SystemConstant.UserType.EXTERNAL==Integer.parseInt(filter.getValue().toString())){
//					return userDAO.findAllExternalUser(startNo, offset, searchFilters, searchOrders);
//				}
//			}
//		}
		return userDAO.findAllInternalUser(startNo, offset, searchFilters, searchOrders);
		
	}
	
	@Override
	public boolean checkPassword(String userName, String password) {
		return userDAO.checkPassword(userName, password);
	}
	
	@Override
	public void updatePassword(String userName, String password){
		userDAO.updatePassword(userName, password);
	}
	
	@Override
	public void updatePasswordBO(String userName, String password){
		userDAO.updatePasswordBO(userName, password);
	}
	
	@Override
	public void updatePasswordFO(Long companyId, String userName, String password){
		userDAO.updatePasswordFO(companyId, userName, password);
	}

	@Override
	public User findByIdEager(Long id) throws SystemException {
		return userDAO.findByIdEager(id);
	}

	@Override
	public List<User> findBankUserByAppFunctionId(Long appFunctionId)
			throws SystemException {
		return userDAO.findBankUserByAppFunctionId(appFunctionId);
	}

	@Override
	public List<User> findUserByAppFunctionCompanyCommunity(Long appFunctionId,
			Long companyId, Long communityId) throws SystemException {
		return userDAO.findUserByAppFunctionCompanyCommunity(appFunctionId, companyId, communityId);
	}

	@Override
	public List<User> findReportCollection(List<SearchFilter> searchFilters,
			List<SearchOrder> searchOrders) throws SystemException {
		return userDAO.findAllInternalUser(0,0,searchFilters, searchOrders).getResult();
	}

	@Override
	public List<User> findReportCollection(int startNo, int offset,
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Map<String,Object> validateUser(String userId,String userName) {
		Map<String,Object> result = new HashMap<String,Object>();
		
		int recordId = -1;
		
		if (recordId == -1) {
			return result;
		}
		
		return result;
	}

	@Override
	public void mergeRCASCompanyUser(List<User> companyUserList) throws SystemException {
		List<ErrorHolder> errorHolders = new ArrayList<ErrorHolder>();
		LOGGER.info(Logger.EVENT_SUCCESS, "merging to RCAS user size "+ companyUserList.size());
	}
	
	/**
	 * @param user
	 * @param oldToken
	 * @throws SystemException
	 */
	private void processToken(User user, String oldToken) throws SystemException {
		
	}
	
	@Override
	public HashMap<String, Object> validateRCAS(User user){
		HashMap<String, Object> map = new HashMap<String, Object>();
		boolean isValidate = false;
		map.put("isValidate", isValidate);
		return map;
	}

//	@Override
//	public void saveUserPasswordHistory(UserPasswordHistory history) {
//		List<SearchFilter> filters = new ArrayList<SearchFilter>();
//		filters.add(new SearchFilter(UserPasswordHistory.APP_USER_ID, Operator.EQUALS, history.getAppUserId()));
//		List <UserPasswordHistory> listPassHist = userPasswordHistoryDAO.findAll(0, 0, filters, null);
//		if (listPassHist.size() >= SystemParameter.MAX_PASSWORD_HISTORY_CHECK){
//			userPasswordHistoryDAO.deleteObject(listPassHist.get(0));
//		}
//		userPasswordHistoryDAO.saveOrUpdate(history);
//	}
	
	@Override
	public List<User> findRCASCompanyUser(String userId, String userName,
			String companyID) {
		List<User> users= new LinkedList<User>();
		return users;
	}
	
	@Override
	public PagingWrapper<User> findBankUserByDorman(int startNo, int offset, List<SearchOrder> searchOrder) throws SystemException {
		return userDAO.findBankUserByDorman(startNo, offset, searchOrder);
	}
	
	@Override
	public List<User> findBankUserForAssignCommunity(List<User> userSelected) {
		Long[] userId = {};
		if (userSelected != null && !userSelected.isEmpty()){
			userId = new Long[userSelected.size()];
			int i=0;
			for (User user:userSelected){
				userId[i] = user.getId();
				i++;
			}
		}
		
		List<SearchFilter> searchFilters = new ArrayList<SearchFilter>();
		searchFilters.add(new SearchFilter(User.TYPE,Operator.EQUALS,SystemConstant.UserType.INTERNAL));
		searchFilters.add(new SearchFilter(User.STATUS,Operator.EQUALS,SystemConstant.MasterDataStatus.ACTIVE));
		searchFilters.add(new SearchFilter(User.APPROVAL_STATUS,Operator.EQUALS,SystemConstant.MasterDataApprovalStatus.APPROVED));
		
		if (userId.length > 0){
			searchFilters.add(new SearchFilter(User.ID,Operator.NOT_IN_ARRAY,userId));
		}
		
		List<User> userList = userDAO.findAll(searchFilters);
		return userList;
	}
	

	
	@Override
	public List<User> findBankUserForAssignCommunity() {
		List<SearchFilter> searchFilters = new ArrayList<SearchFilter>();
		searchFilters.add(new SearchFilter(User.TYPE,Operator.EQUALS,SystemConstant.UserType.INTERNAL));
		searchFilters.add(new SearchFilter(User.STATUS,Operator.EQUALS,SystemConstant.MasterDataStatus.ACTIVE));
		searchFilters.add(new SearchFilter(User.APPROVAL_STATUS,Operator.EQUALS,SystemConstant.MasterDataApprovalStatus.APPROVED));
		
		List<User> userList = userDAO.findAll(searchFilters);

		return userList;
	}

	public List<User> findUserForPicklist(int userType, List<SearchFilter> searchFilters) throws SystemException{
//		if(SystemConstant.UserType.EXTERNAL==userType){
//			return userDAO.findCompanyUserForPicklist(searchFilters);
//		}else{
			return userDAO.findBankUserForPicklist(searchFilters);
//		}
	}
	
	@Override
	public User findByPhoneNumber(String phoneNumber) throws SystemException{
		return userDAO.findByPhoneNo(phoneNumber);
	}
	
	@Override
	public User findByFirstName(String firstName) throws SystemException{
		return userDAO.findByFirstName(firstName);
	}

	public Boolean isBankUserExist(Long id, String username){
		return userDAO.isBankUserExist(id, username);
	}
	
	@Override
	public List<String> getBankUserEmails(long accessbility, Long locationId) throws SystemException{
		return userDAO.getBankUserEmails(accessbility, locationId);
	}
	
	@Override
	public void updateLastLogoutDate(Long userId) throws SystemException{
		userDAO.updateLastLogoutDate(userId);
	}
	
	@Override
	public int validateChangeTitle(Long id, boolean isMobile, String userName) throws SystemException{
		return userDAO.validateChangeTitle(id, isMobile, userName);
	}
	
	@Override
	public List<User> findAll() throws SystemException {
		List<User> result = userDAO.findAllUser();
		
		return result;
	}

	@Override
	public List<User> findByTitleCodeAndLocationId(Long locationId)
			throws SystemException {
		return userDAO.findByTitleCodeAndLocationId(locationId);
	}
	
	@Override
	public List<User> findByTitleCodeAndLocationIdPurna(Long locationId)
			throws SystemException {
		return userDAO.findByTitleCodeAndLocationIdPurna(locationId);
	}
	
	@Override
	public List<User> findByGeographyAssignment(String locationId, Integer lob)
			throws SystemException {
		return userDAO.findByGeographyAssignment(locationId, lob);
	}

	@Override
	public List<User> findAll(List<SearchFilter> searchFilters, List<SearchOrder> searchOrder) throws SystemException {
		return userDAO.findAll(searchFilters, searchOrder);
	}
	
//	Map<String, Role> roleMap = new HashMap<>();
	
//	@Override
//	public void saveFromWS(User user) throws Exception{
//		generateRoleMap();
//		List<Lookup> userTitleLookup = new ArrayList<>();
//		if (user.getTitle()!=null) {
//			Lookup titleLookup = lookupDAO.findByName(user.getTitle().getCodeAndName(), ILookupGroupConstant.USER_TITLE);
//			if (titleLookup !=null) {
//				userTitleLookup.add(titleLookup);
//			}
//		}
//		saveFromWS(user, userTitleLookup);
//	}
	
	private StringDigester digester;

	public void setDigester(StringDigester digester) {
		this.digester = digester;
	}
	
	public void saveFromWS(User user, List<Lookup> userTitleLookup) throws SystemException{
//		if (!SystemConstant.divisionList.contains(user.getDivision())) {
//			throw new Exception("Division not allowed");
//		}
		
//		if (!SystemParameter.structures.contains(user.getStructure()!=null?user.getStructure().toUpperCase():null)) {
//			throw new Exception("Structure not allowed");
//		}
//		Role uwmpUserRole = user.getRole();
		
		Lookup title = user.getTitle();
		User existingUser = userDAO.findByUsernameWithoutAnyCondition(user.getUserName());
		if (existingUser==null) {
			existingUser = new User();
			existingUser.setTitle(new Lookup());
			existingUser.setRole(new Role());

			//necessery defaulf fields but not provided by OUTSYSTEM
			user.setPassword(digester.digest(SystemParameter.DEFAULT_AAPL));
			Calendar c = Calendar.getInstance();
			c.setTime(DateTimeFunction.getCurrentDate());
			c.add(Calendar.DATE, SystemParameter.PASSWORD_LIFETIME);
			user.setPwdExpiredDate(c.getTime());
			user.setMustChangePassword(IUserConstant.NEW_USER);
			user.setStatus(SystemConstant.MasterDataStatus.masterDataStatusUwmpSync.get(!user.getDeleted()));
			user.setLock(false);
			user.setDeleted(false);
			//
		} else {
//			user = (User) CopyFunction.copyFields(existingUser, user, User.SYCN_LIST_FIELDS_EXCLUDE);
			user.setId(existingUser.getId());
			//necessery defaulf fields but not provided by OUTSYSTEM
			user.setPassword(existingUser.getPassword());
			user.setPwdExpiredDate(existingUser.getPwdExpiredDate());
			user.setMustChangePassword(existingUser.getMustChangePassword());
			user.setStatus(SystemConstant.MasterDataStatus.masterDataStatusUwmpSync.get(!user.getDeleted()));
			user.setLock(existingUser.getLock());
			user.setApkVersion(existingUser.getApkVersion());
			
			user.setLastLogInDate(existingUser.getLastLogInDate());
			user.setLatestApproval(existingUser.getLatestApproval()); //artinya, field ini di ignore, karena data uwmp untuk latest approval mereka kirim null
			user.setDeleted(existingUser.getDeleted());
			if (!(checkTitle(existingUser.getTitle(), user.getTitle()) || !existingUser.getLocationId().equals(user.getLocationId()))) {
				user.setGeographyAssignment(existingUser.getGeographyAssignment());
			}
		}

		
		
		user.setStructure(user.getStructure().toUpperCase());
		user.setLob(SystemConstant.ProjectType.enumLobList.get(user.getLobName().toUpperCase()));
		for (Lookup lookup : userTitleLookup) {
			if (title!=null && lookup.getCode().equals(title.getCode())) {
				if (existingUser.getTitle()==null || existingUser.getTitle().getId()!=lookup.getId()) {
					user.setTitle(lookup);
					break;
				}
			}
		}
//		Role role = roleDAO.findRoleByCode(SystemConstant.UserSyncTitle.ROLE_MAP.get(user.getTitle().getCode()));
		Role role = roleDAO.findRoleByCodeAndLob(title.getCode(), user.getLob()); //ROLE CODE = TITLE CODE
		if (role == null) {
			role = roleDAO.findRoleByCodeAndLob(SystemParameter.DEFAULT_ROLE_PURNA, user.getLob());
		}

		user.setRole(role);
		
		// allways null, uwmd doest send role
//		if (uwmpUserRole!=null && uwmpUserRole.getName()!=null&&!uwmpUserRole.getName().isEmpty()) {
//			if (roleMap.containsKey(uwmpUserRole.getName())) {
//				role = roleMap.get(uwmpUserRole.getName());
//			} else {
//				role = roleDAO.findRoleByName(uwmpUserRole.getName());
//				if (role!=null) {
//					roleMap.put(uwmpUserRole.getName(), role);
//				} else {
//					role = roleMap.get(SystemParameter.DEFAULT_ROLE_NAME);
//				}
//			}
			
//		}
//		if ((existingUser==null) ||existingUser.getRole().getId()!=role.getId()) {
//			user.setRole(role);
//		}
		
		//Maintenance
		existingUser=null;
		// avoiding save transient error if no tittle
		if (user.getTitle()== null || user.getTitle().getId()==null) {
			user.setTitle(null);
		}
		// avoiding save transient error if no role
//		if (user.getRole() == null && user.getRole().getId()==null) {
//			user.setRole(null);
//		}
		
		user.setIsFromUwmp(true);
		
		userDAO.saveOrUpdate(user);
	}
	
	@Override	
	public void saveListFromWS(List<User> users) throws SystemException{
//		generateRoleMap();
		List<Lookup> userTitleLookups = lookupDAO.findLookupByLookupGroup(ILookupGroupConstant.USER_TITLE_UWMP);
//		Role defaultRole =roleDAO.findRoleByCode("DEFAULT_ROLE_PURNA");
		for (User user : users) {
//			user.setRole(defaultRole);
			try {
				saveFromWS(user, userTitleLookups);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	/*private void generateRoleMap(){
		Role role = roleDAO.findRoleByName(SystemParameter.DEFAULT_ROLE_NAME);
		if (role!=null) {
			roleMap.put(role.getName(), role);
			roleMap.put(SystemParameter.DEFAULT_ROLE_NAME, role);
		}
	}*/
	
	@Override
	public Date findMaxUwmpSyncDate() throws SystemException {
		return userDAO.findMaxUwmpSyncDate();
	}

	@Override
	public User findByUsernameWithoutAnyCondition(String userName) {
		return userDAO.findByUsernameWithoutAnyCondition(userName);
	}
	
	@Override
	public Boolean checkIfUserEligibleForUpload(String userName) {
		User user = findByUsernameWithoutAnyCondition(userName);
		if (user != null) {
			if (user.getApprovalStatus()!=null&&user.getApprovalStatus().equals(SystemConstant.MasterDataApprovalStatus.PENDING)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean checkTitle(Lookup oldTitle, Lookup newTitle){
		if (oldTitle==null&&newTitle==null) {
			return false;
		} else {
			try {
				if (!oldTitle.getCode().equals(newTitle.getCode())) {
					return true;
				} else {
					return false;
				}
			} catch (NullPointerException e) {
				return false;
			}
		}
	}
	
}
