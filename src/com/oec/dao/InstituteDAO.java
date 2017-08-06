package com.oec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.oec.util.JDBCUtil;
import com.oec.vo.InstituteVO;

public class InstituteDAO {
	private static Connection con;
	static{
		con=JDBCUtil.getConnection();
	}
	
	
	public boolean checkClientId(int clientId){
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery("select client_id from institutevo where client_id="+clientId);
			if(resultSet.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void saveInstitute(InstituteVO instituteVo){
		try{
			PreparedStatement ps = con.prepareStatement("INSERT INTO `institutevo`(`client_id`, `name`, `username`, `password`, `email`, `address`, `mobile`, `status`) VALUES (?,?,?,?,?,?,?,?)");
			ps.setInt(1,instituteVo.getClient_id());
			ps.setString(2,instituteVo.getName());
			ps.setString(3,instituteVo.getUsername());
			ps.setString(4,instituteVo.getPassword());
			ps.setString(5,instituteVo.getEmail());
			ps.setString(6,instituteVo.getAddress());
			ps.setString(7,instituteVo.getMobile());
			ps.setInt(8,1);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	//retrive only name,client_id and status
	public List getInstituteinfo(){
		try{
			String sql = "select client_id,name,status from institutevo";
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			List<InstituteVO> instituteList = new ArrayList();
			while(resultSet.next()){
				InstituteVO instVo = new InstituteVO();
				instVo.setClient_id(resultSet.getInt(1));
				instVo.setName(resultSet.getString(2));
				instVo.setStatus(resultSet.getInt(3));
				instituteList.add(instVo);
			}
			return instituteList;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
		}
	public InstituteVO getInstituteDetails(int client_id){
		try{
			String sql = "select * from institutevo where client_id="+client_id;
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			if(resultSet.next()){
				InstituteVO instVo = new InstituteVO();
				instVo.setClient_id(resultSet.getInt(1));
				instVo.setName(resultSet.getString(2));
				instVo.setUsername(resultSet.getString(3));
				instVo.setPassword(resultSet.getString(4));
				instVo.setEmail(resultSet.getString(5));
				instVo.setAddress(resultSet.getString(6));
				instVo.setMobile(resultSet.getString(7));
				instVo.setStatus(resultSet.getInt(8));
				return instVo;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
		}
	
	
	public void updateInstitute(InstituteVO instVo,String value){
		try{
			String sql ="";
			if(value.equals("onlyUpdate")){
				StringBuilder sb = new StringBuilder("");
					sb.append("update institutevo set ");
					sb.append(" name='"+instVo.getName());
					sb.append("',username='"+instVo.getUsername());
					sb.append("',email='"+instVo.getEmail());
					sb.append("',mobile='"+instVo.getMobile());
					sb.append("',address='"+instVo.getAddress());
					sb.append("' where client_id="+instVo.getClient_id());
				sql=sb.toString();
			}else{
				StringBuilder sb = new StringBuilder("");
					sb.append("update institutevo set ");
					sb.append("name='"+instVo.getName());
					sb.append("',username='"+instVo.getUsername());
					sb.append("',email='"+instVo.getEmail());
					sb.append("',mobile='"+instVo.getMobile());
					sb.append("',address='"+instVo.getAddress());
					sb.append("',status=1");
					sb.append(" where client_id="+instVo.getClient_id());
			sql=sb.toString();
			}
			Statement statement = con.createStatement();
			statement.executeUpdate(sql);
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void deleteInstitute(int client_id){
		String sql = "update institutevo set status =0 where client_id="+client_id;
		try{
		Statement statement = con.createStatement();
		statement.executeUpdate(sql);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public boolean isActive(int client_id){
		try{
			String sql = "select status from institutevo where client_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,client_id);
			ResultSet resultSet = ps.executeQuery();
			if(resultSet.next()){
				if(resultSet.getInt(1)==1){
					return true;
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	
	public static void updateInstituteProfile(InstituteVO instVo) throws SQLException{
		String sql = "UPDATE `institutevo` SET `name`=?,`username`=?,`password`=?,`email`=?,`address`=?,`mobile`=? WHERE client_id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1,instVo.getName());
		ps.setString(2,instVo.getUsername());
		ps.setString(3,instVo.getPassword());
		ps.setString(4,instVo.getEmail());
		ps.setString(5,instVo.getAddress());
		ps.setString(6,instVo.getMobile());
		ps.setInt(7,instVo.getClient_id());
		ps.executeUpdate();
	}
	
	public static String getNameByClientId(int client_id) throws SQLException{
		String sql = "SELECT name from institutevo where client_id="+client_id;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		if(rs.next()){
			return rs.getString(1);
		}
		return "";
	}
	
	public static String getInstituteNameByClientId(int client_id) throws SQLException{
		String sql = "SELECT name FROM institutevo WHERE client_id="+client_id;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		if(rs.next()){
			return rs.getString(1);
		}else{
			return "";
		}
	}

}
