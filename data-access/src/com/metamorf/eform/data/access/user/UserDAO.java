package com.metamorf.eform.data.access.user;

import java.util.ArrayList;
import java.util.List;

import com.metamorf.eform.common.data.util.Operator;
import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.data.access.core.AbstractHibernate4DAO;
import com.metamorf.eform.entity.user.User;

public class UserDAO extends AbstractHibernate4DAO<User, Long> implements IUserDAO {
	
	@Override
	public void saveOrUpdate(User user) {
		if(user.getId()==null) {
			super.create(user);
		} else {
			super.update(user);
		}
	}
	
	public List<User> findAll(){
		return super.findAll();
	}

	@Override
	public List<User> findAll(int startNo, int offset,List<SearchFilter> filter, List<SearchOrder> order)throws SystemException {
		return super.findByFilter(null, null, null, filter, order, startNo, offset, null);
	}
	
	@Override
	public PagingWrapper<User> findAllByFilter(int startNo, int offset,
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		return super.findFetchedPropertyWithPagingWrapper(startNo, offset, null, null, searchFilters, searchOrders, null, false);
	}

	@Override
	public void deleteObject(User anObject) throws SystemException {
		super.delete(anObject);
	}

	static final String UPDATE_LAST_LOGOUT_DATE = 
			"UPDATE app_user "
			+ "SET last_logout_date = :lastLogOutDate "
			+ "WHERE id = :userId";
	
	@Override
	public User findByUsername(String username) throws SystemException{
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		filters.add(new SearchFilter(User.USERNAME, Operator.EQUALS, username));
		List<User> result = super.findAll(filters, null, null);
		
		if (result.size() > 0) 
			return result.get(0);
		else
			return null;
	}
	
	@Override
	public User findByEmail(String email) throws SystemException{
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		filters.add(new SearchFilter(User.EMAIL, Operator.EQUALS, email));
		List<User> result = super.findAll(filters, null, null);
		
		if (result.size() > 0) 
			return result.get(0);
		else
			return null;
	}
	
	@Override
	public User findByVerificationToken(String token) throws SystemException{
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		filters.add(new SearchFilter(User.VERIFICATION_TOKEN, Operator.EQUALS, token));
		List<User> result = super.findAll(filters, null, null);
		
		if (result.size() > 0) 
			return result.get(0);
		else
			return null;
	}

	@Override
	public User findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

}