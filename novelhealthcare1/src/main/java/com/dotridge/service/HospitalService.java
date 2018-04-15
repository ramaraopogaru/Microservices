package com.dotridge.service;

import java.util.List;

import com.dotridge.bean.HospitalBean;
import com.dotridge.domain.Hospital;

public interface HospitalService 
{
	public Hospital mapBeanToDomain(HospitalBean hospitalBean);
	
	public HospitalBean mapDomainToBean(Hospital hospitalDomain);
	
	public HospitalBean createHospital(HospitalBean hospitalBean);
	
	public HospitalBean updateHospital(HospitalBean hospitalBean);
	
	public HospitalBean getHospitalById(int id);
	
	public List<HospitalBean> getHospitalByName(String hospitalName);
	
	public List<HospitalBean> getHospitalByEmail(String email);
	
	public List<HospitalBean> getHospitalByAddress1(String address1);
	
	public List<HospitalBean> getHospitalByPhone(long phoneNumber);
	
	public List<HospitalBean> getHospitalByStatus(Boolean status);
	
	public List<HospitalBean> getAllHospitals();
	
	public boolean deleteHospital(int id);
	
	public List<HospitalBean> getAllHospitalsByPagining(int currentPage, int numberOfRecords);
}
