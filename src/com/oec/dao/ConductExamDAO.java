package com.oec.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import com.oec.util.JDBCUtil;
import com.oec.vo.AnswerSubmitVO;
import com.oec.vo.ExamStudentQuestionVO;
import com.oec.vo.QuestionAnswerVO;

public class ConductExamDAO {
	private static Connection con = JDBCUtil.getConnection();

	public static int getRandomInt(int limit) throws SQLException{
		return new Random().nextInt(limit);
	}
	
	public static List<Integer> getQuestionId(int exam_id) throws SQLException{
		String sql = "SELECT question_id FROM questionanswervo WHERE exam_id="+exam_id;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		List ll = new LinkedList();
		while(rs.next()){
			ll.add(rs.getInt(1));
		}
		return ll;
	}
	
	public static ExamStudentQuestionVO getQuestion(int question_id) throws SQLException{
		String sql = "SELECT `question`, `a`, `b`, `c`, `d`, `answer`, `mark`, `minus` FROM `questionanswervo` WHERE question_id="+question_id;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		ExamStudentQuestionVO esqVo = new ExamStudentQuestionVO();
		if(rs.next()){
			esqVo.setQuestion(rs.getString(1));
			esqVo.setA(rs.getString(2));
			esqVo.setB(rs.getString(3));
			esqVo.setC(rs.getString(4));
			esqVo.setD(rs.getString(5));
		}
		return esqVo;
	}
	
	public static int getQuestionCount(int exam_id) throws SQLException{
		String sql = "select count(question_id) as id from questionanswervo where exam_id="+exam_id;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		if(rs.next()){
			return rs.getInt(1);
		}
		return 0;
	}
	
	public static int getDuration(int exam_id) throws SQLException{
		String sql = "SELECT duration FROM examvo where exam_id="+exam_id;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		if(rs.next()){
			return rs.getInt(1);
		}
		return 0;
	}
	
	public static void insertTempData(int student_id,int exam_id,String questionList,int min,int sec,int occurance) throws SQLException{
		PreparedStatement ps=null;
		String sql="";
		if(getOccurance(exam_id, student_id)>0 && getOccurance(exam_id, student_id)<3){
			sql = "UPDATE `tempstore` SET `Question_id`=?,`min`=?,`sec`=?,`occurance`=? WHERE exam_id="+exam_id+" AND student_id="+student_id;
			ps = con.prepareStatement(sql);
			ps.setString(1,questionList);
			ps.setInt(2,min);
			ps.setInt(3,sec);
			ps.setInt(4,occurance);
			ps.executeUpdate();
		}else{
			sql = "INSERT INTO `tempstore`(`exam_id`, `student_id`, `Question_id`, `min`,`sec`,`occurance`) VALUES (?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1,exam_id);
			ps.setInt(2,student_id);
			ps.setString(3,questionList);
			ps.setInt(4,min);
			ps.setInt(5,sec);
			ps.setInt(6,occurance);
			ps.executeUpdate();
		}
	}
	
	public static String getCommaSeparatedFromList(List l){
		return l.toString().substring(1,l.toString().length()-1);
	}
	
