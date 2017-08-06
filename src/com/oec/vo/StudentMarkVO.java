package com.oec.vo;

public class StudentMarkVO {
	private int student_id;
	private String name;
	private double mark;
	
	public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getMark() {
		return mark;
	}
	public void setMark(double mark) {
		this.mark = mark;
	}
	@Override
	public String toString() {
		return "StudentMarkVO [student_id=" + student_id + ", name=" + name + ", mark=" + mark + "]";
	}

}
