/**
 * 
 */
package com.metamorf.eform.data.access.security;

import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.data.access.core.IBaseDAO;
import com.metamorf.eform.entity.user.Role;

import java.util.List;

/**
 * @author Hendri Yauw
 *
 */
public interface IRoleDAO extends IBaseDAO<Role>{

	public abstract List<Role> findAll(int startNo, int offset,
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException;

	public abstract List<Role> findAll() throws SystemException;
	
	public abstract void saveOrUpdate(Role role) throws SystemException;
	
	public abstract Role findById(Long id) throws SystemException;

	public abstract boolean isRoleExistsByCode(String code, Long id) throws SystemException;

	public abstract boolean isRoleExistsByName(String name, Long id) throws SystemException;
	
	public boolean isRoleExistsByCodeAndName(String name, String code, Long id)	throws SystemException;

	public abstract boolean isUsedByActiveUser(Long id)  throws SystemException;

	boolean isCodePending(Long id) throws SystemException;
	
	public Role findRoleByName(String name) throws SystemException;
	
	public Role findRoleByCode(String code) throws SystemException;
	
	public Role findRoleByCodeAndLob(String code, Integer lob) throws SystemException;
}
