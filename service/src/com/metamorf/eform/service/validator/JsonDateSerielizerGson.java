package com.metamorf.eform.service.validator;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


public class JsonDateSerielizerGson implements JsonSerializer<Date>{

	@Override
	public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
		return src == null ? null :new JsonPrimitive(src.getTime());
	}


}
