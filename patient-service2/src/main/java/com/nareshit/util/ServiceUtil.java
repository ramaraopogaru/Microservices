/**
 * 
 */
package com.nareshit.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


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
	
	public static void main(String[] args) {
		System.out.println(getStringFromDate(new Date()));
	}
}
