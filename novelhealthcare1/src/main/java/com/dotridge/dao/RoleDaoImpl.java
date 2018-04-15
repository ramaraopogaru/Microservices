package com.dotridge.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dotridge.domain.Role;

@Repository
public class RoleDaoImpl implements RoleDao
{
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Role> getRoles() 
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "from Role r where r.roleId between :from and :to";
		Query query = session.createQuery(hql);
		query.setParameter("from", 2);
		query.setParameter("to", 5);
		List<Role> roles = query.list();
		session.close();
		return roles;
	}
	
	public Role getRoleByUniqueName(String role)
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "from Role r where r.roleName=:roleName";
		Query query = session.createQuery(hql);
		query.setParameter("roleName", role);
		Role roleName = (Role) query.uniqueResult();
		session.close();
		return roleName;
	}
}
