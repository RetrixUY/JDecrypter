/*
 * Copyright (C) 2018 Neogex Studios
 * 
 * Author: Agustin Rojas
 *
 */
package uy.neogex.jdecrypter.Model;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

public class EncryptedData{
	
	private String key = "";
	private String data;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public EncryptedData(HttpServletRequest request) {
		char[] k = request.getParameter("jk").split("\'")[1].toCharArray();
		this.data = request.getParameter("crypted");
		for (int i = 0;i <k.length;i++){
			this.key += k[i];
		}
	}

	public List<Link> Decrypt() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		String key = getKey();
		String data = getData();
		List<Link> resultURLs = new ArrayList<>();
		Cipher decryptCipher = Cipher.getInstance("AES/CBC/NoPadding");
		decryptCipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(hexToByteArray(key), "AES"), new IvParameterSpec(hexToByteArray(key)));
        String[] results;
        results = new String(decryptCipher.doFinal(Base64.getDecoder().decode(data))).split("\n");
        int count = 0;
        for (String result : results) {
            result = result.trim();
            if (result.trim().length() > 0) {
            	count++;
            	resultURLs.add(new Link(count,result));
            }
        }
        return resultURLs;
	
}
	private static byte[] hexToByteArray(String s) {
		 final int len = s.length();
	        byte[] data = new byte[len / 2];
	        for (int i = 0; i < len; i += 2) {
	            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
	        }
	        return data;
	}
}