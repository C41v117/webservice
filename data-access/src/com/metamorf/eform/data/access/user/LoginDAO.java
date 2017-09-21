package com.metamorf.eform.data.access.user;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.metamorf.eform.common.data.util.Operator;
import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.data.access.core.AbstractHibernate4DAO;
import com.metamorf.eform.entity.user.RuntimeUserLogin;

public class LoginDAO extends AbstractHibernate4DAO<RuntimeUserLogin,Long> implements ILoginDAO {
	private final static Logger LOGGER = LoggerFactory.getLogger(LoginDAO.class);

	public void delete(Long[] objectPKs) throws SystemException {
		try {
			String strPKs = Arrays.toString(objectPKs);
			LOGGER.info("start LoginDAO.unregistering {}", new Object[]{URLEncoder.encode(StringUtils.trimToEmpty(strPKs), "UTF-8")});
		
			List<RuntimeUserLogin> objectList = new ArrayList<RuntimeUserLogin>();
			RuntimeUserLogin object =null;
			for(int i=0;i<objectPKs.length;i++){
				object= new RuntimeUserLogin();
				object = findRuntimeUserLoginById(objectPKs[i]);
				LOGGER.info("runtimeUserLogin {} exist, add to objectList", new Object[]{URLEncoder.encode(StringUtils.trimToEmpty(strPKs), "UTF-8")});
				if(object!=null){
					object.setDoForceLogout(Boolean.TRUE);
					objectList.add(object);
				}
	
			}
			//super.delete(objectList); //using do force logout
			LOGGER.info("finish LoginDAO.unregistering {}", new Object[]{URLEncoder.encode(StringUtils.trimToEmpty(strPKs), "UTF-8")});
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public PagingWrapper<RuntimeUserLogin> findAllRuntimeUserLogin(int startNo,
			int offset) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset);
	}

	public RuntimeUserLogin findRuntimeUserLoginById(Long id)
			throws SystemException {
		return super.findByPK(id);
	}
	
	public RuntimeUserLogin findRuntimeUserLoginByIdAndLogout(Long id)
			throws SystemException {
		List<SearchFilter> searchFilters = new ArrayList<SearchFilter>();
		searchFilters.add(new SearchFilter(RuntimeUserLogin.DO_FORCE_LOGOUT, Operator.EQUALS, Boolean.FALSE));
		List<RuntimeUserLogin> loginUsers = super.findAll(searchFilters, new ArrayList<SearchOrder>(), null);
		if(loginUsers!=null && loginUsers.size()>0){
			return loginUsers.get(0);
		}
		return null;
	}

	public void saveOrUpdate(RuntimeUserLogin anObject) throws SystemException {
		super.create(anObject);
	}

	public PagingWrapper<RuntimeUserLogin> findAllRuntimeUserLoginByFilter(
			int startNo, int offset, List<SearchFilter> filter,
			List<SearchOrder> searchSettlement) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, searchSettlement, null);
	}
	
	@Override
	public void deleteAll() {
		Query query = getSession().createQuery("delete from RuntimeUserLogin");
		query.executeUpdate();
	}

	@Override
	public List<RuntimeUserLogin> findForForceLogout() throws SystemException {
		List<SearchFilter> searchFilters = new ArrayList<SearchFilter>();
		searchFilters.add(new SearchFilter(RuntimeUserLogin.DO_FORCE_LOGOUT, Operator.EQUALS, Boolean.TRUE));
		return super.findAll(searchFilters, new ArrayList<SearchOrder>(), null);
	}

	@Override
	public void realDelete(List<RuntimeUserLogin> logoutUsers) {
		super.delete(logoutUsers);
	}
	
	@Override
	public void realDelete(Long userId) {
		RuntimeUserLogin logoutUser = findRuntimeUserLoginById(userId);
		if(logoutUser!=null){
			super.delete(logoutUser);
		}
	}
	
	@Override
	public void realDelete(String userName) {
		RuntimeUserLogin logoutUser = findByUserName(userName);
		if(logoutUser!=null){
			super.delete(logoutUser);
		}
	}
	
	static final String COUNT_USER_LOGIN_BY_APP_FUNCTION = 
			"select count(*) "+ 
			"	from app_role_function arf  "+
			"	inner join app_role ar on arf.role_id = ar.id "+
			"	inner join app_user au on ar.id = au.role_id "+
			"   inner join runtime_user_login ru on ru.userid = au.id "+
			"	where arf.app_function_id = ?  ";
	
	static final String COUNT_USER_LOGIN_BY_APP_FUNCTION_AND_LOB = 
			"select count(*) "+ 
			"	from app_role_function arf  "+
			"	inner join app_role ar on arf.role_id = ar.id "+
			"	inner join app_user au on ar.id = au.role_id "+
			"   inner join runtime_user_login ru on ru.userid = au.id "+
			"	where arf.app_function_id = ?  and (au.lob = ? or au.lob = 2) ";
	
	@Override
	public int countUserLoginByAccessbility(long accessbility) throws SystemException{
		return Integer.parseInt(getSession().createSQLQuery(COUNT_USER_LOGIN_BY_APP_FUNCTION)
				.setParameter(0, accessbility)
				.uniqueResult().toString());
	}
	
	@Override
	public int countUserLoginByAccessbilityAndLob(long accessbility, int lob) throws SystemException{
		return Integer.parseInt(getSession().createSQLQuery(COUNT_USER_LOGIN_BY_APP_FUNCTION_AND_LOB)
				.setParameter(0, accessbility)
				.setParameter(1, lob)
				.uniqueResult().toString());
	}
	
	@Override
	public RuntimeUserLogin findByUserName(String userName){
		Criteria codeCriteria = getSession().createCriteria(RuntimeUserLogin.class);
		codeCriteria.add(Restrictions.eq(RuntimeUserLogin.USERNAME, userName));
		return (RuntimeUserLogin) codeCriteria.uniqueResult();
	}
	
	@Override
	public RuntimeUserLogin findByAccessInfoId(String accessInfoId) throws SystemException{
		Criteria codeCriteria = getSession().createCriteria(RuntimeUserLogin.class);
		codeCriteria.add(Restrictions.eq(RuntimeUserLogin.ACCESS_INFO_ID, accessInfoId));
		return (RuntimeUserLogin) codeCriteria.uniqueResult();
	}
}
