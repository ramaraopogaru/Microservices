package com.dotridge.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.dotridge.bean.DoctorBean;
import com.dotridge.domain.Doctor;

public interface DoctorService 
{
	public Doctor mapBeanToDomain(DoctorBean doctorBean);
	
	public DoctorBean mapDomainToBean(Doctor doctorDomain);
	
	public DoctorBean createDoctor(DoctorBean doctorBean);
	
	public DoctorBean updateDoctor(DoctorBean doctorBean);
	
	public DoctorBean getDoctorById(int id);
	
	public List<DoctorBean> getDoctorByName(String doctorFirstName);
	
	public List<DoctorBean> getDoctorByEmail(String email);
	
	public List<DoctorBean> getDoctorByPhone(long phoneNumber);
	
	public List<DoctorBean> getDoctorByStatus(Boolean status);
	
	
	@PreAuthorize("hasPermission('doctor','view')")
	public List<DoctorBean> getAllDoctors();
	
	public boolean deleteDoctor(int id);
	
	public List<DoctorBean> getAllDoctorsByPagining(int currentPage, int numberOfRecords);
}
