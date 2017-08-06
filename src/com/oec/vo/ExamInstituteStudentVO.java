package com.oec.vo;

public class ExamInstituteStudentVO {
	
	private int exam_id;
	private int student_id;
	private int client_id;
	private String client_name;
	private String topic;
	private String dateOfExam;
	private long duration;
	
	
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
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
	public int getClient_id() {
		return client_id;
	}
	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}
	public String getClient_name() {
		return client_name;
	}
	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getDateOfExam() {
		return dateOfExam;
	}
	public void setDateOfExam(String dateOfExam) {
		this.dateOfExam = dateOfExam;
	}
	@Override
	public String toString() {
		return "ExamInstituteStudentVO [exam_id=" + exam_id + ", student_id=" + student_id + ", client_id=" + client_id
				+ ", client_name=" + client_name + ", topic=" + topic + ", dateOfExam=" + dateOfExam + ", duration="
				+ duration + "]";
	}
	

}
