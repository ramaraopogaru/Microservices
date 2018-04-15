package com.nareshit.service;

import java.util.List;

import com.nareshit.bean.DoctorBean;


public interface DoctorService {

	public DoctorBean addDoctor(DoctorBean doctorBean);

	public boolean deleteDoctor(int doctorId);

	public boolean activeDoctor(int doctorId);

	public boolean inactiveDoctor(int doctorId);

	public List<DoctorBean> getAllDoctors();

	public List<DoctorBean> searchDoctor(int doctorId, String firstName, String lastName, String email,
			long phoneNumber, String status);

	public DoctorBean getDoctorById(int doctorId);
	
	public DoctorBean getDoctorByEmailId(String email);
	
}
