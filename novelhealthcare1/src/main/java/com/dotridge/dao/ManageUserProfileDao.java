package com.dotridge.dao;

import com.dotridge.domain.Token;

public interface ManageUserProfileDao {

	public Token createToken(Token token);
	public Token getTokenByTokenKey(String token);
}
