package com.metamorf.eform.common.util;

import com.metamorf.eform.common.core.SystemParameter;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;


public class EmailFunction {
	//for checking single email address
	public static boolean isAddressValid(String emailAddress) {
		String addr = StringFunction.trim(emailAddress);
		try {
			new InternetAddress(addr, SystemParameter.STRICT_EMAIL_ADDRESS);
		} catch (AddressException e) {
			return false;
		}
		return true;
	}
	
	/*for checking multiple email addresses
	first parameter emailAddress is string object with value multiple email address that is separate by delimiter, for example: "email1@mail.com;email2@mail.com;email3@mail.com"
	second parameter is delimiter, the above example the delimiter is (;), if second parameter is null, then use default(;)
	return true if all email addresses are valid otherwise return false*/
	public static boolean isAddressesValid(String emailAddresses, String delimiter) {
		String _emailAddresses = StringFunction.trim(emailAddresses);
		String _delimiter = delimiter;
		if(StringFunction.checkString(_delimiter)==null){
			_delimiter = ";"; //default
		}
		String addrs[] = StringFunction.split(_emailAddresses, _delimiter);
		if(addrs.length>0){
			for (String emailAddress : addrs) {
				String addr = StringFunction.trim(emailAddress);
				try {
					new InternetAddress(addr, SystemParameter.STRICT_EMAIL_ADDRESS);
				} catch (AddressException e) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	public static boolean isAddressesValid(String emailAddresses) {
		String _delimiter = ";";
		return isAddressesValid(emailAddresses, _delimiter);
	}
	
	/*for checking multiple email addresses and only returning valid email address with (;) delimiter
	first parameter emailAddress is string object with value multiple email address that is separate by delimiter, for example: "email1@mail.com;email2@mail.com;email3@mail.com"
	second parameter is delimiter, the above example the delimiter is (;), if second parameter is null, then use default(;)*/
	public static String getValidAddresses(String emailAddresses, String delimiter) {
		StringBuilder validAddreses = new StringBuilder();
		String _emailAddresses = StringFunction.trim(emailAddresses);
		String _delimiter = delimiter;
		if(StringFunction.checkString(_delimiter)==null){
			_delimiter = ";"; //default
		}
		String addrs[] = StringFunction.split(_emailAddresses, _delimiter);
		if(addrs.length>0){
			for (String emailAddress : addrs) {
				String addr = StringFunction.trim(emailAddress);
				try {
					new InternetAddress(addr, SystemParameter.STRICT_EMAIL_ADDRESS);
					validAddreses.append(addr).append(";");
				} catch (AddressException e) {
					//not throwing error
				} catch (Exception e) {
					//not throwing error
				}
			}
			if(validAddreses.indexOf(";")>0){
				validAddreses.replace(validAddreses.length()-1, validAddreses.length(), "");
			}
		}
		return validAddreses.toString();
	}
	
	public static String getValidAddresses(String emailAddresses) {
		String _delimiter = ";";
		return getValidAddresses(emailAddresses, _delimiter);
	}
	
	/*split email addresses, invalid email address will be excluded*/
	public static String[] splitEmailAddresses(String emailAddresses, String delimiter) {
		String _delimiter = delimiter;
		if(StringFunction.checkString(_delimiter)==null){
			_delimiter = ";"; //default
		}
		String validAddresses = getValidAddresses(emailAddresses, delimiter);
		return StringFunction.split(validAddresses, _delimiter);
	}
	
	public static String[] splitEmailAddresses(String emailAddresses) {
		String _delimiter = ";";
		return splitEmailAddresses(emailAddresses, _delimiter);
	}
}
