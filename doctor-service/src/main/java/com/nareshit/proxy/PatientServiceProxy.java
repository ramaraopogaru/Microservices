package com.nareshit.proxy;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient(name="patient-proxy",url="localhost:9096")
//@RibbonClient
//@FeignClient(name="patient-proxy")
//@FeignClient(name="PATIENT-SERVICE")
@FeignClient(name="ZUUL-APIGATEWAY")
public interface PatientServiceProxy {

	@PostMapping(path = "/api/pat-service/patientApi/createPatient", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addPatient(@RequestBody String payload);
}
