package com.hyo.smart.common.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import com.google.common.hash.Hashing;

public class DataEncryption {
	
	private static final String privateKey = "password16number";
	private static final String algorithm = "AES";
    private static final String blockNPadding = algorithm + "/CBC/PKCS5Padding";
    private static final String factoryKey = "PBKDF2WithHmacSHA256";
    private static String salt;
	private static int pswdIterations = 65536  ;
    private static int keySize = 256;
	private static int saltlength = keySize / 8;
	
	// SHA256.
	public String encSha256( String _plainText ) {
		System.out.println("# encSha256 #");
		return Hashing.sha256().hashString(_plainText, StandardCharsets.UTF_8).toString();
	}
	
	// AES256 Encryption.
	public String encAes256( String _plainText ) {	
		try {
			System.out.println("# encAes256 #");
	        SecretKeyFactory factory = SecretKeyFactory.getInstance(factoryKey);
	        KeySpec spec = new PBEKeySpec(privateKey.toCharArray(), salt.getBytes(), pswdIterations, keySize);
	        SecretKey secretKey = factory.generateSecret(spec);
	        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), algorithm);
	         
	        Cipher c = Cipher.getInstance(blockNPadding);
	        c.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(privateKey.getBytes()));
	       
	        return Base64.getEncoder().encodeToString(c.doFinal(_plainText.getBytes("UTF-8")));
		} catch (Exception e) {
			System.out.println("Encrypting Error > " + e.toString());
		}
		return null;
	}
	
	// AES256 Decryption.
	public String decAes256( String _encryptedText ) {	
		try {
			System.out.println("# decAes256 #");
	        SecretKeyFactory factory = SecretKeyFactory.getInstance(factoryKey);
	        KeySpec spec = new PBEKeySpec(privateKey.toCharArray(), salt.getBytes(), pswdIterations, keySize);
	        SecretKey secretKey = factory.generateSecret(spec);
	        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), algorithm);
	         
	        Cipher c = Cipher.getInstance(blockNPadding);
	        c.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(privateKey.getBytes()));
	       
	        return new String(c.doFinal(Base64.getDecoder().decode(_encryptedText)));
		} catch (Exception e) {
			System.out.println("Decrypting Error > " + e.toString());
		}
		return null;
	}
	
	public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[saltlength];
        random.nextBytes(bytes);
        return new String(bytes);
    }
		
	public static void main(String[] args) throws UnsupportedEncodingException {
		DataEncryption data = new DataEncryption();		
		
		salt = generateSalt();
		
		String plainText = "testStrData58722222222222222222222222222222229%Test@";
		// SHA256.
		String shaText = data.encSha256(plainText);
		System.out.println("[" + plainText + "] [" + shaText + "] [Len: " + shaText.length() + "]");
		
		// AES256 Encryption.
		String encText = data.encAes256(plainText);
		System.out.println("[" + plainText + "] [" + encText + "] [Len: " + encText.length() + "]");
		// AES256 Decryption.
		String decText = data.decAes256(encText);
		System.out.println("[" + encText + "] [" + decText + "] [Len: " + decText.length() + "]");
		
	}    
}
