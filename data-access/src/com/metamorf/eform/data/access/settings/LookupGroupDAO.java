package com.metamorf.eform.data.access.settings;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.exception.ErrorHolder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.data.access.core.AbstractHibernate4DAO;
import com.metamorf.eform.entity.settings.LookupGroup;

public class LookupGroupDAO extends AbstractHibernate4DAO<LookupGroup,Long> implements ILookupGroupDAO {

	public PagingWrapper<LookupGroup> findAllLookupGroup(int startNo, int offset)
			throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset);
	}

	@Override
	public PagingWrapper<LookupGroup> findAllLookupGroup(int startNo,
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

	@Override
	public List<LookupGroup> findAll(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return super.findFetchedProperty(startNo, offset, null, null, filter, order, null, false);
	}
	
	@Override
	public PagingWrapper<LookupGroup> findAllLookupGroupWithFilter(int startNo,
			int offset, List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		String[] fetchedProperties = new String[] {LookupGroup.NAME, LookupGroup.DESCR, LookupGroup.IS_UPDATABLE, LookupGroup.IS_VIEWABLE, LookupGroup.LOB};

		return super.findFetchedPropertyWithPagingWrapper(startNo, offset, null, fetchedProperties, searchFilters, searchOrders, null, false);
	}
	
	@Override
	public LookupGroup findByName(String name) throws SystemException{
		LookupGroup lookupGroup = null;
		try{
			lookupGroup = (LookupGroup) getSession().createCriteria(LookupGroup.class)
					.add(Restrictions.eq(LookupGroup.NAME, name))
					.uniqueResult();
		}catch(HibernateException e){
			throw new SystemException(new ErrorHolder("error.lookupGroup.not.found"));
		}
		
		return lookupGroup;
	}
}