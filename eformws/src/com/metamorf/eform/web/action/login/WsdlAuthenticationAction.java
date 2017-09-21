package com.metamorf.eform.web.action.login;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.codec.binary.Base64;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.metamorf.eform.common.core.SystemParameter;
import com.opensymphony.xwork2.ActionSupport;

public class WsdlAuthenticationAction extends ActionSupport implements AuthenticationProvider{
	private static final long serialVersionUID = -2868374251232881379L;

	private static final Logger LOGGER = LoggerFactory.getLogger(WsdlAuthenticationAction.class);

	private StrongPasswordEncryptor encryptor;
	
	private String realUsername;
	private String realPassword;
	
	public WsdlAuthenticationAction(){
		
	}
	
	public WsdlAuthenticationAction(String keypass){
		String realBase64 = getRealEncodedBase64(keypass);
		realBase64 = decodeStringBase64(realBase64);
		String[] split = realBase64.split(":");
		if(split.length > 1){
			realUsername = split[0];
			realPassword = split[1];
		}
	}
	
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
		String username = String.valueOf(auth.getPrincipal());
		String password = String.valueOf(auth.getCredentials());
		
		//LOGGER.info("Basic Authentication with username [{}], password [{}] : BEGIN ", username, password);		
		
//		if(	validateUserWithSHA256(username, password)){
//		if(	validateUserWithBase64(username, password)){
		if(	validateUserSimple(username, password)){
			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("ROLE_AGENT"));
			return new UsernamePasswordAuthenticationToken(username, password, authorities);
		} else {
			throw new UsernameNotFoundException("User or password invalid");
		}
		
	}
	
	private boolean validateUserWithSHA256(String username, String password){
		if(	encryptor.checkPassword(username, SystemParameter.WSDL_BASIC_AUTH_USERNAME) &&
			encryptor.checkPassword(password, SystemParameter.WSDL_BASIC_AUTH_PASSWORD) ) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean validateUserSimple(String username, String password){
		if(username.equals(realUsername) && password.equals(realPassword)){
			return true;
		} else {
			return false;
		}
	}
	
	private boolean validateUserWithBase64(String username, String password){
		String realBase64 = getRealEncodedBase64(SystemParameter.WSDL_BASIC_AUTH_PASSWORD);
		String realUsername;
		String realPassword;
		realBase64 = decodeStringBase64(realBase64);
		String[] split = realBase64.split(":");
		if(split.length <= 1){
			return false;
		} else {
			realUsername = split[0];
			realPassword = split[1];
			if(realUsername.equals(username) && realPassword.equals(password)){
				return true;
			} else {
				return false;
			}
		}
	}
	
	private boolean validateUserWithBase64(String encodedBase64){
		String realBase64 = getRealEncodedBase64(SystemParameter.WSDL_BASIC_AUTH_PASSWORD);
		if(encodedBase64.equals(realBase64)){
			return true;
		} else {
			return false;
		}
	}
	
	private String getRealEncodedBase64(String decodedBase64){
		return decodedBase64.replaceFirst(SystemParameter.WSDL_BASIC_AUTH_SALT, "");
	}
	
	private String decodeStringBase64(String encodedString){
		String result = "";
		
		byte[] decoded = Base64.decodeBase64(encodedString.getBytes());
		try {
			result = new String(decoded, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			LOGGER.error("Decode String Error! Unsupported Encoding Exception, Encoded String: [{}]", encodedString);
		}
		return result;
	}
	
	private String getUsername(String decodedString){
		String[] split = decodedString.split(":");
		if(split.length <= 0){
			return null;
		} else {
			return split[0];
		}
	}
	
	private String getPassword(String decodedString){
		String[] split = decodedString.split(":");
		if(split.length <= 1){
			return null;
		} else {
			return split[1];
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	public StrongPasswordEncryptor getEncryptor() {
		return encryptor;
	}

	public void setEncryptor(StrongPasswordEncryptor encryptor) {
		this.encryptor = encryptor;
	}
	
}
