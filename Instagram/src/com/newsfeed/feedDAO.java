package com.newsfeed;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class feedDAO {
	
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

	public LinkedHashMap<String,feedDTO> getNewsFeed(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LinkedHashMap<String,feedDTO> map = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT n.contents, n.user_id, n.NEWSFEED_ID, n.FEED_DATE ");
		sql.append("FROM NEWSFEED n JOIN (SELECT following FROM follow ");
		sql.append("WHERE user_id = ?) p ");
		sql.append("ON n.user_id = p.following ");
		sql.append("ORDER BY n.feed_date DESC");
		
		System.out.println(sql.toString());
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			map = new LinkedHashMap<>();
			
			while(rs.next())
			{
				feedDTO dto = new feedDTO();
				dto.setContents(rs.getString("contents"));
				System.out.println(rs.getString("contents"));
				dto.setUser_id(rs.getString("user_id"));
				dto.setDate(rs.getString("feed_date"));
				dto.setReplys(new ArrayList<>());
				map.put(rs.getString("newsfeed_id"), dto);
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
		return map;
	}
	
	public void getReply(LinkedHashMap<String,feedDTO> map, String feedid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT user_id, contents ");
		sql.append("FROM reply ");
		sql.append("WHERE NEWSFEED_ID = ? ");
		sql.append("ORDER BY reply_date");
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, feedid);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				String id = rs.getString("user_id");
				String contents = rs.getString("contents");
				System.out.println(id+" ggg "+contents);
				map.get(feedid).getReplys().add(new reply(id,contents));
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
		
	}
}
