package com.metamorf.eform.interfaces.user;

import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.entity.user.User;
import com.metamorf.eform.interfaces.core.IBaseService;

public interface IUserService extends IBaseService<User>{
	public User findByUsername(String username) throws SystemException;
}