package com.dotridge.dao;

import java.util.List;

import com.dotridge.domain.Doctor;

public interface DoctorDao 
{
	public Doctor createDoctor(Doctor doctorDomain);
	
	public Doctor updateDoctor(Doctor doctorDomain);
	
	public Doctor getDoctorById(int id);
	
	public List<Doctor> getDoctorByName(String firstName);
	
	public List<Doctor> getDoctorByEmail(String email);
	
	public List<Doctor> getDoctorByPhone(long phoneNumber);
	
	public List<Doctor> getDoctorByStatus(Boolean status);
	
	public List<Doctor> getAllDoctors();
	
	public boolean deleteDoctor(int id);
	
	public List<Doctor> getAllDoctorsByPagining(int currentPage, int numberOfRecords);
}
