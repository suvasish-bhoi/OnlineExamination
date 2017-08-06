package com.oec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.oec.util.JDBCUtil;
import com.oec.vo.StudentExamMapVO;
import com.oec.vo.StudentVO;

public class StudentExamMapDAO {
	
	private static Connection con = JDBCUtil.getConnection();
	
	public static boolean isApplied(int exam_id,int student_id) throws SQLException{
		String sql = "SELECT id FROM studentexammap where exam_id=? and student_id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1,exam_id);
		ps.setInt(2,student_id);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			return true;
		}
		return false;
	}
	
	public static void applyForExam(StudentExamMapVO seVo) throws SQLException{
		String sql = "INSERT INTO `studentexammap`(`exam_id`, `student_id`, `status`) VALUES (?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1,seVo.getExam_id());
		ps.setInt(2,seVo.getStudent_id());
		ps.setInt(3,seVo.getStatus());
		ps.executeUpdate();
		
	}
	
	public static List getStudentList(int exam_id) throws SQLException{
		String sql = "SELECT sv.student_id,sv.name,sv.email,sv.skills FROM studentexammap sem , studentvo sv where sem.student_id = sv.student_id AND sem.exam_id ="+exam_id;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		List ll = new ArrayList();
		while(rs.next()){
			StudentVO s = new StudentVO();
			s.setStudent_id(rs.getInt(1));
			s.setName(rs.getString(2));
			s.setEmail(rs.getString(3));
			s.setSkills(rs.getString(4));
			ll.add(s);
		}
		return ll;		
	}
	
	public static void approveStudentList(String[] studentList,int exam_id) throws SQLException{
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < studentList.length ; i++){
			sb.append(studentList[i]);
			if(i!=studentList.length-1){
				sb.append(",");
			}
		}
		String sql = "DELETE FROM studentexammap WHERE exam_id="+exam_id+" AND student_id NOT IN ("+sb.toString()+")";
		Statement st = con.createStatement();
		st.executeUpdate(sql);
		sql = "UPDATE studentexammap SET status = 1 WHERE exam_id="+exam_id+" AND student_id IN ("+sb.toString()+")";
		st.executeUpdate(sql);
	}

}
