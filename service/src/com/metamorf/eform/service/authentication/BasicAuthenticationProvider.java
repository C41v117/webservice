package com.metamorf.eform.service.authentication;

import java.util.ArrayList;
import java.util.Collection;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Logger;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.metamorf.eform.common.util.PhoneNumberUtil;
import com.metamorf.eform.data.access.user.UserDAO;
import com.metamorf.eform.entity.user.User;

public class BasicAuthenticationProvider implements AuthenticationProvider {
	private static final Logger LOGGER = ESAPI.getLogger(BasicAuthenticationProvider.class);
	
	private UserDAO userDAO;
	
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
		String username = String.valueOf(auth.getPrincipal());
		String password = String.valueOf(auth.getCredentials());

		LOGGER.info(Logger.EVENT_SUCCESS, "username:" + username);
//		LOGGER.info(Logger.EVENT_SUCCESS, "password:" + password);// Don't log passwords in real app

		// 1. Use the username to load the data for the user, including authorities and password.
		User user = userDAO.findByUserName(username);
		
		if(user == null){
			throw new UsernameNotFoundException("user not found");
		}
		
		String synchPhone = PhoneNumberUtil.convert(username);
		
		  // 2. Check the passwords match.
//		if (!user.getPassword().equals(password)) {
//			throw new BadCredentialsException("Bad Credentials");
//		}

		  // 3. Preferably clear the password in the user object before storing in authentication object
//		user.clearPassword();

		  // 4. Return an authenticated token, containing user data and authorities  

		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_AGENT"));
		return new UsernamePasswordAuthenticationToken(username, password, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		 return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
  
}