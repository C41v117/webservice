package com.metamorf.eform.common.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLFunction {
	private static final Logger LOGGER = LoggerFactory.getLogger(XMLFunction.class);
	
	public static Document safeParse(String xmlString){
        try {
        	DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            builderFactory.setNamespaceAware(true);
			builderFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
			builderFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
	        builderFactory.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
	        DocumentBuilder builder = builderFactory.newDocumentBuilder();
	        return builder.parse(new InputSource(new ByteArrayInputStream(xmlString.getBytes())));
		} catch (ParserConfigurationException e) {
			LOGGER.error(e.getMessage());
		} catch (SAXException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		return null;
	}
}
