package com.dotridge.service;

import com.dotridge.bean.TokenBean;

public interface ManageUserProfileService {

	public TokenBean generateToken(TokenBean tokenBean);
	public TokenBean getTokenByTokenKey(String tokenKey);
}
