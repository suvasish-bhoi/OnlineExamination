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
import com.oec.vo.MainLoginVO;


@WebServlet("/ExamHistoryController")
public class ExamHistoryController extends HttpServlet {
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().isNew()){
			request.getRequestDispatcher("LogoutController").forward(request, response);
			return;
		}
		HttpSession session = request.getSession();
		MainLoginVO mainVo = (MainLoginVO)session.getAttribute("mainLoginVO");
		int client_id = mainVo.getClient_id();
		try {
			List<ExamVO> examVo = ExamDAO.getExamVoList(client_id);
			request.setAttribute("examVo",examVo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("examHistory.jsp").forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
