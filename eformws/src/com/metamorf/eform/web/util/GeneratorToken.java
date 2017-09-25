package com.metamorf.eform.web.util;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class GeneratorToken {
	
	private static char[] digits = {'0', '1', '2', '3', '4','5','6','7','8','9','Z','M','Y','G','O','X'};

    private static Integer placeSplit = 6;
    private static Integer splitCount = placeSplit+1;
	
  
    
    private static long nowMillis = System.currentTimeMillis();
    Date now = new Date(nowMillis);
    

    
	public static String TokenGenerate(Long id) throws Exception{
		Integer temp = placeSplit;
		
		StringBuilder result = new StringBuilder();
		SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
		String randomNum = new Integer(prng.nextInt()).toString();
		
		MessageDigest sha = MessageDigest.getInstance("SHA-1");
		byte[] aInput =  sha.digest(randomNum.getBytes());
			for (int idx = 0; idx < aInput.length; ++idx) {
				if(idx % 2 == 0){
					byte b = aInput[idx];
					result.append(digits[ (b&0xf0) >> 4 ]);
					result.append(digits[ b&0x0f]);
					if(result.length() % temp == 0 && idx+1 < aInput.length){
						result.append("-");
						temp += splitCount;
					}
				}
			}
		if(result.substring(result.length())!= "-"){
			result.delete(result.toString().lastIndexOf('-') + 1, result.length());
			
		}
		result.append(appendToken(id));
		result.append("-");
		result.append(System.currentTimeMillis());
		String token = result.toString();
		
		//50 -> token length in user database
		token = token.length() > 50 ? token.substring(0,49) : token;
		return token;
	}
	
	private static String appendToken (Long id) throws Exception{
		StringBuilder result = new StringBuilder();
		
		Date current = Calendar.getInstance().getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern("dd");
		String day = sdf.format(current);
		sdf.applyPattern("MM");
		String month = sdf.format(current);
		sdf.applyPattern("yy");
		String year = sdf.format(current);
		String date = day.concat(month).concat(year);
		result.append(date);
		result.append("-");
		result.append(String.valueOf(id));
		 
		return result.toString();
	}
	
	 public static String generateSecretClient(){
		SecretKey secretKey = null;
		try {
			secretKey = KeyGenerator.getInstance("AES").generateKey();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		String encoded = DatatypeConverter.printBase64Binary(secretKey.getEncoded());
		return encoded; 
	}
	 
	 
	 public static String generateClientId(){
		return UUID.randomUUID().toString().replace("-", "");
	 }
	 
	public static String createToken(String userName, String clientSecret) {
		long ttlMillis = Long.parseLong("5000000000");
	    long expMillis = nowMillis + ttlMillis;
	    Date exp = new Date(expMillis);
	    
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(clientSecret);
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
	    
		String token = Jwts.builder().setId(userName).signWith(signatureAlgorithm, signingKey).setExpiration(exp)
				.compact();
		return token;
	}
	
	public static Boolean verifyToken(String token, String clientSecret, String userName){
		Claims claims = Jwts.parser()         
			       .setSigningKey(DatatypeConverter.parseBase64Binary(clientSecret))
			       .parseClaimsJws(token).getBody();
//			    System.out.println("ID: " + claims.getId());
//			    System.out.println("Subject: " + claims.getSubject());
//			    System.out.println("Expiration: " + claims.getExpiration());
			    
				    
		if(userName.equalsIgnoreCase(claims.getId())){
			return true;
		}else{
			return false;
		}
		
	}

}
