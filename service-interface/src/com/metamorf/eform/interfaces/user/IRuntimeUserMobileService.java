package com.metamorf.eform.interfaces.user;

import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.entity.user.RuntimeUserMobile;
import com.metamorf.eform.entity.user.User;
import com.metamorf.eform.interfaces.core.IBaseService;

public interface IRuntimeUserMobileService extends IBaseService<RuntimeUserMobile> {
	public RuntimeUserMobile findRuntimeUserMobileByUsername(String username) throws SystemException;

	public void delete(String username, Integer appId) throws SystemException;

	public void delete(String username) throws SystemException;

	public void updateRuntimeUserMobileAndUserWeb(RuntimeUserMobile runtimeUserMobile, User user)
			throws SystemException;

	public void deleteAndUpdateUserWeb(User user, Integer appId) throws SystemException;

	public RuntimeUserMobile findRuntimeUserMobileByUsernameAndToken(String username, String token)
			throws SystemException;

	public RuntimeUserMobile findRuntimeUserMobileByUsernameAndAppId(String username, Integer appId)
			throws SystemException;

	public RuntimeUserMobile findRuntimeUserMobileByUserId(String userId) throws SystemException;

	public RuntimeUserMobile findByUserIdAndToken(Long userId, String token) throws SystemException;
}
