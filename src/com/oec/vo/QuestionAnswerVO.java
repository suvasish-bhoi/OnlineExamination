package com.oec.vo;

public class QuestionAnswerVO {
	private int question_id;
	private int exam_id;
	private String question;
	private String a;
	private String b;
	private String c;
	private String d;
	private String answer;
	private float mark;
	private float minus;
	
	public int getQuestion_Id() {
		return question_id;
	}
	public void setQuestion_Id(int id) {
		this.question_id = id;
	}
	public int getExam_id() {
		return exam_id;
	}
	public void setExam_id(int exam_id) {
		this.exam_id = exam_id;
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
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public float getMark() {
		return mark;
	}
	public void setMark(float mark) {
		this.mark = mark;
	}
	public float getMinus() {
		return minus;
	}
	public void setMinus(float minus) {
		this.minus = minus;
	}
	@Override
	public String toString() {
		return "QuestionAnswerVO [question_id=" + question_id + ", exam_id=" + exam_id + ", question=" + question + ", a=" + a + ", b="
				+ b + ", c=" + c + ", d=" + d + ", answer=" + answer + ", mark=" + mark + ", minus=" + minus + "]";
	}
}
