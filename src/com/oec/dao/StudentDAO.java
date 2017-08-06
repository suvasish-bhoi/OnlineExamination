package com.oec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.oec.util.JDBCUtil;
import com.oec.vo.StudentVO;


public class StudentDAO {
		private static Connection con = JDBCUtil.getConnection();
	
	public static boolean checkEmail(String email) throws SQLException{
		String sql = "select * from studentVo where email='"+email+"'";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		if(rs.next()){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static boolean checkUserName(String userName) throws SQLException{
		String sql = "select * from studentVo where username='"+userName+"'";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		if(rs.next()){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static void saveStudent(StudentVO svo) throws SQLException{
		String sql = "INSERT INTO `studentvo`(`name`, `email`, `mobile`, `address`, `username`, `password`,`skills`) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1,svo.getName());
		ps.setString(2,svo.getEmail());
		ps.setString(3,svo.getMobile());
		ps.setString(4,svo.getAddress());
		ps.setString(5,svo.getUserName());
		ps.setString(6,svo.getPassword());
		ps.setString(7,svo.getSkills());
		ps.executeUpdate();
	}
	
	public static StudentVO getStudentDetails(String username) throws SQLException{
		String sql = "SELECT `student_id`, `name`, `email`, `mobile`, `address`,`password` FROM `studentvo` WHERE username='"+username+"'";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		StudentVO svo = new StudentVO();
		if(rs.next()){
			svo.setUserName(username);
			svo.setStudent_id(rs.getInt(1));
			svo.setName(rs.getString(2));
			svo.setEmail(rs.getString(3));
			svo.setMobile(rs.getString(4));
			svo.setAddress(rs.getString(5));
			svo.setPassword(rs.getString(6));
			return svo;
		}
		return null;
	}
	
	public static String getEmailById(int id) throws SQLException{
		String sql = "SELECT email FROM studentvo WHERE student_id="+id;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		if(rs.next()){
			return rs.getString(1);
		}
		return null;
	}
	
	public static StudentVO getStudentDetailsById(int id) throws SQLException{
		String sql = "SELECT `username`, `name`, `email`, `mobile`, `address`,`password` FROM `studentvo` WHERE student_id="+id;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		StudentVO svo = new StudentVO();
		if(rs.next()){
			svo.setUserName(rs.getString(1));
			svo.setStudent_id(id);
			svo.setName(rs.getString(2));
			svo.setEmail(rs.getString(3));
			svo.setMobile(rs.getString(4));
			svo.setAddress(rs.getString(5));
			svo.setPassword(rs.getString(6));
			return svo;
		}
		return null;
	}
	
	public static void updateProfile(StudentVO sv) throws SQLException{
		String sql = "UPDATE `studentvo` SET `name`=?,`email`=?,`mobile`=?,`address`=?,`username`=?,`password`=?,`skills`=? WHERE student_id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1,sv.getName());
		ps.setString(2,sv.getEmail());
		ps.setString(3,sv.getMobile());
		ps.setString(4,sv.getAddress());
		ps.setString(5,sv.getUserName());
		ps.setString(6,sv.getPassword());
		ps.setString(7,sv.getSkills());
		ps.setInt(8,sv.getStudent_id());
		ps.executeUpdate();
	}
	
	public static String getUsernameById(int id) throws SQLException{
		String sql = "select username FROM studentvo WHERE student_id="+id;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		if(rs.next()){
			return rs.getString(1);
		}
		return "";
	}
	
	public static String getStudentNameByUsername(String username) throws SQLException{
		String sql = "SELECT name FROM studentvo WHERE username='"+username+"'";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		if(rs.next()){
			return rs.getString(1);
		}else{
			return "";
		}
	}

}
