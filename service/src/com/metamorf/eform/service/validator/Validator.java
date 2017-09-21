package com.metamorf.eform.service.validator;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.hibernate.proxy.pojo.javassist.JavassistLazyInitializer;
import org.owasp.esapi.ESAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.metamorf.eform.common.core.SystemConstant;
import com.metamorf.eform.common.core.SystemParameter;
import com.metamorf.eform.common.exception.ErrorHolder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.util.DateTimeFunction;
import com.metamorf.eform.common.util.EmailFunction;
import com.metamorf.eform.common.util.StringFunction;
import com.metamorf.eform.data.access.mytask.IMasterDataApprovalDAO;
import com.metamorf.eform.entity.settings.Lookup;
import com.metamorf.eform.entity.user.User;
import com.metamorf.eform.interfaces.validator.IValidator;

public abstract class Validator<T> implements IValidator<T>{
	private final static Logger LOGGER = LoggerFactory.getLogger(Validator.class);
	
	protected ResourceBundle resourceBundle;
	protected IMasterDataApprovalDAO masterDataApprovalDAO;
	
	public void setMasterDataApprovalDAO(
			IMasterDataApprovalDAO masterDataApprovalDAO) {
		this.masterDataApprovalDAO = masterDataApprovalDAO;
	}
	
	public void setResourceBundle(ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;
	}

	public SystemException validateFromAction(Locale locale, final T anObject){
		resourceBundle = ResourceBundle.getBundle("resources", locale);
		List<ErrorHolder> errorHolders = new ArrayList<ErrorHolder>();
		if(anObject!=null && !(anObject instanceof User) && !(anObject instanceof Lookup)){
			errorHolders.add(validateXSS(anObject)); //open when ready
		}
		errorHolders.addAll(validate(anObject));
		errorHolders.removeAll(Collections.singleton(null));
		return new SystemException(errorHolders);
	}
	
	public SystemException validateFromAction(Locale locale, final T anObject, final Object... arguments){
		resourceBundle = ResourceBundle.getBundle("resources", locale);
		List<ErrorHolder> errorHolders = new ArrayList<ErrorHolder>();
		if(anObject!=null && !(anObject instanceof User) && !(anObject instanceof Lookup) ){
			errorHolders.add(validateXSS(anObject)); //open when ready
		}
		errorHolders.addAll(validate(anObject, arguments));
		errorHolders.removeAll(Collections.singleton(null));
		return new SystemException(errorHolders);
	}
		
	public ErrorHolder validateFieldCompareString(String str1, String str2, String fieldName1, String fieldName2){
		ErrorHolder errorMessage = null;
		boolean isNull = false;
		if(null==str1 || str1.isEmpty()){
			errorMessage = new ErrorHolder("validator.error.mandatory", new Object[]{fieldName1});
			isNull = true;
		}
		if(null==str2 || str2.isEmpty()){
			errorMessage = new ErrorHolder("validator.error.mandatory", new Object[]{fieldName2});
			isNull = true;
		}
		
		if(!isNull){
			if(!StringFunction.compareString(str1, str2)){
				errorMessage = new ErrorHolder("validator.error.compare.same", new Object[]{fieldName1,fieldName2});
			}
		}
		return errorMessage;
	}
	
	static Set<Class<?>> ret = new HashSet<Class<?>>();
	static{
	    ret.add(Boolean.class);
	    ret.add(Character.class);
	    ret.add(Byte.class);
	    ret.add(Short.class);
	    ret.add(Integer.class);
	    ret.add(Long.class);
	    ret.add(Float.class);
	    ret.add(Double.class);
	    ret.add(Void.class);
	    ret.add(String.class);
	    ret.add(BigDecimal.class);
	    ret.add(BigInteger.class);
	    ret.add(JavassistLazyInitializer.class);
	    ret.add(Date.class);
	}
	
	private Boolean checkComplexObject(Class cc){
		Boolean result = false;
		if(!cc.isPrimitive()){
			if(!ret.contains(cc)){
				result = true;
			}
		}
		return result;
	}
	
	public ErrorHolder validateXSS(final T anObject){
		LOGGER.debug("Validating XSS for object {}", anObject.getClass().getCanonicalName());
		return traverseObject(anObject,0);
	}
	
