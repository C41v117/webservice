package com.metamorf.eform.interfaces.user;

import java.util.List;

import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.entity.user.RuntimeUserLogin;

public interface ILoginService {
	
	public void register(RuntimeUserLogin userLogin) throws SystemException ;
	public void unregister(Long userPK) throws SystemException ;
	public RuntimeUserLogin findById(Long id) throws SystemException;
	public void unregisterAll() throws SystemException;
	public List<RuntimeUserLogin> findForForceLogout() throws SystemException;
	public void delete(List<RuntimeUserLogin> logoutUsers) throws SystemException;
	public void delete(Long logoutUserId) throws SystemException;
	public int countUserLoginByAccessbility(long accessbility) throws SystemException;
	public RuntimeUserLogin findByUserName(String userName);
	public RuntimeUserLogin findByAccessInfoId(String userName) throws SystemException;
	public void delete(String userName);
	public int countUserLoginByAccessbilityAndLob(long accessbility, int lob) throws SystemException;
}
