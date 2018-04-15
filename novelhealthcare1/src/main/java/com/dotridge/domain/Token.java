package com.dotridge.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Token {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int tokenId;
	private String token;
	@Temporal(TemporalType.TIMESTAMP)
	private Date tokenExpTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date tokenCreationTime;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public Date getTokenExpTime() {
		return tokenExpTime;
	}
	public void setTokenExpTime(Date tokenExpTime) {
		this.tokenExpTime = tokenExpTime;
	}
	public Date getTokenCreationTime() {
		return tokenCreationTime;
	}
	public void setTokenCreationTime(Date tokenCreationTime) {
		this.tokenCreationTime = tokenCreationTime;
	}
	public int getTokenId() {
		return tokenId;
	}
	public void setTokenId(int tokenId) {
		this.tokenId = tokenId;
	}
	
}
