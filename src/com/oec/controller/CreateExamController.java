package com.oec.controller;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oec.dao.ExamDAO;
import com.oec.dao.InstituteDAO;
import com.oec.util.DateUtil;
import com.oec.vo.ExamVO;
import com.oec.vo.MainLoginVO;

@WebServlet("/CreateExamController")
public class CreateExamController extends HttpServlet {
	       
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().isNew()){
			request.getRequestDispatcher("LogoutController").forward(request, response);
			return;
		}
		HttpSession session = request.getSession();
		String dateTime = request.getParameter("dateofexam");
		MainLoginVO mainVo = (MainLoginVO)session.getAttribute("mainLoginVO");
		ExamVO evo = new ExamVO();
		evo.setClient_id(mainVo.getClient_id());
		evo.setTopic(request.getParameter("topic"));
		evo.setDateOfCreation(DateUtil.getDate());
		evo.setDuration(Long.parseLong(request.getParameter("duration")));
		evo.setDescription(request.getParameter("description"));
		
		try {
			evo.setDateOfExam(DateUtil.getFormatedDate(request.getParameter("dateofexam")));
			ExamDAO.saveExam(evo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("instituteVo",new InstituteDAO().getInstituteDetails(mainVo.getClient_id()));
		request.getRequestDispatcher("instituteHome.jsp?result=Exam Added Succesfully").forward(request,response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
