package com.metamorf.eform.interfaces.authentication;

import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.entity.user.User;

public interface AuthenticationService<T> {
	
	public Boolean authenticateLogin(User user, String password) throws SystemException;
	public String generatePassword(String password) throws SystemException;
	
}
