package com.oec.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oec.dao.ExamDAO;
import com.oec.dao.InstituteDAO;
import com.oec.dao.StudentDAO;
import com.oec.dao.StudentExamMapDAO;
import com.oec.dao.SystemDAO;
import com.oec.util.Email;
import com.oec.vo.EmailVO;
import com.oec.vo.ExamVO;
import com.oec.vo.MainLoginVO;
import com.oec.vo.StudentVO;


@WebServlet("/ApproveStudentController")
public class ApproveStudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().isNew()){
			request.getRequestDispatcher("LogoutController").forward(request, response);
			return;
		}
		int exam_id = Integer.parseInt(request.getParameter("exam_id"));
		HttpSession session = request.getSession();
		MainLoginVO mVo = (MainLoginVO) session.getAttribute("mainLoginVO");
		try {
			String[] studentIdList = request.getParameterValues("id");
				StudentExamMapDAO.approveStudentList(studentIdList, exam_id);
				ExamVO exVo = ExamDAO.getExamVOByExamId(exam_id);
				EmailVO evo = new EmailVO();
				evo.setMainBody("Your Application has been approved for '"+exVo.getTopic()+"' exam conducting by "+new InstituteDAO().getInstituteDetails(mVo.getClient_id()).getName()
						+" is held on "+exVo.getDateOfExam());
				evo.setSubject("Exam Application Approved");
				for(int i = 0 ; i < studentIdList.length ; i++){
					String email = StudentDAO.getEmailById(Integer.parseInt(studentIdList[i]));
					if(email!=null){
						evo.setReceiverMailId(email);
						Email.sendEmail(evo);
					}
				}
			List<ExamVO> eVo = ExamDAO.getExamVoList(mVo.getClient_id());
			request.setAttribute("examVoList",eVo);
			request.getRequestDispatcher("examStudentListView.jsp?result=Selected Students Approved").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			List<StudentVO> studentList = null;
			try {
				studentList = StudentExamMapDAO.getStudentList(exam_id);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			request.setAttribute("studentList",studentList);
			request.setAttribute("exam_id",exam_id);
			request.getRequestDispatcher("appliedStudentListView.jsp?result=Select student before Approve").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
