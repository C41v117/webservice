package com.metamorf.eform.data.access.settings;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.metamorf.eform.common.core.SystemConstant.MasterDataStatus;
import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.data.access.core.AbstractHibernate4DAO;
import com.metamorf.eform.data.access.core.DynamicResultTransformer;
import com.metamorf.eform.entity.settings.Lookup;

public class LookupDAO extends AbstractHibernate4DAO<Lookup,Long> implements ILookupDAO {

	static final String UPDATE_HIGH_RISK =
			"update l "+ 
			"	set l.is_high_risk = 1, l.version = (select version+1 from version where module_type = 0) "+
			" 	from lookup l "+ 
			"	where l.id in (   ";
	
	static final String UPDATE_HIGH_RISK_BY_GROUP =
			"update l "+ 
			"	set l.is_high_risk = 0, l.version = (select version+1 from version where module_type = 0) "+
			" 	from lookup l "+ 
			"	where l.LOOKUP_GROUP = ?   ";
	
	public void setNoHighRiskByGroupName(String lookupGroup) throws SystemException{
		StringBuffer queryString = new StringBuffer(UPDATE_HIGH_RISK_BY_GROUP);		
		Query query = getSession().createSQLQuery(queryString.toString()).
			setParameter(0, lookupGroup);
		query.executeUpdate();
	}
	
