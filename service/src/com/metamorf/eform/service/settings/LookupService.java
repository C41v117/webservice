package com.metamorf.eform.service.settings;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.metamorf.eform.common.core.SystemConstant;
import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.enumer.ActionType;
import com.metamorf.eform.common.enumer.ModuleType;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.data.access.master.IVersionDAO;
import com.metamorf.eform.data.access.settings.ILookupDAO;
import com.metamorf.eform.entity.master.Version;
import com.metamorf.eform.entity.settings.Lookup;
import com.metamorf.eform.interfaces.settings.ILookupService;

@Service
public class LookupService implements ILookupService {

	protected  ILookupDAO lookupDAO;
	protected IVersionDAO versionDAO;
	
    public void setLookupDAO(ILookupDAO lookupDAO) {
		this.lookupDAO = lookupDAO;
	}

	public void setVersionDAO(IVersionDAO versionDAO) {
		this.versionDAO = versionDAO;
	}

	public PagingWrapper<Lookup> findAll(int startNo, int offset)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	public Lookup findById(Long id) throws SystemException {
		return lookupDAO.findById(id);
	}

	public void saveOrUpdate(Lookup anObject) throws SystemException {
		if(anObject.getId() != null && anObject.getId() > 0) {
			Lookup original = findById(anObject.getId());
			List<Lookup> details = new ArrayList<Lookup>();
//			if (original != null)		
//				original.getLookupDetails();
//			anObject.setLookupDetails(details);
		}
		lookupDAO.saveOrUpdate(anObject);
	}

	public void updateHighRisk(String lookupGroup, Long[] ids) throws SystemException{
		lookupDAO.setNoHighRiskByGroupName(lookupGroup);
		lookupDAO.updateHighRisk(ids);
		Version version = versionDAO.findVersionByModule(ModuleType.LOOKUP);
		version.setVersion(version.getVersion() + 1);
		versionDAO.saveOrUpdate(version);
	}
	
	public void delete(Long[] objectPKs) throws SystemException {
		lookupDAO.delete(objectPKs);
	}
	
	public List<Lookup> findByLookupGroup(String lookupGroup) throws SystemException{
		return lookupDAO.findLookupByLookupGroup(lookupGroup);
	}
	
	public List<Lookup> findAllByLookupGroup(String lookupGroup) throws SystemException{
		return lookupDAO.findAllLookupByLookupGroup(lookupGroup);
	}
	
	public List<Lookup> findGreaterVersion(int version) throws SystemException{
		return lookupDAO.findGreaterVersion(version);
	}
	
	@Override
	public List<Lookup> findByLookupGroupOrderBy(String lookupGroup, String orderBy) throws SystemException{
		return lookupDAO.findLookupByLookupGroupOrderBy(lookupGroup, orderBy);
	}

	public PagingWrapper<Lookup> findAllByFilter(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return lookupDAO.findAllLookupWithFilter(startNo, offset, filter, order);
	}

	public Lookup findByCode(String code, String lookupGroup) {
		return lookupDAO.findByCode(code, lookupGroup);
	}
	
	public Lookup findByName(String name, String lookupGroup) {
		return lookupDAO.findByName(name, lookupGroup);
	}

	@Override
	public List<Lookup> findAll(int startNo, int offset,
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		return lookupDAO.findAll(startNo, offset, searchFilters, searchOrders);
	}

	@Override
	public void delete(Lookup anObject) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PagingWrapper<Lookup> findAllWithPagingWrapper(int startNo,
			int offset, List<SearchFilter> searchFilters,
			List<SearchOrder> searchOrders) throws SystemException {
		return lookupDAO.findAllLookupWithFilter(startNo, offset, searchFilters, searchOrders);
	}

	@Override
	public List<Lookup> findByLookupGroups(String[] lookupGroupFilters)
			throws SystemException {
		return lookupDAO.findByLookupGroups(lookupGroupFilters);
	}

	@Override
	public List<Lookup> findReportCollection(List<SearchFilter> searchFilters,
			List<SearchOrder> searchOrders) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Lookup> findReportCollection(int startNo, int offset,
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Lookup> findByLookupGroupNotCode(String lookupGroup, String[] notCode) throws SystemException{
		return lookupDAO.findLookupByLookupGroupNotCode(lookupGroup, notCode);
	}
	
	@Override
	public void saveOrUpdate(List<Lookup> lookups) throws Exception {
		for (Lookup lookup : lookups) {
			Lookup old = findByCode(lookup.getCode(), lookup.getLookupGroupString());
			if (old != null) {
				lookup.setId(old.getId());
				lookup.setActionType(ActionType.EDIT);
			}  else {
				lookup.setId(null);
				lookup.setActionType(ActionType.CREATE);
			}
			if (lookup.getStatus()==null) {
				lookup.setStatus(SystemConstant.MasterDataStatus.INACTIVE);
			}
			Version version = getNextVersion(ModuleType.LOOKUP);
			lookup.setVersion(version.getVersion());
			lookupDAO.saveOrUpdate(lookup);
			versionDAO.saveOrUpdate(version);
		}
	}
	
	private Version getNextVersion(ModuleType moduleType){
		Version version = versionDAO.findVersionByModule(moduleType);
		version.setVersion(version.getVersion()+1);
		return version;
	}

	@Override
	public List<Lookup> findLookupDetail(Long fkLookupParent)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveLookupDetail(Long lookupParentId, Lookup lookupDetail)
			throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteLookupDetail(Long parentLookupId, Long[] lookupIds)
			throws SystemException {
		// TODO Auto-generated method stub
		
	}
}
