package com.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class commentDAO {

	private static commentDAO instance = new commentDAO();
	
	public static commentDAO getinstance() {
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
	
	public int insertComment(commentDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO comments ");
		sql.append("VALUES(comment_seq.nextval,?,?,sysdate,?)");
		int check = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getUser_id());
			pstmt.setString(2, dto.getFeed_id());
			pstmt.setString(3, dto.getContent());
			check = pstmt.executeUpdate();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return check;
	}

	public List<commentDTO> getComments(String feedid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<commentDTO> list = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT user_id, comment_date, contents ");
		sql.append("FROM comments ");
		sql.append("WHERE NEWSFEED_ID = ? ");
		sql.append("ORDER BY comment_date");
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, feedid);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				commentDTO dto = new commentDTO();
				dto.setUser_id(rs.getString("user_id"));
				dto.setComment_date(rs.getString("comment_date"));
				dto.setContent(rs.getString("contents"));
				list.add(dto);
				//System.out.println(id+" ggg "+contents);
				//map.get(feedid).getReplys().add(new reply(id,contents));
			
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
