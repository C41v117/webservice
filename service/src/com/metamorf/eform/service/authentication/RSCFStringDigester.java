package com.metamorf.eform.service.authentication;

import org.jasypt.digest.StringDigester;

public class RSCFStringDigester implements StringDigester {
	StringDigester digester;
	
	public RSCFStringDigester(StringDigester digester) {
		this.digester = digester;
	}
	
	/**
	 * Trim the digest result since jasypt 1.6 produces /r/n at 
	 * the end of the digested text (or is it the common-codec?)
	 * @param text
	 * @return
	 */
	public String digest(String text) {
		return digester.digest(text).trim();
	}

	@Override
	public boolean matches(String string1, String string2) {
		return digester.matches(string1, string2);
	}
	
}
