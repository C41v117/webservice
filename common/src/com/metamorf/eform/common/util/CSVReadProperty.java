package com.metamorf.eform.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;



public class CSVReadProperty {

	/**
	 * @param args
	 */

	
	
	public CSVReadProperty() {
		
	}
	public File canReadFile (String fileName) throws IOException {
		
		if (StringUtils.isNotEmpty(fileName)) {
			File file = new File(fileName);
			if (file.exists()){
				if (!file.canRead()){
					throw new IOException("Could not open " + fileName);
				}
			} else {
				throw new IOException("File does not exists: " + fileName);
			}
			return file;
		} else {
			throw new IOException("File name is empty: " + fileName);
		}
	}
	
	public List<Property> getListProperty (File file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		List<Property> propertyList = new ArrayList<Property>();
		try{
	        String line;
	        ResourceBundle rb = ResourceBundle.getBundle("resources");
		    while((line = br.readLine()) != null) {
		    	Property property = new Property();
	
	            if (StringFunction.isNotEmpty(line)) {
	            	String[] array = StringFunction.split(line, "|");
	            	
	            	if (array.length == 2) {
	            		property.setTitle(rb.getString(array[0]));
	            		property.setName(array[1]);
	            		property.setAnnotation(Property.view);
	            		propertyList.add(property);
	            		
	            	} else if (array.length == 3) {
	            		property.setTitle(rb.getString(array[0]));
	            		property.setName(array[1]);
	            		property.setFormat(array[2]);
	            		property.setAnnotation(Property.view);
	            		propertyList.add(property);
	            		
	            	} else {
	            		throw new IOException("Line format does not exist" + line);
	            	}
	            	
	            }
	        }
		}finally{
		    if(br!=null){
		    	br.close();
		    }
		}
	    return propertyList;
	}
	public void printCSV(File fileConfig,OutputStream out, List printList) throws IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ParseException {
			
		CSVUtil csvUtil = new CSVUtil(out);
			
		List<Property> propertyList = getListProperty (fileConfig);
			
	    if (printList != null) {
			NumberFormat fmt;
	        
	       	for (Property p : propertyList) {
	        	csvUtil.print(p.getTitle());
        	}
	       	csvUtil.println();
	        for (Object object : printList) {
	        		
	        	for (Property p : propertyList) {
	        	
	        		Object value =null;
	        		String dataType= "String";
	        		
	        		try {
	        			value = PropertyUtils.getProperty(object, p.getName());
	        			dataType = PropertyUtils.getPropertyDescriptor(object, p.getName()).getPropertyType().toString();
	        		} catch (Exception e) {
	        			
	        		}
	        		if (value!=null) {
	        			if (StringFunction.isNotEmpty(p.getFormat())) {
		        			
		        			if (dataType.contains("Date")) {
		        				csvUtil.print(DateTimeFunction.date2String((Date)value, p.getFormat()));
		        			} else if (dataType.contains("Double") || dataType.contains("Long") || dataType.contains("BigDecimal")) {
		        				fmt = new DecimalFormat(p.getFormat());
		        				csvUtil.print(fmt.format(value));
		        			} else {
		        				csvUtil.print(value.toString());
		        			}
		        		} else {
		        			csvUtil.print(value.toString());
		        		}
	        		}
	        		else {
	        			csvUtil.print("");
	        		}
	        	}
	        	csvUtil.println();
	      	}
	        	
	    }
	
	}
	
	class Property {
		public final static int view = 1;
		
		private String title;
		private String name;
		private int annotation;
		private String format;

		public Property() {
			
		}
		public Property(String title, String name) {
			this.title = title;
			this.name = name;
		}
		
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		public void setAnnotation(int annotation) {
			this.annotation = annotation;
		}
		
		public void setAnnotation(String s) {
			this.annotation = toInt(s);
		}
		
		public String getFormat() {
			return format;
		}
		public void setFormat(String format) {
			this.format = format;
		}
		public String getName() {
			return name;
		}
	
		public int getAnnotation() {
			return annotation;
		}
		
		public int toInt (String s) {
			try {
				return Integer.parseInt(s);
			} catch (Exception e) {
				return -1;
			}
		}
	}
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}

}
