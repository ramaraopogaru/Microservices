package com.dotridge.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.dotridge.domain.Doctor;

@Repository
public class DoctorDaoImpl implements DoctorDao
{
	@Autowired
	private SessionFactory sessionFactory;

	
	public Doctor createDoctor(Doctor doctorDomain) 
	{
		Session session = sessionFactory.getCurrentSession();
		//session.beginTransaction();
		
		session.save(doctorDomain);
		//session.getTransaction().commit();
		//session.close();
		return doctorDomain;
	}

	public Doctor updateDoctor(Doctor doctorDomain) 
	{
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(doctorDomain);
		session.getTransaction().commit();
		session.close();
		return doctorDomain;
	}

	public Doctor getDoctorById(int id) 
	{
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Doctor doctor = (Doctor) session.get(Doctor.class, id);
		session.getTransaction().commit();
		session.close();
		return doctor;
	}

	public List<Doctor> getDoctorByName(String firstName) 
	{
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		String hql = "from Doctor d whre d.firstName like:docFirstName";
		Query query = session.createQuery(hql);
		query.setParameter("docFirstName", "%" + firstName + "%");
		List<Doctor> doctors = query.list();
		session.getTransaction().commit();
		session.close();
		return doctors;
	}

	public List<Doctor> getDoctorByEmail(String email) 
	{
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql = "from Doctor d where d.email like:docEmail";
		Query query = session.createQuery(hql);
		query.setParameter("docEmail", "%" + email + "%");
		List<Doctor> doctors = query.list();
		session.getTransaction().commit();
		session.close();
		return doctors;
	}

	public List<Doctor> getDoctorByPhone(long phoneNumber) 
	{
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql = "from Doctor d where d.phoneNumber=:docPhoneNumber";
		Query query = session.createQuery(hql);
		query.setParameter("docPhoneNumber", phoneNumber);
		List<Doctor> doctors = query.list();
		session.getTransaction().commit();
		session.close();
		return doctors;
	}

	public List<Doctor> getDoctorByStatus(Boolean status) 
	{
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql = "from Doctor d where d.status=:IsActive";
		Query  query = session.createQuery(hql);
		query.setParameter("IsActive", status);
		List<Doctor> doctors = query.list();
		session.getTransaction().commit();
		session.close();
		return doctors;
	}

	public List<Doctor> getAllDoctors() 
	{
		//Session session = sessionFactory.getCurrentSession();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "from Doctor";
		Query query = session.createQuery(hql);
		//query.setCacheable(true);
		List<Doctor> doctors = query.list();
		session.getTransaction().commit();
		session.close();
		return doctors;
	}

	public boolean deleteDoctor(int id) 
	{
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Doctor doctor = (Doctor) session.get(Doctor.class, id);
		session.delete(doctor);
		session.getTransaction().commit();
		session.close();
		if(doctor == null)
		{
			return true;
		}
		return false;
	}

	public List<Doctor> getAllDoctorsByPagining(int currentPage, int numberOfRecords) 
	{
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		String hql = "from Doctor";
		Query query = session.createQuery(hql);
		query.setFirstResult((currentPage - 1) * numberOfRecords);
		query.setMaxResults(numberOfRecords);
		List<Doctor> doctors = query.list();
		session.close();
		return doctors;
	}
	
	/*public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		DoctorDao dao = ctx.getBean(DoctorDao.class);
		Doctor doc = new Doctor();
		doc.setUserId("john");
		doc.setPassword("test");
		doc.setEmail("john@email.com");
		doc.setFirstName("john");
		doc.setLastName("sanda");
		doc.setPhoneNumber(9876);
		doc.setStatus(false);
		doc.setCreatedBy("john");
		doc.setModifiedBy("john");
		doc.setCreatedDate(new Date());
		doc.setModifiedDate(new Date());

		dao.createDoctor(doc);
		System.out.println(doc.getDoctorId());
	}*/
}
