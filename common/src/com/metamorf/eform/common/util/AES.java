package com.metamorf.eform.common.util;

import java.security.Key;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.mapping.Array;

public class AES
{
	private static final String ALGORITHM = "AES";
    private static final byte[] keyValue = "ADBSJHJS12547896".getBytes();
	private static final byte[] keyValueApiGateway = "APIGATEWAY123456".getBytes();
    private static Key keys;
    
    public static String encryptString(String valueToEnc) throws Exception {

        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, keys);

        byte[] encValue = c.doFinal(valueToEnc.getBytes());
//        System.out.println("encValue length" + encValue.length);
        byte[] encryptedByteValue = new Base64().encode(encValue);
        String encryptedValue = new String(encryptedByteValue);
//        System.out.println("encryptedValue " + encryptedValue);

        return encryptedValue;
    }

    public static String decryptString(String encryptedValue) throws Exception {
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.DECRYPT_MODE, keys);
        byte[] decodedValue = new Base64().decode(encryptedValue.getBytes());
		byte[] decryptedVal = c.doFinal(decodedValue);
        
        return new String(decryptedVal);
    }
    
    public static Key generateKey() throws Exception {
        keys = new SecretKeySpec(keyValue, ALGORITHM);
        return keys;
    }

	public static Key generateKeyAPIGateway() throws Exception {
		keys = new SecretKeySpec(keyValueApiGateway, ALGORITHM);
		return keys;
	}
	
	public static  void main (String[] args) {
		try {
			generateKey();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String a[] = {"Revamp751"
				,"Revamp752"
				,"Revamp753"
				,"Revamp754"
				,"Revamp755"
				,"Revamp756"
				,"Revamp757"
				,"Revamp758"
				,"Revamp759"
				,"Revamp760"
				,"Revamp761"
				,"Revamp762"
				,"Revamp763"
				,"Revamp764"
				,"Revamp765"
				,"Revamp766"
				,"Revamp767"
				,"Revamp768"
				,"Revamp769"
				,"Revamp770"
				,"Revamp771"
				,"Revamp772"
				,"Revamp773"
				,"Revamp774"
				,"Revamp775"
				,"Revamp776"
				,"Revamp777"
				,"Revamp778"
				,"Revamp779"
				,"Revamp780"
				,"Revamp781"
				,"Revamp782"
				,"Revamp783"
				,"Revamp784"
				,"Revamp785"
				,"Revamp786"
				,"Revamp787"
				,"Revamp788"
				,"Revamp789"
				,"Revamp790"
				,"Revamp791"
				,"Revamp792"
				,"Revamp793"
				,"Revamp794"
				,"Revamp795"
				,"Revamp796"
				,"Revamp797"
				,"Revamp798"
				,"Revamp799"
				,"Revamp800"};
		try {
			for (int i= 0 ; i<a.length ; i++) {
				System.out.println(a[i] + "    "+encryptString(a[i]));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
     
}