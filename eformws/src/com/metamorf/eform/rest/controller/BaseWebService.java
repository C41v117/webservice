package com.metamorf.eform.rest.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.metamorf.eform.common.core.SystemParameter;
import com.metamorf.eform.rest.config.Constant;

/*
 * since security and client ip responsibilty is taken by meap, 
 * i think we need not to get the client ip anymore
 */
public class BaseWebService {
	private static final Logger logger = LoggerFactory.getLogger(BaseWebService.class);
	@Resource
	WebServiceContext wsContext;
	
	protected void sendError(int status, String msg) {
        try {
            MessageContext msgCtx = wsContext.getMessageContext();
            HttpServletResponse response = 
               (HttpServletResponse) msgCtx.get(MessageContext.SERVLET_RESPONSE);
            response.sendError(status, msg);
        } catch (IOException e) {
            // Never happens or yes?
        }
    }
	
	static protected Properties properties;
	protected Boolean fileExists;
	protected Gson gson = new GsonBuilder().setDateFormat(SystemParameter.JSON_DATE_FORMAT).create();
	protected Gson gsonRest = new GsonBuilder().setDateFormat(SystemParameter.DATE_FORMAT).create();
//	protected StringWriter stack = new StringWriter();
	
	static {
		properties = new Properties();
		try {
			InputStream is = BaseWebService.class.getClassLoader().getResourceAsStream(Constant.MESSAGE_PROPERTY);
			properties.load(is);
			is.close();
		} catch (IOException e) {
			logger.error("Failed loading properties for webservice, message: " + e.getMessage());
		}
	}
	protected void getMessageProperties() throws IOException{
		if (properties == null || properties.isEmpty()) {
			properties = new Properties();
			try {
				InputStream is = BaseWebService.class.getClassLoader().getResourceAsStream(Constant.MESSAGE_PROPERTY);
				properties.load(is);
				is.close();
			} catch (IOException e) {
				logger.error("Failed loading properties for webservice, message: " + e.getMessage());
			}
		}
	}
}
