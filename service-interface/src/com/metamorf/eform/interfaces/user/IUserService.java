package com.metamorf.eform.interfaces.user;

import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.entity.user.User;
import com.metamorf.eform.interfaces.core.IBaseService;

public interface IUserService extends IBaseService<User>{
	public User findByUsername(String username) throws SystemException;
	public User findByEmail(String email) throws SystemException;
	public User findByVerificationToken(String token) throws SystemException;
	public User findByPasswordToken(String token) throws SystemException;
}