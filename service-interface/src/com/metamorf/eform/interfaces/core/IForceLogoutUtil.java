package com.metamorf.eform.interfaces.core;

import com.metamorf.eform.common.exception.SystemException;

public interface IForceLogoutUtil {
	public void rmiForceLogout(String sessionId) throws SystemException;
}
