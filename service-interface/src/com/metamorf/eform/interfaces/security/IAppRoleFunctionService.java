/**
 * 
 */
package com.metamorf.eform.interfaces.security;

import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.entity.settings.AppRoleFunction;
import com.metamorf.eform.entity.settings.UserGroupAccessNode;
import com.metamorf.eform.entity.user.Role;

import java.util.List;

/**
 * @author Hendri Yauw
 *
 */
public interface IAppRoleFunctionService {
	
	public List<UserGroupAccessNode> getAccessTree(Long pkAppRole) throws SystemException;

	public void updateRoleAccessbility(Role role, Long[] pkAppFunctions,Boolean isFO) throws SystemException;
	
	public List<AppRoleFunction> findAppRoleFunction(Long roleId, int userType) throws SystemException;
}
