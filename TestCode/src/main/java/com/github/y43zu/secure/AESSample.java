package com.github.y43zu.secure;

public class AESSample {

	private static String encryptedPKCS12Str = "+UinVnZAYK0wM3c45rIf8C2ExR9E1GiKMceSpteoUcw=";
	private static String encryptedJCEKSStr = "NYvjotml+LJH+V+17Q7NmDnlKDTPk445KsQ3pl5g4Y0=";

	/**
	 * keystore:legacyKeyStore.jceks alias:jdk6key で暗号化した文字列
	 * legacyKeyStore.jceksの作成コマンド　JDK6で実施
	 *　　　keytool -genseckey -storetype jceks -keyalg AES -keysize 128 -keystore legacyKeyStore.jceks -storepass password -alias jdk6key -keypass password
	 *　　　keytool -list -keystore legacyKeyStore.jceks -storetype jceks -storepass password
	 * 作成した鍵をJDK8で以下のようにmyKeyStore.pkcs12にインポート
	 * 　　keytool -list -keystore myKeyStore.pkcs12 -storetype pkcs12 -storepass password
	 * 　　keytool -importkeystore -v -srckeystore legacyKeyStore.jceks -destkeystore myKeyStore.pkcs12 -srcstoretype jceks -deststoretype pkcs12 -srcstorepass password -deststorepass password -srcalias jdk6key -destalias jdk6key -srckeypass password -destkeypass password
	 *　　　
	 */
	private static String encryptedJDEKS_jdk6key = "aLnd4U/wqMTpzH9IaXCHobG7XaYSnWa/gRfAmwYWlyA=";

	public static void main(String[] args) {
//		testJDK6();
//		testJDK8();
		testJDK8_decriptFromJdk6key();
	}

	private static void testJDK8() {
//		String str = AESUtil.encryptAES("暗号化対象の文字列", "myKeyStore.pkcs12", "key1");
//		System.out.println(str);
//		
//		str = AESUtil.decryptAES(str, "myKeyStore.pkcs12", "key1");
//		System.out.println("暗号化&BASE64文字列：" + str);
//		
//		System.out.println("===");
//		
//		str = AESUtil.encryptAES4JDK6("暗号化対象の文字列", "myKeyStore.jceks", "key1");
//		System.out.println(str);
//		
//		str = AESUtil.decryptAES4JDK6(str, "myKeyStore.jceks", "key1");
//		System.out.println(str);
	}

	private static void testJDK8_decriptFromJdk6key() {
		System.out.println("暗号化済み文字列：" + encryptedJDEKS_jdk6key);
		String str = AESUtil.decryptAES(encryptedJDEKS_jdk6key, "myKeyStore.pkcs12", "jdk6key");
		System.out.println("暗号化済み文字列：" + str);
	}

	/**
	 * JDK6のAESサンプル。実行環境をJDK6に変更して試せる。実行する際にはほかのクラスでJDK6で使えないクラスを利用しているので、一時的にコメントアウトする
	 */
	private static void testJDK6() {
		System.out.println("JDK6用のAES暗号化サンプル");

		String crypted = AESUtilforJDK6.encryptAES("暗号化対象の文字列", "myKeyStore.jceks", "key1");
		System.out.println("暗号化&BASE64文字列：" + crypted);
		System.out.println("---");

		String plain = AESUtilforJDK6.decryptAES(crypted, "myKeyStore.jceks", "key1");
		System.out.println("復号後の文字列：" + plain);

		System.out.println("===");
		System.out.println("");

		System.out.println("次はレガシーキーストア");

		crypted = AESUtilforJDK6.encryptAES("暗号化対象の文字列", "legacyKeyStore.jceks", "jdk6key");
		System.out.println("暗号化&BASE64文字列：" + crypted);
		System.out.println("---");

		plain = AESUtilforJDK6.decryptAES(crypted, "legacyKeyStore.jceks", "jdk6key");
		System.out.println("復号後の文字列：" + plain);

		System.out.println("===");
		System.out.println("");

//		System.out.println("PKCS12で暗号化した文字列："+encryptedPKCS12Str);
//		try {
//			plain = AESUtilforJDK6.decryptAES(encryptedPKCS12Str, "myKeyStore.jceks", "key1");
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println("jcekesで暗号化した文字列："+encryptedJCEKSStr);
//		try {
//			plain = AESUtilforJDK6.decryptAES(encryptedJCEKSStr, "myKeyStore.jceks", "key1");
//		}catch(Exception e) {
//			e.printStackTrace();
//		}

	}

}
