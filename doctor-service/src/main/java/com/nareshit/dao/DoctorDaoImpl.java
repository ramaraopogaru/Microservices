package com.nareshit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nareshit.domain.Doctor;


@Repository
public class DoctorDaoImpl implements DoctorDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Doctor addDoctor(Doctor doctor) {
		Session session = sessionFactory.openSession();
		// Transaction transaction = session.beginTransaction();
		session.save(doctor);
		// transaction.commit();
		session.close();
		return doctor;
	}

	@Override
	public boolean deleteDoctor(int doctorId) {
		return inactiveDoctor(doctorId);

	}

	@Override
	public boolean activeDoctor(int doctorId) {
		Session session = sessionFactory.openSession();
		// Transaction transaction = session.beginTransaction();
		Doctor doctor = (Doctor) session.load(Doctor.class, doctorId);
		doctor.setStatus(true);
		session.update(doctor);
		// transaction.commit();
		session.close();
		return doctor.isStatus();
	}

	@Override
	public boolean inactiveDoctor(int doctorId) {
		Session session = sessionFactory.openSession();
		// Transaction transaction = session.beginTransaction();
		Doctor doctor = (Doctor) session.load(Doctor.class, doctorId);
		doctor.setStatus(false);
		session.update(doctor);
		// transaction.commit();
		session.close();
		return doctor.isStatus();
	}

	@Override
	public List<Doctor> getAllDoctors() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Doctor");
		List<Doctor> listOfDoctors = query.list();
		session.close();
		return listOfDoctors;
	}

	@Override
	public List<Doctor> searchDoctor(int doctorId, String firstName, String lastName, String email, long phoneNumber,
			String status) {

		boolean isSearched = false;
		StringBuilder sb = new StringBuilder();
		sb.append("from Doctor as d ");
		if (doctorId > 0) {
			sb.append("where p.patientId= " + doctorId);
			isSearched = true;
		}
		if (firstName != null && !firstName.isEmpty()) {
			if (isSearched) {
				sb.append(" or ");
			} else {
				sb.append("where ");
			}

			sb.append("d.firstName like \'%" + firstName + "%\'");
			isSearched = true;
		}
		if (lastName != null && !lastName.isEmpty()) {
			if (isSearched) {
				sb.append(" or ");
			} else {
				sb.append("where ");
			}
			sb.append("d.lastName like \'%" + lastName + "%\'");
			isSearched = true;
		}
		if (email != null && !email.isEmpty()) {
			if (isSearched) {
				sb.append(" or ");
			} else {
				sb.append("where ");
			}
			sb.append("d.email like \'%" + email + "%\'");
			isSearched = true;
		}
		if (phoneNumber > 0) {
			if (isSearched) {
				sb.append(" or ");
			} else {
				sb.append("where ");
			}
			sb.append("or d.phoneNumber= " + phoneNumber);
			isSearched = true;
		}
		if (status != null && !status.isEmpty()) {
			if (isSearched) {
				sb.append(" or ");
			} else {
				sb.append("where ");
			}
			sb.append("d.status=" + Boolean.valueOf(status));

		}
		System.out.println(sb.toString());
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(sb.toString());
		List<Doctor> listOfDoctors = query.list();
		return listOfDoctors;

	}

	@Override
	public Doctor getDoctorById(int doctorId) {
		Session session = sessionFactory.openSession();
		// Transaction transaction = session.beginTransaction();
		Doctor doctor = (Doctor) session.load(Doctor.class, doctorId);
		session.close();
		return doctor;
	}

	@Override
	public Doctor getDoctorByEmail(String email) {
		System.out.println("email in dao is:\t"+email);
		//Session session = sessionFactory.getCurrentSession();
		Session session = sessionFactory.openSession();
		String findByEmail = "from Doctor d where d.email like \'%"+email+"%\'";
        Query q = session.createQuery(findByEmail);
        //q.setParameter("email", email);
        Doctor doc = (Doctor) q.uniqueResult();
        System.out.println("doc :\t"+doc.toString());
        return doc;
	}

}
