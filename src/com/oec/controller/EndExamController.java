package com.oec.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oec.dao.ConductExamDAO;
import com.oec.dao.ExamDAO;
import com.oec.dao.StudentDAO;
import com.oec.util.Email;
import com.oec.vo.AnswerSubmitVO;
import com.oec.vo.EmailVO;
import com.oec.vo.StudentVO;


@WebServlet("/EndExamController")
public class EndExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().isNew()){
			request.getRequestDispatcher("LogoutController").forward(request, response);
			return;
		}
		AnswerSubmitVO answerVo = (AnswerSubmitVO) request.getAttribute("answervo");
		try {
			ConductExamDAO.deleteTempStore(answerVo.getStudent_id(),answerVo.getExam_id());
			if(!answerVo.getAnswer().equals("n")){
				ConductExamDAO.insertMark(answerVo);
			}
			ConductExamDAO.deactivateStudent(answerVo.getStudent_id(),answerVo.getExam_id());
			EmailVO email = new EmailVO();
			email.setReceiverMailId(StudentDAO.getEmailById(answerVo.getStudent_id()));
			email.setMainBody("Thanks for appearing the exam. you can check your result in the Result option in your Page");
			email.setSubject("Exam Complete");
			Email.sendEmail(email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
