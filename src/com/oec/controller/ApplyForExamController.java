package com.oec.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oec.dao.ExamDAO;
import com.oec.dao.StudentExamMapDAO;
import com.oec.vo.StudentExamMapVO;


@WebServlet("/ApplyForExamController")
public class ApplyForExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().isNew()){
			request.getRequestDispatcher("LogoutController").forward(request, response);
			return;
		}
		StudentExamMapVO semVo = new StudentExamMapVO();
		semVo.setExam_id(Integer.parseInt(request.getParameter("exam_id")));
		semVo.setStudent_id(Integer.parseInt(request.getParameter("student_id")));
		semVo.setStatus(0);
		try {
			StudentExamMapDAO.applyForExam(semVo);
			request.setAttribute("UpcomingExams",ExamDAO.getCommingExamList());
			request.getRequestDispatcher("upcomingExamView.jsp").forward(request, response);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		request.getRequestDispatcher("upcomingExamView.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
