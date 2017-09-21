package com.metamorf.eform.service.validator;

import java.util.ArrayList;
import java.util.List;

import com.metamorf.eform.common.core.ILookupConstant;
import com.metamorf.eform.common.core.SystemConstant;
import com.metamorf.eform.common.exception.ErrorHolder;
import com.metamorf.eform.data.access.mytask.IMasterDataApprovalDAO;
import com.metamorf.eform.data.access.security.IRoleDAO;
import com.metamorf.eform.data.access.settings.ILookupDAO;
import com.metamorf.eform.data.access.user.IUserDAO;
import com.metamorf.eform.entity.mytask.MasterDataApproval;
import com.metamorf.eform.entity.settings.AppParameter;
import com.metamorf.eform.entity.settings.Lookup;
import com.metamorf.eform.entity.user.Role;
import com.metamorf.eform.entity.user.User;

public class MasterDataApprovalValidator extends Validator<MasterDataApproval> {

	private IUserDAO userDAO;
	private ILookupDAO lookupDAO;
	private IRoleDAO roleDAO;
	private IMasterDataApprovalDAO masterDataApprovalDAO;

	@Override
	public List<ErrorHolder> validate(MasterDataApproval approval) {
		return validateAll(approval,null);
	}
	
	@Override
	public List<ErrorHolder> validate(MasterDataApproval anObject, Object... arguments) {
		return validateAll(anObject,arguments);
	}
	
	private List<ErrorHolder> validateAll(MasterDataApproval approval, Object... arguments){
		List<ErrorHolder> errorHolders = new ArrayList<ErrorHolder>();
		Object oldObject = approval.getApprovalData().get(SystemConstant.ApprovalData.OLD_OBJECT);
		Object newObject = approval.getApprovalData().get(SystemConstant.ApprovalData.NEW_OBJECT);
		
		if(null!=newObject){
			if(newObject instanceof AppParameter){
				AppParameter appParameter = (AppParameter) newObject; 
				AppParameterValidator validator = new AppParameterValidator();
				validator.setResourceBundle(this.resourceBundle);
				
				if(null!=arguments){
					// for validate using arguments
				}else{
					errorHolders.addAll(validator.validate(appParameter));
				}
			}else if(newObject instanceof Lookup){
				Lookup lookup = (Lookup) newObject;
				LookupValidator validator = new LookupValidator();
				validator.setLookupDAO(lookupDAO);
				validator.setMasterDataApprovalDAO(masterDataApprovalDAO);
				validator.setResourceBundle(this.resourceBundle);
				
				if(null!=arguments){
					// for validate using arguments
				}else{
					errorHolders.addAll(validator.validate(lookup));
				}
			}else if(newObject instanceof Role){
				Role role = (Role) newObject;
				RoleValidator validator = new RoleValidator();
				validator.setRoleDAO(roleDAO);
				validator.setMasterDataApprovalDAO(masterDataApprovalDAO);
				validator.setResourceBundle(this.resourceBundle);
				
				if(null!=arguments){
					errorHolders.addAll(validator.validate(role, arguments));
				}else{
					errorHolders.addAll(validator.validate(role));
				}
			}else if(newObject instanceof User){
				User user = (User) newObject; 
				UserValidator validator = new UserValidator();
				validator.setUserDAO(userDAO);
				validator.setMasterDataApprovalDAO(masterDataApprovalDAO);
				validator.setResourceBundle(this.resourceBundle);
				
				if(null!=arguments){
					errorHolders.addAll(validator.validate(user, arguments));
				}else{
					if(ILookupConstant.MasterDataModule.BUS.equals(approval.getModuleId())){
						errorHolders.addAll(validator.validate(user, new Object[]{"bankUser", approval.getAction()==SystemConstant.MakerAction.ADD?"add":"edit"}));
					}else{
						errorHolders.addAll(validator.validate(user));
					}
				}
			}
			
		}else if (null!=oldObject) {
			if (oldObject instanceof Lookup) {
				Lookup curLookup = (Lookup) oldObject;
				LookupValidator validator = new LookupValidator();
				validator.setLookupDAO(lookupDAO);
				validator.setMasterDataApprovalDAO(masterDataApprovalDAO);
				validator.setResourceBundle(this.resourceBundle);
				
				if(null!=arguments){
					// for validate using arguments
				}else{
					errorHolders.addAll(validator.validate(curLookup));
				}
			}else if(oldObject instanceof Role){
				Role role = (Role) oldObject;
				RoleValidator validator = new RoleValidator();
				validator.setRoleDAO(roleDAO);
				validator.setMasterDataApprovalDAO(masterDataApprovalDAO);
				validator.setResourceBundle(this.resourceBundle);
				
				if(null!=arguments){
					errorHolders.addAll(validator.validate(role, arguments));
				}else{
					errorHolders.addAll(validator.validate(role));
				}
			}
		}
		
		
		return errorHolders;
	}
	
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public void setLookupDAO(ILookupDAO lookupDAO) {
		this.lookupDAO = lookupDAO;
	}
	
	public void setRoleDAO(IRoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public void setMasterDataApprovalDAO(
			IMasterDataApprovalDAO masterDataApprovalDAO) {
		this.masterDataApprovalDAO = masterDataApprovalDAO;
	}
	
}