	public ErrorHolder traverseObject(final Object anObject, int deep){
		//only validate String type or collection of String, since xss attack suppose to be attack on input that accept character(s), CMIIW
		//TODO test for Map
		ErrorHolder errorMessage = null;
		if(deep<3){ //traverse only 3 object deep
			try {
				LOGGER.debug("traverse object {}", anObject.getClass().getCanonicalName());
				Class c = Class.forName(anObject.getClass().getCanonicalName());
				Field[] fields = c.getDeclaredFields();
				LOGGER.debug("fields is null? {}", fields==null);
				for (Field field : fields) {
					LOGGER.debug("validating field {}", field.getName());
					if(!"approvalData".equals(field.getName())){
						field.setAccessible(true);
						Object check = field.get(anObject);
						if(check!=null){
							Class cc = field.getType();
							if(!Modifier.isStatic(field.getModifiers())){
								if(cc.isArray()){
									LOGGER.debug("field {} is array", field.getName());
									Object[] checks = (Object[])check;
									LOGGER.debug("checks is null? {}", checks==null);
									for (Object ck : checks) {
										LOGGER.debug("ck is null? {}", ck==null);
										if("String".equals(ck.getClass().getSimpleName())){
											if(!isNotXSS(field.getName(), ck)){
												errorMessage = new ErrorHolder("validator.security.field.input.not.allowed", new Object[]{field.getName()});
												break;
											}
										}
									}
								}else if(Collection.class.isAssignableFrom(field.getType())){
									LOGGER.debug("field {} is collection", field.getName());
									Type pType = field.getGenericType();
					                if (pType instanceof ParameterizedType) {
					                	ParameterizedType aType = (ParameterizedType) pType;
					                    Type[] fieldArgTypes = aType.getActualTypeArguments();
					                    for(Type fieldArgType : fieldArgTypes){
					                        Class fieldArgClass = (Class) fieldArgType;
					                        if("String".equals(fieldArgClass.getSimpleName())){
					                        	Collection<String> checks = (Collection<String>) field.get(anObject);
					                        	for (String ck : checks) {
					                        		if(!isNotXSS(field.getName(), ck)){
														errorMessage = new ErrorHolder("validator.security.field.input.not.allowed", new Object[]{field.getName()});
														break;
													}
												}
					                        }
					                    }
					                }
								}else if(checkComplexObject(cc)){
									if(check!=null){
										LOGGER.debug("field {} is complex object", field.getName());
										errorMessage = traverseObject(check,++deep);
										if(errorMessage!=null){
											break;
										}
									}
								}else if("String".equals(cc.getSimpleName())){
									LOGGER.debug("field {} is String", field.getName());
									if(!isNotXSS(field.getName(), check)){
										errorMessage = new ErrorHolder("validator.security.field.input.not.allowed", new Object[]{field.getName()});
										break;
									}
								}
							}
						}
					}
				}
				LOGGER.debug("finish looping traverse object {} at depth {}", anObject.getClass().getCanonicalName(), deep);
			} catch (ClassNotFoundException e) {
				LOGGER.error("ClassNotFoundException for xss validation [{}]", e);
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				LOGGER.error("IllegalArgumentException xss validation [{}]", e);
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				LOGGER.error("IllegalAccessException xss validation [{}]", e);
				e.printStackTrace();
			} catch (Exception e){
				LOGGER.error("DontKnowWhatException xss validation [{}]", e);
				e.printStackTrace();
			}
		}
		return errorMessage;
	}
	
	private Boolean isNotXSS(String fieldName, Object input){
		if(input!=null){
			LOGGER.debug("validating XSS for {}", input.toString());
			if(fieldName.toLowerCase().contains("email")){
				if(input instanceof String){
					/*try {
						String result = ESAPI.validator().getValidInput(fieldName, String.valueOf(input), "Email", 99999, true, true);
						LOGGER.debug("1_fieldName {} validInput {}", fieldName, result);
					} catch (ValidationException e) {
						e.printStackTrace();
					} catch (IntrusionException e) {
						e.printStackTrace();
					}*/
					String[] anEmail = input.toString().split(",");
					Boolean result = true;
					if(anEmail.length>1){
						for (String email : anEmail) {
							result = ESAPI.validator().isValidInput(fieldName, email.trim(), "Email", 99999, true);
							if(!result){
								return result;
							}
						}
						return result;
					}
					return ESAPI.validator().isValidInput(fieldName, input.toString(), "Email", 99999, true); 
				}else{
					/*try {
						String result = ESAPI.validator().getValidInput(fieldName, String.valueOf(input), "XSSCustom", 99999, true, true);
						LOGGER.debug("2_fieldName {} validInput {}", fieldName, result);
					} catch (ValidationException e) {
						e.printStackTrace();
					} catch (IntrusionException e) {
						e.printStackTrace();
					}*/
					return ESAPI.validator().isValidInput(fieldName, input.toString(), "XSSCustom", 99999, true); //comment dulu, classpath related
				}
			}else{
				/*try {
					String result = ESAPI.validator().getValidInput(fieldName, String.valueOf(input), "XSSCustom", 99999, true, true);
					LOGGER.debug("3_fieldName {} validInput {}", fieldName, result);
				} catch (ValidationException e) {
					e.printStackTrace();
				} catch (IntrusionException e) {
					e.printStackTrace();
				}*/
				return ESAPI.validator().isValidInput(fieldName, input.toString(), "XSSCustom", 99999, true); //comment dulu, classpath related
			}
			//return true;
		}else{
			return true;
		}
	}
	
