package com.oec.vo;

public class MainLoginVO {
	
	public String toString() {
		return "MainLoginVO [id=" + id + ", client_id=" + client_id + ", username=" + username + ", password="
				+ password + ", group_type=" + group_type + "]";
	}
	private int id;
	private int client_id;
	private String username;
	private String password;
	private String group_type;


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getClient_id() {
		return client_id;
	}
	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGroup_type() {
		return group_type;
	}
	public void setGroup_type(String group_type) {
		this.group_type = group_type;
	}
	
}
