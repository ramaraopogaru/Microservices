package com.dotridge.bean;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.dotridge.bean.LoginBean;

@Component
public class LoginValidator implements Validator
{
	public boolean supports(Class<?> clazz)
	{
		return clazz.equals(LoginBean.class);
	}

	public void validate(Object target, Errors errors) 
	{
		LoginBean loginBean = (LoginBean) target;
		errors.rejectValue("userId", "userId.required.loginBean.userId", new Object[]{loginBean.getUserId()},"userId is required" );
	}

}
