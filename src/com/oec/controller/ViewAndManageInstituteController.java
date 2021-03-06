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


@WebServlet("/ViewAndManageInstituteController")
public class ViewAndManageInstituteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().isNew()){
			request.getRequestDispatcher("LogoutController").forward(request, response);
			return;
		}
		List<InstituteVO> getInst = new InstituteDAO().getInstituteinfo();
		request.setAttribute("instituteDetails",getInst);
		request.getRequestDispatcher("viewAndManageInstitute.jsp").forward(request, response);
	}

}
