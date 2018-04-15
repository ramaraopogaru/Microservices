package com.dotridge.util;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dotridge.dao.ManageUserProfileDao;
import com.dotridge.domain.Token;

@Component
public class TokenValidator {

	@Autowired
	private ManageUserProfileDao profileDao;
	public boolean isTokenValidated(String token,Date currDate){
		boolean isValid = false;
		
		Token dbToken = profileDao.getTokenByTokenKey(token);
		
		Calendar currCal = Calendar.getInstance();
		currCal.setTime(currDate);
		
		System.out.println("current time is:\t"+currCal.getTime());
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(dbToken.getTokenExpTime());

		System.out.println("exp time is:\t"+cal.getTime());
		isValid = currCal.before(cal);
		System.out.println("is token valid:\t"+isValid);
		
		return isValid;
	}
}
