package com.metamorf.eform.data.access.settings;

import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.entity.settings.AppParameter;

import java.util.List;

public interface IAppParameterDAO {
	
	public abstract void delete(Long[] objectPKs) throws SystemException;
	
	public abstract PagingWrapper<AppParameter> findAllParameterWithFilter(int startNo,
			int offset,List<SearchFilter> filter,List<SearchOrder> order) throws SystemException;

	public abstract AppParameter findAppParameterById(Long id)
			throws SystemException;
	
	public abstract List<AppParameter> findAll()
	throws SystemException;
	
	public abstract List<AppParameter> findAll(int startNo,
			int offset,List<SearchFilter> filter,List<SearchOrder> order)
			throws SystemException;

	public abstract void saveOrUpdate(AppParameter anObject)
			throws SystemException;
	
	public AppParameter findAppParameterByName(String name) throws SystemException;
	
	public abstract String generatePaymentDisbSeqNo() throws SystemException;
	
	public abstract String generatePaymentLoanSeqNo() throws SystemException;
	
	public abstract String generateInvoiceSeqNo() throws SystemException;

	public abstract List<AppParameter> findAllCron();

	void create(AppParameter appParameter);

	Long getMaxId();
}