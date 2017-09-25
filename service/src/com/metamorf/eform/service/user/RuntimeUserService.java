package com.metamorf.eform.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.data.access.user.IRuntimeUserDAO;
import com.metamorf.eform.entity.user.RuntimeUser;
import com.metamorf.eform.entity.user.User;
import com.metamorf.eform.interfaces.core.IBaseService;
import com.metamorf.eform.interfaces.user.IRuntimeUserService;
import com.metamorf.eform.interfaces.user.IUserService;

@Service
public class RuntimeUserService implements IRuntimeUserService, IBaseService<RuntimeUser>{

	private IRuntimeUserDAO runtimeUserMobileDAO;
	private IUserService userService;
	
	 public RuntimeUserService(IRuntimeUserDAO runtimeUserMobileDAO, IUserService userService) {
		 this.runtimeUserMobileDAO = runtimeUserMobileDAO;
		 this.userService = userService;
	 }
	
	
	@Override
	public List<RuntimeUser> findAll(int startNo, int offset, List<SearchFilter> searchFilters,
			List<SearchOrder> searchOrders) throws SystemException {
		
		return null;
	}

	@Override
	public PagingWrapper<RuntimeUser> findAllWithPagingWrapper(int startNo, int offset,
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders) throws SystemException {
		
		return null;
	}

	@Override
	public RuntimeUser findById(Long id) throws SystemException {
		
		return null;
	}

	@Override
	public void saveOrUpdate(RuntimeUser anObject) throws SystemException {
		runtimeUserMobileDAO.saveOrUpdate(anObject);
	}

	@Override
	public void delete(RuntimeUser anObject) throws SystemException {
		runtimeUserMobileDAO.delete(anObject);
	}

	@Override
	public List<RuntimeUser> findReportCollection(List<SearchFilter> searchFilters,
			List<SearchOrder> searchOrders) throws SystemException {
		
		return null;
	}

	@Override
	public List<RuntimeUser> findReportCollection(int startNo, int offset, List<SearchFilter> searchFilters,
			List<SearchOrder> searchOrders) throws SystemException {
		
		return null;
	}

	@Override
	public RuntimeUser findRuntimeUserMobileByUsername(String username) throws SystemException {
		return runtimeUserMobileDAO.findRuntimeUserMobileByUsername(username);
	}
	
	@Override
	public RuntimeUser findRuntimeUserMobileByUserId(String userId) throws SystemException {
		return runtimeUserMobileDAO.findRuntimeUserMobileByUserId(userId);
	}
	
	@Override
	public RuntimeUser findRuntimeUserMobileByUsernameAndToken(String username, String token)
			throws SystemException {
		return runtimeUserMobileDAO.findByUsernameAndToken(username, token);
	}

	@Override
	public void delete(String username, Integer appId) throws SystemException {
		List<RuntimeUser> objectList = runtimeUserMobileDAO.findAllRuntimeUserMobileByUsername(username, appId);
		runtimeUserMobileDAO.delete(objectList);
	}

	@Override
	public void delete(String username) throws SystemException {
		List<RuntimeUser> objectList = runtimeUserMobileDAO.findAllRuntimeUserMobileByUsername(username, null);
		runtimeUserMobileDAO.delete(objectList);
	}

	@Override
	public void updateRuntimeUserMobileAndUserWeb(RuntimeUser runtimeUserMobile, User user) {
		userService.saveOrUpdate(user);
		if (runtimeUserMobile != null) {
			saveOrUpdate(runtimeUserMobile);
		}
	}

}
