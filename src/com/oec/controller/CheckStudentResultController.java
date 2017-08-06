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
import com.oec.dao.StudentDAO;
import com.oec.vo.ResultVO;
import com.oec.vo.StudentVO;


@WebServlet("/CheckStudentResultController")
public class CheckStudentResultController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().isNew()){
			request.getRequestDispatcher("LogoutController").forward(request, response);
			return;
		}
		int exam_id = Integer.parseInt(request.getParameter("exam_id"));
		int student_id =0;
		HttpSession session = request.getSession();
		if((session.getAttribute("studentVO"))==null || (session.getAttribute("studentVO")).toString().equals("")){
			student_id = Integer.parseInt(request.getParameter("student_id"));
		}else{
			student_id = ((StudentVO) session.getAttribute("studentVO")).getStudent_id();
		}
		
		try {
			List<ResultVO> resultList = ExamDAO.getResultList(student_id,exam_id);
			request.setAttribute("examvo",ExamDAO.getExamVOByExamId(exam_id));
			request.setAttribute("studentvo",StudentDAO.getStudentDetailsById(student_id));
			request.setAttribute("ResultList",resultList);
			request.getRequestDispatcher("displayResultView.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
