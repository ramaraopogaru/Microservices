package com.dotridge.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dotridge.domain.Hospital;

@Repository
public class HospitalDaoImpl implements HospitalDao 
{
	@Autowired
	private SessionFactory sessionFactory;
	
	public Hospital createHospital(Hospital hospital) 
	{
		//SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(hospital);
		session.getTransaction().commit();
		session.close();
		return hospital;
	}

	public Hospital updateHospital(Hospital hospital) 
	{
		//SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		//Hospital hospital2 = new Hospital();
		session.beginTransaction();
		System.out.println(hospital.getHospitalId());
		/*hospital2.setHospitalName(hospital.getHospitalName());
		hospital2.setAddress1(hospital.getAddress1());
		hospital2.setAddress2(hospital.getAddress2());
		hospital2.setCity(hospital.getCity());
		hospital2.setState(hospital.getState());
		hospital2.setZipCode(hospital.getZipCode());
		hospital2.setStatus(hospital.getStatus());
		hospital2.setLogo(hospital.getLogo());
		hospital2.setRegistrationDocument(hospital.getRegistrationDocument());
		hospital2.setCreatedBy(hospital.getCreatedBy());
		hospital2.setCreatedDate(hospital.getCreatedDate());
		hospital2.setModifiedBy(hospital.getModifiedBy());
		hospital2.setModifiedDate(hospital.getModifiedDate());*/
		session.update(hospital);
		session.getTransaction().commit();
		session.close();
		return hospital;
	}

	public Hospital getHospitalById(int id) 
	{
		//SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Hospital hospital = (Hospital)session.get(Hospital.class, id);
		session.getTransaction().commit();
		session.close();
		return hospital;
	}
	
	public Hospital getHospitalByUniqueName(String hospitalName)
	{
		//SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String hql = "from Hospital h where h.hospitalName=:hospitalName";
		Query query = session.createQuery(hql);
		query.setParameter("hospitalName", hospitalName);
		Hospital hospital = (Hospital) query.uniqueResult();
		session.close();
		return hospital;
	}
	
	public List<Hospital> getHospitalByName(String hospitalName) 
	{
		//SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String hql = "from Hospital h where h.hospitalName like:hospitalName";
		Query query = session.createQuery(hql);
		query.setParameter("hospitalName", "%" + hospitalName + "%");
		List<Hospital> hospitals = query.list();
		session.close();
		return hospitals;
	}
	
	public List<Hospital> getHospitalByEmail(String email)
	{
		//SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String hql = "from Hospital h where h.email like:hospitalMail";
		Query query = session.createQuery(hql);
		query.setParameter("hospitalMail", "%" + email + "%");
		List<Hospital> hospitals = query.list();
		session.close();
		return hospitals;
	}
	
	public List<Hospital> getHospitalByAddress1(String address1)
	{
		//SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String hql = "from Hospital h where h.address1 like:hospitalAddress1";
		Query query = session.createQuery(hql);
		query.setParameter("hospitalAddress1", "%" + address1 + "%");
		List<Hospital> hospitals = query.list();
		session.close();
		return hospitals;
	}
	
	public List<Hospital> getHospitalByPhone(long phoneNumber)
	{
		//SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String hql = "from Hospital h where h.phone=:phone";
		Query  query = session.createQuery(hql);
		query.setParameter("phone", phoneNumber);
		List<Hospital> hospitals = query.list();
		session.close();
		System.out.println(hospitals);
		return hospitals;
	}
	
	public List<Hospital> getHospitalByStatus(Boolean status)
	{
		//SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String hql = "from Hospital h where h.status=:IsActive";
		Query  query = session.createQuery(hql);
		query.setParameter("IsActive", status);
		List<Hospital> hospitals = query.list();
		session.close();
		System.out.println(hospitals);
		return hospitals;
	}

	public List<Hospital> getAllHospitals() 
	{
		//SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String hql = "from Hospital";
		Query query = session.createQuery(hql);
		query.setCacheable(true);
		List<Hospital> hospitals = query.list();
		session.close();
		return hospitals;
	}

	public boolean deleteHospital(int id) 
	{
		//SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Hospital hospital = (Hospital)session.load(Hospital.class, id);
		session.delete(hospital);
		session.getTransaction().commit();
		session.close();
		if(hospital == null)
		{
			return true;
		}
		return false;
	}

	public List<Hospital> getAllHospitalsByPagining(int currentPage, int numberOfRecords) 
	{
		//SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String hql = "from Hospital";
		Query query = session.createQuery(hql);
		query.setFirstResult((currentPage - 1) * numberOfRecords);
		query.setMaxResults(numberOfRecords);
		List<Hospital> hospitals = query.list();
		session.close();
		return hospitals;
	}

}