	public ErrorHolder validateField(Object anObject, int validationType, Boolean nullable, String fieldName, Integer minLength, Integer maxLength) throws Exception{
		ErrorHolder errorMessage = null;
		
		if(!nullable){
			if (anObject == null) {
				errorMessage = new ErrorHolder("validator.error.mandatory", new Object[]{fieldName});
			}
			else if (null==StringFunction.checkString(anObject.toString())) {
				errorMessage = new ErrorHolder("validator.error.mandatory", new Object[]{fieldName});
			}
		}
		
		
		if(anObject instanceof String){
			String fieldValue = anObject.toString();
			
			if(null!=StringFunction.checkString(fieldValue)){
				switch(validationType){
				case SystemConstant.Validation.Type.STRING:{
					// String Validation
					if(0!=minLength){
						if(fieldValue.length()<minLength)
							errorMessage = new ErrorHolder("validator.errors.minlength", new Object[]{fieldName,minLength});
					}
					if(0!=maxLength){
						if(fieldValue.length()>maxLength)
							errorMessage = new ErrorHolder("validator.errors.maxlength", new Object[]{fieldName,maxLength});
					}
					
					break;
				}
				case SystemConstant.Validation.Type.NUMBER: {
					// Number Validation
					if(!StringFunction.isNumber(fieldValue))
						errorMessage = new ErrorHolder("validator.errors.integer", new Object[]{fieldName});
					
					break;
				}
				case SystemConstant.Validation.Type.EMAIL: {
					// Email Validation
					if(!EmailFunction.isAddressValid(fieldValue))
						errorMessage = new ErrorHolder("validator.errors.email", new Object[]{fieldName});
						
					break;
				}
				case SystemConstant.Validation.Type.PASSWORD: {
					// Password Validation
					if(fieldValue.length()<SystemParameter.MIN_PASSWORD_LENGTH)
						errorMessage = new ErrorHolder("validator.errors.minlength", new Object[]{fieldName,SystemParameter.MIN_PASSWORD_LENGTH});
				
					if(fieldValue.length()>SystemParameter.MAX_PASSWORD_LENGTH)
						errorMessage = new ErrorHolder("validator.errors.maxlength", new Object[]{fieldName,SystemParameter.MAX_PASSWORD_LENGTH});
					
					if(!StringFunction.isValidPassword(fieldValue))
						errorMessage = new ErrorHolder("validator.errors.aapl", new Object[]{fieldName});
						
					break;
				}
				case SystemConstant.Validation.Type.PHONE: {
					// Phone Validation
					if(!StringFunction.isValidPhoneFormat(fieldValue))
						errorMessage = new ErrorHolder("validator.errors.phone", new Object[]{fieldName});
						
					break;
				}
				case SystemConstant.Validation.Type.DATE: {
					// Date Validation
					if(!StringFunction.isValidDateFormat(fieldValue))
						errorMessage = new ErrorHolder("validator.errors.date", new Object[]{fieldName});
						
					break;
				}
				case SystemConstant.Validation.Type.TIME: {
					// Time Validation
					if(!DateTimeFunction.isValidTimeFormat(fieldValue))
						errorMessage = new ErrorHolder("validator.errors.time", new Object[]{fieldName});
						
					break;
				}
				case SystemConstant.Validation.Type.MONEY: {
					// Money Validation
					if(!StringFunction.isValidMoneyFormat(fieldValue))
						errorMessage = new ErrorHolder("validator.errors.money", new Object[]{fieldName});
						
					break;
				}
				case SystemConstant.Validation.Type.PERCENTAGE: {
					// Percentage Validation
					if(!StringFunction.isValidPercentageFormat(fieldValue))
						errorMessage = new ErrorHolder("validator.errors.percentage", new Object[]{fieldName});
						
					break;
				}
			}
			}
		}else if(anObject instanceof Integer){
			
		}else if(anObject instanceof Double){
			String fieldValue = anObject.toString();
			
			if(null!=StringFunction.checkString(fieldValue)){
				/*Swith are designed complex branches, and allow branches to share treatement. Using a switch for only a few 
			     branches is ill advised, as switches are not as easy to understand as if. In this case, it's most likely
			     is a good idea to use a if statement instead, at least to increase code readability.*/
				switch(validationType){ // NOPMD by AGE-SYSTEM on 9/30/13 11:03 AM, but since this switch might growing, remarked as reviewd
					case SystemConstant.Validation.Type.MONEY: {
						// Money Validation
						if(!StringFunction.isValidMoneyFormat(fieldValue))
							errorMessage = new ErrorHolder("validator.errors.money", new Object[]{fieldName});
							
						break;
					}
					case SystemConstant.Validation.Type.PERCENTAGE: {
						// Percentage Validation
						if(!StringFunction.isValidPercentageFormat(fieldValue))
							errorMessage = new ErrorHolder("validator.errors.percentage", new Object[]{fieldName});
							
						break;
					}
				}
			}
		}else if(anObject instanceof Boolean){
			
		}
		return errorMessage;
	}
	
	protected Map<String, Object> getMapFromJson(String json, Type type){
		Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDateDeserializer()).create();
		
		Map<String, Object> results = (Map<String, Object>) gson.fromJson(json, type);
		return results;
	}
	
}
