package com.metamorf.eform.web.util;

import java.lang.reflect.Field;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.metamorf.eform.common.core.LoginSession;
import com.metamorf.eform.common.core.SessionConstants;
import com.metamorf.eform.entity.settings.AppFunction;

public class AccessibilityChecker {

	private final static Logger LOGGER = LoggerFactory.getLogger(AccessibilityChecker.class);

	public static HttpSession getSession() {
		HttpServletRequest request = ServletActionContext.getRequest();
		return request.getSession(false);
	}

	public static LoginSession getSessionUser() {
		HttpServletRequest request = ServletActionContext.getRequest();
		LOGGER.debug("request on getSessionUser is null: {} ", request == null);
		return WebGeneralFunction.getLogin(request);
	}

	@SuppressWarnings("unchecked")
	public static List<AppFunction> getSessionMenu() {
		return (List<AppFunction>) getSession().getAttribute(SessionConstants.MENU_OBJECT_KEY);
	}

	public static boolean hasPermission(String accessibilityKey) throws Exception {
		boolean hasPermission = false;
		String[] clazzz = accessibilityKey.split("@");
		Class<?> clazz = null;
		clazz = Class.forName(clazzz[1]);
		Field field = clazz.getField(clazzz[2]);
		hasPermission = getSessionUser().hasPermission(field.getLong(Long.TYPE));
		return hasPermission;
	}

}