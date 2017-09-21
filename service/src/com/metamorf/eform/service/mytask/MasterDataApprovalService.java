package com.metamorf.eform.service.mytask;

import java.util.Date;
import java.util.List;

import org.jasypt.digest.StringDigester;

import com.metamorf.eform.common.core.ILookupConstant;
import com.metamorf.eform.common.core.IUserConstant;
import com.metamorf.eform.common.core.SystemConstant;
import com.metamorf.eform.common.core.SystemParameter;
import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.enumer.ActionType;
import com.metamorf.eform.common.enumer.ModuleType;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.data.access.master.IVersionDAO;
import com.metamorf.eform.data.access.mytask.IMasterDataApprovalDAO;
import com.metamorf.eform.data.access.security.IRoleDAO;
import com.metamorf.eform.data.access.settings.IAppParameterDAO;
import com.metamorf.eform.data.access.settings.ILookupDAO;
import com.metamorf.eform.data.access.user.IUserDAO;
import com.metamorf.eform.entity.core.SimpleApprovalObject;
import com.metamorf.eform.entity.master.Version;
import com.metamorf.eform.entity.mytask.MasterDataApproval;
import com.metamorf.eform.entity.settings.AppParameter;
import com.metamorf.eform.entity.settings.Lookup;
import com.metamorf.eform.entity.user.Role;
import com.metamorf.eform.entity.user.User;
import com.metamorf.eform.interfaces.mytask.IMasterDataApprovalService;
import com.metamorf.eform.interfaces.security.IAppRoleFunctionService;
import com.metamorf.eform.interfaces.settings.IAppFunctionService;
import com.metamorf.eform.interfaces.settings.ILookupService;
import com.metamorf.eform.interfaces.user.IUserService;


public class MasterDataApprovalService implements IMasterDataApprovalService {
	
	private IMasterDataApprovalDAO masterDataApprovalDAO;
	private IAppParameterDAO appParameterDAO;
	private IAppRoleFunctionService appRoleFunctionService;
	private ILookupService lookupService;
	private IUserService userService;
	private IRoleDAO roleDAO;
	private ILookupDAO lookupDAO;
	private IUserDAO userDAO;
	private StringDigester digester;
	private IVersionDAO versionDAO;

	public void setVersionDAO(IVersionDAO versionDAO) {
		this.versionDAO = versionDAO;
	}

	public void setMasterDataApprovalDAO(
			IMasterDataApprovalDAO masterDataApprovalDAO) {
		this.masterDataApprovalDAO = masterDataApprovalDAO;
	}

	public void setAppParameterDAO(IAppParameterDAO appParameterDAO) {
		this.appParameterDAO = appParameterDAO;
	}

	public void setAppRoleFunctionService(
			IAppRoleFunctionService appRoleFunctionService) {
		this.appRoleFunctionService = appRoleFunctionService;
	}

