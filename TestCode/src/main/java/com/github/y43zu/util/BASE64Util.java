package com.github.y43zu.util;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import javax.xml.bind.DatatypeConverter;

/**
 * JDK6��commons-codec�Ȃǂ��g�킸��BASE64���g�����߂̃��b�p�[�B�񐄏����\�b�h�g�p�B
 * 
 * @author shimi
 *
 */
public class BASE64Util {

//	public static String encode(String str) {
//		sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
//		byte[] bytes = null;
//		try {
//			System.out.println("encode UTF-8:" + new String(str.getBytes("UTF-8")));
//			System.out.println("encode SJIS:" + new String(str.getBytes("SJIS")));
//			System.out.println("encode -:" + new String(str.getBytes()));
//			
//			//getBytes()�͈����Ȃ����ƃV�X�e���f�t�H���g�G���R�[�h�Ŏ擾�����̂ŁA���ӁB�w�肪�K�{
//			bytes = str.getBytes();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return encoder.encode(bytes);
//	}
//
//	public static String decode(String str) {
//		byte[] decodeResult = null;
//		String result = "";
//		try {
//			decodeResult = new sun.misc.BASE64Decoder().decodeBuffer(str);
//			System.out.println("decode UTF-8:" + new String(decodeResult, "UTF-8"));
//			System.out.println("decode SJIS:" + new String(decodeResult, "SJIS"));
//			System.out.println("decode -:" + new String(decodeResult));
//			result = new String(decodeResult);
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//		return result;
//	}
	
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
		System.out.println(Arrays.toString( decode(encode("BASE64�G���R�[�h�f�R�[�h���s".getBytes()))));
		System.out.println(new String( decode(encode("BASE64�G���R�[�h�f�R�[�h���s".getBytes()))));
		
	}

}
