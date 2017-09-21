package com.metamorf.eform.service.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.data.access.user.IRuntimeUserMobileDAO;
import com.metamorf.eform.entity.user.RuntimeUserMobile;
import com.metamorf.eform.entity.user.User;
import com.metamorf.eform.interfaces.core.IBaseService;
import com.metamorf.eform.interfaces.user.IRuntimeUserMobileService;
import com.metamorf.eform.interfaces.user.IUserService;

@Service
public class RuntimeUserMobileService implements IRuntimeUserMobileService, IBaseService<RuntimeUserMobile>{
	private final static Logger LOGGER = LoggerFactory.getLogger(RuntimeUserMobileService.class);

	private IRuntimeUserMobileDAO runtimeUserMobileDAO;
	private IUserService userService;
	
	 public RuntimeUserMobileService(IRuntimeUserMobileDAO runtimeUserMobileDAO, IUserService userService) {
		 this.runtimeUserMobileDAO = runtimeUserMobileDAO;
		 this.userService = userService;
	 }
	
	
	@Override
	public List<RuntimeUserMobile> findAll(int startNo, int offset, List<SearchFilter> searchFilters,
			List<SearchOrder> searchOrders) throws SystemException {
		
		return null;
	}

	@Override
	public PagingWrapper<RuntimeUserMobile> findAllWithPagingWrapper(int startNo, int offset,
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders) throws SystemException {
		
		return null;
	}

	@Override
	public RuntimeUserMobile findById(Long id) throws SystemException {
		
		return null;
	}

	@Override
	public void saveOrUpdate(RuntimeUserMobile anObject) throws SystemException {
		runtimeUserMobileDAO.saveOrUpdate(anObject);
	}

	@Override
	public void delete(RuntimeUserMobile anObject) throws SystemException {
		runtimeUserMobileDAO.delete(anObject);
	}

	@Override
	public List<RuntimeUserMobile> findReportCollection(List<SearchFilter> searchFilters,
			List<SearchOrder> searchOrders) throws SystemException {
		
		return null;
	}

	@Override
	public List<RuntimeUserMobile> findReportCollection(int startNo, int offset, List<SearchFilter> searchFilters,
			List<SearchOrder> searchOrders) throws SystemException {
		
		return null;
	}

	@Override
	public RuntimeUserMobile findRuntimeUserMobileByUsername(String username) throws SystemException {
		return runtimeUserMobileDAO.findRuntimeUserMobileByUsername(username);
	}
	
	@Override
	public RuntimeUserMobile findByUserIdAndToken(Long userId, String token) throws SystemException {
		return runtimeUserMobileDAO.findByUserIdAndToken(userId, token);
	}
	
	@Override
	public RuntimeUserMobile findRuntimeUserMobileByUserId(String userId) throws SystemException {
		return runtimeUserMobileDAO.findRuntimeUserMobileByUserId(userId);
	}
	
	@Override
	public RuntimeUserMobile findRuntimeUserMobileByUsernameAndAppId(String username, Integer appId)
			throws SystemException {
		return runtimeUserMobileDAO.findByUsernameAndAppId(username, appId);
	}

	@Override
	public RuntimeUserMobile findRuntimeUserMobileByUsernameAndToken(String username, String token)
			throws SystemException {
		return runtimeUserMobileDAO.findByUsernameAndToken(username, token);
	}

	@Override
	public void delete(String username, Integer appId) throws SystemException {
		List<RuntimeUserMobile> objectList = runtimeUserMobileDAO.findAllRuntimeUserMobileByUsername(username, appId);
		runtimeUserMobileDAO.delete(objectList);
	}

	@Override
	public void delete(String username) throws SystemException {
		List<RuntimeUserMobile> objectList = runtimeUserMobileDAO.findAllRuntimeUserMobileByUsername(username, null);
		runtimeUserMobileDAO.delete(objectList);
	}

	@Override
	public void updateRuntimeUserMobileAndUserWeb(RuntimeUserMobile runtimeUserMobile, User user) {
		userService.saveOrUpdate(user);
		if (runtimeUserMobile != null) {
			saveOrUpdate(runtimeUserMobile);
		}
	}

	@Override
	public void deleteAndUpdateUserWeb(User user, Integer appId) throws SystemException {
		userService.saveOrUpdate(user);
		delete(user.getUserName(), appId);
	}
}
