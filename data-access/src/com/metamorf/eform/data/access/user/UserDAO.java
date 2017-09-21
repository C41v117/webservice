package com.metamorf.eform.data.access.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.ResultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.metamorf.eform.common.core.SystemConstant;
import com.metamorf.eform.common.core.SystemConstant.MasterDataStatus;
import com.metamorf.eform.common.core.SystemParameter;
import com.metamorf.eform.common.data.util.Operator;
import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.data.util.SearchOrder.Sort;
import com.metamorf.eform.common.enumer.UserType;
import com.metamorf.eform.common.exception.ErrorHolder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingUtil;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.common.util.DateTimeFunction;
import com.metamorf.eform.common.util.StringFunction;
import com.metamorf.eform.data.access.core.AbstractHibernate4DAO;
import com.metamorf.eform.data.access.core.DynamicResultTransformer;
import com.metamorf.eform.data.access.core.SearchAlias;
import com.metamorf.eform.entity.user.User;

public class UserDAO extends AbstractHibernate4DAO<User, Long> implements IUserDAO {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDAO.class);
	
	//DO NOT USE THIS FOR OTHER THAN APPROVAL PROCESS
	static final String GET_BANK_USER_BY_APP_FUNCTION = 
			"select au.id, au.user_name, au.COMPANY_ID "+ 
			"	from app_role_function arf  "+
			"	inner join app_role ar on arf.role_id = ar.id "+
			"	inner join app_user au on ar.id = au.role_id "+
			"	where arf.app_function_id = ?  "+
			"	and au.type = 1 				 "+
			"	and au.status = 1				 "+
			"	and au.is_deleted = 0 		 ";
	
	//DO NOT USE THIS FOR OTHER THAN APPROVAL PROCESS
	static final String GET_USER_BY_APP_FUNCTION_COMP_COMM = 
			"select au.id, au.user_name, au.email "+ 
			"	from app_role_function arf  "+
			"	inner join app_role ar on arf.role_id = ar.id "+
			"	inner join app_user au on ar.id = au.role_id " +
			"	where arf.app_function_id = ?  "+
			"	and au.type = 2 				 "+
			"	and au.status = 1				 "+
			"   and au.COMPANY_ID = ? "+
			"	and au.is_deleted = 0 		 "+
			"	and exists(select null from vw_community_member_user cmtu "+
			"				where cmtu.user_id = au.id and cmtu.company_id = au.company_id  "+
			"					and cmtu.community_id = ? )";
	
	static final String GET_BANK_USER_BY_DORMAN = 
			"select id, user_name, full_name, approval_status, latest_suggestion, latest_suggestor, latest_approval, latest_approver, status, last_login_date "+
			"from app_user "+ 
			"where "+
			"	is_super = "+SystemConstant.NOPOWER+" and "+
			"	type = 1 and "+ 
			"	approval_status = 1 and "+ 
			"	status = 1 and "+
			"	is_deleted = 0 and "+
			"	days(current_date) - days(COALESCE(last_login_date, LATEST_APPROVAL)) >= (select a.value from app_parameter a where a.name = 'DAYS_NOT_LOGIN') ";
	
	static final String COUNT_BANK_USER_BY_DORMAN = 
			"select count(*) "+
			"from app_user "+ 
			"where "+
			"	is_super = "+SystemConstant.NOPOWER+" and "+
			"	type = 1 and "+ 
			"	approval_status = 1 and "+ 
			"	status = 1 and "+
			"	is_deleted = 0 and "+
			"	days(current_date) - days(COALESCE(last_login_date, LATEST_APPROVAL)) >= (select a.value from app_parameter a where a.name = 'DAYS_NOT_LOGIN') ";

	static final String GET_USER_BY_TITLE_CODE_AND_LOCATION_ID=
			" select au.id, "+
			" au.EMAIL, "+
			" au.FULL_NAME "+
			" from app_user au join lookup l on au.TITLE = l.ID "+
			" where l.CODE in (:code) and  "+
			" au.LOCATION_ID in (select AREA_ID from dbo.branch_location where id = idBranch) ";
	
	static final String GET_USER_BY_TITLE_CODE_AND_LOCATION_ID_PURNA=
			" select au.id, "+
			" au.EMAIL,  "+
			" au.FULL_NAME "+
			" from app_user au join lookup l on au.TITLE = l.ID  "+
			" where l.CODE IN (:code) and  "+
			" au.LOCATION_ID in (select POP_ID from AREA where ID in "+
			" (select AREA_ID from dbo.branch_location where POP_ID = :popId))  "+
			" and au.status = (:status) "+
			" union "+
			" select au.id, "+
			" au.EMAIL,  "+
			" au.FULL_NAME "+
			" from app_user au join lookup l on au.TITLE = l.ID  "+
			" where l.CODE IN(:code) and  "+
			" au.LOCATION_ID in (select POP_ID from AREA where ID in "+
			" (select AREA_ID from branch_location bl join sub_branch sb on bl.ID = sb.BRANCH_LOCATION_ID where sb.POP_ID = :popId))  "+
			" and au.status = (:status) ";
	
	static final String GET_USER_GEOGRAPHY_ASSIGNMENT=
			" select au.id, "+
			" au.EMAIL, "+
			" au.FULL_NAME "+
			" from app_user au join lookup l on au.TITLE = l.ID "+
			" where l.CODE in (:code) and  "+
			" au.GEOGRAPHY_ASSIGNMENT like :popId"+ 
			" and au.lob = :lob "+
			" and au.status = :status ";
	
	@Override
	public User findByUserName(final String userName) throws SystemException{
		return findByUserName(userName, false);
	}
	
	@Override
	public User findByUserName(final String userName, Boolean isDeleted) throws SystemException{
		try{
			Criteria crit = getSession().createCriteria(User.class);
					crit.add(Restrictions.eq(User.USER_NAME, userName)).setFetchMode(User.ROLE, FetchMode.JOIN)
					.setFetchMode(User.TITLE, FetchMode.JOIN)
					.add(Restrictions.ne(User.STATUS, SystemConstant.MasterDataStatus.INACTIVE))
					.add(Restrictions.ne(User.ACCOUNT_LOCK, SystemConstant.UserLock.IS_LOCK))
					.setFetchMode(User.ROLE, FetchMode.JOIN);
					if (isDeleted!=null) {
						crit.add(Restrictions.eq(User.DELETED, isDeleted));
					}
					
					return (User) crit.uniqueResult();
		}catch(HibernateException e){
			LOGGER.error(e.getMessage());
			throw new SystemException(new ErrorHolder("error.user.not.found"));
		}
	}
	
	@Override
	public User findByUsernameWithoutAnyCondition(final String userName) throws SystemException{
		return (User) getSession().createCriteria(User.class)
				.add(Restrictions.eq(User.USER_NAME, userName))
				.uniqueResult();
	}
	
	@Override
	public User findByRoCode(String roCode) throws SystemException{
		User user = null;
		try{
			user = (User) getSession().createCriteria(User.class)
					.add(Restrictions.eq(User.RO_CODE, roCode))
					.uniqueResult();
		}catch(HibernateException e){
			LOGGER.error(e.getMessage());
			throw new SystemException(new ErrorHolder("error.user.not.found"));
		}
		
		return user;
	}
	
	@Override
	public User findByUserNameClean(final String userName) throws SystemException{
		User user = null;
		try{
			user = (User) getSession().createCriteria(User.class)
					.add(Restrictions.eq(User.USER_NAME, userName))
					.add(Restrictions.eq(User.DELETED, Boolean.FALSE))
					.uniqueResult();
		}catch(HibernateException e){
			LOGGER.error(e.getMessage());
			throw new SystemException(new ErrorHolder("error.user.userName.not.found"));
		}
		
		return user;
	}
	
	@Override
	public User findForBasicAuth(final String userName) throws SystemException{
		User user = null;
		try{
			user = (User) getSession().createCriteria(User.class)
					.add(Restrictions.eq(User.USER_NAME, userName))
					.add(Restrictions.ne(User.STATUS, SystemConstant.MasterDataStatus.INACTIVE))
					.setFetchMode(User.ROLE, FetchMode.JOIN)
					.add(Restrictions.eq(User.DELETED, Boolean.FALSE))
					.uniqueResult();
		}catch(HibernateException e){
			LOGGER.error(e.getMessage());
			throw new SystemException(new ErrorHolder("error.user.not.found"));
		}
		
		return user;
	}
	
	@Override
	public User findByPhoneNo(final String phoneNo) throws SystemException{
		User user = null;
		try{
			user = (User) getSession().createCriteria(User.class)
					.add(Restrictions.eq(User.PHONE_NO, phoneNo))
					.add(Restrictions.eq(User.DELETED, Boolean.FALSE))
					.setFetchMode(User.ROLE, FetchMode.JOIN)
					.setFetchMode(User.AREA, FetchMode.JOIN)
					.uniqueResult();
		}catch(HibernateException e){
			LOGGER.error(e.getMessage());
			throw new SystemException(new ErrorHolder("error.user.not.found"));
		}
		
		return user;
	}
	
	@Override
	public User findByFirstName(final String firstName) throws SystemException{
		User user = null;
		try{
			user = (User) getSession().createCriteria(User.class)
					.add(Restrictions.eq(User.FIRST_NAME, firstName))
					.uniqueResult();
		}catch(HibernateException e){
			LOGGER.error(e.getMessage());
			throw new SystemException(new ErrorHolder("error.user.not.found"));
		}
		
		return user;
	}
	
	@Override
	public User findByUserNameBO(final String userName) throws SystemException{
		User user = null;
		try{
//			System.out.println("User Type : "+UserType.BANK_USER.ordinal());
			user = (User) getSession().createCriteria(User.class)
					.add(Restrictions.eq(User.USER_NAME, userName))
					.setFetchMode(User.ROLE, FetchMode.JOIN)
					.setFetchMode(User.TITLE, FetchMode.JOIN)
//					.add(Restrictions.eq(User.STATUS,SystemConstant.MasterDataStatus.ACTIVE))
					.add(Restrictions.eq(User.DELETED, Boolean.FALSE))
					.uniqueResult();
		}catch(HibernateException e){
			LOGGER.error(e.getMessage());
			throw new SystemException(new ErrorHolder("error.user.not.found"));
		}
		
		return user;
	}
	
	@Override
	public User findByUserNameBODeleted(final String userName) throws SystemException{
		User user = null;
		try{
			user = (User) getSession().createCriteria(User.class)
					.add(Restrictions.eq(User.USER_NAME, userName))
					.add(Restrictions.eq(User.TYPE, UserType.BANK_USER))
					.setFetchMode(User.ROLE, FetchMode.JOIN)
					.add(Restrictions.eq(User.DELETED, Boolean.TRUE))
					.uniqueResult();
		}catch(HibernateException e){
			LOGGER.error(e.getMessage());
			throw new SystemException(new ErrorHolder("error.user.not.found"));
		}
		
		return user;
	}
	
	@Override
	public User findByUserNameFO(String userName,Long companyId){
		return (User) getSession().createCriteria(User.class)
				.createAlias(User.COMPANY, User.COMPANY, JoinType.LEFT_OUTER_JOIN)
				.createAlias(User.ROLE, User.ROLE, JoinType.LEFT_OUTER_JOIN)
				.add(Restrictions.eq(User.USER_NAME, userName))
				.add(Restrictions.eq(User.TYPE, SystemConstant.UserType.EXTERNAL))
				.add(Restrictions.eq(User.COMPANY_ID, companyId))
				.add(Restrictions.eq(User.DELETED, Boolean.FALSE))
				.uniqueResult();
	}
	
	@Override
	public User findByUserNameFO(String userName,String companyCode){
		return (User) getSession().createCriteria(User.class)
				.createAlias(User.COMPANY, User.COMPANY, JoinType.LEFT_OUTER_JOIN)
				.createAlias(User.ROLE, User.ROLE, JoinType.LEFT_OUTER_JOIN)
				.add(Restrictions.eq(User.USER_NAME, userName))
				.add(Restrictions.eq(User.TYPE, SystemConstant.UserType.EXTERNAL))
				.add(Restrictions.eq(User.COMPANY_CODE, companyCode))
				.add(Restrictions.eq(User.DELETED, Boolean.FALSE))
				.uniqueResult();
	}
	
	@Override
	public User findByUserNameFODeleted(String userName,String companyCode){
		return (User) getSession().createCriteria(User.class)
				.createAlias(User.COMPANY, User.COMPANY, JoinType.LEFT_OUTER_JOIN)
				.createAlias(User.ROLE, User.ROLE, JoinType.LEFT_OUTER_JOIN)
				.add(Restrictions.eq(User.USER_NAME, userName))
				.add(Restrictions.eq(User.TYPE, SystemConstant.UserType.EXTERNAL))
				.add(Restrictions.eq(User.COMPANY_CODE, companyCode))
				.add(Restrictions.eq(User.DELETED, Boolean.TRUE))
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByCompanyId(Long id) throws SystemException {
		ProjectionList projectionList = Projections.projectionList();
		for (String fetchedProperty : User.MAINTENANCE_DETAIL_FIELDS) {
			projectionList.add(Projections.property(fetchedProperty));
		}
		
		List<User> list = getSession().createCriteria(User.class)
				.add(Restrictions.eq(User.COMPANY_ID, id))
				.add(Restrictions.eq(User.DELETED, Boolean.FALSE))
				.createAlias(User.ROLE, User.ROLE, JoinType.INNER_JOIN)
				.createAlias(User.COMPANY, User.COMPANY, JoinType.INNER_JOIN)
				.setProjection(projectionList)
				.setResultTransformer(new DynamicResultTransformer<User>(User.class, User.MAINTENANCE_DETAIL_FIELDS, User.MAINTENANCE_JOIN_ENTITIES))
				.list();
		return list;
	}
	
	@Override
	public User findByIdAndToken(final Long id, final String token) throws SystemException{
		User user = null;
		try{
			user = (User) getSession().createCriteria(User.class)
					.add(Restrictions.eq(User.TOKEN, token))
					.add(Restrictions.eq(User.DELETED, Boolean.FALSE))
					.add(Restrictions.isNotNull(User.TOKEN))
					.add(Restrictions.eq(User.ID, id)).setFetchMode(User.ROLE, FetchMode.JOIN)
					.uniqueResult();
		}catch(HibernateException e){
			LOGGER.error(e.getMessage());
			throw new SystemException(new ErrorHolder("error.user.not.found"));
		}
		
		return user;
	}
	
	@Override
	public void saveOrUpdate(User user) {
		if(user.getId()==null) {
			user.setIsSuper(SystemConstant.NOPOWER);
			super.create(user);
		} else {
			if(user.getIsSuper()!=SystemConstant.SUPERPOWER){
				super.update(user);
			}
		}
	}
	
	public List<User> findAll(){
		return super.findAll();
	}

	@Override
	public List<User> findAll(int startNo, int offset,List<SearchFilter> filter, List<SearchOrder> order)throws SystemException {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
		detachedCriteria.setFetchMode(User.ROLE, FetchMode.JOIN);
		SearchAlias[] searchAliases = new SearchAlias[2];
		searchAliases[0] = new SearchAlias(User.ROLE, User.ROLE);
		searchAliases[1] = new SearchAlias(User.TITLE, User.TITLE, JoinType.LEFT_OUTER_JOIN);
		return super.findByFilter(detachedCriteria, searchAliases, User.MAINTENANCE_LIST_FIELDS, filter, order, startNo, offset, null);
	}
	
	@Override
	public PagingWrapper<User> findAllExternalUser(int startNo, // NOPMD by AGE-SYSTEM on 9/30/13 10:39 AM, it's okay
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.createAlias(User.USER_ACCESS, User.USER_ACCESS, JoinType.LEFT_OUTER_JOIN);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		List<Criterion> criterions = new LinkedList<Criterion>();
		
		if (null != filter) {
			for (SearchFilter fltr : filter) {
				criteria.add(buildCriterion(fltr));
				criterions.add(buildCriterion(fltr));
			}
		}
		
		if(null != order){
			for (SearchOrder searchOrder : order) {
				criteria.addOrder(buildOrder(searchOrder));
			}
		}
		
		SearchAlias[] searchAlias = new SearchAlias[1];
		searchAlias[0] = new SearchAlias(User.USER_ACCESS, User.USER_ACCESS);
		
		long totalRecords = getRowCount(searchAlias,criterions);
		// normalize startIndex (in case startIndex > totalRecords/maxRow)
		if (startNo > totalRecords) {
			startNo = PagingUtil.getLastPageStartRow(totalRecords, offset);
		}		
		
		List<User> users = (List<User>)criteria.list();
		return new PagingWrapper<User>(users, totalRecords, startNo, offset);
	}
	
	@Override
	public PagingWrapper<User> findAllInternalUser(int startNo,
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		
		/*DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
		detachedCriteria.setFetchMode(User.ROLE, FetchMode.JOIN);*/
		SearchAlias[] searchAliases = new SearchAlias[2];
		searchAliases[0] = new SearchAlias(User.ROLE, User.ROLE);
		searchAliases[1] = new SearchAlias(User.TITLE, User.TITLE, JoinType.LEFT_OUTER_JOIN);
		/*return super.findAllWithPagingWrapper(detachedCriteria, startNo, offset, searchAliases, User.MAINTENANCE_LIST_FIELDS, filter, order, null);*/
		return super.findFetchedPropertyWithPagingWrapper(startNo, offset, searchAliases, User.MAINTENANCE_LIST_FIELDS, filter, order, null, true);
		/*return super.findFetchedPropertyWithPagingWrapper(startNo, offset, null, User.MAINTENANCE_LIST_FIELDS, filter, order, null, false);*/
	}

	@Override
	public User findById(Long id) throws SystemException {
		String[] lazyProps = new String[2];
		lazyProps[0] = User.ROLE;
		lazyProps[1] = User.TITLE;
		return super.findByPK(id, lazyProps);
	}
	
	/*please note that this method does not return user with role and company*/
	@Override
	public List<User> findByIds(Long[] ids) throws SystemException {
		List<SearchFilter> searchFilters = new ArrayList<SearchFilter>();
		searchFilters.add(new SearchFilter(User.ID, Operator.IN_ARRAY, ids));
		return super.findAll(searchFilters, new ArrayList<SearchOrder>(), null);
	}
	
	@Override
	public User findByIdEager(Long id) throws SystemException {
		String[] lazyProps = new String[3];
		lazyProps[0] = User.ROLE;
		lazyProps[1] = User.USER_ACCESS;
		lazyProps[2] = User.COMPANY;
		return super.findByPK(id, lazyProps);
	}
	
	@Override
	public Boolean isUserExist(Long id, String username, Long compId){
		List<Criterion> criterias = new ArrayList<Criterion>();
		criterias.add(Restrictions.eq(User.USER_NAME, username));
		if(null!=id){
			criterias.add(Restrictions.ne(User.ID, id));
		}
		if(null!=compId){
			criterias.add(Restrictions.eq(User.COMPANY_ID, compId));
		}
		criterias.add(Restrictions.eq(User.DELETED, Boolean.FALSE));
		return getRowCount(criterias)>0?Boolean.TRUE:Boolean.FALSE;
	}
	
	@Override
	public Boolean isBankUserExist(Long id, String username){
		List<Criterion> criterias = new ArrayList<Criterion>();
		criterias.add(Restrictions.eq(User.USER_NAME, username.toUpperCase()));
		//criterias.add(Restrictions.eq(User.TYPE, UserType.BANK_USER));
//		criterias.add(Restrictions.eq(User.DELETED, Boolean.FALSE));
		if(null!=id){
			criterias.add(Restrictions.ne(User.ID, id));
		}
		return getRowCount(criterias)>0?Boolean.TRUE:Boolean.FALSE;
	}
	
	@Override
	public boolean checkPassword(String userName, String password){
		List<Criterion> criterias = new ArrayList<Criterion>();
		criterias.add(Restrictions.eq(User.USER_NAME, userName));
		criterias.add(Restrictions.eq("password", password));
		criterias.add(Restrictions.eq(User.DELETED, Boolean.FALSE));
		return getRowCount(criterias)>0?Boolean.TRUE:Boolean.FALSE;
	}
	
	@Override
	public void updatePassword(String userName, String password){
		User user = findByUserName(userName);
		changePasswordUpdate(user);
		user.setPassword(password);
		super.update(user);
	}
	
	@Override
	public void updatePasswordBO(String userName, String password){
		User user = findByUserNameBO(userName);
		changePasswordUpdate(user);
		user.setPassword(password);
		super.update(user);
	}
	
	private void changePasswordUpdate(User user){
		user.setMustChangePassword(0);
		Calendar c = Calendar.getInstance();
		c.setTime(DateTimeFunction.getCurrentDate());
		c.add(Calendar.DATE, SystemParameter.PASSWORD_LIFETIME);
		user.setPwdExpiredDate(c.getTime());
	}
	
	@Override
	public void updatePasswordFO(Long companyId, String userName, String password){
		User user = findByUserNameFO(userName, companyId);
		changePasswordUpdate(user);
		user.setPassword(password);
		super.update(user);
	}

	@Override
	public PagingWrapper<User> findAllByFilter(int startNo, int offset,
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		SearchAlias[] searchAliases = new SearchAlias[1];
		searchAliases[0] = new SearchAlias(User.ROLE, User.ROLE, JoinType.LEFT_OUTER_JOIN);
		return super.findFetchedPropertyWithPagingWrapper(startNo, offset, searchAliases, User.MAINTENANCE_LIST_FIELDS, searchFilters, searchOrders, null, false);
	}

	@Override
	public void deleteObject(User anObject) throws SystemException {
		// TODO Auto-generated method stub	
	}

	@Override
	public List<User> findBankUserByAppFunctionId(Long appFunctionId)
			throws SystemException {
		return getSession().createSQLQuery(GET_BANK_USER_BY_APP_FUNCTION)
				.setParameter(0, appFunctionId.longValue())
				.setResultTransformer(
						new ResultTransformer() {
							@Override
							public Object transformTuple(Object[] results, String[] aliases) {
								User user = new User();
								user.setId(Long.valueOf(String.valueOf(results[0])));
								user.setUserName(String.valueOf(results[1]));
								return user;
							}
							@Override
							public List transformList(List list) {
								return list;
							}
				}).list();
	}

	@Override
	public List<User> findUserByAppFunctionCompanyCommunity(Long appFunctionId, Long companyId, Long communityId)
			throws SystemException {
		return getSession().createSQLQuery(GET_USER_BY_APP_FUNCTION_COMP_COMM)
				.setParameter(0, appFunctionId)
				.setParameter(1, companyId)
				.setParameter(2, communityId)
				.setResultTransformer(
						new ResultTransformer() {
							@Override
							public Object transformTuple(Object[] results, String[] aliases) {
								User user = new User();
								user.setId(Long.valueOf(String.valueOf(results[0])));
								user.setUserName(String.valueOf(results[1]));
								user.setEmail(String.valueOf(results[2]));
								return user;
							}
							@Override
							public List transformList(List list) {
								return list;
							}
				}).list();
	}
	
	@Override
	public List<User> findAllByUserName(List<SearchFilter> searchFilters) throws SystemException {
		return super.findAll(searchFilters, null, null);
	}
	
	@Override
	public List<User> findAll(List<SearchFilter> searchFilters) throws SystemException {
		return super.findAll(searchFilters, null, null);
	}
	
	@Override
	public List<User> findAll(List<SearchFilter> searchFilters, List<SearchOrder> searchOrders) throws SystemException {
		return super.findAll(searchFilters, searchOrders, null);
	}
	
	@Override
	public List<User> findCompanyUserForPicklist(List<SearchFilter> searchFilters) throws SystemException {
		List<SearchOrder> searchOrders = new ArrayList<SearchOrder>();
		searchOrders.add(new SearchOrder(User.COMPANY_CODE, Sort.ASC));
		searchOrders.add(new SearchOrder(User.USER_NAME, Sort.ASC));
		SearchAlias[] searchAlias = new SearchAlias[]{new SearchAlias(User.COMPANY, User.COMPANY)};
		String[] joinEntity = new String[]{User.COMPANY};
		searchFilters.add(new SearchFilter(User.STATUS, Operator.EQUALS, SystemConstant.MasterDataStatus.ACTIVE));
		searchFilters.add(new SearchFilter(User.DELETED, Operator.EQUALS, Boolean.FALSE));
		return super.findFetchedPropertyList(searchAlias, User.COMPANY_USER_PICK_LIST_FIELDS, searchFilters, searchOrders, null, joinEntity);
	}
	
	public List<User> findBankUserForPicklist(List<SearchFilter> searchFilters) throws SystemException {
		List<SearchOrder> searchOrders = new ArrayList<SearchOrder>();
		searchOrders.add(new SearchOrder(User.USER_NAME, Sort.ASC));
		searchFilters.add(new SearchFilter(User.STATUS, Operator.EQUALS, SystemConstant.MasterDataStatus.ACTIVE));
		searchFilters.add(new SearchFilter(User.DELETED, Operator.EQUALS, Boolean.FALSE));
		searchFilters.add(new SearchFilter(User.TYPE, Operator.EQUALS, UserType.BANK_USER));
		searchFilters.add(new SearchFilter(User.COMPANY, Operator.IS_NULL, UserType.BANK_USER));
		return super.findFetchedPropertyList(null, User.BANK_USER_PICK_LIST_FIELDS, searchFilters, searchOrders, null);
	}
	
	private String createOrder(SearchOrder searchOrder) {
		SearchOrder _searchOrder = searchOrder;
		String fieldName = searchOrder.getFieldName();
		String sqlFieldName = getSqlFieldName(fieldName);
		if(StringFunction.checkString(sqlFieldName)!=null){
			_searchOrder.setFieldName(sqlFieldName);
			return buildOrder(_searchOrder).toString();
		}else{
			return "";
		}
	}
	
	private String getSqlFieldName(String fieldName){
		String sqlFieldName = "";
		if(fieldName.equals(User.USER_NAME)){
			sqlFieldName = "USER_NAME";
		}else if(fieldName.equals(User.FULL_NAME)){
			sqlFieldName = "FULL_NAME";
		}else if(fieldName.equals(User.STATUS)){
			sqlFieldName = "STATUS";
		}else if(fieldName.equals(User.APPROVAL_STATUS)){
			sqlFieldName = "APPROVAL_STATUS";
		}else if(fieldName.equals(User.LATEST_SUGGESTION)){
			sqlFieldName = "LATEST_SUGGESTION";
		}else if(fieldName.equals(User.LATEST_SUGGESTOR)){
			sqlFieldName = "LATEST_SUGGESTOR";
		}else if(fieldName.equals(User.LATEST_APPROVAL)){
			sqlFieldName = "LATEST_APPROVAL";
		}else if(fieldName.equals(User.LATEST_APPROVER)){
			sqlFieldName = "LATEST_APPROVER";
		}else if(fieldName.equals(User.LAST_LOGIN_DATE)){
			sqlFieldName = "LAST_LOGIN_DATE";
		}
		return sqlFieldName;
	}
	
	@SuppressWarnings({ "unchecked", "serial" })
	@Override
	public PagingWrapper<User> findBankUserByDorman(int startNo, int offset, List<SearchOrder> searchOrders) throws SystemException {
		
		StringBuilder _SQLORDER = new StringBuilder();
		
		if(searchOrders!=null){
			if(searchOrders.size()>0){
				_SQLORDER.append(" ORDER BY ");
			}
			int count = 0;
			for (SearchOrder searchOrder : searchOrders) {
				String _result = createOrder(searchOrder).toString();
				if(StringFunction.checkString(_result)!=null){
					count++;
					_SQLORDER.append(_result).append(" ");
					_SQLORDER.append(", ");
				}
			}
			if(count==0){
				_SQLORDER = new StringBuilder();
			}else{
				//remove last ,
				_SQLORDER.replace(_SQLORDER.lastIndexOf(", "), _SQLORDER.length(), "");
			}
		}
		
		StringBuilder _SQL = new StringBuilder();
		_SQL.append(GET_BANK_USER_BY_DORMAN);
		
		if(_SQLORDER.length()>0){
			_SQL.append(_SQLORDER.toString());
		}
		
				SQLQuery queryData = getSession().createSQLQuery(_SQL.toString());
				SQLQuery queryCount = getSession().createSQLQuery(COUNT_BANK_USER_BY_DORMAN);

				
				long _totalRecords = Long.valueOf(String.valueOf(queryCount.uniqueResult()));
				
				if (startNo>0) {
					queryData.setFirstResult(startNo - 1);
				}
				
				if (offset>0) {
					queryData.setMaxResults(offset);
				}
				
				List<User> listResult = queryData		
				.setResultTransformer(
						new ResultTransformer() {
							@Override
							public Object transformTuple(Object[] results, String[] aliases) {
								User user = new User();
								user.setId(Long.valueOf(String.valueOf(results[0])));
								user.setUserName(String.valueOf(results[1]));
								user.setFullName(String.valueOf(results[2]));
								user.setApprovalStatus(Integer.valueOf(String.valueOf(results[3])));
								user.setLatestSuggestion(DateTimeFunction.string2Date(String.valueOf(results[4]), SystemConstant.WEB_SERVICE_DATETIME));
								user.setLatestSuggestor(String.valueOf(results[5]));
								user.setLatestApproval(DateTimeFunction.string2Date(String.valueOf(results[6]), SystemConstant.WEB_SERVICE_DATETIME));
								user.setLatestApprover(String.valueOf(results[7]));
								user.setStatus(Integer.valueOf(String.valueOf(results[8])));
								user.setLastLogInDate(DateTimeFunction.string2Date(String.valueOf(results[9]), SystemConstant.WEB_SERVICE_DATETIME));
								return user;
							}
							@SuppressWarnings("rawtypes")
							@Override
							public List transformList(List list) {
								return list;
							}
				}).list();
		return new PagingWrapper<User>(listResult, _totalRecords, startNo, offset);		
	}
	
	static final String GET_BANK_USER_EMAIL = 
			"select distinct email from app_user where ROLE_ID in "+ 
			"	(select ROLE_ID from app_role_function where APP_FUNCTION_ID = ?) "+ 
			"	and LOCATION_ID = ? and TITLE in (select ID from lookup where DESCR = 'Branch') "+ 
			"	and STATUS = 1 and IS_LOCK = 0 "+ 
			"	UNION "+ 
			"	select distinct email from app_user where ROLE_ID in "+  
			"	(select ROLE_ID from app_role_function where APP_FUNCTION_ID = ?) "+ 
			"	and LOCATION_ID in (select AREA_ID from branch_location where ID = ?) "+ 
			"	and TITLE in (select ID from lookup where DESCR = 'Area') "+ 
			"	and STATUS = 1 and IS_LOCK = 0 "+ 
			"	UNION "+ 
			"	select distinct email from app_user where ROLE_ID in "+  
			"	(select ROLE_ID from app_role_function where APP_FUNCTION_ID = ?) "+ 
			"	and LOCATION_ID in (select REGION_ID from area where ID in "+ 
			"	(select AREA_ID from branch_location where ID = ?)) "+ 
			"	and TITLE in (select ID from lookup where DESCR = 'Region') "+ 
			"	and STATUS = 1 and IS_LOCK = 0 "; 
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getBankUserEmails(long accessbility, Long locationId) throws SystemException {
		return getSession().createSQLQuery(GET_BANK_USER_EMAIL)
				.setParameter(0, accessbility)
				.setParameter(1, locationId)
				.setParameter(2, accessbility)
				.setParameter(3, locationId)
				.setParameter(4, accessbility)
				.setParameter(5, locationId).list();
	}
	
	static final String UPDATE_LAST_LOGOUT_DATE = 
			"UPDATE app_user "
			+ "SET last_logout_date = :lastLogOutDate "
			+ "WHERE id = :userId";
	
	@Override
	public void updateLastLogoutDate(Long userId) throws SystemException{
		StringBuffer queryString = new StringBuffer(UPDATE_LAST_LOGOUT_DATE);
		
		// Make Sure only update last log out date when userId is not NULL.
		if(userId != null){
			SimpleDateFormat sdf = new SimpleDateFormat(SystemParameter.SQL_FORMAT_WITH_TIME);
			Query query = getSession().createSQLQuery(queryString.toString()).
					setParameter("lastLogOutDate", sdf.format(new Date())).
					setParameter("userId", userId);
			query.executeUpdate();
		}else{
			
		}
	}

	static final String VALIDATE_CHANGE_TITLE_MOBILE = 
			"SELECT count(1) FROM customer c JOIN running_approval ra ON c.workflow_id = ra.wf_id "
			+ " WHERE ra.is_finished = 0 and c.agent_id = ? ";

	static final String VALIDATE_CHANGE_TITLE_BANK = 
			"SELECT count(1) FROM running_approval WHERE is_finished = 0 and assignee = ? ";
	
	@Override
	public int validateChangeTitle(Long id, boolean isMobile, String userName) throws SystemException{
		StringBuffer queryString;
		
		if(isMobile){
			queryString = new StringBuffer(VALIDATE_CHANGE_TITLE_MOBILE);
			return (int) getSession().createSQLQuery(queryString.toString()).
					setParameter(0, id).uniqueResult();
		}
		else{
			queryString = new StringBuffer(VALIDATE_CHANGE_TITLE_BANK);
			return (int) getSession().createSQLQuery(queryString.toString()).
					setParameter(0, userName).uniqueResult();
		}
	}

	@Override
	public List<User> findAllUser()
			throws SystemException {
		return super.findAll();
	}

	@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	@Override
	public List<User> findByTitleCodeAndLocationId(Long locationId)
			throws SystemException {
		String[] code = SystemParameter.USER_TITLE_EDD.trim().split("\\s*,\\s*");
		List<String> codeList = Arrays.asList(code);
		Query query = getSession().createSQLQuery(GET_USER_BY_TITLE_CODE_AND_LOCATION_ID).setParameterList("code", codeList)
				.setParameter("idBranch", locationId);
		
		query.getQueryString();
		query.setResultTransformer(
						new ResultTransformer() {
							@Override
							public Object transformTuple(Object[] results, String[] aliases) {
								User user = new User();
								user.setId(Long.valueOf(String.valueOf(results[0])));
								user.setEmail(String.valueOf(results[1]));
								user.setFullName(String.valueOf(results[2]));
								return user;
							}
							public List transformList(List list) {
								return list;
							}
				});
		List<User> result = query.list();
		return result;
	}
	
	
	@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	@Override
	public List<User> findByTitleCodeAndLocationIdPurna(Long locationId)
			throws SystemException {
		String[] code = SystemParameter.USER_TITLE_EDD.trim().split("\\s*,\\s*");
		List<String> codeList = Arrays.asList(code);
		Query query = getSession().createSQLQuery(GET_USER_BY_TITLE_CODE_AND_LOCATION_ID_PURNA).
				setParameterList("code", codeList).setParameter("popId", locationId).setParameter("status", MasterDataStatus.ACTIVE).
				setParameterList("code", codeList).setParameter("popId", locationId).setParameter("status", MasterDataStatus.ACTIVE);
		query.getQueryString();
		query.setResultTransformer(
						new ResultTransformer() {
							@Override
							public Object transformTuple(Object[] results, String[] aliases) {
								User user = new User();
								user.setId(Long.valueOf(String.valueOf(results[0])));
								user.setEmail(String.valueOf(results[1]));
								user.setFullName(String.valueOf(results[2]));
								return user;
							}
							public List transformList(List list) {
								return list;
							}
				});
		List<User> result = query.list();
		return result;
	}

	@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	@Override
	public List<User> findByGeographyAssignment(String locationId, Integer lob) throws SystemException {
		String[] code = SystemParameter.USER_TITLE_EDD.trim().split("\\s*,\\s*");
		List<String> codeList = Arrays.asList(code);
		Query query = getSession().createSQLQuery(GET_USER_GEOGRAPHY_ASSIGNMENT).setParameterList("code", codeList)
				.setParameter("popId", "%"+locationId+"%")
				.setParameter("lob", lob).setParameter("status", MasterDataStatus.ACTIVE);
		query.getQueryString();
		query.setResultTransformer(
						new ResultTransformer() {
							@Override
							public Object transformTuple(Object[] results, String[] aliases) {
								User user = new User();
								user.setId(Long.valueOf(String.valueOf(results[0])));
								user.setEmail(String.valueOf(results[1]));
								user.setFullName(String.valueOf(results[2]));
								return user;
							}
							public List transformList(List list) {
								return list;
							}
				});
		List<User> result = query.list();
		return result;
	}
	
	@Override
	//find max lastModifiedDate
	public Date findMaxUwmpSyncDate() throws SystemException {
		return (Date) getSession().createCriteria(User.class)
				.add(Restrictions.eq(User.IS_FROM_UWMP, true))
				.setProjection( Projections.max(User.UWMP_SYNC_DATE)).uniqueResult();
	}
	
	@Override
	public Set<String> findDownLineMultiLevel(String userName){
		List<User> users = new ArrayList<>();
		Set<String> strings = new HashSet<>();
		List<String> querys = new ArrayList<>();
		User user = findByUserName(userName);
		querys.add(user.getUserName());
		
		while(users != null){
			users =  getSession().createCriteria(User.class)
					.add(Restrictions.in(User.NIK_SUPERVISOR, querys)).list();
			querys.clear();
			if (users !=null && !users.isEmpty()) {
				for (User userr : users) {
					strings.add(userr.getUserName());
					querys.add(userr.getUserName());
				}
			} else return strings;
		}
		
		return strings;
	}

}