package com.oec.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oec.dao.InstituteDAO;
import com.oec.vo.InstituteVO;


@WebServlet("/EditInstituteController")
public class EditInstituteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().isNew()){
			request.getRequestDispatcher("LogoutController").forward(request, response);
			return;
		}
		InstituteVO instVo = new InstituteDAO().getInstituteDetails(Integer.parseInt(request.getParameter("client_id")));
		request.setAttribute("instituteDetails",instVo);
		request.getRequestDispatcher("editInstitute.jsp").forward(request, response);
	}
}
