package com.oec.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.oec.util.JDBCUtil;
import com.oec.vo.MainLoginVO;

public class SystemDAO {
	
	private static Connection con;
	static{
		con = JDBCUtil.getConnection();
		try {
			createAdmin();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void createAdmin() throws SQLException{
		String sql = "SELECT * FROM mainloginvo where client_id=101";
		Properties pp = new Properties();
		try {
			Class c = SystemDAO.class;
			ClassLoader cl = c.getClassLoader();
			pp.load(cl.getResourceAsStream("Admin.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("AdminProperties file not found");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Unable to read from AdminProperties");
		}
		String username=pp.getProperty("adminUsername");
		String password=pp.getProperty("adminPassword");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		if(!rs.next()){
			sql = "INSERT INTO `mainloginvo`(`client_id`, `username`, `password`, `group_type`) VALUES (101,'"+username+"','"+password+"','admin')";
			st.executeUpdate(sql);
		}else{
			if(!rs.getString(3).equals(username) || !rs.getString(4).equals(password)){
				sql = "UPDATE mainloginvo SET username ='"+username+"' AND password='"+password+"' WHERE id=101";
				st.executeUpdate(sql);
			}
		}
	}
	
	public String getGroupType(MainLoginVO mainLoginVO){
		int client_id = mainLoginVO.getClient_id();
		String username = mainLoginVO.getUsername();
		String password = mainLoginVO.getPassword();
		String group_type="";
		try {
			PreparedStatement ps = con.prepareStatement("select group_type from mainloginvo where client_id=? and username=? and password=?");
			ps.setInt(1,client_id);
			ps.setString(2,username);
			ps.setString(3,password);
			ResultSet resultSet = ps.executeQuery();
			if(resultSet.next()){
				return resultSet.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
		
	public void saveLogin(MainLoginVO mainVo){
		try{
			String sql = "INSERT INTO `mainloginvo`(`client_id`, `username`, `password`, `group_type`) VALUES (?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,mainVo.getClient_id());
			ps.setString(2,mainVo.getUsername());
			ps.setString(3,mainVo.getPassword());
			ps.setString(4,mainVo.getGroup_type());
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void updateLogin(MainLoginVO mainVo) throws SQLException{
		String sql= "UPDATE `mainloginvo` SET `username`=?,`password`=? WHERE client_id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1,mainVo.getUsername());
		ps.setString(2,mainVo.getPassword());
		ps.setInt(3,mainVo.getClient_id());
		ps.executeUpdate();
	}
	
	public static void updateUserName(MainLoginVO mainVo) throws SQLException{
		String sql = "UPDATE `mainloginvo` SET `username`=? WHERE client_id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1,mainVo.getUsername());
		ps.setInt(2,mainVo.getClient_id());
		ps.executeUpdate();
	}

}
