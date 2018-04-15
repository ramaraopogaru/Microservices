package com.nareshit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nareshit.domain.Patient;

@Repository
public class PatientDaoImpl implements PatientDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Patient addPatient(Patient patient) {
		Session session = sessionFactory.openSession();
		//Transaction transaction = session.beginTransaction();
		session.save(patient);
		//transaction.commit();
		session.close();
		return patient;
	}

	@Override
	public boolean deletePatient(int patientId) {
		return inactivePatient(patientId);
	}

	@Override
	public boolean activePatient(int patientId) {
		Session session = sessionFactory.openSession();
		//Transaction transaction = session.beginTransaction();
		Patient patient = (Patient) session.load(Patient.class, patientId);
		patient.setStatus(true);
		session.update(patient);
		//transaction.commit();
		session.close();
		return patient.isStatus();

	}

	@Override
	public boolean inactivePatient(int patientId) {
		Session session = sessionFactory.openSession();
		//Transaction transaction = session.beginTransaction();
		Patient patient = (Patient) session.load(Patient.class, patientId);
		patient.setStatus(false);
		session.update(patient);
		//transaction.commit();
		session.close();
		return patient.isStatus();
	}

	@Override
	public List<Patient> getAllPatients() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Patient");
		List<Patient> listOfPatients = query.list();
		session.close();
		return listOfPatients;
	}

	@Override
	public List<Patient> searchPatient(int patientId, String firstName, String lastName, String email, long phoneNumber,
			String status) {
		boolean isSearched = false;
		StringBuilder sb = new StringBuilder();
		sb.append("from Patient as p ");
		if(patientId>0) {
			sb.append("where p.patientId= "+patientId);
			isSearched = true;
		}
		if(firstName!=null && !firstName.isEmpty()) {
			if(isSearched) {
				sb.append(" or ");
			}else {
				sb.append("where ");
			}
				
			sb.append("p.firstName like \'%"+firstName+"%\'");
			isSearched = true;
		}
		if(lastName!=null && !lastName.isEmpty()) {
			if(isSearched) {
				sb.append(" or ");
			}else {
				sb.append("where ");
			}
			sb.append("p.lastName like \'%"+lastName+"%\'");
			isSearched = true;
		}
		if(email!=null && !email.isEmpty()) {
			if(isSearched) {
				sb.append(" or ");
			}else {
				sb.append("where ");
			}
			sb.append("p.email like \'%"+email+"%\'");
			isSearched = true;
		}
		if(phoneNumber>0) {
			if(isSearched) {
				sb.append(" or ");
			}else {
				sb.append("where ");
			}
			sb.append("or p.phoneNumber= "+phoneNumber);
			isSearched = true;
		}
		if(status!=null && !status.isEmpty()) {
			if(isSearched) {
				sb.append(" or ");
			}else {
				sb.append("where ");
			}
			sb.append("p.status="+Boolean.valueOf(status));
			
		}
		System.out.println(sb.toString());
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(sb.toString());
		List<Patient> listOfPatients = query.list();
		return listOfPatients;
	}

	@Override
	public Patient getPatientById(int patientId) {
		Session session = sessionFactory.openSession();
		//Transaction transaction = session.beginTransaction();
		Patient patient = (Patient) session.load(Patient.class, patientId);
		session.close();
		return patient;
	}

	public static void main(String[] args) {
		PatientDao patDao = new PatientDaoImpl();
		patDao.searchPatient(0, "john", "kane", "sagar@gmail.com", 0, null);
	}
}
