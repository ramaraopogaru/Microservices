package com.nareshit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nareshit.bean.PatientBean;
import com.nareshit.dao.PatientDataRepository;
import com.nareshit.dao.PatientHibernateEngineImpl;
import com.nareshit.domain.Patient;

@Service
public class PatientServiceImpl implements PateintService {

	@Autowired
	private PatientDataRepository patRepo;
	
	@Autowired
	private PatientHibernateEngineImpl patHibRepo;
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public PatientBean addPatient(PatientBean pat) {
		Patient patDomain = mapBeanToDomain(pat);
		//patDomain = patRepo.save(patDomain);
		patDomain = patHibRepo.addPatient(patDomain);
		mapDomainBean(patDomain);
		return mapDomainBean(patDomain);
	}

	@Override
	@Transactional(rollbackFor=Exception.class,readOnly=true)
	public PatientBean getPatientById(int patId) {
		Patient patDomain = patRepo.findOne(patId);
		return mapDomainBean(patDomain);
	}

	
	private Patient mapBeanToDomain(PatientBean pat) {
		Patient patDomain = new Patient();
		patDomain.setFirstName(pat.getFirstName());
		patDomain.setLastName(pat.getLastName());
		patDomain.setEmail(pat.getEmail());
		patDomain.setPassword(pat.getPassword());
		return patDomain;
	}

	private PatientBean mapDomainBean(Patient pat) {
		PatientBean patDomain = new PatientBean();
		patDomain.setpId(pat.getpId());
		patDomain.setFirstName(pat.getFirstName());
		patDomain.setLastName(pat.getLastName());
		patDomain.setEmail(pat.getEmail());
		patDomain.setPassword(pat.getPassword());
		return patDomain;
	}

	@Override
	public List<PatientBean> getAllPatientsByName(String name) {
		List<Patient> patList = patRepo.getAllPatientsByname(name);
		List<PatientBean> patBeanList = new ArrayList<PatientBean>();
		for(Patient pat : patList) {
			patBeanList.add(mapDomainBean(pat));
		}
		return patBeanList;
	}

}
