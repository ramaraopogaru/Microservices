package com.dotridge.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dotridge.domain.Admin;


@Repository
public class AdminDaoImpl implements AdminDao 
{
	@Autowired
	private SessionFactory sessionFactory;
	public Admin createAdmin(Admin adminDomain) 
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(adminDomain);
		session.getTransaction().commit();
		session.close();
		return adminDomain;
	}

	public Admin updateAdmin(Admin adminDomain) 
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.update(adminDomain);
		session.getTransaction().commit();
		session.close();
		return adminDomain;
	}

	public Admin getAdminById(int id) 
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Admin adminDomain = (Admin)session.load(Admin.class, id);
		session.getTransaction().commit();
		//session.close();
		return adminDomain;
	}

	public List<Admin> getAdminByName(String firstName) 
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String hql = "from Admin a where a.firstName like:adminFirstName";
		Query query = session.createQuery(hql);
		query.setParameter("adminFirstName", "%" + firstName + "%");
		List<Admin> adminDomains = query.list();
		session.close();
		return adminDomains;
	}
	
	public List<Admin> getAdminByEmail(String email)
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String hql = "from Admin ad where ad.email like:adminMail";
		Query query = session.createQuery(hql);
		query.setParameter("adminMail", "%" + email + "%");
		List<Admin> admins = query.list();
		session.close();
		return admins;
	}
	
	public List<Admin> getAdminByPhone(long phoneNumber)
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String hql = "from Admin ad where ad.phone=:phone";
		Query  query = session.createQuery(hql);
		query.setParameter("phone", phoneNumber);
		List<Admin> admins = query.list();
		session.close();
		return admins;
	}
	
	public List<Admin> getAdminByStatus(Boolean status)
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String hql = "from Admin ad where ad.status=:IsActive";
		Query  query = session.createQuery(hql);
		query.setParameter("IsActive", status);
		List<Admin> admins = query.list();
		session.close();
		return admins;
	}

	public List<Admin> getAllAdmins() 
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String hql = "from Admin";
		Query query = session.createQuery(hql);
		List<Admin> adminDomains = query.list();
		session.close();
		return adminDomains;
	}

	public boolean deleteAdmin(int id) 
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Admin adminDomain = (Admin)session.load(Admin.class, id);
		session.delete(adminDomain);
		session.getTransaction().commit();
		session.clear();
		session.close();
		System.out.println(adminDomain);
		if(adminDomain == null)
		{
			return true;
		}
		return false;
	}

	public List<Admin> getAllAdminsByPagining(int currentPage, int numberOfRecords) 
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String hql = "from Admin";
		Query query = session.createQuery(hql);
		query.setFirstResult((currentPage - 1) * numberOfRecords);
		query.setMaxResults(numberOfRecords);
		List<Admin> adminDomains = query.list();
		session.close();
		return adminDomains;
	}

}
