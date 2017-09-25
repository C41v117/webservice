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
import com.metamorf.eform.entity.user.RuntimeUser;

public class RuntimeUserDAO extends AbstractHibernate4DAO<RuntimeUser,Long> implements IRuntimeUserDAO {

	@Override
	public RuntimeUser findRuntimeUserMobileByUsername(String username) throws SystemException {
		Criteria codeCriteria = getSession().createCriteria(RuntimeUser.class);
		codeCriteria.add(Restrictions.eq(RuntimeUserLogin.USERNAME, username));
		return (RuntimeUser) codeCriteria.uniqueResult();
	}
	
	@Override
	public RuntimeUser findRuntimeUserMobileByUserId(String userId) throws SystemException {
		Criteria codeCriteria = getSession().createCriteria(RuntimeUser.class);
		codeCriteria.add(Restrictions.eq(RuntimeUserLogin.USER_ID, userId));
		return (RuntimeUser) codeCriteria.uniqueResult();
	}

	@Override
	public RuntimeUser findByUsernameAndToken(String username, String token) throws SystemException {
		Criteria criteria = getSession().createCriteria(RuntimeUser.class);
		criteria.add(Restrictions.eq(RuntimeUser.USERNAME, username));
		criteria.add(Restrictions.eq(RuntimeUser.TOKEN, token));
		return (RuntimeUser) criteria.uniqueResult();
	}
	
	@Override
	public void saveOrUpdate(RuntimeUser anObject) throws SystemException {
		if(null==anObject.getId()){
			super.create(anObject);
		}else{
			super.update(anObject);
		}
		
	}
	
	@Override
	public List<RuntimeUser> findAllRuntimeUserMobileByUsername(String username, Integer appId)
			throws SystemException {
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		filter.add(new SearchFilter("username", Operator.EQUALS, username));
		if (appId != null) {
			filter.add(new SearchFilter("appId", Operator.EQUALS, appId));
		}
		return super.findAll(filter, new ArrayList<SearchOrder>(), null);
	}

	@Override
	public void delete(List<RuntimeUser> runtimeUserMobileList) throws SystemException {
		super.delete(runtimeUserMobileList);
		
	}
	
	@Override
	public void delete(RuntimeUser anObject) throws SystemException{
		super.delete(anObject);
	}
}
