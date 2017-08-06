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

@WebServlet("/ProcessExamController")
public class ProcessExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{  
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
	if(answerVo.getQuestion_idList().contains(null) || answerVo.getMin()+answerVo.getSec()==0){
		request.setAttribute("answervo",answerVo);
		request.getRequestDispatcher("EndExamController").forward(request, response);
		}else{
			try {
				ConductExamDAO.updateTempStore(answerVo);
				if(!answerVo.getAnswer().equals("n")){
					ConductExamDAO.insertMark(answerVo);
				}
				LinkedList<Integer> question_idList = (LinkedList<Integer>) answerVo.getQuestion_idList();
				int index = ConductExamDAO.getRandomInt(question_idList.size());
				esqVo = ConductExamDAO.getQuestion(question_idList.get(index));
				esqVo.setStudent_id(answerVo.getStudent_id());
				esqVo.setExam_id(answerVo.getExam_id());
				esqVo.setQuestion_idList(question_idList);
				esqVo.setQuestion_no(answerVo.getQuestion_no()+1);
				esqVo.setQuestion_id(question_idList.get(index));
				question_idList.remove(index);
				response.setContentType("application/json");
				mapper.writeValue(response.getOutputStream(),esqVo);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
