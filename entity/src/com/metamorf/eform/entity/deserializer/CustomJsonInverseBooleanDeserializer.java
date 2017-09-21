package com.metamorf.eform.entity.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomJsonInverseBooleanDeserializer extends JsonDeserializer<Boolean> {

	@Override
	public Boolean deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		String booleanParser = jp.getText();
		// since outsystem field is active and this is used for deleted
		if (booleanParser.equals("True") || booleanParser.equals("true") || booleanParser.equals("1")) {
			return false;
		}
		return true;
	}

}