	public void updateHighRisk(Long[] ids) throws SystemException{
		StringBuffer queryString = new StringBuffer(UPDATE_HIGH_RISK);
		StringBuffer whereString = new StringBuffer();
		for(Long id : ids){
			whereString.append(id).append(",");
		}
		if(whereString.length() > 1)
			whereString.deleteCharAt(whereString.length()-1);
		else
			whereString.append(0);
		queryString.append(whereString).append(" )");
		Query query = getSession().createSQLQuery(queryString.toString());
		
		query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<Lookup> findLookupByLookupGroup(String lookupGroup)
			throws SystemException {
		List<Lookup> lookups = new ArrayList<Lookup>();
		
		Criteria c = getSession().createCriteria(Lookup.class);
		c.add(Restrictions.eq(Lookup.LOOKUP_GROUP_STRING, lookupGroup));
		c.add(Restrictions.eq(Lookup.STATUS, MasterDataStatus.ACTIVE));
		c.addOrder(Order.asc(Lookup.ID));// TODO
		c.setCacheable(true);
		c.setCacheRegion("query.findLookupByLookupGroup");
		
		lookups = c.list();
		
        if(lookups.size() > 0)
    	   return lookups ;
        else
        	return new LinkedList<Lookup>();
	}
	
	@SuppressWarnings("unchecked")
	public List<Lookup> findAllLookupByLookupGroup(String lookupGroup)
			throws SystemException {
		List<Lookup> lookups = new ArrayList<Lookup>();
		
		Criteria c = getSession().createCriteria(Lookup.class);
		c.add(Restrictions.eq(Lookup.LOOKUP_GROUP_STRING, lookupGroup));
		c.add(Restrictions.eq(Lookup.STATUS, MasterDataStatus.ACTIVE));
		c.addOrder(Order.asc(Lookup.ORDER_NO_STRING));
		c.setCacheable(true);
		c.setCacheRegion("query.findLookupByLookupGroup");
		
		lookups = c.list();
		
        if(lookups.size() > 0)
    	   return lookups ;
        else
        	return new LinkedList<Lookup>();
	}
	
	@SuppressWarnings("unchecked")
	public List<Lookup> findLookupByLookupGroupNotCode(String lookupGroup, String[] notCode)
			throws SystemException {
		List<Lookup> lookups = new ArrayList<Lookup>();
		
		Criteria c = getSession().createCriteria(Lookup.class);
		c.add(Restrictions.eq(Lookup.LOOKUP_GROUP_STRING, lookupGroup));
		c.add(Restrictions.eq(Lookup.STATUS, MasterDataStatus.ACTIVE));
		for(String code : notCode){
			c.add(Restrictions.ne(Lookup.CODE_STRING, code));
		}
		c.addOrder(Order.asc(Lookup.ORDER_NO_STRING));
		c.setCacheable(true);
		c.setCacheRegion("query.findLookupByLookupGroup");
		
		lookups = c.list();
		
        if(lookups.size() > 0)
    	   return lookups ;
        else
        	return new LinkedList<Lookup>();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Lookup> findLookupByLookupGroupOrderBy(String lookupGroup, String orderBy)
	throws SystemException {
		List<Lookup> lookups = new ArrayList<Lookup>();
		
		Criteria c = getSession().createCriteria(Lookup.class);
		c.add(Restrictions.eq(Lookup.LOOKUP_GROUP_STRING, lookupGroup));
		c.add(Restrictions.eq(Lookup.STATUS, MasterDataStatus.ACTIVE));
		c.addOrder(Order.asc(orderBy));
		c.setCacheable(true);
		c.setCacheRegion("query.findLookupByLookupGroup");
		
		lookups = c.list();
		
		if(lookups.size() > 0)
		   return lookups ;
		else
			return new LinkedList<Lookup>();
	}
	
	public Lookup findById(Long lookupId) throws SystemException {
		return super.findByPK(lookupId);
	}

	public void saveOrUpdate(Lookup lookup) throws SystemException {
		if(lookup.getId()==null)
			super.create(lookup);
		else
		    super.update(lookup);
	}

	public Lookup findByCode(String code, String lookupGroup) {
		Criteria codeCriteria = getSession().createCriteria(Lookup.class);
		codeCriteria.add(Restrictions.eq(Lookup.CODE_STRING, code));
		codeCriteria.add(Restrictions.eq(Lookup.LOOKUP_GROUP_STRING, lookupGroup));
		return (Lookup) codeCriteria.uniqueResult();
	}
	
	public PagingWrapper<Lookup> findAllLookupWithFilter(int startNo,
			int offset, List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		String[] fetchedProperties = new String[] {Lookup.ID, Lookup.CODE_STRING, Lookup.NAME_STRING, Lookup.DESCRIPTION, 
			Lookup.IS_ALTERNATE_ENTRY,Lookup.LOOKUP_GROUP_STRING, Lookup.STATUS, Lookup.APPROVAL_STATUS, Lookup.LATEST_SUGGESTION,
			Lookup.LATEST_SUGGESTOR, Lookup.LATEST_APPROVAL, Lookup.LATEST_APPROVER, Lookup.IS_HIGH_RISK,Lookup.IS_EDIT_TABLE};

		return super.findFetchedPropertyWithPagingWrapper(startNo, offset, null, fetchedProperties, searchFilters, searchOrders, null, true);
	}

	@Override
	public List<Lookup> findAll(List<SearchFilter> filters,
			List<SearchOrder> order) throws SystemException {
		return super.findAll(filters, null, null);
	}
	
	@Override
	public void delete(Long[] ids) throws SystemException {
		List<Lookup> objectList = new ArrayList<Lookup>();
		for(int i=0;i<ids.length;i++){
			Lookup object = new Lookup();
			object = findById(ids[i]);
			objectList.add(object);
		}
		super.delete(objectList);
	}
	
	@Override
	public Lookup findByName(String name, String lookupGroup) {
		Criteria c = getSession().createCriteria(Lookup.class);
		c.add(Restrictions.eq(Lookup.LOOKUP_GROUP_STRING, lookupGroup));
		c.add(Restrictions.eq(Lookup.NAME_STRING, name));
		return (Lookup) c.uniqueResult();
	}

	@Override
	public Lookup findByNameIgnoreCase(String name, String lookupGroup) {
		Criteria c = getSession().createCriteria(Lookup.class);
		c.add(Restrictions.eq(Lookup.LOOKUP_GROUP_STRING, lookupGroup));
		c.add(Restrictions.eq(Lookup.NAME_STRING, name).ignoreCase());
		return (Lookup) c.uniqueResult();
	}

	public Lookup findLookup(String code, String lookupGroup, long fkLookupParent) {
		String sql = "select a from Lookup a inner join a.lookupDetailList as b " +
				"where a.lookupGroupString=:lookupGroup and " +
				"a.code = :code and b.fkLookupParent = :fkLookupParent";
		Query query = getSession().createQuery(sql)
				.setString("code", code)
				.setString("lookupGroup", lookupGroup)
				.setLong("fkLookupParent", fkLookupParent);

		return (Lookup) query.uniqueResult();
	}
	
	@Override
	public List<Lookup> findAll(int startNo, int offset,
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
//		String[] fetchedProperties = new String[] {Lookup.ID, Lookup.CODE_STRING, Lookup.NAME_STRING, Lookup.DESCRIPTION, 
//				Lookup.LOOKUP_GROUP_STRING, Lookup.ISACTIVE, Lookup.STATUS, Lookup.APPROVAL_STATUS, Lookup.LATEST_SUGGESTION,
//				Lookup.LATEST_SUGGESTOR, Lookup.LATEST_APPROVAL, Lookup.LATEST_APPROVER};
		return super.findFetchedProperty(startNo, offset, null, null, searchFilters, searchOrders, null, false);
	}
	
	@Override
	public Boolean isCodeExist(Long id, String newCode, String lookupGroup){
		List<Criterion> criterias = new ArrayList<Criterion>();
		criterias.add(Restrictions.eq(Lookup.CODE_STRING, newCode.toLowerCase()).ignoreCase());
		criterias.add(Restrictions.eq(Lookup.LOOKUP_GROUP_STRING, lookupGroup));
		if(id!=null){
			criterias.add(Restrictions.ne(Lookup.ID, id));
		}
		return getRowCount(criterias)>0?Boolean.TRUE:Boolean.FALSE;
	}
	
	/*
	 * Only one alternate entry allowed
	 */
	public Boolean isAlternateEntryExist(Long id, Boolean isAlternateEntry, String lookupGroup){
		List<Criterion> criterias = new ArrayList<Criterion>();
		criterias.add(Restrictions.eq(Lookup.IS_ALTERNATE_ENTRY, isAlternateEntry));
		criterias.add(Restrictions.eq(Lookup.LOOKUP_GROUP_STRING, lookupGroup));
		if(id!=null){
			criterias.add(Restrictions.ne(Lookup.ID, id));
		}
		return getRowCount(criterias)>0?Boolean.TRUE:Boolean.FALSE;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lookup> findByLookupGroups(String[] lookupGroupFilters)
			throws SystemException {
		ProjectionList projectionList = Projections.projectionList();
		for (String fetchedProperty : Lookup.MAINTENANCE_DETAIL_FIELDS) {
			projectionList.add(Projections.property(fetchedProperty));
		}
		
		List<Lookup> lookups = new ArrayList<Lookup>();
		
		Criteria c = getSession().createCriteria(Lookup.class);
		c.add(Restrictions.in(Lookup.LOOKUP_GROUP_STRING, lookupGroupFilters));
		c.add(Restrictions.eq(Lookup.STATUS, MasterDataStatus.ACTIVE));
		c.setProjection(projectionList);
		c.addOrder(Order.asc(Lookup.LOOKUP_GROUP_STRING));
		c.addOrder(Order.asc(Lookup.DESCRIPTION));
		c.setResultTransformer(new DynamicResultTransformer<Lookup>(Lookup.class, Lookup.MAINTENANCE_DETAIL_FIELDS));
		
		lookups = c.list();
		
        if(lookups.size() > 0)
    	   return lookups ;
        else
        	return new LinkedList<Lookup>();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Lookup> findGreaterVersion(int version)
			throws SystemException {
		ProjectionList projectionList = Projections.projectionList();
		for (String fetchedProperty : Lookup.MAINTENANCE_DETAIL_FIELDS) {
			projectionList.add(Projections.property(fetchedProperty));
		}
		
		List<Lookup> lookups = new ArrayList<Lookup>();
		
		Criteria c = getSession().createCriteria(Lookup.class);
		c.add(Restrictions.gt(Lookup.VERSION, version));
//		c.add(Restrictions.eq(Lookup.STATUS, MasterDataStatus.ACTIVE));
		c.setProjection(projectionList);
		c.addOrder(Order.asc(Lookup.VERSION));
		c.setResultTransformer(new DynamicResultTransformer<Lookup>(Lookup.class, Lookup.MAINTENANCE_DETAIL_FIELDS));
		
		lookups = c.list();
		
        if(lookups.size() > 0)
        	return lookups ;
        else
        	return new LinkedList<Lookup>();
	}
	
	@SuppressWarnings("unchecked")
	public Map<Long, String> getNameFromLookupByLookupGroupOrderBy(String lookupGroup, String orderBy)
	throws SystemException {
		
		List<String> ls = new ArrayList<String>();
		
		Criteria c = getSession().createCriteria(Lookup.class);
		c.setProjection(Projections.distinct(Projections.property(Lookup.NAME_STRING)));
		c.add(Restrictions.eq(Lookup.LOOKUP_GROUP_STRING, lookupGroup));
		c.add(Restrictions.eq(Lookup.STATUS, MasterDataStatus.ACTIVE));
		c.addOrder(Order.asc(orderBy));
		c.setCacheable(true);
		c.setCacheRegion("query.findLookupByLookupGroup");
		
		ls = c.list();
		
		Map<Long, String> nameMap = new LinkedHashMap<Long, String>();
		
		Iterator<String> iterator = ls.iterator();
		Long i = 0l;
		while (iterator.hasNext()) {
			String obj = (String) iterator.next();
			nameMap.put(i, obj);
			i++;
		}
		
		return nameMap;	
	}
	
}
