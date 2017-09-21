package com.metamorf.eform.data.access.audit;

import java.util.List;

import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.data.access.core.IBaseDAO;
import com.metamorf.eform.entity.audit.LogAuditTrail;

public interface IAuditTrailDAO extends IBaseDAO<LogAuditTrail>{
	
	public List<LogAuditTrail> findAll(List<SearchFilter> searchFilters, List<SearchOrder> searchOrders) throws SystemException;

}