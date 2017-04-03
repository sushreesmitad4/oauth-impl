package com.omnypay.common.services.crypt;

import java.security.MessageDigest;

public class ShahHashEncoder {
	/**
	 * Encode sha hash.
	 * 
	 * @param str  the str
	 * @return the string
	 * @throws Exception the exception
	 */
	public static String encodeShaHash(String str) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.update(str.getBytes());
		byte byteData[] = md.digest();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}
}
