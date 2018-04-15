package com.nareshit.dao;

import java.util.List;

import com.nareshit.domain.Doctor;



public interface DoctorDao {

	
	public Doctor addDoctor(Doctor doctor);
	
	public boolean deleteDoctor(int doctorId);
	
	public boolean activeDoctor(int doctorId);

	public boolean inactiveDoctor(int doctorId);
	
	public List<Doctor> getAllDoctors();

	public List<Doctor> searchDoctor(int doctorId, String firstName, String lastName, String email, long phoneNumber,
			String status);

	public Doctor getDoctorById(int doctorId);
	
	public Doctor getDoctorByEmail(String email);
}
