package com.rosy.sample;

import java.io.Serializable;

/**
 *  認証情報保持クラス
 *  ログイン画面で入力された認証情報を保持するクラスです。
 *  @version 1.1
 *  @author akema
 */
public class UserBean implements Serializable {

	// コンストラクタ
	public UserBean() {
		setaName("No Name");
		setAddress("No address");
	}

	private String userId;

	private String name;

	private String password;

	private String address;

	public String getId() {
		return userId;
	}

	public void setId(String id) {
		this.userId = id;
	}

	public String getaName() {
		return name;
	}

	public void setaName(String na) {
		name = na;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String ps) {
		password = ps;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String adr) {
		address = adr;
	}

}
