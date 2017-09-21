package com.metamorf.eform.data.access.settings;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Projections;

import com.metamorf.eform.common.core.SystemConstant;
import com.metamorf.eform.common.core.SystemParameter;
import com.metamorf.eform.common.data.util.Operator;
import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.data.access.core.AbstractHibernate4DAO;
import com.metamorf.eform.entity.settings.AppParameter;

public class AppParameterDAO extends AbstractHibernate4DAO<AppParameter,Long> implements IAppParameterDAO {
	
	final static String DISB_SEQUENCE_NO = "DISB_SEQUENCE_NO";
	final static String LOAN_SEQUENCE_NO = "LOAN_SEQUENCE_NO";
	final static String INV_SEQUENCE_NO  = "INV_SEQUENCE_NO";
	
	public void delete(Long[] objectPKs) throws SystemException {
		// TODO Auto-generated method stub
	}
	
	public List<AppParameter> findAll(){
		return super.findAll();
	}
	
	@Override
	public List<AppParameter> findAllCron(){
		List<SearchFilter> searchFilters = new ArrayList<SearchFilter>();
		searchFilters.add(new SearchFilter(AppParameter.NAME, Operator.LIKE, SystemConstant.CRON_EXP_FLAG));
		return super.findAll(searchFilters, new ArrayList<SearchOrder>(), null);
	}
	

	public AppParameter findAppParameterById(Long id) throws SystemException {
		return super.findByPK(id);
	}
	
	public AppParameter findAppParameterByName(String name) throws SystemException{
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		filters.add(new SearchFilter(AppParameter.NAME, Operator.EQUALS, name));
		List<AppParameter> result = super.findAll(filters, null, null);
		
		if (result.size() > 0) return result.get(0);
		return null;
	}

	public void saveOrUpdate(AppParameter anObject) throws SystemException {
		//since AppParameter will not be created from Front End, so there is no need for insert
		 /*if(anObject.getParameterPK()==null)
			 super.create(anObject);
	     else {*/
	    	 super.update(anObject);
	    	 SystemParameter.updateSystemEnvironment(anObject.getName(), anObject.getValue());
	    	
	    
	     /*}*/
	}
	
	/*use only for unit testing*/
	public void saveOrUpdateForUnitTesting(AppParameter anObject) throws SystemException {
		super.update(anObject);
	}

	public PagingWrapper<AppParameter> findAllParameterWithFilter(int startNo,
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return super.findFetchedPropertyWithPagingWrapper(startNo, offset, null, AppParameter.MAINTENANCE_LIST_FIELDS, filter, order, null, false);
		/*return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);*/
	}

	@Override
	public List<AppParameter> findAll(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return super.findFetchedProperty(startNo, offset, null, AppParameter.MAINTENANCE_LIST_FIELDS, filter, order, null, false);
	}

	public String generatePaymentDisbSeqNo() throws SystemException {
		return populateDisbSequenceNo();
	};

	private synchronized String populateDisbSequenceNo() throws SystemException {
		AppParameter parameter = findAppParameterByName(DISB_SEQUENCE_NO);
		parameter.setValue(SystemParameter.generatedDisbSeqNo());
		saveOrUpdate(parameter);
		return parameter.getValue();
	}
	
	public String generatePaymentLoanSeqNo() throws SystemException {
		return populateLoanSequenceNo();
	};

	private synchronized String populateLoanSequenceNo() throws SystemException {
		AppParameter parameter = findAppParameterByName(LOAN_SEQUENCE_NO);
		parameter.setValue(SystemParameter.generateLoanSeqNo());
		saveOrUpdate(parameter);
		return parameter.getValue();
	}	
	
	public String generateInvoiceSeqNo() throws SystemException {
		return populateInvSequenceNo();
	};

	private synchronized String populateInvSequenceNo() throws SystemException {
		AppParameter parameter = findAppParameterByName(INV_SEQUENCE_NO);
		parameter.setValue(SystemParameter.generateInvSeqNo());
		saveOrUpdate(parameter);
		return parameter.getValue();
	}	
	
	@Override
	public void create(AppParameter appParameter){
		super.create(appParameter);
	}
	
	@Override
	public Long getMaxId() {
		return (Long) getSession().createCriteria(AppParameter.class)
		.setProjection(Projections.max(AppParameter.ID)).uniqueResult();
	}
	
}
