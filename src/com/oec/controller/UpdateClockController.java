package com.oec.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oec.dao.ConductExamDAO;
import com.oec.vo.AnswerSubmitVO;
import com.oec.vo.ExamStudentQuestionVO;

@WebServlet("/UpdateClockController")
public class UpdateClockController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().isNew()){
			request.getRequestDispatcher("LogoutController").forward(request, response);
			return;
		}
	BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
	String json = "";
	if(br != null){
		json = br.readLine();
	}
	
	ObjectMapper mapper = new ObjectMapper();
	
	ExamStudentQuestionVO esqVo = mapper.readValue(json, ExamStudentQuestionVO.class);
	String time = esqVo.getTime();
	esqVo.setMin(Integer.parseInt(time.split(":")[1]));
	esqVo.setSec(Integer.parseInt(time.split(":")[2]));
	AnswerSubmitVO answerVo = ConductExamDAO.getAnswerSubmitVO(esqVo);
	try {
		ConductExamDAO.updateTime(answerVo.getStudent_id(),answerVo.getExam_id(),answerVo.getMin(),answerVo.getSec());
		} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
