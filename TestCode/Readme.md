# JDK6とJDK8のAES鍵相互運用

### ○前提
 * JDK8からkeytoolの仕様が変わっていて、キーストアのバイナリに互換性がない。
 * JDK8はPKCS12という形式だが、JDK6はJCEKSになっている。
　このため、JDK6ではJDK8のキーストアをJavaから利用できず、keytoolコマンドでJDK6のキーストアにインポートもできない。
 * 試した結果、JDK6で作った鍵を、JDK8のkeytoolでPKCS12形式にインポートすることはできた。
　（JDK8のkeytoolでJDK6用のJCEKSファイルへインポートもできたが、鍵が微妙に変化して暗号化・復号化が失敗した）
 * JDK6はAES鍵長は128bit、JDK8も途中のバージョンまで標準128bit。

### ○JDK6とJDK8でのAES鍵の共有方法
 1. JDK6のkeytoolでAES鍵を作り、キーストアに格納する
 1. JDK8のkeytoolでJDK6のキーストアから、JDK8のキーストアに鍵をインポートする


* JDK6で鍵作成　コマンド例
~~~
> keytool -genseckey -storetype jceks -keyalg AES -keysize 128 -keystore legacyKeyStore.jceks -storepass password -alias jdk6key -keypass password
> keytool -list -keystore legacyKeyStore.jceks -storetype jceks -storepass password
~~~
 * JDK8でインポート　コマンド例
~~~
> keytool -list -keystore myKeyStore.pkcs12 -storetype pkcs12 -storepass password
> keytool -importkeystore -v -srckeystore legacyKeyStore.jceks -destkeystore myKeyStore.pkcs12 -srcstoretype jceks -deststoretype pkcs12 -srcstorepass password -deststorepass password -srcalias jdk6key -destalias jdk6key -srckeypass password -destkeypass password
~~~

### ○参考サイト
* [JDK6リファレンス](https://docs.oracle.com/javase/jp/6/)
* [JDK6リファレンス：セキュリティ](https://docs.oracle.com/javase/jp/6/technotes/guides/security/index.html)
* [AES使えるよ](https://docs.oracle.com/javase/jp/6/technotes/guides/security/SunProviders.html)
* [共通かぎはストアタイプはjceksしか無理](https://stackoverflow.com/questions/6656263/why-do-i-get-the-error-cannot-store-non-privatekeys-when-creating-an-ssl-socke)
　→keytoolに常に -storetype jceks の指定が必要
* JDK8でpkcs12の暗号化アルゴリズムが強化されたせいで、JDK7以前のキーストアと互換性がなくなった模様。
  * https://www.java.com/ja/download/help/release_changes.html
  * https://bugs.openjdk.java.net/browse/JDK-8153005
* [JDK6でpkcs12を使いたい人のQA](https://stackoverflow.com/questions/13860939/create-pkcs12-certificate-in-java)
* [JDK6と８のキーストア相互移動のスレッド](https://www.cnblogs.com/mindwind/p/5193155.html)

#### 余談
 * JDK6はBase64クラスがないが、代替手段はある。
 * String.getbytes()は引数なしの場合、システムのデフォルトエンコーディングが使われる。文字化けのもと。おそらく指定が必須。
 * byte[]を無理やりStringにする（new String(byte[])）と、指定した文字コード範囲に含まれない値があると勝手に変換されてしまうらしい。（＝なんの文字コードを指定しようと、微妙にもとのバイト列から変わってしまう可能性がある。）暗号化したbyte[]を復号化する間に、一度意味もなくStringを挟んでいて、バイト列が変わってしまうことで復号に失敗し、ハマった。

