package com.oec.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oec.dao.ExamDAO;
import com.oec.vo.StudentMarkVO;


@WebServlet("/GetAttempedStudent")
public class GetAttempedStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().isNew()){
			request.getRequestDispatcher("LogoutController").forward(request, response);
			return;
		}
		int exam_id = Integer.parseInt(request.getParameter("exam_id"));
		
		try {
			List<StudentMarkVO> smvo = ExamDAO.getStudentMarkList(exam_id);
			request.setAttribute("studentMarkList",smvo);
			request.setAttribute("exam_id",exam_id);
			request.getRequestDispatcher("studentMarkListView.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
