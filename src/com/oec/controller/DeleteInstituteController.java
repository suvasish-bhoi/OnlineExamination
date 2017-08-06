package com.oec.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oec.dao.InstituteDAO;
import com.oec.vo.InstituteVO;


@WebServlet("/DeleteInstituteController")
public class DeleteInstituteController extends HttpServlet {
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().isNew()){
			request.getRequestDispatcher("LogoutController").forward(request, response);
			return;
		}
		int client_id = Integer.parseInt(request.getParameter("client_id"));
		new InstituteDAO().deleteInstitute(client_id);
		List<InstituteVO> getInst = new InstituteDAO().getInstituteinfo();
		request.setAttribute("instituteDetails",getInst);
		request.getRequestDispatcher("viewAndManageInstitute.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
