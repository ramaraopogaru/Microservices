package com.nareshit.bean;

import java.io.Serializable;

public class PatientBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
private int pId;
	
	private String firstName;
	private String middleName;
	private String lastName;
	
	private String email;
	
	
	public int getpId() {
		return pId;
	}


	public void setpId(int pId) {
		this.pId = pId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getMiddleName() {
		return middleName;
	}


	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	private String password;
	

	
}
