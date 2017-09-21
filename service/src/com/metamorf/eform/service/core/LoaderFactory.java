package com.metamorf.eform.service.core;

import java.util.List;

import org.jasypt.digest.config.SimpleDigesterConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.metamorf.eform.common.config.BeanUtilsConfigurer;
import com.metamorf.eform.common.core.IAuditTrailConstant;
import com.metamorf.eform.common.core.ILookupGroupConstant;
import com.metamorf.eform.common.core.SystemConstant;
import com.metamorf.eform.common.core.SystemParameter;
import com.metamorf.eform.entity.settings.AppParameter;
import com.metamorf.eform.entity.settings.Lookup;
import com.metamorf.eform.interfaces.settings.IAppParameterService;
import com.metamorf.eform.interfaces.settings.ILookupService;

public class LoaderFactory{
	
	final static Logger LOGGER = LoggerFactory.getLogger(LoaderFactory.class);
	
	public static void initializeDigester(SimpleDigesterConfig config) {
		LOGGER.info("Start initializing digester... ");
		config.setAlgorithm(SystemParameter.DIGESTER_ALGORITHM);
		LOGGER.info("Finish initializing digester... ");
	}
	
	
	public static void initializeBeanUtils() {
		LOGGER.info("Start initializing beanutils... ");
		BeanUtilsConfigurer.configure();
		LOGGER.info("Finish initializing beanutils... ");
	}
	
	public static void initializeAppParameter(IAppParameterService appParameterService) {
		LOGGER.info("Start  loading App Parameter ..");
		List<AppParameter> appParams = appParameterService.findAll();
		for (AppParameter appParam : appParams) {
			if(appParam.getName()!=null && appParam.getValue()!=null){
					LOGGER.debug("Setting appParameter [{}] with value [{}] ",appParam.getName(), appParam.getValue());
					
					/*[30 June 2016] 
					 * Remove Load parameter for date format. 
					 * currently not using date format from database for eForm. */
					/*if(appParam.getName().contains(SystemConstant.DATE_FORMAT_FLAG)){
						String format = normalizeDateToJavaFormat(appParam.getValue(),false);
						String formatNoTime = normalizeDateToJavaFormat(appParam.getValue(),true);
						SystemParameter.updateSystemEnvironment(appParam.getName(),format);
						SystemParameter.updateParameterDataType(appParam.getName(), appParam.getDataType());
						SystemParameter.updateSystemEnvironment(SystemConstant.STRUTS2_DATE_FORMAT_FLAG, "{0,date,"+format+"}");
						SystemParameter.updateSystemEnvironment(SystemConstant.JQUERY_DATE_FORMAT_FLAG, getDateJquery(format));
						SystemParameter.updateSystemEnvironment(SystemConstant.JQUERY_DATE_NO_TIME_FORMAT_FLAG, getDateJqueryNoTime(format));
						SystemParameter.updateSystemEnvironment(SystemConstant.JQGRID_DATE_FORMAT_FLAG, getDateJqGrid(format,false));
						SystemParameter.updateSystemEnvironment(SystemConstant.JQGRID_DATE_NO_TIME_FORMAT_FLAG, getDateJqGrid(format,true));
						SystemParameter.updateSystemEnvironment(SystemConstant.DATE_NO_TIME_FORMAT_FLAG, formatNoTime);
						SystemParameter.updateSystemEnvironment(SystemConstant.STRUTS2_DATE_FORMAT_NO_TIME_FLAG, "{0,date,"+formatNoTime+"}");
					}else{*/
						SystemParameter.updateSystemEnvironment(appParam.getName(),appParam.getValue().toString());
						SystemParameter.updateParameterDataType(appParam.getName(), appParam.getDataType());
					/*}*/
			}
		}
		LOGGER.info("Finish loading App Parameter ..");
	}
	
