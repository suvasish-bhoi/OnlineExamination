package com.oec.vo;

public class ResultVO {
	String question;
	String answer;
	String attempt;
	double mark;
	double total;
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getAttempt() {
		return attempt;
	}
	public void setAttempt(String attempt) {
		this.attempt = attempt;
	}
	public double getMark() {
		return mark;
	}
	public void setMark(double mark) {
		this.mark = mark;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "ResultVO [question=" + question + ", answer=" + answer + ", attempt=" + attempt + ", mark=" + mark
				+ ", total=" + total + "]";
	}
		
}
