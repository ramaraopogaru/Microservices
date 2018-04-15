package com.dotridge.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.dotridge.domain.Module;
import com.dotridge.domain.RoleToModule;
import com.dotridge.domain.UserProfile;

@Repository
public class RegistrationDaoImpl implements RegistrationDao 
{
	@Autowired
	private SessionFactory sessionFactory;

		
	public UserProfile createUser(UserProfile user) 
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
		return user;
	}
	
	public UserProfile getUserByName(String username,String password) 
	{
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from UserProfile where userId=:userId and password=:password");
		q.setParameter("userId", username);
		q.setParameter("password", password);
		UserProfile user = (UserProfile)q.uniqueResult();
		return user;
	}
	
	public List<Module> getAllModulesOfRole(int roleId){
		
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from RoleToModule rt where rt.role.roleId=:roleId");
		q.setParameter("roleId", roleId);
		
		List<RoleToModule> rmList = q.list();
		
		List<Module> modList = new ArrayList<Module>();
		
		if(rmList != null && !rmList.isEmpty()){
			for(RoleToModule rm : rmList){
				modList.add(rm.getModule());
			}
		}
		
		return modList;
		
	}
}
