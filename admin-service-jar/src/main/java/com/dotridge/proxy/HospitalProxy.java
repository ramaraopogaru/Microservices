package com.dotridge.proxy;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class HospitalProxy {

	private static String HOSPITAL_SERVICE_URI = "http://localhost:9096/hospitalManagement/";
	public static String getHospitalInfoByName(String hospName) {
		
		HOSPITAL_SERVICE_URI = HOSPITAL_SERVICE_URI+"getHospitalByName/"+hospName;
		System.out.println(HOSPITAL_SERVICE_URI);
		RestTemplate rt = new RestTemplate();
		ResponseEntity<String> hospitalInfo = rt.getForEntity(HOSPITAL_SERVICE_URI, String.class);
		return hospitalInfo.getBody();
	}
	
	public static void main(String[] args) {
		System.out.println("hospital data is:\t"+getHospitalInfoByName("yashodha"));
	}
}
