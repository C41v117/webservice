package com.metamorf.eform.service.settings;

import java.util.ArrayList;
import java.util.List;

import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.data.access.settings.ILookupGroupDAO;
import com.metamorf.eform.entity.settings.LookupGroup;
import com.metamorf.eform.interfaces.settings.ILookupGroupService;

public class LookupGroupService implements ILookupGroupService {

	protected  ILookupGroupDAO lookupGroupDAO;
	
	public LookupGroupService(ILookupGroupDAO lookupGroupDAO){
		this.lookupGroupDAO = lookupGroupDAO;
	}

	public LookupGroup findById(Long id) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveOrUpdate(LookupGroup anObject) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	public void delete(Long[] objectPKs) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	public Long create(LookupGroup objectToCreate) {
		// TODO Auto-generated method stub
		return null;
	}

	public LookupGroup findByReferencesId(Long referencesPK)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	public PagingWrapper<LookupGroup> findAllByFilter(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)  // NOPMD by AGE-SYSTEM on 9/30/13 10:55 AM, no error so far
			throws SystemException {
		if (order==null) {
			order = new ArrayList<SearchOrder>();
            order.add(new SearchOrder(LookupGroup.NAME, SearchOrder.Sort.ASC));
		}
		return lookupGroupDAO.findAllLookupGroup(startNo, offset, filter, order);
	}

	@Override
	public List<LookupGroup> findAll() throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LookupGroup> findAll(int startNo, int offset,
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		return lookupGroupDAO.findAll(startNo, offset, searchFilters, searchOrders);
	}

	@Override
	public void delete(LookupGroup anObject) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PagingWrapper<LookupGroup> findAllWithPagingWrapper(int startNo,
			int offset, List<SearchFilter> searchFilters,
			List<SearchOrder> searchOrders) throws SystemException {
		return lookupGroupDAO.findAllLookupGroupWithFilter(startNo, offset, searchFilters, searchOrders);
	}

	@Override
	public List<LookupGroup> findReportCollection(
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LookupGroup> findReportCollection(int startNo, int offset,
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LookupGroup findByName(String name) throws SystemException{
		return lookupGroupDAO.findByName(name);
	}
}
