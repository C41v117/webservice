package com.metamorf.eform.data.access.user;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.data.access.core.IBaseDAO;
import com.metamorf.eform.entity.user.User;

public interface IUserDAO extends IBaseDAO<User>{
	public User findByRoCode(String roCode) throws SystemException;
	public User findByUserName(final String userName) throws SystemException;
	public User findByUserNameClean(final String userName) throws SystemException;
	public User findForBasicAuth(final String userName) throws SystemException;
	public User findByPhoneNo(final String phoneNo) throws SystemException;
	public User findByFirstName(String firstName) throws SystemException;
	public User findByUserNameBO(final String userName) throws SystemException;
	public User findByUserNameFO(String userName,Long companyId) throws SystemException;
	public User findByUserNameFO(String userName,String companyCode) throws SystemException;
	public User findByUserNameBODeleted(final String userName) throws SystemException;
	public User findByUserNameFODeleted(String userName,String companyCode) throws SystemException;
	public List<User> findByCompanyId(final Long id) throws SystemException;
	public abstract User findByPK(final Long id) throws SystemException;
	public abstract User findById(final Long id) throws SystemException;
	public abstract User findByIdEager(Long id) throws SystemException;
	public User findByIdAndToken(final Long id, final String token) throws SystemException;

	public abstract void saveOrUpdate(User anObject);
	public abstract List<User> findAll(int startNo, int offset,List<SearchFilter> filters, List<SearchOrder> orders)throws SystemException;
	public abstract PagingWrapper<User> findAllInternalUser(int startNo,int offset,List<SearchFilter> filter,List<SearchOrder> order) throws SystemException;
	public abstract PagingWrapper<User> findAllExternalUser(int startNo,int offset,List<SearchFilter> filter,List<SearchOrder> order) throws SystemException;
	public abstract Boolean isUserExist(Long id, String username, Long compId);
	public abstract Boolean isBankUserExist(Long id, String username);
	public boolean checkPassword(final String userName, final String password);
	public void updatePassword(String userName, String password);
	public void updatePasswordFO(Long companyId, String userName, String password);
	public void updatePasswordBO(String userName, String password);
	public List<User> findBankUserByAppFunctionId(Long appFunctionId) throws SystemException;
	public List<User> findUserByAppFunctionCompanyCommunity(Long appFunctionId, Long companyId, Long communityId)
			throws SystemException;
	public abstract List<User> findAllByUserName(List<SearchFilter> searchFilters)
			throws SystemException;
	public abstract List<User> findAll(List<SearchFilter> searchFilters)
			throws SystemException;
	public abstract List<User> findByIds(Long[] ids) throws SystemException;
	public PagingWrapper<User> findBankUserByDorman(int startNo, int offset, List<SearchOrder> searchOrder) throws SystemException;
	public List<User> findCompanyUserForPicklist(List<SearchFilter> searchFilters)
			throws SystemException;
	public List<User> findBankUserForPicklist(List<SearchFilter> searchFilters)
			throws SystemException;
	public List<String> getBankUserEmails(long accessbility, Long locationId) throws SystemException;
	public void updateLastLogoutDate(Long userId) throws SystemException;
	public int validateChangeTitle(Long id, boolean isMobile, String userName) throws SystemException;
	public abstract List<User> findAllUser() throws SystemException;
	public List<User> findByTitleCodeAndLocationId(Long locationId) throws SystemException;
	public List<User> findAll(List<SearchFilter> searchFilters, List<SearchOrder> searchOrders) throws SystemException;
	public Date findMaxUwmpSyncDate() throws SystemException;
	public List<User> findByTitleCodeAndLocationIdPurna(Long locationId) throws SystemException;
	public User findByUsernameWithoutAnyCondition(String userName) throws SystemException;
	Set<String> findDownLineMultiLevel(String userName);
	public User findByUserName(String userName, Boolean isDeleted) throws SystemException;
	List<User> findByGeographyAssignment(String locationId, Integer lob) throws SystemException;
}