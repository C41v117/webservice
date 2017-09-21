package com.metamorf.eform.data.access.core;

import java.io.Serializable;
import java.lang.reflect.Field;

import org.hibernate.criterion.LikeExpression;

public class CriteriaUtil {
	public static final char[] RESERVED_KEYWORD = new char[]{'_'};
	
	public static final char ESCAPE_KEYWORD = '\\';
	public static final String ESCAPE_KEYWORD_STRING = "\\\\";
	
	public static class EscapingLikeExpression extends LikeExpression{
		
		private static final long serialVersionUID = 8551027267237634381L;

		public EscapingLikeExpression(String propertyName, String value){
			super(propertyName, escapeString(value), ESCAPE_KEYWORD, false);
		}
		
		static String escapeString(String inputString) {
			String _inputString = inputString;
			for (char rkey : RESERVED_KEYWORD) {
				_inputString = _inputString.replaceAll(Character.toString(rkey), ESCAPE_KEYWORD_STRING+rkey);
			}
			return "%".concat(_inputString).concat("%");
		}	
	}
	
	public static class CustomLikeExpression implements Serializable{
		private static final long serialVersionUID = -4816629011481734920L;
		
		private LikeExpression le;
		
		public CustomLikeExpression(LikeExpression le) {
			this.le = le; 
		}
		
		@Override
		public String toString() {
			String escape = " escape '" + CriteriaUtil.RESERVED_KEYWORD[0] + "'";
			String column = null;
			try {
				Class c = le.getClass();
				Field f1 = c.getDeclaredField("propertyName");
				f1.setAccessible(true);
				column = (String)f1.get(le);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			if(column!=null){
				return "lcase" + '(' + column + ')' + " like ? " + escape;
			}else{
				return "";
			}
		}
	}
}
