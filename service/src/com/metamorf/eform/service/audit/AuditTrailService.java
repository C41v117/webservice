package com.metamorf.eform.service.audit;

import java.util.List;
import java.util.Map;

import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.data.access.audit.IAuditTrailDAO;
import com.metamorf.eform.data.access.settings.ILookupDAO;
import com.metamorf.eform.entity.audit.LogAuditTrail;
import com.metamorf.eform.interfaces.audit.IAuditTrailService;

public class AuditTrailService implements IAuditTrailService{
	private IAuditTrailDAO auditTrailDAO;
	private ILookupDAO lookupDAO;

	public void setLookupDAO(ILookupDAO lookupDAO) {
		this.lookupDAO = lookupDAO;
	}

	public void setAuditTrailDAO(IAuditTrailDAO auditTrailDAO) {
		this.auditTrailDAO = auditTrailDAO;
	}

	@Override
	public List<LogAuditTrail> findAll(int startNo, int offset,
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagingWrapper<LogAuditTrail> findAllWithPagingWrapper(int startNo,
			int offset, List<SearchFilter> searchFilters,
			List<SearchOrder> searchOrders) throws SystemException {
		return auditTrailDAO.findAllByFilter(startNo, offset, searchFilters, searchOrders);
	}

	@Override
	public LogAuditTrail findById(Long id) throws SystemException {
		return auditTrailDAO.findById(id);
	}

	@Override
	public void saveOrUpdate(LogAuditTrail anObject) throws SystemException {
		if(anObject!=null){
			anObject.escapeData();
			//this is not needed(it could cause NPE) because activity and description is get from IAuditTrailConstant.activityMap and IAuditTrailConstant.activityDescriptionMap
			//if you find activity in the LogAuditTrail is null, it probably because no record of the lookup of that audit trail code
			//please insert the record
			/*if (anObject.getActivity()==null) {
				Lookup lookup = lookupDAO.findByCode(anObject.getCode(), ILookupGroupConstant.LOG_GROUP);
				anObject.setActivity(lookup.getName());
			}
			if (anObject.getDescription()==null) {
				Lookup lookup = lookupDAO.findByCode(anObject.getCode(), ILookupGroupConstant.LOG_GROUP);
				anObject.setDescription(lookup.getDescription());
			}*/
			auditTrailDAO.saveOrUpdate(anObject);
		}
	}
	
	@Override
	public void saveListAuditTrail(List<LogAuditTrail> objects) throws SystemException {
		if(objects!=null){
			for (LogAuditTrail anObject : objects) {
				anObject.escapeData();
				saveOrUpdate(anObject);
			}
		}
	}

	@Override
	public void delete(LogAuditTrail anObject) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<LogAuditTrail> findReportCollection(
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		// TODO Auto-generated method stub
		/*return auditTrailDAO.findAll(searchFilters, searchOrders);*/
		return auditTrailDAO.findAllByFilter(0,0,searchFilters, searchOrders).getResult();
	}

	@Override
	public List<LogAuditTrail> findReportCollection(int startNo, int offset,
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Map<Long, String> getNameFromLookupByLookupGroupOrderBy(String lookupGroup, String orderBy)
			throws SystemException {
		return lookupDAO.getNameFromLookupByLookupGroupOrderBy(lookupGroup, orderBy);
	}

}
