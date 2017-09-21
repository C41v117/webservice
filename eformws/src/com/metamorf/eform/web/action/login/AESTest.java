package com.metamorf.eform.web.action.login;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AESTest {
	public static void main(String[] args) {
		
		try {
			StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
			String realUser = "eformService";
			String realPass = "wowbtpneform123";
			String eUser = encryptor.encryptPassword(realUser);
			String ePass = encryptor.encryptPassword(realPass);
			System.out.println("user: " + eUser );
			System.out.println("pass: " + ePass );
			
			System.out.println("is user: " + encryptor.checkPassword(realUser, eUser));
			System.out.println("is pass: " + encryptor.checkPassword(realPass, ePass));
			
//			AESBouncyCastle abc = new AESBouncyCastle();
//			 
//			String user = abc.encrypt("eformService");
//			String pass = abc.encrypt("wowbtpneform123");
//			System.out.println("user: " + user);
//			System.out.println("pass: " + pass);
//			
//			String realUser = abc.decrypt(user);
//			String realPass = abc.decrypt(pass);
//			
//			System.out.println("real user: " + realUser);
//			System.out.println("real pass: " + realPass);
			
//			byte[] ba = user.getBytes("UTF-8");
//			
//			byte[] encr = abc.encrypt(ba);
//			System.out.println("Encrypted : "
//			    + Hex.toHexString(encr));
//			byte[] retr = abc.decrypt(Hex.decode(SystemParameter.WSDL_BASIC_AUTH_USERNAME));
//			 
//			if ( retr.length == ba.length ) {
//			    ba = retr;
//			} else {
//			    System.arraycopy(retr, 0, ba, 0, ba.length);
//			}
//			 
//			String decrypted = new String(ba, "UTF-8");
//			System.out.println(decrypted);
			
			
			
			
			
//			AESUtil2 aes = new AESUtil2();
//			System.out.println("Encrypted username string:" + AESUtil.encrypt("eformService"));
//			System.out.println("Encrypted password string:" + AESUtil.encrypt("wowbtpneform123"));
//	        String encryptedText = AESUtil.encrypt("eformService");
//	        System.out.println("Decrypted string:" + AESUtil.decrypt(encryptedText));
//			String encrypt = aes.encrypt("eformService");
//			String realUsername = aes.decrypt(SystemParameter.WSDL_BASIC_AUTH_USERNAME);
//			String realPassword = aes.decrypt(SystemParameter.WSDL_BASIC_AUTH_PASSWORD);
//			System.out.println("username: " + realUsername);
//			System.out.println("password: " + realPassword);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println();
			throw new UsernameNotFoundException("Error encrypt or decrypt text!");
		}         
	}
}