	public void setLookupService(ILookupService lookupService) {
		this.lookupService = lookupService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public void setAppFunctionService(IAppFunctionService appFunctionService) {
	}

	public void setRoleDAO(IRoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public void setLookupDAO(ILookupDAO lookupDAO) {
		this.lookupDAO = lookupDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public void setDigester(StringDigester digester) {
		this.digester = digester;
	}

	@Override
	public List<MasterDataApproval> findAll(int startNo, int offset,
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		return masterDataApprovalDAO.findAll(startNo, offset, searchFilters, searchOrders);
	}

	public List<MasterDataApproval> findAll(List<SearchFilter> searchFilters, List<SearchOrder> searchOrders) throws SystemException {
		return masterDataApprovalDAO.findAll(searchFilters, searchOrders);
	}

	@Override
	public PagingWrapper<MasterDataApproval> findAllWithPagingWrapper(int startNo, int offset,
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		return masterDataApprovalDAO.findAllByFilter(startNo, offset, searchFilters, searchOrders);
	}
	
	@Override
	public Long countPendingTask(List<SearchFilter> searchFilters) throws SystemException{
		Long counter = masterDataApprovalDAO.countPendingTask(searchFilters);
		if(counter==null){
			counter = 0L;
		}
		return counter;
	}

	@Override
	public MasterDataApproval findById(Long id) throws SystemException {
		return masterDataApprovalDAO.findById(id);
	}

	@Override
	public void saveOrUpdate(List<MasterDataApproval> anObjects){
		for(MasterDataApproval anObject : anObjects){
			saveOrUpdate(anObject);
		}
	}
	
	@Override
	public void saveOrUpdate(MasterDataApproval anObject)
			throws SystemException {
		masterDataApprovalDAO.saveOrUpdate(anObject);
		if(anObject.getAction()!=SystemConstant.MakerAction.ADD){
			if(anObject.getModuleId().equals(ILookupConstant.MasterDataModule.SYSPA)){
				appParameterDAO.saveOrUpdate((AppParameter)anObject.getApprovalData().get(SystemConstant.ApprovalData.OLD_OBJECT));
			}else if(anObject.getModuleId().equals(ILookupConstant.MasterDataModule.ROL)){
				roleDAO.saveOrUpdate((Role)anObject.getApprovalData().get(SystemConstant.ApprovalData.OLD_OBJECT));
			}else if(anObject.getModuleId().equals(ILookupConstant.MasterDataModule.SECPA)){
				AppParameter objParam=(AppParameter)anObject.getApprovalData().get(SystemConstant.ApprovalData.OLD_OBJECT);
				appParameterDAO.saveOrUpdate(objParam);
			}else if(anObject.getModuleId().equals(ILookupConstant.MasterDataModule.LOOKUP)){
				lookupDAO.saveOrUpdate((Lookup)anObject.getApprovalData().get(SystemConstant.ApprovalData.OLD_OBJECT));
			}else if(anObject.getModuleId().equals(ILookupConstant.MasterDataModule.BUS)){
				userDAO.saveOrUpdate((User)anObject.getApprovalData().get(SystemConstant.ApprovalData.OLD_OBJECT));
			}
		}
	}
	
	private void populateApprovalProperties(Object approvalObject, MasterDataApproval anObject){
		if(approvalObject instanceof SimpleApprovalObject){
			((SimpleApprovalObject) approvalObject).setLatestSuggestor(anObject.getLatestMaker());
			((SimpleApprovalObject) approvalObject).setLatestSuggestion(anObject.getLatestMakerDate());
			((SimpleApprovalObject) approvalObject).setLatestApprover(anObject.getLatestApprovalActor());
			((SimpleApprovalObject) approvalObject).setLatestApproval(anObject.getLatestApprovalDate());
		}
	}
	
	/*
	 * make method private void processYourApprovalModule like this
	 * */
	private void processSysParam(MasterDataApproval anObject, int action) throws SystemException {
		AppParameter newObject = null;
		AppParameter oldObject = null;
		AppParameter approvalObject = null;
		switch (action) {
		case SystemConstant.ApproverAction.APPROVE:
			switch (anObject.getAction().intValue()) {
			case SystemConstant.MakerAction.EDIT:
				newObject = (AppParameter)anObject.getApprovalData().get(SystemConstant.ApprovalData.NEW_OBJECT);
				newObject.setStatus(SystemConstant.MasterDataStatus.ACTIVE);
				approvalObject = newObject;
				approvalObject.setActionType(ActionType.EDIT);
				break;
			case SystemConstant.MakerAction.SUSPEND:
				oldObject = (AppParameter)anObject.getApprovalData().get(SystemConstant.ApprovalData.OLD_OBJECT);
				oldObject.setStatus(SystemConstant.MasterDataStatus.INACTIVE);
				approvalObject = oldObject;
				break;
			case SystemConstant.MakerAction.UNSUSPEND:
				oldObject = (AppParameter)anObject.getApprovalData().get(SystemConstant.ApprovalData.OLD_OBJECT);
				oldObject.setStatus(SystemConstant.MasterDataStatus.ACTIVE);
				approvalObject = oldObject;
				break;
			default:
				oldObject = (AppParameter)anObject.getApprovalData().get(SystemConstant.ApprovalData.OLD_OBJECT);
				approvalObject = oldObject;
				approvalObject.setActionType(ActionType.CREATE);
				break;
			}
			approvalObject.setApprovalStatus(SystemConstant.MasterDataApprovalStatus.APPROVED);
			populateApprovalProperties(approvalObject, anObject);
			
			Version version = versionDAO.findVersionByModule(ModuleType.SYSTEM_PARAMETER);
			version.setVersion(version.getVersion()+1);
			version.setVersionDate(new Date());
			versionDAO.saveOrUpdate(version);
			approvalObject.setVersion(version.getVersion());			
			appParameterDAO.saveOrUpdate(approvalObject);
			if(approvalObject.getName().contains(SystemConstant.CRON_EXP_FLAG)){
				//cronTriggerUpdater.updateCronTrigger(approvalObject.getName());
			}else if(approvalObject.getName().contains(SystemConstant.DATE_FORMAT_FLAG)){
				SystemParameter.updateSystemEnvironment(SystemConstant.STRUTS2_DATE_FORMAT_FLAG, "{0,date,"+approvalObject.getValue()+"}");
			}else{
				SystemParameter.updateSystemEnvironment(approvalObject.getName(), approvalObject.getValue());
			}
			break;
		case SystemConstant.ApproverAction.REJECT:
			if(anObject.getAction().intValue()!=SystemConstant.MakerAction.ADD){
				oldObject = (AppParameter)anObject.getApprovalData().get(SystemConstant.ApprovalData.OLD_OBJECT);
				approvalObject = oldObject;
				approvalObject.setApprovalStatus(SystemConstant.MasterDataApprovalStatus.APPROVED);
				populateApprovalProperties(approvalObject, anObject);
				appParameterDAO.saveOrUpdate(approvalObject);
			}
			break;
		default:
			//raise error
			break;
		}
	}

	private void processRole(MasterDataApproval anObject, int action) throws SystemException {
		Role newObject = null;
		Role oldObject = null;
		Role approvalObject = null;
		switch (action) {
		case SystemConstant.ApproverAction.APPROVE:
			switch (anObject.getAction().intValue()) {
			case SystemConstant.MakerAction.EDIT:
				newObject = (Role)anObject.getApprovalData().get(SystemConstant.ApprovalData.NEW_OBJECT);
				newObject.setStatus(SystemConstant.MasterDataStatus.ACTIVE);
				approvalObject = newObject;
				break;
			case SystemConstant.MakerAction.SUSPEND:
				oldObject = (Role)anObject.getApprovalData().get(SystemConstant.ApprovalData.OLD_OBJECT);
				oldObject.setStatus(SystemConstant.MasterDataStatus.INACTIVE);
				approvalObject = oldObject;
				break;
			case SystemConstant.MakerAction.UNSUSPEND:
				oldObject = (Role)anObject.getApprovalData().get(SystemConstant.ApprovalData.OLD_OBJECT);
				oldObject.setStatus(SystemConstant.MasterDataStatus.ACTIVE);
				approvalObject = oldObject;
				break;
			default:
				oldObject = (Role)anObject.getApprovalData().get(SystemConstant.ApprovalData.OLD_OBJECT);
				approvalObject = oldObject;
				break;
			}
			approvalObject.setApprovalStatus(SystemConstant.MasterDataApprovalStatus.APPROVED);
			populateApprovalProperties(approvalObject, anObject);
			appRoleFunctionService.updateRoleAccessbility(approvalObject, approvalObject.getAccessibilities(),SystemConstant.no);
			break;
		case SystemConstant.ApproverAction.REJECT:
			oldObject = (Role)anObject.getApprovalData().get(SystemConstant.ApprovalData.OLD_OBJECT);
			approvalObject = oldObject;
			approvalObject.setApprovalStatus(SystemConstant.MasterDataApprovalStatus.APPROVED);
			populateApprovalProperties(approvalObject, anObject);
			if (anObject.getAction().intValue() != SystemConstant.MakerAction.ADD) {
				appRoleFunctionService.updateRoleAccessbility(approvalObject, approvalObject.getAccessibilities(),SystemConstant.no);
			}
		default:
			//raise error
			break;
		}
	}
	
	private void processLookup(MasterDataApproval masterDataApproval, int action) throws SystemException {
		Lookup newObject = null;
		Lookup oldObject = null;
		Lookup approvalObject = null;
		switch (action) {
		case SystemConstant.ApproverAction.APPROVE:
			switch (masterDataApproval.getAction().intValue()) {
			case SystemConstant.MakerAction.EDIT:
				newObject = (Lookup)masterDataApproval.getApprovalData().get(SystemConstant.ApprovalData.NEW_OBJECT);
				newObject.setStatus(SystemConstant.MasterDataStatus.ACTIVE);
				approvalObject = newObject;
				approvalObject.setActionType(ActionType.EDIT);
				break;
			case SystemConstant.MakerAction.SUSPEND:
				oldObject = (Lookup)masterDataApproval.getApprovalData().get(SystemConstant.ApprovalData.OLD_OBJECT);
				oldObject.setStatus(SystemConstant.MasterDataStatus.INACTIVE);
				approvalObject = oldObject;
				break;
			case SystemConstant.MakerAction.UNSUSPEND:
				oldObject = (Lookup)masterDataApproval.getApprovalData().get(SystemConstant.ApprovalData.OLD_OBJECT);
				oldObject.setStatus(SystemConstant.MasterDataStatus.ACTIVE);
				approvalObject = oldObject;
				break;
			default:	//ADD
				oldObject = (Lookup)masterDataApproval.getApprovalData().get(SystemConstant.ApprovalData.OLD_OBJECT);
				oldObject.setStatus(SystemConstant.MasterDataStatus.ACTIVE);
				approvalObject = oldObject;
				approvalObject.setActionType(ActionType.CREATE);
				break;
			}
			Version version = versionDAO.findVersionByModule(ModuleType.LOOKUP);
			version.setVersion(version.getVersion()+1);
			version.setVersionDate(new Date());
			versionDAO.saveOrUpdate(version);
			approvalObject.setVersion(version.getVersion());	
			approvalObject.setApprovalStatus(SystemConstant.MasterDataApprovalStatus.APPROVED);
			populateApprovalProperties(approvalObject, masterDataApproval);
			lookupService.saveOrUpdate(approvalObject);
			break;
		case SystemConstant.ApproverAction.REJECT:
			if (masterDataApproval.getAction().intValue() != SystemConstant.MakerAction.ADD) {
				oldObject = (Lookup)masterDataApproval.getApprovalData().get(SystemConstant.ApprovalData.OLD_OBJECT);
				approvalObject = oldObject;
				approvalObject.setApprovalStatus(SystemConstant.MasterDataApprovalStatus.APPROVED);
				populateApprovalProperties(approvalObject, masterDataApproval);
				lookupService.saveOrUpdate(approvalObject);
			}
			break;
		default:
			//raise error
			break;
		}
	}
	
	private void processInternalUser(MasterDataApproval anObject, int action) throws SystemException {
		User newObject = null;
		User oldObject = null;
		User approvalObject = null;
		switch (action) {
		case SystemConstant.ApproverAction.APPROVE:
			switch (anObject.getAction().intValue()) {
			case SystemConstant.MakerAction.ADD:
				newObject = (User)anObject.getApprovalData().get(SystemConstant.ApprovalData.NEW_OBJECT);
				oldObject = (User)anObject.getApprovalData().get(SystemConstant.ApprovalData.OLD_OBJECT);
				approvalObject =newObject!=null?newObject:oldObject;
				break;
		
			case SystemConstant.MakerAction.EDIT:
				newObject = (User)anObject.getApprovalData().get(SystemConstant.ApprovalData.NEW_OBJECT);
				approvalObject = newObject;
				break;
			case SystemConstant.MakerAction.SUSPEND:
				oldObject = (User)anObject.getApprovalData().get(SystemConstant.ApprovalData.OLD_OBJECT);
				oldObject.setStatus(SystemConstant.MasterDataStatus.INACTIVE);
				approvalObject = oldObject;
				break;
			case SystemConstant.MakerAction.UNSUSPEND:
				oldObject = (User)anObject.getApprovalData().get(SystemConstant.ApprovalData.OLD_OBJECT);
				oldObject.setStatus(SystemConstant.MasterDataStatus.ACTIVE);
				approvalObject = oldObject;
				break;
			case SystemConstant.MakerAction.RESET_AAPL:
				newObject = (User)anObject.getApprovalData().get(SystemConstant.ApprovalData.NEW_OBJECT);
				oldObject = (User)anObject.getApprovalData().get(SystemConstant.ApprovalData.OLD_OBJECT);
				if(newObject.getIsSuper()!=SystemConstant.SUPERPOWER){
					approvalObject = newObject;
					approvalObject.setPassword(digester.digest(SystemParameter.DEFAULT_AAPL));
					approvalObject.setMustChangePassword(IUserConstant.NEW_USER);
				}
				break;
			case SystemConstant.MakerAction.LOCK:
				oldObject = (User)anObject.getApprovalData().get(SystemConstant.ApprovalData.OLD_OBJECT);
				oldObject.setLock(SystemConstant.UserLockStatus.LOCKED_BOOELAN);
				approvalObject = oldObject;
				break;
			case SystemConstant.MakerAction.UNLOCK:
				newObject = (User)anObject.getApprovalData().get(SystemConstant.ApprovalData.NEW_OBJECT);
				approvalObject = newObject;
				break;	
			case SystemConstant.MakerAction.DELETE:
				newObject = (User)anObject.getApprovalData().get(SystemConstant.ApprovalData.NEW_OBJECT);
				approvalObject = newObject;
				break;
			default:
				oldObject = (User)anObject.getApprovalData().get(SystemConstant.ApprovalData.OLD_OBJECT);
				approvalObject = oldObject;
				break;
			}
			if(approvalObject!=null){
				approvalObject.setApprovalStatus(SystemConstant.MasterDataApprovalStatus.APPROVED);
			}
			populateApprovalProperties(approvalObject, anObject);
			if(approvalObject!=null){
				Role role = roleDAO.findById(approvalObject.getRole().getId());
				approvalObject.setRole(role);
				if(approvalObject.getIsSuper()!=SystemConstant.SUPERPOWER){
					if (anObject.getAction().intValue() == SystemConstant.MakerAction.ADD) {
						//userService.saveOrUpdateWithLDAP(approvalObject);
						
						/*User tmpUser = userService.findByUserNameBODeleted(approvalObject.getUserName());
						
						if(null!=tmpUser){
							approvalObject.setId(tmpUser.getId());
							approvalObject.setDeleted(Boolean.FALSE);
						}*/
						
						userService.saveOrUpdate(approvalObject);
					} else if (anObject.getAction().intValue() == SystemConstant.MakerAction.DELETE){
						userService.saveOrUpdate(approvalObject);
					} else {
						userService.saveOrUpdate(approvalObject);
					}
				}
			}
			break;
		case SystemConstant.ApproverAction.REJECT:
			if(anObject.getAction().intValue()!=SystemConstant.MakerAction.ADD){
				oldObject = (User)anObject.getApprovalData().get(SystemConstant.ApprovalData.OLD_OBJECT);
				if(oldObject.getIsSuper()!=SystemConstant.SUPERPOWER){
					oldObject = userService.findByIdEager(oldObject.getId());
					approvalObject = oldObject;
					approvalObject.setApprovalStatus(SystemConstant.MasterDataApprovalStatus.APPROVED);
					populateApprovalProperties(approvalObject, anObject);
					userService.saveOrUpdate(approvalObject);
				}
			}
			break;
		default:
			//raise error
			break;
		}
	}
	
	private void processApproval(MasterDataApproval anObject, int action) throws SystemException {
		if(anObject.getModuleId().equals(ILookupConstant.MasterDataModule.SYSPA)){
			processSysParam(anObject, action);
		}
		else if(anObject.getModuleId().equals(ILookupConstant.MasterDataModule.SECPA)){
			processSysParam(anObject, action);
		}
		else if(anObject.getModuleId().equals(ILookupConstant.MasterDataModule.ROL)){
			processRole(anObject, action);
		}
		else if (anObject.getModuleId().equals(ILookupConstant.MasterDataModule.LOOKUP)) {
			processLookup(anObject, action);
		}
		else if(anObject.getModuleId().equals(ILookupConstant.MasterDataModule.BUS)){
			processInternalUser(anObject, action);
		}
	}

	@Override
	public void approve(List<MasterDataApproval> objects) throws SystemException {
		for(MasterDataApproval anObject : objects){
			processApproval(anObject, SystemConstant.ApproverAction.APPROVE);
			anObject.setStatus(SystemConstant.MyTaskApprovalStatus.APPROVED);
			masterDataApprovalDAO.saveOrUpdate(anObject);
		}
	}
	
	@Override
	public void approve(MasterDataApproval anObject) throws SystemException {
		processApproval(anObject, SystemConstant.ApproverAction.APPROVE);
		anObject.setStatus(SystemConstant.MyTaskApprovalStatus.APPROVED);
		masterDataApprovalDAO.saveOrUpdate(anObject);
	}
	
	@Override
	public void reject(MasterDataApproval anObject) throws SystemException {
		processApproval(anObject, SystemConstant.ApproverAction.REJECT);
		anObject.setStatus(SystemConstant.MyTaskApprovalStatus.REJECTED);
		masterDataApprovalDAO.saveOrUpdate(anObject);
	}
	
	@Override
	public void requestRepair(MasterDataApproval anObject) throws SystemException {
		anObject.setStatus(SystemConstant.MyTaskApprovalStatus.REQUEST_REPAIR);
		masterDataApprovalDAO.saveOrUpdate(anObject);
	}
	
	@Override
	public void repairing(MasterDataApproval anObject) throws SystemException {
		anObject.setStatus(SystemConstant.MyTaskApprovalStatus.PENDING);
		masterDataApprovalDAO.saveOrUpdate(anObject);
	}

	@Override
	public void delete(MasterDataApproval anObject) throws SystemException {
		
	}

	@Override
	public List<MasterDataApproval> findReportCollection(
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MasterDataApproval> findReportCollection(int startNo,
			int offset, List<SearchFilter> searchFilters,
			List<SearchOrder> searchOrders) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isAvailableInApproval(String moduleId, String masterDataId,
			String name) throws SystemException {
		return masterDataApprovalDAO.isAvailableInApproval(moduleId, masterDataId, name);
	}
}
