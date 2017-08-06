package com.oec.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oec.dao.ConductExamDAO;
import com.oec.dao.StudentDAO;
import com.oec.util.Email;
import com.oec.vo.EmailVO;
import com.oec.vo.ExamStudentQuestionVO;

@WebServlet("/DisqualifyCandidateController")
public class DisqualifyCandidateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().isNew()){
			request.getRequestDispatcher("LogoutController").forward(request, response);
			return;
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json = "";
		if(br != null){
			json = br.readLine();
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		ExamStudentQuestionVO esqVo = mapper.readValue(json, ExamStudentQuestionVO.class);
		try {
			int student_id = esqVo.getStudent_id();
			int exam_id = esqVo.getExam_id();
			ConductExamDAO.deleteTempStore(student_id,exam_id);
			ConductExamDAO.deactivateStudent(student_id,exam_id);
			ConductExamDAO.deleteMarkMap(student_id,exam_id);
			EmailVO email = new EmailVO();
			email.setReceiverMailId(StudentDAO.getEmailById(student_id));
			email.setMainBody("You have misused the resources. So you has been disqualified");
			email.setSubject("Disqualified");
			Email.sendEmail(email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
