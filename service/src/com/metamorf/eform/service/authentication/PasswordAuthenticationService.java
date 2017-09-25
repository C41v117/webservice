package com.metamorf.eform.service.authentication;

import org.jasypt.digest.StringDigester;

import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.entity.user.User;
import com.metamorf.eform.interfaces.authentication.AuthenticationService;

public class PasswordAuthenticationService implements AuthenticationService<User> {

	StringDigester digester;
	
	public PasswordAuthenticationService(StringDigester digester) {
		super();
		this.digester = digester;
	}
	
	public Boolean authenticateLogin(User user, String password) throws SystemException {
		String digestedPassword = digester.digest(password); 
		if(!digestedPassword.equals(user.getPassword())) {
			return false;
		}
		return true;
	}
	
	public String generatePassword(String password) throws SystemException {
		return digester.digest(password); 
	}
	
}
