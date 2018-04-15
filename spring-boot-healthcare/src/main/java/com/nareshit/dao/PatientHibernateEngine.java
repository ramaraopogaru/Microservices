package com.nareshit.dao;

import com.nareshit.domain.Patient;

public interface PatientHibernateEngine {
	public Patient addPatient(Patient pat);
	public Patient getPatientById(int patId);
}
