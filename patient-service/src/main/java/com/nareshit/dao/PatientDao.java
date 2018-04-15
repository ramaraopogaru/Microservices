package com.nareshit.dao;

import java.util.List;

import com.nareshit.domain.Patient;

public interface PatientDao {

	public Patient addPatient(Patient patient);

	public boolean deletePatient(int patientId);

	public boolean activePatient(int patientId);

	public boolean inactivePatient(int patientId);

	public List<Patient> getAllPatients();

	public List<Patient> searchPatient(int patientId, String firstName, String lastName, String email, long phoneNumber,
			String status);

	public Patient getPatientById(int patientId);

}
