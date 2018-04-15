package com.nareshit.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Hospital implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -557606317496045415L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int hospId;
	
	private String hospName;

	public int getHospId() {
		return hospId;
	}

	public void setHospId(int hospId) {
		this.hospId = hospId;
	}

	public String getHospName() {
		return hospName;
	}

	public void setHospName(String hospName) {
		this.hospName = hospName;
	}
	

	
}
