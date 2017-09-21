/**
 * 
 */
package com.metamorf.eform.interfaces.security;

import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.entity.settings.AppFunction;
import com.metamorf.eform.entity.settings.UserGroupAccessNode;
import com.metamorf.eform.entity.user.Role;
import com.metamorf.eform.interfaces.core.IBaseService;

import java.util.List;


/**
 * @author Hendri Yauw
 *
 */
public interface IRoleService extends IBaseService<Role>{
	public List<Role> findAll(int startNo, int offset,List<SearchFilter> searchFilters, List<SearchOrder> searchOrders) throws SystemException; 
	public List<Role> findAll() throws SystemException;
	public List<Role> findAllInternal(Integer lob) throws SystemException;
	public List<Role> findAllExternal() throws SystemException;
	public Role findById(Long id) throws SystemException;
	public List<UserGroupAccessNode> convertToUserGroupAccessNode(List<AppFunction> listAllAppFunctions, Long[] listAllAppFunctionSelected);
	public Role findByName(String name) throws SystemException;
	public Role findByCode(String code) throws SystemException;
}
