package com.nareshit.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nareshit.domain.Patient;

@Repository
public class PatientHibernateEngineImpl implements PatientHibernateEngine{
	
	@Autowired
	private SessionFactory sf;

	@Override
	public Patient addPatient(Patient pat) {
		// TODO Auto-generated method stub
		
		//Session ses = sf.openSession();
		Session ses = sf.getCurrentSession();
		ses.save(pat);
		return pat;
	}

	@Override
	public Patient getPatientById(int patId) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
