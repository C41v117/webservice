package com.metamorf.eform.service.user;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.data.access.user.ILoginDAO;
import com.metamorf.eform.data.access.user.IUserDAO;
import com.metamorf.eform.entity.user.RuntimeUserLogin;
import com.metamorf.eform.interfaces.core.IBaseService;
import com.metamorf.eform.interfaces.user.ILoginService;

public class LoginService implements ILoginService, IBaseService<RuntimeUserLogin>{
	private final static Logger LOGGER = LoggerFactory.getLogger(LoginService.class);
	
	protected  ILoginDAO loginDAO;
	private IUserDAO userDAO;

	public LoginService(ILoginDAO loginDAO,IUserDAO userDAO) {
		this.loginDAO = loginDAO;
		this.userDAO=userDAO;
	}
	
	public void delete(List<RuntimeUserLogin> logoutUsers) throws SystemException {
		loginDAO.realDelete(logoutUsers);
	}

	public RuntimeUserLogin findById(Long id) throws SystemException {
		return loginDAO.findRuntimeUserLoginById(id);
	}

	public void saveOrUpdate(RuntimeUserLogin anObject) throws SystemException {
		loginDAO.saveOrUpdate(anObject);
	}

//	public void register(RuntimeUserLogin runtimeUser) throws SystemException {
//		loginDAO.saveOrUpdate(runtimeUser);
//		User user = userDAO.findByPK(runtimeUser.getUserId());
//		user.setLastLogInDate(runtimeUser.getLoginTime());
//		user.setLastLogInFrom(runtimeUser.getRemoteAddress());
//		userDAO.saveOrUpdate(user);
//	}
//
//	public void unregister(Long runtimeUserId) throws SystemException {
//		try {
//			LOGGER.info("start LoginService.unregistering {}", URLEncoder.encode(StringUtils.trimToEmpty(String.valueOf(runtimeUserId)), "UTF-8"));
//			loginDAO.realDelete(runtimeUserId);
//			LOGGER.info("delete RuntimeUserLogin {}", URLEncoder.encode(String.valueOf(runtimeUserId), "UTF-8"));
//			User user = userDAO.findByPK(runtimeUserId);
//			user.setLastLogOutDate(new Date());
//			userDAO.saveOrUpdate(user);
//			LOGGER.info("updated User {}", URLEncoder.encode(StringUtils.trimToEmpty(String.valueOf(runtimeUserId)), "UTF-8"));
//			LOGGER.info("finish LoginService.unregistering {}", URLEncoder.encode(StringUtils.trimToEmpty(String.valueOf(runtimeUserId)), "UTF-8"));
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public RuntimeUserLogin findByReferencesId(Long referencesPK)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<RuntimeUserLogin> findAll(int startNo, int offset, List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		List<RuntimeUserLogin> list = new ArrayList<RuntimeUserLogin>();
		//RuntimeUserLogin login = null;
		
		//TODO
		/*for (Long l : objectPKs) {
			login = loginDAO.findRuntimeUserLoginById(l);
			list.add(login);
		}*/
		return list;
	}

	@Override
	public void unregisterAll() throws SystemException {
		loginDAO.deleteAll();
	}

	@Override
	public void delete(RuntimeUserLogin anObject) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PagingWrapper<RuntimeUserLogin> findAllWithPagingWrapper(
			int startNo, int offset, List<SearchFilter> searchFilters,
			List<SearchOrder> searchOrders) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RuntimeUserLogin> findReportCollection(
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RuntimeUserLogin> findReportCollection(int startNo, int offset,
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RuntimeUserLogin> findForForceLogout() throws SystemException {
		return loginDAO.findForForceLogout();
	}

	@Override
	public void delete(Long logoutUserId) throws SystemException {
		loginDAO.realDelete(logoutUserId);
	}
	
	@Override
	public int countUserLoginByAccessbility(long accessbility) throws SystemException{
		return loginDAO.countUserLoginByAccessbility(accessbility);
	}
	
	@Override
	public int countUserLoginByAccessbilityAndLob(long accessbility, int lob) throws SystemException{
		return loginDAO.countUserLoginByAccessbilityAndLob(accessbility, lob);
	}
	
	@Override
	public RuntimeUserLogin findByUserName(String userName)
			throws SystemException {
		return loginDAO.findByUserName(userName);
	}
	
	@Override
	public RuntimeUserLogin findByAccessInfoId(String accessInfoId) throws SystemException{
		return loginDAO.findByAccessInfoId(accessInfoId);
	}
	
	@Override
	public void delete(String userName) throws SystemException {
		loginDAO.realDelete(userName);
	}

	@Override
	public void register(RuntimeUserLogin userLogin) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unregister(Long userPK) throws SystemException {
		// TODO Auto-generated method stub
		
	}
}
