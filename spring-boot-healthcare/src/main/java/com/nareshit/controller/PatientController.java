package com.nareshit.controller;

import java.util.List;

import javax.sql.DataSource;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nareshit.bean.PatientBean;
import com.nareshit.service.PateintService;

@Controller
@RequestMapping("/patientservice")
public class PatientController {

	@Autowired
	private PateintService patService;
	
	@Autowired
	private DataSource ds;
	
	@Autowired
	private Environment env;
	
	@RequestMapping(value="/addPatient",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody PatientBean addPatinet(@RequestBody PatientBean patBean){
		
		String patinetType = env.getProperty("patient.type");
		System.out.println("patientTYpe is:\t"+patinetType);
		
		System.out.println("current datasource is:\t"+ds);
		
		patBean = patService.addPatient(patBean);
		
		return patBean;
	}
	
	
	@RequestMapping(value="/getPatientById/{patId}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.GET)
	public @ResponseBody PatientBean getPatinetById(@PathVariable("patId") String patId){
		
		System.out.println("patied is:\t"+patId);
		
		
		String patinetType = env.getProperty("patient.type");
		System.out.println("patientTYpe is:\t"+patinetType);
		
		System.out.println("current datasource is:\t"+ds);
		
		PatientBean patBean = patService.getPatientById(Integer.valueOf(patId));
		
		return patBean;
	}
	
	@RequestMapping(value="/getAllPatientsByName/{patName}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.GET)
	public @ResponseBody List<PatientBean> getPatinetByName(@PathVariable("patName") String patName){
		
		System.out.println("patName is:\t"+patName);
		
		
		String patinetType = env.getProperty("patient.type");
		System.out.println("patientTYpe is:\t"+patinetType);
		
		System.out.println("current datasource is:\t"+ds);
		
		List<PatientBean> patBean = patService.getAllPatientsByName(patName);
		
		return patBean;
	}
	
	
}
