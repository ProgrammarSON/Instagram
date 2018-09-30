package com.activelog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.util.*;


public class activelogDAO {
	
private static activelogDAO instance = new activelogDAO();
	
	public static activelogDAO getinstance() {
		return instance;
	}
	
	private Connection getConnection() {
		
		Context context = null;
		DataSource dataSource = null;
		Connection connection = null;
		try {
			context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
			connection = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	public List<activelogDTO> getLikeLog(String user_id){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<activelogDTO> list = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT l.user_id AS like_user, like_date, m.PROFILE_IMG ");
		sql.append("FROM likes l JOIN myfeed m ");
		sql.append("ON l.user_id = m.user_id AND ");
		sql.append("l.newsfeed_id IN (SELECT newsfeed_id FROM newsfeed ");
		sql.append("				  WHERE user_id = ? )");
		
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			list = new ArrayList<>();
			
			while(rs.next()) {
				activelogDTO dto = new activelogDTO();
				dto.setLike_user(rs.getString("like_user"));
				dto.setLog_date(rs.getDate("like_date").getTime());
				dto.setProfile_img(rs.getString("profile_img"));
				//dto.setLog_date(rs.getString("like_date"));
				list.add(dto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public List<activelogDTO> getCommentLog(String user_id){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<activelogDTO> list = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT c.user_id AS comment_user, comment_date, m.PROFILE_IMG ");
		sql.append("FROM COMMENTS c JOIN myfeed m ");
		sql.append("ON c.user_id = m.user_id AND ");
		sql.append("c.newsfeed_id IN (SELECT newsfeed_id FROM newsfeed ");
		sql.append("				  WHERE user_id = ?)");
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			list = new ArrayList<>();
			
			while(rs.next()) {
				activelogDTO dto = new activelogDTO();
				dto.setComment_user(rs.getString("comment_user"));
				dto.setLog_date(rs.getDate("comment_date").getTime());
				dto.setProfile_img(rs.getString("profile_img"));
				//dto.setLog_date(rs.getString("comment_date"));
				list.add(dto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
}
