package com.metamorf.eform.common.util;

import com.metamorf.eform.common.core.SystemConstant;

public class PhoneNumberUtil {
	private static StringBuilder builder;
	
	public static String convert(String phoneNumber){
		builder = new StringBuilder();
		builder.append(phoneNumber.substring(1));
		Character prefix = phoneNumber.charAt(0);
		String result = null;
		for(int i = 0; i < SystemConstant.PhoneNumberPrefix.PREFIX.length; i++){
			if(prefix.equals(SystemConstant.PhoneNumberPrefix.PREFIX[i])){
				result = builder.insert(0, SystemConstant.PhoneNumberPrefix.mobileNumberMap.get(SystemConstant.PhoneNumberPrefix.PREFIX[i])).toString();
				break;
			}
		}
		
		return result;
	}
}
