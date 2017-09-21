package com.metamorf.eform.service.master;

import java.util.List;

import org.springframework.stereotype.Service;

import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.enumer.ModuleType;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.data.access.master.IVersionDAO;
import com.metamorf.eform.entity.master.Version;
import com.metamorf.eform.interfaces.master.IVersionService;

@Service
public class VersionService implements IVersionService {
	
	IVersionDAO versionDAO;
	
	public VersionService(){}
	
	public VersionService(IVersionDAO versionDAO){
		this.versionDAO = versionDAO;
	}

	public void setVersionDAO(IVersionDAO versionDAO) {
		this.versionDAO = versionDAO;
	}

	@Override
	public List<Version> findAll(int startNo, int offset, List<SearchFilter> searchFilters, List<SearchOrder> searchOrders) throws SystemException {
		return versionDAO.findAll(startNo, offset, searchFilters, searchOrders);
	}

	@Override
	public Version findById(Long id) throws SystemException {
		return versionDAO.findVersionById(id);
	}

	@Override
	public void saveOrUpdate(Version anObject) throws SystemException {
		versionDAO.saveOrUpdate(anObject);
	
	}

	@Override
	public void delete(Version anObject) throws SystemException {
		// DO NOT WRITE ANY CODE HERE SINCE APPPARAMETER IS NOT SUPPOSED TO BE DELETE
		
	}

	@Override
	public List<Version> findAll() throws SystemException {
		return versionDAO.findAll();
	}

	@Override
	public PagingWrapper<Version> findAllWithPagingWrapper(int startNo,
			int offset, List<SearchFilter> searchFilters,
			List<SearchOrder> searchOrders) throws SystemException {
		return versionDAO.findAllVersionWithFilter(startNo, offset, searchFilters, searchOrders);
	}

	@Override
	public List<Version> findReportCollection(
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Version> findReportCollection(int startNo, int offset,
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUpdateAvailable(ModuleType moduleType, int version)
			throws SystemException {
		return versionDAO.isUpdateAvailable(moduleType, version);
	}

	@Override
	public Version findByModuleType(ModuleType moduleType)
			throws SystemException {
		return versionDAO.findVersionByModule(moduleType);
	}
}
