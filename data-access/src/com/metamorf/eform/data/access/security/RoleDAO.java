/**
 * 
 */
package com.metamorf.eform.data.access.security;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.metamorf.eform.common.core.SystemConstant;
import com.metamorf.eform.common.data.util.Operator;
import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.data.access.core.AbstractHibernate4DAO;
import com.metamorf.eform.entity.user.Role;
import com.metamorf.eform.entity.user.User;

/**
 * @author Hendri Yauw
 *
 */
public class RoleDAO extends AbstractHibernate4DAO<Role, Long> implements IRoleDAO {
	
	@Override
	public List<Role> findAll(int startNo, int offset,
			List<SearchFilter> filters, List<SearchOrder> orders)
			throws SystemException {
		return super.findFetchedProperty(startNo, offset, null, Role.MAINTENANCE_LIST_FIELDS, filters, orders, null, false);
	}
	
	@Override
	public List<Role> findAll() throws SystemException {
		return super.findAll();
	}

	@Override
	public void saveOrUpdate(Role anObject) throws SystemException {
		anObject.setIsSuper(SystemConstant.NOPOWER);
		if(anObject.getId()==null) {
			super.create(anObject);
		} else {
			super.update(anObject);
		}	
	}

	@Override
	public Role findById(Long id) throws SystemException {
		return super.findByPK(id);
	}
	
	public Role findRoleByName(String name) throws SystemException{
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		filters.add(new SearchFilter(Role.NAME, Operator.EQUALS, name));
		List<Role> result = super.findAll(filters, null, null);
		
		if (result.size() > 0) return result.get(0);
		return null;
	}
	
	public Role findRoleByCode(String code) throws SystemException{
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		filters.add(new SearchFilter(Role.CODE, Operator.EQUALS, code));
		List<Role> result = super.findAll(filters, null, null);
		
		if (result.size() > 0) return result.get(0);
		return null;
	}

	@Override
	public boolean isRoleExistsByCode(String code, Long id)
			throws SystemException {
		List<Criterion> criterias = new ArrayList<Criterion>();
		criterias.add(Restrictions.eq(Role.CODE, code.toLowerCase()).ignoreCase());
		if(id!=null){
			criterias.add(Restrictions.ne(Role.ID, id));
		}
		return getRowCount(criterias)>0?Boolean.TRUE:Boolean.FALSE;
	}

	@Override
	public boolean isRoleExistsByName(String name, Long id) throws SystemException {
		List<Criterion> criterias = new ArrayList<Criterion>();
		criterias.add(Restrictions.eq(Role.NAME, name));
		if(id!=null){
			criterias.add(Restrictions.ne(Role.ID, id));
		}
		return getRowCount(criterias)>0?Boolean.TRUE:Boolean.FALSE;
	}
	
	@Override
	public boolean isRoleExistsByCodeAndName(String name, String code, Long id)
			throws SystemException {
		List<Criterion> criterias = new ArrayList<Criterion>();
		criterias.add(Restrictions.or(
								Restrictions.eq(Role.CODE, code.toLowerCase()).ignoreCase(), 
								Restrictions.eq(Role.NAME, name)
								)
					 );
		if(id!=null){
			criterias.add(Restrictions.ne(Role.ID, id));
		}
		return getRowCount(criterias)>0?Boolean.TRUE:Boolean.FALSE;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isUsedByActiveUser(Long id) throws SystemException {
		List<User> list;
		Criteria criteria = getSession().createCriteria(User.class)
			.createAlias(User.ROLE, "role")
			.add(Restrictions.eq(User.ROLE_ID, id))
			.add(Restrictions.eq(User.STATUS, SystemConstant.MasterDataStatus.ACTIVE));
		
		list = criteria.list();
		return list.size()>0?Boolean.TRUE:Boolean.FALSE;
	}
	
	@Override
	public boolean isCodePending(Long id) throws SystemException {
		List<Criterion> criterias = new ArrayList<Criterion>();
		criterias.add(Restrictions.eq(Role.APPROVAL_STATUS, SystemConstant.MasterDataApprovalStatus.PENDING));
		criterias.add(Restrictions.eq(Role.ID, id));
		return getRowCount(criterias)>0?Boolean.TRUE:Boolean.FALSE;
	}
	
	@Override
	public PagingWrapper<Role> findAllByFilter(int startNo, int offset,
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		return super.findFetchedPropertyWithPagingWrapper(startNo, offset, null, Role.MAINTENANCE_LIST_FIELDS, searchFilters, searchOrders, null, true);
	}

	@Override
	public void deleteObject(Role anObject) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.metamorf.eform.data.access.security.IRoleDAO#findRoleByCodeAndLob(java.lang.String, java.lang.Integer)
	 */
	@Override
	public Role findRoleByCodeAndLob(String code, Integer lob) throws SystemException {
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		filters.add(new SearchFilter(Role.CODE, Operator.EQUALS, code));
		
		if (lob != null) {
			filters.add(new SearchFilter(Role.LOB, Operator.EQUALS, lob));
		}
		
		List<Role> result = super.findAll(filters, null, null);
		
		if (result.size() > 0) return result.get(0);
		return null;
	}
}
