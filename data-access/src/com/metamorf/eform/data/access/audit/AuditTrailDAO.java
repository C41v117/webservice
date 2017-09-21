package com.metamorf.eform.data.access.audit;

import java.util.List;

import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.data.access.core.AbstractHibernate4DAO;
import com.metamorf.eform.entity.audit.LogAuditTrail;

public class AuditTrailDAO extends AbstractHibernate4DAO<LogAuditTrail, Long> implements IAuditTrailDAO{

	@Override
	public void saveOrUpdate(LogAuditTrail anObject) throws SystemException {
		super.create(anObject);
	}

	@Override
	public LogAuditTrail findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public List<LogAuditTrail> findAll(int startNo, int offset,
			List<SearchFilter> filters, List<SearchOrder> orders)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<LogAuditTrail> findAll(List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		// TODO Auto-generated method stub
		return super.findFetchedPropertyList(null, LogAuditTrail.MAINTENANCE_LIST_FIELDS, searchFilters, searchOrders, null);
	}

	@Override
	public PagingWrapper<LogAuditTrail> findAllByFilter(int startNo,
			int offset, List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		return super.findFetchedPropertyWithPagingWrapper(startNo, offset, null, LogAuditTrail.MAINTENANCE_LIST_FIELDS, searchFilters, searchOrders, null, false);
	}

	@Override
	public void deleteObject(LogAuditTrail anObject) throws SystemException {
		// TODO Auto-generated method stub
		
	}

}
