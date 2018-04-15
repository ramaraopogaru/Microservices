package com.nareshit.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nareshit.bean.PatientBean;
import com.nareshit.dao.PatientDao;
import com.nareshit.domain.Patient;
import com.nareshit.util.ServiceUtil;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientDao patientDao;

	@Override
	public PatientBean addPatient(PatientBean patientBean) {

		Patient patient = mapToDomain(patientBean);
		patient = patientDao.addPatient(patient);
		patientBean = mapToBean(patient);
		return patientBean;
	}

	@Override
	public boolean deletePatient(int patientId) {
		boolean flag = false;
		flag = patientDao.deletePatient(patientId);
		return flag;

	}

	@Override
	public boolean activePatient(int patientId) {
		boolean flag = false;
		flag = patientDao.activePatient(patientId);
		return flag;
	}

	@Override
	public boolean inactivePatient(int patientId) {
		boolean flag = false;
		flag = patientDao.inactivePatient(patientId);
		return flag;
	}

	@Override
	public List<PatientBean> getAllPatients() {

		List<Patient> patientDomains = patientDao.getAllPatients();
		List<PatientBean> patientBeans = listOfDomainsToBeans(patientDomains);
		return patientBeans;
	}

	@Override
	public List<PatientBean> searchPatient(int patientId, String firstName, String lastName, String email,
			long phoneNumber, String status) {

		List<Patient> patientDomains = patientDao.searchPatient(patientId, firstName, lastName, email, phoneNumber,
				status);
		List<PatientBean> patientBeans = listOfDomainsToBeans(patientDomains);

		return patientBeans;
	}

	@Override
	public PatientBean getPatientById(int patientId) {

		Patient patient = patientDao.getPatientById(patientId);
		return mapToBean(patient);
	}

	private PatientBean mapToBean(Patient patient) {
		PatientBean patientBean = new PatientBean();
		patientBean.setPatientId(String.valueOf(patient.getPatientId()));
		patientBean.setFirstName(patient.getFirstName());
		patientBean.setLastName(patient.getLastName());
		patientBean.setMiddleName(patient.getMiddleName());
		if(patient.getBirthDate() != null)
		patientBean.setBirthDate(patient.getBirthDate().toString());
		patientBean.setAddress(patient.getAddress());
		patientBean.setBloodGroup(patient.getBloodGroup());
		patientBean.setPhoneNumber(String.valueOf(patient.getPhoneNumber()));
		patientBean.setGender(patient.getGender());
		patientBean.setEmail(patient.getEmail());
		patientBean.setPassword(patient.getPassword());
		patientBean.setStatus(String.valueOf(patient.isStatus()));
		patientBean.setCreatedBy(patient.getCreatedBy());
		if(patient.getCreatedDate() != null)
		patientBean.setCreatedDate(ServiceUtil.getStringFromDate(patient.getCreatedDate()));
		return patientBean;

	}

	private Patient mapToDomain(PatientBean patientBean) {
		Patient patient = new Patient();
		if (patientBean.getFirstName() != null && !patientBean.getFirstName().isEmpty()) {
			patient.setFirstName(patientBean.getFirstName());
		}
		if (patientBean.getLastName() != null && !patientBean.getLastName().isEmpty()) {
			patient.setLastName(patientBean.getLastName());
		}
		if (patientBean.getMiddleName() != null && !patientBean.getMiddleName().isEmpty()) {
			patient.setMiddleName(patientBean.getMiddleName());
		}
		if (patientBean.getEmail() != null && !patientBean.getEmail().isEmpty()) {
			patient.setEmail(patientBean.getEmail());
		}
		if (patientBean.getPassword() != null && !patientBean.getPassword().isEmpty()) {
			patient.setPassword(patientBean.getPassword());
		}
		if (patientBean.getBirthDate() != null && !patientBean.getBirthDate().isEmpty()) {
			patient.setBirthDate(ServiceUtil.getDateFromString(patientBean.getBirthDate()));
		}
		if (patientBean.getAddress() != null && !patientBean.getAddress().isEmpty()) {
			patient.setAddress(patientBean.getAddress());
		}
		if (patientBean.getGender() != null && !patientBean.getGender().isEmpty()) {
			patient.setGender(patientBean.getGender());
		}
		if (patientBean.getBloodGroup() != null && !patientBean.getBloodGroup().isEmpty()) {
			patient.setBloodGroup(patientBean.getBloodGroup());
		}
		if (patientBean.getPhoneNumber() != null && !patientBean.getPhoneNumber().isEmpty()) {
			patient.setPhoneNumber(Long.valueOf(patientBean.getPhoneNumber()));
		}
		if (patientBean.getStatus() != null && !patientBean.getStatus().isEmpty()) {
			patient.setStatus(Boolean.valueOf(patientBean.getStatus()));
		}
		if (patientBean.getCreatedBy() != null && !patientBean.getCreatedBy().isEmpty()) {
			patient.setCreatedBy(patientBean.getCreatedBy());
		}
		if (patientBean.getCreatedDate() != null && !patientBean.getCreatedDate().isEmpty()) {
			patient.setCreatedDate(ServiceUtil.getDateFromString(patientBean.getCreatedDate()));
		}
		
		return patient;

	}

	private List<PatientBean> listOfDomainsToBeans(List<Patient> patientDomains) {
		List<PatientBean> patientBeans = new ArrayList<PatientBean>();
		if (patientDomains != null && !patientDomains.isEmpty()) {
			Iterator iterator = patientDomains.iterator();
			while (iterator.hasNext()) {
				Patient patient = (Patient) iterator.next();
				patientBeans.add(mapToBean(patient));
			}
		}
		return patientBeans;

	}

}
