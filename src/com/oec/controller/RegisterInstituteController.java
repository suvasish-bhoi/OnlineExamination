package com.oec.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oec.dao.InstituteDAO;
import com.oec.dao.SystemDAO;
import com.oec.util.Email;
import com.oec.util.InstituteUtil;
import com.oec.vo.EmailVO;
import com.oec.vo.InstituteVO;
import com.oec.vo.MainLoginVO;

@WebServlet("/RegisterInstituteController")
public class RegisterInstituteController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().isNew()){
			request.getRequestDispatcher("LogoutController").forward(request, response);
			return;
		}
		InstituteVO instituteVo = new InstituteVO();
		instituteVo.setAddress(request.getParameter("address"));
		instituteVo.setClient_id(InstituteUtil.generateClientId());
		instituteVo.setEmail(request.getParameter("email"));
		instituteVo.setMobile(request.getParameter("mobile"));
		instituteVo.setName(request.getParameter("name"));
		instituteVo.setPassword(InstituteUtil.generatePassword(instituteVo));
		instituteVo.setUsername(request.getParameter("userName"));
		
		EmailVO emailvo = new EmailVO();
		emailvo.setReceiverMailId(instituteVo.getEmail());
		emailvo.setSubject("Client_Id & Password from OnlineExaminationSystem");
		emailvo.setMainBody("Thank you for registration/n your client_id - "+instituteVo.getClient_id()+"/npassword - "+instituteVo.getPassword());
		new Email().sendEmail(emailvo);
		
		MainLoginVO mm = new MainLoginVO();
		mm.setClient_id(instituteVo.getClient_id());
		mm.setUsername(instituteVo.getUsername());
		mm.setPassword(instituteVo.getPassword());
		mm.setGroup_type("Institute");
		new SystemDAO().saveLogin(mm);
		new InstituteDAO().saveInstitute(instituteVo);
		response.sendRedirect("admin.jsp?result=Register_Succesfully");
		
	}

}
