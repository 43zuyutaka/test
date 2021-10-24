package com.github.y43zu.util;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import javax.xml.bind.DatatypeConverter;

/**
 * JDK6でcommons-codecなどを使わずにBASE64を使うためのutil
 * 
 * @author 43zu
 *
 */
public class BASE64Util {
	
	public static String encode(byte[] bytes) {
		String result = null;
		try {
			result = DatatypeConverter.printBase64Binary(bytes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	public static byte[] decode(String str) {
		byte[] result = null;
		try {
			result = DatatypeConverter.parseBase64Binary(str);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}


	public static void main(String[] args) {
		System.out.println(Arrays.toString( decode(encode("BASE64エンコードデコード試行".getBytes()))));
		System.out.println(new String( decode(encode("BASE64エンコードデコード試行".getBytes()))));
		
	}

}
