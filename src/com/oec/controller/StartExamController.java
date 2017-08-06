package com.oec.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oec.dao.ConductExamDAO;
import com.oec.dao.ExamDAO;
import com.oec.vo.ExamStudentQuestionVO;


@WebServlet("/StartExamController")
public class StartExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().isNew()){
			request.getRequestDispatcher("LogoutController").forward(request, response);
			return;
		}
		HttpSession session = request.getSession();
		if(session.isNew()){
			response.sendRedirect("index.jsp");
		}else{
			int exam_id = Integer.parseInt(request.getParameter("exam_id"));
			int student_id = Integer.parseInt(request.getParameter("student_id"));
			try {
				int occurance = ConductExamDAO.getOccurance(exam_id, student_id);
				if(occurance==-1){
					LinkedList<Integer> question_idList = (LinkedList<Integer>) ConductExamDAO.getQuestionId(exam_id);
					int index = ConductExamDAO.getRandomInt(question_idList.size());
					ExamStudentQuestionVO esqVo = ConductExamDAO.getQuestion(question_idList.get(index));
					esqVo.setStudent_id(student_id);
					esqVo.setExam_id(exam_id);
					esqVo.setQuestion_idList(question_idList);
					esqVo.setQuestion_no(1);
					esqVo.setQuestion_id(question_idList.get(index));
					esqVo.setMin(ExamDAO.getHour(exam_id));
					esqVo.setSec(0);
					ConductExamDAO.insertTempData(student_id, exam_id,ConductExamDAO.getCommaSeparatedFromList(question_idList),ExamDAO.getHour(exam_id),0, 1);
					question_idList.remove(index);
					request.setAttribute(student_id+"",esqVo);
					request.getRequestDispatcher("runningExamView.jsp?student_id="+student_id).forward(request, response);
				}else{
					LinkedList<Integer> question_idList = ConductExamDAO.getQuestionList(student_id, exam_id);
					int index = ConductExamDAO.getRandomInt(question_idList.size());
					ExamStudentQuestionVO esqVo = ConductExamDAO.getQuestion(question_idList.get(index));
					esqVo.setStudent_id(student_id);
					esqVo.setExam_id(exam_id);
					esqVo.setQuestion_id(question_idList.get(index));
					esqVo.setQuestion_idList(question_idList);
					question_idList.remove(index);
					esqVo.setQuestion_no(ConductExamDAO.getQuestionCount(exam_id)-question_idList.size());
					String remainHour = ConductExamDAO.getRemainingHour(student_id, exam_id);
					int min = remainHour.split(",")[0].equals("") ? 0 : Integer.parseInt(remainHour.split(",")[0]);
					int sec = remainHour.split(",")[1].equals("") ? 0 : Integer.parseInt(remainHour.split(",")[1]);
					esqVo.setMin(min);
					esqVo.setSec(sec);
					ConductExamDAO.insertTempData(student_id, exam_id,ConductExamDAO.getCommaSeparatedFromList(question_idList),min,sec,ConductExamDAO.getOccurance(exam_id, student_id)+1);
					request.setAttribute(student_id+"",esqVo);
					request.getRequestDispatcher("runningExamView.jsp?student_id="+student_id).forward(request, response);
				}
				} catch (SQLException e){
				e.printStackTrace();
			}

		}
				
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
