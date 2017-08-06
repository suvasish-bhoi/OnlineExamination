package com.oec.vo;

public class ExamInstituteVO {
	private int exam_id;
	private String topic;
	private String dateOfExam;
	private String description;
	private String name;
	private long duration;
	
	public int getExam_id() {
		return exam_id;
	}
	public void setExam_id(int exam_id) {
		this.exam_id = exam_id;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	@Override
	public String toString() {
		return "ExamInstituteVO [exam_id=" + exam_id + ", topic=" + topic + ", dateOfExam=" + dateOfExam
				+ ", description=" + description + ", name=" + name + ", duration=" + duration + "]";
	}
	
}
