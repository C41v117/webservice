package com.metamorf.eform.interfaces.core;

import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.entity.settings.AppParameter;

import java.util.List;

public interface IBaseService<T> {
	public List<T> findAll(int startNo, int offset, List<SearchFilter> searchFilters, List<SearchOrder> searchOrders) throws SystemException;
	public PagingWrapper<T> findAllWithPagingWrapper(int startNo, int offset, List<SearchFilter> searchFilters, List<SearchOrder> searchOrders) throws SystemException;
	public T findById(Long id) throws SystemException;
	public void saveOrUpdate(T anObject) throws SystemException;
	public void delete(T anObject) throws SystemException;
	public List<T> findReportCollection(List<SearchFilter> searchFilters, List<SearchOrder> searchOrders) throws SystemException ;
	public List<T> findReportCollection(int startNo, int offset, List<SearchFilter> searchFilters, List<SearchOrder> searchOrders) throws SystemException ;
}
