/**
 * 
 */
package com.metamorf.eform.service.security;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.metamorf.eform.common.core.SystemConstant;
import com.metamorf.eform.common.core.SystemConstant.MasterDataApprovalStatus;
import com.metamorf.eform.common.core.SystemConstant.MasterDataStatus;
import com.metamorf.eform.common.core.SystemConstant.ProjectType;
import com.metamorf.eform.common.data.util.Operator;
import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.data.util.SearchOrder.Sort;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.data.access.security.IRoleDAO;
import com.metamorf.eform.entity.settings.AppFunction;
import com.metamorf.eform.entity.settings.UserGroupAccessNode;
import com.metamorf.eform.entity.user.Role;
import com.metamorf.eform.interfaces.security.IRoleService;

/**
 * @author Hendri Yauw
 *
 */
public class RoleService implements IRoleService {

	IRoleDAO roleDAO;
	
	public void setRoleDAO(IRoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	@Override
	public List<Role> findAll(int startNo, int offset,
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		return roleDAO.findAll(startNo, offset, searchFilters, searchOrders);
	}
	
	@Override
	public List<Role> findAll() throws SystemException {
		return roleDAO.findAll();
	}

	@Override
	public Role findById(Long id) throws SystemException {
		return roleDAO.findById(id);
	}
	
	@Override
	public Role findByName(String name) throws SystemException {
		return roleDAO.findRoleByName(name);
	}
	
	@Override
	public Role findByCode(String code) throws SystemException {
		return roleDAO.findRoleByCode(code);
	}

	@Override
	public void saveOrUpdate(Role anObject) throws SystemException {
		roleDAO.saveOrUpdate(anObject);
	}

	@Override
	public void delete(Role anObject) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PagingWrapper<Role> findAllWithPagingWrapper(int startNo,
			int offset, List<SearchFilter> searchFilters,
			List<SearchOrder> searchOrders) throws SystemException {
		return roleDAO.findAllByFilter(startNo, offset, searchFilters, searchOrders);
	}

	public List<UserGroupAccessNode> convertToUserGroupAccessNode (
			List<AppFunction> listAllAppFunctions,
			Long[] listAllAppFunctionSelected) {
		
		List<Long> arrLongAllAppFunctionSelected = new ArrayList<Long>();
		if (listAllAppFunctionSelected.length > 0) {
			for(Long pkAppFunction : listAllAppFunctionSelected){
				arrLongAllAppFunctionSelected.add(pkAppFunction);
			}
		}

		List<UserGroupAccessNode> nodes = new LinkedList<UserGroupAccessNode>();
		if(listAllAppFunctions.size() > 0){
			for (AppFunction appFunction : listAllAppFunctions) {
				UserGroupAccessNode node = new UserGroupAccessNode();
				node.setChildPK(appFunction.getId());
				if(appFunction.getParentId() != null) {
        			node.setParentPK(appFunction.getParentId());
        		}
				else {
        			node.setParentPK(-1);
        		}
				node.setName(appFunction.getName());
				if(arrLongAllAppFunctionSelected.contains(appFunction.getId())) {
        			node.setChecked(true);
        		}
				else {
        			node.setChecked(false);
        		}
				nodes.add(node);
			}
			return nodes;
		} 
		else {
        	return null;
        }
	}

	@Override
	public List<Role> findAllInternal(Integer lob) throws SystemException {
		List<SearchFilter> searchFilters = new LinkedList<SearchFilter>();
		searchFilters.add(new SearchFilter(Role.TYPE, Operator.EQUALS, SystemConstant.RoleType.INTERNAL));
		searchFilters.add(new SearchFilter(Role.STATUS, Operator.EQUALS, MasterDataStatus.ACTIVE));
		searchFilters.add(new SearchFilter(Role.APPROVAL_STATUS, Operator.EQUALS, MasterDataApprovalStatus.APPROVED));
		searchFilters.add(new SearchFilter(Role.IS_SUPER, Operator.EQUALS, SystemConstant.NOPOWER));
		if(!lob.equals(ProjectType.BOTH))
			searchFilters.add(new SearchFilter(Role.LOB, Operator.EQUALS, lob));
		
		List<SearchOrder> searchOrders = new LinkedList<SearchOrder>();
		searchOrders.add(new SearchOrder(Role.NAME, Sort.ASC));
		return roleDAO.findAll(0, 100, searchFilters, searchOrders);
	}

	@Override
	public List<Role> findAllExternal() throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> findReportCollection(List<SearchFilter> searchFilters,
			List<SearchOrder> searchOrders) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> findReportCollection(int startNo, int offset,
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}
}
