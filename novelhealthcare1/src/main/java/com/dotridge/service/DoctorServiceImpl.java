package com.dotridge.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import com.dotridge.bean.DoctorBean;
import com.dotridge.dao.DoctorDao;
import com.dotridge.domain.Doctor;

@Service
public class DoctorServiceImpl implements DoctorService {
	@Autowired
	private DoctorDao doctorDao;
	
	/*public DoctorServiceImpl(DoctorDao doctorDao) {
		this.doctorDao = doctorDao;
	}*/

	@Autowired
	private PlatformTransactionManager txManager;

	private TransactionDefinition getTransactionDef() {
		TransactionDefinition tdf = new TransactionDefinition() {

			public boolean isReadOnly() {
				// TODO Auto-generated method stub
				return false;
			}

			public int getTimeout() {
				// TODO Auto-generated method stub
				return 5;
			}

			public int getPropagationBehavior() {
				// TODO Auto-generated method stub
				// TransactionDefinition.PROPAGATION_REQUIRED;
				//return TransactionDefinition.PROPAGATION_NESTED;
				return TransactionDefinition.PROPAGATION_REQUIRED;
			}

			public String getName() {
				return DoctorService.class.getName();
			}

			public int getIsolationLevel() {
				// TODO Auto-generated method stub
				return this.ISOLATION_SERIALIZABLE;
				// return 0;
			}
		};
		return tdf;
	}

	private TransactionStatus getTxnStatus() {
		TransactionDefinition tdf = getTransactionDef();
		TransactionStatus ts = txManager.getTransaction(tdf);
		return ts;
	}

	public Doctor mapBeanToDomain(DoctorBean doctorBean) {
		Date date = new Date();
		Doctor doctor = new Doctor();
		doctor.setFirstName(doctorBean.getFirstName());
		doctor.setMiddleName(doctorBean.getMiddleName());
		doctor.setLastName(doctorBean.getLastName());
		doctor.setEmail(doctorBean.getEmail());
		doctor.setPassword(doctorBean.getPassword());
		doctor.setProfilePicture(doctorBean.getProfilePicture());
		doctor.setProfileDocuments(doctorBean.getProfileDocuments());
		boolean status = doctorBean.isStatus();
		System.out.println("status is:\t"+status);
		doctor.setStatus(status);
		doctor.setCreatedBy("Yugandhar");
		doctor.setCreatedDate(date);
		doctor.setMiddleName("Yugandhar");
		doctor.setModifiedDate(date);
		doctor.setModifiedBy(doctorBean.getFirstName());
		doctor.setPhoneNumber(doctorBean.getPhoneNumber());
		doctor.setUserId(doctorBean.getFirstName());
		
		HibernateTransactionManager htm = new HibernateTransactionManager();
		
		return doctor;
	}

	public DoctorBean mapDomainToBean(Doctor doctorDomain) {
		DoctorBean doctorBean = new DoctorBean();
		doctorBean.setFirstName(doctorDomain.getFirstName());
		doctorBean.setMiddleName(doctorDomain.getMiddleName());
		doctorBean.setLastName(doctorDomain.getLastName());
		doctorBean.setEmail(doctorDomain.getEmail());
		doctorBean.setPassword(doctorDomain.getPassword());
		//doctorBean.setProfileDocuments(doctorDomain.getProfileDocuments());
		//doctorBean.setProfilePicture(doctorDomain.getProfileDocuments());
		doctorBean.setStatus(doctorDomain.isStatus());
		return doctorBean;
	}

	public DoctorBean createDoctor(DoctorBean doctorBean) {

		TransactionStatus ts = getTxnStatus();
		try{
			Doctor doc = mapBeanToDomain(doctorBean);
			System.out.println(doc.toString());
			DoctorBean doctorBean2 = mapDomainToBean(doctorDao.createDoctor(doc));
			txManager.commit(ts);
			return doctorBean2;
		}catch(Exception e){
			e.printStackTrace();
			ts.createSavepoint();
			txManager.rollback(ts);
		}
		
		return null;
		
	}

	public DoctorBean updateDoctor(DoctorBean doctorBean) {
		DoctorBean doctorBean2 = mapDomainToBean(doctorDao.updateDoctor(mapBeanToDomain(doctorBean)));
		return doctorBean2;
	}

	public DoctorBean getDoctorById(int id) {
		DoctorBean doctorBean = mapDomainToBean(doctorDao.getDoctorById(id));
		return doctorBean;
	}

	public List<DoctorBean> getDoctorByName(String doctorFirstName) {
		List<Doctor> doctors = doctorDao.getDoctorByName(doctorFirstName);
		List<DoctorBean> doctorBeans = new ArrayList<DoctorBean>();
		for (Doctor doctor : doctors) {
			doctorBeans.add(mapDomainToBean(doctor));
		}
		return doctorBeans;
	}

	public List<DoctorBean> getDoctorByEmail(String email) {
		List<Doctor> doctors = doctorDao.getDoctorByEmail(email);
		List<DoctorBean> doctorBeans = new ArrayList<DoctorBean>();
		for (Doctor doctor : doctors) {
			doctorBeans.add(mapDomainToBean(doctor));
		}
		return doctorBeans;
	}

	public List<DoctorBean> getDoctorByPhone(long phoneNumber) {
		List<Doctor> doctors = doctorDao.getDoctorByPhone(phoneNumber);
		List<DoctorBean> doctorBeans = new ArrayList<DoctorBean>();
		for (Doctor doctor : doctors) {
			doctorBeans.add(mapDomainToBean(doctor));
		}
		return doctorBeans;
	}

	public List<DoctorBean> getDoctorByStatus(Boolean status) {
		List<Doctor> doctors = doctorDao.getDoctorByStatus(status);
		List<DoctorBean> doctorBeans = new ArrayList<DoctorBean>();
		for (Doctor doctor : doctors) {
			doctorBeans.add(mapDomainToBean(doctor));
		}
		return doctorBeans;
	}

	public List<DoctorBean> getAllDoctors() {
		List<Doctor> doctors = doctorDao.getAllDoctors();
		List<DoctorBean> doctorBeans = new ArrayList<DoctorBean>();
		for (Doctor doctor : doctors) {
			doctorBeans.add(mapDomainToBean(doctor));
		}
		return doctorBeans;
	}

	public boolean deleteDoctor(int id) {
		return doctorDao.deleteDoctor(id);
	}

	public List<DoctorBean> getAllDoctorsByPagining(int currentPage, int numberOfRecords) {
		List<Doctor> doctors = doctorDao.getAllDoctorsByPagining(currentPage, numberOfRecords);
		List<DoctorBean> doctorBeans = new ArrayList<DoctorBean>();
		for (Doctor doctor : doctors) {
			doctorBeans.add(mapDomainToBean(doctor));
		}
		return doctorBeans;
	}
}
