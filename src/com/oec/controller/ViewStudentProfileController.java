package com.oec.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oec.dao.StudentDAO;
import com.oec.vo.MainLoginVO;
import com.oec.vo.StudentVO;


@WebServlet("/ViewStudentProfileController")
public class ViewStudentProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().isNew()){
			request.getRequestDispatcher("LogoutController").forward(request, response);
			return;
		}	
		HttpSession session = request.getSession();
			MainLoginVO mlVo = (MainLoginVO) session.getAttribute("mainLoginVO");
			String username = mlVo.getUsername();
			try {
				request.setAttribute("studentVo",StudentDAO.getStudentDetails(username));
				request.getRequestDispatcher("viewStudentProfile.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
