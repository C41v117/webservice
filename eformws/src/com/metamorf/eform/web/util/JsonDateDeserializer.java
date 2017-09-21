package com.metamorf.eform.web.util;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class JsonDateDeserializer implements JsonDeserializer<Date> {
	public Date deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		String s = json.getAsJsonPrimitive().getAsString();
		
		Date d = null;
		try {
			d = DateUtils.parseDefaultDate(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}
}
