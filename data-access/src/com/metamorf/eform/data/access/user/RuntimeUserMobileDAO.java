package com.metamorf.eform.data.access.user;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.metamorf.eform.common.data.util.Operator;
import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.data.access.core.AbstractHibernate4DAO;
import com.metamorf.eform.entity.user.RuntimeUserLogin;
import com.metamorf.eform.entity.user.RuntimeUserMobile;

public class RuntimeUserMobileDAO extends AbstractHibernate4DAO<RuntimeUserMobile,Long> implements IRuntimeUserMobileDAO {

	@Override
	public RuntimeUserMobile findRuntimeUserMobileByUsername(String username) throws SystemException {
		Criteria codeCriteria = getSession().createCriteria(RuntimeUserMobile.class);
		codeCriteria.add(Restrictions.eq(RuntimeUserLogin.USERNAME, username));
		return (RuntimeUserMobile) codeCriteria.uniqueResult();
	}
	
	@Override
	public RuntimeUserMobile findRuntimeUserMobileByUserId(String userId) throws SystemException {
		Criteria codeCriteria = getSession().createCriteria(RuntimeUserMobile.class);
		codeCriteria.add(Restrictions.eq(RuntimeUserLogin.USER_ID, userId));
		return (RuntimeUserMobile) codeCriteria.uniqueResult();
	}

	@Override
	public RuntimeUserMobile findByUsernameAndToken(String username, String token) throws SystemException {
		Criteria criteria = getSession().createCriteria(RuntimeUserMobile.class);
		criteria.add(Restrictions.eq(RuntimeUserMobile.USERNAME, username));
		criteria.add(Restrictions.eq(RuntimeUserMobile.TOKEN, token));
		return (RuntimeUserMobile) criteria.uniqueResult();
	}
	
	@Override
	public RuntimeUserMobile findByUserIdAndToken(Long userId, String token) throws SystemException {
		Criteria criteria = getSession().createCriteria(RuntimeUserMobile.class);
		criteria.add(Restrictions.eq(RuntimeUserMobile.USER_ID, userId));
		criteria.add(Restrictions.eq(RuntimeUserMobile.TOKEN, token));
		return (RuntimeUserMobile) criteria.uniqueResult();
	}

	@Override
	public RuntimeUserMobile findByUsernameAndAppId(String username, Integer appId) throws SystemException {
		Criteria criteria = getSession().createCriteria(RuntimeUserMobile.class);
		criteria.add(Restrictions.eq(RuntimeUserMobile.USERNAME, username));
		criteria.add(Restrictions.eq(RuntimeUserMobile.APP_ID, appId));
		return (RuntimeUserMobile) criteria.uniqueResult();
	}

	@Override
	public void saveOrUpdate(RuntimeUserMobile anObject) throws SystemException {
		if(null==anObject.getId()){
			super.create(anObject);
		}else{
			super.update(anObject);
		}
		
	}
	
	@Override
	public List<RuntimeUserMobile> findAllRuntimeUserMobileByUsername(String username, Integer appId)
			throws SystemException {
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		filter.add(new SearchFilter("username", Operator.EQUALS, username));
		if (appId != null) {
			filter.add(new SearchFilter("appId", Operator.EQUALS, appId));
		}
		return super.findAll(filter, new ArrayList<SearchOrder>(), null);
	}

	@Override
	public void delete(List<RuntimeUserMobile> runtimeUserMobileList) throws SystemException {
		super.delete(runtimeUserMobileList);
		
	}
	
	@Override
	public void delete(RuntimeUserMobile anObject) throws SystemException{
		super.delete(anObject);
	}
}
