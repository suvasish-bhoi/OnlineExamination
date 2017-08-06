package com.oec.util;

import java.sql.*;

public class JDBCUtil {
	
	private static Connection con;
	private static String url = "jdbc:mysql://localhost:3306/onlineexamination";
	private static String username = "root";
	private static String password = "";
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		if(con==null){
			try {
				return DriverManager.getConnection(url,username,password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return con;
		
	}
	
	public static void close(){
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
