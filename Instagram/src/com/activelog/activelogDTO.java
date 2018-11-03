package com.activelog;

import java.util.Date;

public class activelogDTO implements Comparable<activelogDTO>{
	private String like_user;
	private String comment_user;
	private String profile_img;
	private long log_date;

		
	public String getProfile_img() {
		return profile_img;
	}
	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}
	public long getLog_date() {
		return log_date;
	}
	public void setLog_date(long log_date) {
		this.log_date = log_date;
	}	
	public String getLike_user() {
		return like_user;
	}
	public void setLike_user(String like_user) {
		this.like_user = like_user;
	}
	public String getComment_user() {
		return comment_user;
	}
	public void setComment_user(String comment_user) {
		this.comment_user = comment_user;
	}
	
	@Override
	public int compareTo(activelogDTO obj) {
		
		return (this.log_date > obj.getLog_date() ? 1 : 
			    this.log_date == obj.getLog_date() ? 0 : -1);
	}

}
