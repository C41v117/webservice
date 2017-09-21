package com.metamorf.eform.web.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.metamorf.eform.common.config.BeanUtilsConfigurer;
import com.metamorf.eform.common.core.ILookupConstant;
import com.metamorf.eform.common.core.SystemConstant;
import com.metamorf.eform.interfaces.settings.IAppParameterService;
import com.metamorf.eform.interfaces.settings.ILookupService;
import com.metamorf.eform.service.core.LoaderFactory;

public class RSCFParameterLoader implements ServletContextListener {

	private final static Logger LOGGER = LoggerFactory.getLogger(RSCFParameterLoader.class);

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		LOGGER.info("start Context Initialization");
		ServletContext servletContext = servletContextEvent.getServletContext();

		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);

		initializeAppParameter(ctx);

		// web service doesn't need to know holiday
//		initializeHoliday(ctx);

		initializeBeanUtils();

		initializeAuditTrailParameter(ctx);

		// initializeLookupConstant(ctx);

		LOGGER.info("end Context Initialization");
	}

	private void initializeLookupConstant(WebApplicationContext ctx) {
		Map<String, Object> lookupConstant = new HashMap<String, Object>();
		Class c = ILookupConstant.class;
		Class[] classes = c.getDeclaredClasses();
		for (int i = 0; i < classes.length; i++) {
			Class cc = classes[i];
			int modifier = cc.getModifiers();
			if (Modifier.isFinal(modifier) && !Modifier.isPrivate(modifier)) {
				Field[] ccFields = cc.getDeclaredFields();
				for (int j = 0; j < ccFields.length; j++) {
					Field field = ccFields[j];
					int ccModifier = field.getModifiers();
					if (Modifier.isFinal(ccModifier) && !Modifier.isPrivate(ccModifier)) {
						try {
							lookupConstant.put(cc.getSimpleName() + "." + field.getName(), field.get(lookupConstant));
						} catch (IllegalAccessException e) {

						}
					}
				}
			}
		}
		Field[] fields = c.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			int modifier = field.getModifiers();
			if (Modifier.isFinal(modifier) && !Modifier.isPrivate(modifier)) {
				try {
					lookupConstant.put(field.getName(), field.get(lookupConstant));
				} catch (IllegalAccessException e) {

				}
			}
		}
		ctx.getServletContext().setAttribute("lookupConstant", lookupConstant);
	}

	private void initializeSystemConstant(WebApplicationContext ctx) {
		Map<String, Object> systemConstant = new HashMap<String, Object>();
		Class c = SystemConstant.class;
		Class[] classes = c.getDeclaredClasses();
		for (int i = 0; i < classes.length; i++) {
			Class cc = classes[i];
			int modifier = cc.getModifiers();
			if (Modifier.isFinal(modifier) && !Modifier.isPrivate(modifier)) {
				Field[] ccFields = cc.getDeclaredFields();
				for (int j = 0; j < ccFields.length; j++) {
					Field field = ccFields[j];
					int ccModifier = field.getModifiers();
					if (Modifier.isFinal(ccModifier) && !Modifier.isPrivate(ccModifier)) {
						try {
							systemConstant.put(cc.getSimpleName() + "." + field.getName(), field.get(systemConstant));
						} catch (IllegalAccessException e) {

						}
					}
				}
			}
		}
		Field[] fields = c.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			int modifier = field.getModifiers();
			if (Modifier.isFinal(modifier) && !Modifier.isPrivate(modifier)) {
				try {
					systemConstant.put(field.getName(), field.get(systemConstant));
				} catch (IllegalAccessException e) {

				}
			}
		}
		ctx.getServletContext().setAttribute("systemConstant", systemConstant);
	}

	private void initializeBeanUtils() {
		LOGGER.info("Start initializing beanutils... ");
		BeanUtilsConfigurer.configure();
		LOGGER.info("Finish initializing beanutils... ");
	}

	private void initializeAppParameter(WebApplicationContext ctx) {
		IAppParameterService appParameterService = (IAppParameterService) ctx.getBean("appParameterService");
		LoaderFactory.initializeAppParameter(appParameterService);

	}

	private void initializeAuditTrailParameter(WebApplicationContext ctx) {
		ILookupService lookupService = (ILookupService) ctx.getBean("lookupService");
		LoaderFactory.initializeAuditTrailParameter(lookupService);
	}

}
