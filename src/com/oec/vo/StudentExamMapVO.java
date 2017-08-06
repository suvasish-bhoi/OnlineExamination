package com.oec.vo;

public class StudentExamMapVO {
	private int id;
	private int exam_id;
	private int student_id;
	private int status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "StudentExamMapVO [id=" + id + ", exam_id=" + exam_id + ", student_id=" + student_id + ", status="
				+ status + "]";
	}

}
