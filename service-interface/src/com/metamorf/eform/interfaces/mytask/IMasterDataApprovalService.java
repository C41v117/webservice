package com.metamorf.eform.interfaces.mytask;

import java.util.List;

import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.entity.mytask.MasterDataApproval;
import com.metamorf.eform.interfaces.core.ISimpleApprovalService;

public interface IMasterDataApprovalService extends ISimpleApprovalService<MasterDataApproval>{

	public abstract void repairing(MasterDataApproval anObject) throws SystemException;

	public abstract void requestRepair(MasterDataApproval anObject) throws SystemException;

	public abstract void reject(MasterDataApproval anObject) throws SystemException;

	public abstract void approve(MasterDataApproval anObject) throws SystemException;

	public List<MasterDataApproval> findAll(List<SearchFilter> searchFilters, List<SearchOrder> searchOrders) throws SystemException;

	public abstract Long countPendingTask(List<SearchFilter> searchFilters)
			throws SystemException;
	
	public Boolean isAvailableInApproval(String moduleId, String masterDataId, String name) throws SystemException;
	
	public void saveOrUpdate(List<MasterDataApproval> anObjects);
	
	public void approve(List<MasterDataApproval> objects) throws SystemException;
}