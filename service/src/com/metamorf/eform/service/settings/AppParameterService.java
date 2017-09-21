package com.metamorf.eform.service.settings;

import java.util.List;

import org.springframework.stereotype.Service;

import com.healthmarketscience.jackcess.DataType;
import com.metamorf.eform.common.core.SystemConstant;
import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.enumer.ActionType;
import com.metamorf.eform.common.enumer.ModuleType;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.data.access.master.IVersionDAO;
import com.metamorf.eform.data.access.settings.IAppParameterDAO;
import com.metamorf.eform.entity.master.Version;
import com.metamorf.eform.entity.settings.AppParameter;
import com.metamorf.eform.interfaces.settings.IAppParameterService;

@Service
public class AppParameterService implements IAppParameterService {
	
	IAppParameterDAO appParameterDAO;
	IVersionDAO versionDAO;
	
	public void setVersionDAO(IVersionDAO versionDAO) {
		this.versionDAO = versionDAO;
	}

	public void setAppParameterDAO(IAppParameterDAO appParameterDAO) {
		this.appParameterDAO = appParameterDAO;
	}

	@Override
	public List<AppParameter> findAll(int startNo, int offset, List<SearchFilter> searchFilters, List<SearchOrder> searchOrders) throws SystemException {
		return appParameterDAO.findAll(startNo, offset, searchFilters, searchOrders);
	}

	@Override
	public AppParameter findById(Long id) throws SystemException {
		return appParameterDAO.findAppParameterById(id);
	}

	@Override
	public void saveOrUpdate(AppParameter anObject) throws SystemException {
		appParameterDAO.saveOrUpdate(anObject);
	
	}

	@Override
	public void delete(AppParameter anObject) throws SystemException {
		// DO NOT WRITE ANY CODE HERE SINCE APPPARAMETER IS NOT SUPPOSED TO BE DELETE
		
	}

	@Override
	public List<AppParameter> findAll() throws SystemException {
		return appParameterDAO.findAll();
	}
	
	@Override
	public List<AppParameter> findAllCron() throws SystemException {
		return appParameterDAO.findAllCron();
	}

	@Override
	public PagingWrapper<AppParameter> findAllWithPagingWrapper(int startNo,
			int offset, List<SearchFilter> searchFilters,
			List<SearchOrder> searchOrders) throws SystemException {
		return appParameterDAO.findAllParameterWithFilter(startNo, offset, searchFilters, searchOrders);
	}

	@Override
	public List<AppParameter> findReportCollection(
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppParameter> findReportCollection(int startNo, int offset,
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void setDefaultAppParam(AppParameter appParameter1){
		//default data
		appParameter1.setActionType(ActionType.CREATE);
		appParameter1.setDataType(SystemConstant.FIELD_TYPE_STRING);
		appParameter1.setForSecurity(false);
		appParameter1.setViewable(false);
		appParameter1.setStatus(1);
		appParameter1.setTextArea(false);
		appParameter1.setLob(SystemConstant.ProjectType.PUR);
	}
	
	@Override
	public void saveOrUpdate(List<AppParameter> appParameters) throws Exception{
		for (AppParameter appParameter : appParameters) {
			AppParameter old = appParameterDAO.findAppParameterByName(appParameter.getName());
			Version version = getNextVersion(ModuleType.SYSTEM_PARAMETER);
			appParameter.setVersion(version.getVersion());
			if (old!=null) {
				appParameter.setId(old.getId());
				appParameter.setVersion(version.getVersion());
				setDefaultAppParam(appParameter);
				appParameterDAO.saveOrUpdate(appParameter);
			}else{
//				new AppParameter(id, name, value, dataType, description, viewable, forSecurity, textArea, status, approvalStatus, latestSuggestion, latestSuggestor, latestApproval, latestApprover)
				AppParameter appParameter1 = new AppParameter();

				//from outsystem
				appParameter1.setDescription(appParameter.getDescription());
				appParameter1.setName(appParameter.getName());
				appParameter1.setValue(appParameter.getValue());

				setDefaultAppParam(appParameter1);
				
				appParameter1.setVersion(version.getVersion());
				appParameter1.setId(appParameterDAO.getMaxId()+1);
				appParameterDAO.create(appParameter1);
			}
			versionDAO.saveOrUpdate(version);
		}
		
	}
	
	private Version getNextVersion(ModuleType moduleType){
		Version version = versionDAO.findVersionByModule(moduleType);
		version.setVersion(version.getVersion()+1);
		return version;
	}

}
