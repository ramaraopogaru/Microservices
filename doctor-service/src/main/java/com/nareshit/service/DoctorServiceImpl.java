package com.nareshit.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nareshit.bean.DoctorBean;
import com.nareshit.dao.DoctorDao;
import com.nareshit.domain.Doctor;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private DoctorDao doctorDao;

	@Override
	public DoctorBean addDoctor(DoctorBean doctorBean) {
		Doctor doctor = mapToDomain(doctorBean);
		doctor = doctorDao.addDoctor(doctor);
		doctorBean = mapToBean(doctor);
		return doctorBean;
	}

	@Override
	public boolean deleteDoctor(int doctorId) {
		boolean flag = false;
		flag = doctorDao.deleteDoctor(doctorId);
		return flag;

	}

	@Override
	public boolean activeDoctor(int doctorId) {
		boolean flag = false;
		flag = doctorDao.activeDoctor(doctorId);
		return flag;
	}

	@Override
	public boolean inactiveDoctor(int doctorId) {
		boolean flag = false;
		flag = doctorDao.inactiveDoctor(doctorId);
		return flag;
	}

	@Override
	public List<DoctorBean> getAllDoctors() {
		List<Doctor> doctorDomains = doctorDao.getAllDoctors();
		List<DoctorBean> doctorBeans = listOfDomainsToBeans(doctorDomains);
		return doctorBeans;
	}

	@Override
	public List<DoctorBean> searchDoctor(int doctorId, String firstName, String lastName, String email,
			long phoneNumber, String status) {

		List<Doctor> doctorDomains = doctorDao.searchDoctor(doctorId, firstName, lastName, email, phoneNumber, status);

		List<DoctorBean> doctorBeans = listOfDomainsToBeans(doctorDomains);

		return doctorBeans;
	}

	@Override
	public DoctorBean getDoctorById(int doctorId) {
		Doctor doctor = doctorDao.getDoctorById(doctorId);
		return mapToBean(doctor);
	}

	private DoctorBean mapToBean(Doctor doctor) {
		DoctorBean doctorBean = new DoctorBean();
		doctorBean.setDoctorId(String.valueOf(doctor.getDoctorId()));
		doctorBean.setFirstName(doctor.getFirstName());
		doctorBean.setLastName(doctor.getLastName());
		doctorBean.setMiddleName(doctor.getMiddleName());
		doctorBean.setPhoneNumber(String.valueOf(doctor.getPhoneNumber()));
		doctorBean.setEmail(doctor.getEmail());
		doctorBean.setPassword(doctor.getPassword());
		doctorBean.setStatus(String.valueOf(doctor.isStatus()));
		return doctorBean;

	}

	private Doctor mapToDomain(DoctorBean doctorBean) {
		Doctor doctor = new Doctor();
		if (doctorBean.getFirstName() != null && !doctorBean.getFirstName().isEmpty()) {
			doctor.setFirstName(doctorBean.getFirstName());
		}
		if (doctorBean.getLastName() != null && !doctorBean.getLastName().isEmpty()) {
			doctor.setLastName(doctorBean.getLastName());
		}
		if (doctorBean.getMiddleName() != null && !doctorBean.getMiddleName().isEmpty()) {
			doctor.setMiddleName(doctorBean.getMiddleName());
		}
		if (doctorBean.getEmail() != null && !doctorBean.getEmail().isEmpty()) {
			doctor.setEmail(doctorBean.getEmail());
		}
		if (doctorBean.getPassword() != null && !doctorBean.getPassword().isEmpty()) {
			doctor.setPassword(doctorBean.getPassword());
		}

		if (doctorBean.getPhoneNumber() != null && !doctorBean.getPhoneNumber().isEmpty()) {
			doctor.setPhoneNumber(Long.valueOf(doctorBean.getPhoneNumber()));
		}
		if (doctorBean.getStatus() != null && !doctorBean.getStatus().isEmpty()) {
			doctor.setStatus(Boolean.valueOf(doctorBean.getStatus()));
		}

		return doctor;

	}

	private List<DoctorBean> listOfDomainsToBeans(List<Doctor> doctorDomains) {
		List<DoctorBean> doctorBeans = new ArrayList<DoctorBean>();
		if (doctorDomains != null && !doctorDomains.isEmpty()) {
			Iterator iterator = doctorDomains.iterator();
			while (iterator.hasNext()) {
				Doctor doctor = (Doctor) iterator.next();
				doctorBeans.add(mapToBean(doctor));
			}
		}
		return doctorBeans;

	}

	@Override
	//@Transactional
	public DoctorBean getDoctorByEmailId(String email) {
		// TODO Auto-generated method stub
		Doctor doc = doctorDao.getDoctorByEmail(email);
		return mapToBean(doc);
	}

}
