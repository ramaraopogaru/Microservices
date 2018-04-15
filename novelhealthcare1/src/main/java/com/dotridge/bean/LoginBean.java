package com.dotridge.bean;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class LoginBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	//@NotEmpty(message="userId is required")
	@Min(value=4)
	@Max(value=30)
	private String userId;
	private String password;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginBean [userId=" + userId + ", password=" + password + "]";
	}
	
}
