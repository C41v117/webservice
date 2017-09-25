package com.metamorf.eform.interfaces.user;

import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.entity.user.RuntimeUser;
import com.metamorf.eform.entity.user.User;
import com.metamorf.eform.interfaces.core.IBaseService;

public interface IRuntimeUserService extends IBaseService<RuntimeUser> {
	public RuntimeUser findRuntimeUserMobileByUsername(String username) throws SystemException;

	public void delete(String username, Integer appId) throws SystemException;

	public void delete(String username) throws SystemException;

	public void updateRuntimeUserMobileAndUserWeb(RuntimeUser runtimeUserMobile, User user)
			throws SystemException;

	public RuntimeUser findRuntimeUserMobileByUsernameAndToken(String username, String token)
			throws SystemException;

	public RuntimeUser findRuntimeUserMobileByUserId(String userId) throws SystemException;

}
