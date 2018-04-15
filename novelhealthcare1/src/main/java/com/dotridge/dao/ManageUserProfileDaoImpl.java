package com.dotridge.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dotridge.domain.Token;

@Repository
public class ManageUserProfileDaoImpl implements ManageUserProfileDao {

	@Autowired
	private SessionFactory sf;
	
	public Token createToken(Token token) {
		Session ses = sf.openSession();
		ses.save(token);
		return token;
	}

	public Token getTokenByTokenKey(String token) {
		Session ses = sf.openSession();
		Query q = ses.createQuery("from Token where token=:tokenKey");
		q.setParameter("tokenKey", token);
		Token tokenDomain = (Token)q.uniqueResult();
		return tokenDomain;
	}

}
