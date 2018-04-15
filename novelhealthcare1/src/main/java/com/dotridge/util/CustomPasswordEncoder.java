package com.dotridge.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


public class CustomPasswordEncoder {


	public static String encodePassword(String password) {
		byte[] tokenSign = encryptPassword(password);
		
		String token = Base64.getEncoder().encodeToString(tokenSign);
		
		return token;
	}
	
	private static byte[] encryptPassword(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			
			byte[] hash =  md.digest();
			
			MessageDigest sha1MD = MessageDigest.getInstance("SHA-1");
			sha1MD.update(hash);
			
			byte[] sha1Bytes =  sha1MD.digest();
			
			return sha1Bytes;
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/*public static void main(String[] args) {
		System.out.println(CustomPasswordEncoder.encodePassword("john"));
		System.out.println(CustomPasswordEncoder.encodePassword("john"));
	}*/
}
