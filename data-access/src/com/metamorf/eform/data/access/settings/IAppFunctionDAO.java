package com.metamorf.eform.data.access.settings;

import java.util.List;

import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.entity.settings.AppFunction;

public interface IAppFunctionDAO {
	public List<AppFunction> findAppFunctionByPermission(Long appRole) throws SystemException;
	public abstract List<AppFunction> findAllAppFunction() throws SystemException;
	
	public abstract List<AppFunction> findAppFunctionMenuByUserRole(Long userRole) throws SystemException ;
	
	public abstract List<AppFunction> findAppFunctionTreeMenuByUserRole(Long appRole, Long[] parentId) ;

	public List<AppFunction> findAppFunction(boolean isActive, int userType) throws SystemException; 
	
	public List<AppFunction> findAppFunctionByAppFunctionMatrixId(Long id) throws SystemException;
	
	public AppFunction findById(Long id) throws SystemException;
	
	public int checkRoleHasFunction(Long roleId, Long appFunctionId) throws SystemException;
}