package com.metamorf.eform.service.validator;

import java.util.ArrayList;
import java.util.List;

import com.metamorf.eform.common.core.SystemConstant;
import com.metamorf.eform.common.exception.ErrorHolder;
import com.metamorf.eform.common.util.StringFunction;
import com.metamorf.eform.entity.settings.AppParameter;

public class AppParameterValidator extends Validator<AppParameter> {

	@Override
	public List<ErrorHolder> validate(AppParameter appParameter) {
		List<ErrorHolder> errorHolders = new ArrayList<ErrorHolder>();
		if (StringFunction.checkString(appParameter.getValue()) == null) {
			errorHolders.add(new ErrorHolder("validator.appparam.value.required"));
		}
		/*Integer dataType = SystemParameter.appParamDataTypeMap.get(appParameter.getName());
		if(dataType!=null){
			switch (dataType) {
			case SystemConstant.FIELD_TYPE_INT:
				if (!StringFunction.isValidNumeric(appParameter.getValue())) {
					errorHolders.add(new ErrorHolder("validator.appparam.value.required.numeric"));
				}else if(appParameter.getName().equalsIgnoreCase("MAX_ITEM_ROW") && 
						(Integer.valueOf(appParameter.getValue()) < 5)){
					errorHolders.add(new ErrorHolder("validator.appparam.value.minimum5"));
				}
				break;
			case SystemConstant.FIELD_TYPE_LONG:
				if (!StringFunction.isValidNumeric(appParameter.getValue())) {
					errorHolders.add(new ErrorHolder("validator.appparam.value.required.numeric"));
				}
				break;
			case SystemConstant.FIELD_TYPE_DOUBLE:
				if (!StringFunction.isValidNumeric(appParameter.getValue())) {
					errorHolders.add(new ErrorHolder("validator.appparam.value.required.numeric"));
				}
				break;
			case SystemConstant.FIELD_TYPE_DATE:
				if (!StringFunction.isValidDateFormat(appParameter.getValue())) {
					errorHolders.add(new ErrorHolder("validator.appparam.value.required.date"));
				}
				break;
			case SystemConstant.FIELD_TYPE_BOOLEAN:
				if (!StringFunction.isValidBooleanFormat(appParameter.getValue())) {
					errorHolders.add(new ErrorHolder("validator.appparam.value.required.boolean"));
				}
				break;
			case SystemConstant.FIELD_TYPE_TIME:
				if (!DateTimeFunction.isValidTimeFormat(appParameter.getValue())) {
					errorHolders.add(new ErrorHolder("validator.appparam.value.required.time"));
				}
				break;
			case SystemConstant.FIELD_TYPE_DAY:
				if(!DateTimeFunction.isValidDayFormat(appParameter.getValue())) {
					errorHolders.add(new ErrorHolder("validator.appparam.value.required.day"));
				}
			}
		}else{
			errorHolders.add(new ErrorHolder("validator.appparam.data.type.not.found", new Object[]{appParameter.getName()}));
		}*/
		return errorHolders;
	}

	@Override
	public List<ErrorHolder> validate(AppParameter anObject, Object... arguments) {
		if(arguments[0].equals(SystemConstant.MakerAction.EDIT)){
			return validate(anObject);
		}
		return null;
	}

}
