/**
 * 
 */
package com.nareshit.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author Narsaiah
 *
 */
public class ServiceUtil {

	public static Date getDateFromString(String date) {
		SimpleDateFormat sdf =new SimpleDateFormat(ServiceConstants.PATIENT_SERVICE_DATE_INPUT_FORMAT);
		Date convertedDate = null;
		try {
			convertedDate = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return convertedDate;
	}
	
	
	public static String getStringFromDate(Date date) {
		SimpleDateFormat sdf =new SimpleDateFormat(ServiceConstants.PATIENT_SERVICE_DATE_INPUT_FORMAT);
		String convertedDate = null;
		convertedDate = sdf.format(date);
		return convertedDate;
	}
	
	public static String createPatient(String patientLoad) {
		RestTemplate rt = new RestTemplate();
		String uri = "http://localhost:9096/patientApi/createPatient";
		
		HttpHeaders header = new HttpHeaders();
		header.set("content-type", "application/json");
		
		HttpEntity<String> req = new HttpEntity<String>(patientLoad, header);
		
		ResponseEntity<String> resp = rt.exchange(uri, HttpMethod.POST, req, String.class);
		String patientInfo = resp.getBody();
		System.out.println("patient is:\t"+patientInfo);
		
		
		
		
		return patientInfo;
	}
	
	/*public static void main(String[] args) {
		StringBuilder str = new StringBuilder();
		str.append("{");
		str.append("	\"firstName\":\"john\",");
		str.append("	\"lastName\":\"kane\",");
		str.append("	\"middleName\":\"linda\",");
		str.append("	\"email\":\"johnk@email.com\",");
		str.append("	\"phoneNumber\":\"987655490\",");
		str.append("	\"password\":\"Adan12\"");
		str.append("		");
		str.append("}");
		
		System.out.println(createPatient(str.toString()));
	}
	*/
}
