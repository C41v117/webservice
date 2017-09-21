package com.metamorf.eform.common.util;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

/**
 * @author kismanhong
 *
 */
public class VelocityUtils {
	
	private static VelocityUtils instance = null;

	public static synchronized VelocityUtils getInstance() {
		if (instance == null) {
			instance = new VelocityUtils();
		}
		return instance;
	}

	public String merge(String template, HashMap<String, Object> params) throws Exception{
		Velocity.init();
		VelocityContext context = new VelocityContext();
		for (Iterator<?> iterator = params.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			context.put(key, params.get(key));
		}
		StringWriter w = new StringWriter();
        Velocity.evaluate( context, w, "email", template );
        return w.toString();
	}
	
//	public String mergeObject(String template, HashMap<String, Object> params) throws Exception{
//		Velocity.init();
//		VelocityContext context = new VelocityContext();
//		for (Iterator<?> iterator = params.keySet().iterator(); iterator.hasNext();) {
//			String key = (String) iterator.next();
//			context.put(key, params.get(key));
//		}
//		StringWriter w = new StringWriter();
//        Velocity.evaluate( context, w, "email", template );
//        return w.toString();
//	}
}
