package com.metamorf.eform.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.data.access.user.IUserDAO;
import com.metamorf.eform.entity.user.User;
import com.metamorf.eform.interfaces.core.IBaseService;
import com.metamorf.eform.interfaces.user.IUserService;

@Service
public class UserService implements IBaseService<User>, IUserService {

	private IUserDAO userDAO;
	
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
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
		userDAO.saveOrUpdate(anObject);
	}

	@Override
	public void delete(User user) throws SystemException {
		userDAO.deleteObject(user);
	}

	@Override
	public User findByUsername(String username) throws SystemException{
		return userDAO.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) throws SystemException{
		return userDAO.findByEmail(email);
	}

	@Override
	public User findByVerificationToken(String token) throws SystemException{
		return userDAO.findByVerificationToken(token);
	}
	
	@Override
	public User findByPasswordToken(String token) throws SystemException{
		return userDAO.findByPasswordToken(token);
	}
	
	@Override
	public PagingWrapper<User> findAllWithPagingWrapper(int startNo,
			int offset, List<SearchFilter> searchFilters,
			List<SearchOrder> searchOrders) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findReportCollection(List<SearchFilter> searchFilters,
			List<SearchOrder> searchOrders) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findReportCollection(int startNo, int offset,
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
