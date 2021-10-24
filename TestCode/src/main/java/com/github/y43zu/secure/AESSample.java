package com.github.y43zu.secure;

public class AESSample {

	private static String encryptedPKCS12Str = "+UinVnZAYK0wM3c45rIf8C2ExR9E1GiKMceSpteoUcw=";
	private static String encryptedJCEKSStr = "NYvjotml+LJH+V+17Q7NmDnlKDTPk445KsQ3pl5g4Y0=";

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
	private static String encryptedJDEKS_jdk6key = "aLnd4U/wqMTpzH9IaXCHobG7XaYSnWa/gRfAmwYWlyA=";

	public static void main(String[] args) {
//		testJDK6();
//		testJDK8();
		testJDK8_decriptFromJdk6key();
	}

	private static void testJDK8() {
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
	}

	private static void testJDK8_decriptFromJdk6key() {
		System.out.println("�Í����ςݕ�����F" + encryptedJDEKS_jdk6key);
		String str = AESUtil.decryptAES(encryptedJDEKS_jdk6key, "myKeyStore.pkcs12", "jdk6key");
		System.out.println("�Í����ςݕ�����F" + str);
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
