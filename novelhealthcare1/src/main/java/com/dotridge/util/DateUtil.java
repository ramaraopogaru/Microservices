package com.dotridge.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String getStringFromDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat(ServiceConstants.NOVEL_HEALTH_DATE_FOMRAT);
		String dateInString = sdf.format(date);
		
		return dateInString;
	}
	
	public static Date getDateFromString(String dateInString){
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(ServiceConstants.NOVEL_HEALTH_DATE_FOMRAT);
		try {
			date = sdf.parse(dateInString);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return date;
	}
	
	/*public static void main(String[] args) {
		String dateString = DateUtil.getStringFromDate(new Date());
		System.out.println("date to string:\t"+dateString);
		Date date = getDateFromString(dateString);
		System.out.println("String to date is:\t"+date.toString());
	}*/
}
