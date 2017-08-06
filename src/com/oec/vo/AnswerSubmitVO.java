package com.oec.vo;

import java.util.List;

public class AnswerSubmitVO {
	private int exam_id;
	private int student_id;
	private int question_id;
	private int question_no;
	private List question_idList;
	private String answer;
	private int min;
	private int sec;
	
	public int getExam_id() {
		return exam_id;
	}

	public void setExam_id(int exam_id) {
		this.exam_id = exam_id;
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public int getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}

	public int getQuestion_no() {
		return question_no;
	}

	public void setQuestion_no(int question_no) {
		this.question_no = question_no;
	}

	public List getQuestion_idList() {
		return question_idList;
	}

	public void setQuestion_idList(List question_idList) {
		this.question_idList = question_idList;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getSec() {
		return sec;
	}

	public void setSec(int sec) {
		this.sec = sec;
	}

	@Override
	public String toString() {
		return "AnswerSubmitVO [exam_id=" + exam_id + ", student_id=" + student_id + ", question_id=" + question_id
				+ ", question_no=" + question_no + ", question_idList=" + question_idList + ", answer=" + answer
				+ ", min=" + min + ", sec=" + sec + "]";
	}
	
}