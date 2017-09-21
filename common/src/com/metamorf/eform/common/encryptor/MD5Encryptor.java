package com.metamorf.eform.common.encryptor;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Encyrptor with MD5 approach
 * @author eRyan
 */
public class MD5Encryptor implements PasswordEncryptor {

	public String encrypt(String password) {
		MessageDigest md = null;
		byte[] digest = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
		if (md==null) {
			return "";
		} else {
			md.reset();
			md.update(password.getBytes());
			digest = md.digest();
			return dumpBytes(digest);
		}
	}

	private static String dumpBytes(byte[] bytes) {

		int size = bytes.length;
		StringBuffer sb = new StringBuffer(size * 2);
		String s;

		for (int i = 0; i < size; i++) {

			s = Integer.toHexString(bytes[i]);

			if (s.length() == 8) // -128 <= bytes[i] < 0
				sb.append(s.substring(6));
			else if (s.length() == 2) // 16 <= bytes[i] < 128
				sb.append(s);
			else
				sb.append("0").append(s); // 0 <= bytes[i] < 16
		}

		return sb.toString();
	}
}
