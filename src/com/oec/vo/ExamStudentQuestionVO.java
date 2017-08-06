package com.oec.vo;

import java.util.LinkedList;

public class ExamStudentQuestionVO {
	private int exam_id;
	private int student_id;
	private int question_id;
	private String question;
	private String a;
	private String b;
	private String c;
	private String d;
	private String attempt;
	private int question_no;
	private int min;
	private int sec;
	private String time;
	private LinkedList<Integer> question_idList;
	
	@Override
	public String toString() {
		return "ExamStudentQuestionVO [exam_id=" + exam_id + ", student_id=" + student_id + ", question_id="
				+ question_id + ", question=" + question + ", a=" + a + ", b=" + b + ", c=" + c + ", d=" + d
				+ ", attempt=" + attempt + ", question_no=" + question_no + ", min=" + min + ", sec=" + sec + ", time="
				+ time + ", question_idList=" + question_idList + "]";
	}

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

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public String getAttempt() {
		return attempt;
	}

	public void setAttempt(String attempt) {
		this.attempt = attempt;
	}

	public int getQuestion_no() {
		return question_no;
	}

	public void setQuestion_no(int question_no) {
		this.question_no = question_no;
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public LinkedList<Integer> getQuestion_idList() {
		return question_idList;
	}

	public void setQuestion_idList(LinkedList<Integer> question_idList) {
		this.question_idList = question_idList;
	}
	
	}
