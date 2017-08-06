package com.oec.vo;


public class ExamVO {
	
	private int exam_id;
	private int client_id;
	private String topic;
	private String dateOfCreation;
	private String dateOfExam;
	private long duration;
	private String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getExam_id() {
		return exam_id;
	}
	public void setExam_id(int id) {
		this.exam_id = id;
	}
	public int getClient_id() {
		return client_id;
	}
	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getDateOfCreation() {
		return dateOfCreation;
	}
	public void setDateOfCreation(String dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
	public String getDateOfExam() {
		return dateOfExam;
	}
	public void setDateOfExam(String dateOfExam) {
		this.dateOfExam = dateOfExam;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	@Override
	public String toString() {
		return "ExamVO [exam_id=" + exam_id + ", client_id=" + client_id + ", topic=" + topic + ", dateOfCreation="
				+ dateOfCreation + ", dateOfExam=" + dateOfExam + ", duration=" + duration + ", description="
				+ description + "]";
	}
}
