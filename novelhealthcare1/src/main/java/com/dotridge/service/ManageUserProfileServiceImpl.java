package com.dotridge.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dotridge.bean.TokenBean;
import com.dotridge.dao.ManageUserProfileDao;
import com.dotridge.domain.Token;
import com.dotridge.util.DateUtil;
import com.dotridge.util.ServiceConstants;

@Service
public class ManageUserProfileServiceImpl implements ManageUserProfileService {

	@Autowired
	private ManageUserProfileDao profileDao;
	
	public TokenBean generateToken(TokenBean tokenBean) {
		Token tokenDomain = profileDao.createToken(mapBeanToDomain(tokenBean));
		tokenBean = mapDomainToBean(tokenDomain);
		
		return tokenBean;
	}
	
	private Token mapBeanToDomain(TokenBean tb){
		Token token = new Token();
		token.setToken(tb.getToken());
		Date creatDate = DateUtil.getDateFromString(tb.getTokenCreationTime());
		token.setTokenCreationTime(creatDate);
		System.out.println("created date in registration is:\t"+creatDate);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(creatDate);
		System.out.println("calender date is:\t"+cal.getTime());
		cal.add(Calendar.HOUR_OF_DAY, ServiceConstants.tokenValidityInHours);
		/*System.out.println("year is:\t"+year);
		cal.set(year, creatDate.getMonth(), creatDate.getDate(), 
				creatDate.getHours()+ServiceConstants.tokenValidityInHours, 
				creatDate.getMinutes(),
				creatDate.getSeconds());*/
		System.out.println(cal.getTime());
		token.setTokenExpTime(cal.getTime());
		
		return token;
	}

	private TokenBean mapDomainToBean(Token token){
		TokenBean tb = new TokenBean();
		tb.setToken(token.getToken());
		tb.setTokenCreationTime(DateUtil.getStringFromDate(token.getTokenCreationTime()));
		tb.setTokenExpTime(DateUtil.getStringFromDate(token.getTokenExpTime()));
		return tb;
	}

	public TokenBean getTokenByTokenKey(String tokenKey) {
       Token domain = profileDao.getTokenByTokenKey(tokenKey);
		return mapDomainToBean(domain);
	}
}
