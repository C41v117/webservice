/**
 * 
 */
package com.metamorf.eform.service.security;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.metamorf.eform.common.core.AccessibilityConstant;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.data.access.security.IAppRoleFunctionDAO;
import com.metamorf.eform.data.access.security.IRoleDAO;
import com.metamorf.eform.entity.settings.AppRoleFunction;
import com.metamorf.eform.entity.settings.UserGroupAccessNode;
import com.metamorf.eform.entity.user.Role;
import com.metamorf.eform.interfaces.security.IAppRoleFunctionService;

/**
 * @author Hendri Yauw
 *
 */
public class AppRoleFunctionService implements IAppRoleFunctionService {

	IAppRoleFunctionDAO appRoleFunctionDAO;
	IRoleDAO roleDAO;
	
	public void setAppRoleFunctionDAO(IAppRoleFunctionDAO appRoleFunctionDAO) {
		this.appRoleFunctionDAO = appRoleFunctionDAO;
	}

	public void setRoleDAO(IRoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	@Override
	public List<UserGroupAccessNode> getAccessTree(Long pkAppRole)
			throws SystemException {
		return appRoleFunctionDAO.getAccessTree(pkAppRole==null?0L:pkAppRole);
	}

	@Override
	public void updateRoleAccessbility(Role role, Long[] pkAppFunction, Boolean isFO)
			throws SystemException {
		Long[] newAppFunction;
		
		if(isFO){
			newAppFunction = buildFORoleFunction(pkAppFunction);
		}else{
			newAppFunction = buildRoleFunction(pkAppFunction);
		}
		
		roleDAO.saveOrUpdate(role);
		appRoleFunctionDAO.delete(role.getId());
		appRoleFunctionDAO.insert(role.getId(), newAppFunction);
	}
	
	private Long[] buildRoleFunction(Long[] pkAppFunction) {
		if(pkAppFunction==null) return new Long[0];
		Set<Long> newAppFunction = new LinkedHashSet<Long>();
		Set<Long> myTaskFunction = new LinkedHashSet<Long>();
		for (int i=0 ; i<pkAppFunction.length ; i++) {
			if(AccessibilityConstant.FUNC_BANK_MY_TASK_MAP.containsKey(pkAppFunction[i])){
				myTaskFunction.add(AccessibilityConstant.FUNC_BANK_MY_TASK_MAP.get(pkAppFunction[i]));
			}
		}
		/*if(myTaskFunction.size()>0){
			newAppFunction.add(AccessibilityConstant.FUNC_MY_TASK);
			newAppFunction.addAll(myTaskFunction);
		}*/
		newAppFunction.addAll(Arrays.asList(pkAppFunction));
		return newAppFunction.toArray(new Long[newAppFunction.size()]);
	}
	
	private Long[] buildFORoleFunction(Long[] pkAppFunction) {
		if(pkAppFunction==null) return new Long[0];
		Set<Long> newAppFunction = new LinkedHashSet<Long>();
		Set<Long> myTaskFunction = new LinkedHashSet<Long>();
		
		/*if(myTaskFunction.size()>0){
			newAppFunction.add(AccessibilityConstant.FUNC_MY_TASK);
			newAppFunction.addAll(myTaskFunction);
		}*/
		
		// add view own activity
		newAppFunction.add(AccessibilityConstant.FUNC_FO_VIEW_OWN_ACTIVITY);
		newAppFunction.add(AccessibilityConstant.FUNC_FO_VIEW_OWN_ACTIVITY_LIST);
		
		newAppFunction.addAll(Arrays.asList(pkAppFunction));
		return newAppFunction.toArray(new Long[newAppFunction.size()]);
	}

	@Override
	public List<AppRoleFunction> findAppRoleFunction(Long roleId, int userType)
			throws SystemException {
		return appRoleFunctionDAO.findAppRoleFunction(roleId==null?0L:roleId, userType);
	}

}