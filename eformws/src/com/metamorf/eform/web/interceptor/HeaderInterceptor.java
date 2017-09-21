package com.metamorf.eform.web.interceptor;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.metamorf.eform.common.core.SystemParameter;
import com.metamorf.eform.common.util.AES;

public class HeaderInterceptor extends HandlerInterceptorAdapter {
	
	private static final String SWAGGER = "swagger";

	String USERNAME = "DTPAPIGATEWAY";
	String PASSWORD = "PASSDTPAPIGW123";
	// Hasil encrtpy and base64: 7Y3G39q/dCxXsH4/ShsPdOESnHotJF4ugVa+8h45YHw=
	// Format = USERNAME:PASSWORD encrypt using AES alogrithm then encode to
	// base64
	private static final Logger logger = LoggerFactory.getLogger(HeaderInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//bypass if BYPASS_REST_AUTHENTICATION is set to true "OR" request uri contains swagger url
		if (SystemParameter.BYPASS_REST_AUTHENTICATION || request.getRequestURI().contains(SWAGGER)) {
			return true;
		} else { 
			String authorizationHeader = request.getHeader("X-DTPkey");
			if (authorizationHeader != null && !authorizationHeader.isEmpty()) {
				String userNamePasswordPlain = "";
				try {
					AES.generateKeyAPIGateway();
					userNamePasswordPlain = AES.decryptString(authorizationHeader);
				} catch (Exception ex) {
					logger.error("Authorization failed. (Secret Key/Username/Password is wrong)");
					logger.error(ex.getMessage());
					response.sendError(HttpStatus.SC_UNAUTHORIZED);
					return false;
				}
				String[] result = userNamePasswordPlain.split(Pattern.quote(":"));
				String userNamePlain = result[0];
				String passwordPlain = result[1];
				if (userNamePlain.equals(USERNAME) && passwordPlain.equals(PASSWORD)) {
					return true;
				}
			} else {
				logger.error("Authorization failed. (X-DTPKey not found in header)");
				response.sendError(HttpStatus.SC_UNAUTHORIZED);
				return false;
			}
			logger.error("Authorization failed. (Username or Password is wrong)");
			response.sendError(HttpStatus.SC_UNAUTHORIZED);
			return false;
		}
	}
}
