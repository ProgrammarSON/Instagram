package com.member;

public class memberDTO {
	private String email;
	private String username;
	private String user_id;
	private String password;
	private String profileImg_path;
	
	
	public String getProfileImg_path() {
		return profileImg_path;
	}
	public void setProfileImg_path(String profileImg_path) {
		this.profileImg_path = profileImg_path;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
}


