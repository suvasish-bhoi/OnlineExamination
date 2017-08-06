package com.oec.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

public class LogUtil {
	
	private static LogUtil logger;
	private static boolean debug=false;
	static{
		try {
			System.setOut(new PrintStream(new File("src/System.log")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties pp = new Properties();
		try {
			Class<LogUtil> c = LogUtil.class;
			ClassLoader cl = c.getClassLoader();
			pp.load(cl.getResourceAsStream("Admin.properties"));
		} catch (FileNotFoundException e) {
			System.out.println("AdminProperties file not found");
		} catch (IOException e) {
			System.out.println("Unable to read from AdminProperties");
		}
		if(pp.getProperty("logger").equals("debug")){
			debug= true;
		}
	}
	public static LogUtil getLogger(){
		if(logger!=null){
			return logger;
		}else{
			return new LogUtil();
		}
	}
	
	public void logDebug(String clasz,String message){
		if(debug){
			System.out.println(clasz+";"+message);
		}
	}
}
