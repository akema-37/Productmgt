package com.rosy.sample;

public class UserAuthBean {
	public UserAuthBean() {

	}

	// executeメソッドにUserBeanのインスタンスを与えると認証をします
	// 認証の結果を戻り値で返します
	public boolean execute(UserBean ub) {
		if (ub.getaName().equals("akema") == true
				&& ub.getPassword().equals("akema") == true) {
			return true;
		} else {
			return false;
		}
	}

}
