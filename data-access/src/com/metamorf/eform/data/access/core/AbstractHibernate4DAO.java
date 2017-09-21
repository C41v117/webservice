package com.metamorf.eform.data.access.core;



import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.BatchUpdateException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.Id;

import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.exception.ErrorHolder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingUtil;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.common.util.ReflectionFunction;

/**
 * AbstractHibernateDAO is abstract class to further wrap basic CRUD functionality
 * for DAO. This is to allow standardize DAO processing methods.
 * 
 */
public class AbstractHibernate4DAO<T,Y extends Serializable> {
	
	protected final static Logger LOG = LoggerFactory.getLogger(AbstractHibernate4DAO.class);
	
	protected Class<T> domainClass = getDomainClass();
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
	    return sessionFactory.getCurrentSession();
	}

	private Class<T> getDomainClass() {
	    if (domainClass == null) {
	    	ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();
	        domainClass = (Class<T>) thisType.getActualTypeArguments()[0];
	    }
	    return domainClass;
	}    
    
	public final void create(final Object anObject) throws SystemException {
		create(anObject,false);
	}
	
	public final void create(final Object anObject,final boolean flush) throws SystemException {
		try{
			getSession().save(anObject) ; 
			if(flush)
			   getSession().flush();	
		}catch (HibernateException e) {
			Exception e2 = e;
			while ((e2 = (Exception) e2.getCause()) != null) {
				e2.printStackTrace();
				if (e2 instanceof BatchUpdateException) {
					SQLException exception = ((BatchUpdateException) e2).getNextException();
					if (exception != null) {
						exception.printStackTrace();
						throw new SystemException(exception);
					}
					else
						throw new SystemException(e2);
				}
			}
			LOG.error(e.getMessage());
			LOG.error("create.error",e);
			throw new SystemException(new ErrorHolder("id", new Object[]{e.getMessage()}));
		}
	}
	
	public final Long createGetPK(final Object anObject,final boolean flush) throws SystemException {
		Long pk = (Long) getSession().save(anObject) ;
		if(flush)
			getSession().flush();	
		return pk;
	}

	public final void create(final List<T> list) throws SystemException {
		for (T object : list) {
			create(object,false);
		}
	}
	
	public final void updateWithUpdate(final Object anObject) throws SystemException {
		try{
			getSession().update(anObject); 
			getSession().flush();
		}catch (HibernateException e) {
			Exception e2 = e;
			while ((e2 = (Exception) e2.getCause()) != null) {
				e2.printStackTrace();
				if (e2 instanceof BatchUpdateException) {
					SQLException exception = ((BatchUpdateException) e2).getNextException();
					exception.printStackTrace();
				}
			}
			LOG.error("update.error",e);
			throw new SystemException(new ErrorHolder("id", new Object[]{e.getMessage()}));
		}
	}	
	
	public final void update(final Object anObject) throws SystemException {
		try{
			getSession().merge(anObject); 
			getSession().flush();
		}catch (HibernateException e) {
			/*Exception e2 = e;
			while ((e2 = (Exception) e2.getCause()) != null) {
				e2.printStackTrace();
				if (e2 instanceof BatchUpdateException) {
					SQLException exception = ((BatchUpdateException) e2).getNextException();
					exception.printStackTrace();
				}
			}*/
			LOG.error("update.error",e);
			throw new SystemException(new ErrorHolder("id", new Object[]{e.getMessage()}));
		}
	}
	
	public final void update(final List<T> list) throws SystemException {
		for (T object : list) {
			update(object);
		}
	}

	
	public final void delete(final Object anObject) throws SystemException {
		getSession().delete(anObject) ; getSession().flush();
	}
	
	public void delete(final List<T> objectList) throws SystemException {
		for (T object : objectList) {
			getSession().delete(object);
		}
		getSession().flush();
	}
	
		
	
	
	
        
   
	public T findByPK(final Y id) throws SystemException {
		return (T) getSession().get(domainClass, id);
	}
	
	public T findByPK(final Y id, final String[] lazyProps) throws SystemException {
		Criteria crit = getSession().createCriteria(domainClass);
		Method[] methods = domainClass.getDeclaredMethods();
		String idName = "id";
		for (Method method : methods) {
			if(method.isAnnotationPresent(Id.class)){
				idName = method.getName();
				idName = idName.replace("get", "");
				idName = String.valueOf(idName.charAt(0)).toLowerCase()+idName.substring(1, idName.length());
				break;
			}
		}
		crit.add(Restrictions.eq(idName, id));
		for (String lazyProp : lazyProps) {
			LOG.debug("lazyProps is [{}]", lazyProp);
			crit.setFetchMode(lazyProp, FetchMode.JOIN);
		}
		return (T)crit.uniqueResult();
	}

    public List<T> findByQueryString(final String queryString,final Object[] params) 
    throws SystemException {
		Query query = getSession().createQuery(queryString);
		for (int j = 0; j < params.length; j++) {
			query.setParameter(j, params[j]);
		}
		return query.list();
    }
    

	protected Long getRowCount(final List<Criterion> criterias)
			throws SystemException {
		Criteria criteria = getSession().createCriteria(domainClass
				.getName());
		if (criterias != null) {
			for (Criterion criterion : criterias) {
				criteria.add(criterion);
			}
		}
		Long res = (Long) criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		return res;
	}
	
	protected Long getRowCount(final List<Criterion> criterias, SearchAlias[] searchAliases)
			throws SystemException {
		Criteria criteria = getSession().createCriteria(domainClass
				.getName());
		if (criterias != null) {
			for (Criterion criterion : criterias) {
				criteria.add(criterion);
			}
		}
		if (searchAliases != null) {
			for (SearchAlias searchAlias : searchAliases) {
				if(searchAlias.getAliasJoinType()!=null){
					criteria.createAlias(searchAlias
							.getAliasProperty(), searchAlias
							.getAliasName(), searchAlias.getAliasJoinType());
				}else{
					criteria.createAlias(searchAlias
						.getAliasProperty(), searchAlias
						.getAliasName());
				}
			}
		}
		Long res = (Long) criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		return res;
	}
	
	protected Long getRowCount(final SearchAlias[] searchAliases, final List<Criterion> criterias)
			throws SystemException {
		Criteria criteria = getSession().createCriteria(domainClass
				.getName());
		
		if (searchAliases != null) {
			for (SearchAlias searchAlias : searchAliases) {
				if(searchAlias.getAliasJoinType()!=null){
					criteria.createAlias(searchAlias
							.getAliasProperty(), searchAlias
							.getAliasName(), searchAlias.getAliasJoinType());
				}else{
					criteria.createAlias(searchAlias
						.getAliasProperty(), searchAlias
						.getAliasName());
				}
			}
		}
		
		if (criterias != null) {
			for (Criterion criterion : criterias) {
				criteria.add(criterion);
			}
		}
		Long res = (Long) criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		return res;
	}

	protected List<T> findByCriterion(final List<Criterion> criterias,
			final List<Order> orders, final Integer startIndex,
			final Integer maxRow, final String cacheRegion)
			throws SystemException {
		return findAndFetchByCriterion(null,null, criterias, orders, startIndex, maxRow, cacheRegion, false);
	}
	
	@SuppressWarnings("unchecked")
	protected List<T> findAndFetchByCriterion(final SearchAlias[] searchAliases, 
			final String[] fetchProperties,  final List<Criterion> criterias,
			final List<Order> orders, final Integer startIndex,
			final Integer maxRow, final String cacheRegion, final boolean isDistinct, final String... joinEntity)
			throws SystemException {
		Criteria criteria = getSession().createCriteria(domainClass);

		if (searchAliases != null) {
			for (SearchAlias searchAlias : searchAliases) {
				if(searchAlias.getAliasJoinType()!=null){
					criteria.createAlias(searchAlias
							.getAliasProperty(), searchAlias
							.getAliasName(), searchAlias.getAliasJoinType());
				}else{
					criteria.createAlias(searchAlias
						.getAliasProperty(), searchAlias
						.getAliasName());
				}
				criteria.setFetchMode(searchAlias.getAliasName(), FetchMode.JOIN);
			}
		}

		if (fetchProperties != null) {
			ProjectionList projectionList = Projections.projectionList();

			for (String fetchedProperty : fetchProperties) {
				projectionList.add(Projections.property(fetchedProperty));
			}

			if (isDistinct) {
				criteria.setProjection(Projections.distinct(projectionList));
				//criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			}
			else {
				criteria.setProjection(projectionList);
			}
			if(searchAliases!=null){
				criteria.setResultTransformer(new DynamicResultTransformer(domainClass, fetchProperties, joinEntity, searchAliases));
			}else{
				criteria.setResultTransformer(new DynamicResultTransformer(domainClass, fetchProperties, joinEntity));
			}
		}

		if (criterias != null) {
			for (Criterion criterion : criterias) {
				criteria.add(criterion);
			}
		}

		if (orders != null) {
			for (Order order : orders) {
				criteria.addOrder(order);
			}
		}

		List<T> list = new ArrayList<T>();

		if (startIndex != null)
			if (startIndex>0)
				criteria.setFirstResult(startIndex - 1);

		if (maxRow != null)
			if (maxRow>0)
				criteria.setMaxResults(maxRow);

		if (cacheRegion != null) {
			criteria.setCacheable(true);
			criteria.setCacheRegion(cacheRegion);
		}
		
		Set<T> set = new LinkedHashSet<T>();
		set.addAll(criteria.list());

		Iterator<T> iterator = set.iterator();

		while (iterator.hasNext()) {
			T object = (T) iterator.next();
			list.add(object);
		}

		return list;
	}
	
	@SuppressWarnings("unchecked")
	protected T findAndFetchObjectByCriterion(final SearchAlias[] searchAliases  , 
			final String[] fetchProperties,  final List<Criterion> criterias,
			final List<Order> orders, final String cacheRegion){
		Criteria criteria = getSession().createCriteria(domainClass);

		if (searchAliases != null) {
			for (SearchAlias searchAlias : searchAliases) {
				if(searchAlias.getAliasJoinType()!=null){
					criteria.createAlias(searchAlias
							.getAliasProperty(), searchAlias
							.getAliasName(), searchAlias.getAliasJoinType());
				}else{
					criteria.createAlias(searchAlias
							.getAliasProperty(), searchAlias
							.getAliasName());
				}
			}
		}

		if (fetchProperties != null) {
			ProjectionList projectionList = Projections
					.projectionList();
			for (String fetchedProperty : fetchProperties) {
				projectionList.add(Projections
						.property(fetchedProperty));
			}
			criteria.setProjection(projectionList);
			criteria
					.setResultTransformer(new DynamicResultTransformer(
							domainClass, fetchProperties));
		}

		if (criterias != null) {
			for (Criterion criterion : criterias) {
				criteria.add(criterion);
			}
		}

		if (orders != null) {
			for (Order order : orders) {
				criteria.addOrder(order);
			}
		}

		if (cacheRegion != null) {
			criteria.setCacheable(true);
			criteria.setCacheRegion(cacheRegion);
		}
		
		return (T) criteria.uniqueResult();
	}

	
	protected Integer getRowCount(final DetachedCriteria detachedCriteria ,final SearchAlias[] searchAliases,
			final List<Criterion> criterias) throws SystemException {
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		
		if (searchAliases != null) {
			for (SearchAlias searchAlias : searchAliases) {
				 criteria.createAlias(searchAlias
						.getAliasProperty(), searchAlias
						.getAliasName());
			}
		}
		
		if (criterias != null) {
			for (Criterion criterion : criterias) {
				criteria.add(criterion);
			}
		}
		return (Integer) criteria.setProjection(Projections.rowCount())
				.uniqueResult();
	}
	
	public List<T> findByFilter(final DetachedCriteria detachedCriteria , 
			final SearchAlias[] searchAliases,final String[] fetchProperties ,final List<SearchFilter> searchFilters,
			final List<SearchOrder> searchOrders, final Integer startIndex,
			final Integer maxRow, final String cacheRegion)
			throws SystemException { 
		List<Criterion> criterias = new LinkedList<Criterion>();
		List<Order> orders = new LinkedList<Order>();
		
		if (searchFilters != null) {
			for (SearchFilter searchFilter : searchFilters) {
				criterias.add(buildCriterion(searchFilter));
			}
		}

		if (searchOrders != null) {
			for (SearchOrder searchOrder : searchOrders) {
				orders.add(buildOrder(searchOrder));
			}
		}
		return findByCriterion(detachedCriteria,searchAliases,fetchProperties,criterias, orders, startIndex, maxRow, cacheRegion);
	}
	
	@SuppressWarnings("unchecked")
	protected List<T> findByCriterion(final DetachedCriteria detachedCriteria , 
			final SearchAlias[] searchAliases,final String[] fetchProperties ,final List<Criterion> criterias,
			final List<Order> orders, final Integer startIndex,
			final Integer maxRow, final String cacheRegion)
			throws SystemException {
			Criteria criteria = detachedCriteria
					.getExecutableCriteria(getSession());

			if (searchAliases != null) {
				for (SearchAlias searchAlias : searchAliases) {
					if(searchAlias.getAliasJoinType()!=null){
						criteria.createAlias(searchAlias
								.getAliasProperty(), searchAlias
								.getAliasName(), searchAlias.getAliasJoinType());
					}else{
						criteria.createAlias(searchAlias
							.getAliasProperty(), searchAlias
							.getAliasName());
					}
				}
			}

			if (fetchProperties != null) {
				ProjectionList projectionList = Projections.projectionList();

				for (String fetchedProperty : fetchProperties) {
					projectionList.add(Projections.property(fetchedProperty));
				}

				criteria.setProjection(projectionList);
				/*if(searchAliases==null){*/
					criteria.setResultTransformer(new DynamicResultTransformer(domainClass, fetchProperties));
				/*}else{
					criteria.setResultTransformer(Transformers.aliasToBean(domainClass));
				}*/
			}
			
			if (criterias != null) {
				for (Criterion criterion : criterias) {
					criteria.add(criterion);
				}
			}

			if (orders != null) {
				for (Order order : orders) {
					criteria.addOrder(order);
				}
			}

			List<T> list = new ArrayList<T>();

			if (startIndex != null)
				criteria.setFirstResult(startIndex - 1);

			if (maxRow != null)
				criteria.setMaxResults(maxRow);

			if (cacheRegion != null) {
				criteria.setCacheable(true);
				criteria.setCacheRegion(cacheRegion);
			}

			Set<T> set = new LinkedHashSet<T>();
			set.addAll(criteria.list());

			Iterator<T> iterator = set.iterator();

			while (iterator.hasNext()) {
				T object = (T) iterator.next();
				list.add(object);
			}

			return list;
	}

	public PagingWrapper<T> findAllWithPagingWrapper(final int startIndex,final int maxRow)
			throws SystemException {
		long _totalRecords = getRowCount(null);
		List<T> pList = findByCriterion(null, null, startIndex, maxRow,null);
		return new PagingWrapper<T>(pList, _totalRecords, startIndex, maxRow);
	}
    
   
    
	protected Order buildOrder(SearchOrder searchOrder) {
    	if(searchOrder.getSort()==SearchOrder.Sort.ASC)
    	   return Order.asc(searchOrder.getFieldName());
    	return Order.desc(searchOrder.getFieldName());
    }
    
    private Criterion getCriterion(SearchFilter searchFilter){
    	 LOG.debug(" filter : [{}] " ,searchFilter);
    	 switch (searchFilter.getOperand()) {
			case  EQUALS:
				  if(searchFilter.isIgnoreCase() && searchFilter.getValue() instanceof String){
					  return Restrictions.eq(searchFilter.getFieldName(), String.valueOf(searchFilter.getValue()).toLowerCase()).ignoreCase();
				  }else{
					  return Restrictions.eq(searchFilter.getFieldName(), searchFilter.getValue());
				  }
			case  EQUALS_OR_GREATER_THAN:
				  if(searchFilter.isIgnoreCase() && searchFilter.getValue() instanceof String){
					  return Restrictions.ge(searchFilter.getFieldName(), String.valueOf(searchFilter.getValue()).toLowerCase()).ignoreCase();
				  }else{
					  return Restrictions.ge(searchFilter.getFieldName(), searchFilter.getValue());
				  }
			case  EQUALS_OR_LESS_THAN:
				  if(searchFilter.isIgnoreCase() && searchFilter.getValue() instanceof String){
					  return Restrictions.le(searchFilter.getFieldName(), String.valueOf(searchFilter.getValue()).toLowerCase()).ignoreCase();
				  }else{
					  return Restrictions.le(searchFilter.getFieldName(), searchFilter.getValue());
				  }
			case  GREATER_THAN:
				  if(searchFilter.isIgnoreCase() && searchFilter.getValue() instanceof String){
					  return Restrictions.gt(searchFilter.getFieldName(), String.valueOf(searchFilter.getValue()).toLowerCase()).ignoreCase();
				  }else{
					  return Restrictions.gt(searchFilter.getFieldName(), searchFilter.getValue());
				  }
			case  AND :
				  if(searchFilter.isIgnoreCase() && searchFilter.getValue() instanceof String){
					  return Restrictions.gt(searchFilter.getFieldName(), String.valueOf(searchFilter.getValue()).toLowerCase()).ignoreCase();
				  }else{
					  return Restrictions.gt(searchFilter.getFieldName(), searchFilter.getValue());
				  }
			case  IN :  
				  return Restrictions.in(searchFilter.getFieldName(), (Collection)searchFilter.getValue());
			case  IS_NOT_NULL :  
				  return Restrictions.isNotNull(searchFilter.getFieldName());
			case  IS_NULL :  
				  return Restrictions.isNull(searchFilter.getFieldName());
			case  LESS_THAN :
				  if(searchFilter.isIgnoreCase() && searchFilter.getValue() instanceof String){
					  return Restrictions.lt(searchFilter.getFieldName(), String.valueOf(searchFilter.getValue()).toLowerCase()).ignoreCase();
				  }else{
					  return Restrictions.lt(searchFilter.getFieldName(), searchFilter.getValue());
				  }
			case  LIKE :   
				String likeValue = searchFilter.getValue().toString();
				if(StringUtils.containsAny(likeValue, CriteriaUtil.RESERVED_KEYWORD)){
					return new CriteriaUtil.EscapingLikeExpression(searchFilter.getFieldName(),likeValue);
				}else{
					return Restrictions.ilike(searchFilter.getFieldName(), "%".concat(likeValue).concat("%"));
				}
			case  START_END_LIKE :   
				  return Restrictions.ilike(searchFilter.getFieldName(), searchFilter.getValue().toString().concat("%"));
			case  NOT_EQUAL :
				  if(searchFilter.isIgnoreCase() && searchFilter.getValue() instanceof String){
					  return Restrictions.ne(searchFilter.getFieldName(),  String.valueOf(searchFilter.getValue()).toLowerCase()).ignoreCase();
				  }else{
					  return Restrictions.ne(searchFilter.getFieldName(),  searchFilter.getValue());
				  }
			case  IN_ARRAY :
				  return Restrictions.in(searchFilter.getFieldName(), (Object[]) searchFilter.getValue());
			case  NOT_IN_ARRAY :
				  return Restrictions.not(Restrictions.in(searchFilter.getFieldName(), (Object[]) searchFilter.getValue()));
			case  SQL_RESTRICTION :
				String[] values = (String[])searchFilter.getValue(); 
				org.hibernate.type.Type[] types = new org.hibernate.type.Type[values.length];
				Arrays.fill(types, org.hibernate.type.StringType.INSTANCE);
				return Restrictions.sqlRestriction( searchFilter.getFieldName(), values, types);
			case  SIMILAR_TO :
				  return Restrictions.sqlRestriction("{alias}." + ReflectionFunction.getColumnName(domainClass, searchFilter.getFieldName()) + " similar to ? ", searchFilter.getValue(), org.hibernate.type.StringType.INSTANCE);
		    default :
		    	  return null;
	 }
    }
    
    protected Criterion buildCriterion(SearchFilter searchFilter) {
    	if(searchFilter.isOr()){
    		return Restrictions.or(buildCriterion(searchFilter.getLeftFilter()), buildCriterion(searchFilter.getRightFilter()));
		}else if(searchFilter.isAnd()) {
			return Restrictions.and(buildCriterion(searchFilter.getLeftFilter()), buildCriterion(searchFilter.getRightFilter()));
		}else{
    		return getCriterion(searchFilter);
    	}
    }
    
    public PagingWrapper<T> findAllWithPagingWrapper(DetachedCriteria detachedCriteria,int startIndex,
			int maxRow,SearchAlias[] searchAlias , String[] fetchProperties ,List<SearchFilter> searchFilters,
			List<SearchOrder> searchOrders, String cacheRegion)
			throws SystemException {
    	DetachedCriteria clonedDetachedCriteria = (DetachedCriteria)SerializationUtils.clone(detachedCriteria);
		List<Criterion> criterionList = new LinkedList<Criterion>();
		List<Order> orderList = new LinkedList<Order>();

		if (searchFilters != null) {
			for (SearchFilter filter : searchFilters) {
				criterionList.add(buildCriterion(filter));
			}
		}

		if (searchOrders != null) {
			for (SearchOrder searchOrder : searchOrders) {
				orderList.add(buildOrder(searchOrder));
			}
		}

		int _totalRecords = getRowCount(detachedCriteria, searchAlias ,criterionList);
		List<T> pList = findByCriterion(clonedDetachedCriteria,searchAlias,fetchProperties,criterionList, orderList, startIndex,
				maxRow, cacheRegion);

		return new PagingWrapper<T>(pList, _totalRecords, startIndex, maxRow);

	}
    
	public PagingWrapper<T> findAllWithPagingWrapper(int startIndex,
			int maxRow, List<SearchFilter> searchFilters,
			List<SearchOrder> searchOrders, String cacheRegion)
			throws SystemException {
		int _startIndex = startIndex;
		List<Criterion> criterionList = new LinkedList<Criterion>();
		List<Order> orderList = new LinkedList<Order>();

		if (searchFilters != null) {
			for (SearchFilter filter : searchFilters) {
				criterionList.add(buildCriterion(filter));
			}
		}

		if (searchOrders != null) {
			for (SearchOrder searchOrder : searchOrders) {
				orderList.add(buildOrder(searchOrder));
			}
		}

		long _totalRecords = getRowCount(criterionList);
		// normalize startIndex (in case startIndex > totalRecords/maxRow)
		if (_startIndex > _totalRecords) {
			_startIndex = PagingUtil.getLastPageStartRow(_totalRecords, maxRow);
		}
		List<T> pList = findByCriterion(criterionList, orderList, _startIndex,
				maxRow, cacheRegion);

		return new PagingWrapper<T>(pList, _totalRecords, _startIndex, maxRow);

	}
	
	public PagingWrapper<T> findFetchedPropertyWithPagingWrapper(int startIndex,
			int maxRow,SearchAlias[] searchAlias ,String[] fetchedProperties ,List<SearchFilter> searchFilters,
			List<SearchOrder> searchOrders, String cacheRegion, boolean isDistinct, String... joinEntity)
			throws SystemException {
		int _startIndex = startIndex;
		List<Criterion> criterionList = new LinkedList<Criterion>();
		List<Order> orderList = new LinkedList<Order>();

		if (searchFilters != null) {
			for (SearchFilter filter : searchFilters) {
				criterionList.add(buildCriterion(filter));
			}
		}

		if (searchOrders != null) {
			for (SearchOrder searchOrder : searchOrders) {
				orderList.add(buildOrder(searchOrder));
			}
		}

		long _totalRecords = getRowCount(searchAlias, criterionList);
		// normalize startIndex (in case startIndex > totalRecords/maxRow)
		if (_startIndex > _totalRecords) {
			_startIndex = PagingUtil.getLastPageStartRow(_totalRecords, maxRow);
		}
		List<T> pList = findAndFetchByCriterion(searchAlias,fetchedProperties,criterionList, orderList, _startIndex,
				maxRow, cacheRegion, isDistinct, joinEntity);

		return new PagingWrapper<T>(pList, _totalRecords, _startIndex, maxRow);

	}
	
	public List<T> findFetchedProperty(int startIndex,
			int maxRow,SearchAlias[] searchAlias ,String[] fetchedProperties ,List<SearchFilter> searchFilters,
			List<SearchOrder> searchOrders, String cacheRegion, boolean isDistinct, String... joinEntity)
			throws SystemException {
		int _startIndex = startIndex;
		List<Criterion> criterionList = new LinkedList<Criterion>();
		List<Order> orderList = new LinkedList<Order>();

		if (searchFilters != null) {
			for (SearchFilter filter : searchFilters) {
				criterionList.add(buildCriterion(filter));
			}
		}

		if (searchOrders != null) {
			for (SearchOrder searchOrder : searchOrders) {
				orderList.add(buildOrder(searchOrder));
			}
		}

		// normalize startIndex (in case startIndex > totalRecords/maxRow)
		long _totalRecords = getRowCount(searchAlias, criterionList);
		if (_startIndex > _totalRecords) {
			_startIndex = PagingUtil.getLastPageStartRow(_totalRecords, maxRow);
		}
		List<T> pList = findAndFetchByCriterion(searchAlias,fetchedProperties,criterionList, orderList, _startIndex,
				maxRow, cacheRegion, isDistinct, joinEntity);

		return pList;

	}

    public List<T> findAll(List<SearchFilter> searchFilters,List<SearchOrder> searchOrders,String cacheRegion)
    	throws SystemException{
    	List<Criterion> criterionList = new LinkedList<Criterion>();
		List<Order> orderList = new LinkedList<Order>();

		if (searchFilters != null) {
			for (SearchFilter filter : searchFilters) {
				criterionList.add(buildCriterion(filter));
			}
		}

		if (searchOrders != null) {
			for (SearchOrder searchOrder : searchOrders) {
				orderList.add(buildOrder(searchOrder));
			}
		}

		return findByCriterion(criterionList, orderList, null, null, cacheRegion);

    }
    
	protected List<T> findFetchedPropertyList(SearchAlias[] searchAlias,
			String[] fetchedProperty, List<SearchFilter> searchFilters,
			List<SearchOrder> searchOrders, String cacheRegion, String... joinEntity)
			throws SystemException {
		List<Criterion> criterionList = new LinkedList<Criterion>();
		List<Order> orderList = new LinkedList<Order>();

		if (searchFilters != null) {
			for (SearchFilter filter : searchFilters) {
				criterionList.add(buildCriterion(filter));
			}
		}

		if (searchOrders != null) {
			for (SearchOrder searchOrder : searchOrders) {
				orderList.add(buildOrder(searchOrder));
			}
		}
		return findAndFetchByCriterion(searchAlias, fetchedProperty,
				criterionList, orderList, null, null, cacheRegion, false,joinEntity);
	}
	
	public void partialUpdate(final String[] fields, final Object[] args,
			final String idField, final Y idVal) throws SystemException {
		StringBuffer queryString = new StringBuffer(" update ").append(
				getDomainClass().getName()).append(" set ");
		StringBuffer updateString = new StringBuffer();
		for (String field : fields) {
			updateString.append(field).append(" = ? ,");
		}
		String updateStatement = updateString.substring(0, updateString.length()-1);
		queryString.append(updateStatement);
		queryString.append(" where ").append(idField).append("  = ? ");
		Query query = getSession().createQuery(queryString.toString());
		int i = 0;
		for (Object param : args) {
			query.setParameter(i, param);
			i++;
		}
		query.setParameter(i, idVal);
		query.executeUpdate();
	}
	
	public void partialUpdate(final String[] fields, final Object[] args,
			final String[] idFields, final Object[] idVals) throws SystemException {
		StringBuffer queryString = new StringBuffer(" update ").append(
				getDomainClass().getName()).append(" set ");
		StringBuffer updateString = new StringBuffer();
		for (String field : fields) {
			updateString.append(field).append(" = ? ,");
		}
		String updateStatement = updateString.substring(0, updateString.length()-1);
		queryString.append(updateStatement);
		queryString.append(" where ");
		int j = idFields.length;
		for (String idField : idFields) {
			queryString.append(idField).append(" = ? ");
			j--;
			if(j!=0){
				queryString.append(" and ");
			}
		}
		Query query = getSession().createQuery(queryString.toString());
		int i = 0;
		for (Object param : args) {
			query.setParameter(i, param);
			i++;
		}
		for (Object idVal : idVals) {
			query.setParameter(i, idVal);
			i++;
		}
		query.executeUpdate();
	}

        
	public List<T> findAll(int startIndex , int maxRow) throws SystemException {
		return findByCriterion(null, null, startIndex, maxRow, null);
	}
	
	public List<T> findAll() throws SystemException {
		return findByCriterion(null, null, null, null, null);
	}
    
	public List<T> findAll(List<SearchFilter> filters, List<SearchOrder> orders,
			String[] fetchedProperties, String[] groupProperties) throws SystemException {
		Criteria criteria = getSession().createCriteria(domainClass);

		if (filters != null) {
			for (SearchFilter searchFilter : filters) {
				criteria.add(buildCriterion(searchFilter));
			}
		}

		if (orders != null) {
			for (SearchOrder searchSettlement : orders) {
				criteria.addOrder(buildOrder(searchSettlement));
			}
		}

		ProjectionList projectionList = Projections.projectionList();

		for (String fetchedProperty : fetchedProperties) {
			projectionList.add(Projections.property(fetchedProperty));
		}

		for (String groupProperty : groupProperties) {
			projectionList.add(Projections.groupProperty(groupProperty));
		}

		criteria.setProjection(projectionList);
		criteria.setResultTransformer(new DynamicResultTransformer(domainClass, fetchedProperties));

		return criteria.list();
		
	}
	
	public void refreshEntity(T obj){
		getSession().refresh(obj);
	}
	
	protected BigDecimal getBigDecimal(Object value) {
		/*BigDecimal result = new BigDecimal(0);
		if (value!=null) {
			result = BigDecimal.valueOf(Double.valueOf(String.valueOf(value)));
			//result = (BigDecimal)value;
			
		}
		return result;*/
		
		BigDecimal ret = null;
        if( value != null ) {
            if( value instanceof BigDecimal ) {
                ret = (BigDecimal) value;
            } else if( value instanceof String ) {
                ret = new BigDecimal( (String) value );
            } else if( value instanceof BigInteger ) {
                ret = new BigDecimal( (BigInteger) value );
            } else if( value instanceof Number ) {
                ret = new BigDecimal( ((Number)value).doubleValue() );
            } else {
                throw new ClassCastException("Not possible to coerce ["+value+"] from class "+value.getClass()+" into a BigDecimal.");
            }
        }
        return ret;
	}
	
	protected Boolean getBoolean(Object value) {
		Boolean result = false;
		if (value!=null) {
			result = Boolean.valueOf(String.valueOf(value));
		}
		return result;
	}
	
	protected Integer getInteger(Object value) {
		Integer result = 0;
		if (value!=null) {
			result = Integer.valueOf(String.valueOf(value));
		}
		return result;
	}
	
	protected Long getLong(Object value) {
		Long result = 0L;
		if (value!=null) {
			result = Long.valueOf(String.valueOf(value));
		}
		return result;
	}

	protected String getString(Object value) {
		if (value == null) {
			return "";
		}
		return value.toString();
	}
}