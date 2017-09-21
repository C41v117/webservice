/**
 * 
 */
package com.metamorf.eform.service.validator;

import java.util.ArrayList;
import java.util.List;

import com.metamorf.eform.common.core.ILookupConstant;
import com.metamorf.eform.common.core.SystemConstant;
import com.metamorf.eform.common.data.util.Operator;
import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.exception.ErrorHolder;
import com.metamorf.eform.data.access.security.IRoleDAO;
import com.metamorf.eform.entity.mytask.MasterDataApproval;
import com.metamorf.eform.entity.user.Role;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

/**
 * @author Hendri Yauw
 *
 */
public class RoleValidator extends Validator<Role> {

	private final static Logger LOGGER = LoggerFactory.getLogger(RoleValidator.class);
	private IRoleDAO roleDAO;
	
	public void setRoleDAO(IRoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}
	
	@Override
	public List<ErrorHolder> validate(Role anObject) {
		List<ErrorHolder> errorHolders = new ArrayList<ErrorHolder>();
		
		try {
			if(anObject != null){
				defaultValidationEdit(anObject, errorHolders);
			}
		}catch(Exception e) {
			LOGGER.error("ERROR: "+e);
			errorHolders.add(new ErrorHolder(e.getMessage()));
		}
		
		return errorHolders;
	}

	@Override
	public List<ErrorHolder> validate(Role anObject, Object... arguments) {
		List<ErrorHolder> errorHolders = new ArrayList<ErrorHolder>();
		try {
			if (arguments.length > 0) {
				if (arguments[0].equals(SystemConstant.MakerAction.ADD)) {
					defaultValidationAdd(anObject, errorHolders);
				}else if (arguments[0].equals(SystemConstant.MakerAction.EDIT)) {
					defaultValidationEdit(anObject, errorHolders);
				}else if (arguments[0].equals(SystemConstant.MakerAction.SUSPEND)) {
					if(anObject != null){
						if(roleDAO.isUsedByActiveUser(anObject.getId())){
							errorHolders.add(new ErrorHolder("validator.error.exist.activeUser",new Object[]{resourceBundle.getString("role.code")}));
						}
						if(roleDAO.isCodePending(anObject.getId())){
							errorHolders.add(new ErrorHolder("validator.error.pending.role"));
						}
					}
				}
			}
		}catch(Exception e) {
			LOGGER.error("ERROR: "+e);
			errorHolders.add(new ErrorHolder(e.getMessage()));
		}
		return errorHolders;
	}

	private void defaultValidationEdit (Role anObject, List<ErrorHolder> errorHolders) throws Exception {		
		
		if(roleDAO.isRoleExistsByCodeAndName(anObject.getName(), anObject.getCode(), anObject.getId())){
			errorHolders.add(new ErrorHolder("validator.error.exist.role.name"));
		}
		
		/*if(roleDAO.isRoleExistsByCode(anObject.getCode(), anObject.getId())){
			errorHolders.add(new ErrorHolder("validator.error.exist",new Object[]{resourceBundle.getString("role.code")}));
		}
		
		if(roleDAO.isRoleExistsByName(anObject.getName(), anObject.getId())){
			errorHolders.add(new ErrorHolder("validator.error.exist",new Object[]{resourceBundle.getString("role.name")}));
		}*/
		
		/*START - check exists in master data approval*/
		List<SearchFilter> searchFilters = new ArrayList<SearchFilter>();
		searchFilters.add(new SearchFilter(MasterDataApproval.MODULE_ID, Operator.EQUALS, ILookupConstant.MasterDataModule.ROL));
		searchFilters.add(new SearchFilter(MasterDataApproval.STATUS, Operator.EQUALS, SystemConstant.MyTaskApprovalStatus.PENDING));
		searchFilters.add(
				new SearchFilter(
					new SearchFilter(MasterDataApproval.MASTER_DATA_ID, Operator.EQUALS, anObject.getCode()),
					new SearchFilter(MasterDataApproval.NAME, Operator.EQUALS, anObject.getName())
				));
				
		if (masterDataApprovalDAO.isExistByCriteria(searchFilters)) {
			errorHolders.add(new ErrorHolder("validator.error.exist.masterDataApproval",new Object[]{resourceBundle.getString("role.code"), resourceBundle.getString("role.name")}));
		}
		/*END - check exists in master data approval*/
		
		errorHolders.add(validateField(anObject.getCode(), SystemConstant.Validation.Type.STRING, SystemConstant.Validation.Nullable.FALSE, 
				resourceBundle.getString("role.code"), 0, 0));
		
		errorHolders.add(validateField(anObject.getName(), SystemConstant.Validation.Type.STRING, SystemConstant.Validation.Nullable.FALSE, 
				resourceBundle.getString("role.name"), 0, 0));
	}
	
	private void defaultValidationAdd (Role anObject, List<ErrorHolder> errorHolders) throws Exception {
		
		if(roleDAO.isRoleExistsByCodeAndName(anObject.getName(), anObject.getCode(), anObject.getId())){
			errorHolders.add(new ErrorHolder("validator.error.exist.role.name"));
		}
		
		/*if(roleDAO.isRoleExistsByCode(anObject.getCode(), anObject.getId())){
			errorHolders.add(new ErrorHolder("validator.error.exist",new Object[]{resourceBundle.getString("role.code")}));
		}
		
		if(roleDAO.isRoleExistsByName(anObject.getName(), anObject.getId())){
			errorHolders.add(new ErrorHolder("validator.error.exist",new Object[]{resourceBundle.getString("role.name")}));
		}*/
		
		/*START - check exists in master data approval*/
		List<SearchFilter> searchFilters = new ArrayList<SearchFilter>();
		searchFilters.add(new SearchFilter(MasterDataApproval.MODULE_ID, Operator.EQUALS, ILookupConstant.MasterDataModule.ROL));
		searchFilters.add(new SearchFilter(MasterDataApproval.STATUS, Operator.EQUALS, SystemConstant.MyTaskApprovalStatus.PENDING));
		searchFilters.add(
				new SearchFilter(
					new SearchFilter(MasterDataApproval.MASTER_DATA_ID, Operator.EQUALS, anObject.getCode()),
					new SearchFilter(MasterDataApproval.NAME, Operator.EQUALS, anObject.getName())
				));
				
		if (masterDataApprovalDAO.isExistByCriteria(searchFilters)) {
			errorHolders.add(new ErrorHolder("validator.error.exist.masterDataApproval",new Object[]{resourceBundle.getString("role.code"), resourceBundle.getString("role.name")}));
		}
		/*END - check exists in master data approval*/
		
		errorHolders.add(validateField(anObject.getCode(), SystemConstant.Validation.Type.STRING, SystemConstant.Validation.Nullable.FALSE, 
				resourceBundle.getString("role.code"), 0, 0));
		
		errorHolders.add(validateField(anObject.getName(), SystemConstant.Validation.Type.STRING, SystemConstant.Validation.Nullable.FALSE, 
				resourceBundle.getString("role.name"), 0, 0));
	}
}
