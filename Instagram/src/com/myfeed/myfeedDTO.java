package com.myfeed;

public class myfeedDTO {
	String user_id;
	String feed_num;
	String follower_num;
	String following_num;
	String contents;
	String profile_img;
	String comment_count;
	String like_count;
	String follow_check;
	
	public String getFollow_check() {
		return follow_check;
	}
	public void setFollow_check(String follow_check) {
		this.follow_check = follow_check;
	}
	public String getComment_count() {
		return comment_count;
	}
	public void setComment_count(String comment_count) {
		this.comment_count = comment_count;
	}
	public String getLike_count() {
		return like_count;
	}
	public void setLike_count(String like_count) {
		this.like_count = like_count;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getProfile_img() {
		return profile_img;
	}
	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getFeed_num() {
		return feed_num;
	}
	public void setFeed_num(String feed_num) {
		this.feed_num = feed_num;
	}
	public String getFollower_num() {
		return follower_num;
	}
	public void setFollower_num(String follower_num) {
		this.follower_num = follower_num;
	}
	public String getFollowing_num() {
		return following_num;
	}
	public void setFollowing_num(String following_num) {
		this.following_num = following_num;
	}	
}
