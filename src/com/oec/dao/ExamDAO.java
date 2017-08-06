package com.oec.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.oec.util.JDBCUtil;
import com.oec.vo.ExamInstituteStudentVO;
import com.oec.vo.ExamInstituteVO;
import com.oec.vo.ExamVO;
import com.oec.vo.ResultVO;
import com.oec.vo.StudentMarkVO;

public class ExamDAO {
	
	private static Connection con = JDBCUtil.getConnection();

	public static void saveExam(ExamVO evo) throws SQLException {
		String sql = "INSERT INTO `examvo`(`client_id`, `topic`, `dateofcreation`, `dateofexam`,`description`,`duration`) VALUES (?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, evo.getClient_id());
		ps.setString(2, evo.getTopic());
		ps.setString(3, evo.getDateOfCreation());
		ps.setString(4, evo.getDateOfExam());
		ps.setString(5, evo.getDescription());
		ps.setLong(6, evo.getDuration());
		ps.executeUpdate();
	}

	public static List<ExamVO> getExamVoList(int client_id) throws SQLException {
		List<ExamVO> ll = new ArrayList<ExamVO>();
		String sql = "SELECT `exam_id`,`topic`, `dateofcreation`, `dateofexam`,`description`,`duration` FROM `examvo` WHERE client_id=? ORDER BY dateofexam";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, client_id);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ExamVO e = new ExamVO();
			e.setClient_id(client_id);
			e.setExam_id(rs.getInt(1));
			e.setTopic(rs.getString(2));
			e.setDateOfCreation(rs.getString(3));
			e.setDateOfExam(rs.getString(4));
			e.setDescription(rs.getString(5));
			e.setDuration(rs.getLong(6));
			ll.add(e);
		}
		return ll;
	}

	public static boolean saveQuestionAnswer(String path, int exam_id) throws IOException, SQLException {
		con.createStatement().executeUpdate("DELETE FROM questionanswervo WHERE exam_id="+exam_id);		
		String separatorConstant = "~~~~";
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));
		String line = null;
		while ((line = br.readLine()) != null) {
			String wholeLine = line;
			if(!wholeLine.trim().equals("")){
				String[] token = wholeLine.split(separatorConstant);
				saveEachQuestion(token, exam_id);
			}
		}
		return true;
	}
	

	public static void saveEachQuestion(String[] s, int exam_id) throws SQLException {
		for (int i = 0; i < s.length; i++) {
		}
		String sql = "INSERT INTO `questionanswervo`(`exam_id`, `question`, `a`, `b`, `c`, `d`, `answer`, `mark`, `minus`) VALUES (?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, exam_id);// exam_id
		ps.setString(2, s[0]);// question
		ps.setString(3, s[1]);// a
		ps.setString(4, s[2]);// b
		ps.setString(5, s[3]);// c
		ps.setString(6, s[4]);// d
		ps.setString(7, s[5]);// answer
		ps.setFloat(8, Float.parseFloat(s[6]));// mark
		ps.setFloat(9, Float.parseFloat(s[7]));// minus
		ps.executeUpdate();

	}

	public static void deleteExam(int exam_id) throws SQLException {
		String sql = "delete from examvo where exam_id=" + exam_id;
		Statement st = con.createStatement();
		st.executeUpdate(sql);
		sql = "delete from studentexammap where exam_id="+exam_id;
		st.executeUpdate(sql);
	}

	public static List getCommingExamList() throws SQLException {
		String sql = "SELECT e.exam_id,i.name,e.topic,e.dateofexam,e.description,e.duration FROM examvo e,institutevo i WHERE e.client_id=i.client_id AND e.dateofexam>now()";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		List ll = new ArrayList<ExamInstituteVO>();
		while (rs.next()) {
			ExamInstituteVO ievo = new ExamInstituteVO();
			ievo.setExam_id(rs.getInt(1));
			ievo.setName(rs.getString(2));
			ievo.setTopic(rs.getString(3));
			ievo.setDateOfExam(rs.getString(4));
			ievo.setDescription(rs.getString(5));
			ievo.setDuration(rs.getLong(6));
			ll.add(ievo);
		}
		return ll;
	}

	public static ExamVO getExamVOByExamId(int exam_id) throws SQLException {
		String sql = "SELECT `exam_id`, `client_id`, `topic`, `dateofcreation`, `dateofexam`, `description`,`duration` FROM `examvo` WHERE exam_id="
				+ exam_id;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		if (rs.next()) {
			ExamVO evo = new ExamVO();
			evo.setExam_id(rs.getInt(1));
			evo.setClient_id(rs.getInt(2));
			evo.setTopic(rs.getString(3));
			evo.setDateOfCreation(rs.getString(4));
			evo.setDateOfExam(rs.getString(5));
			evo.setDescription(rs.getString(6));
			evo.setDuration(rs.getLong(7));
			return evo;
		}
		return null;
	}

	public static String getTopicFromExamId(int exam_id) throws SQLException {
		String sql = "SELECT topic FROM examvo WHERE exam_id=" + exam_id;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		if (rs.next()) {
			return rs.getString(1);
		}
		return null;
	}

	public static List getTodaysExam(int student_id) throws SQLException {
		String sql = "SELECT sem.exam_id,e.client_id,e.topic,i.name,e.duration "
				+ "FROM studentexammap sem,examvo e,institutevo i "
				+ "where sem.exam_id=e.exam_id AND e.client_id=i.client_id "
				+ "AND EXTRACT(HOUR from dateofexam)< TIME(NOW()) "
				+ "AND EXTRACT(DAY from dateofexam)=EXTRACT(DAY FROM CURDATE()) "
				+ "AND EXTRACT(MONTH from dateofexam)=EXTRACT(MONTH FROM CURDATE()) "
				+ "AND EXTRACT(YEAR from dateofexam)=EXTRACT(YEAR FROM CURDATE()) AND sem.student_id=" + student_id
				+ " AND sem.status=1";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		List ll = new ArrayList<ExamInstituteStudentVO>();
		while (rs.next()) {
			ExamInstituteStudentVO eisVo = new ExamInstituteStudentVO();
			eisVo.setExam_id(rs.getInt(1));
			eisVo.setClient_id(rs.getInt(2));
			eisVo.setTopic(rs.getString(3));
			eisVo.setClient_name(rs.getString(4));
			eisVo.setDuration(rs.getLong(5));
			eisVo.setStudent_id(student_id);
			ll.add(eisVo);
		}
		return ll;
	}

	public static int getHour(int exam_id) throws SQLException {
		String sql = "select duration from examvo where exam_id=" + exam_id;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		if (rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}

	public static List<ExamInstituteVO> getAttemptExamList(int student_id) throws SQLException {
		String sql = "SELECT distinct s.exam_id,e.topic,i.name,e.dateofexam FROM studentmarkmap s "
				+ "INNER JOIN examvo e ON s.exam_id=e.exam_id " + "INNER JOIN institutevo i ON e.client_id=i.client_id "
				+ "AND s.student_id=" + student_id;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		List<ExamInstituteVO> ll = new ArrayList<>();
		while (rs.next()) {
			ExamInstituteVO eivo = new ExamInstituteVO();
			eivo.setExam_id(rs.getInt(1));
			eivo.setTopic(rs.getString(2));
			eivo.setName(rs.getString(3));
			eivo.setDateOfExam(rs.getString(4));
			ll.add(eivo);
		}
		return ll;
	}

	public static List<ExamVO> getConductedExamList(int client_id) throws SQLException {
		String sql = "SELECT evo.exam_id,evo.topic,evo.dateofexam," + "(SELECT COUNT(DISTINCT student_id) FROM "
				+ "studentmarkmap smm where evo.exam_id=smm.exam_id) "
				+ "as student FROM `examvo` evo WHERE dateofexam < now() AND evo.client_id="+client_id;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		List<ExamVO> ll = new ArrayList<>();
		while (rs.next()) {
			ExamVO evo = new ExamVO();
			evo.setExam_id(rs.getInt(1));
			evo.setTopic(rs.getString(2));
			evo.setDateOfExam(rs.getString(3));
			evo.setDuration(rs.getLong(4));
			ll.add(evo);
		}
		return ll;
	}

	public static List<ResultVO> getResultList(int student_id, int exam_id) throws SQLException {
		String sql = "SELECT qav.question,case when qav.answer = 'a' then qav.a " + "when qav.answer = 'b' then qav.b "
				+ "when qav.answer = 'c' then qav.c " + "when qav.answer = 'd' then qav.d" + " end as answer,case "
				+ "when smm.answer = 'a' then qav.a " + "when smm.answer = 'b' then qav.b"
				+ "	when smm.answer = 'c' then qav.c " + "when smm.answer = 'd' then qav.d"
				+ " end as attemp,smm.mark,qav.mark FROM studentmarkmap smm INNER JOIN   questionanswervo qav ON smm.question_id = qav.question_id"
				+ " WHERE smm.student_id=" + student_id + " AND smm.exam_id=" + exam_id;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		List ll = new ArrayList<ResultVO>();
		while (rs.next()) {
			ResultVO rvo = new ResultVO();
			rvo.setQuestion(rs.getString(1));
			rvo.setAnswer(rs.getString(2));
			rvo.setAttempt(rs.getString(3));
			rvo.setMark(rs.getDouble(4));
			rvo.setTotal(rs.getDouble(5));
			ll.add(rvo);
		}
		return ll;
	}

	public static List<StudentMarkVO> getStudentMarkList(int exam_id) throws SQLException {
		String sql = "SELECT distinct sv.student_id,sv.name,(SELECT sum(mark) " + "FROM studentmarkmap smm "
				+ "WHERE smm.student_id=sv.student_id) as mark " + "FROM studentmarkmap smm INNER JOIN studentvo sv ON sv.student_id=smm.student_id WHERE (SELECT sum(mark) "
				+ "FROM studentmarkmap smm WHERE smm.student_id=sv.student_id) IS NOT NULL" + " AND smm.exam_id=" + exam_id + ""
				+ " ORDER BY (SELECT sum(mark) " + "FROM studentmarkmap smm WHERE smm.student_id=sv.student_id)";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		List<StudentMarkVO> studentMarkList = new ArrayList<>();
		while (rs.next()) {
			StudentMarkVO smv = new StudentMarkVO();
			smv.setStudent_id(rs.getInt(1));
			smv.setName(rs.getString(2));
			smv.setMark(rs.getDouble(3));
			studentMarkList.add(smv);
		}
		return studentMarkList;
	}

	public static double getSumMarkbyExamId(int exam_id) throws SQLException {
		String sql = "SELECT sum(mark) FROM questionanswervo WHERE exam_id=" + exam_id;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		if (rs.next()) {
			return rs.getDouble(1);
		}
		return 0;
	}
}