	public static String getDateJquery(String format){
    	String FDATE = format;
	    //removing time using systemconstant as blacklist, if there is new format, either with SOP to reject the format or re:code
	    FDATE = FDATE.replace(SystemConstant.WEB_SERVICE_TIME, "");
	    FDATE = FDATE.replace(SystemConstant.HOUR_MINUTE_SECOND_MASK, "");
	    FDATE = FDATE.replace(SystemConstant.HOUR_MINUTE_SECOND_MASK_NO_DELIMITER, "");
	    FDATE = FDATE.replace(SystemConstant.HOUR_MINUTE_MASK, "");
	    FDATE = FDATE.replace(SystemConstant.HOUR_MINUTE_SECOND_AMPM, "");
	    FDATE = FDATE.replace(SystemConstant.HOUR_MINUTE_MASK_NO_DELIMITER, "");
	    FDATE = FDATE.replace("hh:mm:ss", "");
	    FDATE = FDATE.replace("hhmmss", "");
	    FDATE = FDATE.replace("hh-mm-ss", "");
	    //remove whitespace on left and right
	    FDATE = FDATE.trim();
	    
    	if(FDATE.contains("yyyy")||FDATE.contains("YYYY")){
    		FDATE = FDATE.replace("yyyy", "yy");
    		FDATE = FDATE.replace("YYYY", "yy");
    	}else if(FDATE.contains("yy")||FDATE.contains("YY")){
    		FDATE = FDATE.replace("yy", "y");
    		FDATE = FDATE.replace("YY", "y");
    	}
    	if(FDATE.contains("MMMMM")||FDATE.contains("mmmmm")){
    		FDATE = FDATE.replace("MMMMM", "MM");
    		FDATE = FDATE.replace("mmmmm", "MM");
    	}else if(FDATE.contains("MMM")||FDATE.contains("mmm")){
    		FDATE = FDATE.replace("MMM", "M");
    		FDATE = FDATE.replace("mmm", "M");
    	}else if(FDATE.contains("MM")){
    		FDATE = FDATE.replace("M", "m");
    	}else if(FDATE.contains("M")){
    		FDATE = FDATE.replace("M", "m");
    	}
    	if(FDATE.contains("DD")){
    		FDATE = FDATE.replace("DD", "dd");
    	}else if(FDATE.contains("D")||FDATE.contains("d")){
    		FDATE = FDATE.replace("D", "d");
    		FDATE = FDATE.replace("d", "d");
    	}
    	return FDATE;
    }
	
	public static String getDateJqueryNoTime(String format){
		String FDATE = format;
	    //removing time using systemconstant as blacklist, if there is new format, either with SOP to reject the format or re:code
	    FDATE = FDATE.replace(SystemConstant.WEB_SERVICE_TIME, "");
	    FDATE = FDATE.replace(SystemConstant.HOUR_MINUTE_SECOND_MASK, "");
	    FDATE = FDATE.replace(SystemConstant.HOUR_MINUTE_SECOND_MASK_NO_DELIMITER, "");
	    FDATE = FDATE.replace(SystemConstant.HOUR_MINUTE_MASK, "");
	    FDATE = FDATE.replace(SystemConstant.HOUR_MINUTE_SECOND_AMPM, "");
	    FDATE = FDATE.replace(SystemConstant.HOUR_MINUTE_MASK_NO_DELIMITER, "");
	    FDATE = FDATE.replace("hh:mm:ss", "");
	    FDATE = FDATE.replace("hhmmss", "");
	    FDATE = FDATE.replace("hh-mm-ss", "");
	    FDATE = FDATE.trim();
	    FDATE = FDATE.replace("Y", "y");
    	FDATE = FDATE.replace("m", "M");
    	FDATE = FDATE.replace("D", "d");
	    return FDATE.trim();
	}
	
