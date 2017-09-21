package com.metamorf.eform.service.settings;

import java.util.List;

import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.data.access.settings.ILookupDAO;
import com.metamorf.eform.entity.settings.Lookup;
import com.metamorf.eform.interfaces.core.IBaseService;

public class LookupHandler implements IBaseService<Lookup> {

	protected  ILookupDAO lookupDAO;
    
    public LookupHandler(){};
    
    public LookupHandler(ILookupDAO lookupDAO){
    	this.lookupDAO = lookupDAO;
    }
	
	public PagingWrapper<Lookup> findAll(int startNo, int offset)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	public Lookup findById(Long id) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveOrUpdate(Lookup anObject) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	public void delete(Long[] objectPKs) throws SystemException {
		// TODO Auto-generated method stub
		
	}
	
	public List<Lookup> findByLookupGroup(String lookupGroup) throws SystemException{
		return lookupDAO.findLookupByLookupGroup(lookupGroup);
		
	}

	public PagingWrapper<Lookup> findAllByFilter(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return lookupDAO.findAllLookupWithFilter(startNo, offset, filter, order);
	}

	@Override
	public List<Lookup> findAll(int startNo, int offset,
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Lookup anObject) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PagingWrapper<Lookup> findAllWithPagingWrapper(int startNo,
			int offset, List<SearchFilter> searchFilters,
			List<SearchOrder> searchOrders) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Lookup> findReportCollection(List<SearchFilter> searchFilters,
			List<SearchOrder> searchOrders) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Lookup> findReportCollection(int startNo, int offset,
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}
}
