package com.metamorf.eform.data.access.master;

import java.util.List;

import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.data.access.core.AbstractHibernate4DAO;
import com.metamorf.eform.entity.settings.ApprovalReason;

public class ApprovalReasonDAO extends AbstractHibernate4DAO<ApprovalReason,Long> implements IApprovalReasonDAO {
	
	public void delete(Long[] objectPKs) throws SystemException {
		// TODO Auto-generated method stub
	}
	
	public List<ApprovalReason> findAll(){
		return super.findAll();
	}

	public ApprovalReason findApprovalReasonById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	public void saveOrUpdate(ApprovalReason anObject) throws SystemException {
		//since ApprovalReason will not be created from Front End, so there is no need for insert
		 /*if(anObject.getParameterPK()==null)
			 super.create(anObject);
	     else {*/
	    	 super.update(anObject);
	     /*}*/
	}
	
	/*use only for unit testing*/
	public void saveOrUpdateForUnitTesting(ApprovalReason anObject) throws SystemException {
		super.update(anObject);
	}

	public PagingWrapper<ApprovalReason> findAllApprovalReasonWithFilter(int startNo,
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return super.findFetchedPropertyWithPagingWrapper(startNo, offset, null, ApprovalReason.MAINTENANCE_LIST_FIELDS, filter, order, null, false);
		/*return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);*/
	}

	@Override
	public List<ApprovalReason> findAll(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return super.findFetchedProperty(startNo, offset, null, ApprovalReason.MAINTENANCE_LIST_FIELDS, filter, order, null, false);
	}
	
}
