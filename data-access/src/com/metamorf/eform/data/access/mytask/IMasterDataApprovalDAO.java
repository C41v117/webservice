package com.metamorf.eform.data.access.mytask;

import java.util.List;

import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.data.access.core.IBaseDAO;
import com.metamorf.eform.entity.mytask.MasterDataApproval;

public interface IMasterDataApprovalDAO extends IBaseDAO<MasterDataApproval>{
	public List<MasterDataApproval> findAll(List<SearchFilter> searchFilters, List<SearchOrder> searchOrders) throws SystemException;

	public abstract Boolean isExistByCriteria(List<SearchFilter> searchFilters)
			throws SystemException;

	public abstract Long countPendingTask(List<SearchFilter> searchFilters)
			throws SystemException;
	
	public Boolean isAvailableInApproval(String moduleId, String masterDataId, String name) throws SystemException;
}