	public static String getDateJqGrid(String format, boolean noTime){
    	String FDATE = format;
    	if(noTime){
    		FDATE = FDATE.replace(SystemConstant.HOUR_MINUTE_SECOND_MASK, "");
		    FDATE = FDATE.replace(SystemConstant.HOUR_MINUTE_SECOND_MASK_NO_DELIMITER, "");
		    FDATE = FDATE.replace(SystemConstant.HOUR_MINUTE_MASK, "");
		    FDATE = FDATE.replace(SystemConstant.HOUR_MINUTE_MASK_NO_DELIMITER, "");
		    FDATE = FDATE.replace("hh:mm:ss", "");
		    FDATE = FDATE.replace("hhmmss", "");
		    FDATE = FDATE.replace("hh-mm-ss", "");
		    FDATE = FDATE.trim();
    	}else{
		    FDATE = FDATE.replace(SystemConstant.HOUR_MINUTE_SECOND_MASK, "H:i:s");
		    FDATE = FDATE.replace(SystemConstant.HOUR_MINUTE_SECOND_MASK_NO_DELIMITER, "His");
		    FDATE = FDATE.replace(SystemConstant.HOUR_MINUTE_MASK, "H:i");
		    FDATE = FDATE.replace(SystemConstant.HOUR_MINUTE_MASK_NO_DELIMITER, "Hi");
		    FDATE = FDATE.replace("hh:mm:ss", "H:i:s");
		    FDATE = FDATE.replace("hhmmss", "His");
		    FDATE = FDATE.replace("hh-mm-ss", "H-i-s");
    	}
    	if(FDATE.contains("yyyy")||FDATE.contains("YYYY")){
    		FDATE = FDATE.replace("yyyy", "Y");
    		FDATE = FDATE.replace("YYYY", "Y");
    	}else if(FDATE.contains("yy")||FDATE.contains("YY")){
    		FDATE = FDATE.replace("yy", "y");
    		FDATE = FDATE.replace("YY", "y");
    	}
    	if(FDATE.contains("MMMMM")||FDATE.contains("mmmmm")){
    		FDATE = FDATE.replace("MMMMM", "F");
    		FDATE = FDATE.replace("mmmmm", "F");
    	}else if(FDATE.contains("MMM")||FDATE.contains("mmm")){
    		FDATE = FDATE.replace("MMM", "M");
    		FDATE = FDATE.replace("mmm", "M");
    	}else if(FDATE.contains("MM")||FDATE.contains("mm")){
    		FDATE = FDATE.replace("MM", "m");
    		FDATE = FDATE.replace("mm", "m");
    	}
    	if(FDATE.contains("DD")||FDATE.contains("dd")){
    		FDATE = FDATE.replace("DD", "d");
    		FDATE = FDATE.replace("dd", "d");
    	}else if(FDATE.contains("D")||FDATE.contains("d")){
    		FDATE = FDATE.replace("D", "n");
    		FDATE = FDATE.replace("d", "n");
    	}
    	return FDATE;
    }
	
	public static String normalizeDateToJavaFormat(String format, boolean noTime) {
		String _format = format;
		if(_format.contains("Y")){
			_format = _format.replace("Y", "y");
		}
		if(_format.contains("m")){
			_format = _format.replace("m", "M");
		}
		if(_format.contains("D")){
			_format = _format.replace("D", "d");
		}
		if(noTime){
			if(_format.contains("hh:mm:ss")||_format.contains("hh:MM:ss")||_format.contains("HH:MM:ss")){
				_format = _format.replace("hh:mm:ss", "");
				_format = _format.replace("hh:MM:ss", "");
				_format = _format.replace("HH:MM:ss", "");
			}
		}else{
			if(_format.contains("hh:mm:ss")||_format.contains("hh:MM:ss")||_format.contains("HH:MM:ss")){
				_format = _format.replace("hh:mm:ss", "HH:mm:ss");
				_format = _format.replace("hh:MM:ss", "HH:mm:ss");
				_format = _format.replace("HH:MM:ss", "HH:mm:ss");
			}
		}
		
		return _format.trim();
	}
	
	public static  void initializeAuditTrailParameter(ILookupService lookupService){
		LOGGER.info("Start loading AuditTrailParameter..");
		List<Lookup> lookupLog= lookupService.findByLookupGroup(ILookupGroupConstant.LOG_GROUP);
		for(Lookup LOG : lookupLog){
			IAuditTrailConstant.activityMap.put(LOG.getCode(),LOG.getName());
			IAuditTrailConstant.activityDescriptionMap.put(LOG.getCode(),LOG.getDescription());
		}
		LOGGER.info("Finish loading AuditTrailParameter..");
	}
	
}
