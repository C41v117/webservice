package com.metamorf.eform.data.access.master;

import java.util.List;

import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.entity.settings.ApprovalReason;

public interface IApprovalReasonDAO {
	
	public abstract void delete(Long[] objectPKs) throws SystemException;
	
	public abstract PagingWrapper<ApprovalReason> findAllApprovalReasonWithFilter(int startNo,
			int offset,List<SearchFilter> filter,List<SearchOrder> order) throws SystemException;

	public abstract ApprovalReason findApprovalReasonById(Long id)
			throws SystemException;
	
	public abstract List<ApprovalReason> findAll() throws SystemException;
	
	public abstract List<ApprovalReason> findAll(int startNo,
			int offset,List<SearchFilter> filter,List<SearchOrder> order)
			throws SystemException;

	public abstract void saveOrUpdate(ApprovalReason anObject)
			throws SystemException;
}