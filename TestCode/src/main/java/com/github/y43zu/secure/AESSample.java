package com.github.y43zu.secure;
import com.github.y43zu.secure.*;
public class AESSample {

	private static String encryptedPKCS12Str = "+UinVnZAYK0wM3c45rIf8C2ExR9E1GiKMceSpteoUcw=";
	private static String encryptedJCEKSStr = "NYvjotml+LJH+V+17Q7NmDnlKDTPk445KsQ3pl5g4Y0=";

	private static String encryptedJDEKS_jdk6key = "aLnd4U/wqMTpzH9IaXCHobG7XaYSnWa/gRfAmwYWlyA=";
	private static String encryptedPKCS12Str_jdk8key = "6m5SEx6MuK5AeRInySz5EhoCuVQ+FIlxCppFI18c8sI=";
	private static String encryptedJCEKSStr_jdk8key2 = "eTIft7SUkgQYjYWi6rM18bO1TdJGHI6iUKzSFfQvGB8=";
			
	/**
	 * keystore:legacyKeyStore.jceks alias:jdk6key �ňÍ�������������
	 * legacyKeyStore.jceks�̍쐬�R�}���h�@JDK6�Ŏ��{
	 *�@�@�@keytool -genseckey -storetype jceks -keyalg AES -keysize 128 -keystore legacyKeyStore.jceks -storepass password -alias jdk6key -keypass password
	 *�@�@�@keytool -list -keystore legacyKeyStore.jceks -storetype jceks -storepass password
	 * �쐬��������JDK8�ňȉ��̂悤��myKeyStore.pkcs12�ɃC���|�[�g
	 * �@�@keytool -list -keystore myKeyStore.pkcs12 -storetype pkcs12 -storepass password
	 * �@�@keytool -importkeystore -v -srckeystore legacyKeyStore.jceks -destkeystore myKeyStore.pkcs12 -srcstoretype jceks -deststoretype pkcs12 -srcstorepass password -deststorepass password -srcalias jdk6key -destalias jdk6key -srckeypass password -destkeypass password
	 *�@�@�@
	 */

	public static void main(String[] args) {

//		testJDK8();
//		testJDK8_decriptFromJdk6key();
//		testJDK8_encriptFromJdk8key();
		testJDK8_decriptFromJdk8key2();

//		testJDK6_decriptFromJdk8key();
//		testJDK6();
	}
//
//	private static void testJDK8() {
//		String str = AESUtil.encryptAES("�Í����Ώۂ̕�����", "myKeyStore.pkcs12", "key1");
//		System.out.println(str);
//		
//		str = AESUtil.decryptAES(str, "myKeyStore.pkcs12", "key1");
//		System.out.println("�Í���&BASE64������F" + str);
//		
//		System.out.println("===");
//		
//		str = AESUtil.encryptAES4JDK6("�Í����Ώۂ̕�����", "myKeyStore.jceks", "key1");
//		System.out.println(str);
//		
//		str = AESUtil.decryptAES4JDK6(str, "myKeyStore.jceks", "key1");
//		System.out.println(str);
//	}
//
//	private static void testJDK8_decriptFromJdk6key() {
//		System.out.println("�Í����ςݕ�����F" + encryptedJDEKS_jdk6key);
//		String str = AESUtil.decryptAES(encryptedJDEKS_jdk6key, "myKeyStore.pkcs12", "jdk6key");
//		System.out.println("�Í����ςݕ�����F" + str);
//	}
//	
//	private static void testJDK8_encriptFromJdk8key() {
//		System.out.println("�Í����ςݕ�����F" );
//		String str = AESUtil.encryptAES("�Í����Ώۂ̕�����", "myKeyStore.pkcs12", "jdk8key");
//		System.out.println("�Í����ςݕ�����F" + str);
//		str = AESUtil.decryptAES(encryptedPKCS12Str_jdk8key, "myKeyStore.pkcs12", "jdk8key");
//		System.out.println("��������������F" + str);
//	}
	
