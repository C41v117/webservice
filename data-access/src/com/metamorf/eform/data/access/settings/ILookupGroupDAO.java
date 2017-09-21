package com.metamorf.eform.data.access.settings;

import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.entity.settings.LookupGroup;

import java.util.List;

public interface ILookupGroupDAO {
	
	public PagingWrapper<LookupGroup> findAllLookupGroup(
			int startNo, int offset) throws SystemException ;

	PagingWrapper<LookupGroup> findAllLookupGroup(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException;
	
	List<LookupGroup> findAll() throws SystemException;
	public List<LookupGroup> findAll(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException;

	PagingWrapper<LookupGroup> findAllLookupGroupWithFilter(int startNo,
			int offset, List<SearchFilter> searchFilters,
			List<SearchOrder> searchOrders) throws SystemException;
	
	public LookupGroup findByName(String name) throws SystemException;
}
