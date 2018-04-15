package com.nareshit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nareshit.bean.PatientBean;
import com.nareshit.domain.Patient;


public interface PateintService {

	public PatientBean addPatient(PatientBean pat);
	public PatientBean getPatientById(int patId);
	
	public List<PatientBean>  getAllPatientsByName(String name);
	
		
}
