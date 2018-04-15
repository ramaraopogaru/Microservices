package com.nareshit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nareshit.bean.DoctorBean;
import com.nareshit.proxy.DoctorSource;
import com.nareshit.proxy.PatientServiceProxy;
import com.nareshit.service.DoctorService;
import com.nareshit.util.ServiceUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@EnableBinding(DoctorSource.class)
@RequestMapping("/doctorApi")
@RefreshScope
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private PatientServiceProxy feignClient;
	
	@Autowired
	private DoctorSource docSourceQ;
		
	@Value("${outpatient.fare}")
	private String opFare;
	/*
	 * @Autowired private RestTemplate restTemplate;
	 */

	
	@PostMapping(path = "/createDoctor", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addDoctor(@RequestBody DoctorBean doctorBean) {
		//System.out.println("doctor type is::::::\t"+doctorType);
		doctorBean = doctorService.addDoctor(doctorBean);
		return new ResponseEntity<String>(doctorBean.toString(), HttpStatus.OK);

	}
	
	
	@GetMapping(path = "/getDoctorByEmail/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DoctorBean> getDoctorByName(@PathVariable("email") String email) {
		DoctorBean doctorBean = doctorService.getDoctorByEmailId(email);
		return new ResponseEntity<DoctorBean>(doctorBean, HttpStatus.OK);

	}

	@HystrixCommand(fallbackMethod="addpatient_fallback")
	@PostMapping(path = "/createPatient", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addPatient(@RequestBody String patientBean) {
         System.out.println("outpatient fare is:\t"+opFare);
		   //String patientInfo = ServiceUtil.createPatient(patientBean);
		ResponseEntity<String> bodyData = feignClient.addPatient(patientBean);
		docSourceQ.doctorQ().send(MessageBuilder.withPayload(bodyData.getBody()).build());
		return new ResponseEntity<String>(bodyData.getBody(), HttpStatus.OK);

	}
	
	public ResponseEntity<String> addpatient_fallback(@RequestBody String patientBean) {
         String message ="Patient Service is currently down! try after some time.";
	   return new ResponseEntity<String>(message, HttpStatus.OK);

}
	
	

}
