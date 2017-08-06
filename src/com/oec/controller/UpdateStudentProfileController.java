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
import com.oec.dao.SystemDAO;
import com.oec.util.Email;
import com.oec.vo.EmailVO;
import com.oec.vo.MainLoginVO;
import com.oec.vo.StudentVO;


@WebServlet("/UpdateStudentProfileController")
public class UpdateStudentProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().isNew()){
			request.getRequestDispatcher("LogoutController").forward(request, response);
			return;
		}
		int id = Integer.parseInt(request.getParameter("id"));
		StudentVO sVoOld;
		try {
			sVoOld = StudentDAO.getStudentDetailsById(id);
		
		StudentVO sVo = new StudentVO();
		sVo.setStudent_id(id);
		sVo.setName(request.getParameter("name"));
		sVo.setAddress(request.getParameter("address"));
		sVo.setEmail(request.getParameter("email"));
		sVo.setMobile(request.getParameter("mobile"));
		sVo.setUserName(request.getParameter("userName"));
		sVo.setPassword(request.getParameter("password"));
		sVo.setSkills(request.getParameter("skill"));
		
		MainLoginVO mainVo = new MainLoginVO();
		mainVo.setClient_id(999);
		mainVo.setUsername(sVo.getUserName());
		mainVo.setPassword(sVo.getPassword());
		
		HttpSession session = request.getSession();
		session.setAttribute("mainLoginVO",mainVo);
		try {
			StudentDAO.updateProfile(sVo);
			SystemDAO.updateLogin(mainVo);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		boolean emailFlag=false;
		if(sVo.getEmail()!=sVoOld.getEmail()){
			emailFlag=true;
		}
	
		if(sVo.getPassword()!=sVoOld.getPassword() && sVo.getUserName()!=sVoOld.getUserName()){
			if(emailFlag){
				EmailVO eVo = new EmailVO();
				eVo.setMainBody("Your Profile has been updated. Username : "+sVo.getUserName()+" , Password :"+sVo.getPassword());
				eVo.setReceiverMailId(sVo.getEmail());
				eVo.setSubject("OnlineExamination : Profile Update");
				Email.sendEmail(eVo);
			}else{
				EmailVO eVo = new EmailVO();
				eVo.setMainBody("Your Profile has been updated. Username : "+sVo.getUserName()+" , Password :"+sVo.getPassword());
				eVo.setReceiverMailId(sVoOld.getEmail());
				eVo.setSubject("OnlineExamination : Profile Update");
				Email.sendEmail(eVo);
			}
		}
		else if(sVo.getUserName()!=sVoOld.getUserName()){
			if(emailFlag){
				EmailVO eVo = new EmailVO();
				eVo.setMainBody("Your Profile has been updated. Username : "+sVo.getUserName());
				eVo.setReceiverMailId(sVo.getEmail());
				eVo.setSubject("OnlineExamination : Profile Update");
				Email.sendEmail(eVo);
			}else{
				EmailVO eVo = new EmailVO();
				eVo.setMainBody("Your Profile has been updated. Username : "+sVo.getUserName());
				eVo.setReceiverMailId(sVoOld.getEmail());
				eVo.setSubject("OnlineExamination : Profile Update");
				Email.sendEmail(eVo);
			}
			
		}else if(sVo.getPassword()!=sVoOld.getPassword()){
			if(emailFlag){
				EmailVO eVo = new EmailVO();
				eVo.setMainBody("Your Profile has been updated. Password :"+sVo.getPassword());
				eVo.setReceiverMailId(sVo.getEmail());
				eVo.setSubject("OnlineExamination : Profile Update");
				Email.sendEmail(eVo);
			}else{
				EmailVO eVo = new EmailVO();
				eVo.setMainBody("Your Profile has been updated. Password :"+sVo.getPassword());
				eVo.setReceiverMailId(sVoOld.getEmail());
				eVo.setSubject("OnlineExamination : Profile Update");
				Email.sendEmail(eVo);
			}
		}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		request.getRequestDispatcher("studentHome.jsp?result=Profile Updated Successfully").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
