package com.metamorf.eform.interfaces.authentication;

import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.entity.user.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AuthenticationService<T> {
	
	public T authenticateLogin(User user, String password) throws SystemException ;
	public T authenticateLogin(User user, String password, int pinLength, String nonce) throws SystemException ;
	public abstract boolean isNotificationPeriod(Date expiredDate, Date currentDate)throws SystemException; 
	public List<Map<String, String>> findListUser(String username) throws SystemException;
	public List<User> checkListUserToLDAP(List<User> users) throws SystemException;
}
