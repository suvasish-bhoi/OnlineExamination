package com.oec.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oec.dao.InstituteDAO;
import com.oec.vo.InstituteVO;
import com.oec.vo.MainLoginVO;


@WebServlet("/ViewProfileController")
public class ViewProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().isNew()){
			request.getRequestDispatcher("LogoutController").forward(request, response);
			return;
		}
		HttpSession session = request.getSession();
		MainLoginVO mainVo = (MainLoginVO)session.getAttribute("mainLoginVO");
		int client_id = mainVo.getClient_id();
		InstituteVO instVo = new InstituteDAO().getInstituteDetails(client_id);
		request.setAttribute("instituteVo",instVo);
		request.getRequestDispatcher("viewInstituteProfile.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
