package com.metamorf.eform.interfaces.audit;

import java.util.List;
import java.util.Map;

import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.entity.audit.LogAuditTrail;
import com.metamorf.eform.interfaces.core.IBaseService;

public interface IAuditTrailService extends IBaseService<LogAuditTrail>{
	
	public Map<Long, String> getNameFromLookupByLookupGroupOrderBy(String lookupGroup, String orderBy) throws SystemException;

	public void saveListAuditTrail(List<LogAuditTrail> objects) throws SystemException;

}