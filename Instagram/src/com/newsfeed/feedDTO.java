package com.newsfeed;

import java.util.*;

public class feedDTO {
	private String user_id;
	private String contents;
	private String date;
	private String image_path;
	private List<reply> replys;	
	

	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	public List<reply> getReplys() {
		return replys;
	}
	public void setReplys(List<reply> replys) {
		this.replys = replys;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
