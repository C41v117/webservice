package com.metamorf.eform.service.settings;

import java.util.List;

import org.springframework.stereotype.Service;

import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.data.access.settings.IAppFunctionDAO;
import com.metamorf.eform.entity.settings.AppFunction;
import com.metamorf.eform.interfaces.settings.IAppFunctionService;

@Service
public class AppFunctionService implements IAppFunctionService {
	
	private IAppFunctionDAO appFunctionDAO;

	public void setAppFunctionDAO(IAppFunctionDAO appFunctionDAO) {
		this.appFunctionDAO = appFunctionDAO;
	}
	
	@Override
	public List<AppFunction> findAppFunctionMenuByUserRole(Long userRole) throws SystemException{
		return appFunctionDAO.findAppFunctionMenuByUserRole(userRole);
	}
	
	public List<AppFunction> findAppFunctionTreeMenuByUserRole(Long userRole, Long[] parentId) throws SystemException{
		return appFunctionDAO.findAppFunctionTreeMenuByUserRole(userRole, parentId);
	}

	@Override
	public List<AppFunction> findAppFunctionByPermission(Long appRole)throws SystemException {
		 return appFunctionDAO.findAppFunctionByPermission(appRole);
	}

	@Override
	public List<AppFunction> findAppFunction(boolean isActive, int userType)
			throws SystemException {
		return appFunctionDAO.findAppFunction(isActive, userType);
	}
	
	@Override
	public int checkRoleHasFunction(Long roleId, Long appFunctionId) throws SystemException{
		return appFunctionDAO.checkRoleHasFunction(roleId, appFunctionId);
	}
}
