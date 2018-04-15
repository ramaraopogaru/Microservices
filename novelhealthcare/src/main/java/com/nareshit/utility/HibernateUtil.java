package com.nareshit.utility;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static HibernateUtil hibUtil;
	private SessionFactory sessionFactory;
	private Session session;
	private Configuration cfg;
	
	private HibernateUtil() {
		cfg = new Configuration().configure();
		sessionFactory = cfg.buildSessionFactory();
	}
	
	public static HibernateUtil getInstance() {
		if(hibUtil == null) {
			hibUtil = new HibernateUtil();
		}
		return hibUtil;
	}
	
	
	public  void reconnect() {
		this.sessionFactory = cfg.buildSessionFactory();
		System.out.println(sessionFactory.hashCode());
	}
	
	public Session getSession() {
		this.session = sessionFactory.openSession();
		if(!session.isConnected()) {
			reconnect();
		}
		
		return session;
	}
	
}
