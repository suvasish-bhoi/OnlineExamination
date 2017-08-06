package com.oec.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oec.dao.ExamDAO;
import com.oec.dao.InstituteDAO;
import com.oec.dao.StudentDAO;
import com.oec.dao.SystemDAO;
import com.oec.vo.MainLoginVO;
import com.oec.vo.StudentVO;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try{
			int client_id = Integer.parseInt(request.getParameter("client_id"));
			String username  = request.getParameter("username");
			String password  = request.getParameter("password");
			
			MainLoginVO mainLoginVO = new MainLoginVO();
			mainLoginVO.setClient_id(client_id);
			mainLoginVO.setUsername(username);
			mainLoginVO.setPassword(password);
			
			SystemDAO systemDAO = new SystemDAO();
			String group_type = systemDAO.getGroupType(mainLoginVO);
			if(group_type!=null){
				if(group_type.equals("admin")){
					HttpSession session = request.getSession(true);
					session.setAttribute("mainLoginVO", mainLoginVO);
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin.jsp");
					requestDispatcher.forward(request, response);
					return;
				}else if(group_type.equals("Institute")){
					if(new InstituteDAO().isActive(client_id)){
						HttpSession session = request.getSession(true);
						session.setAttribute("mainLoginVO",mainLoginVO);
						request.setAttribute("instituteVo",new InstituteDAO().getInstituteDetails(client_id));
						request.getRequestDispatcher("/instituteHome.jsp").forward(request, response);
						return;
					}
				}else if(group_type.equals("Student")){
						HttpSession session = request.getSession(true);
						session.setAttribute("mainLoginVO",mainLoginVO);
						try {
							StudentVO sv = StudentDAO.getStudentDetails(username);
							session.setAttribute("studentVO",sv);
							request.setAttribute("commingExamList",ExamDAO.getCommingExamList());
							request.setAttribute("today'sExamList",ExamDAO.getTodaysExam(sv.getStudent_id()));
						} catch (SQLException e) {
							e.printStackTrace();
						}
						request.getRequestDispatcher("/studentHome.jsp").forward(request, response);
						return;
				}
			}else{
				response.sendRedirect("login.jsp?result=Wrong UserName or Password");
				return;
			}

		}catch(Exception e){
			response.sendRedirect("login.jsp");
			return;
		}
	}

}
