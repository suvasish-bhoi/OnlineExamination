package com.oec.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	private static SimpleDateFormat sdf;
	static{
		sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	}
	
	public static String getDate(){
		return sdf.format(new java.sql.Date(System.currentTimeMillis()));
	}
	
	public static String getFormatedDate(String date) throws ParseException{
		
        return date.replace('/','-')+":00";
	}
	
	public static String examTargetTime(int duration){
		SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy hh:mm:ss a");
		Calendar cc = Calendar.getInstance();
		cc.setTime(new java.util.Date());
		cc.add(Calendar.MINUTE,duration);
		String dateTime = sdf.format(cc.getTime());
		return Integer.toString(new java.util.Date().getMonth()+1)+dateTime.substring(2);		
	}

}
