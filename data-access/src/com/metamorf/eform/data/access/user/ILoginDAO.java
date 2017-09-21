package com.metamorf.eform.data.access.user;

import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.entity.user.RuntimeUserLogin;

import java.util.List;

public interface ILoginDAO {	
	public abstract void delete(Long[] objectPKs) throws SystemException;
	
	public abstract PagingWrapper<RuntimeUserLogin> findAllRuntimeUserLoginByFilter(
		int startNo, int offset,List<SearchFilter> filter,List<SearchOrder> searchSettlement) throws SystemException;
	
	
	
	public abstract RuntimeUserLogin findRuntimeUserLoginById(Long id)
		throws SystemException;
	
	
	public abstract void saveOrUpdate(RuntimeUserLogin anObject)
		throws SystemException;

	public abstract void deleteAll();
	
	public abstract void realDelete(List<RuntimeUserLogin> logoutUsers);
	
	public abstract void realDelete(Long userId);
	
	public abstract List<RuntimeUserLogin> findForForceLogout() throws SystemException;
	
	public int countUserLoginByAccessbility(long accessbility) throws SystemException;
	
	public abstract void realDelete(String userName);
	
	public RuntimeUserLogin findByUserName(String userName);
	
	public RuntimeUserLogin findByAccessInfoId(String accessInfoId) throws SystemException;
	
	public int countUserLoginByAccessbilityAndLob(long accessbility, int lob) throws SystemException;
}
