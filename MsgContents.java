package com.emrsys.medmatrix.util;

public class MsgContents {

	//入力した医師IDが数字ではない
	public static final String IDNONUM = "正しい数字の医師IDを入力してください。";

	//入力した医師IDかパスワードが一致しない
	public static final String LOGINFAILUER = "正しい医師IDかパスワードを入力してください";

	//入力した医師ID登録されてない
	public static final String REGISTRATENASHI = "該当医師IDが登録されていません";

	//権限がないので、ログインできません
	public static final String STATUSCHECK = "権限がないので、ログインできません";

	//入力した医師ID登録されてない
	public static final String DOCNO = "no";

	//入力した医師ID登録されてる
	public static final String DOCYES = "ok";

	//医師IDとパスワード一致する
	public static final String CHECKTRUE = "true";

	//医師IDとパスワード一致しない
	public static final String CHECKFALSE = "false";
}
