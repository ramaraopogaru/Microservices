package com.nareshit.service;

import java.util.List;

import com.nareshit.bean.PatientBean;

public interface PatientService {

	
	public PatientBean addPatient(PatientBean patient);

	public boolean deletePatient(int patientId);

	public boolean activePatient(int patientId);

	public boolean inactivePatient(int patientId);

	public List<PatientBean> getAllPatients();

	public List<PatientBean> searchPatient(int patientId, String firstName, String lastName, String email, long phoneNumber,
			String status);

	public PatientBean getPatientById(int patientId);
}
