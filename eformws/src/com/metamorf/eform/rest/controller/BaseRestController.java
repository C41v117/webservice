package com.metamorf.eform.rest.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.Resource;
import javax.xml.ws.WebServiceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.metamorf.eform.common.core.SystemParameter;
import com.metamorf.eform.rest.config.Constant;

public class BaseRestController {
	private static final Logger logger = LoggerFactory.getLogger(BaseRestController.class);

	@Resource
	WebServiceContext wsContext;

	@ExceptionHandler({ org.springframework.http.converter.HttpMessageNotReadableException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public String resolveException() {
		return "Invalid request application/json parameters : \ndata type value invalid (e.g expect integer, input string) or parameters name invalid (case sensitive)";
	}

	static protected Properties properties;
	protected Boolean fileExists;
	protected Gson gson = new GsonBuilder().setDateFormat(SystemParameter.JSON_DATE_FORMAT)
			.create();
	protected Gson gsonRest = new GsonBuilder().setDateFormat(SystemParameter.DATE_FORMAT).create();
	
	ObjectMapper mapper = new ObjectMapper();

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

	protected void getMessageProperties() throws IOException {
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
