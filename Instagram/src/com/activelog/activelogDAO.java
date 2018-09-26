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
	
	public List<activelogDTO> getActiveLog(String user_id){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<activelogDTO> list = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT l.user_id AS like_id, c.user_id AS comment_id ");
		sql.append("FROM (SELECT * FROM likes ");
		sql.append("	  WHERE newsfeed_id IN (SELECT newsfeed_id FROM newsfeed ");
		sql.append("							WHERE user_id = ? )) l FULL OUTER JOIN COMMENTS c ");
		sql.append("ON c.NEWSFEED_ID = l.newsfeed_id AND c.USER_ID = ? ");
		
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_id);
			rs = pstmt.executeQuery();
			list = new ArrayList<>();
			
			while(rs.next()) {
				activelogDTO dto = new activelogDTO();
				if(rs.getString("like_id") != null) {
					dto.setLike_id(rs.getString("like_id"));
				}else {
					dto.setComment_id(rs.getString("comment_id"));
				}
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
