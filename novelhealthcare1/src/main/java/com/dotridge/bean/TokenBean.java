package com.dotridge.bean;

public class TokenBean {

	private String token;
	private String tokenExpTime;
	private String tokenCreationTime;
	private UserProfileBean upb;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getTokenExpTime() {
		return tokenExpTime;
	}
	public void setTokenExpTime(String tokenExpTime) {
		this.tokenExpTime = tokenExpTime;
	}
	public String getTokenCreationTime() {
		return tokenCreationTime;
	}
	public void setTokenCreationTime(String tokenCreationTime) {
		this.tokenCreationTime = tokenCreationTime;
	}
	public UserProfileBean getUpb() {
		return upb;
	}
	public void setUpb(UserProfileBean upb) {
		this.upb = upb;
	}
}
