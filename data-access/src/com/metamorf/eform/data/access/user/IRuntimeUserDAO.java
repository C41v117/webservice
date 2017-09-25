package com.metamorf.eform.data.access.user;

import java.util.List;

import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.entity.user.RuntimeUser;

public interface IRuntimeUserDAO {
	
	public abstract void delete(List<RuntimeUser> runTimeUserMobile) throws SystemException;
	
	public abstract RuntimeUser findRuntimeUserMobileByUsername(String username)
		throws SystemException;
	
	public abstract void saveOrUpdate(RuntimeUser anObject)
		throws SystemException;

	public List<RuntimeUser> findAllRuntimeUserMobileByUsername(String username, Integer appId)
			throws SystemException;

	public RuntimeUser findByUsernameAndToken(String username, String token) throws SystemException;

	public RuntimeUser findRuntimeUserMobileByUserId(String userId) throws SystemException;

	public void delete(RuntimeUser anObject) throws SystemException;

}
