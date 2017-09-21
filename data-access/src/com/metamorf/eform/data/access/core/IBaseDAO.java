package com.metamorf.eform.data.access.core;

import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;

import java.util.List;

public interface IBaseDAO<T> {

	public abstract void saveOrUpdate(T anObject) throws SystemException;

	public abstract T findById(Long id) throws SystemException;
	
	public abstract List<T> findAll(int startNo, int offset, List<SearchFilter> filters, List<SearchOrder> orders) throws SystemException;
	
	public PagingWrapper<T> findAllByFilter(int startNo, int offset, List<SearchFilter> searchFilters, List<SearchOrder> searchOrders) throws SystemException;
	
	public abstract void deleteObject(T anObject) throws SystemException;

}