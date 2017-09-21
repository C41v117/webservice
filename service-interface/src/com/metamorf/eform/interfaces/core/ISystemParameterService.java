package com.metamorf.eform.interfaces.core;

import com.metamorf.eform.common.exception.SystemException;

public interface ISystemParameterService {
	public Object getValue(String name) throws SystemException;
}
