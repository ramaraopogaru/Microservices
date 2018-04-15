package com.nareshit.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nareshit.bean.PatientBean;
import com.nareshit.service.PatientService;

@RestController
@RequestMapping("/patientApi")
public class PatientController {

	@Autowired
	private PatientService patientService;

	@PostMapping(path = "/createPatient", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PatientBean> addPatient(@RequestBody PatientBean patient) {
		patient = patientService.addPatient(patient);
		return new ResponseEntity<PatientBean>(patient, HttpStatus.OK);

	}

}
