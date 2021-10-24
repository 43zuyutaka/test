package com.github.y43zu.secure;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Key;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.xml.bind.DatatypeConverter;

import com.github.y43zu.util.BASE64Util;

public class AESUtilforJDK6 {
	private static final String ALGORITHM = "AES";
	private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
	private static final String KEYSTORE_PASS = "password";
	private static final String INITIAL_VECTOR = "7B3CB68DE1A8A187A7A84428FC83C5CD";
	private static final String KEYSTORE_TYPE ="JCEKS";
	private static final int KEY_SIZE = 128;
	
	/**
	 * AES暗号化
	 * @param prainText
	 * @param keyStoreName
	 * @param alias
	 * @return
	 */
	public static String encryptAES(String prainText, String keyStoreName, String alias) {
//		return doAESnoBase64(Cipher.ENCRYPT_MODE, prainText, keyStoreName, alias);
		return doAES(Cipher.ENCRYPT_MODE, prainText, keyStoreName, alias);
	}
	
	/**
	 * AES復号化
	 * @param cryptedString
	 * @param keyStoreName
	 * @param alias
	 * @return
	 */
	public static String decryptAES(String cryptedString, String keyStoreName, String alias) {
//		return doAESnoBase64(Cipher.DECRYPT_MODE, cryptedString, keyStoreName, alias);
		return doAES(Cipher.DECRYPT_MODE, cryptedString, keyStoreName, alias);
	}
	
	private static String doAES(int cyphermode, String src, String keyStoreName, String alias) {
		String dest = "";
		
	KeyStore keyStroe = loadKeystore(keyStoreName);
		
		try {
			Key key = keyStroe.getKey(alias, KEYSTORE_PASS.toCharArray());
			
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			byte[] ivByteArray = DatatypeConverter.parseHexBinary(INITIAL_VECTOR);
			IvParameterSpec ivParam = new IvParameterSpec(ivByteArray);
			
			cipher.init(cyphermode, key, ivParam);
			
			if(cyphermode == Cipher.ENCRYPT_MODE) {
				byte[] result = cipher.doFinal(src.getBytes());
				System.out.println("暗号化後：" + Arrays.toString(result));
				
				dest = new String(BASE64Util.encode(result));
				System.out.println("暗号化BASE64後:" + Arrays.toString(dest.getBytes()));

//				dest = Base64.getEncoder().encodeToString(result);
//				System.out.println("BASE64 encoder:" + Base64.getEncoder().encodeToString(result));
			} else if (cyphermode == Cipher.DECRYPT_MODE){
				System.out.println("暗号化BASE64解除前:" + Arrays.toString(src.getBytes()));
				
				byte[] result = BASE64Util.decode(src);
				System.out.println("暗号化BASE64解除後:" + Arrays.toString(result));
				
//				byte[] result = Base64.getDecoder().decode(src.getBytes(StandardCharsets.UTF_8));
//				System.out.println("BASE64 decoder:" + new String(Base64.getDecoder().decode(src.getBytes(StandardCharsets.UTF_8)),  "UTF-8"));

				dest = new String(cipher.doFinal(result));
				
			} else {
				throw new IllegalArgumentException();
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return dest;
	}
	
	private static String doAESnoBase64(int cyphermode, String src, String keyStoreName, String alias) {
		String dest = "";
		
	KeyStore keyStroe = loadKeystore(keyStoreName);
		
		try {
			Key key = keyStroe.getKey(alias, KEYSTORE_PASS.toCharArray());
			
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			byte[] ivByteArray = DatatypeConverter.parseHexBinary(INITIAL_VECTOR);
			IvParameterSpec ivParam = new IvParameterSpec(ivByteArray);
			
			cipher.init(cyphermode, key, ivParam);
			
			if(cyphermode == Cipher.ENCRYPT_MODE) {
				byte[] result = cipher.doFinal(src.getBytes());
				System.out.println("暗号化後：" + Arrays.toString(result));
				dest = new String(result, "ISO-8859-1");
				
//				dest = BASE64Util.encode(new String(result, "SJIS"));
//				System.out.println("暗号化BASE64後:" + Arrays.toString(dest.getBytes("SJIS")));

//				dest = Base64.getEncoder().encodeToString(result);
//				System.out.println("BASE64 encoder:" + Base64.getEncoder().encodeToString(result));
			} else if (cyphermode == Cipher.DECRYPT_MODE){
				System.out.println("暗号化BASE64解除前UTF-8:" + Arrays.toString(src.getBytes("UTF-8")));
				System.out.println("暗号化BASE64解除前UTF8:" + Arrays.toString(src.getBytes("UTF8")));
				System.out.println("暗号化BASE64解除前SJIS:" + Arrays.toString(src.getBytes("SJIS")));
				System.out.println("暗号化BASE64解除前ISO～:" + Arrays.toString(src.getBytes("ISO-8859-1")));
				System.out.println("暗号化BASE64解除前-:" + Arrays.toString(src.getBytes()));
				
//				byte[] result = BASE64Util.decode(src).getBytes();
//				System.out.println("暗号化BASE64解除後:" + Arrays.toString(result));
				
//				byte[] result = Base64.getDecoder().decode(src.getBytes(StandardCharsets.UTF_8));
//				System.out.println("BASE64 decoder:" + new String(Base64.getDecoder().decode(src.getBytes(StandardCharsets.UTF_8)),  "UTF-8"));

				dest = new String(cipher.doFinal(src.getBytes("ISO-8859-1")));
				
			} else {
				throw new IllegalArgumentException();
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return dest;
	}
	

	/*
	 * 初期化ベクトル作成に使う
	 */
	public static String createInitialVector() {
		SecureRandom random = new SecureRandom();
        byte[] ivBytes = new byte[16];
        random.nextBytes(ivBytes);
        return DatatypeConverter.printHexBinary(ivBytes);
	}
	
	private static KeyStore loadKeystore(String keystoreName) {
		String filepath = AESUtilforJDK6.class.getResource(keystoreName).getFile();
		File file = new File(filepath);
		KeyStore keyStore = null;
		FileInputStream is = null;
		try{
			is = new FileInputStream(file);
			keyStore = KeyStore.getInstance(KEYSTORE_TYPE);
			keyStore.load(is, KEYSTORE_PASS.toCharArray());
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally{
			try {
				is.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		
		return keyStore;
	}
	

}
