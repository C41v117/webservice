package com.metamorf.eform.service.validator;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.metamorf.eform.common.core.ILookupConstant;
import com.metamorf.eform.common.core.SystemConstant;
import com.metamorf.eform.common.data.util.Operator;
import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.exception.ErrorHolder;
import com.metamorf.eform.data.access.user.IUserDAO;
import com.metamorf.eform.entity.mytask.MasterDataApproval;
import com.metamorf.eform.entity.user.User;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;


public class UserValidator extends Validator<User> {

	private final static Logger LOGGER = LoggerFactory.getLogger(UserValidator.class);
	private IUserDAO userDAO;
	
	@Override
	public List<ErrorHolder> validate(User anObject) {
		List<ErrorHolder> errorHolders = new ArrayList<ErrorHolder>();
		
		try {
			if(null!=anObject){
				/*if(userDAO.isUserExist(anObject.getId(),anObject.getUserName(),null))
					errorHolders.add(new ErrorHolder("validator.error.exist",new Object[]{resourceBundle.getString("internalUser.userName")}));*/
				
				errorHolders.add(validateField(anObject.getUserName(), SystemConstant.Validation.Type.STRING, SystemConstant.Validation.Nullable.FALSE, 
						resourceBundle.getString("internalUser.userName"), 0, 0));
				
				errorHolders.add(validateField(anObject.getFullName(), SystemConstant.Validation.Type.STRING, SystemConstant.Validation.Nullable.FALSE, 
						resourceBundle.getString("internalUser.name"), 0, 0));
				
//				errorHolders.add(validateField(anObject.getLastName(), SystemConstant.Validation.Type.STRING, SystemConstant.Validation.Nullable.TRUE,
//						resourceBundle.getString("internalUser.lastName"), 0, 0));
				
//				errorHolders.add(validateField(anObject.getEmail(), SystemConstant.Validation.Type.STRING, SystemConstant.Validation.Nullable.FALSE, 
//						resourceBundle.getString("internalUser.email"), 0, 0));
			}
			
		}catch(Exception e) {
			LOGGER.error("ERROR: "+e);
			errorHolders.add(new ErrorHolder(e.getMessage()));
		}
		
		return errorHolders;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	protected Map<String, Object> getMapFromJson(String json, Type type){
//		GsonBuilder builder = new GsonBuilder();
//	    Gson gson = builder.enableComplexMapKeySerialization().create();
	   // MyJson<T> jsonParsed = gson.fromJson(json, type);	
		Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDateDeserializer()).create();
		
//		Type mapType = new TypeToken<LinkedHashMap<String, AppParameter>>(){}.getType();
		Map<String, Object> results = (Map<String, Object>) gson.fromJson(json, type);
		return results;
	}

	@Override
 	public List<ErrorHolder> validate(User anObject, Object... arguments) {
		List<ErrorHolder> errorHolders = new ArrayList<ErrorHolder>();
		String module = arguments[0].toString();
		
		
		if(module.equals("bankUser")){
			
			String action = arguments[1].toString();
			
			errorHolders = this.validate(anObject);
			
			// check if user exist in user with same company id
			if (action.equals("add")){
				if(userDAO.isBankUserExist(anObject.getId(),anObject.getUserName()))
					errorHolders.add(new ErrorHolder("validator.error.userid.exist"));
			}
			
			// check if user exist in master data approval
			List<SearchFilter> searchFilters = new ArrayList<SearchFilter>();
			searchFilters.add(new SearchFilter(MasterDataApproval.MODULE_ID, Operator.EQUALS, ILookupConstant.MasterDataModule.BUS));
			searchFilters.add(new SearchFilter(MasterDataApproval.STATUS, Operator.IN_ARRAY, new Object[] {SystemConstant.MyTaskApprovalStatus.PENDING, SystemConstant.MyTaskApprovalStatus.REQUEST_REPAIR}));
			
			if (anObject != null) { 
				try {
					errorHolders.add(validateField(anObject.getMobileNo(), SystemConstant.Validation.Type.STRING, SystemConstant.Validation.Nullable.TRUE, 
							resourceBundle.getString("internalUser.mobileNo"), 0, 0));
				} catch(Exception e) {
					LOGGER.error("ERROR: "+e);
					errorHolders.add(new ErrorHolder(e.getMessage()));
				}
				if(null==anObject.getId()){
					List<MasterDataApproval> masterDataAppList = masterDataApprovalDAO.findAll(searchFilters, null);
					finduser:
					try {
						for (MasterDataApproval masterDataApproval : masterDataAppList) {
							masterDataApproval.setApprovalData(getMapFromJson(masterDataApproval.getJsonApprovalData(), new TypeToken<LinkedHashMap<String, User>>(){}.getType()));
							if(null!=masterDataApproval.getApprovalData().get(SystemConstant.ApprovalData.OLD_OBJECT) && masterDataApproval.getApprovalData().get(SystemConstant.ApprovalData.OLD_OBJECT) instanceof User){
								User user = (User)masterDataApproval.getApprovalData().get(SystemConstant.ApprovalData.OLD_OBJECT);
								if(user.getUserName().equalsIgnoreCase(anObject.getUserName())){
									if (anObject.getMasterDataApprovalId() == null) { //means add new
										errorHolders.add(new ErrorHolder("validator.error.exist.masterDataApproval2",new Object[]{resourceBundle.getString("internalUser.userName")}));
										break finduser;
									}
									else if (!anObject.getMasterDataApprovalId().equals(masterDataApproval.getId())){ //means repair
										errorHolders.add(new ErrorHolder("validator.error.exist.masterDataApproval2",new Object[]{resourceBundle.getString("internalUser.userName")}));
										break finduser;
									}
								}
							}
						}
					} catch (Exception e) {
						errorHolders.add(new ErrorHolder(e.getMessage()));
					}
				}
			}
			
			
//		}else if(module.equals("changePassword")){
//			try {
//				if(!SystemParameter.RCAS_ENABLE && !userDAO.checkPassword(anObject.getUserName(), anObject.getPassword())){
//					errorHolders.add(new ErrorHolder("validator.error.aapl.incorrect"));
//				}
//				errorHolders.add(validateFieldCompareString(arguments[1].toString(), arguments[2].toString(), arguments[3].toString(), arguments[4].toString()));
//				
//				errorHolders.add(validateField(arguments[1], SystemConstant.Validation.Type.PASSWORD, SystemConstant.Validation.Nullable.TRUE, 
//						resourceBundle.getString("aapl.new"), 0, 0));
//				
//				
//				// check if user password exist in history
//				List<SearchFilter> filters = new ArrayList<SearchFilter>();
//				filters.add(new SearchFilter(UserPasswordHistory.APP_USER_ID, Operator.EQUALS, anObject.getId()));
//				filters.add(new SearchFilter(UserPasswordHistory.AAPL_VALUE, Operator.EQUALS, arguments[5].toString()));
//				List<UserPasswordHistory> passwordHistories = userPasswordHistoryDAO.findAll(0, 0, filters, null);
//				if (!passwordHistories.isEmpty()) {
//					errorHolders.add(new ErrorHolder("validator.error.user.aapl.isFoundHistory"));
//				} else if (anObject.getPassword().equals(arguments[5].toString())){
//					errorHolders.add(new ErrorHolder("validator.error.user.oldPasswordNotSameNewPassword"));
//				}
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
		}
		
		return errorHolders;
	}
	
}