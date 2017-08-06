package com.oec.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oec.dao.InstituteDAO;
import com.oec.dao.SystemDAO;
import com.oec.util.Email;
import com.oec.vo.EmailVO;
import com.oec.vo.InstituteVO;
import com.oec.vo.MainLoginVO;


@WebServlet("/UpdateProfileController")
public class UpdateProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static boolean emailFlag;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().isNew()){
			request.getRequestDispatcher("LogoutController").forward(request, response);
			return;
		}
		int client_id = Integer.parseInt(request.getParameter("client_id"));
		InstituteVO instVoOld = new InstituteDAO().getInstituteDetails(client_id);
		InstituteVO instVo = new InstituteVO();
		instVo.setClient_id(client_id);
		instVo.setName(request.getParameter("name"));
		instVo.setAddress(request.getParameter("address"));
		instVo.setEmail(request.getParameter("email"));
		instVo.setMobile(request.getParameter("mobile"));
		instVo.setUsername(request.getParameter("userName"));
		instVo.setPassword(request.getParameter("password"));
		MainLoginVO mainVo = new MainLoginVO();
		mainVo.setClient_id(instVo.getClient_id());
		mainVo.setUsername(instVo.getUsername());
		mainVo.setPassword(instVo.getPassword());
		
		HttpSession session = request.getSession();
		session.setAttribute("mainLoginVO",mainVo);
		try {
			InstituteDAO.updateInstituteProfile(instVo);
			SystemDAO.updateLogin(mainVo);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		if(instVo.getEmail()!=instVoOld.getEmail()){
			emailFlag=true;
		}
	
		if(instVo.getPassword()!=instVoOld.getPassword() && instVo.getUsername()!=instVoOld.getUsername()){
			if(emailFlag){
				EmailVO eVo = new EmailVO();
				eVo.setMainBody("Your Profile has been updated. Username : "+instVo.getUsername()+" , Password :"+instVo.getPassword());
				eVo.setReceiverMailId(instVo.getEmail());
				eVo.setSubject("OnlineExamination : Profile Update");
				Email.sendEmail(eVo);
			}else{
				EmailVO eVo = new EmailVO();
				eVo.setMainBody("Your Profile has been updated. Username : "+instVo.getUsername()+" , Password :"+instVo.getPassword());
				eVo.setReceiverMailId(instVoOld.getEmail());
				eVo.setSubject("OnlineExamination : Profile Update");
				Email.sendEmail(eVo);
			}
		}
		else if(instVo.getUsername()!=instVoOld.getUsername()){
			if(emailFlag){
				EmailVO eVo = new EmailVO();
				eVo.setMainBody("Your Profile has been updated. Username : "+instVo.getUsername());
				eVo.setReceiverMailId(instVo.getEmail());
				eVo.setSubject("OnlineExamination : Profile Update");
				Email.sendEmail(eVo);
			}else{
				EmailVO eVo = new EmailVO();
				eVo.setMainBody("Your Profile has been updated. Username : "+instVo.getUsername());
				eVo.setReceiverMailId(instVoOld.getEmail());
				eVo.setSubject("OnlineExamination : Profile Update");
				Email.sendEmail(eVo);
			}
			
		}else if(instVo.getPassword()!=instVoOld.getPassword()){
			if(emailFlag){
				EmailVO eVo = new EmailVO();
				eVo.setMainBody("Your Profile has been updated. Password :"+instVo.getPassword());
				eVo.setReceiverMailId(instVo.getEmail());
				eVo.setSubject("OnlineExamination : Profile Update");
				Email.sendEmail(eVo);
			}else{
				EmailVO eVo = new EmailVO();
				eVo.setMainBody("Your Profile has been updated. Password :"+instVo.getPassword());
				eVo.setReceiverMailId(instVoOld.getEmail());
				eVo.setSubject("OnlineExamination : Profile Update");
				Email.sendEmail(eVo);
			}
		}
		request.setAttribute("instituteVo",new InstituteDAO().getInstituteDetails(client_id));
		request.getRequestDispatcher("instituteHome.jsp?result=Profile Updated Successfully").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
