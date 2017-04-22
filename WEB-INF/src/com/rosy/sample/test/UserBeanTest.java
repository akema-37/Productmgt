package com.rosy.sample.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rosy.sample.UserBean;

/**
 *  認証情報保持クラスのテストケース
 *  ログイン画面で入力された認証情報を保持するクラスのテストケースです。
 *  @version 1.1
 *  @author akema
 */
public class UserBeanTest {


	/**
	 *  UserBean()メソッドのテストケース
	 *  Nameが設定されていなかった場合、No nameという値を出力する
	 *  input(入力値)：なし
	 *  expected(出力想定値)：名前：No name
	 *                      ：アドレス：No Address
	 */
	@Test
	public void testUserBean() {
		// テスト対象クラスをインスタンス化
		UserBean target = new UserBean();

		// getName()でUserBeanの変数 name に入っている値をを取得する
		String name = target.getaName();

		// getAddress()でUserBeanの変数 address に入っている値をを取得する
		String address = target.getAddress();

		// かっこの中の2つを比較して中身が一緒ならtrue、そうでなければfalseとなる。
		assertEquals("No Name", name);
		// assertEquals(出力予想値, 出力結果);という風に書くのが一般的(反対でもエラーにはならない)
		assertEquals("No address", address);
	}




	/**
	 *  UserBean()メソッドのテストケース
	 *  Nameが設定されていた際、No Nameでは無く設定した値が出力されることを確認する。
	 *  input(入力値)：名前：須田あゆみ
	 *  expected(出力想定値)：須田あゆみ
	 */
	@Test
	public void testUserBean2() {
		// テスト対象クラスをインスタンス化
		UserBean target = new UserBean();

		// UserBeanの変数nameに"須田あゆみ"をセット(UserBeanの中のNo nameが上書きされる)
		target.setaName("須田あゆみ");

		// 変数 actual(実際の値)に UserBeanのnameからgetした値を入れる
		String actual = target.getaName();

		// 想定値と、実際の値を、アサートイコールで比較する
		assertEquals("須田あゆみ", actual);
	}
}