	public static int getOccurance(int exam_id,int student_id) throws SQLException{
		String sql = "SELECT occurance FROM tempStore WHERE exam_id=? AND student_id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1,exam_id);
		ps.setInt(2,student_id);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			return rs.getInt(1);
		}
		return -1;
	}
	
	public static void removeStudent(int student_id,int exam_id) throws SQLException{
		try{
			String sql = "DELETE FROM `tempstore` WHERE student_id="+student_id+" AND exam_id="+exam_id;
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			sql = "SELECT * FROM `studentexammap` WHERE student_id="+student_id+" AND exam_id="+exam_id;
			st.executeUpdate(sql);
		}catch(Exception e){
			
		}
	}
	
	public static LinkedList<Integer> getQuestionList(int student_id,int exam_id) throws SQLException{
		String sql = "SELECT question_id FROM tempstore WHERE exam_id="+exam_id+" AND student_id="+student_id;
		StringBuilder list = new StringBuilder();
		LinkedList<Integer> ll = new LinkedList<Integer>();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		if(rs.next()){
			list.append(rs.getString(1));
		}
		String[] s = list.toString().split(",");
		for(int i=0;i<s.length;i++){
			ll.add(Integer.parseInt(s[i].trim()));
		}
		return ll;
	}
	
	public static void updateTempStore(AnswerSubmitVO asvo) throws SQLException{
		String sql = "UPDATE `tempstore` SET `Question_id`=?,`min`=?,`sec`=? WHERE exam_id=? AND student_id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1,getCommaSeparatedFromList(asvo.getQuestion_idList()));
		ps.setInt(2,asvo.getMin());
		ps.setInt(3,asvo.getSec());
		ps.setInt(4,asvo.getExam_id());
		ps.setInt(5,asvo.getStudent_id());
		ps.executeUpdate();
	}
	
	public static void insertMark(AnswerSubmitVO asvo) throws SQLException{
		String sql = "INSERT INTO `studentmarkmap`(`exam_id`, `student_id`, `question_id`, `answer`, `mark`) VALUES (?,?,?,?,?)";
		int question_id = asvo.getQuestion_id();
		String answer = asvo.getAnswer();
		double mark = getMark(question_id, answer);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1,asvo.getExam_id());
		ps.setInt(2,asvo.getStudent_id());
		ps.setInt(3,question_id);
		ps.setString(4,answer);
		ps.setDouble(5,mark);
		ps.executeUpdate();
	}
	
	public static double getMark(int question_id,String answer) throws SQLException{
		String sql = "SELECT answer,mark,minus FROM questionanswervo where question_id="+question_id;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		if(rs.next()){
			if(rs.getString(1).equals(answer)){
				return rs.getDouble(2);
			}else{
				return -rs.getDouble(3);
			}
		}
		return 0;
	}
	
	public static List getListFromString(String questionList){
		String[] s = questionList.split(",");
		List ll = new LinkedList<Integer>();
		for(int i = 0 ;i<s.length-1 ; i++){
			ll.add(s[i]);
		}
		return ll;
	}
	
	public static void deleteTempStore(int student_id,int exam_id) throws SQLException{
		String sql = "Delete from tempstore where student_id="+student_id+" AND exam_id="+exam_id;
		Statement st = con.createStatement();
		st.executeUpdate(sql);
	}
	
	public static void deleteMarkMap(int student_id,int exam_id) throws SQLException{
		String sql = "DELETE FROM `studentmarkmap` WHERE student_id="+student_id+" AND exam_id="+exam_id;
		con.createStatement().executeUpdate(sql);
	}
	
	public static void deactivateStudent(int student_id,int exam_id) throws SQLException{
		String sql = "UPDATE `studentexammap` SET `status`=0 WHERE  student_id="+student_id+" AND exam_id="+exam_id;
		Statement st = con.createStatement();
		st.executeUpdate(sql);
	}
	public static AnswerSubmitVO getAnswerSubmitVO(ExamStudentQuestionVO esqVo){
		AnswerSubmitVO aa = new AnswerSubmitVO();
		aa.setAnswer(esqVo.getAttempt());
		aa.setExam_id(esqVo.getExam_id());
		aa.setMin(esqVo.getMin());
		aa.setSec(esqVo.getSec());
		aa.setQuestion_id(esqVo.getQuestion_id());
		aa.setStudent_id(esqVo.getStudent_id());
		aa.setQuestion_idList(esqVo.getQuestion_idList());
		aa.setQuestion_no(esqVo.getQuestion_no());
		
		return aa;
	}
	public static String getRemainingHour(int student_id,int exam_id) throws SQLException{
		String sql="SELECT min,sec from tempstore WHERE exam_id="+exam_id+" AND student_id="+student_id;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		if(rs.next()){
			return rs.getInt(1)+","+rs.getInt(2);
		}
		return ",";
	}
	public static void updateTime(int student_id,int exam_id,int min,int sec) throws SQLException{
		String sql = "UPDATE `tempstore` SET `min`="+min+",`sec`="+sec+" WHERE `exam_id`="+exam_id+" AND`student_id`="+student_id;
		Statement st = con.createStatement();
		st.executeUpdate(sql);
	}
}
