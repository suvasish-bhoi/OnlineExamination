package com.oec.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oec.dao.ExamDAO;
import com.oec.vo.ExamInstituteVO;
import com.oec.vo.StudentVO;


@WebServlet("/GetAttemptExamController")
public class GetAttemptExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().isNew()){
			request.getRequestDispatcher("LogoutController").forward(request, response);
			return;
		}
		HttpSession session = request.getSession();
		StudentVO studentVO = (StudentVO) session.getAttribute("studentVO");
		try {
			List<ExamInstituteVO> examList = ExamDAO.getAttemptExamList(studentVO.getStudent_id());
			request.setAttribute("examList",examList);
			request.getRequestDispatcher("attempExamListView.jsp").forward(request,response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
