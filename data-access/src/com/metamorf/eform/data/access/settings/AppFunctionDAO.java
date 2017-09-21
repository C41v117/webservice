package com.metamorf.eform.data.access.settings;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;

import com.metamorf.eform.common.core.AccessibilityConstant;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.data.access.core.AbstractHibernate4DAO;
import com.metamorf.eform.entity.settings.AppFunction;

public class AppFunctionDAO extends AbstractHibernate4DAO<AppFunction, Long> implements IAppFunctionDAO {
	
	static final String FIND_ALL_APP_FUNCTION = 
			/*"with n (id, access_page, descr, is_active, name, namespace, order_no, parent_id, user_type, is_viewable) as "+
			"	( "+*/
			"	select id, access_page, descr, is_active, name, namespace, order_no, parent_id, user_type, is_viewable from app_function "+
			"	where is_active = ? and user_type = ? and is_viewable = ? "+
			/*"	union all "+
			"	select nplus.id, nplus.access_page, nplus.descr, nplus.is_active, nplus.name, nplus.namespace, nplus.order_no, nplus.parent_id, nplus.user_type, nplus.is_viewable from app_function as nplus, n "+
//			"	where nplus.parent_id = n.id "+
			"	where n.id = n.parent_id "+
			"	) "+
			"	select * from n order by order_no, id asc";*/
			" order by order_no, id asc";
	
	
	public List<AppFunction> findAllAppFunction() throws SystemException {
		return (List<AppFunction>)super.findAll();
	}
	
	@SuppressWarnings("unchecked")
	public List<AppFunction> findAppFunctionMenuByUserRole(Long appRole) {
		return getSession()
				.createCriteria(AppFunction.class)
				.createAlias("appRoleFunctions", "apr")
				.add(Restrictions.eq("isActive", Boolean.TRUE))
				.add(Restrictions.eq("apr.role.id", appRole))
				.add(
						Restrictions
								.in(
										AppFunction.PARENT_ID,
										Arrays
												.asList(new Long[] {
														Long.valueOf(AccessibilityConstant.FUNC_ROOT),
														Long.valueOf(AccessibilityConstant.FUNC_SECURITY),
														Long.valueOf(AccessibilityConstant.FUNC_SETTING),
														Long.valueOf(AccessibilityConstant.FUNC_WORKFLOW_MONITORING),
														Long.valueOf(AccessibilityConstant.FUNC_REPORT),
														Long.valueOf(AccessibilityConstant.FUNC_REPORT_PURNA),
														Long.valueOf(AccessibilityConstant.FUNC_MASTER_DATA_LORINA)
														})))
				.addOrder(Order.asc(AppFunction.MENU_ORDER_NO)).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<AppFunction> findAppFunctionTreeMenuByUserRole(Long appRole, Long[] parentId) {
		return getSession()
				.createCriteria(AppFunction.class)
				.createAlias("appRoleFunctions", "apr")
				.add(Restrictions.eq("isActive", Boolean.TRUE))
				.add(Restrictions.eq("apr.role.id", appRole))
				.add(Restrictions.in(AppFunction.PARENT_ID, Arrays.asList(parentId)))
				.addOrder(Order.asc(AppFunction.ORDER_NO)).list();
	}
	
	@Override
	public List<AppFunction> findAppFunctionByPermission(Long appRole) {
		return getSession()
				.createCriteria(AppFunction.class)
				.createAlias("appRoleFunctions", "apr")
				.add(Restrictions.eq("apr.role.id", appRole))
				.addOrder(Order.asc(AppFunction.ORDER_NO)).list();
	}
	
	@Override
	public List<AppFunction> findAppFunction(boolean isActive, int userType)
			throws SystemException {
		/* change with query to support order by tree
		List<AppFunction> list;
		
		Criteria criteria = getSession().createCriteria(AppFunction.class).add(Restrictions.eq(SystemConstant.USER_TYPE, userType));
		criteria.add(Restrictions.eq("isActive",isActive));
		criteria.addOrder(Order.asc("id"));
		
		list = criteria.list();*/
		return getSession().createSQLQuery(FIND_ALL_APP_FUNCTION)
			.setParameter(0, isActive)
			.setParameter(1, userType)
			.setParameter(2, Boolean.TRUE)
			.setResultTransformer(
					new ResultTransformer() {
						
						@Override
						public Object transformTuple(Object[] results, String[] aliases) {
							AppFunction appFunction = new AppFunction();
							appFunction.setId(Long.valueOf(String.valueOf(results[0])));
							appFunction.setAccessPage(String.valueOf(results[1]));
							appFunction.setDescr(String.valueOf(results[2]));
							appFunction.setIsActive(Boolean.valueOf(String.valueOf(results[3])));
							appFunction.setName(String.valueOf(results[4]));
							appFunction.setNamespace(String.valueOf(results[5]));
							appFunction.setOrderNo(Long.valueOf(String.valueOf(results[6])));
							appFunction.setParentId(results[7]==null?null:Long.valueOf(String.valueOf(results[7])));
							appFunction.setUserType(Integer.valueOf(String.valueOf(results[8])));
							appFunction.setIsViewable(Boolean.valueOf(String.valueOf(results[9])));
							return appFunction;
						}

						@Override
						public List transformList(List list) {
							return list;
						}
			}).list();
	}

	@Override
	public List<AppFunction> findAppFunctionByAppFunctionMatrixId(Long id)
			throws SystemException {
		
		Criteria criteria = getSession().createCriteria(AppFunction.class);
		criteria.createAlias("appFunctionMatrixElements", "afme");
		criteria.add(Restrictions.eq("afme.appFunctionMatrix.id", id));
		
		return (List<AppFunction>)criteria.list();
	}

	@Override
	public AppFunction findById(Long id) throws SystemException {
		return super.findByPK(id);
	}
	
	@Override
	public int checkRoleHasFunction(Long roleId, Long appFunctionId) throws SystemException{
		String query = "SELECT count(af.id) "
					+" FROM app_function af with (nolock) " 
					+" JOIN app_role_function arf ON af.id = arf.app_function_id "
					+" WHERE arf.app_function_id = :appFunctionId "
					+" AND arf.role_id = :roleId";
		
		int result = ((Number) getSession().createSQLQuery(query)
				.setBigInteger("roleId", BigInteger.valueOf(roleId))
				.setBigInteger("appFunctionId", BigInteger.valueOf(appFunctionId))
				.uniqueResult()).intValue();
		
		return result;
	}
}
