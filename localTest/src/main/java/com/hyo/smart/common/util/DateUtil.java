package com.hyo.smart.common.util;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	
	// DateCompare.
	public static void dateCompare( String _day1, String _day2 ) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date day1 = null;
		Date day2 = null;
		try {
			day1 = format.parse(_day1);
			day2 = format.parse(_day2);
			
			int compare = day1.compareTo(day2);
			if ( compare > 0 ) {
				System.out.println("day1 > day2");
			} else if ( compare < 0 ) {
				System.out.println("day1 < day2");
			} else {
				System.out.println("day1 = day2");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}		
	}
	
	// AddDate.
	public static void addDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");		
		Calendar cal = new GregorianCalendar(2019, Calendar.NOVEMBER, 12);
		System.out.println("StartDate : " + format.format(cal.getTime()));
		
		cal.add(Calendar.DAY_OF_MONTH, 7);
		System.out.println("EndDate : " + format.format(cal.getTime()));
	}
		
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		dateCompare("2019-08-09", "2019-09-30");
		addDate();
	}    
}
