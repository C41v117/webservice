package com.metamorf.eform.interfaces.user;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.entity.settings.Lookup;
import com.metamorf.eform.entity.user.User;
import com.metamorf.eform.interfaces.core.IBaseService;

public interface IUserService extends IBaseService<User>{

	public User findByRoCode(String roCode) throws SystemException;
	public abstract User findByUserName(String userName) throws SystemException;
	public User findByUserNameClean(String userName) throws SystemException;
	public User findForBasicAuth(String userName) throws SystemException;
	public abstract User findByUserNameBO(String userName) throws SystemException;
	public abstract User findByUserNameFO(String userName,Long companyId) throws SystemException;
	public abstract User findByUserNameFO(String userName,String companyCode) throws SystemException;
	public abstract User findByUserNameBODeleted(String userName) throws SystemException;
	public abstract User findByUserNameFODeleted(String userName,String companyCode) throws SystemException;
	public abstract List<User> findByCompanyId(Long id) throws SystemException;
	public User findByIdAndToken(Long id, String token) throws SystemException;
	public abstract User findByIdEager(Long id) throws SystemException;
	public abstract void saveOrUpdateWithLDAP(User anObject) throws SystemException;
	public abstract boolean checkPassword(String userName, String password);
	public void updatePassword(String userName, String password);
	public void updatePasswordBO(String userName, String password);
	public void updatePasswordFO(Long companyId, String userName, String password);
	public List<User> findBankUserByAppFunctionId(Long appFunctionId) throws SystemException;
	public List<User> findUserByAppFunctionCompanyCommunity(Long appFunctionId, Long companyId, Long communityId) throws SystemException;
	public abstract List<User> findAllByUserName(List<String> usernames)
			throws SystemException;
	public Map<String,Object> validateUser(String userId,String userName);
	public void mergeRCASCompanyUser(List<User> companyUserList);
	HashMap<String, Object> validateRCAS(User user);
	public List<User> findRCASCompanyUser(String userId,String userName,String companyID);
	public PagingWrapper<User> findBankUserByDorman(int startNo, int offset, List<SearchOrder> searchOrder) throws SystemException;
	public List<User> findBankUserForAssignCommunity(List<User> userSelected);
	public List<User> findBankUserForAssignCommunity();
	public List<User> findUserForPicklist(int userType, List<SearchFilter> searchFilters) throws SystemException;
	public User findByPhoneNumber(String phoneNumber)  throws SystemException;
	public User findByFirstName(String firstName)  throws SystemException;
	public Boolean isBankUserExist(Long id, String username);
	public List<String> getBankUserEmails(long accessbility, Long locationId) throws SystemException;
	public void updateLastLogoutDate(Long userId) throws SystemException;
	public int validateChangeTitle(Long id, boolean isMobile, String userName) throws SystemException;
	
	public abstract List<User> findAll()throws SystemException;
	public List<User> findByTitleCodeAndLocationId(Long locationId) throws SystemException;
	public List<User> findAll(List<SearchFilter> searchFilters, List<SearchOrder> searchOrder) throws SystemException;
	public void saveListFromWS(List<User> users) throws SystemException;
//	public void saveFromWS(User user) throws Exception;
	public void saveFromWS(User user, List<Lookup> userTitleLookup) throws SystemException;
	public 	Date findMaxUwmpSyncDate() throws SystemException;
	public List<User> findByTitleCodeAndLocationIdPurna(Long locationId)throws SystemException;
	public User findByUserName(String userName, Boolean isDeleted) throws SystemException;
	public User findByUserNameForValidation(String userName) throws SystemException;
	public User findByUsernameWithoutAnyCondition(String userName);
	public Boolean checkIfUserEligibleForUpload(String userName);
	List<User> findByGeographyAssignment(String locationId, Integer lob) throws SystemException;
}