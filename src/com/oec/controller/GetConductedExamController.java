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
import com.oec.vo.ExamVO;
import com.oec.vo.InstituteVO;
import com.oec.vo.MainLoginVO;


@WebServlet("/GetConductedExamController")
public class GetConductedExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().isNew()){
			request.getRequestDispatcher("LogoutController").forward(request, response);
			return;
		}
		HttpSession session = request.getSession();
		MainLoginVO mainLoginVO = (MainLoginVO) session.getAttribute("mainLoginVO");
		try {
			List<ExamVO> examList = ExamDAO.getConductedExamList(mainLoginVO.getClient_id());
			request.setAttribute("examList",examList);
			request.getRequestDispatcher("conductedExamListView.jsp").forward(request,response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}