package com.metamorf.eform.service.validator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.metamorf.eform.common.core.ILookupConstant;
import com.metamorf.eform.common.core.SystemConstant;
import com.metamorf.eform.common.data.util.Operator;
import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.exception.ErrorHolder;
import com.metamorf.eform.common.util.StringFunction;
import com.metamorf.eform.data.access.settings.ILookupDAO;
import com.metamorf.eform.entity.mytask.MasterDataApproval;
import com.metamorf.eform.entity.settings.Lookup;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

public class LookupValidator extends Validator<Lookup> {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(LookupValidator.class);

	private ILookupDAO lookupDAO;

	public void setLookupDAO(ILookupDAO lookupDAO) {
		this.lookupDAO = lookupDAO;
	}

	@Override
	public List<ErrorHolder> validate(Lookup curLookup) {
		List<ErrorHolder> errorHolders = new ArrayList<ErrorHolder>();
		if(StringFunction.checkString(curLookup.getCode())==null){
			errorHolders.add(new ErrorHolder("message.mandatory.code"));
		}
		if(StringFunction.checkString(curLookup.getName())==null){
			errorHolders.add(new ErrorHolder("message.mandatory.name"));
		}
		if(lookupDAO.isCodeExist(curLookup.getId(), curLookup.getCode(), curLookup.getLookupGroupString())){
			errorHolders.add(new ErrorHolder("message.duplicate.not.allowed"));
		}
		if (curLookup.getIsAlternateEntry() != null && curLookup.getIsAlternateEntry()
				&& lookupDAO.isAlternateEntryExist(curLookup.getId(), curLookup.getIsAlternateEntry(), curLookup.getLookupGroupString())) {
			errorHolders.add(new ErrorHolder("validator.error.exist.alternateEntry"));
		}
		/*check whether exists in master data approval*/
		List<SearchFilter> searchFilters = new ArrayList<SearchFilter>();
		searchFilters.add(new SearchFilter(MasterDataApproval.MODULE_ID, Operator.EQUALS, ILookupConstant.MasterDataModule.LOOKUP));
		searchFilters.add(new SearchFilter(MasterDataApproval.STATUS, Operator.EQUALS, SystemConstant.MyTaskApprovalStatus.PENDING));
		
		List<MasterDataApproval> masterDataAppList = masterDataApprovalDAO.findAll(searchFilters, null);
		try {
			for (MasterDataApproval masterDataApproval : masterDataAppList) {
				masterDataApproval.setApprovalData(getMapFromJson(masterDataApproval.getJsonApprovalData(), new TypeToken<LinkedHashMap<String, Lookup>>(){}.getType()));
				if(null!=masterDataApproval.getApprovalData().get(SystemConstant.ApprovalData.OLD_OBJECT) && masterDataApproval.getApprovalData().get(SystemConstant.ApprovalData.OLD_OBJECT) instanceof Lookup){
					Lookup lookup = (Lookup)masterDataApproval.getApprovalData().get(SystemConstant.ApprovalData.OLD_OBJECT);
					if(curLookup.getLookupGroupString().equals(lookup.getLookupGroupString()) && curLookup.getCode().equals(lookup.getCode())){
						errorHolders.add(new ErrorHolder("validator.error.exist.masterDataApproval2",new Object[]{resourceBundle.getString("lookup.code")}));
						break;
					}
					if (curLookup.getIsAlternateEntry() != null && curLookup.getIsAlternateEntry() &&
							curLookup.getLookupGroupString().equals(lookup.getLookupGroupString()) && 
							curLookup.getIsAlternateEntry().equals(lookup.getIsAlternateEntry())) {
						errorHolders.add(new ErrorHolder("validator.error.exist.alternateEntry"));
						break;
					}
				}
			}
		} catch (Exception e) {
			errorHolders.add(new ErrorHolder(e.getMessage()));
		}
		return errorHolders;
	}

	@Override
	public List<ErrorHolder> validate(Lookup anObject, Object... arguments) {
		List<ErrorHolder> errorHolders = new ArrayList<ErrorHolder>();
		try {
			if (arguments.length > 0) {
				if (arguments[0].equals(SystemConstant.MakerAction.ADD) || arguments[0].equals(SystemConstant.MakerAction.EDIT)) {
					return validate(anObject);
				}
			}
		}catch(Exception e) {
			LOGGER.error("ERROR: "+e);
			errorHolders.add(new ErrorHolder(e.getMessage()));
		}
		return errorHolders;
	}
}
