package com.metamorf.eform.data.access.user;

import java.util.List;

import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.data.access.core.AbstractHibernate4DAO;
import com.metamorf.eform.entity.user.SendEmail;

public class SendEmailDAO extends AbstractHibernate4DAO<SendEmail, Long> implements ISendEmailDAO {
	
	@Override
	public void saveOrUpdate(SendEmail sendEmail) {
		if(sendEmail.getId()==null) {
			super.create(sendEmail);
		} else {
			super.update(sendEmail);
		}
	}
	
	public List<SendEmail> findAll(){
		return super.findAll();
	}

	@Override
	public List<SendEmail> findAll(int startNo, int offset,List<SearchFilter> filter, List<SearchOrder> order)throws SystemException {
		return super.findFetchedProperty(startNo, offset, null, SendEmail.MAINTENANCE_LIST_FIELDS, filter, order, null, false);
	}
	
	@Override
	public PagingWrapper<SendEmail> findAllByFilter(int startNo, int offset,
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		return super.findFetchedPropertyWithPagingWrapper(startNo, offset, null, null, searchFilters, searchOrders, null, false);
	}

	@Override
	public void deleteObject(SendEmail anObject) throws SystemException {
		super.delete(anObject);
	}

	@Override
	public SendEmail findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

}