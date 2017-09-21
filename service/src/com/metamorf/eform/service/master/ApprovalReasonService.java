package com.metamorf.eform.service.master;

import java.util.List;

import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.data.access.master.IApprovalReasonDAO;
import com.metamorf.eform.entity.settings.ApprovalReason;
import com.metamorf.eform.interfaces.master.IApprovalReasonService;

public class ApprovalReasonService implements IApprovalReasonService {
	
	IApprovalReasonDAO approvalHistoryDAO;
	
	public ApprovalReasonService(){}
	
	public ApprovalReasonService(IApprovalReasonDAO approvalHistoryDAO){
		this.approvalHistoryDAO = approvalHistoryDAO;
	}

	public void setApprovalReasonDAO(IApprovalReasonDAO approvalHistoryDAO) {
		this.approvalHistoryDAO = approvalHistoryDAO;
	}

	@Override
	public List<ApprovalReason> findAll(int startNo, int offset, List<SearchFilter> searchFilters, List<SearchOrder> searchOrders) throws SystemException {
		return approvalHistoryDAO.findAll(startNo, offset, searchFilters, searchOrders);
	}

	@Override
	public ApprovalReason findById(Long id) throws SystemException {
		return approvalHistoryDAO.findApprovalReasonById(id);
	}

	@Override
	public void saveOrUpdate(ApprovalReason anObject) throws SystemException {
		approvalHistoryDAO.saveOrUpdate(anObject);
	
	}

	@Override
	public void delete(ApprovalReason anObject) throws SystemException {
		// DO NOT WRITE ANY CODE HERE SINCE APPPARAMETER IS NOT SUPPOSED TO BE DELETE
		
	}

	@Override
	public List<ApprovalReason> findAll() throws SystemException {
		return approvalHistoryDAO.findAll();
	}

	@Override
	public PagingWrapper<ApprovalReason> findAllWithPagingWrapper(int startNo,
			int offset, List<SearchFilter> searchFilters,
			List<SearchOrder> searchOrders) throws SystemException {
		return approvalHistoryDAO.findAllApprovalReasonWithFilter(startNo, offset, searchFilters, searchOrders);
	}

	@Override
	public List<ApprovalReason> findReportCollection(
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ApprovalReason> findReportCollection(int startNo, int offset,
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

}
