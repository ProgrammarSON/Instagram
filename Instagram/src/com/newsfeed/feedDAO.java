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
	
	private static feedDAO instance = new feedDAO();
	
	public static feedDAO getinstance() {
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

	public LinkedHashMap<String,feedDTO> getNewsFeed(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LinkedHashMap<String,feedDTO> map = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT n.contents, n.user_id, n.NEWSFEED_ID, n.FEED_DATE, n.image_path ");
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
				dto.setImage_path(rs.getString("image_path"));
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
	
	public int insertNewsFeed(feedDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		int check = 0;
		sql.append("INSERT INTO newsfeed ");
		sql.append("VALUES(newsfeed_seq.nextval,?,sysdate,?,?)");
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getUser_id());
			pstmt.setString(2, dto.getContents());
			pstmt.setString(3,dto.getImage_path());
			check = pstmt.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}
	
}
