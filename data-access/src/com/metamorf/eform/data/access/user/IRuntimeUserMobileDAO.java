package com.metamorf.eform.data.access.user;

import java.util.List;

import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.entity.user.RuntimeUserMobile;

public interface IRuntimeUserMobileDAO {
	
	public abstract void delete(List<RuntimeUserMobile> runTimeUserMobile) throws SystemException;
	
	public abstract RuntimeUserMobile findRuntimeUserMobileByUsername(String username)
		throws SystemException;
	
	public abstract void saveOrUpdate(RuntimeUserMobile anObject)
		throws SystemException;

	public List<RuntimeUserMobile> findAllRuntimeUserMobileByUsername(String username, Integer appId)
			throws SystemException;

	public RuntimeUserMobile findByUsernameAndToken(String username, String token) throws SystemException;

	public RuntimeUserMobile findByUsernameAndAppId(String username, Integer appId) throws SystemException;

	public RuntimeUserMobile findRuntimeUserMobileByUserId(String userId) throws SystemException;

	public void delete(RuntimeUserMobile anObject) throws SystemException;

	public RuntimeUserMobile findByUserIdAndToken(Long userId, String token) throws SystemException;
}
