/**
 * 
 */
package com.metamorf.eform.data.access.security;

import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.entity.settings.AppRoleFunction;
import com.metamorf.eform.entity.settings.UserGroupAccessNode;

import java.util.List;

/**
 * @author Hendri Yauw
 *
 */
public interface IAppRoleFunctionDAO {
	
	public List<UserGroupAccessNode> getAccessTree(long groupPK) throws SystemException;

	public void delete(Long roleId) throws SystemException;
	
	public void insert(Long roleId, Long[] pkAppFunction) throws SystemException;

	public List<AppRoleFunction> findAppRoleFunction(long roleId, int userType) throws SystemException;
	
	public void saveOrUpdate(AppRoleFunction anObject) throws SystemException;

	public Boolean doesThisRoleHasThisFunction(Long roleId, Long accessibility)
			throws SystemException;
}
