/**
 * 
 */
package com.metamorf.eform.data.access.security;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.metamorf.eform.common.core.SystemConstant;
import com.metamorf.eform.common.core.SystemConstant.UserType;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.data.access.core.AbstractHibernate4DAO;
import com.metamorf.eform.entity.settings.AppFunction;
import com.metamorf.eform.entity.settings.AppRoleFunction;
import com.metamorf.eform.entity.settings.UserGroupAccessNode;
import com.metamorf.eform.entity.user.Role;

/**
 * @author Hendri Yauw
 *
 */
public class AppRoleFunctionDAO extends AbstractHibernate4DAO<AppRoleFunction, Long> implements
		IAppRoleFunctionDAO {

	@Override
	public List<UserGroupAccessNode> getAccessTree(long groupPK)
			throws SystemException {
		List<AppFunction> ret;
		List<AppRoleFunction> ret2;
		List<AppFunction> ret3 = new ArrayList<AppFunction>();
		
		Criteria criteria = getSession().createCriteria(AppFunction.class).add(Restrictions.eq(SystemConstant.USER_TYPE, UserType.INTERNAL));
		criteria.add(Restrictions.eq("isActive",Boolean.TRUE));
		criteria.addOrder(Order.asc("id"));
		
		Criteria criteria2 = getSession().createCriteria(AppRoleFunction.class)
				.createAlias("role", "role").createAlias("appFunction", "appFunction")
				.add(Restrictions.eq("role.id", groupPK)).add(Restrictions.eq("appFunction."+SystemConstant.USER_TYPE, UserType.INTERNAL));
		
		ret = criteria.list();
		ret2 = criteria2.list();
		
		if(ret2.size()>0){
			for(AppRoleFunction appRoleFunction : ret2){
				ret3.add(appRoleFunction.getAppFunction());
			}
		}
		
		List<UserGroupAccessNode> nodes = new LinkedList<UserGroupAccessNode>();
		if(ret.size() > 0){
			for (AppFunction appFunction : ret) {
				UserGroupAccessNode node = new UserGroupAccessNode();
				node.setChildPK(appFunction.getId());
				if(appFunction.getParentId() != null) {
        			node.setParentPK(appFunction.getParentId());
        		}
				else {
        			node.setParentPK(-1);
        		}
				node.setName(appFunction.getName());
				if(ret3.contains(appFunction)) {
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
	public void delete(Long roleId) throws SystemException {
		List<AppRoleFunction> ret2;
		Criteria criteria = getSession().createCriteria(AppRoleFunction.class);
		Criteria subCriteria = criteria.createAlias("role", "role");
		subCriteria.add(Restrictions.eq("role.id", roleId));
		ret2 = criteria.list();
		super.delete(ret2);
	}

	@Override
	public void insert(Long roleId, Long[] pkAppFunction) throws SystemException {
		List<AppRoleFunction> objList = new ArrayList<AppRoleFunction>();
		for(int i=0;i<pkAppFunction.length;i++){
			AppRoleFunction obj = new AppRoleFunction();
			Role role = new Role();
			role.setId(roleId);
			obj.setRole(role);
			AppFunction appFunction = new AppFunction();
			appFunction.setId(pkAppFunction[i]);
			obj.setAppFunction(appFunction);
			objList.add(obj);
		}
		super.create(objList);
	}

	@Override
	public List<AppRoleFunction> findAppRoleFunction(long roleId, int userType)
			throws SystemException {
		List<AppRoleFunction> list;
		
		Criteria criteria = getSession().createCriteria(AppRoleFunction.class)
				.createAlias("role", "role").createAlias("appFunction", "appFunction")
				.add(Restrictions.eq("role.id", roleId)).add(Restrictions.eq("appFunction."+SystemConstant.USER_TYPE, userType));
		
		list = criteria.list();
		
		return list;
	}

	@Override
	public void saveOrUpdate(AppRoleFunction anObject) throws SystemException {
		if(anObject.getId()==null) {
			super.create(anObject);
		} else {
			super.update(anObject);
		}
	}
	
	@Override
	public Boolean doesThisRoleHasThisFunction(Long roleId, Long accessibility) throws SystemException{
		Criteria criteria = getSession().createCriteria(AppRoleFunction.class)
				.createAlias("role", "role").createAlias("appFunction", "appFunction")
				.add(Restrictions.eq("role.id", roleId)).add(Restrictions.eq("appFunction.id", accessibility));
		AppRoleFunction function = (AppRoleFunction)criteria.uniqueResult();
		return function!=null?true:false;
	}

}
