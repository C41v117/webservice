package com.metamorf.eform.interfaces.settings;

import java.util.List;

import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.entity.settings.AppFunction;

public interface IAppFunctionService {

	public abstract List<AppFunction> findAppFunctionByPermission(Long appRole)
			throws SystemException;

	public abstract List<AppFunction> findAppFunctionMenuByUserRole(Long userRole)
			throws SystemException;
	
	public abstract List<AppFunction> findAppFunctionTreeMenuByUserRole(Long userRole, Long[] parentId)
			throws SystemException;

	public abstract List<AppFunction> findAppFunction(boolean isActive, int userType) throws SystemException;
	
	public int checkRoleHasFunction(Long roleId, Long appFunctionId) throws SystemException;
}