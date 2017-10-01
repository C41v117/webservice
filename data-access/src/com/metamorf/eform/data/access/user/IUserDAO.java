package com.metamorf.eform.data.access.user;

import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.data.access.core.IBaseDAO;
import com.metamorf.eform.entity.user.User;

public interface IUserDAO extends IBaseDAO<User>{
	public User findByUsername(String username) throws SystemException;
	public User findByEmail(String email) throws SystemException;
	public User findByVerificationToken(String token) throws SystemException;
}