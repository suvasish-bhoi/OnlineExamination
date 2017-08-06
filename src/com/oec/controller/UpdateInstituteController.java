package com.oec.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oec.dao.InstituteDAO;
import com.oec.dao.SystemDAO;
import com.oec.util.Email;
import com.oec.vo.EmailVO;
import com.oec.vo.InstituteVO;
import com.oec.vo.MainLoginVO;

@WebServlet("/UpdateInstituteController")
public class UpdateInstituteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().isNew()){
			request.getRequestDispatcher("LogoutController").forward(request, response);
			return;
		}
		InstituteVO instVoOld = new InstituteDAO().getInstituteDetails(Integer.parseInt(request.getParameter("client_id")));
		instVoOld.setPassword(null);
		instVoOld.setStatus(0);
		
		InstituteVO instVo = new InstituteVO();
		instVo.setClient_id(Integer.parseInt(request.getParameter("client_id")));
		instVo.setName(request.getParameter("name"));
		instVo.setEmail(request.getParameter("email"));
		instVo.setAddress(request.getParameter("address"));
		instVo.setUsername(request.getParameter("userName"));
		instVo.setMobile(request.getParameter("mobile"));
		String operation = request.getParameter("operation");
		if(operation.equals("Update")){
			operation="onlyUpdate";
		}else{
			operation="activeAndUpdate";
			instVoOld.setStatus(1);
		}

		MainLoginVO mainVo = new MainLoginVO();
		mainVo.setClient_id(instVo.getClient_id());
		mainVo.setUsername(instVo.getUsername());
		
		try {
			SystemDAO.updateUserName(mainVo);
			new InstituteDAO().updateInstitute(instVo, operation);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(instVoOld.toString()!=instVo.toString()){
			EmailVO evo = new EmailVO();
			evo.setMainBody("Your Profile has been change by ADMIN. Username :"+mainVo.getUsername()+", Email : "+instVo.getEmail()+", Mobile :"+instVo.getMobile());
			evo.setReceiverMailId(instVo.getEmail());
			evo.setSubject("OnlineExamination : Profile updated");
			Email.sendEmail(evo);
		}
		
		
		response.sendRedirect("admin.jsp?result=Updated Succesfully");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
