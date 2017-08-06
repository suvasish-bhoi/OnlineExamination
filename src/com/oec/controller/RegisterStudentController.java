package com.oec.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oec.dao.StudentDAO;
import com.oec.dao.SystemDAO;
import com.oec.util.Email;
import com.oec.vo.EmailVO;
import com.oec.vo.MainLoginVO;
import com.oec.vo.StudentVO;


@WebServlet("/RegisterStudentController")
public class RegisterStudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().isNew()){
			request.getRequestDispatcher("LogoutController").forward(request, response);
			return;
		}
		String email = request.getParameter("email");
		String userName = request.getParameter("userName");
		try {
			if(StudentDAO.checkEmail(email)){
				request.getRequestDispatcher("registerStudentPage.jsp?result=Email id already exist").forward(request, response);
			}else if(StudentDAO.checkUserName(userName)){
				request.getRequestDispatcher("registerStudentPage.jsp?result=username already exist").forward(request, response);
			}else{
				StudentVO sVo = new StudentVO();
				sVo.setName(request.getParameter("name"));
				sVo.setAddress(request.getParameter("address"));
				sVo.setEmail(email);
				sVo.setUserName(userName);
				sVo.setPassword(request.getParameter("password"));
				sVo.setMobile(request.getParameter("mobile"));
				sVo.setSkills(request.getParameter("skill"));
				
				
				MainLoginVO mVo = new MainLoginVO();
				mVo.setClient_id(999);
				mVo.setUsername(userName);
				mVo.setPassword(sVo.getPassword());
				mVo.setGroup_type("Student");
				new SystemDAO().saveLogin(mVo);
				StudentDAO.saveStudent(sVo);
				EmailVO evo = new EmailVO();
				evo.setMainBody("Hello , your username :"+sVo.getUserName()+",Password :"+sVo.getPassword()+",client_id :999");
				evo.setReceiverMailId(sVo.getEmail());
				evo.setSubject("welcome to Our Website");
				Email.sendEmail(evo);
				
				response.sendRedirect("LoginController?client_id=999&username="+mVo.getUsername()+"&password="+mVo.getPassword());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
