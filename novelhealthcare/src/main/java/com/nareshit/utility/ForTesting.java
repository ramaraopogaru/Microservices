package com.nareshit.utility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.nareshit.domain.Hospital;

public class ForTesting {

	public static void main(String[] args) {
		
		/*Configuration cfg = new Configuration().configure();
		SessionFactory sf = cfg.buildSessionFactory();
		System.out.println(sf.hashCode());
		
		SessionFactory sf1 = cfg.buildSessionFactory();
		System.out.println(sf1.hashCode());
		Session ses = HibernateUtil.getInstance().getSession();
		Transaction tx = ses.beginTransaction();
		
		Hospital hosp = new Hospital();
		hosp.setHospName("appollo");
		
		ses.save(hosp);
		tx.commit();
		*/
		System.out.println(SessionFactoryutil.getSessionFactory().hashCode());
		System.out.println(SessionFactoryutil.getSessionFactory().hashCode());
		
	}
}