	/**
	 * JDK8 keytool�ō��������JDK6�p��jceks�L�[�X�g�A�ɃC���|�[�g���āAJDK8�ňÍ�������������̕�������JDK6�ł����B
	 * �@JDK8�Ō��쐬
	 * keytool -genseckey -storetype pkcs12 -keyalg AES -keysize 128 -keystore myKeyStore.pkcs12 -storepass password -alias jdk8key -keypass password
	 * keytool -list -keystore myKeyStore.pkcs12 -storetype pkcs12 -storepass password
	 * �@JDK8��JDK6���ɃC���|�[�g
	 * keytool -importkeystore -v -srckeystore myKeyStore.pkcs12 -destkeystore legacyKeyStore.jceks -srcstoretype pkcs12 -deststoretype jceks -srcstorepass password -deststorepass password -srcalias jdk8key -destalias jdk8key -srckeypass password -destkeypass password
	 * keytool -list -keystore legacyKeyStore.jceks -storetype jceks -storepass password
	 */
	private static void testJDK6_decriptFromJdk8key() {
		System.out.println("�Í����ςݕ�����F" );
		String str = AESUtilforJDK6.encryptAES("�Í����Ώۂ̕�����", "legacyKeyStore.jceks", "jdk8key");
		System.out.println("�Í����ςݕ�����F" + str);
		str = AESUtilforJDK6.decryptAES(encryptedPKCS12Str_jdk8key, "legacyKeyStore.jceks", "jdk8key");
		System.out.println("��������������F" + str);
	}
	
	/**
	 * JDK8 keytool�ō��������JDK6�p��jceks�L�[�X�g�A�ɃC���|�[�g���āAJDK6�ňÍ�������������̕�������JDK8�ł����B
	 * �@JDK8�Ō��쐬
	 * keytool -genseckey -storetype pkcs12 -keyalg AES -keysize 128 -keystore myKeyStore.pkcs12 -storepass password -alias jdk8key2 -keypass password
	 * keytool -list -keystore myKeyStore.pkcs12 -storetype pkcs12 -storepass password
	 * �@JDK8��JDK6���ɃC���|�[�g
	 * keytool -importkeystore -v -srckeystore myKeyStore.pkcs12 -destkeystore legacyKeyStore.jceks -srcstoretype pkcs12 -deststoretype jceks -srcstorepass password -deststorepass password -srcalias jdk8key2 -destalias jdk8key2 -srckeypass password -destkeypass password
	 * keytool -list -keystore legacyKeyStore.jceks -storetype jceks -storepass password
	 */
	private static void testJDK8_decriptFromJdk8key2() {
		System.out.println("�Í����ςݕ�����F" );
		String str = AESUtil.encryptAES("�Í����Ώۂ̕�����", "myKeyStore.pkcs12", "jdk8key2");
		System.out.println("�Í����ςݕ�����F" + str);
		str = AESUtil.decryptAES(encryptedJCEKSStr_jdk8key2, "myKeyStore.pkcs12", "jdk8key2");
		System.out.println("��������������F" + str);
	}

	/**
	 * JDK6��AES�T���v���B���s����JDK6�ɕύX���Ď�����B���s����ۂɂ͂ق��̃N���X��JDK6�Ŏg���Ȃ��N���X�𗘗p���Ă���̂ŁA�ꎞ�I�ɃR�����g�A�E�g����
	 */
	private static void testJDK6() {
		System.out.println("JDK6�p��AES�Í����T���v��");

		String crypted = AESUtilforJDK6.encryptAES("�Í����Ώۂ̕�����", "myKeyStore.jceks", "key1");
		System.out.println("�Í���&BASE64������F" + crypted);
		System.out.println("---");

		String plain = AESUtilforJDK6.decryptAES(crypted, "myKeyStore.jceks", "key1");
		System.out.println("������̕�����F" + plain);

		System.out.println("===");
		System.out.println("");

		System.out.println("���̓��K�V�[�L�[�X�g�A");

		crypted = AESUtilforJDK6.encryptAES("�Í����Ώۂ̕�����", "legacyKeyStore.jceks", "jdk6key");
		System.out.println("�Í���&BASE64������F" + crypted);
		System.out.println("---");

		plain = AESUtilforJDK6.decryptAES(crypted, "legacyKeyStore.jceks", "jdk6key");
		System.out.println("������̕�����F" + plain);

		System.out.println("===");
		System.out.println("");

//		System.out.println("PKCS12�ňÍ�������������F"+encryptedPKCS12Str);
//		try {
//			plain = AESUtilforJDK6.decryptAES(encryptedPKCS12Str, "myKeyStore.jceks", "key1");
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println("jcekes�ňÍ�������������F"+encryptedJCEKSStr);
//		try {
//			plain = AESUtilforJDK6.decryptAES(encryptedJCEKSStr, "myKeyStore.jceks", "key1");
//		}catch(Exception e) {
//			e.printStackTrace();
//		}

	}

}
