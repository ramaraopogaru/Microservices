package com.dotridge.dao;

import java.util.List;

import com.dotridge.domain.Hospital;

public interface HospitalDao 
{
	public Hospital createHospital(Hospital hospital);
	
	public Hospital updateHospital(Hospital hospital);
	
	public Hospital getHospitalById(int id);
	
	public Hospital getHospitalByUniqueName(String hospitalName);
	
	public List<Hospital> getHospitalByName(String hospitalName);
	
	public List<Hospital> getHospitalByEmail(String email);
	
	public List<Hospital> getHospitalByAddress1(String address1);
	
	public List<Hospital> getHospitalByPhone(long phoneNumber);
	
	public List<Hospital> getHospitalByStatus(Boolean status);
	
	public List<Hospital> getAllHospitals();
	
	public boolean deleteHospital(int id);
	
	public List<Hospital> getAllHospitalsByPagining(int currentPage, int numberOfRecords);
}
